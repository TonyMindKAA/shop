package com.epam.student.krynytskyi.beans;

import java.util.List;

public class ProductFacetQueryData {
    private String title;
    private String priceFrom;
    private String priceTo;
    private String order;
    private String numberItems;
    private String currentPage;
    private List<String> productTypes;
    private List<String> productManufactures;

    public List<String> getProductTypes() {
        return productTypes;
    }

    public void setProductTypes(List<String> productTypes) {
        this.productTypes = productTypes;
    }

    public List<String> getProductManufactures() {
        return productManufactures;
    }

    public void setProductManufactures(List<String> productManufactures) {
        this.productManufactures = productManufactures;
    }

    public String getOrder() {
        return order;
    }

    public ProductFacetQueryData setOrder(String order) {
        this.order = order;
        return this;
    }

    public String getNumberItems() {
        return numberItems;
    }

    public ProductFacetQueryData setNumberItems(String numberItems) {
        this.numberItems = numberItems;
        return this;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public ProductFacetQueryData setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
        return this;
    }


    public String getTitle() {
        return title;
    }

    public ProductFacetQueryData setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getPriceFrom() {
        return priceFrom;
    }

    public ProductFacetQueryData setPriceFrom(String priceFrom) {
        this.priceFrom = priceFrom;
        return this;
    }

    public String getPriceTo() {
        return priceTo;
    }

    public ProductFacetQueryData setPriceTo(String priceTo) {
        this.priceTo = priceTo;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ProductFacetQueryData{");
        sb.append("title='").append(title).append('\'');
        sb.append(", priceFrom='").append(priceFrom).append('\'');
        sb.append(", priceTo='").append(priceTo).append('\'');
        sb.append(", order='").append(order).append('\'');
        sb.append(", numberItems='").append(numberItems).append('\'');
        sb.append(", currentPage='").append(currentPage).append('\'');
        sb.append(", productTypes=").append(productTypes);
        sb.append(", productManufactures=").append(productManufactures);
        sb.append('}');
        return sb.toString();
    }
}
