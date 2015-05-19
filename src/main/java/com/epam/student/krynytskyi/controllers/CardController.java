package com.epam.student.krynytskyi.controllers;

import com.epam.student.krynytskyi.beans.card.CardInfo;
import com.epam.student.krynytskyi.containers.CardContainer;
import com.epam.student.krynytskyi.entity.Product;
import com.epam.student.krynytskyi.service.ProductService;
import com.epam.student.krynytskyi.util.JSONSerializer;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/card")
public class CardController extends HttpServlet {

    private static final Logger log = Logger.getLogger(CardController.class);
    public static final String PRODUCT_SERVICE_PARAMETER = "productService";
    public static final String PRODUCT_ID_PARAMETER = "id";
    public static final String CARD_JSP_PATH = "/WEB-INF/pages/card.jsp";
    public static final String CARD_INFO_SESSION_ATTRIBUTE = "cardInfo";
    public static final String CARD_SESSION_ATTRIBUTE = "card";
    private JSONSerializer jsonSerializer;
    private ProductService productService;

    @Override
    public void init() throws ServletException {
        productService = (ProductService) getServletContext().getAttribute("productService");
        jsonSerializer = new JSONSerializer();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(CARD_JSP_PATH).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductService productService = (ProductService) req.getServletContext().getAttribute(PRODUCT_SERVICE_PARAMETER);
        CardContainer card = getCardContainer(req);
        addProductToCard(req, productService, card);
        CardInfo cardInfo = createCardInfo(card);
        req.getSession().setAttribute(CARD_INFO_SESSION_ATTRIBUTE, cardInfo);
        print(resp, cardInfo);
    }

    private CardContainer getCardContainer(HttpServletRequest req) {
        CardContainer card = (CardContainer) req.getSession().getAttribute("card");
        if (card == null) {
            card = new CardContainer();
            req.getSession().setAttribute(CARD_SESSION_ATTRIBUTE, card);
        }
        return card;
    }

    private void addProductToCard(HttpServletRequest req, ProductService productService, CardContainer card) {
        String productId = req.getParameter(PRODUCT_ID_PARAMETER);
        Product product = getProductById(productService, productId);
        card.add(product, 1);
    }

    private CardInfo createCardInfo(CardContainer card) {
        CardInfo cardInfo = new CardInfo();
        cardInfo.setProductsNumber(card.size());
        cardInfo.setTotalCost(card.calculationPurchases());
        return cardInfo;
    }

    private Product getProductById(ProductService productService, String productId) {
        try {
            return productService.getById(productId);
        } catch (Exception e) {
            log.error(e);
            return null;
        }
    }

    protected void print(HttpServletResponse response, Object o) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType(jsonSerializer.getContentType());
        try {
            jsonSerializer.serialize(response.getOutputStream(), o);
        } catch (Exception e) {
            log.warn("Cannot serialize object", e);
            response.setContentType("text/html");
            response.sendError(500);
        }
    }
}
