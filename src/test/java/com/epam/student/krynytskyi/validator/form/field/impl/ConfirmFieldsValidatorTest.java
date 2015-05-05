package com.epam.student.krynytskyi.validator.form.field.impl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ConfirmFieldsValidatorTest {
	private ConfirmFieldsValidator confirmFieldsValidator;

	@Before
	public void setUp() throws Exception {
		confirmFieldsValidator = new ConfirmFieldsValidator();
	}

	@Test
	public void testWhenFirtstValueEqualsSecondValueThenReturnTrue() {
		String firstValue = "kaa";
		String secondValue = "kaa";
		assertTrue(confirmFieldsValidator.validate(firstValue, secondValue));
	}

	@Test
	public void testWhenFirtstValueAndSecondValueIsEmptyThenReturnFalse() {
		assertFalse(confirmFieldsValidator.validate("", ""));
	}

	@Test
	public void testWhenFirtstValueNoEqualsSecondValueThenReturnFalse() {
		assertFalse(confirmFieldsValidator.validate("kaa", "kaaaaa"));
	}

	@Test
	public void testWhenSecondValueNoEqualsFirtstValueThenReturnTrue() {
		String firstValue = "kaasssss";
		String secondValue = "kaa";
		assertFalse(confirmFieldsValidator.validate(firstValue, secondValue));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testWhenFirtstValueNullThenThrowsIllegalArgumentException() {
		String firstValue = null;
		String secondValue = "kaa";
		assertTrue(confirmFieldsValidator.validate(firstValue, secondValue));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testWhenSecondValueNullThenThrowsIllegalArgumentException() {
		String firstValue = "kaa";
		String secondValue = null;
		assertTrue(confirmFieldsValidator.validate(firstValue, secondValue));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testWhenFirtstValueAndSecondValueNullThenThrowsIllegalArgumentException() {
		String firstValue = null;
		String secondValue = null;
		assertTrue(confirmFieldsValidator.validate(firstValue, secondValue));
	}

}
