package com.epam.student.krynytskyi.beans;

import java.util.List;

/**
 * Created by Anton_Krynytskyi on 5/8/2015.
 */
public class ProductFacetQueryData {
    private String title;
    private String priceFrom;
    private String priceTo;
    private String ambientType;
    private String protectedType;
    private String cheapType;
    private String nokia;
    private String sigma;
    private String apple;
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

    public String getProtectedType() {
        return protectedType;
    }

    public ProductFacetQueryData setProtectedType(String protectedType) {
        this.protectedType = protectedType;
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

    public String getAmbientType() {
        return ambientType;
    }

    public ProductFacetQueryData setAmbientType(String ambientType) {
        this.ambientType = ambientType;
        return this;
    }

    public String getCheapType() {
        return cheapType;
    }

    public ProductFacetQueryData setCheapType(String cheapType) {
        this.cheapType = cheapType;
        return this;
    }

    public String getNokia() {
        return nokia;
    }

    public ProductFacetQueryData setNokia(String nokia) {
        this.nokia = nokia;
        return this;
    }

    public String getSigma() {
        return sigma;
    }

    public ProductFacetQueryData setSigma(String sigma) {
        this.sigma = sigma;
        return this;
    }

    public String getApple() {
        return apple;
    }

    public ProductFacetQueryData setApple(String apple) {
        this.apple = apple;
        return this;
    }

    @Override
    public String toString() {
        return "ProductFacetQueryData{" +
                "title='" + title + '\'' +
                ", priceFrom='" + priceFrom + '\'' +
                ", priceTo='" + priceTo + '\'' +
                ", ambientType='" + ambientType + '\'' +
                ", protectedType='" + protectedType + '\'' +
                ", cheapType='" + cheapType + '\'' +
                ", nokia='" + nokia + '\'' +
                ", sigma='" + sigma + '\'' +
                ", apple='" + apple + '\'' +
                ", order='" + order + '\'' +
                ", numberItems='" + numberItems + '\'' +
                ", currentPage='" + currentPage + '\'' +
                ", productTypes=" + productTypes +
                ", productManufactures=" + productManufactures +
                '}';
    }
}
