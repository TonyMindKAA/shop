package com.epam.student.krynytskyi.validator.form.time.out;

import java.util.Date;

public interface TimeOutValidator {
	boolean validate(Date date, long timeLimit);
}
