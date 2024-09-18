package com.orderService.controller;

import com.orderService.Dto.OrderDto;
import com.orderService.Dto.ResponseDto;
import com.orderService.OrderConstants.OrderConstants;
import com.orderService.Service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping(path="/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("order")
    public ResponseEntity<ResponseDto> pleaseOrder(@RequestBody OrderDto orderDto)
    {
        orderService.pleaseOrder(orderDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(OrderConstants.STATUS_200,OrderConstants.MESSAGE_200));
    }


}
