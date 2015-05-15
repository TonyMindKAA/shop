package com.epam.student.krynytskyi.util.bean.creator;

import com.epam.student.krynytskyi.beans.ProductFacetQueryData;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Anton_Krynytskyi on 5/8/2015.
 */
public class ProductFacetQueryDataCreator {
    public static final String FORM_PARAMETER_TITLE = "title";
    public static final String FORM_PARAMETER_PRICE_FROM = "priceFrom";
    public static final String FORM_PARAMETER_PRICE_TO = "priceTo";
    public static final String FORM_PARAMETER_AMBIENT = "AMBIENT";
    public static final String FROM_PARAMETER_PROTECTED = "PROTECTED";
    public static final String FROM_PARAMETER_CHEAP = "CHEAP";
    public static final String FROM_PARAMETER_NOKIA = "NOKIA";
    public static final String FROM_PARAMETER_SIGMA = "SIGMA";
    public static final String FROM_PARAMETER_APPLE = "APPLE";
    public static final String FROM_PARAMETER_ORDER = "order";
    public static final String FROM_PARAMETER_NUMBER_ITEMS = "numberItems";
    public static final String FROM_PARAMETER_CURRENT_PAGE = "currentPage";

    public ProductFacetQueryData create(HttpServletRequest req) {
        ProductFacetQueryData productFormBean = new ProductFacetQueryData();
        productFormBean.setTitle(req.getParameter(FORM_PARAMETER_TITLE));
        productFormBean.setPriceFrom(req.getParameter(FORM_PARAMETER_PRICE_FROM));
        productFormBean.setPriceTo(req.getParameter(FORM_PARAMETER_PRICE_TO));
        productFormBean.setAmbientType(req.getParameter(FORM_PARAMETER_AMBIENT));
        productFormBean.setProtectedType(req.getParameter(FROM_PARAMETER_PROTECTED));
        productFormBean.setCheapType(req.getParameter(FROM_PARAMETER_CHEAP));
        productFormBean.setNokia(req.getParameter(FROM_PARAMETER_NOKIA));
        productFormBean.setSigma(req.getParameter(FROM_PARAMETER_SIGMA));
        productFormBean.setApple(req.getParameter(FROM_PARAMETER_APPLE));
        setOrder(req, productFormBean);
        setNumberItems(req, productFormBean);
        setCurrentPage(req, productFormBean);
        setTypes(req, productFormBean);
        setManufactures(req, productFormBean);
        return productFormBean;
    }

    private void setTypes(HttpServletRequest req, ProductFacetQueryData productFormBean) {
        String[] productTypes = req.getParameterValues("type");
        if (productTypes != null)
            productFormBean.setProductTypes(new ArrayList<String>(Arrays.asList(productTypes)));
        else
            productFormBean.setProductTypes(new ArrayList<String>());
    }

    private void setManufactures(HttpServletRequest req, ProductFacetQueryData productFormBean) {
        String[] productManufactures = req.getParameterValues("manufactures");
        if(productManufactures != null)
            productFormBean.setProductManufactures(new ArrayList<String>(Arrays.asList(productManufactures)));
        else
            productFormBean.setProductManufactures(new ArrayList<String>());
    }

    private void setCurrentPage(HttpServletRequest req, ProductFacetQueryData productFormBean) {
        String currentPage = req.getParameter(FROM_PARAMETER_CURRENT_PAGE);
        if (currentPage == null)
            productFormBean.setCurrentPage((String) req.getServletContext().getAttribute("productCurrentPage"));
        else
            productFormBean.setCurrentPage(currentPage);
    }

    private void setNumberItems(HttpServletRequest req, ProductFacetQueryData productFormBean) {
        String numberItems = req.getParameter(FROM_PARAMETER_NUMBER_ITEMS);
        if (numberItems == null)
            productFormBean.setNumberItems((String) req.getServletContext().getAttribute("productResultView"));
        else
            productFormBean.setNumberItems(numberItems);
    }

    private void setOrder(HttpServletRequest req, ProductFacetQueryData productFormBean) {
        String order = req.getParameter(FROM_PARAMETER_ORDER);
        if (order == null)
            productFormBean.setOrder((String) req.getServletContext().getAttribute("productOrder"));
        else
            productFormBean.setOrder(order);
    }
}
