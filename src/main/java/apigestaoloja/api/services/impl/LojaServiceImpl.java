package apigestaoloja.api.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import apigestaoloja.api.entity.CategoriaLoja;
import apigestaoloja.api.entity.Loja;
import apigestaoloja.api.repositories.CategoriaLojaRepository;
import apigestaoloja.api.repositories.LojaRepository;
import apigestaoloja.api.services.LojaService;

@Service
public class LojaServiceImpl implements LojaService {
	
	@Autowired
	private LojaRepository lojaRepository;
	
	@Autowired
	private CategoriaLojaRepository categoriaLojaRepository;

	@Override
	public Page<Loja> buscaLojasPorIdUsuario(Long id, PageRequest page) {		
		return lojaRepository.findByUsuario_IdOrderByIdDesc(id, page);
	}

	@Override
	public Loja saveLoja(Loja loja) throws Exception {		
		return lojaRepository.save(loja);
	}

	@Override
	public Optional<Loja> buscaLojaPorId(Long id) {
		return lojaRepository.findById(id);
	}

	@Override
	public void excluiLoja(Long id) {
		lojaRepository.deleteById(id);		
	}

	@Override
	public List<CategoriaLoja> buscaCategorias() {		
		return categoriaLojaRepository.findAll();
	}

	@Override
	public Page<Loja> buscaLojasPorNomeAndIdUsuario(Long id, String nome, PageRequest page) {
		return lojaRepository.findByUsuario_IdAndNomeContainingOrderByIdDesc(id, nome, page);
		
	}

}
