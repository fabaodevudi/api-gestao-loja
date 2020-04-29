package apigestaoloja.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import apigestaoloja.api.dtos.UsuarioDto;
import apigestaoloja.api.response.Response;
import apigestaoloja.api.services.UsuarioService;



@RestController
@RequestMapping("/api/usuario")
@CrossOrigin(origins = "*")
public class CadastroUsuarioController {

	@Autowired	
	private UsuarioService usuarioService;
	
	@PutMapping
	public ResponseEntity<Response<UsuarioDto>> atualizar(@Valid @RequestBody UsuarioDto usuarioDto, BindingResult result) {
		
		Response<UsuarioDto> response = new Response<UsuarioDto>();

		usuarioDto = usuarioService.atualizarUsuario(usuarioDto, result);

		if (result.hasErrors()) {			
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		response.setData(usuarioDto);

		return ResponseEntity.ok(response);
	}
	
	


}
