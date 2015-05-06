package com.epam.student.krynytskyi.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.epam.student.krynytskyi.beans.RegistrationFormBean;
import com.epam.student.krynytskyi.beans.RegistrationFormReportBean;
import com.epam.student.krynytskyi.beans.ValidateDataRegistrationForm;
import com.epam.student.krynytskyi.convertor.RegistrationFormBeanToUserConvertor;
import com.epam.student.krynytskyi.entity.User;
import com.epam.student.krynytskyi.provider.CaptchaProvider;
import com.epam.student.krynytskyi.service.UserService;
import com.epam.student.krynytskyi.util.RegistrationFormBeanCreator;
import com.epam.student.krynytskyi.util.ValidateDataRegistrationFormCreator;
import com.epam.student.krynytskyi.validator.form.FullRegistrationFormValidator;
import com.epam.student.krynytskyi.validator.report.RegistrationFormValidationReport;

public class RegistrationPageControllerTest {

	@InjectMocks
	private RegistrationPageController controller;

	@Mock
	private RegistrationFormValidationReport reportObject;

	@Mock
	private CaptchaProvider capthcaPrvider;

	@Mock
	private UserService userServiceImpl;

	@Mock
	private FullRegistrationFormValidator validator;

	@Mock
	private HttpServletRequest request;

	@Mock
	private HttpServletResponse response;

	@Mock
	private ServletContext servletContext;

	@Mock
	private HttpSession session;

	@Mock
	private RequestDispatcher requestDispatcher;
	
	@Mock
	private RegistrationFormBeanCreator formBeanCreator;
	
	@Mock
	private ValidateDataRegistrationFormCreator dateRegistrationFormCreator;
	
	@Mock
	private RegistrationFormBeanToUserConvertor convertor;

	@Before
	public void setUp() throws Exception {
		controller = new RegistrationPageController();
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testShouldInvokeAddMethodFromUserServiceWhenValidMethodFromFullRegistrationFormValidatorReturnTrue()
			throws Exception {
		User user = new User();
		RegistrationFormBean formBean = new RegistrationFormBean();
		ValidateDataRegistrationForm validateDataRegistrationForm= new ValidateDataRegistrationForm();
		validateDataRegistrationForm.setFormBean(formBean);

		Mockito.when(formBeanCreator.cretate(request)).thenReturn(formBean);
		Mockito.when(dateRegistrationFormCreator.create(formBean, request)).thenReturn(validateDataRegistrationForm);
		Mockito.when(validator.validate(validateDataRegistrationForm)).thenReturn(true);
		Mockito.when(convertor.convert(formBean)).thenReturn(user);
		Mockito.when(userServiceImpl.insertUser(user)).thenReturn(true);

		controller.doPost(request, response);

		Mockito.verify(validator).validate(validateDataRegistrationForm);
		Mockito.verify(userServiceImpl).insertUser(user);
		Mockito.verify(response).sendRedirect("main.jsp");
	}

	@Test
	public void testShouldInvokeGenerateRaportObjectWhenValidMethodFromFullRegistrationFormValidatorReturnFalse()
			throws ServletException, IOException {
		RegistrationFormReportBean report = new RegistrationFormReportBean();
		RegistrationFormBean formBean = new RegistrationFormBean();
		ValidateDataRegistrationForm validateDataRegistrationForm= new ValidateDataRegistrationForm();
		validateDataRegistrationForm.setFormBean(formBean);

		Mockito.when(formBeanCreator.cretate(request)).thenReturn(formBean);
		Mockito.when(dateRegistrationFormCreator.create(formBean, request)).thenReturn(validateDataRegistrationForm);
		Mockito.when(validator.validate(validateDataRegistrationForm)).thenReturn(false);
		Mockito.when(reportObject.generate(formBean)).thenReturn(report);
		Mockito.when(request.getSession()).thenReturn(session);
		Mockito.when(session.getServletContext()).thenReturn(servletContext);

		controller.doPost(request, response);

		Mockito.verify(validator).validate(validateDataRegistrationForm);
		Mockito.verify(reportObject).generate(formBean);
		Mockito.verify(response).sendRedirect("registration");
	}

}
