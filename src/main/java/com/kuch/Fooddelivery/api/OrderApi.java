package com.kuch.Fooddelivery.api;

import com.kuch.Fooddelivery.controller.model.OrderModel;
import com.kuch.Fooddelivery.dto.OrderDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Artur Kuch
 */

@Api(tags = "Order management API")
@RequestMapping("/api/v1/order")
public interface OrderApi {

    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "userId", paramType = "path", required = true, value = "User id")
            })
    @ApiOperation("Place order")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{userId}")
    OrderModel placeOrder(@RequestBody OrderDto orderDto, @PathVariable int userId);


    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "orderId", paramType = "path", required = true, value = "Order id")
            })
    @ApiOperation("Get order")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{orderId}")
    OrderModel getOrder(@PathVariable int orderId);


    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "orderId", paramType = "path", required = true, value = "Order id")
            })
    @ApiOperation("Update order")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{orderId}")
    OrderModel updateOrder(@RequestBody OrderDto orderDto, @PathVariable int orderId);


    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", paramType = "path", required = true, value = "Order id")
    })
    @ApiOperation("Delete order")
    @DeleteMapping("/{orderId}")
    ResponseEntity<Void> deleteOrder(@PathVariable int orderId);
}
