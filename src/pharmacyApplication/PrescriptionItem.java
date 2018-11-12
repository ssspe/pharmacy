package pharmacyApplication;

/**
 * Class to define a prescription item.
 * 
 * @author spencer.robertson
 */
public class PrescriptionItem implements InterfacePrescriptionItem {
	private String pharmaceuticalName;
	private int prescribedDailyDose;
	private int duration;
	private int containerSize;
	private boolean availableOverTheCounter;
	private String comments;
	private int numberOfContainers;

	/**
	 * Constructor for a prescription item object.
	 * 
	 * @param pharmaceuticalName      The name of the prescription item.
	 * @param prescribedDailyDose     How much should be taken per day.
	 * @param duration                Number of days the prescription should be
	 *                                taken for.
	 * @param containerSize           Size of the container (Can be Bottle, Box,
	 *                                Tube or Phial).
	 * @param availableOverTheCounter Is the prescription item available over the
	 *                                counter
	 * @param comments                Special requirements and user comments.
	 */
	public PrescriptionItem(String pharmaceuticalName, int prescribedDailyDose, int duration, int containerSize,
			boolean availableOverTheCounter, String comments) {
		this.pharmaceuticalName = pharmaceuticalName;
		this.prescribedDailyDose = prescribedDailyDose;
		this.duration = duration;
		this.containerSize = containerSize;
		this.availableOverTheCounter = availableOverTheCounter;
		this.comments = comments;
	}

	/**
	 * @param comments Special requirements and comments.
	 */
	public void setComment(String comments) {
		this.comments = comments;
	}

	/**
	 * @return (String) Special requirements and comments.
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @return (String) Name of prescription item.
	 */
	public String getPharmaceuticalName() {
		return pharmaceuticalName;
	}

	/**
	 * @return (int) Prescribed daily dose.
	 */
	public int getPrescribedDailyDose() {
		return prescribedDailyDose;
	}

	/**
	 * @param prescribedDailyDose The prescribed daily dose.
	 */
	public void adjustPrescribedDailyDose(int prescribedDailyDose) {
		if ( this.prescribedDailyDose < prescribedDailyDose) {
			this.prescribedDailyDose = prescribedDailyDose;
		}	
	}

	/**
	 * @return (int) The number of days the prescription should be taken for.
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * @param duration The number of days the prescription should be taken for.
	 */
	public void adjustDuration(int duration) {
		if (duration >= 1) {
			this.duration += duration;
		}
	}

	/**
	 * @return (boolean) If the prescription item is available over the counter.
	 */
	public boolean isAvailableOverTheCounter() {
		return availableOverTheCounter;
	}

	/**
	 * @param (int) Number of containers.
	 */
	public int getNumberOfContainers() {
		numberOfContainers = (int) Math.ceil((float) (prescribedDailyDose * duration) / (float) containerSize);
		return numberOfContainers;
	}
}