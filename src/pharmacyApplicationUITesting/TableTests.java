package pharmacyApplicationUITesting;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.*;
import static pharmacyApplicationUITesting.HelperFunctions.setUpUI;
import static pharmacyApplicationUITesting.HelperFunctions.closeUI;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import pharmacyApplication.InterfaceDAL;
import pharmacyApplication.InterfacePrescription;
import pharmacyApplication.Medicine;
import pharmacyApplication.PrescriptionItem;
import pharmacyApplicationFactories.FactoryDAL;
import pharmacyApplicationFactories.FactoryPrescription;

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
		
		mockDependency.setCurrentPharmaName("Medicine1");
		expectLastCall();
		
		expect(mockDependency.getPharmaInfo()).andReturn(Arrays.asList(new Medicine(1, 5, "Bottle", "Comment", 0, 0)));
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
		
		Pattern pattern = new Pattern("imgs/table-one-item.png").similar(1f);
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
		
		Pattern pattern = new Pattern("imgs/table-one-item.png").similar(1f);
		screen.rightClick(pattern.targetOffset(0, -30));
		
		screen.mouseMove(100, 100);
		pattern = new Pattern("imgs/context-menu.png").similar(0.9f);
		screen.click(pattern.targetOffset(0, -10));
		
		pattern = new Pattern("imgs/comment-context-box.png").similar(0.99f);
		assertTrue(screen.exists(pattern) != null);
	}

}
