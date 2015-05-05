package com.epam.student.krynytskyi.validator.form.field.impl;

import static com.epam.student.krynytskyi.validator.patterns.RegistrationFormValidationPatterns.PHONE_PATTERN;

import com.epam.student.krynytskyi.validator.chain.resposibility.EmptyValidator;
import com.epam.student.krynytskyi.validator.chain.resposibility.PatternValidator;
import com.epam.student.krynytskyi.validator.form.field.FieldValidator;

public class PhoneFieldValidator implements FieldValidator {

	@Override
	public boolean validate(String string) {
		return new EmptyValidator()
				.setNext(new PatternValidator(PHONE_PATTERN)).validate(string);
	}

}
