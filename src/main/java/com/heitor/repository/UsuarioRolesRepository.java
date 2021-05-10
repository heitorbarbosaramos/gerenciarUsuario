package com.heitor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.heitor.model.UsuarioRole;

@Repository
public interface UsuarioRolesRepository extends JpaRepository<UsuarioRole, String> {

}
