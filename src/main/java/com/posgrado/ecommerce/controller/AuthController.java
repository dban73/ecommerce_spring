package com.posgrado.ecommerce.controller;

import com.posgrado.ecommerce.dto.AuthenticationRequest;
import com.posgrado.ecommerce.dto.AuthenticationResponse;
import com.posgrado.ecommerce.dto.RegistrationRequest;
import com.posgrado.ecommerce.service.AuthenticationService;
import com.posgrado.ecommerce.service.RegistrationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Authentication")
@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

  private RegistrationService registrationService;
  private AuthenticationService authenticationService;

  @ApiOperation("Register new users")
  @PostMapping("/register")
  public ResponseEntity<String> register(@RequestBody RegistrationRequest request) {
    String token = registrationService.register(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(token);
  }

  @ApiOperation("Config account")
  @GetMapping("/confirm")
  public ResponseEntity<String> confirm(@RequestParam String token) {
    String message = registrationService.confirm(token);
    return ResponseEntity.ok(message);
  }

  @ApiOperation("Authentication user")
  @PostMapping("/login")
  public ResponseEntity<AuthenticationResponse> authenticate(
      @RequestBody AuthenticationRequest request) {
    AuthenticationResponse response = authenticationService.authenticate(request);
    return ResponseEntity.ok(response);
  }

}
