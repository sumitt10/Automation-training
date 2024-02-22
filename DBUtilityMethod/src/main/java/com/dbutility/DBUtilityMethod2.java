package com.dbutility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
 * Class having Utility method to fetch the data from database for parameterized query with specific condition.
 * It is designed for scenarios where customized-column result set is expected according to query.
 */
@Slf4j
public class DBUtilityMethod2 {

    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/studyhubdb";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "silicon";

    /**
     * Fetch and customize data from the database based on a SQL query.
     *
     * @param sqlQuery   - SQL query to execute.
     * @param condition  - condition for the query.
     * @return A list of arrays representing rows with multiple column values.
     */
    public static List<String[]> fetchData(String sqlQuery, String condition) {
        List<String[]> data = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {

            preparedStatement.setString(1, condition);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // Get metadata about the result set (counting no. of columns)
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();

                while (resultSet.next()) {
                    // Created an array of size equal to columns to store row values
                    String[] rowData = new String[columnCount];

                    // Fetching data for each column in the result set
                    for (int i = 1; i <= columnCount; i++) {
                        rowData[i - 1] = resultSet.getString(i);
                    }

                    data.add(rowData);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }

    public static void main(String[] args) {
        String sqlQuery = "SELECT user_id, first_name, last_name FROM user_details WHERE user_id = CAST(? AS bigint)";
        String condition = "2";

        List<String[]> result = fetchData(sqlQuery, condition);

        for (String[] rowData : result) {
            log.info("Fetched Data: " + String.join(" | ", rowData));
        }
    }
}
