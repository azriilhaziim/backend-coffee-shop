package com.accenture.ws.service.impl;

import com.accenture.ws.entity.Order;
import com.accenture.ws.entity.CafeClerk;
import com.accenture.ws.repository.OrderRepository;
import com.accenture.ws.service.interfaces.OrderService;
import com.accenture.ws.service.RegularBill;
import com.accenture.ws.service.DiscountedBill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CafeClerk clerk = new CafeClerk("Admin Clerk");

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order findOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void updateOrder(Long id, Order order) {
        if (orderRepository.existsById(id)) {
            order.setId(id);
            orderRepository.save(order);
        }
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public double getRegularBillTotal() {
        List<Order> allOrders = findAllOrders();
        RegularBill regularBill = new RegularBill(clerk, allOrders);
        return regularBill.getTotalBill();
    }

    @Override
    public double getDiscountedBillTotal() {
        List<Order> allOrders = findAllOrders();
        DiscountedBill discountedBill = new DiscountedBill(clerk, allOrders);
        return discountedBill.getTotalBill();
    }
}
