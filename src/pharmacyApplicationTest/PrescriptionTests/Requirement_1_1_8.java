package pharmacyApplicationTest.PrescriptionTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pharmacyApplication.Prescription;

public class Requirement_1_1_8 {
	Prescription prescription;

	@Before
	public void setUp() throws Exception {
		prescription = new Prescription();
	}

	@Test
	public void NumberOfContainersCorrectForOneItem() {
		prescription.addPrescriptionItem("Medicine1", 1, 1, 10, false, "Comment", 1);
		assertEquals(prescription.getNumberOfContainers(), 1);
		prescription.clearPrescription();
		assertEquals(prescription.getNumberOfContainers(), 0);
	}

	@Test
	public void NumberOfContainersCorrectForTwoItems() {
		prescription.addPrescriptionItem("Medicine1", 1, 1, 10, false, "Comment", 1);
		prescription.addPrescriptionItem("Medicine2", 2, 2, 10, true, "Comment2", 1);
		assertEquals(prescription.getNumberOfContainers(), 2);
		prescription.clearPrescription();
		assertEquals(prescription.getNumberOfContainers(), 0);
	}

	@Test
	public void NumberOfContainersDefaultsTo0() {
		assertEquals(prescription.getNumberOfContainers(), 0);
	}
}
