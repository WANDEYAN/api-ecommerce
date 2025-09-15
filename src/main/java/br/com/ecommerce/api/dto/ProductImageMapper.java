package br.com.ecommerce.api.dto;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.ecommerce.api.model.ProductImage;

@Component
public class ProductImageMapper {

    private String prefixThumbnail;

    public ProductImageMapper(@Value("${api.filestorage.uploads.thumbnail}") String prefix){
        this.prefixThumbnail = prefix;
    }

    public ProductImageResponseDTO toResponseDTO(ProductImage productImage){
        ProductImageResponseDTO dto = new ProductImageResponseDTO(productImage);
        dto.setThumbnailUrl(prefixThumbnail + dto.getImageUrl());
        return dto;
    }
}
