package com.epam.student.krynytskyi.util;

import com.epam.student.krynytskyi.beans.RegistrationFormBean;
import com.epam.student.krynytskyi.beans.ValidateDataRegistrationForm;
import com.epam.student.krynytskyi.provider.CaptchaProvider;

import javax.servlet.http.HttpServletRequest;

public class ValidateDataRegistrationFormCreator {

	public ValidateDataRegistrationForm create(RegistrationFormBean formBean, HttpServletRequest request) {
		ValidateDataRegistrationForm dateRegistrationForm = new ValidateDataRegistrationForm();
		dateRegistrationForm.setFormBean(formBean);
		CaptchaProvider capthcaPrvider = (CaptchaProvider) request.getServletContext().getAttribute("captchaProvider");
		dateRegistrationForm.setCapthcaBean(capthcaPrvider.getCaptcha(request));
		Long timeOut = (Long) request.getServletContext().getAttribute("captchaTimeOut");
		dateRegistrationForm.setTimeOut(timeOut);
		return dateRegistrationForm;
	}
}
