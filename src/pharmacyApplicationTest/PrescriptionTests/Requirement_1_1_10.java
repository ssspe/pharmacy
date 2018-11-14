package pharmacyApplicationTest.PrescriptionTests;

import static org.junit.Assert.assertEquals;
import static pharmacyApplicationTest.PrescriptionTests.HelperFunctions.checkMedicine;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import pharmacyApplication.Prescription;
import pharmacyApplication.PrescriptionItem;

public class Requirement_1_1_10 {
	Prescription prescription;

	@Before
	public void setUp() throws Exception {
		prescription = new Prescription();
	}

	@Test
	public void AddingTheSamePrescriptionItemIncreasesDuration() {
		prescription.addPrescriptionItem("Medicine1", 1, 1, 1, false, "Comment");
		prescription.addPrescriptionItem("Medicine1", 1, 1, 1, false, "Comment");
		List<PrescriptionItem> lPrescriptionItems = prescription.getPrescriptionItems();
		assertEquals(lPrescriptionItems.size(), 1);
		assertEquals(lPrescriptionItems.get(0).getDuration(), 2);
	}
	
	@Test
	public void AddingTheSamePrescriptionItemWithDifferentDurationIncreasesDuration() {
		prescription.addPrescriptionItem("Medicine1", 1, 2, 1, false, "Comment");
		prescription.addPrescriptionItem("Medicine1", 1, 3, 1, false, "Comment");
		prescription.addPrescriptionItem("Medicine1", 1, 4, 1, false, "Comment");
		List<PrescriptionItem> lPrescriptionItems = prescription.getPrescriptionItems();
		assertEquals(lPrescriptionItems.size(), 1);
		assertEquals(lPrescriptionItems.get(0).getDuration(), 9);
	}
	
	@Test
	public void AddingTheSamePrescriptionItemWithZeroDurationKeepsDuration() {
		prescription.addPrescriptionItem("Medicine1", 1, 1, 1, false, "Comment");
		prescription.addPrescriptionItem("Medicine1", 1, 0, 1, false, "Comment");
		List<PrescriptionItem> lPrescriptionItems = prescription.getPrescriptionItems();
		assertEquals(lPrescriptionItems.size(), 1);
		assertEquals(lPrescriptionItems.get(0).getDuration(), 1);
	}
	
	@Test
	public void AddingTheSamePrescriptionItemWithNegativeDurationKeepsDuration() {
		prescription.addPrescriptionItem("Medicine1", 1, 1, 1, false, "Comment");
		prescription.addPrescriptionItem("Medicine1", 1, -1, 1, false, "Comment");
		List<PrescriptionItem> lPrescriptionItems = prescription.getPrescriptionItems();
		assertEquals(lPrescriptionItems.size(), 1);
		assertEquals(lPrescriptionItems.get(0).getDuration(), 1);
	}
}
