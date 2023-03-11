package com.posgrado.ecommerce.service;

import com.posgrado.ecommerce.dto.UserDTO;
import com.posgrado.ecommerce.entity.User;
import com.posgrado.ecommerce.exception.EntityNotFoundException;
import com.posgrado.ecommerce.mapper.UserMapper;
import com.posgrado.ecommerce.repository.UserRepository;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

  private UserRepository userRepository;
  private UserMapper userMapper;

  @Override
  public UserDTO getById(UUID id) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("User", id));
    return userMapper.convertToUserDTO(user);
  }

  @Override
  public boolean existByEmail(String email) {
    return userRepository.findByEmail(email).isPresent();
  }

  @Override
  public User getByEmail(String email) {
    return userRepository.findByEmail(email)
        .orElseThrow(() -> new EntityNotFoundException("User not found"));
  }

  @Override
  public User save(User user) {
    return userRepository.save(user);
  }

  @Override
  public void enable(UUID id) {
    User user = userRepository.findById(id).get();
    user.setEnable(true);
    userRepository.save(user);
  }

}
