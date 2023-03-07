package com.posgrado.ecommerce.service;

import com.posgrado.ecommerce.dto.RegistrationRequest;
import com.posgrado.ecommerce.dto.UserDTO;
import com.posgrado.ecommerce.entity.ConfirmationToken;
import com.posgrado.ecommerce.entity.User;
import com.posgrado.ecommerce.exception.EmailAlreadyTakenException;
import com.posgrado.ecommerce.exception.EntityNotFoundException;
import com.posgrado.ecommerce.mapper.UserMapper;
import com.posgrado.ecommerce.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

  private UserRepository userRepository;
  private UserMapper userMapper;

  private PasswordEncoder passwordEncoder;

  private ConfirmationTokenService confirmationTokenService;

  @Override
  public UserDTO getById(UUID id) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("User", id));
    return userMapper.convertToUserDTO(user);
  }

  @Override
  public String register(RegistrationRequest dto) {
    boolean emailExists = userRepository.findByEmail(dto.getEmail()).isPresent();
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
    userRepository.save(user);

    String token = UUID.randomUUID().toString();

    ConfirmationToken confirmationToken = new ConfirmationToken(
        token,
        LocalDateTime.now(),
        LocalDateTime.now().plusMinutes(15),
        user
    );
    confirmationTokenService.save(confirmationToken);
    //TODO: Send Email with confirmation token link
    return token;
  }
}
