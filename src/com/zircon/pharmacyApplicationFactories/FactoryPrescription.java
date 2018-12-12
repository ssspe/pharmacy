package com.zircon.pharmacyApplicationFactories;

import com.zircon.pharmacyApplication.Prescription;

public class FactoryPrescription {
	private static Prescription dependency = new Prescription();
	
	public static void setInstance(Prescription fakeDependency){
		dependency = fakeDependency;
	}
	
	public static Prescription create() {
		return dependency;
	}

}
