package com.zircon.pharmacyApplicationTest.PrescriptionItem;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.zircon.pharmacyApplication.PrescriptionItem;

public class Requirement_1_2_2 {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void Should_AddItem_When_PharmNameIsValidChar() throws Exception {
		PrescriptionItem prescriptionItem = new PrescriptionItem("TestName", 1, 1, 1, false, "Comment", 1);
		assertEquals("TestName", prescriptionItem.getPharmaceuticalName());
	}

	@Test
	public void Should_AddItem_When_PharmNameIsValidIntegers() throws Exception {
		PrescriptionItem prescriptionItem = new PrescriptionItem("123", 1, 1, 1, false, "Comment", 1);
		assertEquals("123", prescriptionItem.getPharmaceuticalName());
	}

	@Test(expected = Exception.class)
	public void Should_ThrowError_When_PharmaNameIsEmptyString() throws Exception {
		PrescriptionItem prescriptionItem = new PrescriptionItem("", 1, 1, 1, false, "Comment", 1);
	}

}
