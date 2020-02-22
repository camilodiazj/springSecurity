package com.example.demo.clientes;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.beans.Usuario;

@FeignClient(name= "users.service", url = "localhost:8085")

public interface UsersClientRest {
	@GetMapping("/{username}")
	public Usuario getUserByUsername(@PathVariable String username);
}
