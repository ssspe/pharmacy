package pharmacyApplication;

import java.util.ArrayList;
import java.util.Iterator;

public class Prescription implements InterfacePrescription {
	private static class FactoryHelper {
		PrescriptionItem create(String pharmaceuticalName, int prescribedDailyDose, int duration,
				int containerSize, boolean availableOverTheCounter, String comments) {
			return new PrescriptionItem(pharmaceuticalName, prescribedDailyDose, duration, containerSize,
					availableOverTheCounter, comments);
		}
	}

	private ArrayList<PrescriptionItem> prescriptionItems = new ArrayList<PrescriptionItem>();
	private FactoryHelper helper;

	public Prescription() {
		this(new FactoryHelper());
	}

	public Prescription(FactoryHelper helper) {
		this.helper = helper;
	}

	public void addPrescriptionItem(String pharmaceuticalName, int prescribedDailyDose, int duration, int containerSize,
			boolean availableOverTheCounter, String comments) {
		Boolean alreadyExists = false;
		for (PrescriptionItem prescriptionItem : prescriptionItems) {
			if (prescriptionItem.getPharmaceuticalName() == pharmaceuticalName) {
				alreadyExists = true;
				prescriptionItem.setDuration(prescriptionItem.getDuration() + duration);
				prescriptionItem.setPrescribedDailyDose(prescriptionItem.getPrescribedDailyDose() > prescribedDailyDose
						? prescriptionItem.getPrescribedDailyDose()
						: prescribedDailyDose);
			}
		}
		if (!alreadyExists) {

			PrescriptionItem prescriptionItem = helper.create(pharmaceuticalName, prescribedDailyDose, duration,
					containerSize, availableOverTheCounter, comments);
			prescriptionItems.add(prescriptionItem);
		}
	}

	public void removePrescriptionItem(String pharmaceuticalName) {
		Iterator<PrescriptionItem> it = prescriptionItems.iterator();
		while (it.hasNext()) {
			if (pharmaceuticalName == it.next().getPharmaceuticalName()) {
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
		return prescriptionItems.size();
	}

	public int getNumberOfContainers() {
		int count = 0;
		for (PrescriptionItem prescriptionItem : prescriptionItems) {
			count += prescriptionItem.getNumberOfContainers();
		}
		return count;
	}
}
