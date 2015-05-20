package com.epam.student.krynytskyi.util.bean.creator;

import com.epam.student.krynytskyi.beans.card.CardItem;
import com.epam.student.krynytskyi.containers.CardContainer;
import com.epam.student.krynytskyi.entity.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class OrderCreator {

    public Order create(User user, CardContainer card) {
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

    private List<OrderEntry> getOrderEntries(List<CardItem> orderItems) {
        List<OrderEntry> ordersEntry = new ArrayList<>();
        for (int i = 0; i < orderItems.size(); i++) {
            OrderEntry newOrderEntry = new OrderEntry();
            newOrderEntry.setQuantity(orderItems.get(i).getProductNumbers());
            newOrderEntry.setPrice(orderItems.get(i).getPrice());
            newOrderEntry.setProduct(orderItems.get(i).getProduct().getProduct());
            ordersEntry.add(newOrderEntry);
        }
        return ordersEntry;
    }
}
