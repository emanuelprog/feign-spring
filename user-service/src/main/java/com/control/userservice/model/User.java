package com.control.userservice.model;

import com.control.userservice.dto.UserRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public static User fromRequestDTO(UserRequestDTO userRequestDTO) {
        return new User(
                userRequestDTO.getName(),
                userRequestDTO.getAge()
        );
    }
}
