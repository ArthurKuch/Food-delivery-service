package com.kuch.Fooddelivery.controller.model;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.kuch.Fooddelivery.dto.FoodDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

/**
 * @author Artur Kuch
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class FoodModel extends RepresentationModel<FoodModel> {

    @JsonUnwrapped
    private FoodDto foodDto;

}
