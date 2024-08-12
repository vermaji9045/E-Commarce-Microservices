package com.Inventory_Service.Dto;


import lombok.Data;

@Data
public class AddInventoryDto {


    private String skuCode;
    private Integer quantity;
}
