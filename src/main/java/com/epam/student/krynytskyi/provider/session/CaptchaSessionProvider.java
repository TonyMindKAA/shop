package com.epam.student.krynytskyi.provider.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.student.krynytskyi.beans.CapthcaBean;
import com.epam.student.krynytskyi.provider.CaptchaProvider;

public class CaptchaSessionProvider implements CaptchaProvider {

	@Override
	public CapthcaBean getCaptcha(HttpServletRequest request) {
		return  (CapthcaBean) request.getSession().getAttribute("captcha");
	}

	@Override
	public void setCaptcha(HttpServletRequest request,
			HttpServletResponse response, CapthcaBean captcha) {
		request.getSession().setAttribute("captcha", captcha);
	}

	@Override
	public void cleanOld(long timeOut) {
		throw new UnsupportedOperationException();
	}
}
