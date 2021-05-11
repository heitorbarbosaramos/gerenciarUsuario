package com.heitor.model.DTO;

import java.util.HashSet;
import java.util.Set;

import com.heitor.model.Usuario;

public class UsuarioDTO {

	private Integer id;
	private String nome;
	private String email;
	private String userName;
	
	private Set<String> telefones = new HashSet<>();
	
	public UsuarioDTO (Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
		this.userName = usuario.getUsername();
		this.telefones = usuario.getTelefones();
	}
	
	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getUserName() {
		return userName;
	}

	public Set<String> getTelefones(){
		return telefones;
	}

	
}
