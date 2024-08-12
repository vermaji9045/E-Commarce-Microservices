package com.Inventory_Service.Controller;


import com.Inventory_Service.Dto.AddInventoryDto;
import com.Inventory_Service.Dto.InventoryDto;
import com.Inventory_Service.Service.InventoryService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/inventory")
@Slf4j
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/fetch")
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryDto> isInStock(@RequestParam List<String> skuCode)
    {
        log.info("Recived inventory check request for skuCode: {}",skuCode);
        return inventoryService.isInStock(skuCode);
    }


    @PostMapping("/addInventory")
    @ResponseStatus(HttpStatus.CREATED)
    public void addInventory(@RequestBody AddInventoryDto inventoryDto)
    {
        inventoryService.addInventory(inventoryDto);

    }
}
