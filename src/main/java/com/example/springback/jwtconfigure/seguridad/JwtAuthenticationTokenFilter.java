package com.example.springback.jwtconfigure.seguridad;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import com.example.springback.jwtconfigure.model.JwtAuthenticationToken;


public class JwtAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter{
	
	
	public JwtAuthenticationTokenFilter() {
		super("/rest/**");
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
	
			throws AuthenticationException, IOException, ServletException {
		
			String header = request.getHeader("Authorization"); //VARIABLE KEY DEL TOKEN EN EL HEADER
			if( header == null || !header.startsWith("6d6354ece40846bf7fca65dfabd5d9d4")) { //CAPTURAMOS EL PRIMER FILTRO DE LA CONTRASEÑA 6d6354ece40846bf7fca65dfabd5d9d4
				throw new RuntimeException("Token de JWT Perdido " + header + " " + request.getHeaders("Authorization") + " <-- aca");
				
			}
			String authenticationToken = header.substring(32); //SEPARAMOS EL HEADER 32 arriba
			System.out.println(authenticationToken);
			JwtAuthenticationToken token = new JwtAuthenticationToken(authenticationToken);
			return getAuthenticationManager().authenticate(token);
			
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		// TODO Auto-generated method stub
		super.successfulAuthentication(request, response, chain, authResult);
		chain.doFilter(request, response);
	}

		
}
