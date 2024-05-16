package br.com.cfarias.testes;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;

import org.junit.jupiter.api.BeforeAll;


import br.com.cfarias.dao.ClienteDao;
import br.com.cfarias.dao.IClienteDao;
import br.com.cfarias.entity.Cliente;

public class ClienteTestes {

	private static IClienteDao clienteDao = new ClienteDao();
	

	@BeforeAll
	public static void cadastrarClienteTeste() throws Exception {
		
	assertNotNull(clienteDao.cadastrarCliente(new Cliente("Caio", 28, 12345L, 33331060L)));
	System.out.println("Cliente Cadastrado... \n");
	
	}

	@AfterAll
	public static void deletarClienteTeste() throws Exception {
		
		clienteDao.excluir(clienteDao.consultarCliente(12345L));
		assertNull(clienteDao.consultarCliente(12345L));
		clienteDao.excluirTodosClientes();
		
		System.out.println("Cliente excluido \n" );
	}

	@Test
	public void buscarClienteTeste() throws Exception {
		Cliente cli = clienteDao.consultarCliente(12345L);
		assertNotNull(cli);
		
		System.out.println("Cliente encontrado.. \n");
	}
	
	@Test
	public void atualizarClienteTeste() throws Exception{
		
		Cliente cliente = clienteDao.consultarCliente(12345L);
		Cliente clienteAtual = clienteDao.atualizarCliente(new Cliente("Joao",25,12345L,68989L));
		
		System.out.println(cliente + "\n");
		System.out.println(clienteAtual + "\n");
		assertNotEquals(cliente, clienteAtual);
		
	}
	
	@Test
	public void buscarTodosClientesTeste() throws Exception {
		
		clienteDao.cadastrarCliente(new Cliente("jonathan", 25 , 4568l , 1458l));
		clienteDao.cadastrarCliente(new Cliente("pedro", 27 , 45558l , 2158l));
		List<Cliente> clientes = clienteDao.buscarTodos();
	
		System.out.println("Resultado buscar Todos \n");
		clientes.forEach(System.out::println);
		System.out.println("....\n");
		
		
		assertNotNull(clientes);
	}

}
