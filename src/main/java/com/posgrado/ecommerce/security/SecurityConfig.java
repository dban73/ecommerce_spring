package com.posgrado.ecommerce.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

  private static final String[] WHITE_LIST = {
      "/auth/**",
      "/v2/api-docs",
      "/swagger-ui/index.html",
      "/swagger-resources/**",
      "/webjars/**",
      "/configuration/**"
  };

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf().disable();
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    http.authorizeRequests((authz) -> {
      authz
          .antMatchers(WHITE_LIST).permitAll()
          .anyRequest().authenticated();
    });
    return http.build();
  }

}
