package pharmacyApplication;

import java.util.ArrayList;
import java.util.Iterator;

public class Prescription {
	private ArrayList<PrescriptionItem> prescriptionItems = new ArrayList<PrescriptionItem>();
	private int numberOfPharmaceuticals;
	private int numberOfContainers;

	public Prescription() {

	}

	public void addPrescriptionItem(String pharmaceuticalName, int prescribedDailyDose, int duration,
			int containerSize, boolean availableOverTheCounter, String comments) {
		PrescriptionItem prescriptionItem = new PrescriptionItem(pharmaceuticalName, prescribedDailyDose, duration, containerSize, availableOverTheCounter, comments);
		prescriptionItems.add(prescriptionItem);
	}

	public void removePrescriptionItem(String pharmaceuticalName) {
		Iterator<PrescriptionItem> it = prescriptionItems.iterator();
		while (it.hasNext()) {
			if(pharmaceuticalName == it.next().getPharmaceuticalName()) {
				it.remove();
				break;
			}
		}
	}

	public void clearPrescription() {
		Iterator<PrescriptionItem> it = prescriptionItems.iterator();
		while (it.hasNext()) {
			it.next();
			it.remove();
		}
	}

	public ArrayList<PrescriptionItem> getPrescriptionItems() {
		return prescriptionItems;
	}

	public int getNumberOfPharmaceuticals() {
		return numberOfPharmaceuticals;
	}

	public void setNumberOfPharmaceuticals(int numberOfPharmaceuticals) {
		this.numberOfPharmaceuticals = numberOfPharmaceuticals;
	}

	public int getNumberOfContainers() {
		int count = 0;
		for(PrescriptionItem prescriptionItem : prescriptionItems) {
			System.out.println(prescriptionItem.getNumberOfContainers());
			count += prescriptionItem.getNumberOfContainers();
		}
		return count;
	}

	public void setNumberOfContainers(int numberOfContainers) {
		this.numberOfContainers = numberOfContainers;
	}
}
