package com.zircon.pharmacyApplicationTest;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.sikuli.script.Match;

import com.zircon.pharmacyApplication.PrescriptionItem;
import com.zircon.pharmacyApplication.PrescriptionUI;

public class HelperFunctions {

	public static void checkMedicine(PrescriptionItem prescriptionItem, String pharmaName, int dailyDose, int duration,
			boolean overTheCounter, String comment) {
		assertEquals(prescriptionItem.getPharmaceuticalName(), pharmaName);
		assertEquals(prescriptionItem.getPrescribedDailyDose(), dailyDose);
		assertEquals(prescriptionItem.getDuration(), duration);
		assertEquals(prescriptionItem.isAvailableOverTheCounter(), overTheCounter);
		assertEquals(prescriptionItem.getComments(), comment);
	}
	
	private static PrescriptionUI window;
	public static class PlacementSorter implements Comparator<Match>{

        public int compare(Match one, Match another){
            return Integer.compare(one.x, another.x);
        }

	}
	
	public static List<Match> sortList(Iterator<Match> iterator) {
		List<Match> list = new ArrayList<>();
		iterator.forEachRemaining(list::add);

		Collections.sort(list, new PlacementSorter());
		return list;
	}
	
	public static void setUpUI() throws SQLException {
		window = new PrescriptionUI("", "", "");
		window.frame.setVisible(true);
		window.frame.setResizable(false);
	}
	
	public static void closeUI() {
		window.frame.dispose();
	}
}