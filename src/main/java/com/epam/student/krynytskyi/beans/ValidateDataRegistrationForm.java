package com.epam.student.krynytskyi.beans;

public class ValidateDataRegistrationForm {
	private RegistrationFormBean formBean;
	private CaptchaBean capthcaBean;
	private long timeOut;

	public RegistrationFormBean getFormBean() {
		return formBean;
	}

	public void setFormBean(RegistrationFormBean formBean) {
		this.formBean = formBean;
	}

	public CaptchaBean getCapthcaBean() {
		return capthcaBean;
	}

	public void setCapthcaBean(CaptchaBean capthcaBean) {
		this.capthcaBean = capthcaBean;
	}

	public long getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(long timeOut) {
		this.timeOut = timeOut;
	}

	@Override
	public String toString() {
		return "ValidateDateRegistrationForm [formBean=" + formBean
				+ ", capthcaBean=" + capthcaBean + ", timeOut=" + timeOut + "]";
	}

}
