package br.com.ecommerce.api.dto;

import br.com.ecommerce.api.model.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class OrderItemResponseDTO {
    private Long id;
    private Integer quantity;
    private ProductResponseDTO product;
    private Double price;

    public OrderItemResponseDTO(OrderItem data){
        this.id = data.getId();
        this.quantity = data.getQuantity();
        this.product = new ProductResponseDTO(data.getProduct());
        this.price = data.getPrice();
    }
}
