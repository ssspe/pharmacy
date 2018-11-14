package pharmacyApplicationFactories;

import pharmacyApplication.PrescriptionItem;

/**
 * Factory to create prescription item. Allows for easier Unit Testing.
 * 
 * @author spencer.robertson
 */
public class FactoryPrescriptionItem {

	public FactoryPrescriptionItem() {
		
	}
	
	/**
	 * 	 * Static function to create a prescription item.
	 * 
	 * @param pharmaceuticalName      The name of the prescription item.
	 * @param prescribedDailyDose     How much should be taken per day.
	 * @param duration                Number of days the prescription should be
	 *                                taken for.
	 * @param containerSize           Size of the container (Can be Bottle, Box,
	 *                                Tube or Phial).
	 * @param availableOverTheCounter Is the prescription item available over the
	 *                                counter
	 * @param comments                Special requirements and user comments.
	 * @return A new prescription item.
	 * @throws Exception 
	 */
	public PrescriptionItem create(String pharmaceuticalName, int prescribedDailyDose, int duration, int containerSize,
			boolean availableOverTheCounter, String comments) throws Exception {
		return new PrescriptionItem(pharmaceuticalName, prescribedDailyDose, duration, containerSize,
				availableOverTheCounter, comments);
	}
}
