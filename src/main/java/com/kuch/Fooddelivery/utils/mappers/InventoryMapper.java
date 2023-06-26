package com.kuch.Fooddelivery.utils.mappers;

import com.kuch.Fooddelivery.dto.InventoryDto;
import com.kuch.Fooddelivery.entity.Inventory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


/**
 * @author Artur Kuch
 */
@Mapper(uses = {FoodMapper.class})
public interface InventoryMapper {

    InventoryMapper INSTANCE = Mappers.getMapper(InventoryMapper.class);

    @Mapping(source = "inventoryId", target = "id")
    @Mapping(target = "Inventory.user", ignore = true)
    InventoryDto asInventoryDto(Inventory inventory);

    @Mapping(source = "id", target = "inventoryId")
    Inventory asInventory(InventoryDto inventoryDto);

}
