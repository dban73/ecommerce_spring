package com.posgrado.ecommerce.repository;

import com.posgrado.ecommerce.dto.OrderItemDTO;
import com.posgrado.ecommerce.entity.Order;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {

  /*@Query(value="SELECT SUM(products.price * order_items.quantity) "
      + "FROM orders "
      + "JOIN order_items ON orders.id = order_items.order_id "
      + "JOIN products ON order_items.product_id = products.id "
      + "WHERE orders.id = ?1", nativeQuery = true)
  Double getTotalPrice(String orderId);*/

  @Query("SELECT SUM(p.price * oi.quantity) "
      + "FROM Order o "
      + "JOIN o.items oi "
      + "JOIN oi.product p "
      + "WHERE o.id = ?1")
  Double getTotalPriceOrder(UUID orderId);

  @Query(
      "SELECT new com.posgrado.ecommerce.dto.OrderItemDTO(p.id, oi.quantity, oi.quantity * p.price) "
          + "FROM Order o "
          + "JOIN o.items oi "
          + "JOIN oi.product p "
          + "WHERE o.id = ?1")
  List<OrderItemDTO> getItemsWithTotalPrice(UUID orderId);

}
