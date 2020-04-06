package apigestaoloja.api.services;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import apigestaoloja.api.entity.CategoriaLoja;
import apigestaoloja.api.entity.Loja;

public interface LojaService {

	Page<Loja> buscaLojasPorIdUsuario(Long id, PageRequest page);
	Page<Loja> buscaLojasPorNomeAndIdUsuario(Long id, String nome, PageRequest page);
	Loja saveLoja(Loja loja) throws Exception;
	Optional<Loja> buscaLojaPorId(Long id);
	List<CategoriaLoja> buscaCategorias();
	void excluiLoja(Long id);
}
