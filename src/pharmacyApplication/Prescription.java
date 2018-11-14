package pharmacyApplication;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import pharmacyApplicationFactories.FactoryPrescriptionItem;

/**
 * Class to hold the current prescription and calculate the number of items and
 * containers in the prescription.
 * 
 * @author spencer.robertson
 */
public class Prescription implements InterfacePrescription {
	private List<PrescriptionItem> prescriptionItems = new ArrayList<PrescriptionItem>();
	private FactoryPrescriptionItem helper;

	/**
	 * Default constructor that uses the real prescription item class.
	 */
	public Prescription() {
		this(new FactoryPrescriptionItem());
	}

	/**
	 * Second constructor that allows dependency injection of the prescription item.
	 * 
	 * @param helper Injected factory helper.
	 */
	public Prescription(FactoryPrescriptionItem helper) {
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
				prescriptionItem.adjustDuration(duration);
				prescriptionItem.adjustPrescribedDailyDose(prescribedDailyDose);

				// Updating the comments if the user updates the prescription
				prescriptionItem.setComment(comments);
				return;
			}
		}

		if (duration > 0) {
			PrescriptionItem prescriptionItem;
			try {
				prescriptionItem = helper.create(pharmaceuticalName, prescribedDailyDose, duration,
						containerSize, availableOverTheCounter, comments);
				prescriptionItems.add(prescriptionItem);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
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
