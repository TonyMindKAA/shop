package com.epam.student.krynytskyi.db.dto;

import com.epam.student.krynytskyi.entity.Product;

import java.sql.ResultSet;

/**
 * Created by Anton_Krynytskyi on 5/8/2015.
 */
public interface ProductDTO {
    Product extract(ResultSet resSet) throws Exception;
}
