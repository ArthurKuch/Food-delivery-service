package com.kuch.Fooddelivery.repository;

import com.kuch.Fooddelivery.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
}
