package br.com.ecommerce.api.dto;

import org.springframework.stereotype.Component;

import br.com.ecommerce.api.model.ProductImage;

@Component
public class ProductImageMapper {

    public ProductImageResponseDTO toResponseDTO(ProductImage productImage){
        ProductImageResponseDTO dto = new ProductImageResponseDTO();
        dto.setImageUrl(productImage.getImageUrl());
        return dto;
    }
}
