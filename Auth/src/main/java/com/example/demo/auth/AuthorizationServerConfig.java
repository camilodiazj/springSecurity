package com.example.demo.auth;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{
	//(iniciar sesión->validar->autenticar->genera token->entrega token)
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	@Qualifier("authenticationManager") //Se especifica el nombre del metodo a inyectar. (Está en SpringSecurityConfig)
	private AuthenticationManager authenticationManager;

	@Autowired
	private InfoAdicionalToken infoAdicionalToken;
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("permiAll()")
		.checkTokenAccess("isAuthenticated()");
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory().withClient("nombre_de_la_app")  //usuario
		.secret(passwordEncoder.encode("contrasena")) //pwd
		.scopes("read","write") //alcance
		.authorizedGrantTypes("password","refresh_token")  //<- se envian credenciales de usuario y cliente.
		.accessTokenValiditySeconds(3600)  //tiempos de vida de los token
		.refreshTokenValiditySeconds(3600);
		
	}

	@Override  //Autentica y valida el token y su firma.  
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		tokenEnhancerChain.setTokenEnhancers(Arrays.asList(infoAdicionalToken, accessTokenConverter()));
		
		endpoints.authenticationManager(authenticationManager)
		.tokenStore(tokenStore())
		.accessTokenConverter(accessTokenConverter()) // encargado de gestión del token																												
		.tokenEnhancer(tokenEnhancerChain);
	}
	
	@Bean
	public JwtTokenStore tokenStore() {
		// TODO Auto-generated method stub
		return new JwtTokenStore(accessTokenConverter());
	}

	@Bean  //se utiliza el de Jwt en lugar de generico de oauth2
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
		jwtAccessTokenConverter.setSigningKey(JwtConfig.LLAVE_SECRETA);
		return jwtAccessTokenConverter;
	}
	
	
}
