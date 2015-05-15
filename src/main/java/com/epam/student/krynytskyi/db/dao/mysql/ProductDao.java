package com.epam.student.krynytskyi.db.dao.mysql;

import com.epam.student.krynytskyi.entity.Product;
import com.epam.student.krynytskyi.util.db.mysql.PrepareStatementBuilder;
import com.epam.student.krynytskyi.util.db.mysql.PrepareStatementBuilderImpl;

import java.sql.Connection;
import java.util.List;

public interface ProductDao {
    int countProducts(Connection conn, PrepareStatementBuilder statementBuilder) throws Exception;

    List<Product> getByParams(Connection conn, PrepareStatementBuilder statementBuilder) throws Exception;
}