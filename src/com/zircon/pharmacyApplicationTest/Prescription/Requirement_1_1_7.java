package com.zircon.pharmacyApplicationTest.Prescription;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.zircon.pharmacyApplication.Prescription;
import com.zircon.pharmacyApplication.PrescriptionItem;

public class Requirement_1_1_7 {
	Prescription prescription;

	@Before
	public void setUp() throws Exception {
		prescription = new Prescription();
	}

	@Test
	public void Should_ReturnNumberOfPrescriptionItemsOne_When_OneItem() {
		prescription.addPrescriptionItem("Medicine1", 1, 1, 1, false, "Comment", 1);
		assertEquals(prescription.getNumberOfPharmaceuticals(), 1);
		prescription.clearPrescription();
		assertEquals(prescription.getNumberOfPharmaceuticals(), 0);
	}

	@Test
	public void Should_ReturnNumberOfPrescriptionItemsTwo_When_TwoItems() {
		prescription.addPrescriptionItem("Medicine1", 1, 1, 1, false, "Comment", 1);
		prescription.addPrescriptionItem("Medicine2", 2, 2, 2, true, "Comment2", 1);
		assertEquals(prescription.getNumberOfPharmaceuticals(), 2);
		prescription.clearPrescription();
		assertEquals(prescription.getNumberOfPharmaceuticals(), 0);
	}
	
	@Test
	public void Should_ReturnNumberOfPrescriptionItemsZero_When_ByDefault() {
		assertEquals(prescription.getNumberOfPharmaceuticals(), 0);
	}

}
