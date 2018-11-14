package pharmacyApplicationTest.PrescriptionItem;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import pharmacyApplication.PrescriptionItem;

public class Requirement_1_2_8 {
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void AddingItemWithANotFullContainer() throws Exception {
		PrescriptionItem prescriptionItem = new PrescriptionItem("Medicine1", 1, 1, 10, false, "Comment");
		assertEquals(1, prescriptionItem.getNumberOfContainers());
	}

	@Test
	public void AddingItemWithAFullContainer() throws Exception {
		PrescriptionItem prescriptionItem = new PrescriptionItem("Medicine1", 1, 10, 10, false, "Comment");
		assertEquals(1, prescriptionItem.getNumberOfContainers());
	}
	
	@Test
	public void AddingItemWithAnOverFullContainer() throws Exception {
		PrescriptionItem prescriptionItem = new PrescriptionItem("Medicine1", 1, 11, 10, false, "Comment");
		assertEquals(2, prescriptionItem.getNumberOfContainers());
	}
	
	@Test
	public void AddingItemWithLargeNumberOfContainers() throws Exception {
		PrescriptionItem prescriptionItem = new PrescriptionItem("Medicine1", 12, 5, 5, false, "Comment");
		assertEquals(12, prescriptionItem.getNumberOfContainers());
	}
}
