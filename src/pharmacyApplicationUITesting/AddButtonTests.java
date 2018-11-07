package pharmacyApplicationUITesting;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.easymock.EasyMock.anyObject;
import static org.junit.Assert.*;
import static pharmacyApplicationUITesting.HelperFunctions.closeUI;
import static pharmacyApplicationUITesting.HelperFunctions.setUpUI;
import static pharmacyApplicationUITesting.HelperFunctions.sortList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.sikuli.script.Region;

import pharmacyApplication.InterfaceDAL;
import pharmacyApplication.InterfacePrescription;
import pharmacyApplication.Medicine;
import pharmacyApplication.PrescriptionItem;
import pharmacyApplicationFactories.FactoryDAL;
import pharmacyApplicationFactories.FactoryPrescription;

public class AddButtonTests {
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


		expect(mockDependencyDAL.getPharmaInfo(anyObject()))
				.andReturn(new Medicine(1, 5, "Bottle", "Comment", 0, 0)).anyTimes();

		replay(mockDependencyDAL);

		mockDependencyPre = createMock(InterfacePrescription.class);
		FactoryPrescription.setInstance(mockDependencyPre);

		expect(mockDependencyPre.getNumberOfPharmaceuticals()).andReturn(1).anyTimes();
		expect(mockDependencyPre.getNumberOfContainers()).andReturn(1).anyTimes();

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
	public void Add_Button_Adds_One_Medicine_To_Table() throws FindFailed, InterruptedException {
		mockDependencyPre.addPrescriptionItem("Medicine1", 1, 1, 1, false, "Comment; Comes in a 1ml Bottle");
		expectLastCall();
		expect(mockDependencyPre.getPrescriptionItems()).andReturn(
				Arrays.asList(new PrescriptionItem("Medicine1", 1, 1, 1, false, "Comment; Comes in a 1ml Bottle")));
		replay(mockDependencyPre);

		screen.mouseMove(100, 100);
		Pattern pattern = new Pattern("imgs/add-button-active.png").similar(1f);
		screen.click(pattern);
		pattern = new Pattern("imgs/table-one-item.png");
		assertTrue(screen.exists(pattern) != null);
	}

	@Test
	public void Add_Button_Adds_Multiple_Medicine_To_Table() throws FindFailed, InterruptedException {
		mockDependencyPre.addPrescriptionItem("Medicine1", 1, 1, 1, false, "Comment; Comes in a 1ml Bottle");
		expectLastCall();
		mockDependencyPre.addPrescriptionItem("Medicine2", 1, 1, 1, false, "Comment; Comes in a 1ml Bottle");
		expectLastCall();
		expect(mockDependencyPre.getPrescriptionItems()).andReturn(
				Arrays.asList(new PrescriptionItem("Medicine1", 1, 1, 1, false, "Comment; Comes in a 1ml Bottle")));
		expect(mockDependencyPre.getPrescriptionItems()).andReturn(
				Arrays.asList(new PrescriptionItem("Medicine1", 1, 1, 1, false, "Comment; Comes in a 1ml Bottle"),
						new PrescriptionItem("Medicine2", 1, 1, 1, false, "Comment; Comes in a 1ml Bottle")));

		replay(mockDependencyPre);

		screen.click("imgs/add-button-active.png");
		screen.click("imgs/combo-box.png");

		Pattern pattern = new Pattern("imgs/combo-box-three-items.png").similar(1f);
		screen.click(pattern);
		screen.click("imgs/add-button-active.png");
		pattern = new Pattern("imgs/table-two-items.png");
		assertTrue(screen.exists(pattern) != null);
	}

	@Test
	public void Add_Button_Can_Add_If_Over_Dosage_Checkbox_Is_Checked() throws FindFailed, InterruptedException {
		mockDependencyPre.addPrescriptionItem("Medicine1", 6, 1, 1, false,
				"Comment; Comes in a 1ml Bottle; My Comment;\n");
		expectLastCall();

		expect(mockDependencyPre.getPrescriptionItems()).andReturn(Arrays.asList(
				new PrescriptionItem("Medicine1", 1, 1, 1, false, "Comment; Comes in a 1ml Bottle; My Comment")));
		replay(mockDependencyPre);
		List<Match> arrow_list = sortList(screen.findAll("imgs/up-arrow.png"));
		for (int x = 0; x < 5; x++) {
			screen.click(arrow_list.get(0));
		}

		Pattern pattern = new Pattern("imgs/add-button-inactive.png").similar(1f);
		assertTrue(pattern != null);

		List<Match> checkbox_list = sortList(screen.findAll("imgs/check-box.png"));
		screen.click(checkbox_list.get(0));

		pattern = new Pattern("imgs/add-button-active.png").similar(1f);
		assertTrue(pattern != null);

		screen.click(pattern);

		screen.type("My Comment");

		screen.click("imgs/ok-button.png");
		pattern = new Pattern("imgs/table-one-item-edited-comment.png");
		assertTrue(screen.exists(pattern) != null);

	}

}
