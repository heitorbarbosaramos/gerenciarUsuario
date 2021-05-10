package com.heitor.service.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class Senha {

	public static String encriptarSenha(String senha) {
		return new BCryptPasswordEncoder().encode(senha);
	}
}
