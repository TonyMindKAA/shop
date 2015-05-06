package com.epam.student.krynytskyi.listeners;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.epam.student.krynytskyi.containers.handler.CaptchaCleanerThread;
import com.epam.student.krynytskyi.db.dao.UserLocalDaoImpl;
import com.epam.student.krynytskyi.entity.User;
import com.epam.student.krynytskyi.provider.CaptchaProvider;
import com.epam.student.krynytskyi.provider.CaptchaProviderFactory;
import com.epam.student.krynytskyi.provider.inner.storege.CaptchaCookieProvider;
import com.epam.student.krynytskyi.provider.inner.storege.CaptchaHiddenProvider;
import com.epam.student.krynytskyi.service.StaticUserServiceImpl;

public class ContextListener implements ServletContextListener {

	private static final Long DEFAULT_CAPTCHA_TIME_OUT = new Long("60000");
	private static final Object DEFAULT_CAPTCHA_TIME_VERIFICATION = new Long("60000");
	private CaptchaCleanerThread captchaCleanerThread;
	private Long timeOut;
	private Long timeVerification;

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		if(captchaCleanerThread != null)
			captchaCleanerThread.shutDown();
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		ServletContext servletContext = arg0.getServletContext();
		setToContextCaptchaTimeOut(servletContext);
		setToContextUserService(servletContext);
		setToContextTimeVerification(servletContext);
		setToContextProvider(servletContext);
	}

	private void setToContextTimeVerification(ServletContext servletContext) {
		String timeVerificationParam= servletContext.getInitParameter("timeVerification");
		if(isNumber(timeVerificationParam)){
			timeVerification = new Long(timeVerificationParam) * 1000;
			servletContext.setAttribute("timeVerification", timeVerification);
			}
		else
			servletContext.setAttribute("timeVerification",DEFAULT_CAPTCHA_TIME_VERIFICATION);
		
	}

	private void setToContextCaptchaTimeOut(ServletContext servletContext) {
		String captchaTimeOut= servletContext.getInitParameter("captchaTimeOut");
		if(isNumber(captchaTimeOut)){
			timeOut = new Long(captchaTimeOut) * 1000;
			servletContext.setAttribute("captchaTimeOut", timeOut);
			}
		else
			servletContext.setAttribute("captchaTimeOut",DEFAULT_CAPTCHA_TIME_OUT);
	}

	private boolean isNumber(String captchaTimeOut) {
		try {
			Long.parseLong(captchaTimeOut);
			return true;
		} catch (NumberFormatException e) {
			return false;
		} 
	}

	private void setToContextProvider(ServletContext servletContext) {
		String captchaProviderItem = getCapchaProviderItem(servletContext);
		CaptchaProvider provider = new CaptchaProviderFactory().getProvider(captchaProviderItem);
		servletContext.setAttribute("captchaProvider", provider);
		setCptchaClenerThread(provider);
	}

	private void setCptchaClenerThread(CaptchaProvider provider) {
		if(provider instanceof CaptchaHiddenProvider || provider instanceof CaptchaCookieProvider){
			captchaCleanerThread = new CaptchaCleanerThread(provider, timeOut, timeVerification);
		}
	}

	private void setToContextUserService(ServletContext servletContext) {
		List<User> users = new ArrayList<User>() {
			private static final long serialVersionUID = 1L;
			{
				User user = new User();
				user.setEmail("kaa@mail.ru");
				user.setName("tony");
				user.setPassword("kaapro");
				add(user);
				user = new User();
				user.setEmail("bob@mail.ru");
				user.setName("bob");
				user.setPassword("bobpro");
				add(user);
			}
		};
		servletContext.setAttribute("userService", new StaticUserServiceImpl(
				new UserLocalDaoImpl(users)));
	}

	private String getCapchaProviderItem(ServletContext servletContext) {
		String captchaProviderItem = servletContext.getInitParameter("captchaProvider");
		if (captchaProviderItem == null) {
			return "hidden";
		}
		return captchaProviderItem;
	}
}
