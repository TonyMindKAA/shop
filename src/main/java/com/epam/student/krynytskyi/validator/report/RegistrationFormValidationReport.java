package com.epam.student.krynytskyi.validator.report;

import com.epam.student.krynytskyi.beans.RegistrationFormBean;
import com.epam.student.krynytskyi.beans.RegistrationFormReportBean;

public interface RegistrationFormValidationReport {
	RegistrationFormReportBean generate(RegistrationFormBean formBean);
}
