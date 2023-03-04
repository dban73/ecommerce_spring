package com.posgrado.ecommerce.entity;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue
  @Type(type = "uuid-char")
  private UUID id;
  private String firstName;
  private String lastName;
  @Column(unique = true)
  private String email;
  private String password;
  private String address;
  @ManyToOne
  @JoinColumn(name = "role_id")
  private Role role;

}
