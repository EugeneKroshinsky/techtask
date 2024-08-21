package com.example.techtask.service.impl;

import com.example.techtask.model.Order;
import com.example.techtask.repositories.OrderRepository;
import com.example.techtask.service.OrderService;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order findOrder() {
        return orderRepository.findLatestOrder().orElseThrow(NoResultException::new);
    }

    @Override
    public List<Order> findOrders() {
        List<Order> orders = orderRepository.findOrdersWithActiveUsersSorted();
        if (orders != null) {
            return orders;
        } else {
            throw new NoResultException();
        }
    }
}
