package com.control.orderservice.model;

import com.control.orderservice.dto.order.OrderRequestDTO;
import com.control.orderservice.dto.user.UserResponseDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long userId;
    private String product;

    public Order(Long userId, String product) {
        this.userId = userId;
        this.product = product;
    }

    public static Order fromRequestDTO(OrderRequestDTO orderRequestDTO) {
        return new Order(
                orderRequestDTO.getUserId(),
                orderRequestDTO.getProduct()
        );
    }
}
