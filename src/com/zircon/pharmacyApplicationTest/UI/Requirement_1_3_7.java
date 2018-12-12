package com.zircon.pharmacyApplicationTest.UI;

import static com.zircon.pharmacyApplicationTest.HelperFunctions.setUpUI;
import static com.zircon.pharmacyApplicationTest.HelperFunctions.sortList;
import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import com.zircon.pharmacyApplication.DAL;
import com.zircon.pharmacyApplication.Medicine;
import com.zircon.pharmacyApplicationFactories.FactoryDAL;

public class Requirement_1_3_7 {
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
	}
	
	@Test
	public void Should_PrescribedDailyDoseStopAtRecommendedDailyDose_When_DailyDoseIsOne() throws Exception {
		expect(mockDependency.getPharmaInfo(anyObject())).andReturn(new Medicine(0, 1, "Bottle", "T", "Comment", 0, 0));
		replay(mockDependency);
		setUpUI();
		List<Match> arrow_list = sortList(screen.findAll("imgs/up-arrow.png"));
		for (int x = 0; x < 5; x++) {
			screen.click(arrow_list.get(0));
		}
		screen.mouseMove(100, 100);
		Pattern pattern = new Pattern("imgs/prescribed-daily-dose-two.png");
		assertTrue(screen.exists(pattern) != null);
	}
	
	@Test
	public void Should_PrescribedDailyDoseStopAtRecommendedDailyDose_When_DailyDoseIsFive() throws Exception {
		expect(mockDependency.getPharmaInfo(anyObject())).andReturn(new Medicine(0, 5, "Bottle", "T", "Comment", 0, 0));
		replay(mockDependency);
		setUpUI();
		List<Match> arrow_list = sortList(screen.findAll("imgs/up-arrow.png"));
		for (int x = 0; x < 13; x++) {
			screen.click(arrow_list.get(0));
		}
		screen.mouseMove(100, 100);
		Pattern pattern = new Pattern("imgs/prescribed-daily-dose-ten.png");
		assertTrue(screen.exists(pattern) != null);
	}

}
