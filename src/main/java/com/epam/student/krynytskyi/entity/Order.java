package com.epam.student.krynytskyi.entity;

import java.util.Date;
import java.util.List;

public class Order {
    private int orderId;
    private List<OrderEntry> ordersEntry;
    private User user;
    private String shippingAddress;
    private Date createOn;
    private ShippingStatus shippingStatus;
    private long totalPrice;
    private String bankCard;
    private DeliveryType deliveryType;
    private int quantity;
    private String shippingStatusDescription;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public List<OrderEntry> getOrdersEntry() {
        return ordersEntry;
    }

    public void setOrdersEntry(List<OrderEntry> ordersEntry) {
        this.ordersEntry = ordersEntry;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    public ShippingStatus getShippingStatus() {
        return shippingStatus;
    }

    public void setShippingStatus(ShippingStatus shippingStatus) {
        this.shippingStatus = shippingStatus;
    }

    public long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(DeliveryType deliveryType) {
        this.deliveryType = deliveryType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getShippingStatusDescription() {
        return shippingStatusDescription;
    }

    public void setShippingStatusDescription(String shippingStatusDescription) {
        this.shippingStatusDescription = shippingStatusDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (orderId != order.orderId) return false;
        if (totalPrice != order.totalPrice) return false;
        if (quantity != order.quantity) return false;
        if (ordersEntry != null ? !ordersEntry.equals(order.ordersEntry) : order.ordersEntry != null) return false;
        if (user != null ? !user.equals(order.user) : order.user != null) return false;
        if (shippingAddress != null ? !shippingAddress.equals(order.shippingAddress) : order.shippingAddress != null)
            return false;
        if (createOn != null ? !createOn.equals(order.createOn) : order.createOn != null) return false;
        if (shippingStatus != null ? !shippingStatus.equals(order.shippingStatus) : order.shippingStatus != null)
            return false;
        if (bankCard != null ? !bankCard.equals(order.bankCard) : order.bankCard != null) return false;
        if (deliveryType != null ? !deliveryType.equals(order.deliveryType) : order.deliveryType != null) return false;
        return !(shippingStatusDescription != null ? !shippingStatusDescription.equals(order.shippingStatusDescription) : order.shippingStatusDescription != null);

    }

    @Override
    public int hashCode() {
        int result = orderId;
        result = 31 * result + (ordersEntry != null ? ordersEntry.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (shippingAddress != null ? shippingAddress.hashCode() : 0);
        result = 31 * result + (createOn != null ? createOn.hashCode() : 0);
        result = 31 * result + (shippingStatus != null ? shippingStatus.hashCode() : 0);
        result = 31 * result + (int) (totalPrice ^ (totalPrice >>> 32));
        result = 31 * result + (bankCard != null ? bankCard.hashCode() : 0);
        result = 31 * result + (deliveryType != null ? deliveryType.hashCode() : 0);
        result = 31 * result + quantity;
        result = 31 * result + (shippingStatusDescription != null ? shippingStatusDescription.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("orderId=").append(orderId);
        sb.append(", ordersEntry=").append(ordersEntry);
        sb.append(", user=").append(user);
        sb.append(", shippingAddress='").append(shippingAddress).append('\'');
        sb.append(", createOn=").append(createOn);
        sb.append(", shippingStatus=").append(shippingStatus);
        sb.append(", totalPrice=").append(totalPrice);
        sb.append(", bankCard='").append(bankCard).append('\'');
        sb.append(", deliveryType=").append(deliveryType);
        sb.append(", quantity=").append(quantity);
        sb.append(", shippingStatusDescription='").append(shippingStatusDescription).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
