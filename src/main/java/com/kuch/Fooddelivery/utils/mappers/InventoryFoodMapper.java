package com.kuch.Fooddelivery.utils.mappers;

import com.kuch.Fooddelivery.dto.InventoryFoodDto;
import com.kuch.Fooddelivery.entity.Food;
import com.kuch.Fooddelivery.entity.InventoryFood;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author Artur Kuch
 */

@Mapper(uses = {FoodMapper.class})
public interface InventoryFoodMapper {


    InventoryFoodMapper INSTANCE = Mappers.getMapper(InventoryFoodMapper.class);

//    @Mapping(source = "foodId", target = "id")
//    InventoryFoodDto asInventoryFoodDto(Food food);


    InventoryFoodDto asInventoryFoodDto(InventoryFood inventoryFood);

    @Mapping(source = "foodId", target = "id")
    InventoryFood asInventoryFood(Food food);

}
