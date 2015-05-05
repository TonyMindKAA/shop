package com.epam.student.krynytskyi.validator.chain.resposibility;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.epam.student.krynytskyi.validator.chain.resposibility.ConfirmedValidator;

public class ConfirmedValidatorTest {
	private ConfirmedValidator confirmedValidator;
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testValidateWhenCompareTwoEqualsSringThenReturnTrue() {
		String reference = "kaa-pro";
		String dublicate = "kaa-pro";
		confirmedValidator = new ConfirmedValidator(reference);
		assertTrue(confirmedValidator.validate(dublicate));
	}
	
	@Test
	public void testValidateWhenCompareTwoNoEqualsSringThenReturnFalse() {
		String reference = "kaa-pro-21";
		String dublicate = "kaa-pro";
		confirmedValidator = new ConfirmedValidator(reference);
		assertFalse(confirmedValidator.validate(dublicate));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testValidateWhenPutNullToConstructorThenThrowsIllegalArgumentException() {
		String reference = null;
		String dublicate = "kaa-pro";
		confirmedValidator = new ConfirmedValidator(reference);
		assertFalse(confirmedValidator.validate(dublicate));
	}
	@Test(expected = IllegalArgumentException.class)
	public void testValidateWhenPutNullToMethodValidateThenThrowsIllegalArgumentException() {
		String reference = "kaa-pro";
		String dublicate = null;
		confirmedValidator = new ConfirmedValidator(reference);
		assertFalse(confirmedValidator.validate(dublicate));
	}
	@Test(expected = IllegalArgumentException.class)
	public void testValidateWhenPutNullToMethodValidateAndConstructorThenThrowsIllegalArgumentException() {
		String reference = null;
		String dublicate = null;
		confirmedValidator = new ConfirmedValidator(reference);
		assertFalse(confirmedValidator.validate(dublicate));
	}
}
