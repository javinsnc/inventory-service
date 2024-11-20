package com.javinsnc.microservices.inventory_service.controller;

import com.javinsnc.microservices.inventory_service.service.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
public class InventoryServiceController {

    private final InventoryService inventoryService;

    public InventoryServiceController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String isInStock(@RequestParam String skuCode, @RequestParam Integer quantity) {
        boolean isInStock = inventoryService.isInStock(skuCode, quantity);
        return "Is in stock: " + isInStock;
    }

}
