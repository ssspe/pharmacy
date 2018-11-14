package pharmacyApplicationTest.PrescriptionTests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import pharmacyApplication.Prescription;
import pharmacyApplication.PrescriptionItem;

public class Requirement_1_1_9 {
	Prescription prescription;

	@Before
	public void setUp() throws Exception {
		prescription = new Prescription();
	}

	@Test
	public void AddPrescriptionWithZeroDuration() {
		prescription.addPrescriptionItem("Medicine1", 1, 0, 1, false, "Comment");
		List<PrescriptionItem> lPrescriptionItems = prescription.getPrescriptionItems();
		assertEquals(lPrescriptionItems.size(), 0);
	}
}
