package com.example.techtask.repositories;

import com.example.techtask.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM User u " +
            "JOIN u.orders o " +
            "WHERE YEAR(o.createdAt) = :dateOfCreation " +
            "  AND o.orderStatus = 'DELIVERED' " +
            "GROUP BY u.id, u.email, u.userStatus " +
            "ORDER BY SUM(o.price * o.quantity) DESC " +
            "LIMIT 1")
    Optional<User> findUserWithMaxSumByYear(@Param("dateOfCreation") int dateOfCreation);

    @Query( "SELECT DISTINCT u " +
            "FROM User u " +
            "JOIN u.orders o " +
            "WHERE YEAR(o.createdAt) = :dateOfCreation " +
            "  AND o.orderStatus = 'PAID'")
    List<User> findPaidOrdersByYear(@Param("dateOfCreation") int dateOfCreation);
}