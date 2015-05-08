package com.epam.student.krynytskyi.util;

import java.util.UUID;

import com.epam.student.krynytskyi.beans.CaptchaBean;

public class CaptchaBeanGenerator {
	public CaptchaBean generate() {
		CaptchaBean captcha = new CaptchaBean();
		String string = UUID.randomUUID().toString();
		captcha.setId(string);
		string = UUID.randomUUID().toString();
		captcha.setCaptchaValue(string.substring(2, 6));
		return captcha;
	}
}
