package com.epam.student.krynytskyi.util.db.mysql;

import com.epam.student.krynytskyi.beans.ProductFormBean;
import com.epam.student.krynytskyi.beans.ProductFormParamBean;
import org.apache.log4j.Logger;

import java.util.ArrayList;

import static com.epam.student.krynytskyi.db.constant.ProductManufactureConst.*;
import static com.epam.student.krynytskyi.db.constant.ProductTypeConst.*;

/**
 * Created by Anton_Krynytskyi on 5/8/2015.
 */
public class ProductFormParametersParser {
    private static final Logger log = Logger.getLogger(ProductFormParametersParser.class);
    public static final String FORM_PARAMETER_TITLE = "title";
    public static final String FORM_PARAMETER_PRICE_FROM = "priceFrom";
    public static final String FORM_PARAMETER_PRICE_TO = "priceTo";
    public static final String FORM_PARAMETER_AMBIENT = "AMBIENT";
    public static final String FROM_PARAMETER_PROTECTED = "PROTECTED";
    public static final String FROM_PARAMETER_CHEAP = "CHEAP";
    public static final String FROM_PARAMETER_NOKIA = "NOKIA";
    public static final String FROM_PARAMETER_SIGMA = "SIGMA";
    public static final String FROM_PARAMETER_APPLE = "APPLE";


    public ArrayList<ProductFormParamBean> parse(ProductFormBean productFormBean) {
        ArrayList<ProductFormParamBean> productFormParamBeans = new ArrayList<>();
        if (isParameterExist(productFormBean.getTitle())) {
            setParameterFromBean(productFormParamBeans, FORM_PARAMETER_TITLE, productFormBean.getTitle());
        }
        if (isParameterExist(productFormBean.getPriceFrom())) {
            setParameterFromBean(productFormParamBeans, FORM_PARAMETER_PRICE_FROM, productFormBean.getPriceFrom());
        }
        if (isParameterExist(productFormBean.getPriceTo())) {
            setParameterFromBean(productFormParamBeans, FORM_PARAMETER_PRICE_TO, productFormBean.getPriceTo());
        }
        if (isParameterExist(productFormBean.getAmbientType())) {
            setParameterFromBean(productFormParamBeans, FORM_PARAMETER_AMBIENT, AMBIENT_PRODUCT_TYPE);
        }
        if (isParameterExist(productFormBean.getProtectedType())) {
            setParameterFromBean(productFormParamBeans, FROM_PARAMETER_PROTECTED, PROTECTED_PRODUCT_TYPE);
        }
        if (isParameterExist(productFormBean.getCheapType())) {
            setParameterFromBean(productFormParamBeans, FROM_PARAMETER_CHEAP, ÑHEAP_PRODUCT_TYPE);
        }
        if (isParameterExist(productFormBean.getNokia())) {
            setParameterFromBean(productFormParamBeans, FROM_PARAMETER_NOKIA, NOKIA_PRODUCT_MANUFACTURES);
        }
        if (isParameterExist(productFormBean.getSigma())) {
            setParameterFromBean(productFormParamBeans, FROM_PARAMETER_SIGMA, SIGMA_PRODUCT_MANUFACTURES);
        }
        if (isParameterExist(productFormBean.getApple())) {
            setParameterFromBean(productFormParamBeans, FROM_PARAMETER_APPLE, APPLE_PRODUCT_MANUFACTURES);
        }
        return productFormParamBeans;
    }

    private boolean isParameterExist(String parameter) {
        log.debug(parameter);
        return parameter != null && !parameter.isEmpty();
    }

    private void setParameterFromBean(
            ArrayList<ProductFormParamBean> productFormParamBeans, String formParameter, String paramValue) {
        ProductFormParamBean paramBean = new ProductFormParamBean();
        paramBean.setName(formParameter);
        paramBean.setValue(paramValue);
        productFormParamBeans.add(paramBean);
    }
}
