package dao;

import java.util.ArrayList;
import java.util.List;

import domain.Cliente;

public class ArrayListClientDao implements IclienteDao {

	List<Cliente> listClientDao;
	
	public ArrayListClientDao() {
		listClientDao = new ArrayList<>();
	}
	
	
	public void createDao(Cliente cliente) {
		listClientDao.add(cliente);
		
	}

	
	public Cliente readDao(Long cpf) {
		return listClientDao.stream()
				.filter(cliente -> cliente.getCpf().equals(cpf))
				.findFirst()
				.orElse(null);		
		
	/**	 for (Cliente cliente : listClientDao) {
		        if (cliente.getCpf().equals(cpf)) {
		            return cliente;
		        }
		    }
		    return null;
	*/
		
	}

	
	public void updateDao(Cliente cliente) {
		
		listClientDao.stream()
        .filter(c -> c.getCpf().equals(cliente.getCpf()))
        .findFirst()
        .ifPresent(c -> {
        	c.setNome(cliente.getNome());
        	c.setEstado(cliente.getEstado());
        	}); 

		
	/**	int index = listClientDao.indexOf(cliente);

	    if (index == -1) {
	        System.out.println("Cliente inexistente \n");
	        return;
	    }

	    listClientDao.set(index, cliente);
	    ///ATENCAO, FUNCIONA APENAS COM EQUALS E HASHCODE NA ENTIDADE CLIENTE
	   */ 
		
	/**	if(readDao(cliente.getCpf()) == null) {
			System.out.println("Cliente inexistente \n");
		}
		else {
		deleteDao(cliente.getCpf());
		createDao(cliente);
		}
		//MANEIRA MENOS EFICIENTE DE IMPLEMENTAR O updateDAO, POR FAVOR, EVITAR
		
		*/ 
	}

	
	public void deleteDao(Long cpf) {
		
		listClientDao.remove(readDao(cpf));
		
	}

}
