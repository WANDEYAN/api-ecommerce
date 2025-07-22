package br.com.ecommerce.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ecommerce.api.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
    public Optional<Category> findByName(String name);
}
