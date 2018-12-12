package com.zircon.pharmacyApplicationFactories;

import com.zircon.pharmacyApplication.DAL;
import com.zircon.pharmacyApplicationInterfaces.InterfaceDAL;

public class FactoryDAL {
	private static DAL dependency = new DAL();
	
	public static void setInstance(DAL fakeDependency){
		dependency = fakeDependency;
	}
	
	public static DAL create() {
		return dependency;
	}
}
