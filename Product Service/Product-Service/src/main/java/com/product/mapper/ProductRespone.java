package com.product.mapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRespone
{


    private String Id;
    private String name;
    private String description;
    private BigDecimal price;
}
