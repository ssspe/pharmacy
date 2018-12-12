package com.zircon.pharmacyApplicationTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.zircon.pharmacyApplicationTest.Prescription.AllPrescriptionTests;
import com.zircon.pharmacyApplicationTest.PrescriptionItem.AllPrescriptionItemTests;
import com.zircon.pharmacyApplicationTest.UI.AllUITests;

@RunWith(Suite.class)
@SuiteClasses({ AllPrescriptionItemTests.class, AllPrescriptionTests.class, AllUITests.class })
public class AllTests {

}
