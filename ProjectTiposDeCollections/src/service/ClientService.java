package service;

import dao.ArrayListClientDao;
import dao.HashMapClientDao;
import dao.IclienteDao;
import domain.Cliente;

public class ClientService implements IclienteService {

	IclienteDao clienteDao;
	
	public ClientService() {
		//clienteDao = new HashMapClientDao();
		clienteDao = new ArrayListClientDao();
	}

	
	public void createService(String nomeNovoCliente, Long cpfNovoCliente, String estadoNovoCliente) {
		Cliente criarCliente = new Cliente(nomeNovoCliente,cpfNovoCliente,estadoNovoCliente);
		clienteDao.createDao(criarCliente);
		
	}

	
	public Cliente readService(Long cpf) {
		return clienteDao.readDao(cpf);
	}


	public void updateService(String nomeNovoCliente, Long cpfNovoCliente, String estadoNovoCliente) {
		Cliente atualizarCliente = new Cliente(nomeNovoCliente,cpfNovoCliente,estadoNovoCliente);
		clienteDao.updateDao(atualizarCliente);
		
	}


	public void deleteService(Long cpf) {
		clienteDao.deleteDao(cpf);
		
	}
	
	
	
	

}
