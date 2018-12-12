package com.zircon.pharmacyApplicationTest.PrescriptionItem;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.zircon.pharmacyApplication.PrescriptionItem;

public class Requirement_1_2_8 {
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void Should_HaveOneContainer_When_AddingItemWithSmallContainer() throws Exception {
		PrescriptionItem prescriptionItem = new PrescriptionItem("Medicine1", 1, 1, 10, false, "Comment", 1);
		assertEquals(1, prescriptionItem.getNumberOfContainers());
	}

	@Test
	public void Should_HaveOneContainer_When_AddingItemWithFullContainer() throws Exception {
		PrescriptionItem prescriptionItem = new PrescriptionItem("Medicine1", 1, 10, 10, false, "Comment", 1);
		assertEquals(1, prescriptionItem.getNumberOfContainers());
	}
	
	@Test
	public void Should_HaveTwoContainer_When_AddingItemWithOverFullContainer() throws Exception {
		PrescriptionItem prescriptionItem = new PrescriptionItem("Medicine1", 1, 11, 10, false, "Comment", 1);
		assertEquals(2, prescriptionItem.getNumberOfContainers());
	}
	
	@Test
	public void Should_HaveManyContainer_When_AddingItemWithWellOverFullContainer() throws Exception {
		PrescriptionItem prescriptionItem = new PrescriptionItem("Medicine1", 12, 5, 5, false, "Comment", 1);
		assertEquals(12, prescriptionItem.getNumberOfContainers());
	}
}
