package br.com.ecommerce.api.model;


import br.com.ecommerce.api.dto.ProductRequestDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String code;

    private String name;
    private String description;
    private String image;
    private double price;
    private String category;
    private int quantity;

    @Column(name = "inventory_status")
    private String inventoryStatus;
    private int rating;

    public Product(ProductRequestDTO productDto){
        this.name = productDto.getName();
        this.description = productDto.getDescription();
        this.image = productDto.getImage();
        this.price = productDto.getPrice();
        this.category = productDto.getCategory();
        this.quantity = productDto.getQuantity();
        this.inventoryStatus = productDto.getInventoryStatus();
        this.rating = productDto.getRating();
    }

}
