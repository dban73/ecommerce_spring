package com.posgrado.ecommerce.entity;

import java.util.UUID;
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

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {

  @Id
  @GeneratedValue
  @Type(type = "uuid-char")
  private UUID id;
  private String name;
  private String description;
  private String imageUrl;
  private Double price;
  private Integer stock;
  private Boolean active;
  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;
}
