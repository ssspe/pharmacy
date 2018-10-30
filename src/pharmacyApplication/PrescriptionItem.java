package pharmacyApplication;

public class PrescriptionItem {
	private String pharmaceuticalName;
	private int prescribedDailyDose;
	private int duration;
	private int containerSize;
	private boolean availableOverTheCounter;
	private String comments;
	private int numberOfContainers;

	public PrescriptionItem(String pharmaceuticalName, int prescribedDailyDose, int duration, int containerSize,
			boolean availableOverTheCounter, String comments) {
		this.pharmaceuticalName = pharmaceuticalName;
		this.prescribedDailyDose = prescribedDailyDose;
		this.duration = duration;
		this.containerSize = containerSize;
		this.availableOverTheCounter = availableOverTheCounter;
		this.comments = comments;
	}

	public String getPharmaceuticalName() {
		return pharmaceuticalName;
	}

	public int getPrescribedDailyDose() {
		return prescribedDailyDose;
	}

	public void setPrescribedDailyDose(int prescribedDailyDose) {
		this.prescribedDailyDose = prescribedDailyDose;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {

		this.duration = duration;
	}

	public boolean isAvailableOverTheCounter() {
		return availableOverTheCounter;
	}

	public String getComments() {
		return comments;
	}

	public int getNumberOfContainers() {
		numberOfContainers = (int) Math.ceil((float) (prescribedDailyDose * duration) / (float) containerSize);
		return numberOfContainers;
	}
}