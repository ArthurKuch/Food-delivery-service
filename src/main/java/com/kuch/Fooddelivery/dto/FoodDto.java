package com.kuch.Fooddelivery.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

/**
 * @author Artur Kuch
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FoodDto {

    private int id;

    private String name;

    private String description;

    private double price;

}
