package com.kuch.Fooddelivery.service.impl;

import com.kuch.Fooddelivery.dto.OrderDto;
import com.kuch.Fooddelivery.dto.OrderDtoResponse;
import com.kuch.Fooddelivery.entity.Order;
import com.kuch.Fooddelivery.entity.User;
import com.kuch.Fooddelivery.repository.OrderRepository;
import com.kuch.Fooddelivery.repository.UserRepository;
import com.kuch.Fooddelivery.service.OrderService;
import com.kuch.Fooddelivery.service.exception.OrderNotFoundException;
import com.kuch.Fooddelivery.service.exception.UserNotFoundException;
import com.kuch.Fooddelivery.utils.mappers.InventoryFoodMapper;
import com.kuch.Fooddelivery.utils.mappers.OrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    public OrderDtoResponse getOrder(int orderId) {
        log.info("Finding Order by id {}", orderId);
        Order order = orderRepository.findById(orderId)
                .orElseThrow(OrderNotFoundException::new);
        log.info("Order with id {} is found", orderId);
        return OrderMapper.INSTANCE.asOrderDtoResponse(order);
    }

    @Override
    public List<OrderDtoResponse> getUserOrders(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        return user.getOrder().stream().map(OrderMapper.INSTANCE::asOrderDtoResponse).toList();
    }

    @Override
    public OrderDtoResponse updateOrder(OrderDto orderDto, int orderId){
        log.info("Update Order with id {}", orderId);
        Order order = orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);

        orderRepository.save(order);

        log.info("Order with {} id updated", orderId);

        return OrderMapper.INSTANCE.asOrderDtoResponse(order);
    }



    @Override
    @Transactional
    public OrderDtoResponse createOrder(OrderDto orderDto, int userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        Order order = OrderMapper.INSTANCE.asOrder(orderDto);
        List<Order> orders = user.getOrder();

        orders.add(order);
        user.setOrder(orders);
        order.setUser(user);
        order.setInventory(user.getInventory());
        order.setTotal(user.getInventory().getTotal());

        order = orderRepository.save(order);
        userRepository.save(user);

        log.info("Order with id: {} created", order.getId());

        OrderDtoResponse orderDtoResponse = OrderMapper.INSTANCE.asOrderDtoResponse(order);
        orderDtoResponse.setFoodSet(user.getInventory().getUsersFood()
                .stream()
                .map(InventoryFoodMapper.INSTANCE::asInventoryFoodDto)
                .collect(Collectors.toSet()));


        return orderDtoResponse;
    }

    @Override
    public void deleteOrder(int orderId) {
        log.info("Delete Order with id: {}", orderId);
        Order order = orderRepository.findById(orderId)
                .orElseThrow(OrderNotFoundException::new);

        orderRepository.delete(order);
        log.info("Order with {} id successfully deleted", orderId);
    }
}
