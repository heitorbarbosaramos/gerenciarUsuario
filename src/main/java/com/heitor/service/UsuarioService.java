package com.heitor.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heitor.enums.RoleUsuario;
import com.heitor.model.Usuario;
import com.heitor.model.UsuarioRole;
import com.heitor.model.DTO.UsuarioDTO;
import com.heitor.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repo;
	@Autowired
	private UsuarioRoleService serviceUsuarioRole;
	
	public Usuario save(Usuario usuario) {
		return repo.save(usuario);
	}
	
	public Usuario findByusername(String login) {
		return repo.findByUserNamer(login);
	}
	
	public UsuarioDTO fromDto(Usuario usuario) {
		UsuarioDTO usuarioDto = new UsuarioDTO(usuario);
		return usuarioDto;
	}

	public List<Usuario> findAll() {
		List<Usuario> usuarios = repo.findAll();
		Collections.sort(usuarios);
		return repo.findAll();
	}

	public Usuario findById(Integer id) {
		return repo.findById(id).orElse(null);
	}
	
	public Usuario setarStatus(String rolesUser, Usuario usuario) {
		if(rolesUser != null) {
			String[] role = rolesUser.split(",");
			for(RoleUsuario x : RoleUsuario.values()) {
				usuario.removeRoles(serviceUsuarioRole.findById(x.toString()));
			}
			for (String x : role) {				
				UsuarioRole roleUsuario = serviceUsuarioRole.findById(x);	
				usuario.addRoles(roleUsuario);
//				roleUsuario.setUsuarios(usuario);
			}
		}
		return usuario;
	}
	
	public Usuario addTelefones(Usuario usuario, String telefones) {
		if(telefones != null) {
			String[] listaTel = telefones.split(",");
			for(String split : listaTel) {
				usuario.addTelefones(split);
			}
		}
		return usuario;
	}
	
	public Usuario removeTelefones(Usuario usuario, String telefone) {
		if(telefone != null) {usuario.removeTelefones(telefone);}
		return usuario;
	}
	
	public void excluirUsuario(Integer idUsuario) {	
		repo.delete(findById(idUsuario));
	}

}
