package pharmacyApplication;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data access layer for the system. Allows some level of abstraction in case of
 * change in data source.
 * 
 * @author spencer.robertson
 */
public class DAL implements InterfaceDAL {
	private Connection connect = null;

	/**
	 * Default constructor for the class.
	 */
	public DAL() {
	}

	/**
	 * Connects to the specified database using the JDBC MySQL connector.
	 * 
	 * @param connectionURL The URL and the database to connect to
	 * @param username      Username of the user that has access to the database
	 * @param password      Password of the user that has access to the database
	 */
	@Override
	public void connect(String connectionURL, String username, String password) throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		connect = DriverManager.getConnection(connectionURL, username, password);
	}

	/**
	 * Runs the SQL command to get all items from the pharmaceuticals table and
	 * created a list from their names
	 * 
	 * @return (List<String>) List of pharmaceutical names.
	 */
	@Override
	public List<String> getPharmaName() throws SQLException {
		List<String> list = new ArrayList<String>();
		Statement statement = connect.createStatement();
		ResultSet pharmaceuticals = statement.executeQuery("select * from pharmaceuticals");
		while (pharmaceuticals.next()) {
			list.add(pharmaceuticals.getString("PharmaceuticalName"));
		}
		return list;
	}

	/**
	 * Runs the SQL command to get all items from the pharmaceuticals table and
	 * links them to the Foreign Key link in the special requirements table. Then
	 * creates a list of Medicine objects from this info.
	 * 
	 * @return (List<Medicine>) List of medicines in the database.
	 */
	@Override
	public Medicine getPharmaInfo(String currentPharmaName) {
		if (isStringOkay(currentPharmaName)) {
			Statement statement;
			ResultSet pharmaInfo = null;
			try {
				statement = connect.createStatement();
				pharmaInfo = statement
						.executeQuery("select * from pharmaceuticals, specialrequirements where PharmaceuticalName='"
								+ currentPharmaName
								+ "' and pharmaceuticals.SpecialRequirementID=specialrequirements.SpecialRequirementID");
				while (pharmaInfo.next()) {
					int size = pharmaInfo.getInt("ContainerSize");
					int recDailyDose = pharmaInfo.getInt("RecommendedDailyDose");
					String containerType = pharmaInfo.getString("ContainerType");
					String description = pharmaInfo.getString("Description");
					int availableOverTheCounter = pharmaInfo.getInt("AvailableOverTheCounter");
					int storeInFridge = pharmaInfo.getInt("StoreInFridge");
					return new Medicine(size, recDailyDose, containerType, description,
							availableOverTheCounter, storeInFridge);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * Checks whether a string is empty or null
	 * 
	 * @param string The string to check
	 * @return (boolean) The result of the check
	 */
	private boolean isStringOkay(String string) {
		return string != null && !string.isEmpty();
	}
}
