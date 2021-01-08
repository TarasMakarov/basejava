package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.ContactType;
import com.urise.webapp.model.Resume;
import com.urise.webapp.sql.SqlHelper;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class SqlStorage implements Storage {

    SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
        sqlHelper.accept("DELETE FROM resume");
    }

    @Override
    public void update(Resume r) {
        sqlHelper.execute("UPDATE resume SET full_name  = ? WHERE uuid = ?", preparedStatement -> {
            String uuid = r.getUuid();
            preparedStatement.setString(1, r.getFullName());
            preparedStatement.setString(2, uuid);
            preparedStatement.executeUpdate();
            if (preparedStatement.executeUpdate() == 0) {
                throw new NotExistStorageException(uuid);
            }
            return null;
        });
    }

    @Override
    public void save(Resume r) throws Exception {
        sqlHelper.transactionalExecute(conn -> {
                    try (PreparedStatement ps = conn.prepareStatement("INSERT INTO  resume (uuid, full_name) VALUES (?, ?)")) {
                        ps.setString(1, r.getUuid());
                        ps.setString(2, r.getFullName());
                        ps.execute();
                    }
                    try (PreparedStatement ps = conn.prepareStatement("INSERT INTO  contact (resume_uuid, type, value) VALUES (?, ?, ?)")) {
                        for (Map.Entry<ContactType, String> e : r.getContactsMap().entrySet()) {
                            ps.setString(1, r.getUuid());
                            ps.setString(2, e.getKey().name());
                            ps.setString(3, e.getValue());
                            ps.addBatch();
                        }
                        ps.executeBatch();
                    }
                    return null;
                }
        );
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.execute("" +
                        "SELECT * FROM resume r " +
                        "LEFT JOIN contact c " +
                        "    ON r.uuid = c.resume_uuid" +
                        " WHERE r.uuid = ?",
                preparedStatement -> {
                    preparedStatement.setString(1, uuid);
                    ResultSet rs = preparedStatement.executeQuery();
                    if (!rs.next()) {
                        throw new NotExistStorageException(uuid);
                    }
                    Resume r = new Resume(uuid, rs.getString("full_name"));
                    do {
                        ContactType type = ContactType.valueOf(rs.getString("type"));
                        String value = rs.getString("value");
                        r.setContacts(type, value);
                    } while (rs.next());
                    return r;
                });

    }

    @Override
    public void delete(String uuid) {
        sqlHelper.execute("DELETE FROM resume  WHERE uuid = ?", preparedStatement -> {
            preparedStatement.setString(1, uuid);
            if (preparedStatement.executeUpdate() == 0) {
                throw new NotExistStorageException(uuid);
            }
            return null;
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.execute("" +
                "SELECT * FROM resume r " +
                "LEFT JOIN contact c " +
                "ON r.uuid = c.resume_uuid " +
                "ORDER BY full_name, uuid", preparedStatement -> {
            ResultSet rs = preparedStatement.executeQuery();
            Map<String, Resume> resumeMap = new HashMap<>();
            while (rs.next()) {
                String uuid = rs.getString("uuid");
                Resume r = new Resume(uuid, rs.getString("full_name"));
                resumeMap.put(uuid, r);
                String value = rs.getString("value");
                ContactType type = ContactType.valueOf(rs.getString("type"));
                r.setContacts(type, value);
            }
            ArrayList<Resume> list = new ArrayList<>(resumeMap.values());
            Collections.sort(list);
            return list;
        });
    }

    @Override
    public int size() {
        return sqlHelper.execute("SELECT COUNT (*) FROM resume", preparedStatement -> {
            ResultSet rs = preparedStatement.executeQuery();
            return (rs.next()) ? rs.getInt(1) : 0;
        });
    }
}