package pharmacyApplicationUITesting;

import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.verify;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.*;
import static pharmacyApplicationUITesting.HelperFunctions.closeUI;
import static pharmacyApplicationUITesting.HelperFunctions.setUpUI;

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

public class RemoveButtonTests {
	private Screen screen;
	private InterfaceDAL mockDependencyDAL;
	private InterfacePrescription mockDependencyPre;

	@Before
	public void setUp() throws Exception {
		mockDependencyDAL = createMock(InterfaceDAL.class);
		FactoryDAL.setInstance(mockDependencyDAL);
		mockDependencyDAL.connect("", "", "");
		expectLastCall();

		expect(mockDependencyDAL.getPharmaName()).andReturn(Arrays.asList("Medicine1", "Medicine2", "Medicine3"));

		mockDependencyDAL.setCurrentPharmaName(anyObject());
		expectLastCall().anyTimes();

		expect(mockDependencyDAL.getPharmaInfo())
				.andReturn(Arrays.asList(new Medicine(1, 5, "Bottle", "Comment", 0, 0))).anyTimes();

		replay(mockDependencyDAL);

		mockDependencyPre = createMock(InterfacePrescription.class);
		FactoryPrescription.setInstance(mockDependencyPre);

		setUpUI();
		screen = new Screen();
		assertTrue(screen.exists("imgs/duration-default.png") != null);
	}

	@After
	public void tearDown() {
		verify(mockDependencyDAL);
		verify(mockDependencyPre);
		closeUI();
	}

	@Test
	public void Remove_Button_Disabled_By_Default() {
		replay(mockDependencyPre);
		Pattern pattern = new Pattern("imgs/remove-button-inactive.png").similar(1f);
		assertTrue(screen.exists(pattern) != null);
	}

	@Test
	public void Remove_Button_Disabled_Until_Tabel_Row_Is_Selected() throws FindFailed {
		expect(mockDependencyPre.getNumberOfPharmaceuticals()).andReturn(1).anyTimes();
		expect(mockDependencyPre.getNumberOfContainers()).andReturn(1).anyTimes();
		mockDependencyPre.addPrescriptionItem("Medicine1", 1, 1, 1, false, "Comment; Comes in a 1ml Bottle");
		expectLastCall();
		expect(mockDependencyPre.getPrescriptionItems()).andReturn(
				Arrays.asList(new PrescriptionItem("Medicine1", 1, 1, 1, false, "Comment; Comes in a 1ml Bottle")));
		replay(mockDependencyPre);

		Pattern pattern = new Pattern("imgs/remove-button-inactive.png").similar(1f);
		assertTrue(screen.exists(pattern) != null);

		screen.click("imgs/add-button-active.png");

		pattern = new Pattern("imgs/table-one-item.png").similar(1f);
		screen.click(pattern.targetOffset(0, -20));

		pattern = new Pattern("imgs/remove-button-active.png").similar(1f);
		assertTrue(screen.exists(pattern) != null);
	}

	@Test
	public void Remove_Button_Removes_Row() throws FindFailed {
		expect(mockDependencyPre.getNumberOfPharmaceuticals()).andReturn(1).anyTimes();
		expect(mockDependencyPre.getNumberOfContainers()).andReturn(1).anyTimes();
		mockDependencyPre.addPrescriptionItem("Medicine1", 1, 1, 1, false, "Comment; Comes in a 1ml Bottle");
		expectLastCall();
		mockDependencyPre.removePrescriptionItem("Medicine1");
		expectLastCall();
		expect(mockDependencyPre.getPrescriptionItems()).andReturn(
				Arrays.asList(new PrescriptionItem("Medicine1", 1, 1, 1, false, "Comment; Comes in a 1ml Bottle")));
		expect(mockDependencyPre.getPrescriptionItems()).andReturn(Arrays.asList());
		replay(mockDependencyPre);

		Pattern pattern = new Pattern("imgs/remove-button-inactive.png").similar(1f);
		assertTrue(screen.exists(pattern) != null);

		screen.click("imgs/add-button-active.png");

		pattern = new Pattern("imgs/table-one-item.png").similar(1f);
		screen.click(pattern.targetOffset(0, -20));

		pattern = new Pattern("imgs/remove-button-active.png").similar(1f);
		screen.click(pattern);

		pattern = new Pattern("imgs/table-zero-items.png").similar(1f);
		assertTrue(screen.exists(pattern) != null);
	}

	@Test
	public void Remove_Button_Removes_Selected_Row() throws FindFailed, InterruptedException {
		expect(mockDependencyPre.getNumberOfPharmaceuticals()).andReturn(1).anyTimes();
		expect(mockDependencyPre.getNumberOfContainers()).andReturn(1).anyTimes();
		mockDependencyPre.addPrescriptionItem("Medicine1", 1, 1, 1, false, "Comment; Comes in a 1ml Bottle");
		expectLastCall();
		mockDependencyPre.addPrescriptionItem("Medicine2", 1, 1, 1, false, "Comment; Comes in a 1ml Bottle");
		expectLastCall();
		mockDependencyPre.removePrescriptionItem("Medicine1");
		expectLastCall();
		expect(mockDependencyPre.getPrescriptionItems()).andReturn(
				Arrays.asList(new PrescriptionItem("Medicine1", 1, 1, 1, false, "Comment; Comes in a 1ml Bottle")));
		expect(mockDependencyPre.getPrescriptionItems()).andReturn(
				Arrays.asList(new PrescriptionItem("Medicine1", 1, 1, 1, false, "Comment; Comes in a 1ml Bottle"),
						new PrescriptionItem("Medicine2", 1, 1, 1, false, "Comment; Comes in a 1ml Bottle")));
		expect(mockDependencyPre.getPrescriptionItems()).andReturn(
				Arrays.asList(new PrescriptionItem("Medicine2", 1, 1, 1, false, "Comment; Comes in a 1ml Bottle")));

		replay(mockDependencyPre);

		screen.click("imgs/add-button-active.png");
		screen.click("imgs/combo-box.png");

		Pattern pattern = new Pattern("imgs/combo-box-three-items.png").similar(1f);
		screen.click(pattern);

		screen.click("imgs/add-button-active.png");

		pattern = new Pattern("imgs/table-two-items.png").similar(1f);
		screen.click(pattern.targetOffset(0, -30));

		pattern = new Pattern("imgs/remove-button-active.png").similar(1f);
		screen.click(pattern);

		pattern = new Pattern("imgs/table-one-item-medicine-two.png").similar(1f);
		assertTrue(screen.exists(pattern) != null);
	}

}
