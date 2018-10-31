package pharmacyApplicationUITesting;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ PrescribedDailyDoseTests.class, DurationTests.class })
public class AllTests {

}
