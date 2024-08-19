package com.control.orderservice.dto.order;

import com.control.orderservice.dto.user.UserResponseDTO;
import com.control.orderservice.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDTO {
    private Long id;
    private Long userId;
    private String product;

    public static OrderResponseDTO fromUser(Order order) {
        return new OrderResponseDTO(
                order.getId(),
                order.getUserId(),
                order.getProduct()
        );
    }
}
