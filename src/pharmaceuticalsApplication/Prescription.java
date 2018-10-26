package pharmaceuticalsApplication;

import java.util.ArrayList;
import java.util.Iterator;

public class Prescription {
	private ArrayList<PrescriptionItem> prescriptionItems;
	private int numberOfPharmaceuticals;
	private int numberOfContainers;

	public Prescription() {

	}

	public void addPrescriptionItem(String pharmaceuticalName, int prescribedDailyDose, int duration,
			int containerSize, boolean availableOverTheCounter, String comments) {
		int recommendedDailyDose = 1;
		PrescriptionItem prescriptionItem = new PrescriptionItem(pharmaceuticalName, recommendedDailyDose, duration, containerSize, availableOverTheCounter, comments);
		prescriptionItem.setPrescribedDailyDose(prescribedDailyDose);
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
			it.remove();
			break;
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
		return numberOfContainers;
	}

	public void setNumberOfContainers(int numberOfContainers) {
		this.numberOfContainers = numberOfContainers;
	}
}
