package com.zircon.pharmacyApplicationTest.UI;

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
import org.sikuli.script.Screen;

import com.zircon.pharmacyApplication.DAL;
import com.zircon.pharmacyApplication.Medicine;
import com.zircon.pharmacyApplicationFactories.FactoryDAL;
import static com.zircon.pharmacyApplicationTest.HelperFunctions.setUpUI;
import static com.zircon.pharmacyApplicationTest.HelperFunctions.closeUI;

public class Requirement_1_3_1 {
	private DAL mockDependency;
	private Screen screen;
	
	@Before
	public void setUp() throws Exception {
		System.out.println("Working Directory = " +
	              System.getProperty("user.dir"));
		mockDependency = createMock(DAL.class);
		FactoryDAL.setInstance(mockDependency);
		mockDependency.connect("", "", "");
		expectLastCall();
		screen = new Screen();
	}

	@Test
	public void Should_DisplayOnePharmaceuticalName_When_ClickDropDown() throws Exception {
		expect(mockDependency.getPharmaName()).andReturn(Arrays.asList("Medicine1"));
		expect(mockDependency.getPharmaInfo(anyObject())).andReturn(new Medicine(0, 5, "Bottle", "T", "Comment", 0, 0));
		replay(mockDependency);
		setUpUI();
		screen.click("imgs/combo-box");
		assertTrue(screen.exists("imgs/combo-box-one-item.png") != null);
	}
	
	@Test
	public void Should_DisplayTwoPharmaceuticalNames_When_ClickDropDown() throws Exception {
		expect(mockDependency.getPharmaName()).andReturn(Arrays.asList("Medicine1", "Medicine2"));
		expect(mockDependency.getPharmaInfo(anyObject())).andReturn(new Medicine(0, 5, "Bottle", "T", "Comment", 0, 0));
		replay(mockDependency);
		setUpUI();
		screen.click("imgs/combo-box");
		assertTrue(screen.exists("imgs/combo-box-two-items.png") != null);
	}
	
	@After
	public void tearDown() {
		verify(mockDependency);
		closeUI();
	}

}
