package com.control.userservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException ex) {
        HashMap<String, Object> map = new HashMap<>();

        map.put("msg", ex.getMessage());
        map.put("obj", null);
        map.put("status", HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handleBadRequestException(BadRequestException ex) {
        HashMap<String, Object> map = new HashMap<>();

        map.put("msg", ex.getMessage());
        map.put("obj", null);
        map.put("status", HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }
}
