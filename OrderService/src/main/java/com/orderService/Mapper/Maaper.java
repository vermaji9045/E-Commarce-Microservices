package com.orderService.Mapper;

import com.orderService.Dto.OrderDto;
import com.orderService.model.Order;

public class Maaper {



    public static OrderDto mapToOrderDto(Order order,OrderDto orderDto)
    {
        orderDto.setQuantity(order.getQuantity());
        orderDto.setPrice(order.getPrice());
        orderDto.setSkuCode(order.getSkuCode());

        return orderDto;
    }

    public static Order mapToOrder(OrderDto orderDto,Order order)
    {
        order.setPrice(orderDto.getPrice());
        order.setQuantity(orderDto.getQuantity());
        order.setSkuCode(orderDto.getSkuCode());
        return order;
    }
}
