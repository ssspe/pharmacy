package com.zircon.pharmacyApplicationTest.UI;

import static com.zircon.pharmacyApplicationTest.HelperFunctions.closeUI;
import static com.zircon.pharmacyApplicationTest.HelperFunctions.setUpUI;
import static com.zircon.pharmacyApplicationTest.HelperFunctions.sortList;
import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import com.zircon.pharmacyApplication.DAL;
import com.zircon.pharmacyApplication.Medicine;
import com.zircon.pharmacyApplication.Prescription;
import com.zircon.pharmacyApplication.PrescriptionItem;
import com.zircon.pharmacyApplicationFactories.FactoryDAL;
import com.zircon.pharmacyApplicationFactories.FactoryPrescription;

public class Requirement_1_3_20 {

	private Screen screen;
	private DAL mockDependencyDAL;
	private Prescription mockDependencyPre;

	@Before
	public void setUp() throws Exception {
		mockDependencyDAL = createMock(DAL.class);
		FactoryDAL.setInstance(mockDependencyDAL);

		mockDependencyDAL.connect("", "", "");
		expectLastCall();

		expect(mockDependencyDAL.getPharmaName()).andReturn(Arrays.asList("Medicine1"));

		expect(mockDependencyDAL.getPharmaInfo(anyObject()))
				.andReturn(new Medicine(1, 1, "Bottle", "T", "Comment", 0, 0));

		replay(mockDependencyDAL);

		mockDependencyPre = createMock(Prescription.class);
		FactoryPrescription.setInstance(mockDependencyPre);

		expect(mockDependencyPre.getNumberOfPharmaceuticals()).andReturn(1).anyTimes();
		expect(mockDependencyPre.getNumberOfContainers()).andReturn(1).anyTimes();

		setUpUI();
		screen = new Screen();
	}

	@Test
	public void Should_EnableAddButtonIfExceedDailyDoseIsChecked_When_PrescribedDailyDoseIsOverRecommendedDailyDose()
			throws Exception {
		mockDependencyPre.addPrescriptionItem("Medicine1", 2, 1, 1, false,
				"Comment; Comes in a 1ml Bottle; My Comment;\n", 1);
		expectLastCall();

		expect(mockDependencyPre.getPrescriptionItems()).andReturn(Arrays.asList(
				new PrescriptionItem("Medicine1", 2, 1, 1, false, "Comment; Comes in a 1ml Bottle; My Comment", 1)));
		replay(mockDependencyPre);
		List<Match> arrow_list = sortList(screen.findAll("imgs/up-arrow.png"));
		for (int x = 0; x < 1; x++) {
			screen.click(arrow_list.get(0));
		}

		Pattern pattern = new Pattern("imgs/add-button-inactive.png").similar(1f);
		assertTrue(pattern != null);

		List<Match> checkbox_list = sortList(screen.findAll("imgs/check-box.png"));
		screen.click(checkbox_list.get(0));

		screen.click("imgs/add-button-active.png");

		screen.type("My Comment");

		pattern = new Pattern("imgs/ok-button.png");
		screen.click(pattern);

		pattern = new Pattern("imgs/table-one-item-edited-comment.png");
		assertTrue(screen.exists(pattern) != null);
	}

	@After
	public void tearDown() {
		verify(mockDependencyDAL);
		verify(mockDependencyPre);
		closeUI();
	}

}