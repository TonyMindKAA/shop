package com.epam.student.krynytskyi.util;

import com.epam.student.krynytskyi.beans.ProductFormBean;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Anton_Krynytskyi on 5/8/2015.
 */
public class ProductFormBeanCreator {
    public static final String FORM_PARAMETER_TITLE = "title";
    public static final String FORM_PARAMETER_PRICE_FROM = "priceFrom";
    public static final String FORM_PARAMETER_PRICE_TO = "priceTo";
    public static final String FORM_PARAMETER_AMBIENT = "AMBIENT";
    public static final String FROM_PARAMETER_PROTECTED = "PROTECTED";
    public static final String FROM_PARAMETER_CHEAP = "ÑHEAP";
    public static final String FROM_PARAMETER_NOKIA = "NOKIA";
    public static final String FROM_PARAMETER_SIGMA = "SIGMA";
    public static final String FROM_PARAMETER_APPLE = "APPLE";

    public ProductFormBean create(HttpServletRequest req) {
        ProductFormBean productFormBean = new ProductFormBean();
        productFormBean.setTitle(req.getParameter(FORM_PARAMETER_TITLE));
        productFormBean.setPriceFrom(req.getParameter(FORM_PARAMETER_PRICE_FROM));
        productFormBean.setPriceTo(req.getParameter(FORM_PARAMETER_PRICE_TO));
        productFormBean.setAmbientType(req.getParameter(FORM_PARAMETER_AMBIENT));
        productFormBean.setProtectedType(req.getParameter(FROM_PARAMETER_PROTECTED));
        productFormBean.setCheapType(req.getParameter(FROM_PARAMETER_CHEAP));
        productFormBean.setNokia(req.getParameter(FROM_PARAMETER_NOKIA));
        productFormBean.setSigma(req.getParameter(FROM_PARAMETER_SIGMA));
        productFormBean.setApple(req.getParameter(FROM_PARAMETER_APPLE));
        return productFormBean;
    }
}
