package com.epam.student.krynytskyi.validator.chain.resposibility;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class EmptyValidatorTest {
	private EmptyValidator  emptyValidator;
	@Before
	public void setUp() throws Exception {
		emptyValidator = new EmptyValidator();
	}

	@Test
	public void testValidatorWhenPutEmptyStringThenReturnFalse() {
		String value = "";
		assertFalse(emptyValidator.validate(value));
	}

	@Test
	public void testValidatorWhenPutNoEmptyStringThenReturnTrue() {
		String value = " ";
		assertTrue(emptyValidator.validate(value));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testValidatorWhenPutNullThenTrowsIllegalArgumentException() {
		String value = null;
		assertTrue(emptyValidator.validate(value));
	}
}
