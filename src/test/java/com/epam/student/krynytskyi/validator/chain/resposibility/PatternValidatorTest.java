package com.epam.student.krynytskyi.validator.chain.resposibility;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PatternValidatorTest {
	private PatternValidator patternValidator;

	@Test
	public void test1() {
		String pattern = "1234";
		String value = "1234";
		patternValidator = new PatternValidator(pattern);
		assertTrue(patternValidator.validate(value));
	}

	@Test
	public void test21() {
		String pattern = "1234ssafd";
		String value = "1234";
		patternValidator = new PatternValidator(pattern);
		assertFalse(patternValidator.validate(value));
	}

	@Test(expected = IllegalArgumentException.class)
	public void test2() {
		String pattern = null;
		String value = "1234";
		patternValidator = new PatternValidator(pattern);
		assertFalse(patternValidator.validate(value));
	}

	@Test(expected = IllegalArgumentException.class)
	public void test34() {
		String pattern = "1234";
		String value = null;
		patternValidator = new PatternValidator(pattern);
		assertTrue(patternValidator.validate(value));
	}

	@Test(expected = IllegalArgumentException.class)
	public void test3() {
		String pattern = null;
		String value = null;
		patternValidator = new PatternValidator(pattern);
		assertTrue(patternValidator.validate(value));
	}

}
