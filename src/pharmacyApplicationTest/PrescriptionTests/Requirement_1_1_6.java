package pharmacyApplicationTest.PrescriptionTests;

import static org.junit.Assert.*;
import static pharmacyApplicationTest.PrescriptionTests.HelperFunctions.checkMedicine;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import pharmacyApplication.Prescription;
import pharmacyApplication.PrescriptionItem;

public class Requirement_1_1_6 {
	Prescription prescription;

	@Before
	public void setUp() throws Exception {
		prescription = new Prescription();
	}

	@Test
	public void ClearOneItemFromThePrescriptionList() {
		prescription.addPrescriptionItem("Medicine1", 1, 1, 1, false, "Comment");
		List<PrescriptionItem> lPrescriptionItems = prescription.getPrescriptionItems();
		assertEquals(lPrescriptionItems.size(), 1);
		prescription.clearPrescription();
		lPrescriptionItems = prescription.getPrescriptionItems();
		assertEquals(lPrescriptionItems.size(), 0);
	}

	@Test
	public void ClearTwoItemsFromPrescriptionList() {
		prescription.addPrescriptionItem("Medicine1", 1, 1, 1, false, "Comment");
		prescription.addPrescriptionItem("Medicine2", 2, 2, 2, true, "Comment2");
		List<PrescriptionItem> lPrescriptionItems = prescription.getPrescriptionItems();
		assertEquals(lPrescriptionItems.size(), 2);
		prescription.clearPrescription();
		lPrescriptionItems = prescription.getPrescriptionItems();
		assertEquals(lPrescriptionItems.size(), 0);
	}
}
