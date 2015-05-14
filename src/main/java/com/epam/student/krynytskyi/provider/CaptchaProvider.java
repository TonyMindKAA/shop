package com.epam.student.krynytskyi.provider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.student.krynytskyi.beans.captcha.CaptchaBean;

public interface CaptchaProvider {
	CaptchaBean getCaptcha(HttpServletRequest request);
	void setCaptcha(HttpServletRequest request, HttpServletResponse response, CaptchaBean captcha);
	void cleanOld(long timeOut);
}
