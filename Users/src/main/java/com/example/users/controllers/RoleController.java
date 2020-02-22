package com.example.users.controllers;

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
import com.example.users.beans.Role;
import com.example.users.services.RoleService;

import io.swagger.annotations.Api;

@Api(
		value="CRUD Roles",
		protocols = "HTTP/REST",
		consumes = "INTERNO"
)

@RestController
@RequestMapping("/roles")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@GetMapping
	public ResponseEntity<List<Role>> getAll() {
		List<Role> roles = roleService.getAll();
		if (roles != null) {
			return new ResponseEntity<List<Role>>(roles, HttpStatus.OK);
		}
		return new ResponseEntity<List<Role>>(HttpStatus.NOT_FOUND);
	}

	@PostMapping
	public ResponseEntity<Role> create(@RequestBody Role role) {
		if (roleService.create(role) != null) {
			return new ResponseEntity<Role>(role, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Role>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping
	public ResponseEntity<Role> update(@RequestBody Role role) {
		if (roleService.update(role) != null) {
			return new ResponseEntity<Role>(role, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Role>(role, HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping
	public ResponseEntity<Role> delete(@RequestBody Role role) {
		if (roleService.delete(role)) {
			return new ResponseEntity<Role>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Role>(HttpStatus.BAD_REQUEST);
		}
	}
	
}
