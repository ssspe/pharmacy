package pharmacyApplication;

public interface InterfacePrescription {
	public void addPrescriptionItem(String pharmaceuticalName, int prescribedDailyDose, int duration, int containerSize,
			boolean availableOverTheCounter, String comments);
	public void removePrescriptionItem(String pharmaceuticalName);
	public void clearPrescription();
}
