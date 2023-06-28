package com.kuch.Fooddelivery.controller.assembler;

import com.kuch.Fooddelivery.controller.OrderController;
import com.kuch.Fooddelivery.controller.model.OrderModel;
import com.kuch.Fooddelivery.dto.OrderDtoResponse;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

/**
 * @author Artur Kuch
 */

@Component
public class OrderAssembler extends RepresentationModelAssemblerSupport<OrderDtoResponse, OrderModel> {

    public OrderAssembler(){
        super(OrderController.class, OrderModel.class);
    }

    @Override
    public OrderModel toModel(OrderDtoResponse entity) {
        OrderModel orderModel = new OrderModel(entity);


        return orderModel;
    }
}
