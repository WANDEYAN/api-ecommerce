package br.com.ecommerce.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ecommerce.api.model.Order;
import br.com.ecommerce.api.model.User;

public interface OrderRepository extends JpaRepository<Order, Long>{
    public Page<Order> findByUser(User user, Pageable pageable);
}