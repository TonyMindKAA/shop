package com.epam.student.krynytskyi.beans;

import java.util.Date;

public class CaptchaBean {
	private String captchaValue;
	private String id;
	private Date createTime = new Date();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCaptchaValue() {
		return captchaValue;
	}

	public void setCaptchaValue(String captchaValue) {
		this.captchaValue = captchaValue;
	}

	public Date getCreateTime() {
		return createTime;
	}
}
