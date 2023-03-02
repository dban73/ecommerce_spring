package com.posgrado.ecommerce.service;

import com.posgrado.ecommerce.entity.Role;
import com.posgrado.ecommerce.repository.RoleRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService{

  private RoleRepository roleRepository;
  @Override
  public Role getByName(String name) {
    return roleRepository.findByName(name);
  }

  @Override
  public List<Role> getAll() {
    return roleRepository.findAll();
  }
}
