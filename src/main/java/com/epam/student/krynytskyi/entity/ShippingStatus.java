package com.epam.student.krynytskyi.entity;

public class ShippingStatus {
    private int shippingStatusId;
    private String shippingStatus;

    public int getShippingStatusId() {
        return shippingStatusId;
    }

    public ShippingStatus setShippingStatusId(int shippingStatusId) {
        this.shippingStatusId = shippingStatusId;
        return this;
    }

    public String getShippingStatus() {
        return shippingStatus;
    }

    public ShippingStatus setShippingStatus(String shippingStatus) {
        this.shippingStatus = shippingStatus;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShippingStatus that = (ShippingStatus) o;

        if (shippingStatusId != that.shippingStatusId) return false;
        return !(shippingStatus != null ? !shippingStatus.equals(that.shippingStatus) : that.shippingStatus != null);

    }

    @Override
    public int hashCode() {
        int result = shippingStatusId;
        result = 31 * result + (shippingStatus != null ? shippingStatus.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ShippingStatus{");
        sb.append("shippingStatusId=").append(shippingStatusId);
        sb.append(", shippingStatus='").append(shippingStatus).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
