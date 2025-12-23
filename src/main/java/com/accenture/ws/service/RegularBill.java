package com.accenture.ws.service;

import com.accenture.ws.entity.CafeClerk;
import com.accenture.ws.entity.Order;

import java.util.List;

// RegularBill extends OrderBill
public class RegularBill extends OrderBill {

    public RegularBill(CafeClerk clerk, List<Order> orderList) {
        super(clerk, orderList);
    }

    @Override
    public double getTotalBill() {
        // Sums the price of all orders, including discounted ones at their original price.
        return getOrderList().stream()
                .mapToDouble(Order::getPrice)
                .sum();
    }
}