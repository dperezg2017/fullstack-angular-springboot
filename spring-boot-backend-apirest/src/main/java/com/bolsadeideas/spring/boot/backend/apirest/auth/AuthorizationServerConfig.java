package com.bolsadeideas.spring.boot.backend.apirest.auth;

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

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private InfoAdicionalToken infoAdicionalToken;
	
	@Autowired
	@Qualifier("authenticationManager")
	private AuthenticationManager authenticationManager;
	
	/*@descripcion: Metodo generar Overriden/Implement*/
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {

		security.tokenKeyAccess("permitAll()") // damos permisos a cualqueir usuario, asi sea anonimo para autenticarse.
		.checkTokenAccess("isAuthenticated()") //Endpoint que verifica el token y su firma "/oauth/check_token" , solo pueden acceder a esta ruta, los clientes autenticado
		;
		
		super.configure(security);
	}
	/*@descripcion: Metodo generar Overriden/Implement*/
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

		clients.inMemory().withClient("angularapp")
		.secret(passwordEncoder.encode("12345"))
		.scopes("read","write") // permiso de lectura y escritura
		.authorizedGrantTypes("password","refresh_token") // obtener token con el password - via pagina de login, refresh_token: token de acceso renovado o actualizado
		.accessTokenValiditySeconds(3600) // tiempo de expiration de token
		.refreshTokenValiditySeconds(3600) // timeout del refresh token
		;
		
		super.configure(clients);
	}

	/*@descripcion: Metodo generar Overriden/Implement
	 * Se encarga del proceso de administracion de JWT Token*/
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();  //cadena que une informacio nde token, que lo traemos del accessTokenConverter.
		tokenEnhancerChain.setTokenEnhancers(Arrays.asList(infoAdicionalToken,accessTokenConverter())); // accessTokenConverter retorna del tipo JWT
		
		endpoints.authenticationManager(authenticationManager)
		.tokenStore(tokenStore())
		.accessTokenConverter(accessTokenConverter()) // almacena los dato de autenticacion: user ,guardan nombre apellido, dni, etc info que no sea sensible, (no claves)
		.tokenEnhancer(tokenEnhancerChain) // pasamos la cadena
		;
	}
	@Bean
	public JwtTokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}
	/* Publica: validar que nuestro token sea autentico y Privada:Firmar el token JWT*/
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
		jwtAccessTokenConverter.setSigningKey(JwtConfig.RSA_PRIVADA);
		jwtAccessTokenConverter.setVerifierKey(JwtConfig.RSA_PUBLICA);
		return jwtAccessTokenConverter;
	}
	
	
	
}
