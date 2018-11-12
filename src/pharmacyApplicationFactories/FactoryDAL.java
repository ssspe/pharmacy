package pharmacyApplicationFactories;

import pharmacyApplication.DAL;
import pharmacyApplication.InterfaceDAL;

public class FactoryDAL {
	private static DAL dependency = new DAL();
	
	public static void setInstance(DAL fakeDependency){
		dependency = fakeDependency;
	}
	
	public static DAL create() {
		return dependency;
	}
}
