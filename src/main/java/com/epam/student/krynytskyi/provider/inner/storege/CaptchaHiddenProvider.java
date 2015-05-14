package com.epam.student.krynytskyi.provider.inner.storege;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.student.krynytskyi.beans.captcha.CaptchaBean;
import com.epam.student.krynytskyi.containers.CapthcaBeanContainer;

public class CaptchaHiddenProvider extends AbstractCaptchaInnerStoregeProvider {
	@Override
	public CaptchaBean getCaptcha(HttpServletRequest request) {
		String captchaId = getCaptchaId(request);
		CapthcaBeanContainer capthcaBeanContainer = getCapthcaBeanContainer();
		captchaId = (captchaId == null) ? "" : captchaId; 
		return capthcaBeanContainer.getById(captchaId);
	}

	private String getCaptchaId(HttpServletRequest request) {
		return (String) request.getParameter("captchaHiden");
	}

	@Override
	public void setCaptcha(HttpServletRequest request,
			HttpServletResponse response, CaptchaBean captcha) {
		CapthcaBeanContainer capthcaBeanContainer = getCapthcaBeanContainer();
		capthcaBeanContainer.insert(captcha);
		request.setAttribute("captchaHiden", captcha.getId());
	}

	@Override
	public void cleanOld(long timeOut) {
		getCapthcaBeanContainer().cleanOld(timeOut);
	}
}
