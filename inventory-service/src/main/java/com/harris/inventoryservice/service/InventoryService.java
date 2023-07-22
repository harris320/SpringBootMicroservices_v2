package com.harris.inventoryservice.service;

import com.harris.inventoryservice.dto.InventoryResponse;
import com.harris.inventoryservice.model.Inventory;
import com.harris.inventoryservice.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String> skuCode){
        List<Inventory> isInStock = inventoryRepository.findBySkuCodeIn(skuCode);
        return isInStock.stream()
                .map(inventory -> mapToInventoryResponse(inventory))
                .collect(Collectors.toList());
    }

    private InventoryResponse mapToInventoryResponse(Inventory inventory) {
        return InventoryResponse.builder()
                .skuCode(inventory.getSkuCode())
                .isInStock(inventory.getQuantity() > 0)
                .build();
    }
}
