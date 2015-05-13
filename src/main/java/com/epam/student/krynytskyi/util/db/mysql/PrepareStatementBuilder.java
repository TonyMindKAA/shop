package com.epam.student.krynytskyi.util.db.mysql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface PrepareStatementBuilder {

    String getQuery();

    PreparedStatement build(PreparedStatement statement) throws SQLException;
}
