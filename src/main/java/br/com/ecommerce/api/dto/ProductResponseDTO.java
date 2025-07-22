package br.com.ecommerce.api.dto;

import br.com.ecommerce.api.model.Category;
import br.com.ecommerce.api.model.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDTO {
     
    private Long id;
    private String code;
    private String name;
    private String description;
    private String image;
    private double price;
    private Category category;
    private int quantity;
    private String inventoryStatus;
    private int rating;

    public ProductResponseDTO(Product product) {
       
        this.id = product.getId();
        this.code = product.getCode();
        this.name = product.getName();
        this.description = product.getDescription();
        this.image = product.getImage();
        this.price = product.getPrice();
        this.category = product.getCategory();
        this.quantity = product.getQuantity();
        this.inventoryStatus = product.getInventoryStatus();
        this.rating = product.getRating();

    }

}
