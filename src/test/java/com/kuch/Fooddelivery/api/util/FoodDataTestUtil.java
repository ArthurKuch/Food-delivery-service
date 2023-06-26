package com.kuch.Fooddelivery.api.util;

import com.kuch.Fooddelivery.dto.FoodDto;
import com.kuch.Fooddelivery.entity.Food;
import com.kuch.Fooddelivery.utils.mappers.FoodMapper;

/**
 * @author Artur Kuch
 */
public class FoodDataTestUtil {


    public static Food createFood(){

        return Food.builder()
                .foodId(1)
                .name("Food")
                .description("Desc")
                .price(2.0)
                .quantity(1)
                .build();

    }

    public static FoodDto createFoodDto(){
        return FoodMapper.INSTANCE.asFoodDto(createFood());
    }

}
