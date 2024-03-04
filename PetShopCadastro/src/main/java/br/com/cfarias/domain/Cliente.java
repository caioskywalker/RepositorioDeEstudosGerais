package br.com.cfarias.domain;

import java.time.Instant;
import java.util.List;

import br.com.cfarias.anotations.ColunaTabela;
import br.com.cfarias.persistente.Persistente;

public class Cliente implements Persistente {
	
	@ColunaTabela(dbName = "id_cliente" , setJavaName = "setId")
	private Long idCliente;
	
	@ColunaTabela(dbName = "codigo_cliente" , setJavaName = "setCodigoCliente")
	private Long codigoCliente;
	
	@ColunaTabela(dbName = "nome" , setJavaName = "setNome")
	private String nome;
	
	@ColunaTabela(dbName = "idade" , setJavaName = "setIdade")
	private Long idade;
	
	@ColunaTabela(dbName = "data" , setJavaName = "setDataNascimento")
	private Instant dataNascimento;
	
	@ColunaTabela(dbName = "animal" , setJavaName = "setAnimal")
	private List<Animal> animal;

	public Long getId() {
		return idCliente;
	}

	public void setId(Long idCliente) {
		this.idCliente = idCliente;
	}

	public Long getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(Long codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public List<Animal> getAnimal() {
		return animal;
	}

	public void setAnimal(List<Animal> animal) {
		this.animal = animal;
	}


	
	
	

}
