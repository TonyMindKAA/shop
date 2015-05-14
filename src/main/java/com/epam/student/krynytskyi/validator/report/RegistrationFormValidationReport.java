package com.epam.student.krynytskyi.validator.report;

import com.epam.student.krynytskyi.beans.registration.RegistrationFormBean;
import com.epam.student.krynytskyi.beans.registration.RegistrationFormReportBean;

public interface RegistrationFormValidationReport {
	RegistrationFormReportBean generate(RegistrationFormBean formBean);
}
