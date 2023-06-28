package com.kuch.Fooddelivery.service;

import com.kuch.Fooddelivery.dto.OrderDto;
import com.kuch.Fooddelivery.dto.OrderDtoResponse;

/**
 * @author Artur Kuch
 */

public interface OrderService {

    OrderDtoResponse createOrder(OrderDto orderDto, int userId);

}
