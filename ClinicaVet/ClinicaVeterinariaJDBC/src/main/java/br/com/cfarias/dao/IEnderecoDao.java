package br.com.cfarias.dao;

import java.util.List;

import br.com.cfarias.entity.Endereco;

public interface IEnderecoDao {
	
	public Endereco cadastrarEndereco(Endereco endereco) throws Exception;

	public Endereco consultarEndereco(Long idCliente) throws Exception;

	public void excluir(Endereco endereco) throws Exception;
	
	public Endereco atualizarEndereco(Endereco endereco) throws Exception;
	
	public List<Endereco> buscarTodos() throws Exception;

}
