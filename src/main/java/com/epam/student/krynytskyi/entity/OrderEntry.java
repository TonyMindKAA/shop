package com.epam.student.krynytskyi.entity;

/**
 * Created by Anton_Krynytskyi on 5/20/2015.
 */
public class OrderEntry {
    private int orderEntryId;
    private int orderId;
    private Product product;
    private int quantity;
    private long price;

    public int getOrderEntryId() {
        return orderEntryId;
    }

    public OrderEntry setOrderEntryId(int orderEntryId) {
        this.orderEntryId = orderEntryId;
        return this;
    }

    public int getOrderId() {
        return orderId;
    }

    public OrderEntry setOrderId(int orderId) {
        this.orderId = orderId;
        return this;
    }

    public Product getProduct() {
        return product;
    }

    public OrderEntry setProduct(Product product) {
        this.product = product;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public OrderEntry setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public long getPrice() {
        return price;
    }

    public OrderEntry setPrice(long price) {
        this.price = price;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderEntry that = (OrderEntry) o;

        if (orderEntryId != that.orderEntryId) return false;
        if (orderId != that.orderId) return false;
        if (quantity != that.quantity) return false;
        if (price != that.price) return false;
        return !(product != null ? !product.equals(that.product) : that.product != null);

    }

    @Override
    public int hashCode() {
        int result = orderEntryId;
        result = 31 * result + orderId;
        result = 31 * result + (product != null ? product.hashCode() : 0);
        result = 31 * result + quantity;
        result = 31 * result + (int) (price ^ (price >>> 32));
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OrderEntry{");
        sb.append("orderEntryId=").append(orderEntryId);
        sb.append(", orderId=").append(orderId);
        sb.append(", product=").append(product);
        sb.append(", quantity=").append(quantity);
        sb.append(", price=").append(price);
        sb.append('}');
        return sb.toString();
    }
}
