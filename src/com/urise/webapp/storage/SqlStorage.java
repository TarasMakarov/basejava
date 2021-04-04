package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.AbstractSection;
import com.urise.webapp.model.ContactType;
import com.urise.webapp.model.Resume;
import com.urise.webapp.model.SectionType;
import com.urise.webapp.sql.SqlHelper;
import com.urise.webapp.util.JsonParser;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SqlStorage implements Storage {

    SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
        sqlHelper.accept("DELETE FROM resume");
    }

    @Override
    public void update(Resume r) {
        sqlHelper.transactionalExecute(conn -> {
            String uuid = r.getUuid();
            try (PreparedStatement ps = conn.prepareStatement("UPDATE resume SET full_name  = ? WHERE uuid = ?")) {
                ps.setString(1, r.getFullName());
                ps.setString(2, uuid);
                if (ps.executeUpdate() == 0) {
                    throw new NotExistStorageException(uuid);
                }
            }
            deleteContact(conn, uuid);
            deleteSection(conn, uuid);
            insertContact(conn, r);
            insertSection(conn, r);
            return null;
        });
    }

    @Override
    public void save(Resume r) {
        sqlHelper.transactionalExecute(conn -> {
                    try (PreparedStatement ps = conn.prepareStatement("INSERT INTO  resume (uuid, full_name) VALUES (?, ?)")) {
                        ps.setString(1, r.getUuid());
                        ps.setString(2, r.getFullName());
                        ps.execute();
                    }
                    insertContact(conn, r);
                    insertSection(conn, r);
                    return null;
                }
        );
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.transactionalExecute(conn -> {
            Resume r;
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM resume WHERE uuid = ?")) {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) {
                    throw new NotExistStorageException(uuid);
                }
                r = new Resume(uuid, rs.getString("full_name"));
            }
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM contact WHERE resume_uuid = ?")) {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    addContact(rs, r);
                }
            }

            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM section WHERE resume_uuid = ?")) {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    addSection(rs, r);
                }
            }
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
        return sqlHelper.transactionalExecute(conn -> {
            Map<String, Resume> resumeMap = new LinkedHashMap<>();
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM resume ORDER BY full_name, uuid")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String uuid = rs.getString("uuid");
                    Resume r = resumeMap.get(uuid);
                    if (r == null) {
                        r = new Resume(uuid, rs.getString("full_name"));
                    }
                    resumeMap.put(uuid, r);
                }
            }
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM contact")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String contactUuid = rs.getString("resume_uuid");
                    Resume r = resumeMap.get(contactUuid);
                    addContact(rs, r);
                }
            }
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM section")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String sectionUuid = rs.getString("resume_uuid");
                    Resume r = resumeMap.get(sectionUuid);
                    addSection(rs, r);
                }
            }
            return new ArrayList<>(resumeMap.values());
        });
    }

    @Override
    public int size() {
        return sqlHelper.execute("SELECT COUNT (*) FROM resume", preparedStatement -> {
            ResultSet rs = preparedStatement.executeQuery();
            return (rs.next()) ? rs.getInt(1) : 0;
        });
    }

    private void insertContact(Connection conn, Resume r) throws SQLException {
        if (!r.getContactsMap().isEmpty()) {
            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO  contact (resume_uuid, type, value) VALUES (?, ?, ?)")) {
                for (Map.Entry<ContactType, String> e : r.getContactsMap().entrySet()) {
                    ps.setString(1, r.getUuid());
                    ps.setString(2, e.getKey().name());
                    ps.setString(3, e.getValue());
                    ps.addBatch();
                }
                ps.executeBatch();
            }
        }
    }

    private void insertSection(Connection conn, Resume r) throws SQLException {
        if (!r.getSectionMap().isEmpty()) {
            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO  section (resume_uuid, type, value) VALUES (?, ?, ?)")) {
                for (Map.Entry<SectionType, AbstractSection> e : r.getSectionMap().entrySet()) {
                    SectionType type = e.getKey();
                    ps.setString(1, r.getUuid());
                    ps.setString(2, e.getKey().name());
                    AbstractSection section = e.getValue();
                    ps.setString(3, JsonParser.write(section, AbstractSection.class));
                    ps.addBatch();
                }
                ps.executeBatch();
            }
        }
    }

    private void deleteContact(Connection conn, String uuid) throws SQLException {
        deleteAttributes(conn, uuid, "DELETE FROM contact  WHERE resume_uuid = ?");
    }

    private void deleteSection(Connection conn, String uuid) throws SQLException {
        deleteAttributes(conn, uuid, "DELETE FROM section  WHERE resume_uuid = ?");
    }

    private void deleteAttributes(Connection conn, String uuid, String commandSql) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(commandSql)) {
            ps.setString(1, uuid);
            ps.execute();
        }
    }

    private void addContact(ResultSet rs, Resume r) throws SQLException {
        String value = rs.getString("value");
        if (value != null) {
            ContactType type = ContactType.valueOf(rs.getString("type"));
            r.setContacts(type, value);
        }
    }

    private void addSection(ResultSet rs, Resume r) throws SQLException {
        String strings = rs.getString("value");
        if (strings != null) {
            SectionType type = SectionType.valueOf(rs.getString("type"));
            r.setSection(type, JsonParser.read(strings, AbstractSection.class));
        }
    }
}