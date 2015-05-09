package com.epam.student.krynytskyi.controllers;

import com.epam.student.krynytskyi.beans.CaptchaBean;
import com.epam.student.krynytskyi.beans.RegistrationFormBean;
import com.epam.student.krynytskyi.beans.RegistrationFormReportBean;
import com.epam.student.krynytskyi.beans.ValidateDataRegistrationFormBean;
import com.epam.student.krynytskyi.convertor.RegistrationFormBeanToUserConverter;
import com.epam.student.krynytskyi.entity.User;
import com.epam.student.krynytskyi.provider.CaptchaProvider;
import com.epam.student.krynytskyi.service.UserService;
import com.epam.student.krynytskyi.util.avatar.AvatarWriter;
import com.epam.student.krynytskyi.util.capthca.CaptchaBeanGenerator;
import com.epam.student.krynytskyi.util.bean.creator.RegistrationFormBeanCreator;
import com.epam.student.krynytskyi.util.bean.creator.ValidateDataRegistrationFormCreator;
import com.epam.student.krynytskyi.validator.form.FullRegistrationFormValidator;
import com.epam.student.krynytskyi.validator.form.FullRegistrationFormValidatorImpl;
import com.epam.student.krynytskyi.validator.report.RegistrationFormValidationReport;
import com.epam.student.krynytskyi.validator.report.RegistrationFormValidationReportImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
    private CaptchaProvider captchaProvider;
    private AvatarWriter avatarWriter = new AvatarWriter();
    private RegistrationFormValidationReport reportObject = new RegistrationFormValidationReportImpl();
    private RegistrationFormBeanToUserConverter converter = new RegistrationFormBeanToUserConverter();
    private FullRegistrationFormValidator validator = new FullRegistrationFormValidatorImpl();
    private RegistrationFormBeanCreator formBeanCreator = new RegistrationFormBeanCreator();
    private ValidateDataRegistrationFormCreator dateRegistrationFormCreator = new ValidateDataRegistrationFormCreator();

    @Override
    public void init() throws ServletException {
        captchaProvider = (CaptchaProvider) getServletContext().getAttribute(CAPTCHA_PROVIDER);
        userService = (UserService) getServletContext().getAttribute(USER_SERVICE);
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CaptchaBean captcha = new CaptchaBeanGenerator().generate();
        captchaProvider.setCaptcha(request, response, captcha);
        request.getRequestDispatcher(REGISTRATION_PAGE).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ValidateDataRegistrationFormBean validateDateRegistrationForm = createValidateDataRegistrationForm(request);
        if (validator.validate(validateDateRegistrationForm)) {
            if (RegisterUser(request, validateDateRegistrationForm.getFormBean()))
                response.sendRedirect(LOGIN_SERVLET);
            else
                forwardBackWithReport(request, response, validateDateRegistrationForm.getFormBean());
        } else
            forwardBackWithReport(request, response, validateDateRegistrationForm.getFormBean());
    }


    private ValidateDataRegistrationFormBean createValidateDataRegistrationForm(
            HttpServletRequest request) {
        RegistrationFormBean formBean = formBeanCreator.cretate(request);
        return dateRegistrationFormCreator.create(formBean, request);
    }

    private boolean RegisterUser(HttpServletRequest request,
                                 RegistrationFormBean formBean) {
        try {
            String avatarName = avatarWriter.writeAvatar(formBean, request);
            User user = converter.convert(formBean);
            user.setImg(avatarName);
            return userService.insertUser(user);
        } catch (Exception e) {
            log.trace("Can not insert new user to db.");
            return false;
        }
    }

    private void forwardBackWithReport(HttpServletRequest request,
                                       HttpServletResponse response, RegistrationFormBean formBean)
            throws ServletException, IOException {
        RegistrationFormReportBean report = reportObject.generate(formBean);
        request.getSession().getServletContext().setAttribute("registrationFormReport", report);
        response.sendRedirect("registration");
    }
}
