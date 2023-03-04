package com.posgrado.ecommerce.service;

import com.posgrado.ecommerce.dto.OrderDTO;
import java.util.UUID;

public interface OrderService {

  String save(OrderDTO orderDTO);

  OrderDTO getById(UUID id);

}
