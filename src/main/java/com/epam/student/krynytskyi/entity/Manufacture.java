package com.epam.student.krynytskyi.entity;

public class Manufacture {
	private int id;
	private String manufacture;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getManufacture() {
		return manufacture;
	}

	public void setManufacture(String manufacture) {
		this.manufacture = manufacture;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result
				+ ((manufacture == null) ? 0 : manufacture.hashCode());
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
		Manufacture other = (Manufacture) obj;
		if (id != other.id)
			return false;
		if (manufacture == null) {
			if (other.manufacture != null)
				return false;
		} else if (!manufacture.equals(other.manufacture))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Manufacture [id=" + id + ", manufacture=" + manufacture + "]";
	}

}
