package com.epam.student.krynytskyi.service;

import com.epam.student.krynytskyi.entity.ProductType;

import java.util.List;

public interface TypeService {
    List<ProductType> getAll() throws Exception;
}
