package dao;

import domain.Cliente;

public interface IclienteDao {
	
	void createDao(Cliente cliente);
	Cliente readDao(Long cpf);
	void updateDao(Cliente cliente);
	void deleteDao(Long cpf);
	
	
	
	

}
