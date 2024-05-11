package br.com.cfarias.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	private static Connection connection;
	
	private ConnectionFactory(Connection connetion) {
		
		
	}
	
	public static Connection getConnection() throws SQLException {
		if(connection == null || connection.isClosed()) {
			connection = initConnection();
			return connection;
		}
		else {
			return connection;
		}
	}
	
	
	
	private static Connection initConnection() {
		try {
			return DriverManager.getConnection("jdbc:postgresql://localhost:5432/ClinicaVet", "postgres" , "postgres");
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao tentar se conectar...");
		}
	}

}
