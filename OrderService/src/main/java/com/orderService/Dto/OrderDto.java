package com.orderService.Dto;

import com.orderService.model.OrderLineItems;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private List<OrderLineItemsDto>orderLineItemsDtoList;
}
