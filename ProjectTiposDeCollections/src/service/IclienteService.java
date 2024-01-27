package service;

import domain.Cliente;

public interface IclienteService {
	
	void createService(String nome, Long cpf, String estado);
	Cliente readService(Long cpf);
	void updateService(String nomeNovoCliente, Long cpfNovoCliente, String estadoNovoCliente);
	void deleteService(Long cpf);

}
