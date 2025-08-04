package br.com.ecommerce.api.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.ecommerce.api.dto.OrderRequestDTO;
import br.com.ecommerce.api.model.Order;
import br.com.ecommerce.api.model.OrderItem;
import br.com.ecommerce.api.model.Product;
import br.com.ecommerce.api.model.User;
import br.com.ecommerce.api.repository.OrderItemRepository;
import br.com.ecommerce.api.repository.OrderRepository;
import br.com.ecommerce.api.service.exceptions.InsufficientStockException;
import jakarta.transaction.Transactional;

@Service
public class OrderService{
    
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private ProductService productService;

    @Transactional
    public Order createOrder(OrderRequestDTO data, User authenticatedUser){
        Order newOrder = new Order();
        newOrder.setUser(authenticatedUser);
        newOrder.setOrderDate(LocalDateTime.now());
        List<OrderItem> orderItems = new ArrayList<>();
        data.getItems().forEach(item -> {
            Product product = productService.getProductById(item.getProductId());
            if(item.getQuantity() <= product.getQuantity()){
                orderItems.add(new OrderItem(item.getQuantity(), product.getPrice(), newOrder, product));
                product.setQuantity(product.getQuantity() - item.getQuantity());
                productService.updateProduct(product.getId(), product);
            }else{
                throw new InsufficientStockException("Insufficient stock for product");
            }
        });
        orderItemRepository.saveAll(orderItems);
        newOrder.setOrderItem(orderItems);
        return orderRepository.save(newOrder);
    }

    public Page<Order> getAllOrders(Pageable pageable){
        return orderRepository.findAll(pageable);
    }

    public Page<Order> getOrdersByUser(User user, Pageable pageable){
        return orderRepository.findByUser(user, pageable);
    }
}