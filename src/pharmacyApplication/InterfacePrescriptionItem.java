package pharmacyApplication;

public interface InterfacePrescriptionItem {
	public String getPharmaceuticalName();
	public int getPrescribedDailyDose();
	public void setPrescribedDailyDose(int prescribedDailyDose);
	public int getDuration();
	public void setDuration(int duration);
	public boolean isAvailableOverTheCounter();
	public String getComments();
	public int getNumberOfContainers();
}
