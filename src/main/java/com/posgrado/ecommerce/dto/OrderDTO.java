package com.posgrado.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.posgrado.ecommerce.entity.OrderState;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderDTO {

  private String comment;

  private List<OrderItemDTO> items;

  @JsonProperty(access = Access.READ_ONLY)
  private LocalDateTime date;
  @JsonProperty(access = Access.READ_ONLY)
  private OrderState state;

  @JsonProperty(access = Access.READ_ONLY)
  private double totalPrice;
}
