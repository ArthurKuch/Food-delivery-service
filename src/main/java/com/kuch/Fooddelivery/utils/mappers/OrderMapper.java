package com.kuch.Fooddelivery.utils.mappers;

import com.kuch.Fooddelivery.dto.OrderDto;
import com.kuch.Fooddelivery.dto.OrderDtoResponse;
import com.kuch.Fooddelivery.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author Artur Kuch
 */

@Mapper(uses = {InventoryMapper.class})

public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(source = "id", target = "orderId")
    OrderDto asOrderDto(Order order);

    @Mapping(source = "id", target = "orderId")
    OrderDtoResponse asOrderDtoResponse(Order order);

    @Mapping(source = "orderId", target = "id")
    Order asOrder(OrderDto orderDto);

}
