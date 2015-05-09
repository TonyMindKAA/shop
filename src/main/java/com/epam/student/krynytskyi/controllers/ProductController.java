package com.epam.student.krynytskyi.controllers;

import com.epam.student.krynytskyi.beans.ProductFormBean;
import com.epam.student.krynytskyi.beans.ProductFormParamBean;
import com.epam.student.krynytskyi.entity.Product;
import com.epam.student.krynytskyi.service.ProductService;
import com.epam.student.krynytskyi.service.ProductServiceImpl;
import com.epam.student.krynytskyi.util.bean.creator.ProductFormBeanCreator;
import com.epam.student.krynytskyi.util.db.mysql.ProductFormParametersParser;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/products")
public class ProductController extends HttpServlet {
    private static final Logger log = Logger.getLogger(ProductController.class);
    private static final long serialVersionUID = 1L;
    public static final String FORM_PARAMETER_TITLE = "title";
    public static final String FORM_PARAMETER_PRICE_FROM = "priceFrom";
    public static final String FORM_PARAMETER_PRICE_TO = "priceTo";
    public static final String FORM_PARAMETER_AMBIENT = "AMBIENT";
    public static final String FROM_PARAMETER_PROTECTED = "PROTECTED";
    public static final String FROM_PARAMETER_CHEAP = "CHEAP";
    public static final String FROM_PARAMETER_NOKIA = "NOKIA";
    public static final String FROM_PARAMETER_SIGMA = "SIGMA";
    public static final String FROM_PARAMETER_APPLE = "APPLE";
    private ProductService productService;

    @Override
    public void init() throws ServletException {
        productService = new ProductServiceImpl(getSqlQueryParts());
    }

    private Map<String, String> getSqlQueryParts() {
        Map<String, String> partsOfSQLQuery = new HashMap<>();
        partsOfSQLQuery.put(FORM_PARAMETER_TITLE, " AND pr.name LIKE '%' ? '%'");
        partsOfSQLQuery.put(FORM_PARAMETER_PRICE_FROM, " AND pr.price >= ?");
        partsOfSQLQuery.put(FORM_PARAMETER_PRICE_TO, " AND pr.price <= ? ");
        partsOfSQLQuery.put(FORM_PARAMETER_AMBIENT, " OR mn.manufacturer = ?");
        partsOfSQLQuery.put(FROM_PARAMETER_PROTECTED, " OR mn.manufacturer = ?");
        partsOfSQLQuery.put(FROM_PARAMETER_CHEAP, " OR mn.manufacturer = ?");
        partsOfSQLQuery.put(FROM_PARAMETER_NOKIA, " OR prt.type = ?");
        partsOfSQLQuery.put(FROM_PARAMETER_SIGMA, " OR prt.type = ?");
        partsOfSQLQuery.put(FROM_PARAMETER_APPLE, " OR prt.type = ?");
        return partsOfSQLQuery;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ProductFormBeanCreator formBeanCreator = new ProductFormBeanCreator();
        ProductFormBean productFormBean = formBeanCreator.create(req);
        List<Product> products = getProductFormUserParams(productFormBean, resp);
        if (products != null) {
            forwardProductsList(req, resp, productFormBean, products);
        }
    }

    private void forwardProductsList(
            HttpServletRequest req, HttpServletResponse resp, ProductFormBean productFormBean, List<Product> products)
            throws ServletException, IOException {
        req.getSession().setAttribute("products", products);
        req.getSession().setAttribute("productFormBean", productFormBean);
        req.getRequestDispatcher("/WEB-INF/pages/shop.jsp").forward(req, resp);
    }

    private List<Product> getProductFormUserParams(ProductFormBean productFormBean, HttpServletResponse resp)
            throws IOException {
        ProductFormParametersParser parametersParser = new ProductFormParametersParser();
        ArrayList<ProductFormParamBean> productFormParamBeans = parametersParser.parse(productFormBean);
        List<Product> products = null;
        products = getProducts(resp, productFormParamBeans, products);
        return products;
    }

    private List<Product> getProducts(
            HttpServletResponse resp, ArrayList<ProductFormParamBean> productFormParamBeans, List<Product> products)
            throws IOException {
        try {
            products = productService.getByParams(productFormParamBeans);
        } catch (Exception e) {
            log.error("Error,can not return products by form param!" + e.getMessage());
            resp.sendError(500);
        }
        return products;
    }
}
