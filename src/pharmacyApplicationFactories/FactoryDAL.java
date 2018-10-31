package pharmacyApplicationFactories;

import pharmacyApplication.DAL;
import pharmacyApplication.InterfaceDAL;

public class FactoryDAL {
	private static InterfaceDAL dependency = new DAL();
	
	public static void setInstance(InterfaceDAL fakeDependency){
		dependency = fakeDependency;
	}
	
	public static InterfaceDAL create() {
		return dependency;
	}
}
