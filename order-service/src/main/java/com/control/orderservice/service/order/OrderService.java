package com.control.orderservice.service.order;

import com.control.orderservice.dto.order.OrderRequestDTO;
import com.control.orderservice.dto.order.OrderResponseDTO;
import com.control.orderservice.exception.BadRequestException;
import com.control.orderservice.exception.NotFoundException;
import com.control.orderservice.model.Order;
import com.control.orderservice.repository.OrderRepository;
import com.control.orderservice.service.user.UserServiceClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    
    private final KafkaTemplate<String, String> kafkaTemplate;

    private final UserServiceClient userServiceClient;

    public List<OrderResponseDTO> findAll() {
        return orderRepository.findAll().stream().map(OrderResponseDTO::fromUser).collect(Collectors.toList());
    }

    public OrderResponseDTO create(OrderRequestDTO dto) {
        try {
            userServiceClient.getUserById(dto.getUserId());

            Order order = orderRepository.save(Order.fromRequestDTO(dto));

            kafkaTemplate.send("user-topico", "Order created for user ID: " + dto.getUserId());

            return OrderResponseDTO.fromUser(order);

        } catch (FeignException.NotFound e) {
            throw new NotFoundException("User not found!");
        } catch (Exception e) {
            throw new BadRequestException("Unable to create order!");
        }
    }

    public OrderResponseDTO findById(Long id) {
        Optional<Order> userDB = orderRepository.findById(id);

        if (!userDB.isPresent()) {
            throw new NotFoundException("Order not found!");
        }

        return OrderResponseDTO.fromUser(userDB.get());
    }

    public OrderResponseDTO update(Long id, OrderRequestDTO dto) {
        Optional<Order> userDB = orderRepository.findById(id);

        if (!userDB.isPresent()) {
            throw new NotFoundException("User not found!");
        }

        try {
            Order orderUpdated = orderRepository.save(new Order(id, dto.getUserId(), dto.getProduct()));
            return OrderResponseDTO.fromUser(orderUpdated);
        } catch (Exception e) {
            throw new BadRequestException("Unable to update order!");
        }
    }

    public OrderResponseDTO delete(Long id) {
        try {
            orderRepository.deleteById(id);
            return null;
        } catch (Exception e) {
            throw new BadRequestException("Unable to delete order!");
        }
    }
}
