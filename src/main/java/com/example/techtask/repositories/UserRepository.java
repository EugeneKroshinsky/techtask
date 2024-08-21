package com.example.techtask.repositories;

import com.example.techtask.model.Order;
import com.example.techtask.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u.id, u.email, u.user_status, SUM(o.price * o.quantity) as total_amount\n" +
            "FROM users u\n" +
            "JOIN orders o ON u.id = o.user_id\n" +
            "WHERE EXTRACT(YEAR FROM o.created_at) = 2003\n" +
            "  AND o.order_status = 'DELIVERED'\n" +
            "GROUP BY u.id, u.email, u.user_status\n" +
            "ORDER BY total_amount DESC\n" +
            "LIMIT 1;")
    Optional<User> findUserWithMaxSum();

    @Query("SELECT DISTINCT u.id, u.email, u.user_status\n" +
            "FROM users u\n" +
            "JOIN orders o ON u.id = o.user_id\n" +
            "WHERE EXTRACT(YEAR FROM o.created_at) = 2010\n" +
            "  AND o.order_status = 'paid';")
    List<User> findPaidOrders();
}
