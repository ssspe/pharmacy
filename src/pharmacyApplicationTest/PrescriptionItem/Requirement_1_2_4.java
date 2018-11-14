package pharmacyApplicationTest.PrescriptionItem;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pharmacyApplication.PrescriptionItem;

public class Requirement_1_2_4 {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void AddingItemWithValidDurationOf1() throws Exception {
		PrescriptionItem prescriptionItem = new PrescriptionItem("Medicine1", 1, 1, 1, false, "Comment");
		assertEquals(1, prescriptionItem.getDuration());
	}

	@Test
	public void AddingItemWithValidDurationOf10() throws Exception {
		PrescriptionItem prescriptionItem = new PrescriptionItem("Medicine1", 1, 10, 1, false, "Comment");
		assertEquals(10, prescriptionItem.getDuration());
	}

	@Test(expected = Exception.class)
	public void AddingItemWithInvalidDurationOf0() throws Exception {
		PrescriptionItem prescriptionItem = new PrescriptionItem("Medicine1", 1, 0, 1, false, "Comment");
	}

	@Test(expected = Exception.class)
	public void AddingItemWithInvalidDurationOfNegativeNumber() throws Exception {
		PrescriptionItem prescriptionItem = new PrescriptionItem("Medicine1", 1, -1, 1, false, "Comment");
	}

}
