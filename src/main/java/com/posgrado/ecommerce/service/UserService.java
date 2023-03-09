package com.posgrado.ecommerce.service;

import com.posgrado.ecommerce.dto.UserDTO;
import com.posgrado.ecommerce.entity.User;
import java.util.UUID;

public interface UserService {

  UserDTO getById(UUID id);

  boolean existByEmail(String email);

  User getByEmail(String email);

  User save(User user);

  void enable(UUID id);
}
