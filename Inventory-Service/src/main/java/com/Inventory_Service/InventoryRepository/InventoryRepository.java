package com.Inventory_Service.InventoryRepository;

import com.Inventory_Service.Model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Long> {


    List<Inventory> findByskuCodeIn(List<String> skuCode);
}
