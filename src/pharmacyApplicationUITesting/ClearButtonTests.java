package pharmacyApplicationUITesting;

import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertTrue;
import static pharmacyApplicationUITesting.HelperFunctions.closeUI;
import static pharmacyApplicationUITesting.HelperFunctions.setUpUI;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import pharmacyApplication.DAL;
import pharmacyApplication.Medicine;
import pharmacyApplication.Prescription;
import pharmacyApplication.PrescriptionItem;
import pharmacyApplicationFactories.FactoryDAL;
import pharmacyApplicationFactories.FactoryPrescription;

public class ClearButtonTests {
	private Screen screen;
	private DAL mockDependencyDAL;
	private Prescription mockDependencyPre;

	@Before
	public void setUp() throws Exception {
		mockDependencyDAL = createMock(DAL.class);
		FactoryDAL.setInstance(mockDependencyDAL);
		mockDependencyDAL.connect("", "", "");
		expectLastCall();

		expect(mockDependencyDAL.getPharmaName()).andReturn(Arrays.asList("Medicine1", "Medicine2", "Medicine3"));

		expect(mockDependencyDAL.getPharmaInfo(anyObject()))
				.andReturn(new Medicine(1, 5, "Bottle", "Comment", 0, 0)).anyTimes();

		replay(mockDependencyDAL);

		mockDependencyPre = createMock(Prescription.class);
		FactoryPrescription.setInstance(mockDependencyPre);

		setUpUI();
		screen = new Screen();
	}

	@After
	public void tearDown() {
		verify(mockDependencyDAL);
		verify(mockDependencyPre);
		closeUI();
	}

	@Test
	public void Clear_Button_Disabled_By_Default() {
		replay(mockDependencyPre);
		Pattern pattern = new Pattern("imgs/clear-button-inactive.png").similar(1f);
		assertTrue(screen.exists(pattern) != null);
	}

	@Test
	public void Clear_Button_Disabled_Until_Tabel_Row_Exists() throws Exception {
		expect(mockDependencyPre.getNumberOfPharmaceuticals()).andReturn(1).anyTimes();
		expect(mockDependencyPre.getNumberOfContainers()).andReturn(1).anyTimes();
		mockDependencyPre.addPrescriptionItem("Medicine1", 1, 1, 1, false, "Comment; Comes in a 1ml Bottle");
		expectLastCall();
		expect(mockDependencyPre.getPrescriptionItems()).andReturn(
				Arrays.asList(new PrescriptionItem("Medicine1", 1, 1, 1, false, "Comment; Comes in a 1ml Bottle")));
		replay(mockDependencyPre);

		Pattern pattern = new Pattern("imgs/clear-button-inactive.png").similar(1f);
		assertTrue(screen.exists(pattern) != null);

		screen.click("imgs/add-button-active.png");

		pattern = new Pattern("imgs/clear-button-active.png").similar(1f);
		assertTrue(screen.exists(pattern) != null);
	}

	@Test
	public void Clear_Button_Removes_Single_Row() throws Exception {
		expect(mockDependencyPre.getNumberOfPharmaceuticals()).andReturn(1).anyTimes();
		expect(mockDependencyPre.getNumberOfContainers()).andReturn(1).anyTimes();
		mockDependencyPre.addPrescriptionItem("Medicine1", 1, 1, 1, false, "Comment; Comes in a 1ml Bottle");
		expectLastCall();
		mockDependencyPre.clearPrescription();
		expectLastCall();
		expect(mockDependencyPre.getPrescriptionItems()).andReturn(
				Arrays.asList(new PrescriptionItem("Medicine1", 1, 1, 1, false, "Comment; Comes in a 1ml Bottle")));
		replay(mockDependencyPre);

		Pattern pattern = new Pattern("imgs/clear-button-inactive.png").similar(1f);
		assertTrue(screen.exists(pattern) != null);
		
		screen.mouseMove(100, 100);
		screen.click("imgs/add-button-active.png");

		pattern = new Pattern("imgs/table-one-item.png");
		assertTrue(screen.exists(pattern) != null);

		pattern = new Pattern("imgs/clear-button-active.png");
		screen.click(pattern);

		pattern = new Pattern("imgs/table-zero-items.png");
		assertTrue(screen.exists(pattern) != null);
	}

	@Test
	public void Clear_Button_Removes_All_Rows() throws Exception {
		expect(mockDependencyPre.getNumberOfPharmaceuticals()).andReturn(1).anyTimes();
		expect(mockDependencyPre.getNumberOfContainers()).andReturn(1).anyTimes();
		mockDependencyPre.addPrescriptionItem("Medicine1", 1, 1, 1, false, "Comment; Comes in a 1ml Bottle");
		expectLastCall();
		mockDependencyPre.addPrescriptionItem("Medicine2", 1, 1, 1, false, "Comment; Comes in a 1ml Bottle");
		expectLastCall();
		mockDependencyPre.clearPrescription();
		expectLastCall();
		expect(mockDependencyPre.getPrescriptionItems()).andReturn(
				Arrays.asList(new PrescriptionItem("Medicine1", 1, 1, 1, false, "Comment; Comes in a 1ml Bottle")));
		expect(mockDependencyPre.getPrescriptionItems()).andReturn(
				Arrays.asList(new PrescriptionItem("Medicine1", 1, 1, 1, false, "Comment; Comes in a 1ml Bottle"),
						new PrescriptionItem("Medicine2", 1, 1, 1, false, "Comment; Comes in a 1ml Bottle")));

		replay(mockDependencyPre);
		
		screen.mouseMove(100, 100);
		screen.click("imgs/add-button-active.png");
		screen.click("imgs/combo-box.png");

		Pattern pattern = new Pattern("imgs/combo-box-three-items.png");
		screen.click(pattern);

		screen.click("imgs/add-button-active.png");

		pattern = new Pattern("imgs/table-two-items.png");
		assertTrue(screen.exists(pattern) != null);

		pattern = new Pattern("imgs/clear-button-active.png");
		screen.click(pattern);

		pattern = new Pattern("imgs/table-zero-items.png");
		assertTrue(screen.exists(pattern) != null);
	}
}
