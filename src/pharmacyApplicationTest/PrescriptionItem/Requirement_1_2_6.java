package pharmacyApplicationTest.PrescriptionItem;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pharmacyApplication.PrescriptionItem;

public class Requirement_1_2_6 {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void AddingItemWithAvailableOverTheCounterSetToTrue() throws Exception {
		PrescriptionItem prescriptionItem = new PrescriptionItem("Medicine1", 1, 1, 1, true, "Comment");
		assertEquals(true, prescriptionItem.isAvailableOverTheCounter());
	}

	@Test
	public void AddingItemWithAvailableOverTheCounterSetToFalse() throws Exception {
		PrescriptionItem prescriptionItem = new PrescriptionItem("Medicine1", 1, 1, 1, false, "Comment");
		assertEquals(false, prescriptionItem.isAvailableOverTheCounter());
	}

}
