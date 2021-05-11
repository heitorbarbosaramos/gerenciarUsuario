package com.heitor.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class Usuario implements Serializable, UserDetails, Comparable<Usuario>{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	@Email
	private String email;
	private String login;
	private String senha;	
	@ElementCollection
	@CollectionTable(name="TELEFONE")
	private Set<String> telefones = new HashSet<>();
	
	@ManyToMany
	@JoinTable(name = "usuariosRoles", joinColumns = @JoinColumn(name = "id_usuario", referencedColumnName = "id"),
		inverseJoinColumns =  @JoinColumn(name = "id_role", referencedColumnName = "nomeRole"))
	private List<UsuarioRole> roles = new ArrayList<>();

	public Usuario() {
	}

	public Usuario(Integer id, String nome, String email, String login, String senha) {
		super();
		this.id = id;
		this.nome = nome;
		this.login = login;
		this.email = email;
		this.senha = senha;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Set<String> getTelefones() {
		return telefones;
	}

	public void addTelefones(String telefones) {
		this.telefones.add(telefones);
	}
	
	public void removeTelefones(String telefones) {
		this.telefones.remove(telefones);
	}

	public List<UsuarioRole> getRoles() {
		return roles;
	}

	public void addRoles(UsuarioRole roles) {
		this.roles.add(roles);
	}
	
	public void removeRoles(UsuarioRole roles) {
		this.roles.remove(roles);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles;
	}

	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public String getUsername() {
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}

	@Override
	public int compareTo(Usuario outro) {
		return getNome().compareTo(outro.getNome());
	}

	
}
