package com.kuch.Fooddelivery.api;

import com.kuch.Fooddelivery.controller.model.InventoryModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
                    @ApiImplicitParam(name = "inventoryId", paramType = "path", required = true, value = "User id"),
                    @ApiImplicitParam(name = "foodId", paramType = "path", required = true, value = "User id")
            })
    @ApiOperation("Add food to inventory")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("{inventoryId}/add-foods/{foodId}")
    InventoryModel addFoodToInventory(@PathVariable int inventoryId, @PathVariable int foodId);

}
