package com.epam.student.krynytskyi.service;

import com.epam.student.krynytskyi.beans.ProductFormParamBean;
import com.epam.student.krynytskyi.entity.Product;

import java.util.List;

/**
 * Created by Anton_Krynytskyi on 5/8/2015.
 */
public interface ProductService {
    int countProducts() throws Exception;

    List<Product> getByParams(List<ProductFormParamBean> param) throws Exception;
}
