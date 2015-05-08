package com.epam.student.krynytskyi.validator.chain.resposibility;

import com.epam.student.krynytskyi.util.ObjectUtil;

public abstract class Validator {
	private Validator next;

	public Validator getNext() {
		return next;
	}

	protected abstract boolean isValid(String string);

	public boolean validate(String string) {
		ObjectUtil.checkNull(string);
		boolean isValid = isValid(string);
		return isValid && (getNext() == null || next.validate(string));
	}

	public Validator setNext(Validator validator) {
		next = validator;
		return validator;
	}

}
