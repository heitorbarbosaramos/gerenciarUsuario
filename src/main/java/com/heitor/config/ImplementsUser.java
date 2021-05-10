package com.heitor.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.heitor.model.Usuario;
import com.heitor.service.UsuarioService;

@Repository
@Transactional
public class ImplementsUser implements UserDetailsService{

	@Autowired
	private UsuarioService usuarioService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuario = usuarioService.findByusername(username);
		
		if(usuario == null) {
			throw new UsernameNotFoundException("Usuario Não Encontrado");
		}
		
		return new User(usuario.getUserName(), usuario.getSenha(), true, true, true, true, usuario.getAuthorities());
	}

}
