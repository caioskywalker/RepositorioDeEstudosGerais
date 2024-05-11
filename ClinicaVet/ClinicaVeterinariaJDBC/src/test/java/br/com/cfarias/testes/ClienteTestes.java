package br.com.cfarias.testes;

import  org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import br.com.cfarias.dao.ClienteDao;
import br.com.cfarias.dao.IClienteDao;
import br.com.cfarias.entity.Cliente;

public class ClienteTestes {
	
	IClienteDao clienteDao;
	Cliente cliente;
	
	public ClienteTestes() {
		clienteDao = new ClienteDao();
		cliente = new Cliente();
		
	}
	
	@Test
	public void cadastrarClienteTeste() throws Exception {
		Cliente cliente1 = new Cliente("Caio", 28, 12345L, 33331060L);
		Cliente cli = clienteDao.cadastrarCliente(cliente1);
		assertNotNull(cli);
	}

}
