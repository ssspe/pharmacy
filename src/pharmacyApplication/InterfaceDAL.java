package pharmacyApplication;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface InterfaceDAL {
	List getPharmaName() throws SQLException;
	Medicine getPharmaInfo(String currentPharmaName);
	void connect(String connectionURL, String username, String password) throws SQLException;
}
