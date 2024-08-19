package com.control.orderservice.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseWrapperDTO {
    private String msg;
    private UserResponseDTO obj;
    private int status;
}
