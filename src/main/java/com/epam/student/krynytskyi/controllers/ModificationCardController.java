package com.epam.student.krynytskyi.controllers;

import com.epam.student.krynytskyi.containers.CardContainer;
import com.epam.student.krynytskyi.entity.Product;
import com.epam.student.krynytskyi.service.ProductService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/carders")
public class ModificationCardController extends HttpServlet {
    private static final Logger log = Logger.getLogger(ModificationCardController.class);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CardContainer card = (CardContainer) req.getSession().getAttribute("card");
        ProductService productService = (ProductService) getServletContext().getAttribute("productService");
        log.debug("KAA-PRO");
        String productId = req.getParameter("id");
        log.debug(productId);
        String  productsNumber = req.getParameter("productsNumber");
        log.debug(productsNumber);
        Product product = null;
        try {
            product = productService.getById(productId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(product != null) {
            int numbers = Integer.parseInt(productsNumber);
            card.add(product, numbers);
        }
    }
}
