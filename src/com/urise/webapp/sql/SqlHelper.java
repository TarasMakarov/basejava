package com.urise.webapp.sql;

import com.urise.webapp.exception.StorageException;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {

    public final ConnectionFactory connectionFactory;

    public SqlHelper(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public <T> T execute(Executant<T> executant, String commandSql) {
        try (PreparedStatement ps = connectionFactory.getConnection().prepareStatement(commandSql)){
            return executant.execute(ps);
        } catch (SQLException e) {
            throw new StorageException(e.getMessage());
        }
    }

    public void accept(String commandSql) {
        execute(PreparedStatement::execute, commandSql);
    }
}
