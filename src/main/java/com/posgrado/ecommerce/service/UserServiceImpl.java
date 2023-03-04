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
}
