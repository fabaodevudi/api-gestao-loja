package apigestaoloja.api.converters;

import apigestaoloja.api.dtos.CategoriaLojaDto;
import apigestaoloja.api.entity.CategoriaLoja;

public class CategoriaLojaDtoConverter {
	
	public static CategoriaLojaDto converteCategoriaLojaParaDto(CategoriaLoja categoria) {		
		CategoriaLojaDto categoriaLojaDto = new CategoriaLojaDto();
		categoriaLojaDto.setId(categoria.getId());
		categoriaLojaDto.setNome(categoria.getNome());
		categoriaLojaDto.setDescricao(categoria.getDescricao());
		return categoriaLojaDto;
		
	}
	
	public static CategoriaLoja converteDtoParaCategoriaLoja(CategoriaLojaDto categoriaLojaDto) {		
		CategoriaLoja categoriaLoja = new CategoriaLoja();
		categoriaLoja.setId(categoriaLojaDto.getId());
		categoriaLoja.setNome(categoriaLojaDto.getNome());
		categoriaLoja.setDescricao(categoriaLojaDto.getDescricao());
		return categoriaLoja;
		
	}

}
