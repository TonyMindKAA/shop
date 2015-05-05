package com.epam.student.krynytskyi.controllers;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.student.krynytskyi.beans.CapthcaBean;
import com.epam.student.krynytskyi.beans.RegistrationFormBean;
import com.epam.student.krynytskyi.beans.RegistrationFormReportBean;
import com.epam.student.krynytskyi.beans.ValidateDataRegistrationForm;
import com.epam.student.krynytskyi.convertor.RegistrationFormBeanToUserConvertor;
import com.epam.student.krynytskyi.provider.CaptchaProvider;
import com.epam.student.krynytskyi.service.UserService;
import com.epam.student.krynytskyi.util.RegistrationFormBeanCreator;
import com.epam.student.krynytskyi.util.ValidateDataRegistrationFormCreator;
import com.epam.student.krynytskyi.validator.form.FullRegistrationFormValidator;
import com.epam.student.krynytskyi.validator.form.FullRegistrationFormValidatorImpl;
import com.epam.student.krynytskyi.validator.report.RegistrationFormValidationReport;
import com.epam.student.krynytskyi.validator.report.RegistrationFormValidationReportImpl;

@WebServlet("/registration")
public class RegistrationPageController extends HttpServlet {
	private static final String USER_SERVICE = "userService";
	private static final String CAPTCHA_PROVIDER = "captchaProvider";
	private static final String REGISTRATION_PAGE = "/WEB-INF/pages/login.jsp";
	private static final long serialVersionUID = 1L;
	private UserService userServiceImpl ;
	private CaptchaProvider capthcaPrvider;
	private RegistrationFormValidationReport reportObject = new RegistrationFormValidationReportImpl();
	private RegistrationFormBeanToUserConvertor convertor = new RegistrationFormBeanToUserConvertor();
	private FullRegistrationFormValidator validator = new FullRegistrationFormValidatorImpl();
	private RegistrationFormBeanCreator formBeanCreator = new RegistrationFormBeanCreator();
	private ValidateDataRegistrationFormCreator dateRegistrationFormCreator = new ValidateDataRegistrationFormCreator();
	
	@Override
	public void init() throws ServletException {
		capthcaPrvider =  (CaptchaProvider) getServletContext().getAttribute(CAPTCHA_PROVIDER);
		userServiceImpl = (UserService) getServletContext().getAttribute(USER_SERVICE);
	}
	

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CapthcaBean captcha = createCaptchaBean();
		capthcaPrvider.setCaptcha(request, response,captcha);
		request.getRequestDispatcher(REGISTRATION_PAGE).forward(request, response);
	}

	private CapthcaBean createCaptchaBean() {
		CapthcaBean captcha = new CapthcaBean();
		String string = UUID.randomUUID().toString();
		captcha.setId(string);
		string = UUID.randomUUID().toString();
		captcha.setCaptchaValue(string.substring(2, 6));
		return captcha;
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ValidateDataRegistrationForm validateDateRegistrationForm = createValidateDataRegistrationForm(request);
		if(validator.validate(validateDateRegistrationForm)){
			registrateUser(request, validateDateRegistrationForm.getFormBean());
			response.sendRedirect("main.jsp");
		}else{
			forwarBackWithReport(request, response, validateDateRegistrationForm.getFormBean());
		}
	}


	private ValidateDataRegistrationForm createValidateDataRegistrationForm(
			HttpServletRequest request) {
		RegistrationFormBean formBean = formBeanCreator.cretate(request);
		return dateRegistrationFormCreator.create(formBean, request);
	}

	private void registrateUser(HttpServletRequest request,
			RegistrationFormBean formBean) {
		userServiceImpl.add(convertor.convert(formBean));
	}

	private void forwarBackWithReport(HttpServletRequest request,
			HttpServletResponse response, RegistrationFormBean formBean)
			throws ServletException, IOException {
		RegistrationFormReportBean report = reportObject.generate(formBean);
		request.getSession().getServletContext().setAttribute("registrationFormReport",report);
		response.sendRedirect("registration");
	}
}
