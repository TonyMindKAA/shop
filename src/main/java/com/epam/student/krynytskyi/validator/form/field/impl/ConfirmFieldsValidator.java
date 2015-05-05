package com.epam.student.krynytskyi.validator.form.field.impl;

import com.epam.student.krynytskyi.validator.chain.resposibility.ConfirmedValidator;
import com.epam.student.krynytskyi.validator.chain.resposibility.EmptyValidator;
import com.epam.student.krynytskyi.validator.form.field.ConfirmFieldValidator;

public class ConfirmFieldsValidator implements ConfirmFieldValidator {
	private EmptyValidator emptyValidator;

	public ConfirmFieldsValidator() {
		emptyValidator = new EmptyValidator();
	}

	@Override
	public boolean validate(String firstValue, String secondValue) {
		emptyValidator.setNext(new ConfirmedValidator(firstValue));
		return emptyValidator.validate(secondValue);
	}
}
