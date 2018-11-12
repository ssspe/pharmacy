package pharmacyApplication;

public interface InterfacePrescriptionItem {
	public String getPharmaceuticalName();
	public int getPrescribedDailyDose();
	public void adjustPrescribedDailyDose(int prescribedDailyDose);
	public int getDuration();
	public void adjustDuration(int duration);
	public boolean isAvailableOverTheCounter();
	public String getComments();
	public int getNumberOfContainers();
}
