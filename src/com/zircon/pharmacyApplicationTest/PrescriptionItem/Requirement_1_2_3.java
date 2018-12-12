package com.zircon.pharmacyApplicationTest.PrescriptionItem;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.zircon.pharmacyApplication.PrescriptionItem;

public class Requirement_1_2_3 {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void Should_AddItem_When_ValidDailyDoseOf1() throws Exception {
		PrescriptionItem prescriptionItem = new PrescriptionItem("Medicine1", 1, 1, 1, false, "Comment", 1);
		assertEquals(1, prescriptionItem.getPrescribedDailyDose());
	}

	@Test
	public void Should_AddItem_When_ValidDailyDoseOf10() throws Exception {
		PrescriptionItem prescriptionItem = new PrescriptionItem("Medicine1", 10, 1, 1, false, "Comment", 1);
		assertEquals(10, prescriptionItem.getPrescribedDailyDose());
	}

	@Test(expected = Exception.class)
	public void Should_ThrowError_When_InvalidDailyDoseOf0() throws Exception {
		PrescriptionItem prescriptionItem = new PrescriptionItem("Medicine1", 0, 1, 1, false, "Comment", 1);
	}
	
	@Test(expected = Exception.class)
	public void Should_ThrowError_When_InvalidDailyDoseOfNegativeNumber() throws Exception {
		PrescriptionItem prescriptionItem = new PrescriptionItem("Medicine1", -1, 1, 1, false, "Comment", 1);
	}

}
