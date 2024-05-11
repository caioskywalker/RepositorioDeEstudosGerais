package br.com.cfarias.entity;

public class Animal {
	
	private Long idAnimal;
	
	private String nome;
	
	private String raca;
	
	private Integer idade;
	
	private Cliente cliente;
	
	public Animal() {
		
	}
	
	public Animal(Long idAnimal, String nome, String raca, Integer idade, Cliente cliente) {
		super();
		this.idAnimal = idAnimal;
		this.nome = nome;
		this.raca = raca;
		this.idade = idade;
		this.cliente = cliente;
	}
	
	public Long getIdAnimal() {
		return idAnimal;
	}

	public void setIdAnimal(Long idAnimal) {
		this.idAnimal = idAnimal;
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

	public void setRaca(String raca) {
		this.raca = raca;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
	
	
	

}
