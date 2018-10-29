package pharmacyApplication;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DAL {
	private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet1 = null;
    private String currentPharmaName = null;
    
    private Map<String, String> dict = new HashMap<String, String>();
    
	public DAL() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/doc-u-med", "spencer", "password");

			statement = connect.createStatement();
			resultSet1 = statement.executeQuery("select * from pharmaceuticals");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setCurrentPharmaName(String currentPharmaName) {
		this.currentPharmaName = currentPharmaName;
	}
	
	public List<String> getPharmaName() throws SQLException {
		List<String> list = new ArrayList<String> ();   
		while (resultSet1.next()) {
			list.add(resultSet1.getString("PharmaceuticalName"));
		}
		return list;
	}
	
	public ResultSet getPharmaInfo() {
		if (currentPharmaName != null && !currentPharmaName.isEmpty()) {
			Statement statement;
			ResultSet resultSet = null;
			try {
				statement = connect.createStatement();
				resultSet = statement.executeQuery("select * from pharmaceuticals, specialrequirements where PharmaceuticalName='" + currentPharmaName + "' and pharmaceuticals.SpecialRequirementID=specialrequirements.SpecialRequirementID");
				return resultSet;
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
		return null;
	}
	
	public void populateUI() throws SQLException {
        // ResultSet is initially before the first data set
        while (resultSet1.next()) {
            // It is possible to get the columns via name
            // also possible to get the columns via the column number
            // which starts at 1
            // e.g. resultSet.getSTring(2);
            String PharmaceuticalID = resultSet1.getString("PharmaceuticalID");
            System.out.println("PharmaceuticalID: " + PharmaceuticalID);
            
            String SpecialRequirementID = resultSet1.getString("SpecialRequirementID");
            System.out.println("SpecialRequirementID: " + SpecialRequirementID);
            
            String PharmaceuticalName = resultSet1.getString("PharmaceuticalName");
            System.out.println("PharmaceuticalName: " + PharmaceuticalName);
            
            String Description = resultSet1.getString("Description");
            System.out.println("Description: " + Description);
            
            String MedicationType = resultSet1.getString("MedicationType");
            System.out.println("MedicationType: " + MedicationType);
            
            String RecommendedDailyDose = resultSet1.getString("RecommendedDailyDose");
            System.out.println("RecommendedDailyDose: " + RecommendedDailyDose);
            
            System.out.println("");
        }
    }
}
