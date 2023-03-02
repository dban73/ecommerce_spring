package com.posgrado.ecommerce.mapper;

import com.posgrado.ecommerce.dto.UserDTO;
import com.posgrado.ecommerce.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDTO convertToUserDTO(User user){
      UserDTO dto = new UserDTO();
      dto.setId(user.getId());
      dto.setFirstName(user.getFirstName());
      dto.setLastName(user.getLastName());
      dto.setAddress(user.getAddress());
      dto.setEmail(user.getEmail());
      dto.setRoleName(user.getRole().getName());
      return dto;
    }
}
