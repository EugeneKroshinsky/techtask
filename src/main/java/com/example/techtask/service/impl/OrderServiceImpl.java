package com.example.techtask.service.impl;

import com.example.techtask.model.Order;
import com.example.techtask.model.enumiration.UserStatus;
import com.example.techtask.repositories.OrderRepository;
import com.example.techtask.service.OrderService;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order findOrder() {
        return orderRepository.findLatestOrderWithMultipleItems(1)
                .orElseThrow(NoResultException::new);
    }

    @Override
    public List<Order> findOrders() {
        return orderRepository.findOrdersWithUserStatus(UserStatus.ACTIVE);
    }
}
