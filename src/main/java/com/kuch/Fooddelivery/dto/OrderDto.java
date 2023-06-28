package com.kuch.Fooddelivery.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kuch.Fooddelivery.entity.enumeration.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Artur Kuch
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDto implements Serializable {

    private int orderId;

    private String street;

    private int streetNumber;

    private double total;

    private OrderStatus status;

}
