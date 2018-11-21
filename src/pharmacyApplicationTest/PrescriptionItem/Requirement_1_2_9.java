package pharmacyApplicationTest.PrescriptionItem;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pharmacyApplication.PrescriptionItem;

public class Requirement_1_2_9 {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void DurationCanBeUpdatedByOne() throws Exception {
		PrescriptionItem prescriptionItem = new PrescriptionItem("Medicine1", 1, 1, 1, false, "Comment", 1);
		prescriptionItem.adjustDuration(1);
		assertEquals(2, prescriptionItem.getDuration());
	}

	@Test
	public void DurationCannotBeUpdatedByZero() throws Exception {
		PrescriptionItem prescriptionItem = new PrescriptionItem("Medicine1", 1, 1, 1, false, "Comment", 1);
		prescriptionItem.adjustDuration(0);
		assertEquals(1, prescriptionItem.getDuration());
	}
	
	@Test
	public void DurationCannotBeUpdatedByNegativeNumber() throws Exception {
		PrescriptionItem prescriptionItem = new PrescriptionItem("Medicine1", 1, 1, 1, false, "Comment", 1);
		prescriptionItem.adjustDuration(-1);
		assertEquals(1, prescriptionItem.getDuration());
	}

}
