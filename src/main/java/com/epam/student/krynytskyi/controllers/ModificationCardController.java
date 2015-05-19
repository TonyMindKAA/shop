package com.epam.student.krynytskyi.controllers;

import com.epam.student.krynytskyi.beans.card.CardInfo;
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
    public static final String CARD_INFO_SESSION_ATTRIBUTE = "cardInfo";
    private CardContainer card;
    private ProductService productService;

    @Override
    public void init() throws ServletException {
        productService = (ProductService) getServletContext().getAttribute("productService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        card = (CardContainer) req.getSession().getAttribute("card");
        String command = req.getParameter("command");
        executeCommand(req, resp, command);
        CardInfo cardInfo = createCardInfo(card);
        req.getSession().setAttribute(CARD_INFO_SESSION_ATTRIBUTE, cardInfo);
    }

    private void executeCommand(HttpServletRequest req, HttpServletResponse resp, String command) {
        if (command != null) {
            switch (command) {
                case "delete":
                    deleteProduct(req);
                    break;
                case "update":
                    updateProduct(req);
                    break;
                default:
                    resp.setStatus(500);
            }
        }
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

    private CardInfo createCardInfo(CardContainer card) {
        CardInfo cardInfo = new CardInfo();
        cardInfo.setProductsNumber(card.size());
        cardInfo.setTotalCost(card.calculationPurchases());
        return cardInfo;
    }
}
