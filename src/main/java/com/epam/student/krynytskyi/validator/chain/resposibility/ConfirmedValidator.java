package com.epam.student.krynytskyi.validator.chain.resposibility;

import com.epam.student.krynytskyi.util.ObjectUtil;

public class ConfirmedValidator extends Validator {

	String field;

	public ConfirmedValidator(String field) {
		ObjectUtil.checkNull(field);
		this.field = field;
	}

	@Override
	protected boolean isValide(String string) {
		return field.equals(string);
	}
}
