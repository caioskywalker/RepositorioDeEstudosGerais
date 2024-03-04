package br.com.cfarias.dao;

import java.sql.PreparedStatement;

import br.com.cfarias.dao.generic.GenericDao;
import br.com.cfarias.domain.Cliente;

public class ClienteDao extends GenericDao<Cliente, Long> implements IClienteDao{

	@Override
	public Class<Cliente> getTipoClasse() {
		
		return Cliente.class;
	}

	@Override
	public void atualizarDados(Cliente entity, Cliente entityCadastrado) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String getQueryInsercao() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getQueryExclusao() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getQueryAtualizacao() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setParametrosQueryInsercao(PreparedStatement stmInsert, Cliente entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setParametrosQueryExclusao(PreparedStatement stmExclusao, Long valor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setParametrosQueryAtualizacao(PreparedStatement stmUpdate, Cliente entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setParametrosQuerySelect(PreparedStatement stmSelect, Long valor) {
		// TODO Auto-generated method stub
		
	}

}
