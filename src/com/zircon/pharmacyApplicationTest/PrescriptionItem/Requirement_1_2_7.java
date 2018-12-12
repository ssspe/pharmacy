package com.zircon.pharmacyApplicationTest.PrescriptionItem;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.zircon.pharmacyApplication.PrescriptionItem;

public class Requirement_1_2_7 {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void Should_AddItem_With_ValidCommentMadeOfChars() throws Exception {
		PrescriptionItem prescriptionItem = new PrescriptionItem("Medicine1", 1, 1, 1, false, "Test Comment", 1);
		assertEquals("Test Comment", prescriptionItem.getComments());
	}

	@Test
	public void Should_AddItem_With_ValidCommentMadeOfIntegers() throws Exception {
		PrescriptionItem prescriptionItem = new PrescriptionItem("Medicine1", 1, 1, 1, false, "123", 1);
		assertEquals("123", prescriptionItem.getComments());
	}

	@Test(expected = Exception.class)
	public void Should_ThrowError_With_InvalidCommentMadeOfEmptyString() throws Exception {
		PrescriptionItem prescriptionItem = new PrescriptionItem("Medicine1", 1, 1, 1, false, "", 1);
	}

}
