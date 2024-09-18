package com.orderService.Service.Client;

import com.orderService.Dto.InventoryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "inventory")
public interface InventoryFeignClient {
    @GetMapping("/api/fetch")
    public InventoryDto isInStock(@RequestParam String skuCode);
    @PutMapping("/api/update")
    void updateInventory(@RequestBody InventoryDto inventoryDto);


}
