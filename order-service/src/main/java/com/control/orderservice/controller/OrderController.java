package com.control.orderservice.controller;

import com.control.orderservice.dto.order.OrderRequestDTO;
import com.control.orderservice.service.order.OrderService;
import com.control.orderservice.util.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
@Tag(name = "Order Management", description = "Operations related to user management")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    @Operation(summary = "List all orders", description = "Returns a list of all registered orders")
    public ResponseEntity<Object> findAllOrders() {
        return ResponseUtil.generateResponse("List loaded successfully!", HttpStatus.OK, orderService.findAll());
    }

    @PostMapping
    @Operation(summary = "Create a new order", description = "Add a new order to the system")
    public ResponseEntity<Object> createOrder(@RequestBody OrderRequestDTO dto) {
        return ResponseUtil.generateResponse("Order created successfully!", HttpStatus.CREATED, orderService.create(dto));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find for a order by ID", description = "Retrieves information about an existing order in the system based on the provided ID.")
    public ResponseEntity<Object> findOrder(@PathVariable Long id) {
        return ResponseUtil.generateResponse("Order found successfully!", HttpStatus.OK, orderService.findById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing order", description = "Modifies the information of an existing order in the system based on the provided ID.")
    public ResponseEntity<Object> updateOrder(@PathVariable Long id, @RequestBody OrderRequestDTO dto) {
        return ResponseUtil.generateResponse("Order updated successfully!", HttpStatus.OK, orderService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletes an order by ID", description = "Removes an existing order from the system based on the provided ID.")
    public ResponseEntity<Object> deleteOrder(@PathVariable Long id) {
        return ResponseUtil.generateResponse("Order deleted successfully!", HttpStatus.OK, orderService.delete(id));
    }
}