package br.com.cfarias.dao;

import java.util.List;

import br.com.cfarias.entity.Cliente;

public interface IClienteDao {

	public Cliente cadastrarCliente(Cliente cliente) throws Exception;

	public Cliente consultarCliente(Long cpf) throws Exception;

	public void excluir(Cliente cliente) throws Exception;
	
	public Cliente atualizarCliente(Cliente cliente) throws Exception;
	
	public List<Cliente> buscarTodos() throws Exception;

	public void excluirTodosClientes() throws Exception;
	
	
	
}
