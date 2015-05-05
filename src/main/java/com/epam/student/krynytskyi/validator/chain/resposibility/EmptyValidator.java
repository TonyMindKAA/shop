package com.epam.student.krynytskyi.validator.chain.resposibility;


public class EmptyValidator extends Validator {

	@Override
	protected boolean isValide(String string) {
		return  !string.isEmpty();
	}
}
