package com.epam.student.krynytskyi.provider.inner.storege;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.student.krynytskyi.beans.CapthcaBean;
import com.epam.student.krynytskyi.containers.CapthcaBeanContainer;

public  class CaptchaCookieProvider extends AbstractCaptchaInnerStoregeProvider {

	private static final int ONE_DAY = 60 * 60 * 24;

	@Override
	public CapthcaBean getCaptcha(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		String captcha = null;
		for (Cookie cookie : cookies) {
			if ("captcha".equals(cookie.getName())) {
				captcha = cookie.getValue();
			}
		}
		CapthcaBeanContainer capthcaBeanContainer = getCapthcaBeanContainer();
		return capthcaBeanContainer.getById(captcha);
	}

	@Override
	public void setCaptcha(HttpServletRequest request,
			HttpServletResponse response, CapthcaBean captcha) {
		Cookie cookie = new Cookie("captcha", captcha.getId());
		cookie.setMaxAge(ONE_DAY);
		response.addCookie(cookie);
		CapthcaBeanContainer capthcaBeanContainer = getCapthcaBeanContainer();
		capthcaBeanContainer.insert(captcha);
	}

	@Override
	public void cleanOld(long timeOut) {
		getCapthcaBeanContainer().cleanOld(timeOut);
	}
}
