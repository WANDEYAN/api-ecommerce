package br.com.ecommerce.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ecommerce.api.dto.OrderRequestDTO;
import br.com.ecommerce.api.dto.OrderResponseDTO;
import br.com.ecommerce.api.model.User;
import br.com.ecommerce.api.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/orders")
public class OrderController{

    @Autowired
    private OrderService orderService;

    @Operation(summary = "Generetes a new order")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Created successfully"),
        @ApiResponse(responseCode = "400", description = "Insuffcient stock")
    })
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody @Valid OrderRequestDTO data, @AuthenticationPrincipal User authenticatedUser){
        OrderResponseDTO responseOrder = new OrderResponseDTO(orderService.createOrder(data, authenticatedUser));
        return new ResponseEntity<>(responseOrder, HttpStatus.CREATED);
    }

    @Operation(summary = "Search all orders data")
    @ApiResponse(responseCode = "200", description = "All resource found")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<OrderResponseDTO>> getAllOrders(Pageable pageable) {
        Page<OrderResponseDTO> ordersResponse = orderService.getAllOrders(pageable).map(OrderResponseDTO::new);
        return new ResponseEntity<>(ordersResponse, HttpStatus.OK);
    }

    @Operation(summary = "Search for only logged in user orders data")
    @ApiResponse(responseCode = "200", description = "All resource found of this user authenticated")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/my-orders")
    public ResponseEntity<Page<OrderResponseDTO>> getOrdersByUser(@AuthenticationPrincipal User authenticatedUser, Pageable pageable) {
        Page<OrderResponseDTO> orderResponse = orderService.getOrdersByUser(authenticatedUser, pageable).map(OrderResponseDTO::new);
        return new ResponseEntity<>(orderResponse, HttpStatus.OK);
    }
}