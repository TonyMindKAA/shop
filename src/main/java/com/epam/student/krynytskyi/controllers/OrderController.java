package com.epam.student.krynytskyi.controllers;

import com.epam.student.krynytskyi.beans.card.CardInfo;
import com.epam.student.krynytskyi.beans.card.CardItem;
import com.epam.student.krynytskyi.containers.CardContainer;
import com.epam.student.krynytskyi.entity.*;
import com.epam.student.krynytskyi.service.OrderService;
import com.epam.student.krynytskyi.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@WebServlet("/order")
public class OrderController extends HttpServlet {
    public static final String CARD_INFO_SESSION_ATTRIBUTE = "cardInfo";
    private OrderService orderService = new OrderServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/finishOrder.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("user");
        if (user != null) {
            CardContainer card = (CardContainer) req.getSession().getAttribute("card");
            Order newOrder =  createOrder(user, card);
            addOrder(newOrder);
            card.clear();
            CardInfo cardInfo = createCardInfo(card);
            req.getSession().setAttribute(CARD_INFO_SESSION_ATTRIBUTE, cardInfo);
            req.getRequestDispatcher("/WEB-INF/pages/finishOrder.jsp").forward(req,resp);
        } else {
            resp.sendRedirect("login");
        }
    }

    private void addOrder(Order newOrder) {
        try {
            orderService.addOrder(newOrder);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<OrderEntry> getOrderEntries(List<CardItem> orderItems) {
        List<OrderEntry>  ordersEntry = new ArrayList<>();
        for (int i = 0; i < orderItems.size(); i++) {
            OrderEntry newOrderEntry = new OrderEntry();
            newOrderEntry.setQuantity(orderItems.get(i).getProductNumbers());
            newOrderEntry.setPrice(orderItems.get(i).getPrice());
            newOrderEntry.setProduct(orderItems.get(i).getProduct().getProduct());
            ordersEntry.add(newOrderEntry);
        }
        return ordersEntry;
    }

    private Order createOrder(User user, CardContainer card) {
        Order newOrder = new Order();
        DeliveryType deliveryType = getDefaultDeliveryType();
        ShippingStatus shippingStatus = getDefaultShippingStatus();
        List<CardItem> orderItems = card.getAll();
        List<OrderEntry> ordersEntry = getOrderEntries(orderItems);
        newOrder.setBankCard("0");
        newOrder.setCreateOn(new Date());
        newOrder.setDeliveryType(deliveryType);
        newOrder.setOrdersEntry(ordersEntry);
        newOrder.setQuantity(card.size());
        newOrder.setShippingAddress("no address");
        newOrder.setShippingStatus(shippingStatus);
        newOrder.setShippingStatusDescription("no msg");
        newOrder.setTotalPrice(card.calculationPurchases());
        newOrder.setUser(user);
        return newOrder;
    }

    private ShippingStatus getDefaultShippingStatus() {
        ShippingStatus shippingStatus = new ShippingStatus();
        shippingStatus.setShippingStatus("pending");
        shippingStatus.setShippingStatusId(1);
        return shippingStatus;
    }

    private DeliveryType getDefaultDeliveryType() {
        DeliveryType deliveryType = new DeliveryType();
        deliveryType.setDeliverType("pickup");
        deliveryType.setDeliveryTypeId(1);
        return deliveryType;
    }

    private CardInfo createCardInfo(CardContainer card) {
        CardInfo cardInfo = new CardInfo();
        cardInfo.setProductsNumber(card.size());
        cardInfo.setTotalCost(card.calculationPurchases());
        return cardInfo;
    }
}
