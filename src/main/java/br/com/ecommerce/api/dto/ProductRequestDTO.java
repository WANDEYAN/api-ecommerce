package br.com.ecommerce.api.dto;

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


}
