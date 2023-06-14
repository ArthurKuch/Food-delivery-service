package com.kuch.Fooddelivery.utils.mappers;


import com.kuch.Fooddelivery.dto.FoodDto;
import com.kuch.Fooddelivery.entity.Food;
import fr.xebia.extras.selma.Mapper;

@Mapper
public interface FoodMapper {


    FoodDto asFoodDto(Food food);

    Food asFood(FoodDto foodDto);


}
