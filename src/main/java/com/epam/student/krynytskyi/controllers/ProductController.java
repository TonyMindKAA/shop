package com.epam.student.krynytskyi.controllers;

import com.epam.student.krynytskyi.beans.PrepareStatementBuilderParamsBean;
import com.epam.student.krynytskyi.beans.ProductFormBean;
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
import java.util.List;

@WebServlet("/products")
public class ProductController extends HttpServlet {
    private static final Logger log = Logger.getLogger(ProductController.class);
    private static final long serialVersionUID = 1L;
    private ProductService productService = new ProductServiceImpl();

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
        PrepareStatementBuilderParamsBean builderParamsBean = parametersParser.parse(productFormBean);
        log.debug(builderParamsBean.getSqlQuery());
        List<Product> products = null;
        products = getProducts(resp, builderParamsBean, products);
        return products;
    }

    private List<Product> getProducts(
            HttpServletResponse resp, PrepareStatementBuilderParamsBean productFormParamBeans, List<Product> products)
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
