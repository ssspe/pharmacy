package com.zircon.pharmacyApplicationTest.UI;

import static com.zircon.pharmacyApplicationTest.HelperFunctions.closeUI;
import static com.zircon.pharmacyApplicationTest.HelperFunctions.setUpUI;
import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import com.zircon.pharmacyApplication.DAL;
import com.zircon.pharmacyApplication.Medicine;
import com.zircon.pharmacyApplicationFactories.FactoryDAL;

public class Requirement_1_3_2 {
	private DAL mockDependency;
	private Screen screen;

	@Before
	public void setUp() throws Exception {
		mockDependency = createMock(DAL.class);
		FactoryDAL.setInstance(mockDependency);
		mockDependency.connect("", "", "");
		expectLastCall();
		expect(mockDependency.getPharmaName()).andReturn(Arrays.asList("Medicine1", "Medicine2", "Medicine3"));
		expect(mockDependency.getPharmaInfo(anyObject())).andReturn(new Medicine(0, 5, "Bottle", "T", "Comment", 0, 0))
				.anyTimes();
		replay(mockDependency);
		setUpUI();
		screen = new Screen();
	}

	@Test
	public void Should_DisplayPharmaceuticalName_When_SelectMedicineFromDropDown() throws Exception {
		assertTrue(screen.exists(new Pattern("imgs/medicine-one")) != null);
		Pattern pattern = new Pattern("imgs/combo-box");
		screen.click(pattern);
		screen.click(pattern.targetOffset(0, 40));
		assertTrue(screen.exists(new Pattern("imgs/medicine-two")) != null);
		pattern = new Pattern("imgs/combo-box");
		screen.click(pattern);
		screen.click(pattern.targetOffset(0, 60));
		assertTrue(screen.exists(new Pattern("imgs/medicine-three")) != null);
	}
	
	@After
	public void tearDown() {
		verify(mockDependency);
		closeUI();
	}
}
