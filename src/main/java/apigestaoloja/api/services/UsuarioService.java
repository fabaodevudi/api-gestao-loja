package apigestaoloja.api.services;

import java.util.Optional;

import org.springframework.validation.BindingResult;

import apigestaoloja.api.dtos.UsuarioDto;
import apigestaoloja.api.dtos.UsuarioLoginDto;
import apigestaoloja.api.entity.Usuario;
import apigestaoloja.api.security.entities.UsuarioLogin;

public interface UsuarioService {

	/**
	 * Busca e retorna um usuário dado um email.
	 * 
	 * @param email
	 * @return Optional<UsuarioLogin>
	 */
	Optional<UsuarioLogin> buscarUsuarioLoginPorEmail(String email);
	
	Optional<Usuario> buscarUsuarioPorEmail(String email);
	
	Optional<Usuario> buscarUsuarioPorId(Long email);

	UsuarioLogin saveUsuarioLogin(UsuarioLoginDto usuarioLoginDto, BindingResult result) throws Exception;
	
	Usuario saveUsuario(UsuarioDto usuarioDto) throws Exception;
	
	UsuarioDto atualizarUsuario(UsuarioDto usuarioDto, BindingResult result);
}
