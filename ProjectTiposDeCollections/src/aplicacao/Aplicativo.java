package aplicacao;

import service.ClientService;
import service.IclienteService;
import java.util.Scanner;

public class Aplicativo {
	
	
	
	public Aplicativo() {
		
	}
	
	
	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		IclienteService service = new ClientService();
		
		System.out.println("Digite os dados do novo cliente \n");
		System.out.println("Digite o CPF \n");
		Long novoCpf = s.nextLong();
		String novoNome = s.next();
		String novoEstado = s.next();
		
		service.createService(novoNome,novoCpf,novoEstado); //Testando criação de cliente
		
		System.out.println("Cliente criado " + service.readService(novoCpf));
		
		System.out.println("Atualize o cliente...Digite primeiro o cpf de um cliente existente \n");
		Long cpfAtualizar = s.nextLong();
		String nomeAtualizar = s.next();
		String estadoAtualizar = s.next();
		
		service.updateService(nomeAtualizar, cpfAtualizar, estadoAtualizar); //Testando update cliente
		
		System.out.println("Cliente Atualizado " + service.readService(cpfAtualizar));
		
		service.deleteService(cpfAtualizar); //Testando delete de cliente
		
		System.out.println("Se cliente for deletado corretamente, retorno será null ... " + service.readService(cpfAtualizar));
		
		s.close();
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
