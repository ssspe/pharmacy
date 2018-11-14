package pharmacyApplicationTest.PrescriptionTests;

import static org.junit.Assert.*;
import static pharmacyApplicationTest.PrescriptionTests.HelperFunctions.checkMedicine;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import pharmacyApplication.Prescription;
import pharmacyApplication.PrescriptionItem;

public class Requirement_1_1_2 {
	Prescription prescription;

	@Before
	public void setUp() throws Exception {
		prescription = new Prescription();
	}

	@Test
	public void AddTheSameItemAndCheckItIsOnlyAddedOnce() {
		prescription.addPrescriptionItem("Medicine1", 1, 1, 1, false, "Comment");
		prescription.addPrescriptionItem("Medicine1", 1, 1, 1, false, "Comment");
		List<PrescriptionItem> lPrescriptionItems = prescription.getPrescriptionItems();
		assertEquals(lPrescriptionItems.size(), 1);
	}

	@Test
	public void AddTheSameItemMultipleTimesAndCheckItIsOnlyAddedOnce() {
		prescription.addPrescriptionItem("Medicine1", 1, 1, 1, false, "Comment");
		prescription.addPrescriptionItem("Medicine1", 1, 1, 1, false, "Comment");
		prescription.addPrescriptionItem("Medicine1", 1, 1, 1, false, "Comment");
		prescription.addPrescriptionItem("Medicine1", 1, 1, 1, false, "Comment");
		prescription.addPrescriptionItem("Medicine1", 1, 1, 1, false, "Comment");
		List<PrescriptionItem> lPrescriptionItems = prescription.getPrescriptionItems();
		assertEquals(lPrescriptionItems.size(), 1);
	}
}
