package com.bolsadeideas.spring.boot.backend.apirest.auth;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/clientes","/api/clientes/page/**","/api/uploads/img/**","/images/**") // **:cualquier foto
		.permitAll() // solo para usuarios autenticado, en nuestro caso, permitir a todos)
		/* se comenta casi final de la clase
		.antMatchers("/api/clientes/{id}").permitAll()
		.antMatchers("/api/facturas/**").permitAll() */
		
		/*.antMatchers(HttpMethod.GET,"/api/clientes/{id}").hasAnyRole("USER","ADMIN")  // indicar 1 a + roles
		.antMatchers(HttpMethod.POST,"/api/clientes/upload").hasAnyRole("USER","ADMIN")  // indicar 1 a + roles
		.antMatchers(HttpMethod.POST,"/api/clientes").hasRole("ADMIN") //SOLO UN ROL
		.antMatchers("/api/clientes/**").hasRole("ADMIN")  // ** cualquier ruta hacia adelante va requerir permido de ADMIN, partir de lo mas especifico a l omas generico, para que n ose cruce con lso de arriba */
		.anyRequest().authenticated()
		.and().cors().configurationSource(corsConfigurationSource());
		
	}

	
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200","*"));  //  en el * colocar la IP del servidor |||||si pone "*" es para todos los doiminios
        configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","OPTIONS")); // si pone "*" es para todos los metodos, peor es mejor ser especifico
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Arrays.asList("Content-Type","Authorization"));
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
    
    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter(){
    	FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(corsConfigurationSource())); // Filtro de Cors, lo cual le pasamos la configuracion
    	bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
    	return bean;
    }
	
	
}
