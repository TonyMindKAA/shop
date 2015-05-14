package com.epam.student.krynytskyi.util.bean.creator;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.epam.student.krynytskyi.beans.registration.RegistrationFormBean;

public class RegistrationFormBeanCreator {
	private static final Logger log = Logger.getLogger(RegistrationFormBeanCreator.class);
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
		setAvatar(request, formBean);
		return formBean;
	}

	private void setAvatar(HttpServletRequest request,
			RegistrationFormBean formBean) {
		try {
			formBean.setAvatar(request.getPart("avatar"));
		} catch (IllegalStateException | IOException | ServletException e) {
			formBean.setAvatar(null);
			log.error("Failed to get the avatar");
		}
	}
}
