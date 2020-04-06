package apigestaoloja.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apigestaoloja.api.entity.CategoriaLoja;
import apigestaoloja.api.security.entities.UsuarioLogin;

@Repository
public interface CategoriaLojaRepository extends JpaRepository<CategoriaLoja, Long> {

}
