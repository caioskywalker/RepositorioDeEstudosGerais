package domain;

import java.util.Objects;

public class Cliente {
	
	
	private String nome;
    private Long cpf;
	private String estado;
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Long getCpf() {
		return cpf;
	}
	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public String toString() {
		return "Cliente [nome=" + nome + ", cpf=" + cpf + ", estado=" + estado + "]";
	}

	public int hashCode() {
		return Objects.hash(cpf);
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(cpf, other.cpf);
	}
	
	public Cliente(String nome, Long cpf, String estado) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.estado = estado;
	}
	public Cliente() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
    
    

}
