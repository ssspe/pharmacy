package pharmacyApplicationFactories;

import pharmacyApplication.InterfacePrescription;
import pharmacyApplication.Prescription;

public class FactoryPrescription {
	private static InterfacePrescription dependency = new Prescription();
	
	public static void setInstance(InterfacePrescription fakeDependency){
		dependency = fakeDependency;
	}
	
	public static InterfacePrescription create() {
		return dependency;
	}

}
