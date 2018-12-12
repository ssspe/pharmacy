package com.zircon.pharmacyApplicationTest.Prescription;

import static com.zircon.pharmacyApplicationTest.HelperFunctions.checkMedicine;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.zircon.pharmacyApplication.Prescription;
import com.zircon.pharmacyApplication.PrescriptionItem;

public class Requirement_1_1_1 {
	Prescription prescription;

	@Before
	public void setUp() throws Exception {
		prescription = new Prescription();
	}

	@Test
	public void Should_AddOneItem_When_AddButtonIsPressed() {
		prescription.addPrescriptionItem("Medicine1", 1, 1, 1, false, "Comment", 1);
		List<PrescriptionItem> lPrescriptionItems = prescription.getPrescriptionItems();
		assertEquals(lPrescriptionItems.size(), 1);
		checkMedicine(lPrescriptionItems.get(0), "Medicine1", 1, 1, false, "Comment");
	}

	@Test
	public void Should_AddTwoItems_When_AddButtonIsPressed() {
		prescription.addPrescriptionItem("Medicine1", 1, 1, 1, false, "Comment", 1);
		prescription.addPrescriptionItem("Medicine2", 2, 2, 2, true, "Comment2", 1);
		List<PrescriptionItem> lPrescriptionItems = prescription.getPrescriptionItems();
		assertEquals(lPrescriptionItems.size(), 2);
		checkMedicine(lPrescriptionItems.get(0), "Medicine1", 1, 1, false, "Comment");
		checkMedicine(lPrescriptionItems.get(1), "Medicine2", 2, 2, true, "Comment2");
	}
}
