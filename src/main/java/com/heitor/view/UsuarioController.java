package com.heitor.view;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.heitor.model.Usuario;
import com.heitor.model.UsuarioRole;
import com.heitor.model.DTO.UsuarioDTO;
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
	
	private Logger LOG = LoggerFactory.getLogger(UsuarioController.class);
	
	@RequestMapping(value = "/lista", method = RequestMethod.GET)
	public String findAllCliente(ModelMap map) {
		List<UsuarioDTO> usuarioDto = serviceUsuario.findAll().stream().map(obj -> new UsuarioDTO(obj)).collect(Collectors.toList());
		Collections.sort(usuarioDto);
		LOG.info("LISTA DTO: " + usuarioDto.toString());
		map.addAttribute("usuarios", usuarioDto);
		return "usuario-lista";
	}
	
	@RequestMapping(value = "/Adicionar", method = RequestMethod.GET)
	public String adiciona(ModelMap map) {
		List<UsuarioRole> roles = serviceUsuarioRole.findAll();
		map.addAttribute("roles", roles);
		return "usuario-add";
	}

	@RequestMapping(value = "/editar/{id}", method = RequestMethod.GET)
	public String editar(@PathVariable(value = "id") Integer id, ModelMap map) {
		UsuarioDTO usuarioDto = serviceUsuario.fromDto(serviceUsuario.findById(id));
		
		map.addAttribute("idUsuario", usuarioDto.getId());
		map.addAttribute("nome", usuarioDto.getNome());
		map.addAttribute("login", usuarioDto.getLogin());
		map.addAttribute("email", usuarioDto.getEmail());
		map.addAttribute("telefones", usuarioDto.getTelefones());
		
		usuarioDto.getRoles().forEach(role ->{
			map.addAttribute("checar"+role.getNomeRole(), true);
		});
		
		
		return adiciona(map);
	}
	
	@RequestMapping(value = "/atualizar",method = RequestMethod.POST)
	public ResponseEntity<?> atualizarUsuario (Integer idUsuario, String nome, String email, String login, String senha, String telefones, String rolesUser){
		Usuario usuario = serviceUsuario.findById(idUsuario);
		usuario.setNome(nome);
		usuario.setEmail(email);
		usuario.setLogin(login);
		usuario.setSenha(senha != null ? Senha.encriptarSenha(senha):usuario.getSenha());
		serviceUsuario.addTelefones(usuario, telefones);
		serviceUsuario.setarStatus(rolesUser, usuario);
		serviceUsuario.save(usuario);
		return ResponseEntity.ok().build();
	}
	
	@RequestMapping(value = "/salvar",method = RequestMethod.POST)
	public ResponseEntity<?> novoUsuario(Integer idUsuario, String nome, String email, String login, String senha, String telefones,String rolesUser){
		if(idUsuario != null) {
			return atualizarUsuario(idUsuario, nome, email, login, senha, telefones, rolesUser);
		}
		Usuario usuario = new Usuario(null, nome, email, login, Senha.encriptarSenha(senha));
		serviceUsuario.addTelefones(usuario, telefones);	
		serviceUsuario.setarStatus(rolesUser, usuario);
		serviceUsuario.save(usuario);
		return ResponseEntity.ok(serviceUsuario.fromDto(usuario));
	}
	
	@RequestMapping(value = "/excluir/{idCliente}", method = RequestMethod.DELETE)
	public ResponseEntity<?> excluirUsuario(@PathVariable(value = "idCliente") Integer idUsuario){
		serviceUsuario.excluirUsuario(idUsuario);
		return ResponseEntity.ok().build();
	}
}
