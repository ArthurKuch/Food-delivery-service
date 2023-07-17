package com.kuch.Fooddelivery.repository;

import com.kuch.Fooddelivery.entity.Food;
import com.kuch.Fooddelivery.entity.InventoryFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Artur Kuch
 */
@Repository
public interface InventoryFoodRepository extends JpaRepository<InventoryFood, Integer> {

    InventoryFood findInventoryFoodByFood(Food food);

}
