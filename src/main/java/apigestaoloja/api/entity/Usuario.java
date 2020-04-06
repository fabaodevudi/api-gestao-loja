package apigestaoloja.api.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import apigestaoloja.api.security.entities.UsuarioLogin;

@Entity
@Table(name = "usuario") 
public class Usuario implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_usuario", nullable = false)
	private Long id;
	
	@Column(name = "nome", nullable = false)	
	private String nome;
	
	@Column(name = "telefone", nullable = false)
	private String telefone;
	
	@Column(name = "cpf", nullable = false)
	private String cpf;
	
	@ManyToOne
    @JoinColumn(name = "id_usuario_login")
	private UsuarioLogin usuarioLogin;
	
	public UsuarioLogin getUsuarioLogin() {
		return usuarioLogin;
	}
	public void setUsuarioLogin(UsuarioLogin usuarioLogin) {
		this.usuarioLogin = usuarioLogin;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	


}
