package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.beans.Usuario;
import com.example.demo.services.UsuarioServices;

@RestController
@RequestMapping("/users")
public class UsuarioController {

	@Autowired
	UsuarioServices usuarioServices;

	@GetMapping
	public ResponseEntity<List<Usuario>> getAll() {
		List<Usuario> users = usuarioServices.getAll();
		if (users != null) {
			return new ResponseEntity<List<Usuario>>(users, HttpStatus.OK);
		}
		return new ResponseEntity<List<Usuario>>(HttpStatus.NOT_FOUND);
	}

	@PostMapping
	public ResponseEntity<Usuario> create(@RequestBody Usuario usuario) {
		if (usuarioServices.create(usuario) != null) {
			return new ResponseEntity<Usuario>(usuario, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Usuario>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping
	public ResponseEntity<Usuario> update(@RequestBody Usuario usuario) {
		if (usuarioServices.update(usuario) != null) {
			return new ResponseEntity<Usuario>(usuario, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Usuario>(usuario, HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping
	public ResponseEntity<Usuario> delete(@RequestBody Usuario usuario) {
		if (usuarioServices.delete(usuario)) {
			return new ResponseEntity<Usuario>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Usuario>(HttpStatus.BAD_REQUEST);
		}
	}
}
