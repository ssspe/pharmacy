package com.zircon.pharmacyApplicationTest.Prescription;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.zircon.pharmacyApplication.Prescription;

public class Requirement_1_1_8 {
	Prescription prescription;

	@Before
	public void setUp() throws Exception {
		prescription = new Prescription();
	}

	@Test
	public void Should_ReturnNumberOfContainersItemsOne_When_OneItem() {
		prescription.addPrescriptionItem("Medicine1", 1, 1, 10, false, "Comment", 1);
		assertEquals(prescription.getNumberOfContainers(), 1);
		prescription.clearPrescription();
		assertEquals(prescription.getNumberOfContainers(), 0);
	}

	@Test
	public void Should_ReturnNumberOfContainersItemsTwo_When_TwoItems() {
		prescription.addPrescriptionItem("Medicine1", 1, 1, 10, false, "Comment", 1);
		prescription.addPrescriptionItem("Medicine2", 2, 2, 10, true, "Comment2", 1);
		assertEquals(prescription.getNumberOfContainers(), 2);
		prescription.clearPrescription();
		assertEquals(prescription.getNumberOfContainers(), 0);
	}

	@Test
	public void Should_ReturnNumberOfContainersItemsZero_When_ByDefault() {
		assertEquals(prescription.getNumberOfContainers(), 0);
	}
}
