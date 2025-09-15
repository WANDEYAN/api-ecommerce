package br.com.ecommerce.api.dto;

import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import br.com.ecommerce.api.model.Product;

@Component
public class ProductMapper {
    private final ProductImageMapper productImageMapper;

    public ProductMapper(ProductImageMapper imageMapper){
        this.productImageMapper = imageMapper;
    }

    public ProductResponseDTO toResponseDTO(Product product){
        ProductResponseDTO dto = new ProductResponseDTO(product);
        if(product.getImage() != null){
            dto.setImage(product.getImage().stream()
                        .map(productImageMapper::toResponseDTO)
                        .collect(Collectors.toList()));
        }
        return dto;
    }

    public Page<ProductResponseDTO> toResponsePageDTO(Page<Product> productPage){
        return productPage.map(this::toResponseDTO);
    }
}
