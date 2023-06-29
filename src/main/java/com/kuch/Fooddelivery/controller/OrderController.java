package com.kuch.Fooddelivery.controller;

import com.kuch.Fooddelivery.api.OrderApi;
import com.kuch.Fooddelivery.controller.assembler.OrderAssembler;
import com.kuch.Fooddelivery.controller.model.OrderModel;
import com.kuch.Fooddelivery.dto.OrderDto;
import com.kuch.Fooddelivery.dto.OrderDtoResponse;
import com.kuch.Fooddelivery.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Artur Kuch
 */

@RestController
@RequiredArgsConstructor
public class OrderController implements OrderApi {

    private final OrderAssembler orderAssembler;
    private final OrderService orderService;


    @Override
    public OrderModel placeOrder(OrderDto orderDto, int userId) {
        OrderDtoResponse orderDtoResponse = orderService.createOrder(orderDto, userId);

        return orderAssembler.toModel(orderDtoResponse);
    }

    @Override
    public List<OrderModel> getUserOrders(int userId) {
        List<OrderModel> orderModels = orderService.getUserOrders(userId).stream()
                .map(orderAssembler::toModel).toList();

        return orderModels;
    }

    @Override
    public OrderModel getOrder(int orderId) {
        OrderDtoResponse response = orderService.getOrder(orderId);

        return orderAssembler.toModel(response);
    }

    @Override
    public OrderModel updateOrder(OrderDto orderDto, int orderId) {
        OrderDtoResponse response = orderService.updateOrder(orderDto, orderId);

        return orderAssembler.toModel(response);
    }

    @Override
    public ResponseEntity<Void> deleteOrder(int orderId) {
        orderService.deleteOrder(orderId);

        return ResponseEntity.noContent().build();
    }
}
