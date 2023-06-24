package com.kuch.Fooddelivery.api.service;

import com.kuch.Fooddelivery.api.util.FoodDataTestUtil;
import com.kuch.Fooddelivery.dto.FoodDto;
import com.kuch.Fooddelivery.entity.Food;
import com.kuch.Fooddelivery.repository.FoodRepostiory;
import com.kuch.Fooddelivery.service.exception.FoodNotFoundException;
import com.kuch.Fooddelivery.service.impl.FoodServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

/**
 * @author Artur Kuch
 */

@ExtendWith(MockitoExtension.class)
public class FoodServiceTests {

    @Mock
    FoodRepostiory foodRepostiory;

    @InjectMocks
    FoodServiceImpl foodService;

    @Test
    public void FoodService_CreateFood_ReturnsFoodDto(){
        Food food = FoodDataTestUtil.createFood();
        FoodDto foodDto = FoodDataTestUtil.createFoodDto();

        when(foodRepostiory.save(Mockito.any(Food.class))).thenReturn(food);

        FoodDto saved = foodService.createFood(foodDto);

        assertThat(saved).isNotNull();
    }

    @Test
    public void FoodService_GetFoodById_ReturnsFoodDto(){

        Food food = FoodDataTestUtil.createFood();

        when(foodRepostiory.findById(1)).thenReturn(Optional.ofNullable(food));

        FoodDto existed = foodService.getFood(1);

        assertThat(existed).isNotNull();
    }

    @Test
    public void FoodService_GetFoodById_ThrowsFoodNotFound(){
        when(foodRepostiory.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(FoodNotFoundException.class, () -> foodService.getFood(1));
    }

    @Test
    public void FoodService_updateFood_ReturnsFoodDto(){
        Food food = FoodDataTestUtil.createFood();
        FoodDto foodDto = FoodDataTestUtil.createFoodDto();

        when(foodRepostiory.findById(1)).thenReturn(Optional.ofNullable(food));
        when(foodRepostiory.save(Mockito.any(Food.class))).thenReturn(food);

        FoodDto updated = foodService.updateFood(1, foodDto);

        assertThat(updated).isNotNull();
    }

    @Test
    public void FoodService_DeleteById_ReturnsVoid(){
        Food food = FoodDataTestUtil.createFood();

        when(foodRepostiory.findById(1)).thenReturn(Optional.ofNullable(food));

        assertAll(() -> foodService.deleteFood(1));
    }

}
