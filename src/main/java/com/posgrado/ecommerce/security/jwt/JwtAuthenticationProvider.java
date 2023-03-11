package com.posgrado.ecommerce.security.jwt;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.posgrado.ecommerce.entity.Role;
import com.posgrado.ecommerce.entity.User;
import java.util.UUID;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class JwtAuthenticationProvider implements AuthenticationProvider {

  private final JwtService jwtService;

  public JwtAuthenticationProvider(JwtService jwtService) {
    this.jwtService = jwtService;
  }

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) authentication;
    String token = jwtAuthenticationToken.getToken();
    DecodedJWT decodedJWT = jwtService.decodeToken(token);
    UUID userId = decodedJWT.getClaim("id").as(UUID.class);
    String email = decodedJWT.getSubject();
    String roleName = decodedJWT.getClaim("role").as(String.class);

    User user = new User();
    user.setId(userId);
    user.setEmail(email);
    user.setRole(new Role(roleName));

    return new JwtAuthenticationToken(user, token, user.getAuthorities());
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return JwtAuthenticationToken.class.isAssignableFrom(authentication);
  }
}
