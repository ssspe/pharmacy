package com.zircon.pharmacyApplicationTest.UI;

import static com.zircon.pharmacyApplicationTest.HelperFunctions.setUpUI;
import static com.zircon.pharmacyApplicationTest.HelperFunctions.closeUI;
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
import org.sikuli.script.FindFailed;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.zircon.pharmacyApplication.DAL;
import com.zircon.pharmacyApplication.Medicine;
import com.zircon.pharmacyApplication.Prescription;
import com.zircon.pharmacyApplication.PrescriptionItem;
import com.zircon.pharmacyApplicationFactories.FactoryDAL;
import com.zircon.pharmacyApplicationFactories.FactoryPrescription;

public class Requirement_1_3_17 {

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
	public void Should_AddColonAndNewLine_When_NeitherExists() throws Exception {
		expect(mockDependencyPre.getNumberOfPharmaceuticals()).andReturn(1).anyTimes();
		expect(mockDependencyPre.getNumberOfContainers()).andReturn(1).anyTimes();

		mockDependencyPre.addPrescriptionItem("Medicine1", 1, 1, 1, false,
				"Comment; Comes in a 1ml Bottle; Comment With No Colon Or New Line;\n", 1);
		expectLastCall();
		expect(mockDependencyPre.getPrescriptionItems()).andReturn(Arrays.asList(new PrescriptionItem("Medicine1", 1, 1,
				1, false, "Comment; Comes in a 1ml Bottle; Comment With No Colon Or New Line;\n", 1)));
		replay(mockDependencyPre);

		List<Match> checkbox_list = sortList(screen.findAll("imgs/check-box.png"));
		screen.click(checkbox_list.get(1));
		screen.click("imgs/add-button-active.png");

		screen.type("Comment With No Colon Or New Line");
		assertTrue(screen.exists("imgs/comment-dialogue-box.png") != null);
		screen.click("imgs/ok-button.png");
	}

	@Test
	public void Should_AddNewLine_When_OnlyColonExists() throws Exception {
		expect(mockDependencyPre.getNumberOfPharmaceuticals()).andReturn(1).anyTimes();
		expect(mockDependencyPre.getNumberOfContainers()).andReturn(1).anyTimes();

		mockDependencyPre.addPrescriptionItem("Medicine1", 1, 1, 1, false,
				"Comment; Comes in a 1ml Bottle; Comment With No New Line;\n", 1);
		expectLastCall();
		expect(mockDependencyPre.getPrescriptionItems()).andReturn(Arrays.asList(new PrescriptionItem("Medicine1", 1, 1,
				1, false, "Comment; Comes in a 1ml Bottle; Comment With No New Line;\n", 1)));
		replay(mockDependencyPre);

		List<Match> checkbox_list = sortList(screen.findAll("imgs/check-box.png"));
		screen.click(checkbox_list.get(1));
		screen.click("imgs/add-button-active.png");

		screen.type("Comment With No New Line;");
		assertTrue(screen.exists("imgs/comment-dialogue-box.png") != null);
		screen.click("imgs/ok-button.png");
	}

	@Test
	public void Should_AddNothing_When_ColonAndNewLineExist() throws Exception {
		expect(mockDependencyPre.getNumberOfPharmaceuticals()).andReturn(1).anyTimes();
		expect(mockDependencyPre.getNumberOfContainers()).andReturn(1).anyTimes();

		mockDependencyPre.addPrescriptionItem("Medicine1", 1, 1, 1, false,
				"Comment; Comes in a 1ml Bottle; Comment;\n", 1);
		expectLastCall();
		expect(mockDependencyPre.getPrescriptionItems()).andReturn(Arrays.asList(
				new PrescriptionItem("Medicine1", 1, 1, 1, false, "Comment; Comes in a 1ml Bottle; Comment;\n", 1)));
		replay(mockDependencyPre);

		List<Match> checkbox_list = sortList(screen.findAll("imgs/check-box.png"));
		screen.click(checkbox_list.get(1));
		screen.click("imgs/add-button-active.png");

		screen.type("Comment;");
		assertTrue(screen.exists("imgs/comment-dialogue-box.png") != null);
		screen.click("imgs/ok-button.png");
	}

	@After
	public void tearDown() {
		verify(mockDependency);
		verify(mockDependencyPre);
		closeUI();
	}

}
