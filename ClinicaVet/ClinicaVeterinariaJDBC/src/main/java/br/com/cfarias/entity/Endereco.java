package br.com.cfarias.entity;

public class Endereco {
	
	private Long idEndereco;
	
	private String estado;
	
	private String cidade;
	
	private Long cep;
	
	private String nomeEndereco;
	
	private Integer numero;
	
	
	public Endereco() {
		
	}

	public Endereco(Long idEndereco, String estado, String cidade, Long cep, String nomeEndereco, Integer numero) {
		super();
		this.idEndereco = idEndereco;
		this.estado = estado;
		this.cidade = cidade;
		this.cep = cep;
		this.nomeEndereco = nomeEndereco;
		this.numero = numero;
	}

	public Long getIdEndereco() {
		return idEndereco;
	}

	public void setIdEndereco(Long idEndereco) {
		this.idEndereco = idEndereco;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public Long getCep() {
		return cep;
	}

	public void setCep(Long cep) {
		this.cep = cep;
	}

	public String getNomeEndereco() {
		return nomeEndereco;
	}

	public void setNomeEndereco(String nomeEndereco) {
		this.nomeEndereco = nomeEndereco;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	
	
	
	
	
	
	
	
	
	

}
