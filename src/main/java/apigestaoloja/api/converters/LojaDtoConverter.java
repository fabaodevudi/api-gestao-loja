package apigestaoloja.api.converters;


import java.security.NoSuchAlgorithmException;

import apigestaoloja.api.dtos.LojaDto;
import apigestaoloja.api.entity.Loja;

public class LojaDtoConverter {
	
	public static LojaDto converteLojaParaDto(Loja loja) {
		LojaDto lojaDto = new LojaDto();
		lojaDto.setUsuarioDto(UsuarioDtoConverter.converteUsuarioParaDto(loja.getUsuario()));
		lojaDto.setCategoriaLojaDto(CategoriaLojaDtoConverter.converteCategoriaLojaParaDto(loja.getCategoriaLoja()));
		lojaDto.setDescricao(loja.getDescricao());
		lojaDto.setLocalizacao(loja.getLocalizacao());
		lojaDto.setNome(loja.getNome());
		lojaDto.setId(loja.getId());		
				
		return lojaDto;
		
	}
	
	public static Loja converteDtoParaLoja(LojaDto lojaDto) throws NoSuchAlgorithmException {
		Loja loja = new Loja();
		loja.setUsuario(UsuarioDtoConverter.converterDtoParaUsuario(lojaDto.getUsuarioDto()));
		loja.setCategoriaLoja(CategoriaLojaDtoConverter.converteDtoParaCategoriaLoja(lojaDto.getCategoriaLojaDto()));
		loja.setDescricao(lojaDto.getDescricao());
		loja.setLocalizacao(lojaDto.getLocalizacao());
		loja.setNome(lojaDto.getNome());
		loja.setId(lojaDto.getId());		
				
		return loja;
		
	}

}
