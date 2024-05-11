package br.com.cfarias.dao;

import java.util.List;

import br.com.cfarias.entity.Animal;

public interface IAnimalDao {

	public Animal cadastrarAnimal(Animal animal) throws Exception;

	public Animal consultarAnimal(Long idAnimal) throws Exception;

	public void excluir(Animal animal) throws Exception;
	
	public Animal atualizarAnimal(Animal animal) throws Exception;
	
	public List<Animal> buscarTodos() throws Exception;
	
	
}
