package br.com.cfarias.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
	
	public static void fecharConexao(Connection connection, PreparedStatement stm) throws SQLException {

		if (stm != null && !stm.isClosed()) { // se o PreparedStatement não for nulo, ou não estiver fechado, feche-o!
			stm.close();
		}
		if (connection != null && !connection.isClosed()) { // se a Conexão não for nula, ou não estiver fechada,
															// feche-a!
			connection.close();
		}
	}

}
