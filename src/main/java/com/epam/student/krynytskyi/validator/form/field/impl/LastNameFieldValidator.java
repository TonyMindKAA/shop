package com.epam.student.krynytskyi.validator.form.field.impl;

import static com.epam.student.krynytskyi.validator.patterns.RegistrationFormValidationPatterns.LAST_NAME_PATTER;

import com.epam.student.krynytskyi.validator.chain.resposibility.EmptyValidator;
import com.epam.student.krynytskyi.validator.chain.resposibility.PatternValidator;
import com.epam.student.krynytskyi.validator.form.field.FieldValidator;

public class LastNameFieldValidator implements FieldValidator {
	private EmptyValidator emptyValidator;

	public LastNameFieldValidator() {
		emptyValidator = new EmptyValidator();
		emptyValidator.setNext(new PatternValidator(LAST_NAME_PATTER));
	}

	@Override
	public boolean validate(String string) {
		return emptyValidator.validate(string);
	}

}
