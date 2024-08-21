package com.example.techtask.repositories;

import com.example.techtask.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query("SELECT * FROM orders o\n" +
            "WHERE o.quantity > 1\n" +
            "ORDER BY o.created_at DESC\n" +
            "LIMIT 1;")
    Optional<Order> findLatestOrder();

    @Query("SELECT o.id, o.product_name, o.price, o.quantity, o.user_id, o.created_at, o.order_status\n" +
            "FROM orders o\n" +
            "JOIN users u ON o.user_id = u.id\n" +
            "WHERE u.user_status = 'ACTIVE'\n" +
            "ORDER BY o.created_at;")
    List<Order> findOrdersWithActiveUsersSorted();
}
