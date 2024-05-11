package br.com.cfarias.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.cfarias.dao.jdbc.ConnectionFactory;
import br.com.cfarias.entity.Cliente;

public class ClienteDao implements IClienteDao {

	@Override
	public Cliente cadastrarCliente(Cliente cliente) throws Exception {
		Connection con = null;
		PreparedStatement stm = null;
		try {
			con = ConnectionFactory.getConnection();
			stm = con.prepareStatement(
					"INSERT INTO tb_cliente (idCliente, nome, idade, cpf, telefone) VALUES (NEXTVAL('seq_cliente'),?,?,?,?)");
			stm.setString(1, cliente.getNome());
			stm.setInt(2, cliente.getIdade());
			stm.setLong(3, cliente.getCpf());
			stm.setLong(4, cliente.getTelefone());
			stm.executeUpdate();

		} catch (Exception e) {

		}

		return consultarCliente(cliente.getCpf());
	}

	@Override
	public Cliente consultarCliente(Long cpf) throws Exception {
		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		Cliente clienteEncontrado = null;
		try {
			con = ConnectionFactory.getConnection();
			stm = con.prepareStatement("SELECT * FROM tb_cliente WHERE cpf = ?");
			stm.setLong(1, cpf);
			rs = stm.executeQuery();
			if (rs.next()) {
				clienteEncontrado = new Cliente(rs.getString("nome"), rs.getInt("idade"),
						rs.getLong("cpf"), rs.getLong("telefone"));
				clienteEncontrado.setIdCliente(rs.getLong("idCliente"));
			}
		} catch (Exception e) {
			throw e;
		} finally {
			fecharConexao(con, stm);
		}

		return clienteEncontrado;
	}

	@Override
	public void excluir(Cliente cliente) throws Exception {
		Connection con = null;
		PreparedStatement stm = null;
		try {
			con = ConnectionFactory.getConnection();
			stm = con.prepareStatement("DELETE FROM tb_cliente WHERE cpf = ?");
			stm.setLong(1, cliente.getCpf());
			stm.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			fecharConexao(con, stm);
		}

	}

	@Override
	public Cliente atualizarCliente(Cliente cliente) throws Exception {
		Connection con = null;
		PreparedStatement stm = null;

		if (consultarCliente(cliente.getCpf()) == null) {
			throw new Exception("Cliente Inexistente");
		}
		try {
			con = ConnectionFactory.getConnection();
			String sql = """
					UPDATE tb_cliente
					SET nome = ?
					SET idade = ?
					set telefone = ?
					WHERE cpf = ?;
					""";
			stm = con.prepareStatement(sql);
			stm.setString(1, cliente.getNome());
			stm.setInt(2, cliente.getIdade());
			stm.setLong(3, cliente.getTelefone());
			stm.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			fecharConexao(con, stm);
		}

		return consultarCliente(cliente.getCpf());
	}

	@Override
	public List<Cliente> buscarTodos() throws Exception {
		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		List<Cliente> resultList = new ArrayList<Cliente>();
		try {
			con = ConnectionFactory.getConnection();
			stm = con.prepareStatement("SELECT * FROM tb_cliente");
			rs = stm.executeQuery();
			while(rs.next()) {
				resultList.add(new Cliente(rs.getLong("idCliente"), rs.getString("nome"), rs.getInt("idade"), rs.getLong("cpf"), rs.getLong("telefone")));
			}
			
		}catch(Exception e) {
			throw e;
		}finally {
			fecharConexao(con, stm);
		}
		return resultList;
	}

	public void fecharConexao(Connection connection, PreparedStatement stm) throws SQLException {

		if (stm != null && !stm.isClosed()) { // se o PreparedStatement não for nulo, ou não estiver fechado, feche-o!
			stm.close();
		}
		if (connection != null && !connection.isClosed()) { // se a Conexão não for nula, ou não estiver fechada,
															// feche-a!
			connection.close();
		}
	}

}
