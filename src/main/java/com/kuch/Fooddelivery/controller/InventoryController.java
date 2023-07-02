package com.kuch.Fooddelivery.controller;

import com.kuch.Fooddelivery.api.InventoryApi;
import com.kuch.Fooddelivery.controller.assembler.InventoryAssembler;
import com.kuch.Fooddelivery.controller.model.InventoryModel;
import com.kuch.Fooddelivery.dto.InventoryDto;
import com.kuch.Fooddelivery.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Artur Kuch
 */
@RestController
@RequiredArgsConstructor
public class InventoryController implements InventoryApi {

    private final InventoryService inventoryService;
    private final InventoryAssembler inventoryAssembler;


    @Override
    public InventoryModel getUserInventory(int userId) {
        InventoryDto inventoryDto =  inventoryService.getInventory(userId);

        return inventoryAssembler.toModel(inventoryDto);
    }

    @Override
    public InventoryModel addFoodToInventory(int inventoryId, int foodId, int quantity) {
        InventoryDto inventoryDto = inventoryService.addFood(inventoryId, foodId, quantity);

        return inventoryAssembler.toModel(inventoryDto);
    }

    @Override
    public InventoryModel removeFoodFromInventory(int inventoryId, int foodId) {
        InventoryDto inventoryDto = inventoryService.removeFood(inventoryId, foodId);

        return inventoryAssembler.toModel(inventoryDto);
    }

    @Override
    public InventoryModel clearInventory(int inventoryId) {
        InventoryDto inventoryDto = inventoryService.clearInventory(inventoryId);

        return inventoryAssembler.toModel(inventoryDto);
    }
}
