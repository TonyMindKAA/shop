package com.epam.student.krynytskyi.controllers;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;

import com.epam.student.krynytskyi.beans.CapthcaBean;
import com.epam.student.krynytskyi.beans.RegistrationFormBean;
import com.epam.student.krynytskyi.beans.RegistrationFormReportBean;
import com.epam.student.krynytskyi.beans.ValidateDataRegistrationForm;
import com.epam.student.krynytskyi.convertor.RegistrationFormBeanToUserConvertor;
import com.epam.student.krynytskyi.entity.User;
import com.epam.student.krynytskyi.provider.CaptchaProvider;
import com.epam.student.krynytskyi.service.UserService;
import com.epam.student.krynytskyi.util.RegistrationFormBeanCreator;
import com.epam.student.krynytskyi.util.Sha1HexGenerator;
import com.epam.student.krynytskyi.util.ValidateDataRegistrationFormCreator;
import com.epam.student.krynytskyi.validator.form.FullRegistrationFormValidator;
import com.epam.student.krynytskyi.validator.form.FullRegistrationFormValidatorImpl;
import com.epam.student.krynytskyi.validator.report.RegistrationFormValidationReport;
import com.epam.student.krynytskyi.validator.report.RegistrationFormValidationReportImpl;

@WebServlet("/registration")
@MultipartConfig
public class RegistrationPageController extends HttpServlet {
	private static final String DEFALUT_AVATAR_NAME = "default.jpg";
	private static final Logger log = Logger.getLogger(RegistrationPageController.class);
	private static final String REGISTRATION_PAGE = "/WEB-INF/pages/registration.jsp";
	private static final String LOGIN_SERVLET = "login";
	private static final String USER_SERVICE = "userService";
	private static final String CAPTCHA_PROVIDER = "captchaProvider";
	private static final long serialVersionUID = 1L;
	private UserService userService;
	private CaptchaProvider capthcaPrvider;
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
			log.debug("Validation: ok part one");
			if(RegistrateUser(request, validateDateRegistrationForm.getFormBean())){
				log.debug("Validation: ok part one part two ok");
				response.sendRedirect(LOGIN_SERVLET);
				}
			else{
				log.debug("Validation: ok part one part two false");
				forwarBackWithReport(request,response,validateDateRegistrationForm.getFormBean());
			}
		}else{
			log.debug("Validation: false part one");
			forwarBackWithReport(request, response, validateDateRegistrationForm.getFormBean());
		}
	}


	private ValidateDataRegistrationForm createValidateDataRegistrationForm(
			HttpServletRequest request) {
		RegistrationFormBean formBean = formBeanCreator.cretate(request);
		return dateRegistrationFormCreator.create(formBean, request);
	}

	private boolean RegistrateUser(HttpServletRequest request,
			RegistrationFormBean formBean) {
		try {
			String avatarName = writeAvatar(formBean, request);
			User user = convertor.convert(formBean);
			user.setImg(avatarName);
			 return userService.insertUser(user);
		} catch (Exception e) {
			log.trace("Can not insert new user to db.");
			return false;
		}
	}


	private String writeAvatar(RegistrationFormBean formBean, HttpServletRequest request) throws IOException{
		Part avatar = formBean.getAvatar();
		String avatarName = generateAvatarName(formBean);
		String realPath = request.getServletContext().getRealPath("/")+"resources\\avatars\\";
		if(!avatarName.equals(DEFALUT_AVATAR_NAME)) {
			chackFolder(realPath);
		}
			avatar.write(realPath+avatarName);
		return avatarName;
	}

	
	private void chackFolder(String realPath) {
		File file = new File(realPath);
		log.debug("file::::-> "+file.isDirectory());
		if(!file.isDirectory()){
			file.mkdir();
			log.debug("create new folder");
		}
	}


	private String generateAvatarName(RegistrationFormBean formBean) {
		String imgEnding = getAvaterEnding(formBean.getAvatar());
		Sha1HexGenerator hexGenerator = new Sha1HexGenerator();
		try {
			if(imgEnding.equals(""))
				return DEFALUT_AVATAR_NAME;
			else
				return hexGenerator.makeSHA1Hash(formBean.getEmailAddress())+"."+imgEnding;
		} catch (NoSuchAlgorithmException e) {
			return formBean.getEmailAddress().hashCode()+"."+imgEnding;
		}
	}


	private String getAvaterEnding(Part part) {
		for (String cd : part.getHeader("content-disposition").split(";")) {
			   if (cd.trim().startsWith("filename")) {
			    String fileName = cd.substring(cd.indexOf('=') + 1).trim()
			      .replace("\"", "");
			    return fileName.substring(fileName.lastIndexOf('.')+1);
			   }
			  }
		return "";
	}
	
	private void forwarBackWithReport(HttpServletRequest request,
			HttpServletResponse response, RegistrationFormBean formBean)
			throws ServletException, IOException {
		RegistrationFormReportBean report = reportObject.generate(formBean);
		log.debug("Validation: false part one---->"+report);
		request.getSession().getServletContext().setAttribute("registrationFormReport",report);
		response.sendRedirect("registration");
	}
}
