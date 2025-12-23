package com.accenture.ws.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // id : Long
    private String orderName; // orderName : String
    private double price; // price : double
    private boolean discounted; // isDiscounted : boolean

    // Default value as per diagram (double = 5.0)
    private double discountPercentage = 5.0;

    public Order() {
        // Default constructor
    }

    public Order(String orderName, double price, boolean discounted) {
        this.orderName = orderName;
        this.price = price;
        this.discounted = discounted;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isDiscounted() {
        return discounted;
    }

    public void setDiscounted(boolean discounted) {
        this.discounted = discounted;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }
}