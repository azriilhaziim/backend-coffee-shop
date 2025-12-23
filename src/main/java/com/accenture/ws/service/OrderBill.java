package com.accenture.ws.service;

import com.accenture.ws.entity.CafeClerk;
import com.accenture.ws.entity.Order;
import java.util.List;

public abstract class OrderBill {
    private List<Order> orderList; // orderList : List<Order>
    private CafeClerk clerk; // clerk : CafeClerk

    public OrderBill(CafeClerk clerk, List<Order> orderList) {
        this.clerk = clerk;
        this.orderList = orderList;
    }

    // Abstract method to calculate the total bill, implemented by subclasses
    public abstract double getTotalBill();

    // Getters and Setters

    public CafeClerk getClerk() {
        return clerk;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }
}