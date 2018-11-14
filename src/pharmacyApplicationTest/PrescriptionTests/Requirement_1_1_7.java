package pharmacyApplicationTest.PrescriptionTests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import pharmacyApplication.Prescription;
import pharmacyApplication.PrescriptionItem;

public class Requirement_1_1_7 {
	Prescription prescription;

	@Before
	public void setUp() throws Exception {
		prescription = new Prescription();
	}

	@Test
	public void NumberOfPrescriptionsCorrectForOneItem() {
		prescription.addPrescriptionItem("Medicine1", 1, 1, 1, false, "Comment");
		assertEquals(prescription.getNumberOfPharmaceuticals(), 1);
		prescription.clearPrescription();
		assertEquals(prescription.getNumberOfPharmaceuticals(), 0);
	}

	@Test
	public void NumberOfPrescriptionsCorrectForTwoItems() {
		prescription.addPrescriptionItem("Medicine1", 1, 1, 1, false, "Comment");
		prescription.addPrescriptionItem("Medicine2", 2, 2, 2, true, "Comment2");
		assertEquals(prescription.getNumberOfPharmaceuticals(), 2);
		prescription.clearPrescription();
		assertEquals(prescription.getNumberOfPharmaceuticals(), 0);
	}
	
	@Test
	public void NumberOfPrescriptionsDefaultsTo0() {
		assertEquals(prescription.getNumberOfPharmaceuticals(), 0);
	}

}
