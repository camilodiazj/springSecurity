package com.example.demo.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.beans.Usuario;

@Service
public class UsuarioServices implements UserDetailsService {

	private Logger logger = LoggerFactory.getLogger(UsuarioServices.class);

	@Autowired
	private RestTemplate clienteRest;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("username", username);
		Usuario usuario = clienteRest.getForObject("http://localhost:8085/users/{username}", Usuario.class,
				pathVariables);

		if (usuario == null) {
			logger.error("Error en el login: no existe el usuario " + username + "en el sistema.");
			throw new UsernameNotFoundException(
					"Error en el login: no existe el usuario " + username + "en el sistema.");
		}

		List<GrantedAuthority> authorities = usuario.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getNombre()))
				.peek(authority -> logger.info("Role: " + authority.getAuthority())).collect(Collectors.toList());

		return new User(usuario.getUsername(), usuario.getPassword(), usuario.isEnabled(), true, true, true,
				authorities);
	}

}
