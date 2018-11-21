package pharmacyApplication;

import java.util.ArrayList;
import java.util.List;

public interface InterfacePrescription {
	public void addPrescriptionItem(String pharmaceuticalName, int prescribedDailyDose, int duration, int containerSize,
			boolean availableOverTheCounter, String comments, int medicationMultiplier);
	public void removePrescriptionItem(String pharmaceuticalName);
	public void clearPrescription();
	public List<PrescriptionItem> getPrescriptionItems();
	public int getNumberOfPharmaceuticals();
	public int getNumberOfContainers();
}
