package com.epam.student.krynytskyi.validator.form.time.out;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.epam.student.krynytskyi.validator.form.time.out.impl.CaptchaTimeOutValidator;

public class CaptchaTimeOutValidatorTest {
	private CaptchaTimeOutValidator outValidator;

	@Before
	public void setUp() throws Exception {
		outValidator = new CaptchaTimeOutValidator();
	}

	@Test
	public void testShouldReturnTrueWhenTimeLimitLessDateCarate() {
		Date date = new Date();
		long timeLimit = 100000L;
		assertTrue(outValidator.validate(date, timeLimit));
	}

	@Test
	public void testShouldReturnFalseWhenTimeLimitMoreDateCarate() {
		Date date = new Date(new Date().getTime() - 1000);
		long timeLimit = 10;
		assertFalse(outValidator.validate(date, timeLimit));
	}

	@Test
	public void testShouldReturnFalseWhenTimeLimitZeroAndDateCarateNull() {
		Date date = null;
		int timeLimit = 0;
		assertFalse(outValidator.validate(date, timeLimit));
	}

	@Test
	public void testShouldReturnFalseWhenDateCarateNull() {
		Date date = null;
		long timeLimit = 10;
		assertFalse(outValidator.validate(date, timeLimit));
	}

	@Test
	public void testShouldReturnFalseWhenTimeLimitEqualsZero() {
		Date date = new Date(new Date().getTime() - 1000);
		long timeLimit = 0;
		assertFalse(outValidator.validate(date, timeLimit));
	}

}
