package com.Inventory_Service.Service;

import com.Inventory_Service.Dto.AddInventoryDto;
import com.Inventory_Service.Dto.InventoryDto;
import com.Inventory_Service.InventoryRepository.InventoryRepository;
import com.Inventory_Service.Model.Inventory;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    @SneakyThrows
    public List<InventoryDto> isInStock(List<String> skuCode)
    {
        log.info("Checking Inventory");

        return inventoryRepository.findByskuCodeIn(skuCode)
                .stream()
                .map(inventory-> InventoryDto.builder()
                        .skuCode(inventory.getSkuCode())
                        .isInStock(inventory.getQuantity()>0)
                        .build()).toList();

    }

    public void addInventory(AddInventoryDto inventoryDto)
    {

        Inventory inventory=new Inventory();

        inventory.setSkuCode(inventoryDto.getSkuCode());
        inventory.setQuantity(inventoryDto.getQuantity());

        inventoryRepository.save(inventory);

    }
}
