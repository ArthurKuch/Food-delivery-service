package com.kuch.Fooddelivery.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Artur Kuch
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InventoryFoodDto {

    private int id;

    private double price;

    private int quantity;

//    private FoodDto foodDto;

}
