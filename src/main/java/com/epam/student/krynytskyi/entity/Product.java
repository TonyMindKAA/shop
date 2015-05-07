package com.epam.student.krynytskyi.entity;

public class Product {

	private String id;
	private String name;
	private String disription_ru;
	private String disription_en;
	private String img;
	private ProductType productType;
	private Manufacture manufacture;
	private long price;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDisription_ru() {
		return disription_ru;
	}

	public void setDisription_ru(String disription_ru) {
		this.disription_ru = disription_ru;
	}

	public String getDisription_en() {
		return disription_en;
	}

	public void setDisription_en(String disription_en) {
		this.disription_en = disription_en;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	public Manufacture getManufacture() {
		return manufacture;
	}

	public void setManufacture(Manufacture manufacture) {
		this.manufacture = manufacture;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", disription_ru="
				+ disription_ru + ", disription_en=" + disription_en + ", img="
				+ img + ", productType=" + productType + ", manufacture="
				+ manufacture + ", price=" + price + "]";
	}

}
