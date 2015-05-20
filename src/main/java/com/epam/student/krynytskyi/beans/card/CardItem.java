package com.epam.student.krynytskyi.beans.card;

public class CardItem {
    private ProductFixedPrice product;
    private Integer productNumbers;
    private long price;

    public CardItem(ProductFixedPrice product, Integer productNumbers, long price) {
        this.product = product;
        this.productNumbers = productNumbers;
        this.price = price;
    }

public long getPrice() {
        return price;
    }

    public CardItem setPrice(long price) {
        this.price = price;
        return this;
    }

    public ProductFixedPrice getProduct() {
        return product;
    }

    public CardItem setProduct(ProductFixedPrice product) {
        this.product = product;
        return this;
    }

    public Integer getProductNumbers() {
        return productNumbers;
    }

    public CardItem setProductNumbers(Integer productNumbers) {
        this.productNumbers = productNumbers;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CardItem cardItem = (CardItem) o;

        if (price != cardItem.price) return false;
        if (product != null ? !product.equals(cardItem.product) : cardItem.product != null) return false;
        return !(productNumbers != null ? !productNumbers.equals(cardItem.productNumbers) : cardItem.productNumbers != null);

    }

    @Override
    public int hashCode() {
        int result = product != null ? product.hashCode() : 0;
        result = 31 * result + (productNumbers != null ? productNumbers.hashCode() : 0);
        result = 31 * result + (int) (price ^ (price >>> 32));
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CardItem{");
        sb.append("product=").append(product);
        sb.append(", productNumbers=").append(productNumbers);
        sb.append(", price=").append(price);
        sb.append('}');
        return sb.toString();
    }
}
