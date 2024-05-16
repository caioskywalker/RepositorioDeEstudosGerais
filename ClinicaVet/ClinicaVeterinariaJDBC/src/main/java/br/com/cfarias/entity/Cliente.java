package br.com.cfarias.entity;

import java.util.Set;

public class Cliente {
	
	private Long idCliente;

	private String nome;
	
	private Integer idade;
	
	private Long cpf;
	
	private long telefone;
	
	private Endereco endereco;
	
	private Set<Animal> animais;
	
	
	
	public Cliente() {
		
	}
	
	
	
	public Cliente(String nome, Integer idade, Long cpf, Long telefone) {
		super();
		this.nome = nome;
		this.idade = idade;
		this.cpf = cpf;
		this.telefone = telefone;
	}
	
	


	public Cliente(Long idCliente, String nome, Integer idade, Long cpf, long telefone) {
		super();
		this.idCliente = idCliente;
		this.nome = nome;
		this.idade = idade;
		this.cpf = cpf;
		this.telefone = telefone;
	
	}



	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public long getTelefone() {
		return telefone;
	}

	public void setTelefone(long telefone) {
		this.telefone = telefone;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Set<Animal> getAnimais() {
		return animais;
	}

	public void setAnimais(Set<Animal> animais) {
		this.animais = animais;
	}



	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", nome=" + nome + ", idade=" + idade + ", cpf=" + cpf
				+ ", telefone=" + telefone + ", endereco=" + endereco + ", animais=" + animais + "]";
	}
	
	

	
	
	
	
	
}
