package com.epam.student.krynytskyi.beans;

public class RegistrationFormReportBean {
	private RegistrationFormBean registrationFormBean;
	private boolean isNameValid;
	private boolean isLastNameValid;
	private boolean isEmailAddressValid;
	private boolean isConfirmEmailAddressValid;
	private boolean isPasswordValid;
	private boolean isConfirmPasswordValid;
	private boolean isPhoneValid;
	private boolean isCapchaValid;
	private boolean isValid;

	public RegistrationFormBean getRegistrationFormBean() {
		return registrationFormBean;
	}

	public void setRegistrationFormBean(
			RegistrationFormBean registrationFormBean) {
		this.registrationFormBean = registrationFormBean;
	}

	public boolean getNameValid() {
		return isNameValid;
	}

	public void setNameValid(boolean isNameValid) {
		setValid(isNameValid);
		this.isNameValid = isNameValid;
	}

	public boolean getLastNameValid() {
		return isLastNameValid;
	}

	public void setLastNameValid(boolean isLastNameValid) {
		setValid(isLastNameValid);
		this.isLastNameValid = isLastNameValid;
	}

	public boolean getEmailAddressValid() {
		return isEmailAddressValid;
	}

	public void setEmailAddressValid(boolean isEmailAddressValid) {
		setValid(isEmailAddressValid);
		this.isEmailAddressValid = isEmailAddressValid;
	}

	public boolean getConfirmEmailAddressValid() {
		return isConfirmEmailAddressValid;
	}

	public void setConfirmEmailAddressValid(boolean isConfirmEmailAddressValid) {
		setValid(isConfirmEmailAddressValid);
		this.isConfirmEmailAddressValid = isConfirmEmailAddressValid;
	}

	public boolean getPasswordValid() {
		return isPasswordValid;
	}

	public void setPasswordValid(boolean isPasswordValid) {
		setValid(isPasswordValid);
		this.isPasswordValid = isPasswordValid;
	}

	public boolean getConfirmPasswordValid() {
		return isConfirmPasswordValid;
	}

	public void setConfirmPasswordValid(boolean isConfirmPasswordValid) {
		setValid(isConfirmPasswordValid);
		this.isConfirmPasswordValid = isConfirmPasswordValid;
	}

	public boolean getPhoneValid() {
		return isPhoneValid;
	}

	public void setPhoneValid(boolean isPhoneValid) {
		setValid(isPhoneValid);
		this.isPhoneValid = isPhoneValid;
	}

	public boolean isValid() {
		return isValid;
	}

	public boolean getCapchaValid() {
		return isCapchaValid;
	}

	public void setCapchaValid(boolean isCapchaValid) {
		setValid(isPhoneValid);
		this.isCapchaValid = isCapchaValid;
	}

	private void setValid(boolean value) {
		if (value == false)
			isValid = false;
	}

	@Override
	public String toString() {
		return "RegistrationFormReportBeam [registrationFormBean="
				+ registrationFormBean + ", isNameValid=" + isNameValid
				+ ", isLastNameValid=" + isLastNameValid
				+ ", isEmailAddressValid=" + isEmailAddressValid
				+ ", isConfirmEmailAddressValid=" + isConfirmEmailAddressValid
				+ ", isPasswordValid=" + isPasswordValid
				+ ", isConfirmPasswordValid=" + isConfirmPasswordValid
				+ ", isPhoneValid=" + isPhoneValid + ", isCapchaValid="
				+ isCapchaValid + ", isValid=" + isValid + "]";
	}
}
