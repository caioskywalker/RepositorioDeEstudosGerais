package br.com.cfarias.dao.generic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	private static Connection connection;

	private ConnectionFactory(Connection connection) {

	}

	public static Connection getConnection() throws SQLException {
		if (connection == null) {
			connection = initConnection();
			return connection;
		}

		else if (connection.isClosed()) {
			connection = initConnection();
			return connection;
		}

		else {
			return connection;
		}
	}

	private static Connection initConnection() {
		try {
			return DriverManager.getConnection("jdbc:postgresql://localhost:5432/petshop", "postgres", "34679352");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
