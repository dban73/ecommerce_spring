package com.posgrado.ecommerce.service;

import com.posgrado.ecommerce.dto.UserDTO;
import com.posgrado.ecommerce.entity.User;
import java.util.UUID;

public interface UserService {
  UserDTO getById(UUID id);
}
