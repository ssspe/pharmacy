package pharmacyApplication;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface InterfaceDAL {
	List getPharmaName() throws SQLException;
	List getPharmaInfo();
	void connect(String connectionURL, String username, String password) throws SQLException;
	void setCurrentPharmaName(String currentPharmaName);
}
