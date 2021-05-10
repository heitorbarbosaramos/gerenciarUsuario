package com.heitor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heitor.model.Usuario;
import com.heitor.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repo;
	
	public Usuario save(Usuario usuario) {
		return repo.save(usuario);
	}
	
	public Usuario findByusername(String userName) {
		return repo.findByUserNamer(userName);
	}
	

}
