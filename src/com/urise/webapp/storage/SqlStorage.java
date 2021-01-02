package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.sql.Executant;
import com.urise.webapp.sql.SqlHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
        sqlHelper.execute(preparedStatement -> {
            preparedStatement.setString(1, r.getFullName());
            preparedStatement.setString(2, r.getUuid());
            preparedStatement.executeUpdate();
            if (preparedStatement.executeUpdate() == 0) {
                throw new NotExistStorageException(r.getUuid());
            }
            return null;
        }, "UPDATE resume SET full_name  = ? WHERE uuid = ?");
    }

    @Override
    public void save(Resume r) throws Exception {
//        try (Connection conn = connectionFactory.getConnection();
//             PreparedStatement ps = conn.prepareStatement("INSERT INTO  resume (uuid, full_name) VALUES (?, ?)")) {
//            ps.setString(1, r.getUuid());
//            ps.setString(2, r.getFullName());
//            ps.execute();
//        } catch (SQLException e) {
//            if (e.getSQLState().equals("23505")) {
//                throw new ExistStorageException(r.getUuid());
//            } else {
//                throw new StorageException(e);
//            }
//        }
        sqlHelper.execute(preparedStatement -> {
            preparedStatement.setString(1, r.getUuid());
            preparedStatement.setString(2, r.getFullName());
            preparedStatement.execute();
        } catch(SQLException e){
            if (e.getSQLState().equals("23505")) {
                throw new ExistStorageException(r.getUuid());
            } else {
                throw new StorageException(e);
            }
        })
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.execute(preparedStatement -> {
                    preparedStatement.setString(1, uuid);
                    ResultSet rs = preparedStatement.executeQuery();
                    if (!rs.next()) {
                        throw new NotExistStorageException(uuid);
                    }
                    Resume r = new Resume(uuid, rs.getString("full_name"));
                    return r;
                }
                , "SELECT * FROM resume r WHERE r.uuid = ?"
        );
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.execute(preparedStatement -> {
            preparedStatement.setString(1, uuid);
            if (preparedStatement.executeUpdate() == 0) {
                throw new NotExistStorageException(uuid);
            }
            return null;
        }, "DELETE FROM resume  WHERE uuid = ?");
    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.execute(preparedStatement -> {
            ResultSet rs = preparedStatement.executeQuery();
            List<Resume> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Resume("uuid", "full_name"));
            }
            return list;
        }, "SELECT * FROM resume r ORDER BY full_name, uuid")
    }

    @Override
    public int size() {
        return sqlHelper.execute(preparedStatement -> {
            ResultSet rs = preparedStatement.executeQuery();
            return (rs.next()) ? rs.getInt(1) : 0;
        }, "SELECT COUNT (*) FROM resume");
    }
}