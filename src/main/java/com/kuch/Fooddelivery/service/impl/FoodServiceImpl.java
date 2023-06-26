package com.kuch.Fooddelivery.service.impl;

import com.kuch.Fooddelivery.dto.FoodDto;
import com.kuch.Fooddelivery.entity.Food;
import com.kuch.Fooddelivery.repository.FoodRepostiory;
import com.kuch.Fooddelivery.service.FoodService;
import com.kuch.Fooddelivery.service.exception.FoodNotFoundException;
import com.kuch.Fooddelivery.utils.mappers.FoodMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Service
public class FoodServiceImpl implements FoodService {

    private final FoodRepostiory foodRepostiory;

//    private final FoodMapper foodMapper = FoodMapper.INSTANCE;

    @Override
    public FoodDto getFood(int foodId) {
        log.info("Finding food by id {}", foodId);

        Food food = foodRepostiory.findById(foodId)
                .orElseThrow(FoodNotFoundException::new);

        log.info("Food with id {} is found", foodId);

//        return foodMapper.asFoodDto(food);
        return FoodMapper.INSTANCE.asFoodDto(food);
    }

    @Override
    public List<FoodDto> getAllFoods() {
        log.info("Getting all foods");

        List<Food> foods = foodRepostiory.findAll();

        List<FoodDto> foodDtos = foods.stream().map(FoodMapper.INSTANCE::asFoodDto).toList();

        return foodDtos;
    }

    @Override
    public FoodDto createFood(FoodDto foodDto) {
        Food food = FoodMapper.INSTANCE.asFood(foodDto);

        food = foodRepostiory.save(food);
        log.info("Food with id {} created", food.getFoodId());
        log.info("Food: {}", FoodMapper.INSTANCE.asFoodDto(food));
        foodDto.setId(food.getFoodId());

        return foodDto;
    }

    @Override
    public FoodDto updateFood(int foodId, FoodDto foodDto) {
        log.info("Update Food with id {}", foodId);
        Food existedFood = foodRepostiory.findById(foodId)
                .orElseThrow(FoodNotFoundException::new);


        updateFoodWithNonNullFields(existedFood, foodDto);

        Food upFood = foodRepostiory.save(existedFood);

        log.info("Food with {} id updated", foodId);
        log.info("Food: {}", FoodMapper.INSTANCE.asFoodDto(upFood));
        return FoodMapper.INSTANCE.asFoodDto(upFood);
    }

    @Override
    public void deleteFood(int foodId) {
        log.info("Delete Food with id {}", foodId);
        Food food = foodRepostiory.findById(foodId)
                .orElseThrow(FoodNotFoundException::new);

        foodRepostiory.delete(food);

        log.info("Food with {} id deleted", foodId);
    }


    private void updateFoodWithNonNullFields(Food food, FoodDto foodDto){

        String name = foodDto.getName();
        String desc = foodDto.getDescription();
        double price = foodDto.getPrice();


        if(Objects.nonNull(name))
            food.setName(name);
        if(Objects.nonNull(desc))
            food.setDescription(desc);
        if(!Double.isNaN(price) || !Double.isInfinite(price))
            food.setPrice(price);

    }

}
