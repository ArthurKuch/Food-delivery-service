package com.kuch.Fooddelivery.service;

import com.kuch.Fooddelivery.dto.OrderDto;
import com.kuch.Fooddelivery.dto.OrderDtoResponse;

import java.util.List;

/**
 * @author Artur Kuch
 */

public interface OrderService {

    OrderDtoResponse createOrder(OrderDto orderDto, int userId);

    OrderDtoResponse getOrder(int orderId);

    List<OrderDtoResponse> getUserOrders(int userId);

    OrderDtoResponse updateOrder(OrderDto orderDto, int orderId);

    void deleteOrder(int orderId);

}
