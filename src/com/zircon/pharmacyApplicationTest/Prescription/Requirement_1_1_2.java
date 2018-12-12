package com.zircon.pharmacyApplicationTest.Prescription;

import static com.zircon.pharmacyApplicationTest.HelperFunctions.checkMedicine;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.zircon.pharmacyApplication.Prescription;
import com.zircon.pharmacyApplication.PrescriptionItem;

public class Requirement_1_1_2 {
	Prescription prescription;

	@Before
	public void setUp() throws Exception {
		prescription = new Prescription();
	}

	@Test
	public void Should_OnlyHaveOneItem_When_AddingTheSameItem() {
		prescription.addPrescriptionItem("Medicine1", 1, 1, 1, false, "Comment", 1);
		prescription.addPrescriptionItem("Medicine1", 1, 1, 1, false, "Comment", 1);
		List<PrescriptionItem> lPrescriptionItems = prescription.getPrescriptionItems();
		assertEquals(lPrescriptionItems.size(), 1);
	}

	@Test
	public void Should_OnlyHaveOneItem_When_AddingTheSameItemMultipleTimes() {
		prescription.addPrescriptionItem("Medicine1", 1, 1, 1, false, "Comment", 1);
		prescription.addPrescriptionItem("Medicine1", 1, 1, 1, false, "Comment", 1);
		prescription.addPrescriptionItem("Medicine1", 1, 1, 1, false, "Comment", 1);
		prescription.addPrescriptionItem("Medicine1", 1, 1, 1, false, "Comment", 1);
		prescription.addPrescriptionItem("Medicine1", 1, 1, 1, false, "Comment", 1);
		List<PrescriptionItem> lPrescriptionItems = prescription.getPrescriptionItems();
		assertEquals(lPrescriptionItems.size(), 1);
	}
}