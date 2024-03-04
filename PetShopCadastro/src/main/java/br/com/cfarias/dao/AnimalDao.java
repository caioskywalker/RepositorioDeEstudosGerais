package br.com.cfarias.dao;

import java.sql.PreparedStatement;

import br.com.cfarias.dao.generic.GenericDao;
import br.com.cfarias.domain.Animal;

public class AnimalDao extends GenericDao<Animal, Long> implements IAnimalDao {

	@Override
	public Class<Animal> getTipoClasse() {
		
		return Animal.class;
	}

	@Override
	public void atualizarDados(Animal entity, Animal entityCadastrado) {
		
		
	}

	@Override
	protected String getQueryInsercao() {
		
		return null;
	}

	@Override
	protected String getQueryExclusao() {
	
		return null;
	}

	
	protected String getQueryAtualizacao() {
		
		return null;
	}

	@Override
	protected void setParametrosQueryInsercao(PreparedStatement stmInsert, Animal entity) {
		
		
	}

	@Override
	protected void setParametrosQueryExclusao(PreparedStatement stmExclusao, Long valor) {
		
		
	}

	@Override
	protected void setParametrosQueryAtualizacao(PreparedStatement stmUpdate, Animal entity) {
	
		
	}

	@Override
	protected void setParametrosQuerySelect(PreparedStatement stmSelect, Long valor) {
	
		
	}

}
