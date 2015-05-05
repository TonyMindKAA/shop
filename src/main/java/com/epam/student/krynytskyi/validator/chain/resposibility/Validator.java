package com.epam.student.krynytskyi.validator.chain.resposibility;

import com.epam.student.krynytskyi.util.ObjectUtil;

public abstract class Validator {
	private Validator next;
	private boolean isValide = true;

	public Validator getNext() {
		return next;
	}

	protected abstract boolean isValide(String string);

	public boolean validate(String string) {
		ObjectUtil.checkNull(string);
		isValide = isValide(string);
		if (!isValide) {
			return isValide;
		}
		if (getNext() == null) {
			return isValide;
		}
		return next.validate(string);
	}

	public Validator setNext(Validator validator) {
		next = validator;
		return validator;
	}

}
