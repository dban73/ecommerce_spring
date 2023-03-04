package com.posgrado.ecommerce.service;

import com.posgrado.ecommerce.dto.OrderDTO;
import com.posgrado.ecommerce.entity.Order;
import com.posgrado.ecommerce.entity.OrderItem;
import com.posgrado.ecommerce.exception.EntityNotFoundException;
import com.posgrado.ecommerce.repository.OrderRepository;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

  private OrderRepository orderRepository;

  private ProductService productService;

  @Override
  public String save(OrderDTO dto) {
    Order order = new Order();
    order.setComment(dto.getComment());
    List<OrderItem> items = dto.getItems().stream().map(itemDTO -> {
      OrderItem item = new OrderItem();
      item.setQuantity(itemDTO.getQuantity());
      item.setProduct(productService.getById(itemDTO.getProductId()));
      item.setOrder(order);
      return item;
    }).toList();
    order.setItems(items);
    Order orderSaved = orderRepository.save(order);
    return orderSaved.getId().toString();
  }

  @Override
  public OrderDTO getById(UUID id) {
    Order order = orderRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Order", id));
    OrderDTO dto = new OrderDTO();
    dto.setComment(order.getComment());
    dto.setDate(order.getDate());
    dto.setState(order.getState());
    dto.setTotalPrice(orderRepository.getTotalPriceOrder(id));
    dto.setItems(orderRepository.getItemsWithTotalPrice(id));
    return dto;
  }
}
