package com.epam.student.krynytskyi.beans;

public class RegistrationFormBean {
	private String name = "";
	private String lastName = "";
	private String emailAddress = "";
	private String confirmEmailAddress = "";
	private String password = "";
	private String confirmPassword = "";
	private String phone = "";
	private String captcha = "";

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getConfirmEmailAddress() {
		return confirmEmailAddress;
	}

	public void setConfirmEmailAddress(String confirmEmailAddress) {
		this.confirmEmailAddress = confirmEmailAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((captcha == null) ? 0 : captcha.hashCode());
		result = prime
				* result
				+ ((confirmEmailAddress == null) ? 0 : confirmEmailAddress
						.hashCode());
		result = prime * result
				+ ((confirmPassword == null) ? 0 : confirmPassword.hashCode());
		result = prime * result
				+ ((emailAddress == null) ? 0 : emailAddress.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
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
		RegistrationFormBean other = (RegistrationFormBean) obj;
		if (captcha == null) {
			if (other.captcha != null)
				return false;
		} else if (!captcha.equals(other.captcha))
			return false;
		if (confirmEmailAddress == null) {
			if (other.confirmEmailAddress != null)
				return false;
		} else if (!confirmEmailAddress.equals(other.confirmEmailAddress))
			return false;
		if (confirmPassword == null) {
			if (other.confirmPassword != null)
				return false;
		} else if (!confirmPassword.equals(other.confirmPassword))
			return false;
		if (emailAddress == null) {
			if (other.emailAddress != null)
				return false;
		} else if (!emailAddress.equals(other.emailAddress))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RegistrationFormBean [name=" + name + ", lastName=" + lastName
				+ ", emailAddress=" + emailAddress + ", confirmEmailAddress="
				+ confirmEmailAddress + ", password=" + password
				+ ", confirmPassword=" + confirmPassword + ", phone=" + phone
				+ ", captcha=" + captcha + "]";
	}
}
