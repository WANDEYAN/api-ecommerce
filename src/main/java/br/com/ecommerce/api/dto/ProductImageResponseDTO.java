package br.com.ecommerce.api.dto;

import br.com.ecommerce.api.model.ProductImage;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductImageResponseDTO {
    private String imageUrl;
    private String thumbnailUrl;

    public ProductImageResponseDTO(ProductImage productImage){
        this.imageUrl = productImage.getImageUrl();
        this.thumbnailUrl = "";
    }
}
