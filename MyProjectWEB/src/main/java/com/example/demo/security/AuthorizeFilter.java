package com.example.demo.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class AuthorizeFilter extends OncePerRequestFilter{
	
	@Autowired
	JWTUtil jwt;
	
	@Autowired
	DetailUserService userService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String token = request.getHeader("Authorization");
			if(token != null && token.startsWith("Bearer ")) {
				token = token.substring(7);
				String email = jwt.extractEmail(token);
				if(email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
					UserDetails user = userService.loadUserByUsername(email);
					if(jwt.validateToken(token, user)) {
						UsernamePasswordAuthenticationToken authenticate = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
						authenticate.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
						SecurityContextHolder.getContext().setAuthentication(authenticate);
					}
				}
			}	
			filterChain.doFilter(request, response);
			
		}catch(Exception e){
			
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
		}
	}
}
