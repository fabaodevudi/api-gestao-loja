package apigestaoloja.api.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import apigestaoloja.api.converters.CategoriaLojaDtoConverter;
import apigestaoloja.api.converters.LojaDtoConverter;
import apigestaoloja.api.dtos.CategoriaLojaDto;
import apigestaoloja.api.dtos.LojaDto;
import apigestaoloja.api.entity.CategoriaLoja;
import apigestaoloja.api.entity.Loja;
import apigestaoloja.api.response.Response;
import apigestaoloja.api.services.LojaService;

@RestController
@RequestMapping("/api/admin/loja")
@CrossOrigin(origins = "*")
@PreAuthorize("hasAnyRole('ADMIN')")
public class LojaController {
	
	@Autowired
	private LojaService lojaService;
	
	@Value("${paginacao.qtd_por_pagina}")
	private int qtdPorPagina;
	
	@GetMapping(value = "/listar/{idUsuario}")
	public ResponseEntity<Response<Page<LojaDto>>> listarLojasPorId(
			@PathVariable("idUsuario") Long idUsuario,			
			@RequestParam(value = "pag", defaultValue = "0") int pag,
			@RequestParam(value = "ord", defaultValue = "nome") String ord,
			@RequestParam(value = "dir", defaultValue = "DESC") String dir
			
			) {		
		Response<Page<LojaDto>> response = new Response<Page<LojaDto>>();
		PageRequest pageRequest = PageRequest.of(pag, this.qtdPorPagina, Direction.valueOf(dir), ord);
		Page<Loja> lojas = lojaService.buscaLojasPorIdUsuario(idUsuario, pageRequest);		
				
		Page<LojaDto> lojasDto = lojas.map(loja -> LojaDtoConverter.converteLojaParaDto(loja));
		
		response.setData(lojasDto);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "/listar/nome/{idUsuario}")
	public ResponseEntity<Response<Page<LojaDto>>> listarLojasPorNome(
			@PathVariable("idUsuario") Long idUsuario,
			@RequestParam String nome,
			@RequestParam(value = "pag", defaultValue = "0") int pag,
			@RequestParam(value = "ord", defaultValue = "nome") String ord,
			@RequestParam(value = "dir", defaultValue = "DESC") String dir
			
			) {		
		Response<Page<LojaDto>> response = new Response<Page<LojaDto>>();
		PageRequest pageRequest = PageRequest.of(pag, this.qtdPorPagina, Direction.valueOf(dir), ord);
		Page<Loja> lojas = lojaService.buscaLojasPorNomeAndIdUsuario(idUsuario, nome, pageRequest);		
				
		Page<LojaDto> lojasDto = lojas.map(loja -> LojaDtoConverter.converteLojaParaDto(loja));
		
		response.setData(lojasDto);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "/{idLoja}")
	public ResponseEntity<Response<LojaDto>> buscarPorId(
			@PathVariable("idLoja") Long idLoja) {		
		Response<LojaDto> response = new Response<LojaDto>();
		
		Optional<Loja> loja = lojaService.buscaLojaPorId(idLoja);
		
		if(!loja.isPresent()) {
			response.getErrors().add("Loja não localizadao");
			return ResponseEntity.badRequest().body(response);
		}
		
		response.setData(LojaDtoConverter.converteLojaParaDto(loja.get()));
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "/categoria")
	public ResponseEntity<Response<List<CategoriaLojaDto>>> listarCategorias() {		
		Response<List<CategoriaLojaDto>> response = new Response<List<CategoriaLojaDto>>();
		
		List<CategoriaLoja> categorias = this.lojaService.buscaCategorias();		
				
		List<CategoriaLojaDto> categoriasDto = categorias.stream()
				.map(categoria -> CategoriaLojaDtoConverter.converteCategoriaLojaParaDto(categoria))
				.collect(Collectors.toList());
		
		response.setData(categoriasDto);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping
	public ResponseEntity<Response<LojaDto>> adicionar(@Valid @RequestBody LojaDto lojaDto,
			BindingResult result) throws Exception {
	
		Response<LojaDto> response = new Response<LojaDto>();
		validarLoja(lojaDto, result);		

		if (result.hasErrors()) {			
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}		
		
		Loja loja = LojaDtoConverter.converteDtoParaLoja(lojaDto);		
				
		response.setData(LojaDtoConverter.converteLojaParaDto(lojaService.saveLoja(loja)));
		return ResponseEntity.ok(response);
	}
	
	@PutMapping
	public ResponseEntity<Response<LojaDto>> atualizar(@Valid @RequestBody LojaDto lojaDto, BindingResult result) throws Exception {
	
		Response<LojaDto> response = new Response<LojaDto>();
		validarLoja(lojaDto, result);		

		if (result.hasErrors()) {			
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}		

		Loja loja = LojaDtoConverter.converteDtoParaLoja(lojaDto);		
		
		response.setData(LojaDtoConverter.converteLojaParaDto(lojaService.saveLoja(loja)));
		return ResponseEntity.ok(response);
	}
	
	
	@DeleteMapping(value = "/{id}")	
	public ResponseEntity<Response<String>> remover(@PathVariable("id") Long id) {
		
		Response<String> response = new Response<String>();
		Optional<Loja> loja = lojaService.buscaLojaPorId(id);

		if (!loja.isPresent()) {			
			response.getErrors().add("Loja não localizada para exclusão");
			return ResponseEntity.badRequest().body(response);
		}

		lojaService.excluiLoja(id);
		return ResponseEntity.ok(new Response<String>());
	}
	
	private void validarLoja(LojaDto lojaDto, BindingResult result) {
		if (lojaDto.getCategoriaLojaDto() == null) {
			result.addError(new ObjectError("categoria", "Categoria não informada."));
			return;
		}
		if (lojaDto.getUsuarioDto() == null) {
			result.addError(new ObjectError("usuario", "Usuário não informado."));
			return;
		}
		if (lojaDto.getNome() == null) {
			result.addError(new ObjectError("nome", "Nome não informado."));
			return;
		}if (lojaDto.getDescricao() == null) {
			result.addError(new ObjectError("descricao", "Descrição não informada."));
			return;
		}
		
		
	}
	
	
	
	

}
