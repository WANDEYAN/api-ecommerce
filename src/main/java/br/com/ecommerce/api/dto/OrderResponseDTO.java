package br.com.ecommerce.api.dto;

import java.time.LocalDateTime;
import java.util.List;

import br.com.ecommerce.api.model.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDTO{
    private Long id;
    private LocalDateTime orderDate;
    private List<OrderItemResponseDTO> items;
    private UserResponseDTO user;

    public OrderResponseDTO(Order data){
        this.id = data.getId();
        this.orderDate = data.getOrderDate();
        this.items = data.getOrderItem().stream().map(OrderItemResponseDTO::new).toList();
        this.user = new UserResponseDTO(data.getUser().getName(), data.getUser().getEmail());
    }
}
