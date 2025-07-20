package br.com.ecommerce.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ecommerce.api.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
    public Optional<Product> findByName(String name);
}
