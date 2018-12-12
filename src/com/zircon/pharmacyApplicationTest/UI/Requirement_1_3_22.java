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
import org.sikuli.script.Key;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import com.zircon.pharmacyApplication.DAL;
import com.zircon.pharmacyApplication.Medicine;
import com.zircon.pharmacyApplication.Prescription;
import com.zircon.pharmacyApplication.PrescriptionItem;
import com.zircon.pharmacyApplicationFactories.FactoryDAL;
import com.zircon.pharmacyApplicationFactories.FactoryPrescription;

public class Requirement_1_3_22 {

	private Screen screen;
	private DAL mockDependency;
	private Prescription mockDependencyPre;
	
	@Before
	public void setUp() throws Exception {
		mockDependency = createMock(DAL.class);
		FactoryDAL.setInstance(mockDependency);
		mockDependency.connect("", "", "");
		expectLastCall();
		
		expect(mockDependency.getPharmaName()).andReturn(Arrays.asList("Medicine1", "Medicine2"));
		
		expect(mockDependency.getPharmaInfo(anyObject())).andReturn(new Medicine(1, 5, "Bottle", "T", "Comment", 0, 0)).anyTimes();
		replay(mockDependency);
		
		mockDependencyPre = createMock(Prescription.class);
		FactoryPrescription.setInstance(mockDependencyPre);
		
		setUpUI();
		screen = new Screen();
		assertTrue(screen.exists("imgs/add-comment-default.png") != null);
	}
	
	@After
	public void tearDown() {
		verify(mockDependency);
		verify(mockDependencyPre);
		closeUI();
	}
	
	@Test
	public void Should_DisplayContextMenu_When_RightClickingRow() throws Exception {
		expect(mockDependencyPre.getNumberOfPharmaceuticals()).andReturn(1).anyTimes();
		expect(mockDependencyPre.getNumberOfContainers()).andReturn(1).anyTimes();
		
		mockDependencyPre.addPrescriptionItem("Medicine1", 1, 1, 1, false, "Comment; Comes in a 1ml Bottle", 1);
		expectLastCall();
		expect(mockDependencyPre.getPrescriptionItems()).andReturn(
				Arrays.asList(new PrescriptionItem("Medicine1", 1, 1, 1, false, "Comment; Comes in a 1ml Bottle", 1)));
		replay(mockDependencyPre);
		
		screen.click("imgs/add-button-active.png");
		
		Pattern pattern = new Pattern("imgs/table-one-item.png");
		screen.rightClick(pattern.targetOffset(0, -30));
		
		screen.mouseMove(100, 100);
		assertTrue(screen.exists("imgs/context-menu.png") != null);
	}
	
	@Test
	public void Should_DisplayContextMenuOnSelectedRow_When_RightClickingRow() throws Exception {
		expect(mockDependencyPre.getNumberOfPharmaceuticals()).andReturn(1).anyTimes();
		expect(mockDependencyPre.getNumberOfContainers()).andReturn(1).anyTimes();
		
		mockDependencyPre.addPrescriptionItem("Medicine1", 1, 1, 1, false, "Comment; Comes in a 1ml Bottle", 1);
		expectLastCall();
		mockDependencyPre.addPrescriptionItem("Medicine2", 1, 1, 1, false, "Comment; Comes in a 1ml Bottle", 1);
		expectLastCall();
		expect(mockDependencyPre.getPrescriptionItems()).andReturn(
				Arrays.asList(new PrescriptionItem("Medicine1", 1, 1, 1, false, "Comment; Comes in a 1ml Bottle", 1)));
		expect(mockDependencyPre.getPrescriptionItems()).andReturn(
				Arrays.asList(new PrescriptionItem("Medicine1", 1, 1, 1, false, "Comment; Comes in a 1ml Bottle", 1),
						new PrescriptionItem("Medicine2", 1, 1, 1, false, "Comment; Comes in a 1ml Bottle", 1)));
		replay(mockDependencyPre);
		
		screen.click("imgs/add-button-active.png");
		
		Pattern pattern = new Pattern("imgs/combo-box");
		screen.click(pattern);
		screen.click(pattern.targetOffset(0, 40));
		
		screen.click("imgs/add-button-active.png");
		
		pattern = new Pattern("imgs/table-one-item.png");
		screen.rightClick(pattern.targetOffset(0, -10));
		
		screen.mouseMove(100, 100);
		assertTrue(screen.exists("imgs/context-menu.png") != null);
	}
}
