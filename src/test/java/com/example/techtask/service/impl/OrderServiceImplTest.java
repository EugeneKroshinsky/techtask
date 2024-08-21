package com.example.techtask.service.impl;

import com.example.techtask.model.Order;
import com.example.techtask.model.User;
import com.example.techtask.repositories.OrderRepository;
import jakarta.persistence.NoResultException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @Mock
    private Order order;

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    @Test
    void testFindOrder() {
        when(orderRepository.findLatestOrderWithMultipleItems(anyInt())).thenReturn(Optional.of(order));
        Order result = orderService.findOrder();
        assertNotNull(result);
        assertEquals(order, result);
    }

    @Test
    void testFindOrder_NoResultException() {
        when(orderRepository.findLatestOrderWithMultipleItems(anyInt())).thenReturn(Optional.empty());
        assertThrows(NoResultException.class, () -> orderService.findOrder());
    }

    @Test
    void testFindOrders() {
        List<Order> orders = List.of(order);
        when(orderRepository.findOrdersWithActiveUsersSorted()).thenReturn(orders);
        List<Order> result = orderService.findOrders();
        assertNotNull(result);
        assertEquals(orders, result);
    }
}