package com.epam.student.krynytskyi.validator.form.field.impl;

import static com.epam.student.krynytskyi.validator.patterns.RegistrationFormValidationPatterns.PASWORD_PATTERN;

import com.epam.student.krynytskyi.validator.chain.resposibility.EmptyValidator;
import com.epam.student.krynytskyi.validator.chain.resposibility.PatternValidator;
import com.epam.student.krynytskyi.validator.form.field.FieldValidator;

public class PasswordFieldValidator implements FieldValidator {
	private EmptyValidator emptyValidator;

	public PasswordFieldValidator() {
		emptyValidator = new EmptyValidator();
		emptyValidator.setNext(new PatternValidator(PASWORD_PATTERN));
	}

	@Override
	public boolean validate(String string) {
		return emptyValidator.validate(string);
	}

}
