package pharmacyApplicationUITesting;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.sikuli.script.Match;

import com.sun.java.swing.plaf.windows.resources.windows;

import pharmacyApplication.PrescriptionUI;

public class HelperFunctions {
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
