package com.epam.student.krynytskyi.util.bean.creator;

import com.epam.student.krynytskyi.beans.registration.RegistrationFormBean;
import com.epam.student.krynytskyi.beans.registration.ValidateDataRegistrationFormBean;
import com.epam.student.krynytskyi.provider.CaptchaProvider;

import javax.servlet.http.HttpServletRequest;

public class ValidateDataRegistrationFormCreator {

	public ValidateDataRegistrationFormBean create(RegistrationFormBean formBean, HttpServletRequest request) {
		ValidateDataRegistrationFormBean dateRegistrationForm = new ValidateDataRegistrationFormBean();
		dateRegistrationForm.setFormBean(formBean);
		CaptchaProvider capthcaPrvider = (CaptchaProvider) request.getServletContext().getAttribute("captchaProvider");
		dateRegistrationForm.setCapthcaBean(capthcaPrvider.getCaptcha(request));
		Long timeOut = (Long) request.getServletContext().getAttribute("captchaTimeOut");
		dateRegistrationForm.setTimeOut(timeOut);
		return dateRegistrationForm;
	}
}
