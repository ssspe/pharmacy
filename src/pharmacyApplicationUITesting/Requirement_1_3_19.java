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

public class Requirement_1_3_19 {
	private Screen screen;
	private DAL mockDependency;

	@Before
	public void setUp() throws Exception {
		mockDependency = createMock(DAL.class);
		FactoryDAL.setInstance(mockDependency);
		mockDependency.connect("", "", "");
		expectLastCall();

		expect(mockDependency.getPharmaName()).andReturn(Arrays.asList("Medicine1"));

		expect(mockDependency.getPharmaInfo(anyObject())).andReturn(new Medicine(0, 1, "Bottle", "T", "Comment", 0, 0));
		replay(mockDependency);
		setUpUI();
		screen = new Screen();
		assertTrue(screen.exists("imgs/prescribed-daily-dose-default.png") != null);
	}
	
	@Test
	public void Should_DisableAddButton_When_PrescribedDailyDoseIsOverRecommendedDailyDose() throws FindFailed {
		List<Match> arrow_list = sortList(screen.findAll("imgs/up-arrow.png"));
		for (int x = 0; x < 1; x++) {
			screen.click(arrow_list.get(0));
		}

		assertTrue(screen.exists("imgs/add-button-inactive.png") != null);
		Pattern pattern = new Pattern("imgs/add-button-active.png").similar(1f);
		assertFalse(screen.exists(pattern) != null);
	}
	

	@After
	public void tearDown() {
		verify(mockDependency);
		closeUI();
	}

}
