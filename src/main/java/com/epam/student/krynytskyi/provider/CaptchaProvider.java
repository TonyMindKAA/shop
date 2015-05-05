package com.epam.student.krynytskyi.provider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.student.krynytskyi.beans.CapthcaBean;

public interface CaptchaProvider {
	CapthcaBean getCaptcha(HttpServletRequest request);
	void setCaptcha(HttpServletRequest request, HttpServletResponse response, CapthcaBean captcha);
	void cleanOld(long timeOut);
}
