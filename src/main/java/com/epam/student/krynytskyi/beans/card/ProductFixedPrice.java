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
        if (o == null || getClass() != o.getClass()) return false;

        ProductFixedPrice that = (ProductFixedPrice) o;

        if (price != that.price) return false;
        return !(product != null ? !product.equals(that.product) : that.product != null);

    }

    @Override
    public int hashCode() {
        int result = product != null ? product.hashCode() : 0;
        result = 31 * result + (int) (price ^ (price >>> 32));
        return result;
    }
}
