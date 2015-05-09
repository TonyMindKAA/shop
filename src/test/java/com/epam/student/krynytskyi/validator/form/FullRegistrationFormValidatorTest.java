package com.epam.student.krynytskyi.validator.form;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.epam.student.krynytskyi.beans.CaptchaBean;
import com.epam.student.krynytskyi.beans.RegistrationFormBean;
import com.epam.student.krynytskyi.beans.ValidateDataRegistrationFormBean;

public class FullRegistrationFormValidatorTest {
	private FullRegistrationFormValidator formValidator;

	@Before
	public void setUp() throws Exception {
		formValidator = new FullRegistrationFormValidatorImpl();
	}

	@Test
	public void testValidateWhenAllFieldsAreCorrectWhenReturnTrue() {
		RegistrationFormBean formBean = new RegistrationFormBean();
		formBean.setEmailAddress("kaapro@gmail.com");
		formBean.setConfirmEmailAddress("kaapro@gmail.com");
		formBean.setCaptcha("kaapro");
		formBean.setLastName("Toreto");
		formBean.setName("Meltos");
		formBean.setConfirmPassword("0123654789");
		formBean.setPassword("0123654789");
		formBean.setPhone("0954151123");
	
		CaptchaBean capthcaBean = new CaptchaBean();
		capthcaBean.setCaptchaValue("kaapro");

		ValidateDataRegistrationFormBean dataRegistrationForm = new ValidateDataRegistrationFormBean();
		dataRegistrationForm.setTimeOut(2000000);
		dataRegistrationForm.setCapthcaBean(capthcaBean);
		dataRegistrationForm.setFormBean(formBean);
		
		assertTrue(formValidator.validate(dataRegistrationForm));
	}

	@Test
	public void testValidateWhenNameLessThreeCharactersFieldsAreCorrectWhenReturnFalse() {
		RegistrationFormBean formBean = new RegistrationFormBean();
		formBean.setEmailAddress("kaapro@gmail.com");
		formBean.setConfirmEmailAddress("kaapro@gmail.com");
		formBean.setCaptcha("kaapro");
		formBean.setLastName("Toreto");
		formBean.setName("12");
		formBean.setConfirmPassword("0123654789");
		formBean.setPassword("0123654789");
		formBean.setPhone("0954151123");
	
		CaptchaBean capthcaBean = new CaptchaBean();
		capthcaBean.setCaptchaValue("kaapro");

		ValidateDataRegistrationFormBean dataRegistrationForm = new ValidateDataRegistrationFormBean();
		dataRegistrationForm.setTimeOut(2000000);
		dataRegistrationForm.setCapthcaBean(capthcaBean);
		dataRegistrationForm.setFormBean(formBean);
		
		assertFalse(formValidator.validate(dataRegistrationForm));
	}

	@Test
	public void testValidateWhenLastNameLessThreeCharactersFieldsAreCorrectWhenReturnFalse() {
		RegistrationFormBean formBean = new RegistrationFormBean();
		formBean.setEmailAddress("kaapro@gmail.com");
		formBean.setConfirmEmailAddress("kaapro@gmail.com");
		formBean.setCaptcha("kaapro");
		formBean.setLastName("12");
		formBean.setName("Boer");
		formBean.setConfirmPassword("0123654789");
		formBean.setPassword("0123654789");
		formBean.setPhone("0954151123");
	
		CaptchaBean capthcaBean = new CaptchaBean();
		capthcaBean.setCaptchaValue("kaapro");

		ValidateDataRegistrationFormBean dataRegistrationForm = new ValidateDataRegistrationFormBean();
		dataRegistrationForm.setTimeOut(2000000);
		dataRegistrationForm.setCapthcaBean(capthcaBean);
		dataRegistrationForm.setFormBean(formBean);
		
		assertFalse(formValidator.validate(dataRegistrationForm));
	}

	@Test
	public void testValidateWhenPhoneLessTenCharactersFieldsAreCorrectWhenReturnFalse() {
		RegistrationFormBean formBean = new RegistrationFormBean();
		formBean.setEmailAddress("kaapro@gmail.com");
		formBean.setConfirmEmailAddress("kaapro@gmail.com");
		formBean.setCaptcha("kaapro");
		formBean.setLastName("12");
		formBean.setName("Boer");
		formBean.setConfirmPassword("0123654789");
		formBean.setPassword("0123654789");
		formBean.setPhone("09541513");
	
		CaptchaBean capthcaBean = new CaptchaBean();
		capthcaBean.setCaptchaValue("kaapro");

		ValidateDataRegistrationFormBean dataRegistrationForm = new ValidateDataRegistrationFormBean();
		dataRegistrationForm.setTimeOut(2000000);
		dataRegistrationForm.setCapthcaBean(capthcaBean);
		dataRegistrationForm.setFormBean(formBean);
		
		assertFalse(formValidator.validate(dataRegistrationForm));
		
		
	}

