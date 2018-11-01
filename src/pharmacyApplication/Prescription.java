package pharmacyApplication;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Prescription implements InterfacePrescription {
	private static class FactoryHelper {
		PrescriptionItem create(String pharmaceuticalName, int prescribedDailyDose, int duration, int containerSize,
				boolean availableOverTheCounter, String comments) {
			return new PrescriptionItem(pharmaceuticalName, prescribedDailyDose, duration, containerSize,
					availableOverTheCounter, comments);
		}
	}

	private List<PrescriptionItem> prescriptionItems = new ArrayList<PrescriptionItem>();
	private FactoryHelper helper;

	public Prescription() {
		this(new FactoryHelper());
	}

	public Prescription(FactoryHelper helper) {
		this.helper = helper;
	}

	public void addPrescriptionItem(String pharmaceuticalName, int prescribedDailyDose, int duration, int containerSize,
			boolean availableOverTheCounter, String comments) {
		for (PrescriptionItem prescriptionItem : prescriptionItems) {
			if (prescriptionItem.getPharmaceuticalName() == pharmaceuticalName) {
				prescriptionItem.setDuration(prescriptionItem.getDuration() + duration);
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
		prescriptionItems.clear();
	}

	public List<PrescriptionItem> getPrescriptionItems() {
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
