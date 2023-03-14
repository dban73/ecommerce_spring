package com.posgrado.ecommerce.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.posgrado.ecommerce.entity.User;
import java.util.Date;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

  public String generateToken(User user) {
    return JWT.create()
        .withSubject(user.getEmail())
        .withClaim("id", user.getId().toString())
        .withClaim("role", user.getRole().getName())
        .withIssuedAt(new Date())
        .withExpiresAt(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
        .sign(Algorithm.HMAC256("myS3creyK3y"));
  }

  public DecodedJWT decodeToken(String token) {
    JWTVerifier verifier = JWT.require(Algorithm.HMAC256("myS3creyK3y")).build();
    return verifier.verify(token);
  }

}
