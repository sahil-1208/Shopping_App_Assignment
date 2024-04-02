package com.learning.controller;

import com.learning.dto.InventoryDto;
import com.learning.config.ProductConfig;
import com.learning.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/product")
public class InventoryController {

    private final ProductConfig productConfig;

    private final OrderRepository orderRepository;

    @GetMapping("/inventory")
    public ResponseEntity<InventoryDto> getInventory() {
        Integer totalOrderedQuantity = orderRepository.getTotalOrderedQuantity();
        int orderedQuantity;
        orderedQuantity = Objects.requireNonNullElse(totalOrderedQuantity, 0);
        int availableQuantity = productConfig.getAvailable() - orderedQuantity;
        InventoryDto inventoryDto = new InventoryDto(orderedQuantity, productConfig.getPrice(), availableQuantity);
        return ResponseEntity.ok(inventoryDto);
    }
}
