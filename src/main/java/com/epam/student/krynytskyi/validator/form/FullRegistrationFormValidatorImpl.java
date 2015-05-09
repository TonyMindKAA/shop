package com.epam.student.krynytskyi.validator.form;

import com.epam.student.krynytskyi.beans.CaptchaBean;
import com.epam.student.krynytskyi.beans.RegistrationFormBean;
import com.epam.student.krynytskyi.beans.ValidateDataRegistrationFormBean;
import com.epam.student.krynytskyi.validator.form.field.impl.ConfirmFieldsValidator;
import com.epam.student.krynytskyi.validator.form.field.impl.EmailFieldValidator;
import com.epam.student.krynytskyi.validator.form.field.impl.LastNameFieldValidator;
import com.epam.student.krynytskyi.validator.form.field.impl.NameFieldValidator;
import com.epam.student.krynytskyi.validator.form.field.impl.PasswordFieldValidator;
import com.epam.student.krynytskyi.validator.form.field.impl.PhoneFieldValidator;
import com.epam.student.krynytskyi.validator.form.time.out.impl.CaptchaTimeOutValidator;

public class FullRegistrationFormValidatorImpl implements
		FullRegistrationFormValidator {
	private boolean isValid;

	@Override
	public boolean validate(ValidateDataRegistrationFormBean validateDateRegistrationForm) {
		isValid = true;
		RegistrationFormBean formBean = validateDateRegistrationForm.getFormBean();
		long timeOut = validateDateRegistrationForm.getTimeOut();
		CaptchaBean capthcaBean = validateDateRegistrationForm.getCapthcaBean();
		isValide(new EmailFieldValidator().validate(formBean.getEmailAddress()));
		isValide(new LastNameFieldValidator().validate(formBean.getLastName()));
		isValide(new NameFieldValidator().validate(formBean.getName()));
		isValide(new PasswordFieldValidator().validate(formBean.getPassword()));
		isValide(new PhoneFieldValidator().validate(formBean.getPhone()));
		isValide(new ConfirmFieldsValidator().validate( formBean.getEmailAddress(), formBean.getConfirmEmailAddress()));
		isValide(new ConfirmFieldsValidator().validate(formBean.getPassword(), formBean.getConfirmPassword()));
		isValide(new ConfirmFieldsValidator().validate(capthcaBean.getCaptchaValue(), formBean.getCaptcha()));
		isValide(new CaptchaTimeOutValidator().validate(capthcaBean.getCreateTime(),timeOut));
		return isValid;
	}
	private void isValide(boolean value) {
		if (value == false)
			isValid = false;
	}
}
