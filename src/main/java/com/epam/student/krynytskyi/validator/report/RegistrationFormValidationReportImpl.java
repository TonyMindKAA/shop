package com.epam.student.krynytskyi.validator.report;

import com.epam.student.krynytskyi.beans.RegistrationFormBean;
import com.epam.student.krynytskyi.beans.RegistrationFormReportBean;
import com.epam.student.krynytskyi.validator.form.field.impl.ConfirmFieldsValidator;
import com.epam.student.krynytskyi.validator.form.field.impl.EmailFieldValidator;
import com.epam.student.krynytskyi.validator.form.field.impl.LastNameFieldValidator;
import com.epam.student.krynytskyi.validator.form.field.impl.NameFieldValidator;
import com.epam.student.krynytskyi.validator.form.field.impl.PasswordFieldValidator;
import com.epam.student.krynytskyi.validator.form.field.impl.PhoneFieldValidator;

public class RegistrationFormValidationReportImpl implements RegistrationFormValidationReport {
	
	@Override
	public RegistrationFormReportBean generate(RegistrationFormBean formBean){
		RegistrationFormReportBean reportBeam = new RegistrationFormReportBean();
		reportBeam.setRegistrationFormBean(formBean);
		reportBeam.setEmailAddressValid(new EmailFieldValidator().validate(formBean.getEmailAddress()));
		reportBeam.setLastNameValid(new LastNameFieldValidator().validate(formBean.getLastName()));
		reportBeam.setNameValid(new NameFieldValidator().validate(formBean.getName()));
		reportBeam.setPasswordValid(new PasswordFieldValidator().validate(formBean.getPassword()));
		reportBeam.setPhoneValid(new PhoneFieldValidator().validate(formBean.getPhone()));
		reportBeam.setConfirmEmailAddressValid(new ConfirmFieldsValidator().validate( formBean.getEmailAddress(), formBean.getConfirmEmailAddress()));
		reportBeam.setConfirmPasswordValid(new ConfirmFieldsValidator().validate(formBean.getPassword(), formBean.getConfirmPassword()));
		return reportBeam;
	}
}
