package br.com.ecommerce.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ecommerce.api.model.ProductImage;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
    
}
