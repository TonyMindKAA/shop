package com.epam.student.krynytskyi.service;

import com.epam.student.krynytskyi.entity.Manufacture;

import java.sql.Connection;
import java.util.List;

public interface ManufactureService {
    List<Manufacture> getAll(Connection connection) throws Exception;
}
