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

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import pharmacyApplication.DAL;
import pharmacyApplication.Medicine;
import pharmacyApplicationFactories.FactoryDAL;

public class Requirement_1_3_4 {
	private DAL mockDependency;
	private Screen screen;

	@Before
	public void setUp() throws Exception {
		mockDependency = createMock(DAL.class);
		FactoryDAL.setInstance(mockDependency);
		mockDependency.connect("", "", "");
		expectLastCall();
		screen = new Screen();
	}

	@Test
	public void Should_DisplayDescription_When_ByDefault() throws Exception {
		expect(mockDependency.getPharmaName()).andReturn(Arrays.asList("Medicine1"));
		expect(mockDependency.getPharmaInfo(anyObject())).andReturn(new Medicine(0, 1, "Bottle", "T", "Test Description", 0, 0));
		replay(mockDependency);
		setUpUI();
		assertTrue(screen.exists(new Pattern("imgs/test-description")) != null);
	}

	@Test
	public void Should_UpdateDescription_When_NewMedicineIsSelected() throws Exception {
		expect(mockDependency.getPharmaName()).andReturn(Arrays.asList("Medicine1", "Medicine2"));
		expect(mockDependency.getPharmaInfo(anyObject())).andReturn(new Medicine(0, 1, "Bottle", "T", "Test Description", 0, 0));
		expect(mockDependency.getPharmaInfo(anyObject())).andReturn(new Medicine(0, 2, "Bottle", "T", "Test Description 2", 0, 0));
		replay(mockDependency);
		setUpUI();
		Pattern pattern = new Pattern("imgs/combo-box");
		screen.click(pattern);
		screen.click(pattern.targetOffset(0, 40));
		assertTrue(screen.exists(new Pattern("imgs/test-description-two").similar(1f)) != null);
	}
	
	@Test
	public void Should_DisplayAvailableOverTheConter_When_AvailableOverTheCounterIsTrue() throws Exception {
		expect(mockDependency.getPharmaName()).andReturn(Arrays.asList("Medicine1", "Medicine2"));
		expect(mockDependency.getPharmaInfo(anyObject())).andReturn(new Medicine(0, 1, "Bottle", "T", "Test Description", 0, 0));
		expect(mockDependency.getPharmaInfo(anyObject())).andReturn(new Medicine(0, 2, "Bottle", "T", "Test Description", 1, 0));
		replay(mockDependency);
		setUpUI();
		assertTrue(screen.exists(new Pattern("imgs/test-description")) != null);
		Pattern pattern = new Pattern("imgs/combo-box");
		screen.click(pattern);
		screen.click(pattern.targetOffset(0, 40));
		assertTrue(screen.exists(new Pattern("imgs/test-description-over-the-counter")) != null);
	}
	
	@Test
	public void Should_DisplayBoxWithOneTablet_When_SizeIsOneAndContainerTypeIsBox() throws Exception {
		expect(mockDependency.getPharmaName()).andReturn(Arrays.asList("Medicine1"));
		expect(mockDependency.getPharmaInfo(anyObject())).andReturn(new Medicine(1, 1, "Box", "T", "", 0, 0));
		replay(mockDependency);
		setUpUI();
		assertTrue(screen.exists(new Pattern("imgs/box-size-one")) != null);
	}
	
	@Test
	public void Should_DisplayBoxWithTenTablets_When_SizeIsTenAndContainerTypeIsBox() throws Exception {
		expect(mockDependency.getPharmaName()).andReturn(Arrays.asList("Medicine1"));
		expect(mockDependency.getPharmaInfo(anyObject())).andReturn(new Medicine(10, 1, "Box", "T", "", 0, 0));
		replay(mockDependency);
		setUpUI();
		assertTrue(screen.exists(new Pattern("imgs/box-size-ten")) != null);
	}
	
	@Test
	public void Should_DisplayBottleWithOneMl_When_SizeIsOneAndContainerTypeIsBottle() throws Exception {
		expect(mockDependency.getPharmaName()).andReturn(Arrays.asList("Medicine1", "Medicine2"));
		expect(mockDependency.getPharmaInfo(anyObject())).andReturn(new Medicine(1, 1, "Bottle", "T", "", 0, 0));
		replay(mockDependency);
		setUpUI();
		assertTrue(screen.exists(new Pattern("imgs/bottle-size-one")) != null);
	}
	
	@Test
	public void Should_DisplayBottleWithTenTablets_When_SizeIsTenAndContainerTypeIsBottle() throws Exception {
		expect(mockDependency.getPharmaName()).andReturn(Arrays.asList("Medicine1"));
		expect(mockDependency.getPharmaInfo(anyObject())).andReturn(new Medicine(10, 1, "Bottle", "T", "", 0, 0));
		replay(mockDependency);
		setUpUI();
		assertTrue(screen.exists(new Pattern("imgs/bottle-size-ten")) != null);
	}
	
	@Test
	public void Should_DisplayTubeWithOneMl_When_SizeIsOneAndContainerTypeIsTube() throws Exception {
		expect(mockDependency.getPharmaName()).andReturn(Arrays.asList("Medicine1", "Medicine2"));
		expect(mockDependency.getPharmaInfo(anyObject())).andReturn(new Medicine(1, 1, "Tube", "T", "", 0, 0));
		replay(mockDependency);
		setUpUI();
		assertTrue(screen.exists(new Pattern("imgs/tube-size-one")) != null);
	}
	
	@Test
	public void Should_DisplayTubeWithTenTablets_When_SizeIsTenAndContainerTypeIsTube() throws Exception {
		expect(mockDependency.getPharmaName()).andReturn(Arrays.asList("Medicine1"));
		expect(mockDependency.getPharmaInfo(anyObject())).andReturn(new Medicine(10, 1, "Tube", "T", "", 0, 0));
		replay(mockDependency);
		setUpUI();
		assertTrue(screen.exists(new Pattern("imgs/tube-size-ten")) != null);
	}
	
	@Test
	public void Should_DisplayPhialWithOneMl_When_SizeIsOneAndContainerTypeIsPhial() throws Exception {
		expect(mockDependency.getPharmaName()).andReturn(Arrays.asList("Medicine1", "Medicine2"));
		expect(mockDependency.getPharmaInfo(anyObject())).andReturn(new Medicine(1, 1, "Phial", "T", "", 0, 0));
		replay(mockDependency);
		setUpUI();
		assertTrue(screen.exists(new Pattern("imgs/phial-size-one")) != null);
	}
	
	@Test
	public void Should_DisplayPhialWithTenTablets_When_SizeIsTenAndContainerTypeIsPhial() throws Exception {
		expect(mockDependency.getPharmaName()).andReturn(Arrays.asList("Medicine1"));
		expect(mockDependency.getPharmaInfo(anyObject())).andReturn(new Medicine(10, 1, "Phial", "T", "", 0, 0));
		replay(mockDependency);
		setUpUI();
		assertTrue(screen.exists(new Pattern("imgs/phial-size-ten")) != null);
	}
	
	@After
	public void tearDown() {
		verify(mockDependency);
		closeUI();
	}

}
