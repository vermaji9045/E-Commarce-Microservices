package com.orderService.controller;

import com.orderService.Dto.OrderDto;
import com.orderService.Service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("api/order")
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("order")
    @ResponseStatus(HttpStatus.CREATED)
    public String pleaseOrder(@RequestBody OrderDto orderDto)
    {
        orderService.pleaseOrder(orderDto);
        return "Order Pleased Successfully";
    }
}
