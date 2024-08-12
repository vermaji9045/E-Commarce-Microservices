package com.product.Dto;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDto {
    private String Id;
    private String name;
    private String description;
    private BigDecimal price;
}
