package com.example.users.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.users.beans.Usuario;
import com.example.users.services.UsuarioService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(
		value="CRUD Users",
		protocols = "HTTP/REST",
		consumes = "INTERNO"
)


@RestController
@RequestMapping("/users")
public class UsuarioController {
	
	@Autowired
	UsuarioService usuarioServices;
	
	@ApiOperation(value = "Consulta de usuarios", response = List.class, httpMethod = "GET")
	@GetMapping
	public ResponseEntity<List<Usuario>> getAll() {
		List<Usuario> users = usuarioServices.getAll();
		if (users != null) {
			return new ResponseEntity<List<Usuario>>(users, HttpStatus.OK);
		}
		return new ResponseEntity<List<Usuario>>(HttpStatus.NOT_FOUND);
	}

	@ApiOperation(value = "Consulta de usuario por username", response = Usuario.class, httpMethod = "GET")
	@GetMapping("/{username}")
	public ResponseEntity<Usuario> getUserByUsername(@PathVariable String username) {
		Usuario usuario = usuarioServices.getByUsername(username);
		if (usuario != null) {
			return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
		} else {
			return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
		}
	}

	@ApiOperation(value = "Creación de usuario.", response = Usuario.class, httpMethod = "POST")
	@PostMapping
	public ResponseEntity<Usuario> create(@RequestBody Usuario usuario) {
		if (usuarioServices.create(usuario) != null) {
			return new ResponseEntity<Usuario>(usuario, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Usuario>(HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "Actualizacion de usuario.", response = Usuario.class, httpMethod = "PUT")
	@PutMapping
	public ResponseEntity<Usuario> update(@RequestBody Usuario usuario) {
		if (usuarioServices.update(usuario) != null) {
			return new ResponseEntity<Usuario>(usuario, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Usuario>(usuario, HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "Eliminación de usuario.", response = Usuario.class, httpMethod = "DELETE")
	@DeleteMapping
	public ResponseEntity<Usuario> delete(@RequestBody Usuario usuario) {
		if (usuarioServices.delete(usuario)) {
			return new ResponseEntity<Usuario>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Usuario>(HttpStatus.BAD_REQUEST);
		}
	}
}
