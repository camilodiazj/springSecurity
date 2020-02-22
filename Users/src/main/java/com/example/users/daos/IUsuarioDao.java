package com.example.users.daos;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.users.beans.Usuario;

@Repository
public interface IUsuarioDao extends CrudRepository<Usuario, Long>{
	public Optional<Usuario> findByUsername(String username);
	public Optional<Usuario> findByNombre(String nombre);
	public Optional<Usuario> findByApellido(String apellido);
	public Optional<Usuario> findByEmail(String email);
}
