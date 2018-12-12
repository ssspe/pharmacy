package com.zircon.pharmacyApplicationTest.Prescription;

import static com.zircon.pharmacyApplicationTest.HelperFunctions.checkMedicine;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.zircon.pharmacyApplication.Prescription;
import com.zircon.pharmacyApplication.PrescriptionItem;

public class Requirement_1_1_10 {
	Prescription prescription;

	@Before
	public void setUp() throws Exception {
		prescription = new Prescription();
	}

	@Test
	public void Should_IncreaseDuration_When_AddingTheSamePrescriptionItem() {
		prescription.addPrescriptionItem("Medicine1", 1, 1, 1, false, "Comment", 1);
		prescription.addPrescriptionItem("Medicine1", 1, 1, 1, false, "Comment", 1);
		List<PrescriptionItem> lPrescriptionItems = prescription.getPrescriptionItems();
		assertEquals(lPrescriptionItems.size(), 1);
		assertEquals(lPrescriptionItems.get(0).getDuration(), 2);
	}
	
	@Test
	public void Should_IncreaseDuration_When_AddingTheSamePrescriptionItemWithDifferentDuration() {
		prescription.addPrescriptionItem("Medicine1", 1, 2, 1, false, "Comment", 1);
		prescription.addPrescriptionItem("Medicine1", 1, 3, 1, false, "Comment", 1);
		prescription.addPrescriptionItem("Medicine1", 1, 4, 1, false, "Comment", 1);
		List<PrescriptionItem> lPrescriptionItems = prescription.getPrescriptionItems();
		assertEquals(lPrescriptionItems.size(), 1);
		assertEquals(lPrescriptionItems.get(0).getDuration(), 9);
	}
	
	@Test
	public void Should_KeepSameDuration_When_AddingTheSamePrescriptionItemWithNoDuration() {
		prescription.addPrescriptionItem("Medicine1", 1, 1, 1, false, "Comment", 1);
		prescription.addPrescriptionItem("Medicine1", 1, 0, 1, false, "Comment", 1);
		List<PrescriptionItem> lPrescriptionItems = prescription.getPrescriptionItems();
		assertEquals(lPrescriptionItems.size(), 1);
		assertEquals(lPrescriptionItems.get(0).getDuration(), 1);
	}
	
	@Test
	public void Should_KeepSameDuration_When_AddingTheSamePrescriptionItemWithNegativeDuration() {
		prescription.addPrescriptionItem("Medicine1", 1, 1, 1, false, "Comment", 1);
		prescription.addPrescriptionItem("Medicine1", 1, -1, 1, false, "Comment", 1);
		List<PrescriptionItem> lPrescriptionItems = prescription.getPrescriptionItems();
		assertEquals(lPrescriptionItems.size(), 1);
		assertEquals(lPrescriptionItems.get(0).getDuration(), 1);
	}
}
