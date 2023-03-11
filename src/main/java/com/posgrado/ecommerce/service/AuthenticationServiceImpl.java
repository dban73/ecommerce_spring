package com.posgrado.ecommerce.service;

import com.posgrado.ecommerce.dto.AuthenticationRequest;
import com.posgrado.ecommerce.dto.AuthenticationResponse;
import com.posgrado.ecommerce.entity.User;
import com.posgrado.ecommerce.security.jwt.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

  private AuthenticationManager authenticationManager;
  private JwtService jwtService;
  private UserService userService;

  @Override
  public AuthenticationResponse authenticate(AuthenticationRequest dto) {
    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
        dto.getEmail(), dto.getPassword());
    authenticationManager.authenticate(authentication);

    User user = userService.getByEmail(dto.getEmail());
    String jwt = jwtService.generateToken(user);
    return new AuthenticationResponse(jwt);
  }
}
