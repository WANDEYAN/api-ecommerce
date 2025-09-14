package br.com.ecommerce.api.model;


import java.util.List;

import br.com.ecommerce.api.dto.ProductRequestDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

    @Column(unique = true)
    private String name;
    private String description;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductImage> image;

    private double price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    
    private int quantity;

    @Column(name = "inventory_status")
    private String inventoryStatus;
    private int rating;

    public Product(ProductRequestDTO productDto, Category category){
        this.name = productDto.getName();
        this.description = productDto.getDescription();
        this.price = productDto.getPrice();
        this.category = category;
        this.quantity = productDto.getQuantity();
        this.inventoryStatus = productDto.getInventoryStatus();
        this.rating = productDto.getRating();
    }

}
