package com.posgrado.ecommerce.service;

import com.posgrado.ecommerce.dto.OrderDTO;
import com.posgrado.ecommerce.dto.OrderResponse;
import java.util.UUID;

public interface OrderService {

  OrderResponse save(OrderDTO orderDTO);

  OrderDTO getById(UUID id);

}
