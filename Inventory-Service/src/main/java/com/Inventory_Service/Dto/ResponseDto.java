package com.Inventory_Service.Dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class ResponseDto {


    private String statusCode;

    private String statusMsg;
}
