package com.accenture.ws.repository;

import com.accenture.ws.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

// OrderRepository extends JpaRepository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // Basic CRUD operations on the Order entity are inherited.
}
