package com.heitor.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heitor.enums.RoleUsuario;
import com.heitor.model.Usuario;
import com.heitor.model.UsuarioRole;
import com.heitor.service.UsuarioRoleService;
import com.heitor.service.UsuarioService;
import com.heitor.service.utils.Senha;

@Service
public class DbService {

	@Autowired
	private UsuarioService serviceUsuario;
	@Autowired
	private UsuarioRoleService serviceUsuarioRole;
	
	private Logger LOG = LoggerFactory.getLogger(DbService.class);

	public void dbInstatiationTest() {
	
		LOG.info("INSTANCIANDO BANCO DE TESTES");
		
		Usuario usuario1 = new Usuario(null, "Heitor Fernando Barbosa Ramos", "heitorhfbr@gmail.com", "heitor.ramos", Senha.encriptarSenha("123"));
		Usuario usuario2 = new Usuario(null, "Carla Alcantará Fagundes", "carlinhafafa@gmail.com", "carla.fagundes", Senha.encriptarSenha("123"));
		Usuario usuario3 = new Usuario(null, "Fernanda Alcantará Maga", "maga@gmail.com", "maga.fagundes", Senha.encriptarSenha("123"));
		
		
		usuario1.addTelefones("(11) 98946-1545");
		usuario1.addTelefones("(81) 54346-8712");
		usuario1.addTelefones("(21) 88761-9892");
		
		usuario2.addTelefones("(13) 98888-1234");	
		
		usuario3.addTelefones("(14) 99288-1631");
		usuario3.addTelefones("(14) 98188-1532");
		usuario3.addTelefones("(15) 98488-0433");
		usuario3.addTelefones("(16) 98588-1334");
		usuario3.addTelefones("(17) 90688-1235");
		usuario3.addTelefones("(17) 91828-9136");
		
		serviceUsuario.save(usuario1);
		serviceUsuario.save(usuario2);
		serviceUsuario.save(usuario3);
		
		for(RoleUsuario x : RoleUsuario.values()) {
			UsuarioRole role = new UsuarioRole();
			role.setNomeRole(x.toString());
			role.setDescricaoRoler(x.getDescricao());
			serviceUsuarioRole.save(role);					
		}
	
		usuario1.addRoles(serviceUsuarioRole.findById(RoleUsuario.ROLE_ADMIN.toString()));
		usuario1.addRoles(serviceUsuarioRole.findById(RoleUsuario.ROLE_USUARIO.toString()));
		usuario2.addRoles(serviceUsuarioRole.findById(RoleUsuario.ROLE_USUARIO.toString()));
		usuario3.addRoles(serviceUsuarioRole.findById(RoleUsuario.ROLE_USUARIO.toString()));
		serviceUsuario.save(usuario1);	
		serviceUsuario.save(usuario2);	
		serviceUsuario.save(usuario3);	
	}
}