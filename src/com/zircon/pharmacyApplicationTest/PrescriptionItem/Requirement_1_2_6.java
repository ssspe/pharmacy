package com.zircon.pharmacyApplicationTest.PrescriptionItem;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.zircon.pharmacyApplication.PrescriptionItem;

public class Requirement_1_2_6 {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void Should_AddItem_With_ValidAvailableOverTheCounterSetToTrue() throws Exception {
		PrescriptionItem prescriptionItem = new PrescriptionItem("Medicine1", 1, 1, 1, true, "Comment", 1);
		assertEquals(true, prescriptionItem.isAvailableOverTheCounter());
	}

	@Test
	public void Should_AddItem_With_ValidAvailableOverTheCounterSetToFalse() throws Exception {
		PrescriptionItem prescriptionItem = new PrescriptionItem("Medicine1", 1, 1, 1, false, "Comment", 1);
		assertEquals(false, prescriptionItem.isAvailableOverTheCounter());
	}

}
