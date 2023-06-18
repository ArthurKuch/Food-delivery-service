package com.kuch.Fooddelivery.service.impl;

import com.kuch.Fooddelivery.dto.InventoryDto;
import com.kuch.Fooddelivery.entity.Food;
import com.kuch.Fooddelivery.entity.Inventory;
import com.kuch.Fooddelivery.repository.FoodRepostiory;
import com.kuch.Fooddelivery.repository.InventoryRepository;
import com.kuch.Fooddelivery.repository.UserRepository;
import com.kuch.Fooddelivery.service.InventoryService;
import com.kuch.Fooddelivery.service.exception.FoodNotFoundException;
import com.kuch.Fooddelivery.service.exception.InventoryNotFoundException;
import com.kuch.Fooddelivery.utils.mappers.FoodMapper;
import com.kuch.Fooddelivery.utils.mappers.InventoryMapper;
import fr.xebia.extras.selma.Selma;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Artur Kuch
 */

@Slf4j
@RequiredArgsConstructor
@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final UserRepository userRepository;
    private final FoodRepostiory foodRepostiory;

    private final InventoryMapper inventoryMapper = Selma.getMapper(InventoryMapper.class);
    private final FoodMapper mapper = Selma.builder(FoodMapper.class).build();

    @Override
    public InventoryDto getInventory(int userId) {
        log.info("Finding user inventory by id {}", userId);
        Inventory existed = userRepository.findById(userId)
                .orElseThrow(InventoryNotFoundException::new).getInventory();
        log.info("Inventory with id {} is found ", existed.getInventoryId());

        return inventoryMapper.asInventoryDto(existed);
    }

    @Override
    public InventoryDto addFood(int inventoryId, int foodId) {
        Inventory inventory = getInventoryByInventoryId(inventoryId);

        Food food = foodRepostiory.findById(foodId)
                        .orElseThrow(FoodNotFoundException::new);

        inventory.getUsersFood().add(food);

        inventoryRepository.save(inventory);


        return inventoryMapper.asInventoryDto(inventory);
    }

    @Override
    public InventoryDto removeFood(int inventoryId, int foodId) {

        Inventory inventory = getInventoryByInventoryId(inventoryId);

        Food food = foodRepostiory.findById(foodId)
                .orElseThrow(FoodNotFoundException::new);

        inventory.getUsersFood().remove(food);

        inventoryRepository.save(inventory);

        return inventoryMapper.asInventoryDto(inventory);
    }

    @Override
    public InventoryDto clearInventory(int inventoryId) {
        Inventory inventory = getInventoryByInventoryId(inventoryId);

        inventory.getUsersFood().clear();

        inventoryRepository.save(inventory);


        return inventoryMapper.asInventoryDto(inventory);
    }

    private Inventory getInventoryByInventoryId(int inventoryId) {
        return inventoryRepository.findById(inventoryId)
                .orElseThrow(InventoryNotFoundException::new);
    }
}
