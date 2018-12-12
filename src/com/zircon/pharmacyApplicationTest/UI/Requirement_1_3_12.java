package com.zircon.pharmacyApplicationTest.UI;

import static com.zircon.pharmacyApplicationTest.HelperFunctions.setUpUI;
import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import com.zircon.pharmacyApplication.DAL;
import com.zircon.pharmacyApplication.Medicine;
import com.zircon.pharmacyApplicationFactories.FactoryDAL;

public class Requirement_1_3_12 {

	private Screen screen;
	private DAL mockDependencyDAL;

	@Before
	public void setUp() throws Exception {
		mockDependencyDAL = createMock(DAL.class);
		FactoryDAL.setInstance(mockDependencyDAL);
		mockDependencyDAL.connect("", "", "");
		expectLastCall();

		expect(mockDependencyDAL.getPharmaName()).andReturn(Arrays.asList("Medicine1", "Medicine2", "Medicine3"));

		expect(mockDependencyDAL.getPharmaInfo(anyObject()))
				.andReturn(new Medicine(1, 5, "Bottle", "T", "Comment", 0, 0)).anyTimes();

		replay(mockDependencyDAL);

		setUpUI();
		screen = new Screen();
	}

	@Test
	public void Should_ExitApplication_When_ExitButtonClicked() throws FindFailed {
		Pattern pattern = new Pattern("imgs/exit-button.png").similar(1f);
		screen.click(pattern);

		assertFalse(screen.exists(pattern) != null);
	}

	@After
	public void tearDown() {
		verify(mockDependencyDAL);
	}

}
