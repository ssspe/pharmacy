package pharmacyApplication;

/**
 * Class to hold information about specific medicines from the Database.
 * 
 * @author spencer.robertson
 */
public class Medicine {
	private int size;
	private int recDailyDose;
	private String containerType;
	private String medicationType;
	private String description;
	private int availableOverTheCounter;
	private int storeInFridge;

	/**
	 * Constructor for the Medicine object.
	 * 
	 * @param size                    Size of container
	 * @param recDailyDose            Recommended daily dosage
	 * @param containerType           Type of container (Box, Tube Phial...)
	 * @param description             Description and special requirements
	 * @param availableOverTheCounter Whether or not it is available over the
	 *                                counter
	 * @param storeInFridge           Whether or not it needs to be stored in a
	 *                                fridge
	 */
	public Medicine(int size, int recDailyDose, String containerType, String medicationType, String description, int availableOverTheCounter,
			int storeInFridge) {
		this.size = size;
		this.recDailyDose = recDailyDose;
		this.containerType = containerType;
		this.medicationType = medicationType;
		this.description = description;
		this.availableOverTheCounter = availableOverTheCounter;
		this.storeInFridge = storeInFridge;
	}
	
	/**
	 * @return The size of the container.
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * @return The recommended daily dosage.
	 */
	public int getRecDailyDose() {
		return recDailyDose;
	}
	
	/**
	 * @return The type of container.
	 */
	public String getContainerType() {
		return containerType;
	}
	
	/**
	 * @return The type of medication.
	 */
	public String getMedicationType() {
		return medicationType;
	}
	
	/**
	 * @return The description and special requirements.
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * @return Is it available over the counter.
	 */
	public int getAvailableOverTheCounter() {
		return availableOverTheCounter;
	}
	
	/**
	 * @return Does it need to be stored in a fridge.
	 */
	public int getStoreInFridge() {
		return storeInFridge;
	}
}
