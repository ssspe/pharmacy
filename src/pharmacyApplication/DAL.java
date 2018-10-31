package pharmacyApplication;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.sikuli.script.Match;

public class DAL implements InterfaceDAL{
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet1 = null;
	private String currentPharmaName = null;

	private Map<String, String> dict = new HashMap<String, String>();

	public DAL() {
	}
	
	@Override
	public void connect(String connectionURL, String username, String password) throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connect = DriverManager.getConnection(connectionURL, username, password);
	}
	
	@Override
	public void setCurrentPharmaName(String currentPharmaName) {
		this.currentPharmaName = currentPharmaName;
	}
	
	@Override
	public List<String> getPharmaName() throws SQLException {
		List<String> list = new ArrayList<String>();
		statement = connect.createStatement();
		resultSet1 = statement.executeQuery("select * from pharmaceuticals");
		while (resultSet1.next()) {
			list.add(resultSet1.getString("PharmaceuticalName"));
		}
		return list;
	}
	
	@Override
	public List<Medicine> getPharmaInfo() {
		if (currentPharmaName != null && !currentPharmaName.isEmpty()) {
			Statement statement;
			ResultSet resultSet = null;
			try {
				statement = connect.createStatement();
				resultSet = statement
						.executeQuery("select * from pharmaceuticals, specialrequirements where PharmaceuticalName='"
								+ currentPharmaName
								+ "' and pharmaceuticals.SpecialRequirementID=specialrequirements.SpecialRequirementID");
				List<Medicine> list = new ArrayList<>();
				while (resultSet.next()) {
					int size = resultSet.getInt("ContainerSize");
					int recDailyDose = resultSet.getInt("RecommendedDailyDose");
					String containerType = resultSet.getString("ContainerType");
					String description = resultSet.getString("Description");
					int availableOverTheCounter = resultSet.getInt("AvailableOverTheCounter");
					int storeInFridge = resultSet.getInt("StoreInFridge");
					Medicine medicine = new Medicine(size, recDailyDose, containerType, description, availableOverTheCounter, storeInFridge);
					list.add(medicine);
				}
				return list;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
