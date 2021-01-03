package com.urise.webapp.sql;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.StorageException;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {

    private final ConnectionFactory connectionFactory;

    public SqlHelper(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public <T> T execute(String commandSql, Executant<T> executant) {
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
        execute(commandSql, PreparedStatement::execute);
    }
}