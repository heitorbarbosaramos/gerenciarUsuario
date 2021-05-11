package com.heitor.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class UsuarioRole implements GrantedAuthority {
	private static final long serialVersionUID = 1L;

	@Id
	private String nomeRole;
	private String descricaoRoler;

	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Usuario> usuarios = new ArrayList<>();

	public UsuarioRole() {
	}

	public String getNomeRole() {
		return nomeRole;
	}

	public void setNomeRole(String nomeRole) {
		this.nomeRole = nomeRole;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Usuario usuario) {
		this.usuarios.add(usuario);
	}

	public String getDescricaoRoler() {
		return descricaoRoler;
	}

	public void setDescricaoRoler(String descricaoRoler) {
		this.descricaoRoler = descricaoRoler;
	}

	@Override
	public String getAuthority() {
		return nomeRole;
	}
}
