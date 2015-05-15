package com.epam.student.krynytskyi.db.dao.mysql;

import com.epam.student.krynytskyi.entity.Manufacture;

import java.sql.Connection;
import java.util.List;

public interface MnufactureDAO {
    List<Manufacture> getAll(Connection connection) throws Exception;
}
