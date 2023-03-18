package com.posgrado.ecommerce.entity;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
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
@Table(name = "roles")
public class Role {

  @Id
  @GeneratedValue
  @Type(type = "uuid-char")
  private UUID id;
  @NotBlank(message = "{role.name.not-blank}")
  @Column(unique = true)
  private String name;
  @NotBlank(message = "{role.description.not-blank}")
  private String description;

  public Role(String name) {
    this.name = name;
  }
}
