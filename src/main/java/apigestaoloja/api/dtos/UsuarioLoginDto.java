package apigestaoloja.api.dtos;

import apigestaoloja.api.security.enums.PerfilEnum;

public class UsuarioLoginDto {
	
	private Long id;
	private String email;
	private String senha;
		
	private PerfilEnum perfil;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public PerfilEnum getPerfil() {
		return perfil;
	}
	public void setPerfil(PerfilEnum perfil) {
		this.perfil = perfil;
	}

}
