package apigestaoloja.api.security.dto;

import apigestaoloja.api.dtos.UsuarioDto;
import apigestaoloja.api.security.entities.UsuarioLogin;
import apigestaoloja.api.security.enums.PerfilEnum;

public class TokenDto {

	private String token; 
	
	private UsuarioDto usuarioDto;
	
	public TokenDto() {
	}
	
	public TokenDto(String token) {
		this.token = token;
	}
	
	public TokenDto(String token, UsuarioDto usuarioDto) {
		this.token = token;
		this.setUsuarioDto(usuarioDto);
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public UsuarioDto getUsuarioDto() {
		return usuarioDto;
	}

	public void setUsuarioDto(UsuarioDto usuarioDto) {
		this.usuarioDto = usuarioDto;
	}


	

}
