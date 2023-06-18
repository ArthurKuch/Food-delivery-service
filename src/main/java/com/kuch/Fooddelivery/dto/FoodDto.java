package com.kuch.Fooddelivery.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kuch.Fooddelivery.dto.group.OnCreate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

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

    @Min(1)
    private double price;

    @Min(1)
    @Null(message = "Quantity should be absent in request", groups = OnCreate.class)
    private int quantity;

//    private boolean inStock;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
