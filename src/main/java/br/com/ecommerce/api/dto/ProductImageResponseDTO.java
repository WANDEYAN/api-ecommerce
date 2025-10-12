package br.com.ecommerce.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductImageResponseDTO {
    private String imageUrl;
    private String thumbnailUrl;
}
