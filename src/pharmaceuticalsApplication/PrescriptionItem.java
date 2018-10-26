package pharmaceuticalsApplication;

public class PrescriptionItem {
	private String pharmaceuticalName;
	private int prescribedDailyDose;
	private int duration;
	private int containerSize;
	private boolean availableOverTheCounter;
	private String comments;
	private int numberOfContainers;

	public PrescriptionItem(String pharmaceuticalName, int recommendedDailyDose, int duration, int containerSize,
			boolean availableOverTheCounter, String comments) {
		this.pharmaceuticalName = pharmaceuticalName;
		// TODO: recommendedDailyDose
		this.duration = duration;
		this.containerSize = containerSize;
		this.availableOverTheCounter = availableOverTheCounter;
		this.comments = comments;
	}

	public String getPharmaceuticalName() {
		return pharmaceuticalName;
	}

	public void setPharmaceuticalName(String pharmaceuticalName) {
		this.pharmaceuticalName = pharmaceuticalName;
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

	public int getContainerSize() {
		return containerSize;
	}

	public void setContainerSize(int containerSize) {
		this.containerSize = containerSize;
	}

	public boolean isAvailableOverTheCounter() {
		return availableOverTheCounter;
	}

	public void setAvailableOverTheCounter(boolean availableOverTheCounter) {
		this.availableOverTheCounter = availableOverTheCounter;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public int getNumberOfContainers() {
		// TODO: Figure this out
		numberOfContainers = containerSize + prescribedDailyDose + duration;
		return numberOfContainers;
	}

	public void adjustDuration(int additionalDays) {
		duration = duration + additionalDays;
	}
}