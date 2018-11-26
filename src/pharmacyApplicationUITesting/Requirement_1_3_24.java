package pharmacyApplicationUITesting;

import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.*;
import static pharmacyApplicationUITesting.HelperFunctions.closeUI;
import static pharmacyApplicationUITesting.HelperFunctions.setUpUI;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sikuli.script.Key;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import pharmacyApplication.DAL;
import pharmacyApplication.Medicine;
import pharmacyApplication.Prescription;
import pharmacyApplication.PrescriptionItem;
import pharmacyApplicationFactories.FactoryDAL;
import pharmacyApplicationFactories.FactoryPrescription;

public class Requirement_1_3_24 {

	private Screen screen;
	private DAL mockDependency;
	private Prescription mockDependencyPre;
	
	@Before
	public void setUp() throws Exception {
		mockDependency = createMock(DAL.class);
		FactoryDAL.setInstance(mockDependency);
		mockDependency.connect("", "", "");
		expectLastCall();
		
		expect(mockDependency.getPharmaName()).andReturn(Arrays.asList("Medicine1"));
		
		expect(mockDependency.getPharmaInfo(anyObject())).andReturn(new Medicine(1, 5, "Bottle", "T", "Comment", 0, 0));
		replay(mockDependency);
		
		mockDependencyPre = createMock(Prescription.class);
		FactoryPrescription.setInstance(mockDependencyPre);
		
		setUpUI();
		screen = new Screen();
		assertTrue(screen.exists("imgs/add-comment-default.png") != null);
	}
	
	@Test
	public void Should_DecrementDosageByOne_WhenDecrementDosgaeIsClicked() throws Exception {
		expect(mockDependencyPre.getNumberOfPharmaceuticals()).andReturn(1).anyTimes();
		expect(mockDependencyPre.getNumberOfContainers()).andReturn(1).anyTimes();
		
		mockDependencyPre.addPrescriptionItem("Medicine1", 1, 1, 1, false, "Comment; Comes in a 1ml Bottle", 1);
		expectLastCall();
		expect(mockDependencyPre.getPrescriptionItems()).andReturn(
				Arrays.asList(new PrescriptionItem("Medicine1", 2, 1, 1, false, "Comment; Comes in a 1ml Bottle", 1)));
		replay(mockDependencyPre);
		
		screen.click("imgs/add-button-active.png");
		
		Pattern pattern = new Pattern("imgs/table-one-item.png");
		screen.rightClick(pattern.targetOffset(0, -30));
		
		screen.mouseMove(100, 100);
		pattern = new Pattern("imgs/decrement-dosage-disabled.png");
		screen.click(pattern);
		
		pattern = new Pattern("imgs/table-one-item.png");
		assertTrue(screen.exists(pattern) != null);
	}
	
	@Test
	public void Should_DecrementDosageByFour_WhenDecrementDosgaeIsClickedFourTimes() throws Exception {
		expect(mockDependencyPre.getNumberOfPharmaceuticals()).andReturn(1).anyTimes();
		expect(mockDependencyPre.getNumberOfContainers()).andReturn(1).anyTimes();
		
		mockDependencyPre.addPrescriptionItem("Medicine1", 1, 1, 1, false, "Comment; Comes in a 1ml Bottle", 1);
		expectLastCall();
		expect(mockDependencyPre.getPrescriptionItems()).andReturn(
				Arrays.asList(new PrescriptionItem("Medicine1", 5, 1, 1, false, "Comment; Comes in a 1ml Bottle", 1)));
		replay(mockDependencyPre);
		
		screen.click("imgs/add-button-active.png");
		
		for(int i = 0; i < 4; i++) {
			Pattern pattern = new Pattern("imgs/table-one-item.png").similar(0.5f);
			screen.rightClick(pattern.targetOffset(0, -30));
			
			screen.mouseMove(100, 100);
			pattern = new Pattern("imgs/decrement-dosage-disabled.png");
			screen.click(pattern);
		}
		
		Pattern pattern = new Pattern("imgs/table-one-item.png");
		assertTrue(screen.exists(pattern) != null);
	}
	
	
	@After
	public void tearDown() {
		verify(mockDependency);
		verify(mockDependencyPre);
		closeUI();
	}

}
