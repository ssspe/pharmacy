package com.zircon.pharmacyApplicationInterfaces;

import java.sql.SQLException;
import java.util.List;

import com.zircon.pharmacyApplication.Medicine;

public interface InterfaceDAL {
	/**
	 * Connects to the specified database using the JDBC MySQL connector.
	 * 
	 * @param connectionURL The URL and the database to connect to
	 * @param username      Username of the user that has access to the database
	 * @param password      Password of the user that has access to the database
	 */
	void connect(String connectionURL, String username, String password) throws SQLException;

	/**
	 *  Gets all of the pharmaceutical names from the pharmaceutical table.
	 * 
	 * @return (List<String>) List of pharmaceutical names.
	 */
	List getPharmaName() throws SQLException;

	/**
	 * Gets all of the information for a specified pharmaceutical.
	 * 
	 * @return (List<Medicine>) List of medicines in the database.
	 */
	Medicine getPharmaInfo(String currentPharmaName);
}
