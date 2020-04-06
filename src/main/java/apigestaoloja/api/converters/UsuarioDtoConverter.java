package apigestaoloja.api.converters;

import java.security.NoSuchAlgorithmException;

import org.springframework.validation.BindingResult;

import apigestaoloja.api.dtos.UsuarioDto;
import apigestaoloja.api.dtos.UsuarioLoginDto;
import apigestaoloja.api.entity.Usuario;
import apigestaoloja.api.security.entities.UsuarioLogin;
import apigestaoloja.api.security.enums.PerfilEnum;
import apigestaoloja.api.utils.SenhaUtils;



public class UsuarioDtoConverter {

	public static UsuarioLogin converterDtoParaUsuarioLogin(UsuarioLoginDto usuarioLoginDto)
			throws NoSuchAlgorithmException {
		UsuarioLogin usuarioLogin = new UsuarioLogin();
		usuarioLogin.setId(usuarioLoginDto.getId());
		usuarioLogin.setEmail(usuarioLoginDto.getEmail());
		usuarioLogin.setSenha(SenhaUtils.gerarBCrypt(usuarioLoginDto.getSenha()));
		usuarioLogin.setPerfil(usuarioLoginDto.getPerfil() == null ? PerfilEnum.ROLE_USUARIO : usuarioLoginDto.getPerfil());		
		
		return usuarioLogin;
	}
	
	public static Usuario converterDtoParaUsuario(UsuarioDto usuarioDto)
			throws NoSuchAlgorithmException {
		Usuario usuario = new Usuario();
		usuario.setId(usuarioDto.getId());
		usuario.setCpf(usuarioDto.getCpf());
		usuario.setNome(usuarioDto.getNome());
		usuario.setTelefone(usuarioDto.getTelefone());	
		usuario.setUsuarioLogin(converterDtoParaUsuarioLogin(usuarioDto.getUsuarioLoginDto()));
		
		return usuario;
	}
	
	public static UsuarioDto converteUsuarioParaDto(Usuario usuario, UsuarioLogin usuarioLogin) {
		
		UsuarioDto usuarioDto = new UsuarioDto();
		usuarioDto.setId(usuario.getId());
		usuarioDto.setNome(usuario.getNome());
		usuarioDto.setTelefone(usuario.getTelefone());
		usuarioDto.setCpf(usuario.getCpf());		
		
		UsuarioLoginDto usuarioLoginDto = new UsuarioLoginDto();
		usuarioLoginDto.setId(usuarioLogin.getId());
		usuarioLoginDto.setEmail(usuarioLogin.getEmail());
		usuarioLoginDto.setSenha(usuarioLogin.getSenha());		
		usuarioLoginDto.setPerfil(usuarioLogin.getPerfil() == null ? PerfilEnum.ROLE_USUARIO : usuarioLogin.getPerfil() );
		usuarioDto.setUsuarioLoginDto(usuarioLoginDto);
		
		return usuarioDto;
		
	}
	
	public static UsuarioDto converteUsuarioParaDto(Usuario usuario) {
		
		UsuarioDto usuarioDto = new UsuarioDto();
		usuarioDto.setId(usuario.getId());
		usuarioDto.setNome(usuario.getNome());
		usuarioDto.setTelefone(usuario.getTelefone());
		usuarioDto.setCpf(usuario.getCpf());		
		
		UsuarioLoginDto usuarioLoginDto = new UsuarioLoginDto();
		usuarioLoginDto.setId(usuario.getUsuarioLogin().getId());
		usuarioLoginDto.setEmail(usuario.getUsuarioLogin().getEmail());
		usuarioLoginDto.setSenha(usuario.getUsuarioLogin().getSenha());		
		usuarioLoginDto.setPerfil(usuario.getUsuarioLogin().getPerfil() == null ? PerfilEnum.ROLE_USUARIO : usuario.getUsuarioLogin().getPerfil() );
		usuarioDto.setUsuarioLoginDto(usuarioLoginDto);
		
		return usuarioDto;
		
	}

	
}
