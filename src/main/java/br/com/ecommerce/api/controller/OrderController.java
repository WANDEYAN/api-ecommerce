package br.com.ecommerce.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ecommerce.api.dto.OrderRequestDTO;
import br.com.ecommerce.api.dto.OrderResponseDTO;
import br.com.ecommerce.api.model.User;
import br.com.ecommerce.api.service.OrderService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/orders")
public class OrderController{

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody @Valid OrderRequestDTO data, @AuthenticationPrincipal User authenticatedUser){
        OrderResponseDTO responseOrder = new OrderResponseDTO(orderService.createOrder(data, authenticatedUser));
        return new ResponseEntity<>(responseOrder, HttpStatus.CREATED);
    }
}