package com.posgrado.ecommerce.service;

import com.posgrado.ecommerce.dto.RegistrationRequest;
import com.posgrado.ecommerce.entity.ConfirmationToken;
import com.posgrado.ecommerce.entity.Role;
import com.posgrado.ecommerce.entity.User;
import com.posgrado.ecommerce.exception.EmailAlreadyTakenException;
import com.posgrado.ecommerce.util.HtmlGenerator;
import com.posgrado.ecommerce.util.UriGenerator;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RegistrationServiceImpl implements RegistrationService {

  private UserService userService;
  private ConfirmationTokenService confirmationTokenService;
  private PasswordEncoder passwordEncoder;

  private EmailService emailService;

  private RoleService roleService;

  @Override
  public String register(RegistrationRequest dto) {
    boolean emailExists = userService.existByEmail(dto.getEmail());
    if (emailExists) {
      throw new EmailAlreadyTakenException(dto.getEmail());
    }

    User user = new User();
    user.setFirstName(dto.getFirstName());
    user.setLastName(dto.getLastName());
    user.setAddress(dto.getAddress());
    user.setEmail(dto.getEmail());
    String encodedPassword = passwordEncoder.encode(dto.getPassword());
    user.setPassword(encodedPassword);

    Role defaultRole = roleService.getByName("USER");
    user.setRole(defaultRole);

    userService.save(user);

    String token = UUID.randomUUID().toString();
    ConfirmationToken confirmationToken = createConfirmationToken(token, user);
    confirmationTokenService.save(confirmationToken);

    String confirmationLink = UriGenerator.create("/auth/confirm", "token", token);
    String bodyHtml = HtmlGenerator.generateConfirmationTemplate(user.getFirstName(),
        confirmationLink);
    String subject = "Account Confirmation";
    emailService.sendTemplate(user.getEmail(), subject, bodyHtml);
    return token;
  }

  @Override
  public String confirm(String token) {
    ConfirmationToken confirmationToken = confirmationTokenService.getByToken(token);
    if (confirmationToken.getConfirmedAt() != null) {
      throw new RuntimeException("Token already confirmed");
    }
    if (confirmationToken.getExpiresAt().isBefore(LocalDateTime.now())) {
      throw new RuntimeException("Token expired");
    }
    userService.enable(confirmationToken.getUser().getId());
    confirmationTokenService.setConfirmedAt(confirmationToken);
    return "Your account has been confirmed";
  }

  private ConfirmationToken createConfirmationToken(String token, User user) {
    return new ConfirmationToken(
        token,
        LocalDateTime.now(),
        LocalDateTime.now().plusMinutes(15),
        user
    );
  }
}
