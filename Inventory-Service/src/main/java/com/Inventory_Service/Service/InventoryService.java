package com.Inventory_Service.Service;

import com.Inventory_Service.Dto.AddInventoryDto;
import com.Inventory_Service.Dto.InventoryDto;
import com.Inventory_Service.Exception.ResourceNotFoundException;
import com.Inventory_Service.InventoryRepository.InventoryRepository;
import com.Inventory_Service.Model.Inventory;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public InventoryDto  isInStock(String skuCode)
    {

        Inventory inventory = inventoryRepository.findBySkuCode(skuCode)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "skuCode", skuCode));

        InventoryDto inventoryDto=this.mapToInventoryDto(inventory,new InventoryDto());
        return inventoryDto;
    }



    public void addInventory(AddInventoryDto inventoryDto)
    {

        Optional<Inventory> inventory= inventoryRepository.findBySkuCode(inventoryDto.getSkuCode());

        if (inventory.isPresent())
        {
            Inventory inventory1=inventory.get();
            inventory1.setQuantity(inventoryDto.getQuantity()+inventory1.getQuantity());
            inventoryRepository.save(inventory1);
        }else {

            Inventory inventory1=new Inventory();
            inventory1.setSkuCode(inventoryDto.getSkuCode());
            inventory1.setQuantity(inventoryDto.getQuantity());

            inventoryRepository.save(inventory1);
        }

    }

    public InventoryDto mapToInventoryDto(Inventory inventory,InventoryDto inventoryDto)
    {
        inventoryDto.setQuantity(inventory.getQuantity());
        inventoryDto.setSkuCode(inventory.getSkuCode());

        return inventoryDto;
    }


    public boolean updateInventory(InventoryDto inventoryDto) {
        Optional<Inventory> inventoryOptional = inventoryRepository.findBySkuCode(inventoryDto.getSkuCode());

        if (inventoryOptional.isPresent()) {
            Inventory inventory = inventoryOptional.get(); // Get the actual Inventory object

            if (inventory.getQuantity() >= inventoryDto.getQuantity()) {

                inventory.setQuantity(inventoryDto.getQuantity());
                inventoryRepository.save(inventory);
                return true;
            } else {

                return false; // Not enough quantity
            }
        }

            return false;



    }


    private Inventory mapToInventory(InventoryDto inventoryDto, Inventory inventory) {
        if (inventory != null) {
            inventory.setQuantity(inventoryDto.getQuantity());
            inventory.setSkuCode(inventoryDto.getSkuCode());
        }

        return inventory;
    }

}
