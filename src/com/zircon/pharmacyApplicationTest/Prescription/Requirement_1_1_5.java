package com.zircon.pharmacyApplicationTest.Prescription;

import static com.zircon.pharmacyApplicationTest.HelperFunctions.checkMedicine;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.zircon.pharmacyApplication.Prescription;
import com.zircon.pharmacyApplication.PrescriptionItem;

public class Requirement_1_1_5 {
	Prescription prescription;

	@Before
	public void setUp() throws Exception {
		prescription = new Prescription();
	}

	@Test
	public void SHould_RemoveOneItem_When_RemovingItem() {
		prescription.addPrescriptionItem("Medicine1", 1, 1, 1, false, "Comment", 1);
		List<PrescriptionItem> lPrescriptionItems = prescription.getPrescriptionItems();
		assertEquals(lPrescriptionItems.size(), 1);
		prescription.removePrescriptionItem("Medicine1");
		lPrescriptionItems = prescription.getPrescriptionItems();
		assertEquals(lPrescriptionItems.size(), 0);
	}

	@Test
	public void Should_RemoveOneItemFromListOfTwo_When_RemovingItem() {
		prescription.addPrescriptionItem("Medicine1", 1, 1, 1, false, "Comment", 1);
		prescription.addPrescriptionItem("Medicine2", 2, 2, 2, true, "Comment2", 1);
		List<PrescriptionItem> lPrescriptionItems = prescription.getPrescriptionItems();
		assertEquals(lPrescriptionItems.size(), 2);
		prescription.removePrescriptionItem("Medicine1");
		lPrescriptionItems = prescription.getPrescriptionItems();
		assertEquals(lPrescriptionItems.size(), 1);
		checkMedicine(lPrescriptionItems.get(0), "Medicine2", 2, 2, true, "Comment2");
	}
	
	@Test
	public void Should_RemoveBothItemsFromListOfTwo_When_RemovingItem() {
		prescription.addPrescriptionItem("Medicine1", 1, 1, 1, false, "Comment", 1);
		prescription.addPrescriptionItem("Medicine2", 2, 2, 2, true, "Comment2", 1);
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
	public void Should_NotRemoveItem_When_RemovingItemThatDoesNotExist() {
		prescription.addPrescriptionItem("Medicine1", 1, 1, 1, false, "Comment", 1);
		List<PrescriptionItem> lPrescriptionItems = prescription.getPrescriptionItems();
		assertEquals(lPrescriptionItems.size(), 1);
		prescription.removePrescriptionItem("DoesNotExist");
		assertEquals(lPrescriptionItems.size(), 1);
		checkMedicine(lPrescriptionItems.get(0), "Medicine1", 1, 1, false, "Comment");
	}

}
