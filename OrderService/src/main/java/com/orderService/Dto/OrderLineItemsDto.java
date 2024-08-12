package com.orderService.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class OrderLineItemsDto {

    private Long Id;
    private String skuCode;
    private BigDecimal price;
    private int quantity;
}
