package dao;

import java.util.HashMap;
import java.util.Map;

import domain.Cliente;

public class HashMapClientDao implements IclienteDao {

	Map<Long, Cliente> mapClientDao;
	
	
	public HashMapClientDao() {
		super();
		mapClientDao = new HashMap<Long, Cliente>();
	}


	public void createDao(Cliente cliente) {
		mapClientDao.put(cliente.getCpf(), cliente);
		
	}

	
	public Cliente readDao(Long cpf) {
		return mapClientDao.get(cpf);
	}

	
	public void updateDao(Cliente cliente) {
		mapClientDao.replace(cliente.getCpf(), cliente);
	}

	
	public void deleteDao(Long cpf) {
		mapClientDao.remove(cpf);
	}
	
	
	
	
	

}
