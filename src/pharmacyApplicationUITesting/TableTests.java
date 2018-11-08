package pharmacyApplicationUITesting;

import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.*;
import static pharmacyApplicationUITesting.HelperFunctions.setUpUI;
import static pharmacyApplicationUITesting.HelperFunctions.closeUI;

import java.awt.font.NumericShaper.Range;
import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.KeyModifier;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import pharmacyApplication.InterfaceDAL;
import pharmacyApplication.InterfacePrescription;
import pharmacyApplication.Medicine;
import pharmacyApplication.PrescriptionItem;
import pharmacyApplicationFactories.FactoryDAL;
import pharmacyApplicationFactories.FactoryPrescription;
import sun.java2d.ScreenUpdateManager;

public class TableTests {
	private Screen screen;
	private InterfaceDAL mockDependency;
	private InterfacePrescription mockDependencyPre;
	
	@Before
	public void setUp() throws Exception {
		mockDependency = createMock(InterfaceDAL.class);
		FactoryDAL.setInstance(mockDependency);
		mockDependency.connect("", "", "");
		expectLastCall();
		
		expect(mockDependency.getPharmaName()).andReturn(Arrays.asList("Medicine1"));
		
		expect(mockDependency.getPharmaInfo(anyObject())).andReturn(new Medicine(1, 5, "Bottle", "Comment", 0, 0));
		replay(mockDependency);
		
		mockDependencyPre = createMock(InterfacePrescription.class);
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
	public void Right_Clicking_Row_Displays_Context_Menu() throws FindFailed, InterruptedException {
		expect(mockDependencyPre.getNumberOfPharmaceuticals()).andReturn(1).anyTimes();
		expect(mockDependencyPre.getNumberOfContainers()).andReturn(1).anyTimes();
		
		mockDependencyPre.addPrescriptionItem("Medicine1", 1, 1, 1, false, "Comment; Comes in a 1ml Bottle");
		expectLastCall();
		expect(mockDependencyPre.getPrescriptionItems()).andReturn(
				Arrays.asList(new PrescriptionItem("Medicine1", 1, 1, 1, false, "Comment; Comes in a 1ml Bottle")));
		replay(mockDependencyPre);
		
		screen.click("imgs/add-button-active.png");
		
		Pattern pattern = new Pattern("imgs/table-one-item.png");
		screen.rightClick(pattern.targetOffset(0, -30));
		
		screen.mouseMove(100, 100);
		assertTrue(screen.exists("imgs/context-menu.png") != null);
	}
	
	@Test
	public void Edit_Comment_Button_Displays_Dialogue_Of_Existing_Comment() throws FindFailed, InterruptedException {
		expect(mockDependencyPre.getNumberOfPharmaceuticals()).andReturn(1).anyTimes();
		expect(mockDependencyPre.getNumberOfContainers()).andReturn(1).anyTimes();
		
		mockDependencyPre.addPrescriptionItem("Medicine1", 1, 1, 1, false, "Comment; Comes in a 1ml Bottle");
		expectLastCall();
		expect(mockDependencyPre.getPrescriptionItems()).andReturn(
				Arrays.asList(new PrescriptionItem("Medicine1", 1, 1, 1, false, "Comment; Comes in a 1ml Bottle")));
		replay(mockDependencyPre);
		
		screen.click("imgs/add-button-active.png");
		
		Pattern pattern = new Pattern("imgs/table-one-item.png");
		screen.rightClick(pattern.targetOffset(0, -30));
		
		screen.mouseMove(100, 100);
		pattern = new Pattern("imgs/context-menu.png");
		screen.click(pattern.targetOffset(0, -10));
		
		pattern = new Pattern("imgs/comment-context-box.png").similar(0.99f);
		assertTrue(screen.exists(pattern) != null);
		
		screen.click("imgs/cancel-button.png");
	}
	
	@Test
	public void Edit_Comment_Button_Allows_Editing_Of_Existing_Comment() throws FindFailed, InterruptedException {
		expect(mockDependencyPre.getNumberOfPharmaceuticals()).andReturn(1).anyTimes();
		expect(mockDependencyPre.getNumberOfContainers()).andReturn(1).anyTimes();
		
		mockDependencyPre.addPrescriptionItem("Medicine1", 1, 1, 1, false, "Comment; Comes in a 1ml Bottle");
		expectLastCall();
		expect(mockDependencyPre.getPrescriptionItems()).andReturn(
				Arrays.asList(new PrescriptionItem("Medicine1", 1, 1, 1, false, "Comment; Comes in a 1ml Bottle")));
		replay(mockDependencyPre);
		
		screen.click("imgs/add-button-active.png");
		
		Pattern pattern = new Pattern("imgs/table-one-item.png");
		screen.rightClick(pattern.targetOffset(0, -30));
		
		screen.mouseMove(100, 100);
		pattern = new Pattern("imgs/context-menu.png");
		screen.click(pattern.targetOffset(0, -10));
		
		
		pattern = new Pattern("imgs/comment-context-box.png");
		screen.click(pattern);
		
		screen.type("a", Key.CTRL);
		screen.type("Edited Comment");
		
		screen.click("imgs/ok-button.png");
		pattern = new Pattern("imgs/table-one-item-edited-comment-selected.png");
		assertTrue(screen.exists(pattern) != null);
	}
	
	@Test
	public void Decrement_Dosage_Disabled_If_Daily_Dose_Is_One() throws FindFailed, InterruptedException {
		expect(mockDependencyPre.getNumberOfPharmaceuticals()).andReturn(1).anyTimes();
		expect(mockDependencyPre.getNumberOfContainers()).andReturn(1).anyTimes();
		
		mockDependencyPre.addPrescriptionItem("Medicine1", 1, 1, 1, false, "Comment; Comes in a 1ml Bottle");
		expectLastCall();
		expect(mockDependencyPre.getPrescriptionItems()).andReturn(
				Arrays.asList(new PrescriptionItem("Medicine1", 1, 1, 1, false, "Comment; Comes in a 1ml Bottle")));
		replay(mockDependencyPre);
		
		screen.click("imgs/add-button-active.png");
		
		Pattern pattern = new Pattern("imgs/table-one-item.png");
		screen.rightClick(pattern.targetOffset(0, -30));
		
		screen.mouseMove(100, 100);
		pattern = new Pattern("imgs/decrement-dosage-disabled.png");
		assertTrue(screen.exists(pattern) != null);
	}
	
	@Test
	public void Decrement_Dosage_Decrements_By_One() throws FindFailed, InterruptedException {
		expect(mockDependencyPre.getNumberOfPharmaceuticals()).andReturn(1).anyTimes();
		expect(mockDependencyPre.getNumberOfContainers()).andReturn(1).anyTimes();
		
		mockDependencyPre.addPrescriptionItem("Medicine1", 1, 1, 1, false, "Comment; Comes in a 1ml Bottle");
		expectLastCall();
		expect(mockDependencyPre.getPrescriptionItems()).andReturn(
				Arrays.asList(new PrescriptionItem("Medicine1", 2, 1, 1, false, "Comment; Comes in a 1ml Bottle")));
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
	public void Decrement_Dosage_Decrements_By_Multiple() throws FindFailed, InterruptedException {
		expect(mockDependencyPre.getNumberOfPharmaceuticals()).andReturn(1).anyTimes();
		expect(mockDependencyPre.getNumberOfContainers()).andReturn(1).anyTimes();
		
		mockDependencyPre.addPrescriptionItem("Medicine1", 1, 1, 1, false, "Comment; Comes in a 1ml Bottle");
		expectLastCall();
		expect(mockDependencyPre.getPrescriptionItems()).andReturn(
				Arrays.asList(new PrescriptionItem("Medicine1", 5, 1, 1, false, "Comment; Comes in a 1ml Bottle")));
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

}
