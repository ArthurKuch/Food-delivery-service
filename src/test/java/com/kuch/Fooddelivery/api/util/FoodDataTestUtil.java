package com.kuch.Fooddelivery.api.util;

import com.kuch.Fooddelivery.dto.FoodDto;
import com.kuch.Fooddelivery.entity.Food;
import com.kuch.Fooddelivery.utils.mappers.FoodMapper;
import fr.xebia.extras.selma.Selma;

/**
 * @author Artur Kuch
 */
public class FoodDataTestUtil {

    private static final FoodMapper foodMapper = Selma.getMapper(FoodMapper.class);


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
        return foodMapper.asFoodDto(createFood());
    }

}
