package com.example.users.daos;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.users.beans.Role;

@Repository
public interface IRoleDao extends CrudRepository<Role, Long>{
	public Optional<Role> findByNombre(String nombre);
}
