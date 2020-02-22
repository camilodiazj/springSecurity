package com.example.demo.daos;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.beans.Role;

public interface IRoleDao extends CrudRepository<Role, Long>{
	public Optional<Role> findByNombre(String nombre);
}
