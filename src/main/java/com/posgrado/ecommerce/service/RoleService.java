package com.posgrado.ecommerce.service;

import com.posgrado.ecommerce.entity.Role;
import java.util.List;
import org.w3c.dom.stylesheets.LinkStyle;

public interface RoleService {
  Role getByName(String name);

  List<Role> getAll();

}
