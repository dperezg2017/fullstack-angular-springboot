package com.bolsadeideas.spring.boot.backend.apirest.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableGlobalMethodSecurity(securedEnabled=true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsService usuarioService;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	/*@descripcion: Metodo generar Overriden/Implement*/
	@Override
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.usuarioService).passwordEncoder(passwordEncoder());
		super.configure(auth);
	}

	@Bean("authenticationManager")
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	/**CSRF: Cross-Site request forgery o falsificacion e peticio nen sitios cruzados*/
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests() // .antMatchers(HttpMethod.GET,"/api/clientes")  :se comenta por que esto se usara en la otra clase, aqui no
		//.permitAll() // solo para usuarios autenticado, en nuestro caso, permitir a todos)
		.anyRequest().authenticated()
		.and()
		.csrf().disable() // se deshabilita por que usa 2 app separados nG y Springboot 
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //ENUM, es un conjunto de constantes
		; 
	}
	
	
}
