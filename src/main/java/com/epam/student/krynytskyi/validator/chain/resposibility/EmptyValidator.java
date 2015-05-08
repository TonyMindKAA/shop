package com.epam.student.krynytskyi.validator.chain.resposibility;


public class EmptyValidator extends Validator {

	@Override
	protected boolean isValid(String string) {
		return  !string.isEmpty();
	}
}
