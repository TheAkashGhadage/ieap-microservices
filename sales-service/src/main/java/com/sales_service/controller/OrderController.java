package com.sales_service.controller;


import com.sales_service.dto.OrderDTO;
import com.sales_service.entity.Order;
import com.sales_service.service.OrderService;
import jakarta.validation.Valid;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Create a new order
    @PostMapping
    public ResponseEntity<Order> createOrder(@Valid @RequestBody OrderDTO dto) {
        Order order = Order.builder()
                .customerName(dto.getCustomerName())
                .productCode(dto.getProductCode())
                .quantity(dto.getQuantity())
                .totalAmount(dto.getTotalAmount())
                .build();
        return ResponseEntity.ok(orderService.createOrder(order));
    }

    // Get all orders
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    // Get order by ID
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        return (order != null)
                ? ResponseEntity.ok(order)
                : ResponseEntity.notFound().build();
    }

    // Delete order by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.ok("Order deleted successfully");
    }
}