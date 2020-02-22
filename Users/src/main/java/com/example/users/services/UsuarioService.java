package com.example.users.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.users.beans.Usuario;
import com.example.users.daos.IUsuarioDao;

@Service
public class UsuarioService {

	BCryptPasswordEncoder PasswordEncoder = new BCryptPasswordEncoder();

	@Autowired
	private IUsuarioDao usuarioDao;

	public List<Usuario> getAll() {
		if (usuarioDao.count() > 0) {
			return (List<Usuario>) usuarioDao.findAll();
		} else {
			return null;
		}
	}

	public Usuario getByUsername(String username) {
		Optional<Usuario> usuario= usuarioDao.findByUsername(username);
		if(usuario.isPresent()) {
			return usuario.get(); 
		}else {
			return null;
		}
	}
	
	public Usuario create(Usuario usuario) {

		usuario.setPassword(PasswordEncoder.encode(usuario.getPassword()));

		if (!usuarioDao.findByEmail(usuario.getEmail()).isPresent()) {
			return usuarioDao.save(usuario);
		} else {
			return null;
		}
	}

	public Usuario update(Usuario usuario) {
		if (usuarioDao.findById(usuario.getId()).isPresent()) {
			return usuarioDao.save(usuario);
		} else {
			return null;
		}
	}

	public boolean delete(Usuario usuario) {
		if (usuarioDao.findById(usuario.getId()).isPresent()) {
			usuarioDao.delete(usuario);
			return true;
		} else {
			return false;
		}
	}
}
