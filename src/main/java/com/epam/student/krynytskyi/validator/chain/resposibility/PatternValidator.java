package com.epam.student.krynytskyi.validator.chain.resposibility;

import com.epam.student.krynytskyi.util.ObjectUtil;

public class PatternValidator extends Validator {
	String pattern;

	public PatternValidator(String pattern) {
		ObjectUtil.checkNull(pattern);
		this.pattern = pattern;
	}

	@Override
	protected boolean isValid(String string) {
		return string.matches(pattern);
	}

}
