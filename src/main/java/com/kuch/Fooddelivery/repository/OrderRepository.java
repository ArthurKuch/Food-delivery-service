package com.kuch.Fooddelivery.repository;

import com.kuch.Fooddelivery.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Artur Kuch
 */

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
