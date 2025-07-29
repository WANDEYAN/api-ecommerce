package br.com.ecommerce.api.dto;

import br.com.ecommerce.api.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDTO {

    private String name;
    private String description;
    private String image;
    private double price;
    private Long categoryId;
    private int quantity;
    private String inventoryStatus;
    private int rating;

    public ProductRequestDTO(Product product){
        this.name = product.getName();
        this.description = product.getDescription();
        this.image = product.getImage();
        this.price = product.getPrice();
        this.categoryId = product.getCategory().getId();
        this.quantity = product.getQuantity();
        this.inventoryStatus = product.getInventoryStatus();
        this.rating = product.getRating();
    }
}
