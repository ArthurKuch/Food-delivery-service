package com.kuch.Fooddelivery.service.impl;

import com.kuch.Fooddelivery.dto.OrderDto;
import com.kuch.Fooddelivery.dto.OrderDtoResponse;
import com.kuch.Fooddelivery.entity.Order;
import com.kuch.Fooddelivery.entity.User;
import com.kuch.Fooddelivery.repository.OrderRepository;
import com.kuch.Fooddelivery.repository.UserRepository;
import com.kuch.Fooddelivery.service.OrderService;
import com.kuch.Fooddelivery.service.exception.UserNotFoundException;
import com.kuch.Fooddelivery.utils.mappers.FoodMapper;
import com.kuch.Fooddelivery.utils.mappers.OrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 * @author Artur Kuch
 */

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Override
    public OrderDtoResponse createOrder(OrderDto orderDto, int userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        Order order = OrderMapper.INSTANCE.asOrder(orderDto);
        order.setInventory(user.getInventory());
        order.setTotal(user.getInventory().getTotal());
        order = orderRepository.save(order);

        log.info("Order with id: {} created", order.getId());

        OrderDtoResponse orderDtoResponse = OrderMapper.INSTANCE.asOrderDtoResponse(order);
        orderDtoResponse.setFoodSet(user.getInventory().getUsersFood()
                .stream()
                .map(FoodMapper.INSTANCE::asFoodDto)
                .collect(Collectors.toSet()));

        return orderDtoResponse;
    }
}
