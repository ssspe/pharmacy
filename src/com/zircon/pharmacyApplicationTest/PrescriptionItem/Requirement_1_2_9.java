package com.zircon.pharmacyApplicationTest.PrescriptionItem;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.zircon.pharmacyApplication.PrescriptionItem;

public class Requirement_1_2_9 {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void Should_DurationUpdateByOne_WhenAdjustDuration() throws Exception {
		PrescriptionItem prescriptionItem = new PrescriptionItem("Medicine1", 1, 1, 1, false, "Comment", 1);
		prescriptionItem.adjustDuration(1);
		assertEquals(2, prescriptionItem.getDuration());
	}

	@Test
	public void Should_DurationNotUpdate_WhenAdjustDurationByZero() throws Exception {
		PrescriptionItem prescriptionItem = new PrescriptionItem("Medicine1", 1, 1, 1, false, "Comment", 1);
		prescriptionItem.adjustDuration(0);
		assertEquals(1, prescriptionItem.getDuration());
	}
	
	@Test
	public void Should_DurationNotUpdate_WhenAdjustDurationByNegativeNumber() throws Exception {
		PrescriptionItem prescriptionItem = new PrescriptionItem("Medicine1", 1, 1, 1, false, "Comment", 1);
		prescriptionItem.adjustDuration(-1);
		assertEquals(1, prescriptionItem.getDuration());
	}

}
