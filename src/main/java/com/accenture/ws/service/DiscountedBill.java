package com.accenture.ws.service;

import com.accenture.ws.entity.CafeClerk;
import com.accenture.ws.entity.Order;

import java.util.List;

// DiscountedBill extends OrderBill
public class DiscountedBill extends OrderBill {

    public DiscountedBill(CafeClerk clerk, List<Order> orderList) {
        super(clerk, orderList);
    }

    @Override
    public double getTotalBill() {
        // Sums the price of all orders, applying the discount only to discounted orders.
        return getOrderList().stream()
                .mapToDouble(order -> {
                    if (order.isDiscounted()) {
                        double discountAmount = order.getPrice() * (order.getDiscountPercentage() / 100.0);
                        return order.getPrice() - discountAmount;
                    } else {
                        return order.getPrice();
                    }
                })
                .sum();
    }
}