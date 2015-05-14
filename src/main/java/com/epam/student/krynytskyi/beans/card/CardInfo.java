package com.epam.student.krynytskyi.beans.card;

public class CardInfo {
    private int productsNumber;
    private long totalCost;

    public int getProductsNumber() {
        return productsNumber;
    }

    public CardInfo setProductsNumber(int productsNumber) {
        this.productsNumber = productsNumber;
        return this;
    }

    public long getTotalCost() {
        return totalCost;
    }

    public CardInfo setTotalCost(long totalCost) {
        this.totalCost = totalCost;
        return this;
    }
}
