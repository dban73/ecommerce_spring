package com.posgrado.ecommerce.controller;

import com.posgrado.ecommerce.entity.Role;
import com.posgrado.ecommerce.service.RoleService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/roles")
public class RoleController {

  private RoleService roleService;

  @GetMapping("/name/{name}")
  public ResponseEntity<Role> getByName(@PathVariable("name") String name){
    Role role = roleService.getByName(name);
    return ResponseEntity.ok(role);
  }

  @GetMapping
  public ResponseEntity<List<Role>> getAll(){
    return ResponseEntity.ok(roleService.getAll());
  }

}
