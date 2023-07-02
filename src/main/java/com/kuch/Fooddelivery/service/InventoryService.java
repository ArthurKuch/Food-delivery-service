package com.kuch.Fooddelivery.service;

import com.kuch.Fooddelivery.dto.InventoryDto;

/**
 * @author Artur Kuch
 */
public interface InventoryService {

    InventoryDto getInventory(int userId);

    InventoryDto addFood(int inventoryId, int foodId, int quantity);

    InventoryDto removeFood(int inventoryId,  int foodId);

    InventoryDto clearInventory(int inventoryId);

}
