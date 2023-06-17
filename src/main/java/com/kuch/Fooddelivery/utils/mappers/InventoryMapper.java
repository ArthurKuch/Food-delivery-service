package com.kuch.Fooddelivery.utils.mappers;

import com.kuch.Fooddelivery.dto.InventoryDto;
import com.kuch.Fooddelivery.entity.Inventory;
import fr.xebia.extras.selma.Field;
import fr.xebia.extras.selma.Mapper;
import fr.xebia.extras.selma.Selma;

import static fr.xebia.extras.selma.CollectionMappingStrategy.ALLOW_GETTER;

/**
 * @author Artur Kuch
 */
@Mapper( withCustom = FoodMapperSelmaGeneratedClass.class,
        withCollectionStrategy = ALLOW_GETTER,
        withCustomFields = {
        @Field({"Inventory.inventoryId","id"})
}, withIgnoreFields = "Inventory.user")
public interface InventoryMapper {

    InventoryDto asInventoryDto(Inventory inventory);

    Inventory asInventory(InventoryDto inventoryDto);

}
