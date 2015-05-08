package com.epam.student.krynytskyi.beans;

/**
 * Created by Anton_Krynytskyi on 5/8/2015.
 */
public class ProductFormBean {
    private String title;
    private String priceFrom;
    private String priceTo;
    private String ambientType;
    private String protectedType;
    private String cheapType;
    private String nokia;
    private String sigma;
    private String apple;

    public String getProtectedType() {
        return protectedType;
    }

    public ProductFormBean setProtectedType(String protectedType) {
        this.protectedType = protectedType;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public ProductFormBean setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getPriceFrom() {
        return priceFrom;
    }

    public ProductFormBean setPriceFrom(String priceFrom) {
        this.priceFrom = priceFrom;
        return this;
    }

    public String getPriceTo() {
        return priceTo;
    }

    public ProductFormBean setPriceTo(String priceTo) {
        this.priceTo = priceTo;
        return this;
    }

    public String getAmbientType() {
        return ambientType;
    }

    public ProductFormBean setAmbientType(String ambientType) {
        this.ambientType = ambientType;
        return this;
    }

    public String getCheapType() {
        return cheapType;
    }

    public ProductFormBean setCheapType(String cheapType) {
        this.cheapType = cheapType;
        return this;
    }

    public String getNokia() {
        return nokia;
    }

    public ProductFormBean setNokia(String nokia) {
        this.nokia = nokia;
        return this;
    }

    public String getSigma() {
        return sigma;
    }

    public ProductFormBean setSigma(String sigma) {
        this.sigma = sigma;
        return this;
    }

    public String getApple() {
        return apple;
    }

    public ProductFormBean setApple(String apple) {
        this.apple = apple;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ProductFormBean{");
        sb.append("title='").append(title).append('\'');
        sb.append(", priceFrom='").append(priceFrom).append('\'');
        sb.append(", priceTo='").append(priceTo).append('\'');
        sb.append(", ambientType='").append(ambientType).append('\'');
        sb.append(", protectedType='").append(protectedType).append('\'');
        sb.append(", cheapType='").append(cheapType).append('\'');
        sb.append(", nokia='").append(nokia).append('\'');
        sb.append(", sigma='").append(sigma).append('\'');
        sb.append(", apple='").append(apple).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
