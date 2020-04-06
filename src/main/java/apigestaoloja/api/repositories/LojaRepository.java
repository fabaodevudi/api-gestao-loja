package apigestaoloja.api.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apigestaoloja.api.entity.Loja;

@Repository
public interface LojaRepository extends JpaRepository<Loja, Long> {
	public Page<Loja> findByUsuario_IdOrderByIdDesc(Long id, Pageable pageable);
	public Page<Loja> findByUsuario_IdAndNomeContainingOrderByIdDesc(Long id, String nome, Pageable pageable);

}
