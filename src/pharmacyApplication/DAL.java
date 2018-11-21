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
	 * {@inheritDoc}
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
	 * {@inheritDoc} Runs the SQL command to get all items from the pharmaceuticals
	 * table and created a list from their names
	 * 
	 * @return (List<String>) List of pharmaceutical names.
	 */
	@Override
	public List<String> getPharmaName() throws SQLException {
		List<String> list = new ArrayList<String>();
		Statement statement = connect.createStatement();
		// This SQL command will get all rows from the pharmaceuticals table
		ResultSet pharmaceuticals = statement.executeQuery("select * from pharmaceuticals");
		while (pharmaceuticals.next()) {
			list.add(pharmaceuticals.getString("PharmaceuticalName"));
		}
		return list;
	}

	/**
	 * {@inheritDoc} Runs the SQL command to get all items from the pharmaceuticals
	 * table and links them to the Foreign Key link in the special requirements
	 * table.
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
				// This SQL command will return a row containing each row form the
				// pharmaceuticals table and additional info from specialrequirements if the
				// SpecialRequirementID matches
				pharmaInfo = statement
						.executeQuery("select * from pharmaceuticals, specialrequirements where PharmaceuticalName='"
								+ currentPharmaName
								+ "' and pharmaceuticals.SpecialRequirementID=specialrequirements.SpecialRequirementID");
				return createMedicine(pharmaInfo);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * Creates a Medicine object from a result set.
	 * @param pharmaInfo Contains information on the medicine
	 * @return A medicine object
	 * @throws SQLException
	 */
	private Medicine createMedicine(ResultSet pharmaInfo) throws SQLException {
		pharmaInfo.first();
		int size = pharmaInfo.getInt("ContainerSize");
		int recDailyDose = pharmaInfo.getInt("RecommendedDailyDose");
		String containerType = pharmaInfo.getString("ContainerType");
		String medicationType = pharmaInfo.getString("MedicationType");
		String description = pharmaInfo.getString("Description");
		int availableOverTheCounter = pharmaInfo.getInt("AvailableOverTheCounter");
		int storeInFridge = pharmaInfo.getInt("StoreInFridge");
		return new Medicine(size, recDailyDose, containerType, medicationType, description, availableOverTheCounter,
				storeInFridge);
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
