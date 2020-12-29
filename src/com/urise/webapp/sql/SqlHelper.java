package com.urise.webapp.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper implements Executant{

    public final ConnectionFactory connectionFactory;

    public SqlHelper(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public <T> T execute (Executant<T> executant, String commandSql) throws SQLException {
        PreparedStatement ps = connectionFactory.getConnection().prepareStatement(commandSql);

        return null;
    }
}
