package com.kuch.Fooddelivery.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kuch.Fooddelivery.entity.enumeration.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Artur Kuch
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDtoResponse {


    private int orderId;

    private String street;

    private int streetNumber;

    private Set<FoodDto> foodSet =  new HashSet<>();

    private double total;

    private LocalDateTime createdAt;

    private OrderStatus status;


}