	@Test
	public void testValidateWhenCaptchaValuesNoEqualsWhenReturnFalse() {
		RegistrationFormBean formBean = new RegistrationFormBean();
		formBean.setEmailAddress("kaapro@gmail.com");
		formBean.setConfirmEmailAddress("kaapro@gmail.com");
		formBean.setCaptcha("kaapro");
		formBean.setLastName("12");
		formBean.setName("Boer");
		formBean.setConfirmPassword("0123654789");
		formBean.setPassword("0123654789");
		formBean.setPhone("0954151123");
	
		CaptchaBean capthcaBean = new CaptchaBean();
		capthcaBean.setCaptchaValue("kaapro222");

		ValidateDataRegistrationFormBean dataRegistrationForm = new ValidateDataRegistrationFormBean();
		dataRegistrationForm.setTimeOut(2000000);
		dataRegistrationForm.setCapthcaBean(capthcaBean);
		dataRegistrationForm.setFormBean(formBean);
		
		assertFalse(formValidator.validate(dataRegistrationForm));
	}

	@Test
	public void testValidateWhenEmailsValuesNoEqualsWhenReturnFalse() {
		RegistrationFormBean formBean = new RegistrationFormBean();
		formBean.setEmailAddress("aaaaaa@gmail.com");
		formBean.setConfirmEmailAddress("kaapro@gmail.com");
		formBean.setCaptcha("kaapro");
		formBean.setLastName("12");
		formBean.setName("Boer");
		formBean.setConfirmPassword("0123654789");
		formBean.setPassword("0123654789");
		formBean.setPhone("0954151123");
	
		CaptchaBean capthcaBean = new CaptchaBean();
		capthcaBean.setCaptchaValue("kaapro");

		ValidateDataRegistrationFormBean dataRegistrationForm = new ValidateDataRegistrationFormBean();
		dataRegistrationForm.setTimeOut(2000000);
		dataRegistrationForm.setCapthcaBean(capthcaBean);
		dataRegistrationForm.setFormBean(formBean);
		
		assertFalse(formValidator.validate(dataRegistrationForm));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testValidateWhenEmailsNullEqualsWhenReturnFalse() {
		RegistrationFormBean formBean = new RegistrationFormBean();
		formBean.setEmailAddress(null);
		formBean.setConfirmEmailAddress("kaapro@gmail.com");
		formBean.setCaptcha("kaapro");
		formBean.setLastName("12");
		formBean.setName("Boer");
		formBean.setConfirmPassword("0123654789");
		formBean.setPassword("0123654789");
		formBean.setPhone("0954151123");
	
		CaptchaBean capthcaBean = new CaptchaBean();
		capthcaBean.setCaptchaValue("kaapro");

		ValidateDataRegistrationFormBean dataRegistrationForm = new ValidateDataRegistrationFormBean();
		dataRegistrationForm.setTimeOut(2000000);
		dataRegistrationForm.setCapthcaBean(capthcaBean);
		dataRegistrationForm.setFormBean(formBean);
		
		assertFalse(formValidator.validate(dataRegistrationForm));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testValidateWhenCaptchaNullEqualsWhenReturnFalse() {
		RegistrationFormBean formBean = new RegistrationFormBean();
		formBean.setEmailAddress("kaapro@gmail.com");
		formBean.setConfirmEmailAddress("kaapro@gmail.com");
		formBean.setCaptcha(null);
		formBean.setLastName("12");
		formBean.setName("Boer");
		formBean.setConfirmPassword("0123654789");
		formBean.setPassword("0123654789");
		formBean.setPhone("0954151123");
	
		CaptchaBean capthcaBean = new CaptchaBean();
		capthcaBean.setCaptchaValue("kaapro");

		ValidateDataRegistrationFormBean dataRegistrationForm = new ValidateDataRegistrationFormBean();
		dataRegistrationForm.setTimeOut(2000000);
		dataRegistrationForm.setCapthcaBean(capthcaBean);
		dataRegistrationForm.setFormBean(formBean);
		
		assertFalse(formValidator.validate(dataRegistrationForm));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testValidateWhenEmailNullEqualsWhenReturnFalse() {
		RegistrationFormBean formBean = new RegistrationFormBean();
		formBean.setEmailAddress("kaapro@gmail.com");
		formBean.setConfirmEmailAddress(null);
		formBean.setCaptcha("kaapro");
		formBean.setLastName("12");
		formBean.setName("Boer");
		formBean.setConfirmPassword("0123654789");
		formBean.setPassword("0123654789");
		formBean.setPhone("0954151123");
	
		CaptchaBean capthcaBean = new CaptchaBean();
		capthcaBean.setCaptchaValue("kaapro");

		ValidateDataRegistrationFormBean dataRegistrationForm = new ValidateDataRegistrationFormBean();
		dataRegistrationForm.setTimeOut(2000000);
		dataRegistrationForm.setCapthcaBean(capthcaBean);
		dataRegistrationForm.setFormBean(formBean);
		
		assertFalse(formValidator.validate(dataRegistrationForm));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testValidateWhenConfirmEmailNullEqualsWhenReturnFalse() {
		RegistrationFormBean formBean = new RegistrationFormBean();
		formBean.setEmailAddress("kaapro@gmail.com");
		formBean.setConfirmEmailAddress(null);
		formBean.setCaptcha("kaapro");
		formBean.setLastName("12");
		formBean.setName("Boer");
		formBean.setConfirmPassword("0123654789");
		formBean.setPassword("0123654789");
		formBean.setPhone("0954151123");
	
		CaptchaBean capthcaBean = new CaptchaBean();
		capthcaBean.setCaptchaValue("kaapro");

		ValidateDataRegistrationFormBean dataRegistrationForm = new ValidateDataRegistrationFormBean();
		dataRegistrationForm.setTimeOut(2000000);
		dataRegistrationForm.setCapthcaBean(capthcaBean);
		dataRegistrationForm.setFormBean(formBean);
		
		assertFalse(formValidator.validate(dataRegistrationForm));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testValidateWhenPasswordNullEqualsWhenReturnFalse() {
		RegistrationFormBean formBean = new RegistrationFormBean();
		formBean.setEmailAddress("kaapro@gmail.com");
		formBean.setConfirmEmailAddress("kaapro@gmail.com");
		formBean.setCaptcha("kaapro");
		formBean.setLastName("12");
		formBean.setName("Boer");
		formBean.setConfirmPassword("0123654789");
		formBean.setPassword(null);
		formBean.setPhone("0954151123");
	
		CaptchaBean capthcaBean = new CaptchaBean();
		capthcaBean.setCaptchaValue("kaapro");

		ValidateDataRegistrationFormBean dataRegistrationForm = new ValidateDataRegistrationFormBean();
		dataRegistrationForm.setTimeOut(2000000);
		dataRegistrationForm.setCapthcaBean(capthcaBean);
		dataRegistrationForm.setFormBean(formBean);
		
		assertFalse(formValidator.validate(dataRegistrationForm));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testValidateWhenNameNullEqualsWhenReturnFalse() {
		RegistrationFormBean formBean = new RegistrationFormBean();
		formBean.setEmailAddress("kaapro@gmail.com");
		formBean.setConfirmEmailAddress("kaapro@gmail.com");
		formBean.setCaptcha("kaapro");
		formBean.setLastName("12");
		formBean.setName(null);
		formBean.setConfirmPassword("0123654789");
		formBean.setPassword("0123654789");
		formBean.setPhone("0954151123");
	
		CaptchaBean capthcaBean = new CaptchaBean();
		capthcaBean.setCaptchaValue("kaapro");

		ValidateDataRegistrationFormBean dataRegistrationForm = new ValidateDataRegistrationFormBean();
		dataRegistrationForm.setTimeOut(2000000);
		dataRegistrationForm.setCapthcaBean(capthcaBean);
		dataRegistrationForm.setFormBean(formBean);
		
		assertFalse(formValidator.validate(dataRegistrationForm));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testValidateWhenPhoneNullEqualsWhenReturnFalse() {
		RegistrationFormBean formBean = new RegistrationFormBean();
		formBean.setEmailAddress("kaapro@gmail.com");
		formBean.setConfirmEmailAddress("kaapro@gmail.com");
		formBean.setCaptcha("kaapro");
		formBean.setLastName("12");
		formBean.setName("Boer");
		formBean.setConfirmPassword("0123654789");
		formBean.setPassword("0123654789");
		formBean.setPhone(null);
	
		CaptchaBean capthcaBean = new CaptchaBean();
		capthcaBean.setCaptchaValue("kaapro");

		ValidateDataRegistrationFormBean dataRegistrationForm = new ValidateDataRegistrationFormBean();
		dataRegistrationForm.setTimeOut(2000000);
		dataRegistrationForm.setCapthcaBean(capthcaBean);
		dataRegistrationForm.setFormBean(formBean);
		
		assertFalse(formValidator.validate(dataRegistrationForm));
	}

}
