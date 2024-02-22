package com.dbutility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 */
@Slf4j
public class DBUtility {

	private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/studyhubdb";
	private static final String USERNAME = "postgres";
	private static final String PASSWORD = "silicon";

	/**
	 * Fetching data from the database based on a SQL query.
	 *
	 * @param sqlQuery   - SQL query to execute.
	 * @param condition  - condition in where clause.
	 * @return A list of strings representing the fetched data.
	 */
	public static List<String> fetchData(String sqlQuery, String condition) {
		List<String> data = new ArrayList<>();

		try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
				PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {

			preparedStatement.setString(1, condition);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					// Assuming we have to fetch single column result set  
					String value = resultSet.getString(1);
					data.add(value);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return data;
	}

	public static void main(String[] args) {

		String sqlQuery = "SELECT email_id FROM user_details WHERE first_name = ?";
		String condition = "sumit";

		log.info("Fetching data from studyhub database");
		List<String> result = fetchData(sqlQuery, condition);

		log.info("Fetched Data : " + result);
	}
}
