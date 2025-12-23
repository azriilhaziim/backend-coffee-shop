package com.accenture.ws.service.interfaces;

import com.accenture.ws.entity.Order;
import java.util.List;

public interface OrderService {
    List<Order> findAllOrders();
    Order findOrderById(Long id);
    Order createOrder(Order order);
    void updateOrder(Long id, Order order);
    void deleteOrder(Long id);
    double getRegularBillTotal();
    double getDiscountedBillTotal();
}