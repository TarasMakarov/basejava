package com.urise.webapp.sql;

import java.sql.PreparedStatement;

public interface Executant<T> {
    T execute(PreparedStatement preparedStatement);
}
