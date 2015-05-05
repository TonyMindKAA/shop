package com.epam.student.krynytskyi.validator.form.time.out.impl;

import java.util.Date;

import com.epam.student.krynytskyi.validator.form.time.out.TimeOutValidator;

public class CaptchaTimeOutValidator implements TimeOutValidator{

	@Override
	public boolean validate(Date date, long timeLimit) {
		if(date == null || timeLimit <= 0)
			return false;
		Date date2 = new Date();
		long result =  date2.getTime() - date.getTime();
		boolean b = result<= timeLimit;
		return b;
	}
}
