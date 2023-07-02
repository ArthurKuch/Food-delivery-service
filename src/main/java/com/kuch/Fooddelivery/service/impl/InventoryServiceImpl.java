package com.kuch.Fooddelivery.service.impl;

import com.kuch.Fooddelivery.dto.InventoryDto;
import com.kuch.Fooddelivery.entity.Food;
import com.kuch.Fooddelivery.entity.Inventory;
import com.kuch.Fooddelivery.entity.InventoryFood;
import com.kuch.Fooddelivery.repository.FoodRepostiory;
import com.kuch.Fooddelivery.repository.InventoryFoodRepository;
import com.kuch.Fooddelivery.repository.InventoryRepository;
import com.kuch.Fooddelivery.repository.UserRepository;
import com.kuch.Fooddelivery.service.InventoryService;
import com.kuch.Fooddelivery.service.exception.FoodNotFoundException;
import com.kuch.Fooddelivery.service.exception.InventoryNotFoundException;
import com.kuch.Fooddelivery.utils.mappers.InventoryFoodMapper;
import com.kuch.Fooddelivery.utils.mappers.InventoryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Artur Kuch
 */

@Slf4j
@RequiredArgsConstructor
@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final InventoryFoodRepository inventoryFoodRepository;
    private final UserRepository userRepository;
    private final FoodRepostiory foodRepostiory;


    @Override
    public InventoryDto getInventory(int userId) {
        log.info("Finding user inventory by id {}", userId);
        Inventory existed = userRepository.findById(userId)
                .orElseThrow(InventoryNotFoundException::new).getInventory();
        log.info("Inventory with id {} is found ", existed.getInventoryId());

        return InventoryMapper.INSTANCE.asInventoryDto(existed);
    }

    @Override
    @Transactional
    public InventoryDto addFood(int inventoryId, int foodId, int quantity) {
        Inventory inventory = getInventoryByInventoryId(inventoryId);

        Food food = foodRepostiory.findById(foodId)
                        .orElseThrow(FoodNotFoundException::new);

        InventoryFood inventoryFood = InventoryFoodMapper.INSTANCE.asInventoryFood(food);
        double total = inventory.getTotal() + (quantity * inventoryFood.getPrice());

        inventory.setTotal(total);
        inventoryFood.setQuantity(quantity);
        inventory.getUsersFood().add(inventoryFood);

        inventoryFoodRepository.save(inventoryFood);
        inventoryRepository.save(inventory);

        return InventoryMapper.INSTANCE.asInventoryDto(inventory);
    }

    @Override
    @Transactional
    public InventoryDto removeFood(int inventoryId, int foodId) {

        Inventory inventory = getInventoryByInventoryId(inventoryId);

        Food food = foodRepostiory.findById(foodId)
                .orElseThrow(FoodNotFoundException::new);

        InventoryFood inventoryFood = InventoryFoodMapper.INSTANCE.asInventoryFood(food);

        double total = inventory.getTotal() + (inventoryFood.getQuantity() * inventoryFood.getPrice());
        inventory.setTotal(total);

        inventory.getUsersFood().remove(inventoryFood);

        inventoryFoodRepository.delete(inventoryFood);
        inventoryRepository.save(inventory);

        return InventoryMapper.INSTANCE.asInventoryDto(inventory);
    }

    @Override
    public InventoryDto clearInventory(int inventoryId) {
        Inventory inventory = getInventoryByInventoryId(inventoryId);

        inventory.getUsersFood().clear();

        inventoryRepository.save(inventory);


        return InventoryMapper.INSTANCE.asInventoryDto(inventory);
    }

    private Inventory getInventoryByInventoryId(int inventoryId) {
        return inventoryRepository.findById(inventoryId)
                .orElseThrow(InventoryNotFoundException::new);
    }
}
