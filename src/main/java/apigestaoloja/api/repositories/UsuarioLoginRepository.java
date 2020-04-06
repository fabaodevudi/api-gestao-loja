package apigestaoloja.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apigestaoloja.api.security.entities.UsuarioLogin;


@Repository
public interface UsuarioLoginRepository extends JpaRepository<UsuarioLogin, Long> {
	public UsuarioLogin findByEmail(String email);
}
