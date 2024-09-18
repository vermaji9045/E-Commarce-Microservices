package com.orderService.Service;

import com.orderService.Dto.InventoryDto;
import com.orderService.Dto.OrderDto;
import com.orderService.Exception.ResourceNotFoundException;
import com.orderService.Mapper.Maaper;
import com.orderService.Repository.OrderRepository;
import com.orderService.Service.Client.InventoryFeignClient;
import com.orderService.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@Slf4j

public class OrderService {


@Autowired
    private OrderRepository orderRepository;



    private InventoryFeignClient inventoryFeignClient;

    public OrderService(InventoryFeignClient inventoryFeignClient) {
        this.inventoryFeignClient = inventoryFeignClient;
    }
// private WebClient webclient;



    @Transactional
    public void pleaseOrder(OrderDto orderDto)
    {
        Order order=new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

       Order data= Maaper.mapToOrder(orderDto,order);


        InventoryDto inventoryDto =inventoryFeignClient.isInStock(order.getSkuCode());


        if (inventoryDto != null && inventoryDto.getQuantity() >= orderDto.getQuantity()) {
            // Decrement inventory quantity
            inventoryDto.setQuantity(inventoryDto.getQuantity() - orderDto.getQuantity());
            inventoryFeignClient.updateInventory(inventoryDto);

            // Save order
            orderRepository.save(data);
        } else {
            throw new ResourceNotFoundException(orderDto.getSkuCode(),"SkuCode",orderDto.getSkuCode());
        }



    }

}

