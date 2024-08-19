package com.control.userservice.controller;

import com.control.userservice.dto.UserRequestDTO;
import com.control.userservice.service.UserService;
import com.control.userservice.util.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Tag(name = "User Management", description = "Operations related to user management")
public class UserController {

    private final UserService userService;

    @GetMapping
    @Operation(summary = "List all users", description = "Returns a list of all registered users")
    public ResponseEntity<Object> findAllUsers() {
        return ResponseUtil.generateResponse("List loaded successfully!", HttpStatus.OK, userService.findAll());
    }

    @PostMapping
    @Operation(summary = "Create a new user", description = "Add a new user to the system")
    public ResponseEntity<Object> createUser(@RequestBody UserRequestDTO dto) {
        return ResponseUtil.generateResponse("User created successfully!", HttpStatus.CREATED, userService.create(dto));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find for a user by ID", description = "Retrieves the information of an existing user in the system based on the provided ID.")
    public ResponseEntity<Object> findUser(@PathVariable Long id) {
        return ResponseUtil.generateResponse("User found successfully!", HttpStatus.OK, userService.findById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing user", description = "Modifies the information of an existing user in the system based on the provided ID.")
    public ResponseEntity<Object> updateUser(@PathVariable Long id, @RequestBody UserRequestDTO dto) {
        return ResponseUtil.generateResponse("User updated successfully!", HttpStatus.OK, userService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletes a user by ID", description = "Removes an existing user from the system based on the provided ID.")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
        return ResponseUtil.generateResponse("User deleted successfully!", HttpStatus.OK, userService.delete(id));
    }
}