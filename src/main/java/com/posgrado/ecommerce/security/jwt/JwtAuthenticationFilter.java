package com.posgrado.ecommerce.security.jwt;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@AllArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private AuthenticationManager authenticationManager;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {

    String authHeader = request.getHeader("Authorization");
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      filterChain.doFilter(request, response);
      return;
    }

    String jwt = authHeader.substring(7);

    JwtAuthenticationToken authentication = new JwtAuthenticationToken(jwt);
    JwtAuthenticationToken authenticationToken = (JwtAuthenticationToken) authenticationManager.authenticate(
        authentication);

    SecurityContext securityContext = SecurityContextHolder.getContext();
    if (securityContext.getAuthentication() == null) {
      securityContext.setAuthentication(authenticationToken);
    }

    filterChain.doFilter(request, response);
  }
}
