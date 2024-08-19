package com.control.orderservice.service.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "userService", url = "http://localhost:8081")
public interface UserServiceClient {

    @GetMapping("/user/{id}")
    void getUserById(@PathVariable("id") Long id);
}
