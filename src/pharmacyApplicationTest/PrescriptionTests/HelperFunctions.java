package pharmacyApplicationTest.PrescriptionTests;

import static org.junit.Assert.assertEquals;

import pharmacyApplication.PrescriptionItem;

public class HelperFunctions {

	public static void checkMedicine(PrescriptionItem prescriptionItem, String pharmaName, int dailyDose, int duration,
			boolean overTheCounter, String comment) {
		assertEquals(prescriptionItem.getPharmaceuticalName(), pharmaName);
		assertEquals(prescriptionItem.getPrescribedDailyDose(), dailyDose);
		assertEquals(prescriptionItem.getDuration(), duration);
		assertEquals(prescriptionItem.isAvailableOverTheCounter(), overTheCounter);
		assertEquals(prescriptionItem.getComments(), comment);
	}
}