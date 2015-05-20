package com.epam.student.krynytskyi.entity;

public class DeliveryType {
    private int deliveryTypeId;
    private String deliverType;

    public int getDeliveryTypeId() {
        return deliveryTypeId;
    }

    public DeliveryType setDeliveryTypeId(int deliveryTypeId) {
        this.deliveryTypeId = deliveryTypeId;
        return this;
    }

    public String getDeliverType() {
        return deliverType;
    }

    public DeliveryType setDeliverType(String deliverType) {
        this.deliverType = deliverType;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeliveryType that = (DeliveryType) o;

        if (deliveryTypeId != that.deliveryTypeId) return false;
        return !(deliverType != null ? !deliverType.equals(that.deliverType) : that.deliverType != null);

    }

    @Override
    public int hashCode() {
        int result = deliveryTypeId;
        result = 31 * result + (deliverType != null ? deliverType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DeliveryType{");
        sb.append("deliveryTypeId=").append(deliveryTypeId);
        sb.append(", deliverType=").append(deliverType);
        sb.append('}');
        return sb.toString();
    }
}
