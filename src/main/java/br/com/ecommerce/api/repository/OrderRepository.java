package br.com.ecommerce.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ecommerce.api.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}