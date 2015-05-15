package com.epam.student.krynytskyi.util.bean.creator;

import com.epam.student.krynytskyi.beans.ProductFacetQueryData;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;

public class ProductFacetQueryDataCreator {
    public static final String FORM_PARAMETER_TITLE = "title";
    public static final String FORM_PARAMETER_PRICE_FROM = "priceFrom";
    public static final String FORM_PARAMETER_PRICE_TO = "priceTo";
    public static final String FROM_PARAMETER_ORDER = "order";
    public static final String FROM_PARAMETER_NUMBER_ITEMS = "numberItems";
    public static final String FROM_PARAMETER_CURRENT_PAGE = "currentPage";

    public ProductFacetQueryData create(HttpServletRequest req) {
        ProductFacetQueryData productFormBean = new ProductFacetQueryData();
        setTitle(req, productFormBean);
        setPriceFrom(req, productFormBean);
        setPriceTo(req, productFormBean);
        setOrder(req, productFormBean);
        setNumberItems(req, productFormBean);
        setCurrentPage(req, productFormBean);
        setTypes(req, productFormBean);
        setManufactures(req, productFormBean);
        return productFormBean;
    }

    private void setPriceTo(HttpServletRequest req, ProductFacetQueryData facetQueryData) {
        String priceTo = req.getParameter(FORM_PARAMETER_PRICE_TO);
        if (priceTo != null)
            facetQueryData.setPriceTo(priceTo.trim());
    }

    private void setPriceFrom(HttpServletRequest req, ProductFacetQueryData facetQueryData) {
        String priceFrom = req.getParameter(FORM_PARAMETER_PRICE_FROM);
        if (priceFrom != null)
            facetQueryData.setPriceFrom(priceFrom.trim());
    }

    private void setTitle(HttpServletRequest req, ProductFacetQueryData facetQueryData) {
        String title = req.getParameter(FORM_PARAMETER_TITLE);
        if (title != null)
            facetQueryData.setTitle(title.trim());
    }

    private void setTypes(HttpServletRequest req, ProductFacetQueryData facetQueryData) {
        String[] productTypes = req.getParameterValues("type");
        if (productTypes != null)
            facetQueryData.setProductTypes(new ArrayList<String>(Arrays.asList(productTypes)));
        else
            facetQueryData.setProductTypes(new ArrayList<String>());
    }

    private void setManufactures(HttpServletRequest req, ProductFacetQueryData facetQueryData) {
        String[] productManufactures = req.getParameterValues("manufacture");
        if (productManufactures != null)
            facetQueryData.setProductManufactures(new ArrayList<String>(Arrays.asList(productManufactures)));
        else
            facetQueryData.setProductManufactures(new ArrayList<String>());
    }

    private void setCurrentPage(HttpServletRequest req, ProductFacetQueryData facetQueryData) {
        String currentPage = req.getParameter(FROM_PARAMETER_CURRENT_PAGE);
        if (currentPage == null)
            facetQueryData.setCurrentPage(((String) req.getServletContext().getAttribute("productCurrentPage")));
        else
            facetQueryData.setCurrentPage(currentPage.trim());
    }

    private void setNumberItems(HttpServletRequest req, ProductFacetQueryData facetQueryData) {
        String numberItems = req.getParameter(FROM_PARAMETER_NUMBER_ITEMS);
        if (numberItems == null)
            facetQueryData.setNumberItems(((String) req.getServletContext().getAttribute("productResultView")));
        else
            facetQueryData.setNumberItems(numberItems.trim());
    }

    private void setOrder(HttpServletRequest req, ProductFacetQueryData facetQueryData) {
        String order = req.getParameter(FROM_PARAMETER_ORDER);
        if (order == null)
            facetQueryData.setOrder(((String) req.getServletContext().getAttribute("productOrder")));
        else
            facetQueryData.setOrder(order.trim());
    }
}
