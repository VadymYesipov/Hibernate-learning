package com.aimprosoft.yesipov.hibernate.db;

import java.sql.ResultSet;

public interface EntityMapper<T> {

    T mapRow(ResultSet resultSet);
}
