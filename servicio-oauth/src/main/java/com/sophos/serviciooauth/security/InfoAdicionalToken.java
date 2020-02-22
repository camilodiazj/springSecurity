package com.sophos.serviciooauth.security;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;
import com.sophos.serviciooauth.beans.Usuario;
import com.sophos.serviciooauth.clients.UsuarioFeignClient;

@Component
public class InfoAdicionalToken implements TokenEnhancer {

	@Autowired
	UsuarioFeignClient client;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

		Usuario usuario = client.getUserByUsername(authentication.getName());

		Map<String, Object> info = new HashMap<>();

		info.put("info_adicional", "Hola ".concat(authentication.getName()));

		info.put("nombre", usuario.getNombre());
		info.put("apellido", usuario.getApellido());
		info.put("email", usuario.getEmail());

		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);

		return accessToken;

	}

}
