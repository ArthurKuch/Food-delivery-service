package com.kuch.Fooddelivery.service;


import com.kuch.Fooddelivery.dto.FoodDto;

import java.util.List;

public interface FoodService {

    FoodDto getFood(int foodId);

    List<FoodDto> getAllFoods();

    FoodDto createFood(FoodDto foodDto);

    FoodDto updateFood(int foodId, FoodDto foodDto);

    void deleteFood(int foodId);


}
