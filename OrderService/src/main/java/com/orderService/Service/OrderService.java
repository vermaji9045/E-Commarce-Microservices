package com.orderService.Service;

import com.orderService.Dto.InventoryDto;
import com.orderService.Dto.OrderDto;
import com.orderService.Dto.OrderLineItemsDto;
import com.orderService.Repository.OrderRepository;
import com.orderService.model.Order;
import com.orderService.model.OrderLineItems;
import io.micrometer.observation.ObservationRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class OrderService {


    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ObservationRegistry observationRegistry;
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;
@Autowired
    private WebClient webclient;


    public void pleaseOrder(OrderDto orderDto)
    {
        Order order=new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderDto.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();


             order.setOrderLineItems(orderLineItems);



           List<String> skuCode=order.getOrderLineItems()
                  .stream()
                   .map(OrderLineItems::getSkuCode)
                     .toList();


        InventoryDto[] inventoryDto= webclient.get().uri("http://localhost:8082/api/inventory/fetch"
                        ,uriBuilder -> uriBuilder.queryParam("skuCode",skuCode).build())
                           .retrieve()
                                   .bodyToMono(InventoryDto[].class).block();


      Boolean res=  Arrays.stream(inventoryDto)
                .allMatch(InventoryDto::isInStock);
        if(res)
        {
            orderRepository.save(order);
        }else
        {
            throw new IllegalArgumentException("Product is not in stock, please try again later");
        }



              //Call Inventory Service and place order if product is in stock

//        Observation observation=Observation.createNotStarted("inventory-service-lookup", this.observationRegistry);
//         observation.lowCardinalityKeyValue("call","inventory-service");
//
//         return observation.observe(()->{
//
//             InventoryResponse[] inventoryResponses=webclientBuilder.build().get()
//                     .uri("http://inventory-service/api/inventory", uriBuilder -> uriBuilder.queryParam("skuCode",skuCode).build())
//                     .retrieve()
//                     .bodyToMono(InventoryResponse[].class)
//                     .block();
//
//             boolean allProductInStock= Arrays.stream(inventoryResponses)
//                     .allMatch(InventoryResponse::isInStock);
//
//             if (allProductInStock)
//             {
//                 orderRepository.save(order);
//
//                 //applicationEventPublisher.publishEvent();
//                 return "Order Placed";
//             }
//             else {
//
//                 throw new IllegalArgumentException("Product is not in stock, please try again later");
//             }
//         });


    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto)
    {
        OrderLineItems orderLineItems=new OrderLineItems();

        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItems.getSkuCode());

        System.out.println(orderLineItemsDto.getPrice());
        return orderLineItems;
    }
}
