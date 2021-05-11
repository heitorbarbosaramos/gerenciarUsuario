package com.heitor.model.DTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.heitor.model.Usuario;
import com.heitor.model.UsuarioRole;

public class UsuarioDTO implements Serializable, Comparable<UsuarioDTO> {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	private String email;
	private String login;
	
	private Set<String> telefones = new HashSet<>();
	private List<UsuarioRole> roles = new ArrayList<>();
	
	public UsuarioDTO (Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
		this.login = usuario.getLogin();
		this.telefones = usuario.getTelefones();
		this.roles = usuario.getRoles();
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

	public String getLogin() {
		return login;
	}

	public Set<String> getTelefones(){
		return telefones;
	}

	public List<UsuarioRole> getRoles() {
		return roles;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UsuarioDTO [id=");
		builder.append(id);
		builder.append(", nome=");
		builder.append(nome);
		builder.append(", email=");
		builder.append(email);
		builder.append(", login=");
		builder.append(login);
		builder.append(", telefones=");
		builder.append(telefones);
		builder.append(", roles=");
		builder.append(roles);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int compareTo(UsuarioDTO outro) {
		return getNome().compareTo(outro.getNome());
	}

	
	
}
