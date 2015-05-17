package com.epam.student.krynytskyi.service;

import com.epam.student.krynytskyi.beans.ProductFacetQueryData;
import com.epam.student.krynytskyi.entity.Product;

import java.sql.Connection;
import java.util.List;

public interface ProductService {
    int countProducts(ProductFacetQueryData productFormBean) throws Exception;

    List<Product> getByParams(ProductFacetQueryData productFormBean) throws Exception;

    Product getById(String id) throws Exception;
}
