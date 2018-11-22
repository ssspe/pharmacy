package pharmacyApplicationTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import pharmacyApplicationTest.PrescriptionItem.AllPrescriptionItemTests;
import pharmacyApplicationTest.PrescriptionTests.AllPrescriptionTests;
import pharmacyApplicationUITesting.AllUITests;

@RunWith(Suite.class)
@SuiteClasses({ AllPrescriptionItemTests.class, AllPrescriptionTests.class, AllUITests.class })
public class AllTests {

}
