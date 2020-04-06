package apigestaoloja.api.services.impl;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import apigestaoloja.api.converters.UsuarioDtoConverter;
import apigestaoloja.api.dtos.UsuarioDto;
import apigestaoloja.api.dtos.UsuarioLoginDto;
import apigestaoloja.api.entity.Usuario;
import apigestaoloja.api.repositories.UsuarioLoginRepository;
import apigestaoloja.api.repositories.UsuarioRepository;
import apigestaoloja.api.security.entities.UsuarioLogin;
import apigestaoloja.api.services.UsuarioService;
import apigestaoloja.api.utils.SenhaUtils;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	private UsuarioLoginRepository usuarioLoginRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Optional<UsuarioLogin> buscarUsuarioLoginPorEmail(String email) {
		return Optional.ofNullable(usuarioLoginRepository.findByEmail(email));
	}
	
	public UsuarioLogin saveUsuarioLogin(UsuarioLoginDto usuarioLoginDto, BindingResult result) throws NoSuchAlgorithmException {
		UsuarioLogin usuarioLogin = UsuarioDtoConverter.converterDtoParaUsuarioLogin(usuarioLoginDto);
		Optional<UsuarioLogin> opt = buscarUsuarioLoginPorEmail(usuarioLoginDto.getEmail());
		opt.ifPresent(func -> result.addError(new ObjectError("UsuarioLogin", "Email já cadastrado")));		
		if (result.hasErrors()) {			
			return opt.get();
		}				
		return usuarioLoginRepository.save(usuarioLogin);
	}
		

	@Override
	public Optional<Usuario> buscarUsuarioPorEmail(String email) {
		return Optional.ofNullable(usuarioRepository.findByUsuarioLogin_Email(email));
	}

	@Override
	public Usuario saveUsuario(UsuarioDto usuarioDto) throws Exception {		
		Usuario usuario = UsuarioDtoConverter.converterDtoParaUsuario(usuarioDto);		
		return usuarioRepository.save(usuario);
	}

	@Override
	public Optional<Usuario> buscarUsuarioPorId(Long id) {
		return usuarioRepository.findById(id);
	}
	
	public UsuarioDto atualizarUsuario(UsuarioDto usuarioDto, BindingResult result){
		Optional<Usuario> usuario = buscarUsuarioPorEmail(usuarioDto.getUsuarioLoginDto().getEmail());		
		if (usuario.isPresent()) {
			if (usuarioDto.getUsuarioLoginDto().getSenha() != null) {
				usuario.get().getUsuarioLogin().setSenha(SenhaUtils.gerarBCrypt(usuarioDto.getUsuarioLoginDto().getSenha()));
			}
			if (!usuario.get().getUsuarioLogin().getEmail().equals(usuarioDto.getUsuarioLoginDto().getEmail())) {
				buscarUsuarioLoginPorEmail(usuarioDto.getUsuarioLoginDto().getEmail())
						.ifPresent(func -> result.addError(new ObjectError("email", "Email já existente.")));
				usuario.get().getUsuarioLogin().setEmail(usuarioDto.getUsuarioLoginDto().getEmail());
			}
			if (!usuario.get().getCpf().equals(usuarioDto.getCpf())) {
				result.addError(new ObjectError("cpf", "CPF não pode ser alterado."));				
			}				
			
			usuario.get().setNome(usuarioDto.getNome());
			usuario.get().setTelefone(usuarioDto.getTelefone());
			
		} else {
			result.addError(new ObjectError("Usuário", "Usuário não encontrado"));
		}
		
		if(!result.hasErrors()) {
			Usuario usuarioSave = usuarioRepository.save(usuario.get());
			return UsuarioDtoConverter.converteUsuarioParaDto(usuarioSave);
		}
		
		return usuarioDto;
		
	}
	
	
	
	
}
