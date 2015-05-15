package com.epam.student.krynytskyi.controllers;

import com.epam.student.krynytskyi.beans.ProductFacetQueryData;
import com.epam.student.krynytskyi.entity.Product;
import com.epam.student.krynytskyi.service.ProductService;
import com.epam.student.krynytskyi.util.bean.creator.ProductFacetQueryDataCreator;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@WebServlet("/products")
public class ProductController extends HttpServlet {
    private static final Logger log = Logger.getLogger(ProductController.class);
    private static final long serialVersionUID = 1L;
    private ProductService productService;

    @Override
    public void init() throws ServletException {
        productService = (ProductService) getServletContext().getAttribute("productService");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductFacetQueryDataCreator formBeanCreator = new ProductFacetQueryDataCreator();
        ProductFacetQueryData queryData = formBeanCreator.create(req);
        try {
            List<Product> products = productService.getByParams(queryData);
            forwardProductsList(req, queryData, products);
            req.getRequestDispatcher("/WEB-INF/pages/shop.jsp").forward(req, resp);
        } catch (Exception e) {
            log.error("Error,can not return products by form param!" + e.getMessage());
            throw new ServletException(e);
        }
    }

    private void forwardProductsList(HttpServletRequest req, ProductFacetQueryData queryData, List<Product> products)
            throws Exception {
        req.setAttribute("products", products);
        req.setAttribute("productFormBean", queryData);
        int productNumbers = productService.countProducts(queryData);
        req.setAttribute("productNumber", productNumbers);

    }

}
