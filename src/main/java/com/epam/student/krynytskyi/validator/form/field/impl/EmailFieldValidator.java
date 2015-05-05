package com.epam.student.krynytskyi.validator.form.field.impl;

import static com.epam.student.krynytskyi.validator.patterns.RegistrationFormValidationPatterns.MAIL_PATTERN;

import com.epam.student.krynytskyi.validator.chain.resposibility.EmptyValidator;
import com.epam.student.krynytskyi.validator.chain.resposibility.PatternValidator;
import com.epam.student.krynytskyi.validator.form.field.FieldValidator;

public class EmailFieldValidator implements FieldValidator {
	private EmptyValidator validator;

	public EmailFieldValidator() {
		validator = new EmptyValidator();
		validator.setNext(new PatternValidator(MAIL_PATTERN));
	}

	@Override
	public boolean validate(String string) {
		return validator.validate(string);
	}

}
