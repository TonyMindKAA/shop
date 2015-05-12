package com.epam.student.krynytskyi.service;

import com.epam.student.krynytskyi.beans.ProductFormBean;
import com.epam.student.krynytskyi.entity.Product;

import java.util.List;

public interface ProductService {
    int countProducts(ProductFormBean productFormBean) throws Exception;

    List<Product> getByParams(ProductFormBean productFormBean) throws Exception;
}
