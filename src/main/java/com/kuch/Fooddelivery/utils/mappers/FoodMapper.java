package com.kuch.Fooddelivery.utils.mappers;


import com.kuch.Fooddelivery.dto.FoodDto;
import com.kuch.Fooddelivery.entity.Food;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper
public interface FoodMapper {

    FoodMapper INSTANCE = Mappers.getMapper(FoodMapper.class);


    @Mapping(source = "foodId", target = "id")
    FoodDto asFoodDto(Food food);

    @Mapping(source = "id", target = "foodId")
    Food asFood(FoodDto foodDto);

}
