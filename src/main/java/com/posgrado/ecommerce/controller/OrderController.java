package com.posgrado.ecommerce.controller;

import com.posgrado.ecommerce.dto.OrderDTO;
import com.posgrado.ecommerce.dto.OrderResponse;
import com.posgrado.ecommerce.service.OrderService;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {

  private OrderService orderService;

  @PostMapping
  public ResponseEntity<OrderResponse> save(@RequestBody OrderDTO orderDTO) {
    OrderResponse response = orderService.save(orderDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  /*@GetMapping("/total/{id}")
  public Map<String, Object> getTotalPrice (@PathVariable UUID id){
    Map<String, Object> result = new HashMap<>();
    result.put("totalPrice-JPQL", orderRepository.getTotalPriceOrder(id));
    return result;
  }

  @GetMapping("/items/{id}")
  public List<OrderItemDTO> getItemsWithTotalPrice (@PathVariable UUID id){
    return orderRepository.getItemsWithTotalPrice(id);
  }*/

  @GetMapping("/{id}")
  public ResponseEntity<OrderDTO> getById(@PathVariable UUID id) {
    return ResponseEntity.ok(orderService.getById(id));
  }


}
