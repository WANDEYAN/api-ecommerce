package br.com.ecommerce.api.dto;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.ecommerce.api.model.ProductImage;

@Component
public class ProductImageMapper {

    private String prefixThumbnail;
    private String imageBaseUrl;
    private String webHost;

    public ProductImageMapper(@Value("${api.filestorage.uploads.thumbnail}") String prefix, @Value("${api.filestorage.uploads.base-url}") String imageBaseUrl){
        this.prefixThumbnail = prefix;
        this.imageBaseUrl = imageBaseUrl;
        this.webHost = "https://9000-" + System.getenv("WEB_HOST");
    }

    public ProductImageResponseDTO toResponseDTO(ProductImage productImage){
        ProductImageResponseDTO dto = new ProductImageResponseDTO();
        dto.setImageUrl(webHost + imageBaseUrl + productImage.getImageUrl());
        dto.setThumbnailUrl(webHost + imageBaseUrl + prefixThumbnail + productImage.getImageUrl());
        return dto;
    }
}
