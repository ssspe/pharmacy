package pharmacyApplicationTest.PrescriptionTests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import pharmacyApplication.Prescription;
import pharmacyApplication.PrescriptionItem;
import static pharmacyApplicationTest.PrescriptionTests.HelperFunctions.checkMedicine;

public class Requirement_1_1_1 {
	Prescription prescription;

	@Before
	public void setUp() throws Exception {
		prescription = new Prescription();
	}

	@Test
	public void AddOneItemAndCheckItIsAddedToList() {
		prescription.addPrescriptionItem("Medicine1", 1, 1, 1, false, "Comment");
		List<PrescriptionItem> lPrescriptionItems = prescription.getPrescriptionItems();
		assertEquals(lPrescriptionItems.size(), 1);
		checkMedicine(lPrescriptionItems.get(0), "Medicine1", 1, 1, false, "Comment");
	}

	@Test
	public void AddTwoItemsAndCheckTheyAreAddedToList() {
		prescription.addPrescriptionItem("Medicine1", 1, 1, 1, false, "Comment");
		prescription.addPrescriptionItem("Medicine2", 2, 2, 2, true, "Comment2");
		List<PrescriptionItem> lPrescriptionItems = prescription.getPrescriptionItems();
		assertEquals(lPrescriptionItems.size(), 2);
		checkMedicine(lPrescriptionItems.get(0), "Medicine1", 1, 1, false, "Comment");
		checkMedicine(lPrescriptionItems.get(1), "Medicine2", 2, 2, true, "Comment2");
	}
}
