package com.control.userservice.service;

import com.control.userservice.dto.UserRequestDTO;
import com.control.userservice.dto.UserResponseDTO;
import com.control.userservice.exception.BadRequestException;
import com.control.userservice.exception.NotFoundException;
import com.control.userservice.model.User;
import com.control.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserResponseDTO> findAll() {
        return userRepository.findAll().stream().map(UserResponseDTO::fromUser).collect(Collectors.toList());
    }

    public UserResponseDTO create(UserRequestDTO dto) {
        try {
            User user = userRepository.save(User.fromRequestDTO(dto));
            return UserResponseDTO.fromUser(user);
        } catch (Exception e) {
            throw new BadRequestException("Unable to create user!");
        }
    }

    public UserResponseDTO findById(Long id) {
        Optional<User> userDB = userRepository.findById(id);

        if (!userDB.isPresent()) {
            throw new NotFoundException("User not found!");
        }

        return UserResponseDTO.fromUser(userDB.get());
    }

    public UserResponseDTO update(Long id, UserRequestDTO dto) {
        Optional<User> userDB = userRepository.findById(id);

        if (!userDB.isPresent()) {
            throw new NotFoundException("User not found!");
        }

        try {
            User userAtualizado = userRepository.save(new User(id, dto.getName(), dto.getAge()));
            return UserResponseDTO.fromUser(userAtualizado);
        } catch (Exception e) {
            throw new BadRequestException("Unable to update user!");
        }
    }

    public UserResponseDTO delete(Long id) {
        try {
            userRepository.deleteById(id);
            return null;
        } catch (Exception e) {
            throw new BadRequestException("Unable to delete user!");
        }
    }

    @KafkaListener(topics = "user-topico", groupId = "user-group")
    public void processOrder(String message) {
        System.out.println(message);
    }
}
