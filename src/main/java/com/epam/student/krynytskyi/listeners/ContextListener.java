package com.epam.student.krynytskyi.listeners;

import com.epam.student.krynytskyi.containers.handler.CaptchaCleanerThread;
import com.epam.student.krynytskyi.provider.CaptchaProvider;
import com.epam.student.krynytskyi.provider.CaptchaProviderFactory;
import com.epam.student.krynytskyi.provider.inner.storege.CaptchaCookieProvider;
import com.epam.student.krynytskyi.provider.inner.storege.CaptchaHiddenProvider;
import com.epam.student.krynytskyi.service.ProductService;
import com.epam.student.krynytskyi.service.impl.ManufactureServiceImpl;
import com.epam.student.krynytskyi.service.impl.ProductServiceImpl;
import com.epam.student.krynytskyi.service.impl.TypeServiceImpl;
import com.epam.student.krynytskyi.service.impl.UserServiceImpl;
import com.epam.student.krynytskyi.xml.SecurityUnMarshaling;
import com.epam.student.krynytskyi.xml.model.Security;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.xml.bind.JAXBException;

public class ContextListener implements ServletContextListener {
	private static final Logger log = Logger.getLogger(ContextListener.class);
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
		setToContextProductService(servletContext);
		setToContextTypeService(servletContext);
		setToContextManufactureService(servletContext);
		setToContextUserBanSettings(servletContext);
		setToContextSecuritySettings(servletContext);
	}

	private void setToContextSecuritySettings(ServletContext servletContext) {
		SecurityUnMarshaling unMarshaling = new SecurityUnMarshaling();
		String url = servletContext.getInitParameter("securityXmlPath");
		try {
			Security security = unMarshaling.unMarshal(url);
			servletContext.setAttribute("security",security);
		} catch (JAXBException e) {
			log.error("Can not read security.xml. " + e.getMessage());
		}
	}

	private void setToContextUserBanSettings(ServletContext servletContext) {
		String errorEntry = servletContext.getInitParameter("errorEntry");
		String banTime = servletContext.getInitParameter("banTime");
		servletContext.setAttribute("errorEntry",Integer.parseInt(errorEntry));
		servletContext.setAttribute("banTime", Integer.parseInt(banTime));
	}

	private void setToContextManufactureService(ServletContext servletContext) {
		servletContext.setAttribute("manufactureService", new ManufactureServiceImpl());
	}

	private void setToContextTypeService(ServletContext servletContext) {
		servletContext.setAttribute("typeService", new TypeServiceImpl());
	}

	private void setToContextProductService(ServletContext servletContext) {
		ProductService productService = new ProductServiceImpl();
		servletContext.setAttribute("productService",productService);
	}

	private void setToContextDefaultSettingOfFindProducts(ServletContext servletContext) {
		String productOrder = servletContext.getInitParameter("productOrder");
		String productResultView = servletContext.getInitParameter("productResultView");
		String productCurrentPage = servletContext.getInitParameter("productCurrentPage");
		servletContext.setAttribute("productOrder", productOrder);
		servletContext.setAttribute("productResultView", productResultView);
		servletContext.setAttribute("productCurrentPage", productCurrentPage );
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
		String captchaProviderItem = getCaptchaProviderItem(servletContext);
		CaptchaProvider provider = new CaptchaProviderFactory().getProvider(captchaProviderItem);
		servletContext.setAttribute("captchaProvider", provider);
		setCaptchaCleanerThread(provider);
	}

	private void setCaptchaCleanerThread(CaptchaProvider provider) {
		if(provider instanceof CaptchaHiddenProvider || provider instanceof CaptchaCookieProvider){
			captchaCleanerThread = new CaptchaCleanerThread(provider, timeOut, timeVerification);
		}
	}

	private void setToContextUserService(ServletContext servletContext) {
		servletContext.setAttribute("userService", new UserServiceImpl());
	}

	private String getCaptchaProviderItem(ServletContext servletContext) {
		String captchaProviderItem = servletContext.getInitParameter("captchaProvider");
		if (captchaProviderItem == null) {
			return "hidden";
		}
		return captchaProviderItem;
	}
}
