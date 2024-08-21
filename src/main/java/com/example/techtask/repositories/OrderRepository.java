package com.example.techtask.repositories;

import com.example.techtask.model.Order;
import com.example.techtask.model.enumiration.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query("SELECT o FROM Order o " +
            "WHERE o.quantity > :minQuantity " +
            "ORDER BY o.createdAt DESC " +
            "LIMIT 1")
    Optional<Order> findLatestOrderWithMultipleItems(@Param("minQuantity") int minQuantity);

    @Query("SELECT o " +
            "FROM Order o " +
            "WHERE o.userId IN (SELECT u.id FROM User u WHERE u.userStatus = :#{#userStatus?.toString()}) " +
            "ORDER BY o.createdAt")
    List<Order> findOrdersWithActiveUsersSorted(@Param("userStatus") UserStatus userStatus);
}
