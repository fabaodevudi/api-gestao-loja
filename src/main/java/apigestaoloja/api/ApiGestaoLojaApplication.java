package apigestaoloja.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import apigestaoloja.api.entity.Usuario;
import apigestaoloja.api.repositories.UsuarioLoginRepository;
import apigestaoloja.api.repositories.UsuarioRepository;
import apigestaoloja.api.security.entities.UsuarioLogin;
import apigestaoloja.api.security.enums.PerfilEnum;
import apigestaoloja.api.utils.SenhaUtils;

@SpringBootApplication
public class ApiGestaoLojaApplication {
	
	@Autowired
	UsuarioLoginRepository usuarioLoginRepository;
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(ApiGestaoLojaApplication.class, args);
	}
	
	@Component
	public class CommandLineAppStartupRunner implements CommandLineRunner {

		@Override
		public void run(String...args) throws Exception {
			UsuarioLogin login = usuarioLoginRepository.findByEmail("infinity@gmail.com");
			if(login == null) {
				UsuarioLogin usuarioLogin = new UsuarioLogin();			
				usuarioLogin.setEmail("infinity@gmail.com");
				usuarioLogin.setPerfil(PerfilEnum.ROLE_ADMIN);
				usuarioLogin.setSenha(SenhaUtils.gerarBCrypt("123456"));	
				usuarioLoginRepository.save(usuarioLogin);
				Usuario usuario = new Usuario();
				usuario.setUsuarioLogin(usuarioLogin);
				usuario.setNome("Fábio Vasconcelos");
				usuario.setTelefone("3492356565");
				usuario.setCpf("71014962021");
				usuarioRepository.save(usuario);
			}			
		}
	}
}
