package com.zircon.pharmacyApplicationTest.PrescriptionItem;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.zircon.pharmacyApplication.PrescriptionItem;

public class Requirement_1_2_5 {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void Should_AddItem_When_ValidContainerSizeOf1() throws Exception {
		PrescriptionItem prescriptionItem = new PrescriptionItem("Medicine1", 1, 1, 1, false, "Comment", 1);
	}

	@Test
	public void Should_AddItem_When_ValidContainerSizeOf10() throws Exception {
		PrescriptionItem prescriptionItem = new PrescriptionItem("Medicine1", 1, 1, 10, false, "Comment", 1);
	}

	@Test(expected = Exception.class)
	public void Should_ThrowError_When_InvalidContainerSizeOf0() throws Exception {
		PrescriptionItem prescriptionItem = new PrescriptionItem("Medicine1", 1, 1, 0, false, "Comment", 1);
	}

	@Test(expected = Exception.class)
	public void Should_ThrowError_When_InvalidContainerSizeOfNegativeNumber() throws Exception {
		PrescriptionItem prescriptionItem = new PrescriptionItem("Medicine1", 1, 1, -1, false, "Comment", 1);
	}

}
