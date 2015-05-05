package com.epam.student.krynytskyi.validator.form.field.impl;

import static com.epam.student.krynytskyi.validator.patterns.RegistrationFormValidationPatterns.NAME_PATTER;

import com.epam.student.krynytskyi.validator.chain.resposibility.EmptyValidator;
import com.epam.student.krynytskyi.validator.chain.resposibility.PatternValidator;
import com.epam.student.krynytskyi.validator.form.field.FieldValidator;

public class NameFieldValidator implements FieldValidator {
	private EmptyValidator emptyValidator;

	public NameFieldValidator() {
		emptyValidator = new EmptyValidator();
		emptyValidator.setNext(new PatternValidator(NAME_PATTER));
	}

	@Override
	public boolean validate(String string) {
		return emptyValidator.validate(string);
	}

}
