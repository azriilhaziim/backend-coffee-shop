package com.accenture.ws.service;

import com.accenture.ws.entity.CafeClerk;
import com.accenture.ws.entity.Order;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RegularBillTest {

    @Test
    void getTotalBill_SumsAllOrderPrices() {
        List<Order> orders = Arrays.asList(
                new Order("Espresso", 4.50, false),
                new Order("Cappuccino", 5.00, true),
                new Order("Latte", 6.00, false)
        );
        CafeClerk clerk = new CafeClerk("Test Clerk");
        RegularBill bill = new RegularBill(clerk, orders);

        assertThat(bill.getTotalBill()).isEqualTo(15.50);
    }

    @Test
    void getTotalBill_ReturnsZero_WhenOrderListIsEmpty() {
        List<Order> orders = Collections.emptyList();
        CafeClerk clerk = new CafeClerk("Test Clerk");
        RegularBill bill = new RegularBill(clerk, orders);

        assertThat(bill.getTotalBill()).isEqualTo(0.0);
    }
}