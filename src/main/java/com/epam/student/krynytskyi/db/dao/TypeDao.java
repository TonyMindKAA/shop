package com.epam.student.krynytskyi.db.dao;

import com.epam.student.krynytskyi.entity.ProductType;

import java.sql.Connection;
import java.util.List;

public interface TypeDao {
    List<ProductType> getAll(Connection conn) throws Exception;
}
