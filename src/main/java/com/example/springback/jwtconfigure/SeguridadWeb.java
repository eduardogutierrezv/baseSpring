package com.example.springback.jwtconfigure;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.springback.jwtconfigure.seguridad.JwtAuthenticationEntryPoint;
import com.example.springback.jwtconfigure.seguridad.JwtAuthenticationProvider;
import com.example.springback.jwtconfigure.seguridad.JwtAuthenticationTokenFilter;
import com.example.springback.jwtconfigure.seguridad.JwtSuccesHandler;


@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
@EnableWebSecurity
public class SeguridadWeb extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private JwtAuthenticationProvider authenticationProvider;
	@Autowired
	private JwtAuthenticationEntryPoint authenticationEntryPoint;
	
	@Bean
	public AuthenticationManager authenticationmanager() {
		return new ProviderManager(Collections.singletonList(authenticationProvider));
	
	}
	
	@Bean
	public JwtAuthenticationTokenFilter authenticationtokenfilter() {
		JwtAuthenticationTokenFilter filter = new JwtAuthenticationTokenFilter();
		filter.setAuthenticationManager(authenticationmanager());
		filter.setAuthenticationSuccessHandler(new JwtSuccesHandler());
		return filter;
	}
	
	@Override
	 protected void configure(HttpSecurity http) throws Exception {
		http .cors().and() //LO MAS IMPORTANTE EL CORS PARA QUE FUNCIONE
		.csrf().disable()
		.authorizeRequests().antMatchers("**/rest/**").authenticated() //.authenticated() PERMITIR LA RUTA /API/LOGIN A CUALQUEIRR PERMITEALL PERMITE TODOS LOS INGRESOS
	 	.and()
	 	.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
	 	.and()
	 	.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	  http.addFilterBefore(authenticationtokenfilter(), UsernamePasswordAuthenticationFilter.class);
	  http.headers().cacheControl();	
	}
	
	/* @Override
	    protected void configure(HttpSecurity http) throws Exception {
		 	http.csrf().disable().authorizeRequests()
		 	.antMatchers("/rest/**").permitAll() //PERMITIR LA RUTA /API/LOGIN A CUALQUEIRR
		 	.anyRequest().authenticated() //CUALQUIERA DE OTRA PETICION REQUIERE AUTENTICACION
		 	.and();

		 
	    }*/

	   
}
