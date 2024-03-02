package br.com.cfarias.dao.generic;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collection;

import br.com.cfarias.persistente.Persistente;

public interface IGenericDao <T extends Persistente , E extends Serializable> {
	
	public Boolean cadastrar(T entity) throws SQLException;
	
	public void excluir(E valor) throws SQLException;
	
	public void alterar(T entity) throws SQLException;
	
	public T consultar(E valor);
	
	public Collection<T> buscarTodos();

}
