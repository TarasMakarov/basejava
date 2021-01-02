package com.urise.webapp.sql;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.StorageException;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {

//    private String uuid;

    public final ConnectionFactory connectionFactory;

    public SqlHelper(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

//    public void setId(String id) {
//        this.uuid = id;
//    }

    public <T> T execute(Executant<T> executant, String commandSql) {
        try (PreparedStatement ps = connectionFactory.getConnection().prepareStatement(commandSql)){
            return executant.execute(ps);
        } catch (SQLException e) {
            if(e.getSQLState().equals("23505")) {
                throw new ExistStorageException("");
            }
            throw new StorageException(e);
        }
    }

    public void accept(String commandSql) {
        execute(PreparedStatement::execute, commandSql);
    }
}
