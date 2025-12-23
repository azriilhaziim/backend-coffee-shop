package com.accenture.ws.controller;

import com.accenture.ws.entity.Order;
import com.accenture.ws.service.interfaces.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderAndBillingController.class)
public class OrderAndBillingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Autowired
    private ObjectMapper objectMapper;

    private Order order1;
    private Order order2;

    @BeforeEach
    void setUp() {
        order1 = new Order("Espresso", 4.50, false);
        order1.setId(1L);
        order2 = new Order("Cappuccino", 5.00, true);
        order2.setId(2L);
    }

    @Test
    void getOrderList_ReturnsAllOrders() throws Exception {
        List<Order> allOrders = Arrays.asList(order1, order2);
        when(orderService.findAllOrders()).thenReturn(allOrders);

        mockMvc.perform(get("/api/orders"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].orderName").value("Espresso"));

        verify(orderService, times(1)).findAllOrders();
    }

    @Test
    void getOrder_ReturnsOrder_WhenIdExists() throws Exception {
        when(orderService.findOrderById(1L)).thenReturn(order1);

        mockMvc.perform(get("/api/orders/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orderName").value("Espresso"));

        verify(orderService, times(1)).findOrderById(1L);
    }

    @Test
    void getOrder_ReturnsNotFound_WhenIdDoesNotExist() throws Exception {
        when(orderService.findOrderById(99L)).thenReturn(null);

        mockMvc.perform(get("/api/orders/{id}", 99L))
                .andExpect(status().isOk()); // Spring MVC returns 200 OK with a null body by default for Optional.ofNullable(null)

        verify(orderService, times(1)).findOrderById(99L);
    }

    @Test
    void addOrder_ReturnsCreatedOrder() throws Exception {
        when(orderService.createOrder(any(Order.class))).thenReturn(order1);

        mockMvc.perform(post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(order1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orderName").value("Espresso"));

        verify(orderService, times(1)).createOrder(any(Order.class));
    }

    @Test
    void updateOrder_ReturnsOk() throws Exception {
        Order updatedOrder = new Order("Latte", 6.00, false);
        updatedOrder.setId(1L);

        // When the service is called with any Long and any Order object, do nothing.
        doNothing().when(orderService).updateOrder(anyLong(), any(Order.class));

        mockMvc.perform(put("/api/orders/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedOrder)))
                .andExpect(status().isOk());

        // Verify that the service's updateOrder method was called once
        // with the correct ID (1L) and with ANY Order object.
        verify(orderService, times(1)).updateOrder(eq(1L), any(Order.class));
    }

    @Test
    void deleteOrder_ReturnsOk() throws Exception {
        doNothing().when(orderService).deleteOrder(anyLong());

        mockMvc.perform(delete("/api/orders/{id}", 1L))
                .andExpect(status().isOk());

        verify(orderService, times(1)).deleteOrder(1L);
    }

    @Test
    void getTotalRegularBill_ReturnsCorrectTotal() throws Exception {
        when(orderService.getRegularBillTotal()).thenReturn(9.50);

        mockMvc.perform(get("/api/orders/bill/regular"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(9.50));

        verify(orderService, times(1)).getRegularBillTotal();
    }

    @Test
    void getTotalDiscountedBill_ReturnsCorrectTotal() throws Exception {
        when(orderService.getDiscountedBillTotal()).thenReturn(9.25);

        mockMvc.perform(get("/api/orders/bill/discounted"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(9.25));

        verify(orderService, times(1)).getDiscountedBillTotal();
    }
}