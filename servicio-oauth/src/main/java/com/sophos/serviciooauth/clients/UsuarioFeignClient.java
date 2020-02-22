package com.sophos.serviciooauth.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sophos.serviciooauth.beans.Usuario;

@FeignClient(name= "users.service", url = "users-server:8085")
public interface UsuarioFeignClient {
	@GetMapping("/users/{username}")
	public Usuario getUserByUsername(@PathVariable String username);
}
