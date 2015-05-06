package com.epam.student.krynytskyi.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.student.krynytskyi.beans.CapthcaBean;
import com.epam.student.krynytskyi.beans.RegistrationFormBean;
import com.epam.student.krynytskyi.beans.RegistrationFormReportBean;
import com.epam.student.krynytskyi.beans.ValidateDataRegistrationForm;
import com.epam.student.krynytskyi.convertor.RegistrationFormBeanToUserConvertor;
import com.epam.student.krynytskyi.entity.User;
import com.epam.student.krynytskyi.provider.CaptchaProvider;
import com.epam.student.krynytskyi.service.UserService;
import com.epam.student.krynytskyi.util.AvatarWriter;
import com.epam.student.krynytskyi.util.CaptchaBeanGenerator;
import com.epam.student.krynytskyi.util.RegistrationFormBeanCreator;
import com.epam.student.krynytskyi.util.ValidateDataRegistrationFormCreator;
import com.epam.student.krynytskyi.validator.form.FullRegistrationFormValidator;
import com.epam.student.krynytskyi.validator.form.FullRegistrationFormValidatorImpl;
import com.epam.student.krynytskyi.validator.report.RegistrationFormValidationReport;
import com.epam.student.krynytskyi.validator.report.RegistrationFormValidationReportImpl;

@WebServlet("/registration")
@MultipartConfig
public class RegistrationPageController extends HttpServlet {
	private static final Logger log = Logger.getLogger(RegistrationPageController.class);
	private static final String REGISTRATION_PAGE = "/WEB-INF/pages/registration.jsp";
	private static final String LOGIN_SERVLET = "login";
	private static final String USER_SERVICE = "userService";
	private static final String CAPTCHA_PROVIDER = "captchaProvider";
	private static final long serialVersionUID = 1L;
	private UserService userService;
	private CaptchaProvider capthcaPrvider;
	private AvatarWriter avatarWriter = new AvatarWriter();
	private RegistrationFormValidationReport reportObject = new RegistrationFormValidationReportImpl();
	private RegistrationFormBeanToUserConvertor convertor = new RegistrationFormBeanToUserConvertor();
	private FullRegistrationFormValidator validator = new FullRegistrationFormValidatorImpl();
	private RegistrationFormBeanCreator formBeanCreator = new RegistrationFormBeanCreator();
	private ValidateDataRegistrationFormCreator dateRegistrationFormCreator = new ValidateDataRegistrationFormCreator();
	
	@Override
	public void init() throws ServletException {
		capthcaPrvider =  (CaptchaProvider) getServletContext().getAttribute(CAPTCHA_PROVIDER);
		userService = (UserService)getServletContext().getAttribute(USER_SERVICE);
	}
	

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CapthcaBean captcha = new CaptchaBeanGenerator().generate();
		capthcaPrvider.setCaptcha(request, response,captcha);
		request.getRequestDispatcher(REGISTRATION_PAGE).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ValidateDataRegistrationForm validateDateRegistrationForm = createValidateDataRegistrationForm(request);
		if(validator.validate(validateDateRegistrationForm)){
			if(RegistrateUser(request, validateDateRegistrationForm.getFormBean()))
				response.sendRedirect(LOGIN_SERVLET);
			else
				forwarBackWithReport(request,response,validateDateRegistrationForm.getFormBean());
		}else
			forwarBackWithReport(request, response, validateDateRegistrationForm.getFormBean());
	}


	private ValidateDataRegistrationForm createValidateDataRegistrationForm(
			HttpServletRequest request) {
		RegistrationFormBean formBean = formBeanCreator.cretate(request);
		return dateRegistrationFormCreator.create(formBean, request);
	}

	private boolean RegistrateUser(HttpServletRequest request,
			RegistrationFormBean formBean) {
		try {
			String avatarName = avatarWriter.writeAvatar(formBean, request);
			User user = convertor.convert(formBean);
			user.setImg(avatarName);
			 return userService.insertUser(user);
		} catch (Exception e) {
			log.trace("Can not insert new user to db.");
			return false;
		}
	}

	private void forwarBackWithReport(HttpServletRequest request,
			HttpServletResponse response, RegistrationFormBean formBean)
			throws ServletException, IOException {
		RegistrationFormReportBean report = reportObject.generate(formBean);
		request.getSession().getServletContext().setAttribute("registrationFormReport",report);
		response.sendRedirect("registration");
	}
}
