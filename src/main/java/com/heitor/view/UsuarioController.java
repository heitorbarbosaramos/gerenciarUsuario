package com.heitor.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.heitor.model.Usuario;
import com.heitor.model.UsuarioRole;
import com.heitor.service.UsuarioRoleService;
import com.heitor.service.UsuarioService;
import com.heitor.service.utils.Senha;

@Controller
@RequestMapping(value = "/usuario")
public class UsuarioController {
	@Autowired
	private UsuarioService serviceUsuario;
	@Autowired
	private UsuarioRoleService serviceUsuarioRole;	
	
	@RequestMapping(value = "/Adicionar", method = RequestMethod.GET)
	public String addciona(ModelMap map) {
		List<UsuarioRole> roles = serviceUsuarioRole.findAll();
		map.addAttribute("roles", roles);
		return "usuario-add";
	}
	
	@RequestMapping(value = "/salvar",method = RequestMethod.POST)
	public ResponseEntity<?> novoUsuario(String nome, String email, String userName, String senha, String telefones1, String telefones2, String rolesUser){
		Usuario usuario = new Usuario(null, nome, email, userName, Senha.encriptarSenha(senha));
		if(telefones1 != null) {usuario.addTelefones(telefones1);}
		if(telefones2 != null) {usuario.addTelefones(telefones2);}
		serviceUsuario.save(usuario);
		
		if(rolesUser != null) {
			String[] role = rolesUser.split(",");
			for (String x : role) {				
				UsuarioRole roleUsuario = serviceUsuarioRole.findById(x);	
				usuario.addRoles(roleUsuario);
				roleUsuario.setUsuarios(usuario);
			}
		}
		serviceUsuario.save(usuario);
		return ResponseEntity.ok(serviceUsuario.fromDto(usuario));
	}
}
