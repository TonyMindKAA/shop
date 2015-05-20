package com.epam.student.krynytskyi.controllers;

import com.epam.student.krynytskyi.beans.card.CardInfo;
import com.epam.student.krynytskyi.containers.CardContainer;
import com.epam.student.krynytskyi.entity.Order;
import com.epam.student.krynytskyi.entity.User;
import com.epam.student.krynytskyi.service.OrderService;
import com.epam.student.krynytskyi.service.impl.OrderServiceImpl;
import com.epam.student.krynytskyi.util.bean.creator.CardInfoCreator;
import com.epam.student.krynytskyi.util.bean.creator.OrderCreator;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/order")
public class OrderController extends HttpServlet {
    private static final Logger log = Logger.getLogger(OrderController.class);
    private static final String CARD_INFO_SESSION_ATTRIBUTE = "cardInfo";
    private static final String PAGES_FINISH_ORDER_JSP = "/WEB-INF/pages/finishOrder.jsp";
    private static final String PAGES_ERROR_JSP_PATH = "/WEB-INF/pages/error.jsp";
    private static final String LOGIN_SERVLET_PATH = "login";
    private static final String PAGES_FINISH_ORDER_JSP_PATH = "/WEB-INF/pages/finishOrder.jsp";
    private OrderService orderService = new OrderServiceImpl();
    private CardInfoCreator cardInfoCreator = new CardInfoCreator();
    private OrderCreator orderCreator = new OrderCreator();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(PAGES_FINISH_ORDER_JSP_PATH).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("user");
        if (user != null) {
            CardContainer card = (CardContainer) req.getSession().getAttribute("card");
            Order newOrder = orderCreator.create(user, card);
            if (addOrder(newOrder)) {
                card.clear();
                sendResponse(req, resp, card);
            } else
                resp.sendRedirect(PAGES_ERROR_JSP_PATH);
        } else {
            resp.sendRedirect(LOGIN_SERVLET_PATH);
        }
    }

    private void sendResponse(HttpServletRequest req, HttpServletResponse resp, CardContainer card) throws ServletException, IOException {
        CardInfo cardInfo = cardInfoCreator.create(card);
        req.getSession().setAttribute(CARD_INFO_SESSION_ATTRIBUTE, cardInfo);
        req.getRequestDispatcher(PAGES_FINISH_ORDER_JSP).forward(req, resp);
    }

    private boolean addOrder(Order newOrder) {
        try {
            orderService.addOrder(newOrder);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }
}
