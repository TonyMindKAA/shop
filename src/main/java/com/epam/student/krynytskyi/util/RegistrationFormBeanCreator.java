package com.epam.student.krynytskyi.util;

import javax.servlet.http.HttpServletRequest;

import com.epam.student.krynytskyi.beans.RegistrationFormBean;

public class RegistrationFormBeanCreator {
	public RegistrationFormBean cretate(HttpServletRequest request) {
		RegistrationFormBean formBean = new RegistrationFormBean();
		formBean.setConfirmEmailAddress(request.getParameter("confirmEmailAddress"));
		formBean.setConfirmPassword(request.getParameter("confirmPassword"));
		formBean.setEmailAddress(request.getParameter("emailAddress"));
		formBean.setLastName(request.getParameter("lastName"));
		formBean.setName(request.getParameter("name"));
		formBean.setPassword(request.getParameter("password"));
		formBean.setPhone(request.getParameter("phone"));
		formBean.setCaptcha(request.getParameter("captcha"));
		return formBean;
	}
}
