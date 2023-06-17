package com.kuch.Fooddelivery.controller.model;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.kuch.Fooddelivery.dto.InventoryDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class InventoryModel extends RepresentationModel<InventoryModel> {


    @JsonUnwrapped
    private InventoryDto inventory;


}
