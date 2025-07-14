package com.thanawat.hotelx.HotelBook.controller;

import com.thanawat.hotelx.HotelBook.data.Book;
import com.thanawat.hotelx.HotelBook.exception.PrimaryKeyExistInCreateRequest;
import com.thanawat.hotelx.HotelBook.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<Book> makeReservation(@RequestBody Book book) throws PrimaryKeyExistInCreateRequest {
        return ResponseEntity.ok(bookService.makeReservation(book));
    }
}
