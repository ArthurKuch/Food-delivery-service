package com.kuch.Fooddelivery.controller.assembler;

import com.kuch.Fooddelivery.controller.InventoryController;
import com.kuch.Fooddelivery.controller.model.InventoryModel;
import com.kuch.Fooddelivery.dto.InventoryDto;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author Artur Kuch
 */
@Component
public class InventoryAssembler  extends RepresentationModelAssemblerSupport<InventoryDto, InventoryModel> {

    public InventoryAssembler() {
        super(InventoryController.class, InventoryModel.class);
    }

    @Override
    public InventoryModel toModel(InventoryDto entity) {
        InventoryModel inventoryModel = new InventoryModel(entity);


        Link addFood = linkTo(methodOn(InventoryController.class).addFoodToInventory(entity.getId(), 1, 5)).withRel("Add food");

        inventoryModel.add(addFood);

        return inventoryModel;
    }
}
