package com.kuch.Fooddelivery.controller;

import com.kuch.Fooddelivery.api.FoodApi;
import com.kuch.Fooddelivery.controller.assembler.FoodAssembler;
import com.kuch.Fooddelivery.controller.model.FoodModel;
import com.kuch.Fooddelivery.dto.FoodDto;
import com.kuch.Fooddelivery.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Artur Kuch
 */

@RestController
@RequiredArgsConstructor
public class FoodController implements FoodApi {

    private final FoodService foodService;
    private final FoodAssembler foodAssembler;


    @Override
    public FoodModel getFood(int foodId) {
        FoodDto foodDto = foodService.getFood(foodId);

        return foodAssembler.toModel(foodDto);
    }

    @Override
    public List<FoodModel> getFoods() {
        List<FoodDto> foodDtos = foodService.getAllFoods();

        return foodDtos.stream().map(foodAssembler::toModel).toList();
    }

    @Override
    public FoodModel createFood(FoodDto foodDto) {
        FoodDto outFoodDto = foodService.createFood(foodDto);

        return foodAssembler.toModel(outFoodDto);
    }

    @Override
    public FoodModel updateFood(int foodId, FoodDto foodDto) {
        FoodDto updated = foodService.updateFood(foodId, foodDto);

        return foodAssembler.toModel(updated);
    }

    @Override
    public ResponseEntity<Void> deleteFood(int foodId) {
        foodService.deleteFood(foodId);

        return ResponseEntity.noContent().build();
    }
}
