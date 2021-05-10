package com.heitor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heitor.model.UsuarioRole;
import com.heitor.repository.UsuarioRolesRepository;

@Service
public class UsuarioRoleService {

	@Autowired
	private UsuarioRolesRepository repo;
	
	public UsuarioRole save(UsuarioRole usuarioRole) {
		return repo.save(usuarioRole);
	}
	
}
