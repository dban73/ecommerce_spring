package com.posgrado.ecommerce.service;

import com.posgrado.ecommerce.dto.RegistrationRequest;
import com.posgrado.ecommerce.dto.UserDTO;
import java.util.UUID;

public interface UserService {

  UserDTO getById(UUID id);

  String register(RegistrationRequest request);
}
