package com.kuch.Fooddelivery.repository;

import com.kuch.Fooddelivery.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Artur Kuch
 */
public interface FoodRepostiory extends JpaRepository<Food, Integer> {
}
