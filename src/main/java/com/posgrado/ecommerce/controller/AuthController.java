package com.posgrado.ecommerce.controller;

import com.posgrado.ecommerce.dto.RegistrationRequest;
import com.posgrado.ecommerce.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

  private UserService userService;

  @PostMapping("/register")
  public ResponseEntity<String> register(@RequestBody RegistrationRequest request) {
    String token = userService.register(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(token);
  }

}
