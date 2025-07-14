package com.thanawat.hotelx.HotelBook.service;

import com.thanawat.hotelx.HotelBook.data.Book;
import com.thanawat.hotelx.HotelBook.exception.PrimaryKeyExistInCreateRequest;
import com.thanawat.hotelx.HotelBook.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    
    public Book makeReservation(Book book) throws PrimaryKeyExistInCreateRequest{
        if (book.getBookingId() != null) {
            throw new PrimaryKeyExistInCreateRequest();
        } else {
            return bookRepository.save(book);
        }
    }
}
