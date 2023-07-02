package com.kuch.Fooddelivery.api;

import com.kuch.Fooddelivery.controller.model.InventoryModel;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author Artur Kuch
 */
@Api(tags = "Inventory management API")
@RequestMapping("/api/v1/inventory-items")
public interface InventoryApi {


    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "userId", paramType = "path", required = true, value = "User id")
            })
    @ApiOperation("Get user inventory")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{userId}")
    InventoryModel getUserInventory(@PathVariable int userId);

    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "inventoryId", paramType = "path", required = true, value = "Inventory id"),
                    @ApiImplicitParam(name = "foodId", paramType = "path", required = true, value = "Food id"),
                    @ApiImplicitParam(name = "quantity", paramType = "query", required = true, example = "5", value = "Food quantity")
            })
    @ApiOperation("Add food to inventory")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("{inventoryId}/add-foods/{foodId}")
    InventoryModel addFoodToInventory(@PathVariable int inventoryId, @PathVariable int foodId, @RequestParam(name = "quantity") int quantity);


    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "inventoryId", paramType = "path", required = true, value = "Inventory id"),
                    @ApiImplicitParam(name = "foodId", paramType = "path", required = true, value = "Food id")
            })
    @ApiOperation("Remove food from inventory")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("{inventoryId}/remove-foods/{foodId}")
    InventoryModel removeFoodFromInventory(@PathVariable int inventoryId, @PathVariable int foodId);


    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "inventoryId", paramType = "path", required = true, value = "Inventory id"),
            })
    @ApiOperation("Clear inventory")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("{inventoryId}/clear-all")
    InventoryModel clearInventory(@PathVariable int inventoryId);



}
