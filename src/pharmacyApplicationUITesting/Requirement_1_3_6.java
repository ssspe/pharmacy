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
import static pharmacyApplicationUITesting.HelperFunctions.sortList;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import pharmacyApplication.DAL;
import pharmacyApplication.Medicine;
import pharmacyApplicationFactories.FactoryDAL;

public class Requirement_1_3_6 {

	private DAL mockDependency;
	private Screen screen;

	@Before
	public void setUp() throws Exception {
		mockDependency = createMock(DAL.class);
		FactoryDAL.setInstance(mockDependency);
		mockDependency.connect("", "", "");
		expectLastCall();
		screen = new Screen();
		expect(mockDependency.getPharmaName()).andReturn(Arrays.asList("Medicine1"));
		expect(mockDependency.getPharmaInfo(anyObject())).andReturn(new Medicine(0, 1, "Bottle", "T", "Comment", 0, 0));
		replay(mockDependency);
		setUpUI();
	}

	@Test
	public void Should_DisplayPrescribedDailyDose_When_ByDefault() throws Exception {
		assertTrue(screen.exists("imgs/prescribed-daily-dose-default.png") != null);
	}

	@Test
	public void Should_PrescribedDailyDoseStayAtOne_DecrementedBelowOne() throws FindFailed {
		List<Match> arrow_list = sortList(screen.findAll("imgs/down-arrow.png"));
		screen.click(arrow_list.get(0));
		assertFalse(screen.exists("imgs/prescribed-daily-dose-zero.png") != null);
	}

	@Test
	public void Should_PrescribedDailyDoseIncrementByOne_When_IncreasingValue() throws FindFailed {
		List<Match> arrow_list = sortList(screen.findAll("imgs/up-arrow.png"));
		for (int x = 0; x < 3; x++) {
			screen.click(arrow_list.get(0));
		}
		assertTrue(screen.exists("imgs/prescribed-daily-dose-four.png") != null);

		arrow_list = sortList(screen.findAll("imgs/down-arrow.png"));
		for (int x = 0; x < 3; x++) {
			screen.click(arrow_list.get(0));
		}

		assertTrue(screen.exists("imgs/prescribed-daily-dose-default.png") != null);
	}

	@After
	public void tearDown() {
		verify(mockDependency);
		closeUI();
	}
}
