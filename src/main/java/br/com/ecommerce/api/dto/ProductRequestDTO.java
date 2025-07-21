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
    private String category;
    private int quantity;
    private String inventoryStatus;
    private int rating;

    public ProductRequestDTO(Product product){
        this.name = product.getName();
        this.description = product.getDescription();
        this.image = product.getImage();
        this.price = product.getPrice();
        this.category = product.getCategory();
        this.quantity = product.getQuantity();
        this.inventoryStatus = product.getInventoryStatus();
        this.rating = product.getRating();
    }

    public Product toProduct(){
        Product product = new Product();
        product.setName(this.name);
        product.setDescription(this.description);
        product.setImage(this.image);
        product.setPrice(this.price);
        product.setCategory(this.category);
        product.setQuantity(this.quantity);
        product.setInventoryStatus(this.inventoryStatus);
        product.setRating(this.rating);
        return product;
    }
}
