package com.epam.student.krynytskyi.util;

import java.util.UUID;

import com.epam.student.krynytskyi.beans.CapthcaBean;

public class CaptchaBeanGenerator {
	public CapthcaBean generate() {
		CapthcaBean captcha = new CapthcaBean();
		String string = UUID.randomUUID().toString();
		captcha.setId(string);
		string = UUID.randomUUID().toString();
		captcha.setCaptchaValue(string.substring(2, 6));
		return captcha;
	}
}
