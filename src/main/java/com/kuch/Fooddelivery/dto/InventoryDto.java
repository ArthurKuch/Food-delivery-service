package com.kuch.Fooddelivery.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

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
public class InventoryDto {

    private List<FoodDto> usersFoods;

}
