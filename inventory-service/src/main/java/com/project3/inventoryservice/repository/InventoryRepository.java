package com.project3.inventoryservice.repository;

import com.project3.inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
public interface InventoryRepository {
    List<Inventory> findBySkuCodeIn(List<String> skuCode);
}
