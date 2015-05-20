package com.epam.student.krynytskyi.util;

import com.epam.student.krynytskyi.containers.CardContainer;
import com.epam.student.krynytskyi.entity.Product;
import com.epam.student.krynytskyi.service.ProductService;

import javax.servlet.http.HttpServletRequest;

public class CardManger {
    private ProductService productService;
    private CardContainer card;

    public  boolean manage(HttpServletRequest req, CardContainer card) {
        productService = (ProductService) req.getServletContext().getAttribute("productService");
        this.card = card;
        String command = req.getParameter("command");
        if (command != null) {
            switch (command) {
                case "delete":
                    deleteProduct(req);
                    return true;
                case "update":
                    updateProduct(req);
                    return true;
                default:
                    return false;
            }
        }
        return false;
    }

    private void updateProduct(HttpServletRequest req) {
        String id = req.getParameter("id");
        String productsNumber = req.getParameter("productsNumber");
        int productsNumberInt = Integer.parseInt(productsNumber);
        Product product = null;
        try {
            product = productService.getById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        card.set(product, productsNumberInt);
    }

    private void deleteProduct(HttpServletRequest req) {
        String id = req.getParameter("id");
        Product product = null;
        try {
            product = productService.getById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        card.remove(product);
    }
}
