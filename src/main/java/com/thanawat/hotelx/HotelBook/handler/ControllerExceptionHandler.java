package com.thanawat.hotelx.HotelBook.handler;

import com.thanawat.hotelx.HotelBook.exception.PrimaryKeyExistInCreateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(PrimaryKeyExistInCreateRequest.class)
    public ResponseEntity<String> handle(PrimaryKeyExistInCreateRequest e) {
        return ResponseEntity.badRequest().body(PrimaryKeyExistInCreateRequest.class.getName());
    }
}
