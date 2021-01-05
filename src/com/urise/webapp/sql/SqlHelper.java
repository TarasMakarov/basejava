package com.urise.webapp.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {

    private final ConnectionFactory connectionFactory;

    public SqlHelper(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public <T> T execute(String commandSql, Executant<T> executant) {
        try (PreparedStatement ps = connectionFactory.getConnection().prepareStatement(commandSql)) {
            return executant.execute(ps);
        } catch (SQLException e) {
            throw ExceptionUtil.convertException(e);
        }
    }

    public void accept(String commandSql) {
        execute(commandSql, PreparedStatement::execute);
    }
}