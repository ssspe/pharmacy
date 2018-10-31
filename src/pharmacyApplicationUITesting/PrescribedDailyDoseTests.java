package pharmacyApplicationUITesting;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.imageio.plugins.tiff.ExifGPSTagSet;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sikuli.script.Env;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import com.sun.java.swing.plaf.windows.resources.windows;
import com.sun.org.apache.xerces.internal.xs.ShortList;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.verify;import jdk.jfr.internal.PrivateAccess;
import pharmacyApplication.InterfaceDAL;
import pharmacyApplication.Medicine;
import pharmacyApplication.PrescriptionUI;
import pharmacyApplicationFactories.FactoryDAL;
import static pharmacyApplicationUITesting.HelperFunctions.*;

public class PrescribedDailyDoseTests {
	private Screen screen;
	private InterfaceDAL mockDependency;

	@Before
	public void setUp() throws Exception {
		mockDependency = createMock(InterfaceDAL.class);
		FactoryDAL.setInstance(mockDependency);
		mockDependency.connect("", "", "");
		expectLastCall();
		
		expect(mockDependency.getPharmaName()).andReturn(Arrays.asList("Medicine1", "Medicine2", "Medicine3"));
		
		mockDependency.setCurrentPharmaName("Medicine1");
		expectLastCall();
		
		expect(mockDependency.getPharmaInfo()).andReturn(Arrays.asList(new Medicine(0, 5, "Bottle", "Comment", 0, 0)));
		replay(mockDependency);
		setUpUI();
		screen = new Screen();
		assertTrue(screen.exists("imgs/prescribed-daily-dose-default.png") != null);
	}
	
	@Test
	public void Prescribed_Daily_Dose_Cant_Go_Below_Zero() throws FindFailed {
		List<Match> arrow_list = sortList(screen.findAll("imgs/down-arrow.png"));
		screen.click(arrow_list.get(0));
		assertFalse(screen.exists("imgs/prescribed-daily-dose-zero.png") != null);
	}
	
	@Test
	public void Prescribed_Daily_Dose_Increments_By_One() throws FindFailed {
		List<Match> arrow_list = sortList(screen.findAll("imgs/up-arrow.png"));
		for(int x = 0; x < 3; x++) {
			screen.click(arrow_list.get(0));
		}
		assertTrue(screen.exists("imgs/prescribed-daily-dose-four.png") != null);
		
		arrow_list = sortList(screen.findAll("imgs/down-arrow.png"));
		for(int x = 0; x < 3; x++) {
			screen.click(arrow_list.get(0));
		}
		
		assertTrue(screen.exists("imgs/prescribed-daily-dose-default.png") != null);
	}
	
	@Test
	public void Prescribed_Daily_Dose_Over_Allowed_Limit_Stops_Add_Button() throws FindFailed {
		List<Match> arrow_list = sortList(screen.findAll("imgs/up-arrow.png"));
		for(int x = 0; x < 5; x++) {
			screen.click(arrow_list.get(0));
		}
		
		assertTrue(screen.exists("imgs/add-button-inactive.png") != null);
		Pattern pattern = new Pattern("imgs/add-button-active.png").similar(1f);
		assertFalse(screen.exists(pattern) != null);
	}
	
	@Test
	public void Prescribed_Daily_Dose_Maxes_At_Double_Recommended_Dosage() throws FindFailed {
		List<Match> arrow_list = sortList(screen.findAll("imgs/up-arrow.png"));
		for(int x = 0; x < 13; x++) {
			screen.click(arrow_list.get(0));
		}
		screen.mouseMove(100, 100);
		Pattern pattern = new Pattern("imgs/prescribed-daily-dose-ten.png").similar(1f);
		assertTrue(screen.exists(pattern) != null);
	}
}