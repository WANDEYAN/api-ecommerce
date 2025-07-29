package br.com.ecommerce.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ecommerce.api.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

}