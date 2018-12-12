package com.zircon.pharmacyApplicationTest.Prescription;

import static com.zircon.pharmacyApplicationTest.HelperFunctions.checkMedicine;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.zircon.pharmacyApplication.Prescription;
import com.zircon.pharmacyApplication.PrescriptionItem;

public class Requirement_1_1_11 {
	Prescription prescription;

	@Before
	public void setUp() throws Exception {
		prescription = new Prescription();
	}

	@Test
	public void Should_NotChangeDailyDose_When_AddingTheSamePrescriptionItem() {
		prescription.addPrescriptionItem("Medicine1", 1, 1, 1, false, "Comment", 1);
		prescription.addPrescriptionItem("Medicine1", 1, 1, 1, false, "Comment", 1);
		List<PrescriptionItem> lPrescriptionItems = prescription.getPrescriptionItems();
		assertEquals(lPrescriptionItems.size(), 1);
		assertEquals(lPrescriptionItems.get(0).getPrescribedDailyDose(), 1);
	}
	
	@Test
	public void Should_ChangeDailyDose_When_AddingTheSamePrescriptionItemWithFirstItemDifferentDailyDose() {
		prescription.addPrescriptionItem("Medicine1", 2, 1, 1, false, "Comment", 1);
		prescription.addPrescriptionItem("Medicine1", 1, 1, 1, false, "Comment", 1);
		List<PrescriptionItem> lPrescriptionItems = prescription.getPrescriptionItems();
		assertEquals(lPrescriptionItems.size(), 1);
		assertEquals(lPrescriptionItems.get(0).getPrescribedDailyDose(), 2);
	}
	
	@Test
	public void Should_ChangeDailyDose_When_AddingTheSamePrescriptionItemWithSecondItemDifferentDailyDose() {
		prescription.addPrescriptionItem("Medicine1", 2, 1, 1, false, "Comment", 1);
		prescription.addPrescriptionItem("Medicine1", 3, 1, 1, false, "Comment", 1);
		List<PrescriptionItem> lPrescriptionItems = prescription.getPrescriptionItems();
		assertEquals(lPrescriptionItems.size(), 1);
		assertEquals(lPrescriptionItems.get(0).getPrescribedDailyDose(), 3);
	}
}
