package com.accenture.ws.service;

import com.accenture.ws.entity.CafeClerk;
import com.accenture.ws.entity.Order;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DiscountedBillTest {

    @Test
    void getTotalBill_SumsUndiscountedAndDiscountedPrices() {
        Order order1 = new Order("Espresso", 4.50, false);
        Order order2 = new Order("Cappuccino", 5.00, true);
        order2.setDiscountPercentage(10.0);
        Order order3 = new Order("Latte", 6.00, true);
        order3.setDiscountPercentage(5.0);

        List<Order> orders = Arrays.asList(order1, order2, order3);
        CafeClerk clerk = new CafeClerk("Test Clerk");
        DiscountedBill bill = new DiscountedBill(clerk, orders);

        // Expected total: 4.50 + (5.00 - 0.50) + (6.00 - 0.30) = 4.50 + 4.50 + 5.70 = 14.70
        assertThat(bill.getTotalBill()).isEqualTo(14.70);
    }

    @Test
    void getTotalBill_ReturnsZero_WhenOrderListIsEmpty() {
        List<Order> orders = Collections.emptyList();
        CafeClerk clerk = new CafeClerk("Test Clerk");
        DiscountedBill bill = new DiscountedBill(clerk, orders);

        assertThat(bill.getTotalBill()).isEqualTo(0.0);
    }
}