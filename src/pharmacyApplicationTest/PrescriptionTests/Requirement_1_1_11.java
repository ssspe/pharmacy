package pharmacyApplicationTest.PrescriptionTests;

import static org.junit.Assert.*;
import static pharmacyApplicationTest.PrescriptionTests.HelperFunctions.checkMedicine;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import pharmacyApplication.Prescription;
import pharmacyApplication.PrescriptionItem;

public class Requirement_1_1_11 {
	Prescription prescription;

	@Before
	public void setUp() throws Exception {
		prescription = new Prescription();
	}

	@Test
	public void AddingTheSamePrescriptionItemDoesntChangeDailyDose() {
		prescription.addPrescriptionItem("Medicine1", 1, 1, 1, false, "Comment");
		prescription.addPrescriptionItem("Medicine1", 1, 1, 1, false, "Comment");
		List<PrescriptionItem> lPrescriptionItems = prescription.getPrescriptionItems();
		assertEquals(lPrescriptionItems.size(), 1);
		assertEquals(lPrescriptionItems.get(0).getPrescribedDailyDose(), 1);
	}
	
	@Test
	public void AddingTheSamePrescriptionItemWithFirstItemDifferentChangesDailyDose() {
		prescription.addPrescriptionItem("Medicine1", 2, 1, 1, false, "Comment");
		prescription.addPrescriptionItem("Medicine1", 1, 1, 1, false, "Comment");
		List<PrescriptionItem> lPrescriptionItems = prescription.getPrescriptionItems();
		assertEquals(lPrescriptionItems.size(), 1);
		assertEquals(lPrescriptionItems.get(0).getPrescribedDailyDose(), 2);
	}
	
	@Test
	public void AddingTheSamePrescriptionItemWithSecondItemDifferentChangesDailyDose() {
		prescription.addPrescriptionItem("Medicine1", 2, 1, 1, false, "Comment");
		prescription.addPrescriptionItem("Medicine1", 3, 1, 1, false, "Comment");
		List<PrescriptionItem> lPrescriptionItems = prescription.getPrescriptionItems();
		assertEquals(lPrescriptionItems.size(), 1);
		assertEquals(lPrescriptionItems.get(0).getPrescribedDailyDose(), 3);
	}
}
