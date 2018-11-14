package pharmacyApplicationTest.PrescriptionItem;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pharmacyApplication.PrescriptionItem;

public class Requirement_1_2_7 {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void AddingItemWithValidCommentMadeOfChars() throws Exception {
		PrescriptionItem prescriptionItem = new PrescriptionItem("Medicine1", 1, 1, 1, false, "Test Comment");
		assertEquals("Test Comment", prescriptionItem.getComments());
	}

	@Test
	public void AddingItemWithValidCommentMadeOfIntegers() throws Exception {
		PrescriptionItem prescriptionItem = new PrescriptionItem("Medicine1", 1, 1, 1, false, "123");
		assertEquals("123", prescriptionItem.getComments());
	}

	@Test(expected = Exception.class)
	public void AddingItemWithInvalidCommentMadeOfEmptyString() throws Exception {
		PrescriptionItem prescriptionItem = new PrescriptionItem("Medicine1", 1, 1, 1, false, "");
	}

}
