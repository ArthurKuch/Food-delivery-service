package com.kuch.Fooddelivery.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kuch.Fooddelivery.dto.group.OnCreate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * @author Artur Kuch
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FoodDto {

    private int id;

    @NotBlank(message = "Food name shouldn't be empty", groups = OnCreate.class)
    private String name;

    @NotBlank(message = "Food description shouldn't be empty", groups = OnCreate.class)
    private String description;

    @DecimalMin(value = "1.0", message = "Price should not be less than 1.0", inclusive = false)
    private double price;

    @Min(1)
    private int quantity;

}
