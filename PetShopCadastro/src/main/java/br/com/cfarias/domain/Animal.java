package br.com.cfarias.domain;

import java.time.Instant;

import br.com.cfarias.anotations.ColunaTabela;
import br.com.cfarias.anotations.TipoChave;
import br.com.cfarias.persistente.Persistente;

public class Animal implements Persistente {
	
	@ColunaTabela(dbName = "id_animal" , setJavaName = "setId")
	private Long idAnimal;
	
	@TipoChave(chave = "getCodigoAnimal")
	@ColunaTabela(dbName = "codigo_animal" , setJavaName = "setCodigoAnimal")
	private Long codigoAnimal;
	
	@ColunaTabela(dbName = "nome" , setJavaName = "setNome")
	private String nome;
	@ColunaTabela(dbName = "raca" , setJavaName = "setRaca")
	private String raca;
	@ColunaTabela(dbName = "idade" , setJavaName = "setIdade")
	private Long idade;
	
	@ColunaTabela(dbName = "data_nascimento" , setJavaName = "setDataNascimento")
	private Instant dataNascimento;
	
	@ColunaTabela(dbName = "cliente" , setJavaName = "getCliente")
	private Cliente cliente;

	public Long getId() {
		return idAnimal;
	}

	public void setId(Long idAnimal) {
		this.idAnimal = idAnimal;
	}

	public Long getCodigoAnimal() {
		return codigoAnimal;
	}

	public void setCodigoAnimal(Long codigoAnimal) {
		this.codigoAnimal = codigoAnimal;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRaca() {
		return raca;
	}

	public void setRaça(String raça) {
		this.raca = raça;
	}

	public Long getIdade() {
		return idade;
	}

	public void setIdade(Long idade) {
		this.idade = idade;
	}

	public Instant getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Instant dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
	
	
	

}
