package com.accenture.ws.controller;

import com.accenture.ws.entity.Order;
import com.accenture.ws.service.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins ="http://localhost:2002")
public class OrderAndBillingController {

    private final OrderService orderService; // Use the service interface

    @Autowired
    public OrderAndBillingController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<Order> getOrderList() {
        return orderService.findAllOrders();
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable Long id) {
        return orderService.findOrderById(id);
    }

    @PostMapping
    public Order addOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @PutMapping("/{id}")
    public void updateOrder(@PathVariable Long id, @RequestBody Order updatedOrder) {
        orderService.updateOrder(id, updatedOrder);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }

    @GetMapping("/bill/regular")
    public double getTotalRegularBill() {
        return orderService.getRegularBillTotal();
    }

    @GetMapping("/bill/discounted")
    public double getTotalDiscountedBill() {
        return orderService.getDiscountedBillTotal();
    }
}