package com.kuch.Fooddelivery.api;

import com.kuch.Fooddelivery.controller.model.FoodModel;
import com.kuch.Fooddelivery.dto.FoodDto;
import com.kuch.Fooddelivery.dto.group.OnCreate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Artur Kuch
 */
@Api(tags = "Foods management API")
@RequestMapping("/api/v1/foods")
public interface FoodApi {

    @ApiImplicitParams({
            @ApiImplicitParam(name = "foodId", paramType = "path", required = true, value = "Food id")
    })
    @ApiOperation("Get food by id")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{foodId}")
    FoodModel getFood(@PathVariable int foodId);

    @ApiOperation("Get all foods")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    List<FoodModel> getFoods();

    @ApiOperation("Create food")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    FoodModel createFood(@Validated(OnCreate.class) @RequestBody FoodDto foodDto);


    @ApiImplicitParams({
            @ApiImplicitParam(name = "foodId", paramType = "path", required = true, value = "Food id")
    })
    @ApiOperation("Update food")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{foodId}")
    FoodModel updateFood(@PathVariable int foodId, @RequestBody FoodDto foodDto);

    @ApiImplicitParams({
            @ApiImplicitParam(name = "foodId", paramType = "path", required = true, value = "Food id")
    })
    @ApiOperation("Delete food")
    @DeleteMapping("/{foodId}")
    ResponseEntity<Void> deleteFood(@PathVariable int foodId);

}
