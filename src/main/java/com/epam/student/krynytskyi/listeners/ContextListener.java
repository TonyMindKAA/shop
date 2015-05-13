package com.epam.student.krynytskyi.listeners;

import com.epam.student.krynytskyi.beans.ProductFormBean;
import com.epam.student.krynytskyi.containers.handler.CaptchaCleanerThread;
import com.epam.student.krynytskyi.provider.CaptchaProvider;
import com.epam.student.krynytskyi.provider.CaptchaProviderFactory;
import com.epam.student.krynytskyi.provider.inner.storege.CaptchaCookieProvider;
import com.epam.student.krynytskyi.provider.inner.storege.CaptchaHiddenProvider;
import com.epam.student.krynytskyi.service.UserServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

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
		setToContextDefaultSettingOfFindProducts(servletContext);
	}

	private void setToContextDefaultSettingOfFindProducts(ServletContext servletContext) {
		ProductFormBean productFormBean = new ProductFormBean();
		productFormBean.setOrder(servletContext.getInitParameter("productOrder"));
		productFormBean.setNumberItems(servletContext.getInitParameter("productResultView"));
		productFormBean.setCurrentPage(servletContext.getInitParameter("productCurrentPage"));
		servletContext.setAttribute("productFormBean", productFormBean);
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
		servletContext.setAttribute("userService", new UserServiceImpl());
	}

	private String getCapchaProviderItem(ServletContext servletContext) {
		String captchaProviderItem = servletContext.getInitParameter("captchaProvider");
		if (captchaProviderItem == null) {
			return "hidden";
		}
		return captchaProviderItem;
	}
}
