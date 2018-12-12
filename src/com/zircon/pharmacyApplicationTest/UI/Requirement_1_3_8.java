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
import org.sikuli.script.FindFailed;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.zircon.pharmacyApplication.DAL;
import com.zircon.pharmacyApplication.Medicine;
import com.zircon.pharmacyApplicationFactories.FactoryDAL;

public class Requirement_1_3_8 {
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
	public void Should_DisplayDuration_When_ByDefault() throws Exception {
		assertTrue(screen.exists("imgs/duration-default.png") != null);
	}

	@Test
	public void Should_DurationStayAtOne_When_DecrementedBelowOne() throws FindFailed {
		List<Match> arrow_list = sortList(screen.findAll("imgs/down-arrow.png"));
		screen.click(arrow_list.get(0));
		assertFalse(screen.exists("imgs/duration-zero.png") != null);
	}

	@Test
	public void Should_DurationIncrementByOne_When_IncreasingValue() throws FindFailed {
		List<Match> arrow_list = sortList(screen.findAll("imgs/up-arrow.png"));
		for (int x = 0; x < 3; x++) {
			screen.click(arrow_list.get(0));
		}
		assertTrue(screen.exists("imgs/duration-four.png") != null);

		arrow_list = sortList(screen.findAll("imgs/down-arrow.png"));
		for (int x = 0; x < 3; x++) {
			screen.click(arrow_list.get(0));
		}

		assertTrue(screen.exists("imgs/duration-default.png") != null);
	}

	@After
	public void tearDown() {
		verify(mockDependency);
		closeUI();
	}

}