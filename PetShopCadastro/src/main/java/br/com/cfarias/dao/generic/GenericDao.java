package br.com.cfarias.dao.generic;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import br.com.cfarias.anotations.ColunaTabela;
import br.com.cfarias.anotations.Tabela;
import br.com.cfarias.anotations.TipoChave;
import br.com.cfarias.persistente.Persistente;

public abstract class GenericDao<T extends Persistente, E extends Serializable> implements IGenericDao<T, E> {

	public abstract Class<T> getTipoClasse();

	public abstract void atualizarDados(T entity, T entityCadastrado);

	protected abstract String getQueryInsercao();

	protected abstract String getQueryExclusao();

	protected abstract String getQueryAtualizacao();

	protected abstract void setParametrosQueryInsercao(PreparedStatement stmInsert, T entity);

	protected abstract void setParametrosQueryExclusao(PreparedStatement stmExclusao, E valor);

	protected abstract void setParametrosQueryAtualizacao(PreparedStatement stmUpdate, T entity);

	protected abstract void setParametrosQuerySelect(PreparedStatement stmSelect, E valor);

	public GenericDao() {

	}

	public String getNomeCampoChave(Class clazz) {
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			if (field.isAnnotationPresent(TipoChave.class) && field.isAnnotationPresent(ColunaTabela.class)) {
				ColunaTabela coluna = field.getAnnotation(ColunaTabela.class);
				return coluna.dbName();
			}
		}
		return null;
	}

	private Object getValueByType(Class<?> typeField, ResultSet rs, String fieldName)
			throws ClassNotFoundException, SQLException {
		if (typeField.equals(Integer.TYPE)) {
			return rs.getInt(fieldName);
		} else if (typeField.equals(Long.TYPE)) {
			return rs.getLong(fieldName);
		} else if (typeField.equals(Double.TYPE)) {
			return rs.getDouble(fieldName);
		} else if (typeField.equals(Short.TYPE)) {
			return rs.getShort(fieldName);
		} else if (typeField.equals(BigDecimal.class)) {
			return rs.getBigDecimal(fieldName);
		} else if (typeField.equals(String.class)) {
			return rs.getString(fieldName);
		} else {
			throw new ClassNotFoundException("TIPO DE CLASSE NÃO CONHECIDO: " + typeField);
		}
	}

	private void setValueByType(T entity, Method method, Class<?> classField, ResultSet rs, String fieldName)
			throws SQLException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
		if (classField.equals(Integer.class)) {

			Integer val = rs.getInt(fieldName);
			method.invoke(entity, val);
		} else if (classField.equals(Long.class)) {
			Long val = rs.getLong(fieldName);
			method.invoke(entity, val);
		} else if (classField.equals(Double.class)) {
			Double val = rs.getDouble(fieldName);
			method.invoke(entity, val);
		} else if (classField.equals(Short.class)) {
			Short val = rs.getShort(fieldName);
			method.invoke(entity, val);
		} else if (classField.equals(BigDecimal.class)) {
			BigDecimal val = rs.getBigDecimal(fieldName);
			method.invoke(entity, val);
		} else if (classField.equals(String.class)) {
			String val = rs.getString(fieldName);
			method.invoke(entity, val);
		} else {
			throw new ClassNotFoundException("TIPO DE CLASSE NÃO CONHECIDO: " + classField);
		}
	}

	private String getTableName() throws Exception {
		if (getTipoClasse().isAnnotationPresent(Tabela.class)) {
			Tabela table = getTipoClasse().getAnnotation(Tabela.class);
			return table.value();
		} else {
			throw new Exception("Tabela no tipo" + getTipoClasse().getName() + " não foi encontrada");
		}
	}

	public E getChave(T entity) throws Exception {
		Field[] fields = entity.getClass().getDeclaredFields();
		E returnValue = null;
		for (Field field : fields) {
			if (field.isAnnotationPresent(TipoChave.class)) {
				TipoChave tipoChave = field.getAnnotation(TipoChave.class);
				String nomeMetodo = tipoChave.chave();// getCodigo
				try {
					Method method = entity.getClass().getMethod(nomeMetodo);
					returnValue = (E) method.invoke(entity);
					return returnValue;
				} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
					e.printStackTrace();
					throw new Exception("Chave não encontrada.");
				}
			}
		}
		if (returnValue == null) {
			String msg = "Chave principal do objeto " + entity.getClass() + " não encontrada";
			System.out.println("******ERRO*****" + msg);
			throw new Exception(msg);
		}
		return null;
	}

	protected Connection getConnection() throws SQLException {
		try {
			return ConnectionFactory.getConnection();
		} catch (SQLException e) {
			throw new SQLException("Erro ao abrir conexão com DB");
		}

	}

	protected void closeConnection(Connection connection, PreparedStatement stm, ResultSet rs) {

		try {
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}

			if (stm != null && !stm.isClosed()) {
				stm.close();
			}
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private Long validarMaisDeUmRegistro(E valor) throws Exception {
		Connection connection = getConnection();
		PreparedStatement stm = null;
		ResultSet rs = null;
		Long count = null;
		try {
			stm = connection.prepareStatement(
					"SELECT count(*) FROM" + getTableName() + " WHERE " + getNomeCampoChave(getTipoClasse()) + " = ?");
			setParametrosQuerySelect(stm, valor);
			rs = stm.executeQuery();
			if (rs.next()) {
				count = rs.getLong(1);
				if (count > 1) {
					throw new SQLException("Encontrado mais de um registro de " + getTableName());

				}
			}

			return count;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection, stm, rs);
		}
		return count;
	}

	@Override
	public Boolean cadastrar(T entity) throws SQLException {
		Connection connection = null;
		PreparedStatement stm = null;

		try {
			connection = getConnection();
			stm = connection.prepareStatement(getQueryInsercao(), Statement.RETURN_GENERATED_KEYS);
			setParametrosQueryInsercao(stm, entity);
			int rowsAffected = stm.executeUpdate();

			if (rowsAffected > 0) {
				try (ResultSet rs = stm.getGeneratedKeys()) {
					if (rs.next()) {
						Persistente per = (Persistente) entity;
						per.setId(rs.getLong(1));
					}
				}
				return true;
			}

		} catch (SQLException E) {
			throw new SQLException("Erro ao cadastrar objeto");
		} finally {
			closeConnection(connection, stm, null);
		}

		return false;
	}

	@Override
	public void excluir(E valor) throws SQLException {
		Connection connection = getConnection();
		PreparedStatement stm = null;
		try {
			stm = connection.prepareStatement(getQueryExclusao());
			setParametrosQueryExclusao(stm, valor);
			stm.executeUpdate();

		} catch (SQLException e) {
			throw new SQLException("Erro ao excluir objeto ", e);

		} finally {
			closeConnection(connection, stm, null);

		}
	}

	@Override
	public void alterar(T entity) throws SQLException {
		Connection connection = getConnection();
		PreparedStatement stm = null;
		try {
			stm = connection.prepareStatement(getQueryAtualizacao());
			setParametrosQueryAtualizacao(stm, entity);
			stm.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException("Erro ao alterar objeto", e);
		} finally {
			closeConnection(connection, stm, null);
		}

	}

	@Override
	public T consultar(E valor) throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		ResultSet rs = null;

		try {
			validarMaisDeUmRegistro(valor);
			connection = getConnection();
			stm = connection.prepareStatement(
					"SELECT * FROM " + getTableName() + " WHERE " + getNomeCampoChave(getTipoClasse()) + " = ?");
			setParametrosQuerySelect(stm, valor);
			rs = stm.executeQuery();
			if (rs.next()) {
				T entity = getTipoClasse().getConstructor(null).newInstance(null);
				Field[] fields = entity.getClass().getDeclaredFields();
				for (Field field : fields) {
					if (field.isAnnotationPresent(ColunaTabela.class)) {
						ColunaTabela coluna = field.getAnnotation(ColunaTabela.class);
						String dbName = coluna.dbName();
						String javaSetName = coluna.setJavaName();
						Class<?> classField = field.getType();
						try {
							Method method = entity.getClass().getMethod(javaSetName, classField);
							setValueByType(entity, method, classField, rs, dbName);
						} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
							throw new Exception("Erro ao consultar objeto ", e);
						} catch (ClassNotFoundException e) {
							throw new Exception("Erro ao consultar objeto", e);
						}
					}
				}
				return entity;
			}
		} catch (SQLException | InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException | SecurityException | TypeNotPresentException e) {
			throw new Exception("Erro ao consultar objeto ", e);
		} finally {

			closeConnection(connection, stm, rs);
		}

		return null;
	}

	@Override
	public Collection<T> buscarTodos() throws Exception {
		List<T> list = new ArrayList<>();
		Connection connection = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
        try {
		
			connection = getConnection();
			stm = connection.prepareStatement("SELECT * FROM " + getTableName());
			rs = stm.executeQuery();
		    while (rs.next()) {
		    	T entity = getTipoClasse().getConstructor(null).newInstance(null);
		    	Field[] fields = entity.getClass().getDeclaredFields();
		        for (Field field : fields) {
		        	if (field.isAnnotationPresent(ColunaTabela.class)) {
		        		ColunaTabela coluna = field.getAnnotation(ColunaTabela.class);
		                String dbName = coluna.dbName();
		                String javaSetName = coluna.setJavaName();
		                Class<?> classField = field.getType();
		        		try {
		                    Method method = entity.getClass().getMethod(javaSetName, classField);
		                    setValueByType(entity, method, classField, rs, dbName);
		                    
		                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
		                	throw new NoSuchMethodException();
		                } catch (TypeNotPresentException e) {
		                	throw new TypeNotPresentException("ERRO LISTANDO OBJETOS ", e);
						}
		        	}
		        }
		        list.add(entity);
		        
		    }
	    
		} catch (SQLException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			throw new SQLException("ERRO LISTANDO OBJETOS ", e);
		} finally {
			closeConnection(connection, stm, rs);
		}
		return list;
    }

}
