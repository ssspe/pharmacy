package pharmacyApplication;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Class to hold the current prescription and calculate the number of items and
 * containers in the prescription.
 * 
 * @author spencer.robertson
 */
public class Prescription implements InterfacePrescription {
	/**
	 * Factory to create prescription item. Allows for easier Unit Testing.
	 * 
	 * @author spencer.robertson
	 */
	private static class FactoryHelper {

		/**
		 * Static function to create a prescription item.
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
		 */
		PrescriptionItem create(String pharmaceuticalName, int prescribedDailyDose, int duration, int containerSize,
				boolean availableOverTheCounter, String comments) {
			return new PrescriptionItem(pharmaceuticalName, prescribedDailyDose, duration, containerSize,
					availableOverTheCounter, comments);
		}
	}

	private List<PrescriptionItem> prescriptionItems = new ArrayList<PrescriptionItem>();
	private FactoryHelper helper;

	/**
	 * Default constructor that uses the real prescription item class.
	 */
	public Prescription() {
		this(new FactoryHelper());
	}

	/**
	 * Second constructor that allows dependency injection of the prescription item.
	 * 
	 * @param helper Injected factory helper.
	 */
	public Prescription(FactoryHelper helper) {
		this.helper = helper;
	}

	/**
	 * Adds a prescription item to the prescription list. If item already exists it
	 * will update the item with the new duration and prescribed daily dose.
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
	 */
	public void addPrescriptionItem(String pharmaceuticalName, int prescribedDailyDose, int duration, int containerSize,
			boolean availableOverTheCounter, String comments) {
		for (PrescriptionItem prescriptionItem : prescriptionItems) {
			if (prescriptionItem.getPharmaceuticalName() == pharmaceuticalName) {
				// Updating the duration by adding to the previous.
				prescriptionItem.setDuration(prescriptionItem.getDuration() + duration);

				/*
				 * Set the daily dose to either the old daily dose or the new daily dose
				 * depending on which one is higher
				 */
				prescriptionItem.setPrescribedDailyDose(prescriptionItem.getPrescribedDailyDose() > prescribedDailyDose
						? prescriptionItem.getPrescribedDailyDose()
						: prescribedDailyDose);
				return;
			}
		}

		PrescriptionItem prescriptionItem = helper.create(pharmaceuticalName, prescribedDailyDose, duration,
				containerSize, availableOverTheCounter, comments);
		prescriptionItems.add(prescriptionItem);
	}

	/**
	 * Removes a prescription item from the prescription list.
	 * 
	 * @param pharmaceuticalName the name of the pharmaceutical to remove.
	 */
	public void removePrescriptionItem(String pharmaceuticalName) {
		// Using an iterator as it is safer to remove items this way.
		Iterator<PrescriptionItem> it = prescriptionItems.iterator();
		while (it.hasNext()) {
			if (pharmaceuticalName == it.next().getPharmaceuticalName()) {
				it.remove();
				break;
			}
		}
	}

	/**
	 * Clears the current prescription list.
	 */
	public void clearPrescription() {
		prescriptionItems.clear();
	}

	/**
	 * @return (List<PrescriptionItem>) Current prescription list.
	 */
	public List<PrescriptionItem> getPrescriptionItems() {
		return prescriptionItems;
	}

	/**
	 * @return (int) Number of items in the prescription list.
	 */
	public int getNumberOfPharmaceuticals() {
		return prescriptionItems.size();
	}

	/**
	 * @return (int) Number of containers needed for the current prescription.
	 */
	public int getNumberOfContainers() {
		int count = 0;
		for (PrescriptionItem prescriptionItem : prescriptionItems) {
			count += prescriptionItem.getNumberOfContainers();
		}
		return count;
	}

}
