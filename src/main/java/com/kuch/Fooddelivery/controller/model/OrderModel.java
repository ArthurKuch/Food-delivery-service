package com.kuch.Fooddelivery.controller.model;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.kuch.Fooddelivery.dto.OrderDtoResponse;
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
public class OrderModel extends RepresentationModel<OrderModel> {

    @JsonUnwrapped
    private OrderDtoResponse orderDtoResponse;

}
