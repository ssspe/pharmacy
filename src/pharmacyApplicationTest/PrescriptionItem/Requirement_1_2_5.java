package pharmacyApplicationTest.PrescriptionItem;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pharmacyApplication.PrescriptionItem;

public class Requirement_1_2_5 {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void AddingItemWithValidContainerSizeOf1() throws Exception {
		PrescriptionItem prescriptionItem = new PrescriptionItem("Medicine1", 1, 1, 1, false, "Comment");
	}

	@Test
	public void AddingItemWithValidContainerSizeOf10() throws Exception {
		PrescriptionItem prescriptionItem = new PrescriptionItem("Medicine1", 1, 1, 10, false, "Comment");
	}

	@Test(expected = Exception.class)
	public void AddingItemWithInvalidContainerSizeOf0() throws Exception {
		PrescriptionItem prescriptionItem = new PrescriptionItem("Medicine1", 1, 1, 0, false, "Comment");
	}

	@Test(expected = Exception.class)
	public void AddingItemWithInvalidContainerSizeOfNegativeNumber() throws Exception {
		PrescriptionItem prescriptionItem = new PrescriptionItem("Medicine1", 1, 1, -1, false, "Comment");
	}

}
