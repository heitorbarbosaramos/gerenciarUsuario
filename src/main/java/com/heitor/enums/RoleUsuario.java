package com.heitor.enums;

import java.util.ArrayList;
import java.util.List;

public enum RoleUsuario {

	ADMINISTRADOR(1, "Administrador"),
	USUARIO(2, "Usuário comum");
	
	private Integer cod;
	private String descricao;
	
	RoleUsuario(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public Integer getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static List<RoleUsuario> listaRole(){
		List<RoleUsuario> lista = new ArrayList<>();
		for(RoleUsuario x : RoleUsuario.values()) {
			lista.add(x);
		}
		return lista;
	}

	public static RoleUsuario RoleToEnum(Integer cod) {
		if(cod == null) {return null;}
		for(RoleUsuario x : RoleUsuario.values()) {
			if(cod == x.getCod()) {
				return x;
			}
		}
		throw new IllegalArgumentException("Código Role Usuario Invalid: " + cod);
	}
	
	
	
	
	
}
