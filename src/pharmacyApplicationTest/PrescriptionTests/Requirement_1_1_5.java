package pharmacyApplicationTest.PrescriptionTests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import pharmacyApplication.Prescription;
import pharmacyApplication.PrescriptionItem;
import static pharmacyApplicationTest.PrescriptionTests.HelperFunctions.checkMedicine;

public class Requirement_1_1_5 {
	Prescription prescription;

	@Before
	public void setUp() throws Exception {
		prescription = new Prescription();
	}

	@Test
	public void RemoveOneItemFromThePrescriptionList() {
		prescription.addPrescriptionItem("Medicine1", 1, 1, 1, false, "Comment");
		List<PrescriptionItem> lPrescriptionItems = prescription.getPrescriptionItems();
		assertEquals(lPrescriptionItems.size(), 1);
		prescription.removePrescriptionItem("Medicine1");
		lPrescriptionItems = prescription.getPrescriptionItems();
		assertEquals(lPrescriptionItems.size(), 0);
	}

	@Test
	public void RemoveOneItemFromListOfTwoItems() {
		prescription.addPrescriptionItem("Medicine1", 1, 1, 1, false, "Comment");
		prescription.addPrescriptionItem("Medicine2", 2, 2, 2, true, "Comment2");
		List<PrescriptionItem> lPrescriptionItems = prescription.getPrescriptionItems();
		assertEquals(lPrescriptionItems.size(), 2);
		prescription.removePrescriptionItem("Medicine1");
		lPrescriptionItems = prescription.getPrescriptionItems();
		assertEquals(lPrescriptionItems.size(), 1);
		checkMedicine(lPrescriptionItems.get(0), "Medicine2", 2, 2, true, "Comment2");
	}
	
	@Test
	public void RemoveBothItemsFromListOfTwoItems() {
		prescription.addPrescriptionItem("Medicine1", 1, 1, 1, false, "Comment");
		prescription.addPrescriptionItem("Medicine2", 2, 2, 2, true, "Comment2");
		List<PrescriptionItem> lPrescriptionItems = prescription.getPrescriptionItems();
		assertEquals(lPrescriptionItems.size(), 2);
		prescription.removePrescriptionItem("Medicine1");
		lPrescriptionItems = prescription.getPrescriptionItems();
		assertEquals(lPrescriptionItems.size(), 1);
		checkMedicine(lPrescriptionItems.get(0), "Medicine2", 2, 2, true, "Comment2");
		prescription.removePrescriptionItem("Medicine2");
		lPrescriptionItems = prescription.getPrescriptionItems();
		assertEquals(lPrescriptionItems.size(), 0);
	}
	
	@Test
	public void RemoveItemThatDoesntExist() {
		prescription.addPrescriptionItem("Medicine1", 1, 1, 1, false, "Comment");
		List<PrescriptionItem> lPrescriptionItems = prescription.getPrescriptionItems();
		assertEquals(lPrescriptionItems.size(), 1);
		prescription.removePrescriptionItem("DoesNotExist");
		assertEquals(lPrescriptionItems.size(), 1);
		checkMedicine(lPrescriptionItems.get(0), "Medicine1", 1, 1, false, "Comment");
	}

}
