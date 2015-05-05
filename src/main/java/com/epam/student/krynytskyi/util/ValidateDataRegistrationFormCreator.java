package com.epam.student.krynytskyi.util;

import javax.servlet.http.HttpServletRequest;

import com.epam.student.krynytskyi.beans.RegistrationFormBean;
import com.epam.student.krynytskyi.beans.ValidateDataRegistrationForm;
import com.epam.student.krynytskyi.provider.CaptchaProvider;

public class ValidateDataRegistrationFormCreator {
	private CaptchaProvider capthcaPrvider;

	public ValidateDataRegistrationForm create(RegistrationFormBean formBean, HttpServletRequest request) {
		ValidateDataRegistrationForm dateRegistrationForm = new ValidateDataRegistrationForm();
		dateRegistrationForm.setFormBean(formBean);
		capthcaPrvider = (CaptchaProvider) request.getServletContext().getAttribute("captchaProvider");
		dateRegistrationForm.setCapthcaBean(capthcaPrvider.getCaptcha(request));
		Long timeOut = (Long) request.getServletContext().getAttribute("captchaTimeOut");
		dateRegistrationForm.setTimeOut(timeOut);
		return dateRegistrationForm;
	}
}
