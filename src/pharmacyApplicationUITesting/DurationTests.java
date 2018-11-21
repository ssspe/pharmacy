package pharmacyApplicationUITesting;

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
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import pharmacyApplication.DAL;
import pharmacyApplication.Medicine;
import pharmacyApplicationFactories.FactoryDAL;
import static pharmacyApplicationUITesting.HelperFunctions.*;

public class DurationTests {
	private Screen screen;
	private DAL mockDependency;

	@Before
	public void setUp() throws Exception {
		mockDependency = createMock(DAL.class);
		FactoryDAL.setInstance(mockDependency);
		mockDependency.connect("", "", "");
		expectLastCall();

		expect(mockDependency.getPharmaName()).andReturn(Arrays.asList("Medicine1"));

		expect(mockDependency.getPharmaInfo(anyObject())).andReturn(new Medicine(0, 5, "Bottle", "T", "Comment", 0, 0));
		replay(mockDependency);
		setUpUI();
		screen = new Screen();
	}

	@After
	public void tearDown() {
		verify(mockDependency);
		closeUI();
	}

	@Test
	public void Duration_Cant_Go_Below_Zero() throws FindFailed {
		screen.mouseMove(100, 100);
		List<Match> arrow_list = sortList(screen.findAll("imgs/down-arrow.png"));
		screen.click(arrow_list.get(1));
		Pattern pattern = new Pattern("imgs/duration-zero.png").similar(1f);
		assertFalse(screen.exists(pattern) != null);
	}

	@Test
	public void Duration_Increments_By_One() throws FindFailed, InterruptedException {
		List<Match> arrow_list = sortList(screen.findAll("imgs/up-arrow.png"));
		for (int x = 0; x < 3; x++) {
			screen.click(arrow_list.get(arrow_list.size() - 1));
		}
		screen.mouseMove(100, 100);
		Pattern pattern = new Pattern("imgs/duration-four.png");
		assertTrue(screen.exists(pattern) != null);
		

		arrow_list = sortList(screen.findAll("imgs/down-arrow.png"));
		for (int x = 0; x < 3; x++) {
			screen.click(arrow_list.get(1));
		}
		assertTrue(screen.exists("imgs/duration-default.png") != null);
	}

}
