package com.epam.student.krynytskyi.validator.patterns;

public class RegistrationFormValidationPatterns {
	public static final String MAIL_PATTERN= "^([\\w-]+(?:\\.[\\w-]+)*)@((?:[\\w-]+\\.)*\\w[\\w-]{0,66})\\.([a-z]{2,6}(?:\\.[a-z]{2})?)$";
	public static final String PHONE_PATTERN= "^[(]{0,1}[0-9]{3}[)]{0,1}[-\\s\\.]{0,1}[0-9]{3}[-\\s\\.]{0,1}[0-9]{4}$";
	public static final String NAME_PATTER= "^\\w{3,30}";
	public static final String LAST_NAME_PATTER= "^\\w{3,30}";
	public static final String PASWORD_PATTERN= "^\\w{4,30}";
}
