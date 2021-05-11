package com.heitor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heitor.model.Usuario;
import com.heitor.model.UsuarioRole;
import com.heitor.repository.UsuarioRolesRepository;

@Service
public class UsuarioRoleService {

	@Autowired
	private UsuarioRolesRepository repo;
	
	public UsuarioRole save(UsuarioRole usuarioRole) {
		return repo.save(usuarioRole);
	}
	
	public List<UsuarioRole> findAll(){
		return repo.findAll();
	}

	public UsuarioRole findById(String nomeRole) {
		return repo.findById(nomeRole).orElse(null);
	}
	
	public UsuarioRole setUsuario(UsuarioRole role, Usuario usuario) {
		role.setUsuarios(usuario);
		return role;
	}
	
}
