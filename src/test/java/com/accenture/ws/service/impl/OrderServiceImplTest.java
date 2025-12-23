package com.accenture.ws.service.impl;

import com.accenture.ws.entity.Order;
import com.accenture.ws.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    private Order order1;
    private Order order2;

    @BeforeEach
    void setUp() {
        order1 = new Order("Espresso", 4.50, false);
        order1.setId(1L);
        order2 = new Order("Cappuccino", 5.00, true);
        order2.setId(2L);
        order2.setDiscountPercentage(5.0);
    }

    @Test
    void findAllOrders_ReturnsAllOrders() {
        when(orderRepository.findAll()).thenReturn(Arrays.asList(order1, order2));

        List<Order> result = orderService.findAllOrders();
        assertThat(result).hasSize(2);
        assertThat(result).contains(order1, order2);
    }

    @Test
    void findOrderById_ReturnsOrder_WhenIdExists() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order1));

        Order result = orderService.findOrderById(1L);
        assertThat(result).isEqualTo(order1);
    }

    @Test
    void findOrderById_ReturnsNull_WhenIdDoesNotExist() {
        when(orderRepository.findById(99L)).thenReturn(Optional.empty());

        Order result = orderService.findOrderById(99L);
        assertThat(result).isNull();
    }

    @Test
    void createOrder_SavesAndReturnsOrder() {
        when(orderRepository.save(any(Order.class))).thenReturn(order1);

        Order result = orderService.createOrder(new Order("Espresso", 4.50, false));
        assertThat(result).isEqualTo(order1);
    }

    @Test
    void updateOrder_UpdatesAndSavesOrder_WhenIdExists() {
        when(orderRepository.existsById(1L)).thenReturn(true);
        when(orderRepository.save(any(Order.class))).thenReturn(order1);

        Order updatedOrder = new Order("Espresso", 5.00, false);
        orderService.updateOrder(1L, updatedOrder);

        verify(orderRepository, times(1)).save(updatedOrder);
        assertThat(updatedOrder.getId()).isEqualTo(1L);
    }

    @Test
    void updateOrder_DoesNothing_WhenIdDoesNotExist() {
        when(orderRepository.existsById(99L)).thenReturn(false);
        Order updatedOrder = new Order("Espresso", 5.00, false);

        orderService.updateOrder(99L, updatedOrder);

        verify(orderRepository, never()).save(any(Order.class));
    }

    @Test
    void deleteOrder_DeletesOrder() {
        orderService.deleteOrder(1L);
        verify(orderRepository, times(1)).deleteById(1L);
    }

    @Test
    void getRegularBillTotal_CalculatesCorrectTotal() {
        when(orderRepository.findAll()).thenReturn(Arrays.asList(order1, order2));
        double total = orderService.getRegularBillTotal();
        assertThat(total).isEqualTo(9.50); // 4.50 + 5.00
    }

    @Test
    void getDiscountedBillTotal_CalculatesCorrectTotal() {
        when(orderRepository.findAll()).thenReturn(Arrays.asList(order1, order2));
        double total = orderService.getDiscountedBillTotal();
        double discountedPrice = 5.00 - (5.00 * 0.05); // 4.75
        assertThat(total).isEqualTo(4.50 + 4.75); // 9.25
    }
}