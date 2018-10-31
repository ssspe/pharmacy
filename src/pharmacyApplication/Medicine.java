package pharmacyApplication;

public class Medicine {
	private int size;
	private int recDailyDose;
	private String containerType;
	private String description;
	private int availableOverTheCounter;
	private int storeInFridge;
	
	public Medicine(int size, int recDailyDose, String containerType, String description, int availableOverTheCounter,
			int storeInFridge) {
		this.size = size;
		this.recDailyDose = recDailyDose;
		this.containerType = containerType;
		this.description = description;
		this.availableOverTheCounter = availableOverTheCounter;
		this.storeInFridge = storeInFridge;
	}

	public int getSize() {
		return size;
	}

	public int getRecDailyDose() {
		return recDailyDose;
	}

	public String getContainerType() {
		return containerType;
	}

	public String getDescription() {
		return description;
	}

	public int getAvailableOverTheCounter() {
		return availableOverTheCounter;
	}

	public int getStoreInFridge() {
		return storeInFridge;
	}
}
