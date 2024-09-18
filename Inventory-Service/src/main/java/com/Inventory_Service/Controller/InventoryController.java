package com.Inventory_Service.Controller;


import com.Inventory_Service.Dto.AddInventoryDto;
import com.Inventory_Service.Dto.InventoryDto;
import com.Inventory_Service.Dto.ResponseDto;
import com.Inventory_Service.InventoryConstants.InventoryConstants;
import com.Inventory_Service.Service.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Slf4j
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/fetch")
    public ResponseEntity<InventoryDto> isInStock(@RequestParam String skuCode)
    {

        InventoryDto inventoryDto=inventoryService.isInStock(skuCode);
       return ResponseEntity
               .status(HttpStatus.OK)
               .body(inventoryDto);
    }


    @PostMapping("/addInventory")
    @ResponseStatus(HttpStatus.CREATED)
    public void addInventory(@RequestBody AddInventoryDto inventoryDto)
    {
        inventoryService.addInventory(inventoryDto);

    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto>update(@RequestBody InventoryDto inventoryDto)
    {
        boolean isupdate= inventoryService.updateInventory(inventoryDto);
        if (isupdate)
        {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(InventoryConstants.STATUS_200, InventoryConstants.MESSAGE_200));
        }
        else {

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDto(InventoryConstants.STATUS_417, InventoryConstants.MESSAGE_417_UPDATE));
        }
    }

}
