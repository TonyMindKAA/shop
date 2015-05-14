package com.epam.student.krynytskyi.beans.card;

import com.epam.student.krynytskyi.entity.Product;

public class ProductFixedPrice {
    private Product product;
    private long price;

    public ProductFixedPrice(Product product) {
        this.product = product;
        this.price = product.getPrice();
    }

    public Product getProduct() {
        return product;
    }

    public long getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || (getClass() != o.getClass() && o.getClass() != Product.class)) return false;

        ProductFixedPrice that = (ProductFixedPrice) o;

        return !(product != null ? !product.equals(that.product) : that.product != null);

    }

    @Override
    public int hashCode() {
        return product != null ? product.hashCode() : 0;
    }
}
