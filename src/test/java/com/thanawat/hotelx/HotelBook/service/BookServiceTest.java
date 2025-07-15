package com.thanawat.hotelx.HotelBook.service;

import com.thanawat.hotelx.HotelBook.data.Book;
import com.thanawat.hotelx.HotelBook.data.Hotel;
import com.thanawat.hotelx.HotelBook.exception.PrimaryKeyExistInCreateRequest;
import com.thanawat.hotelx.HotelBook.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    private Book defaultBook;
    private Book bookWithId;

    @BeforeEach
    void setup() {
        bookWithId = Book.builder()
                .bookingId(1L)
                .hotel(Hotel.builder().id(1L).build())
                .checkInDate(LocalDate.of(2024,11,05))
                .checkOutDate(LocalDate.of(2024,11,07))
                .peopleNum(2)
                .roomId(1L)
                .build();
        defaultBook = Book.builder()
                .hotel(Hotel.builder().id(1L).build())
                .checkInDate(LocalDate.of(2024,11,05))
                .checkOutDate(LocalDate.of(2024,11,07))
                .peopleNum(2)
                .roomId(1L)
                .build();
        defaultBook.setBookingId(null);
    }

    @Test
    void givenBookingWithNoId_whenMakeReservation_returnReservation() throws Exception {
        when(bookRepository.save(defaultBook)).thenReturn(defaultBook);

        Book actual = bookService.makeReservation(defaultBook);
        assertEquals(defaultBook.getBookingId(),actual.getBookingId());
        assertEquals(defaultBook.getCheckInDate(), actual.getCheckInDate());
        assertEquals(defaultBook.getCheckOutDate(), actual.getCheckOutDate());
        assertEquals(defaultBook.getHotel().getId(), actual.getHotel().getId());
        assertEquals(defaultBook.getCustomerId(), actual.getCustomerId());
        assertEquals(defaultBook.getPeopleNum(), actual.getPeopleNum());
        assertEquals(defaultBook.getRoomId(), actual.getRoomId());
    }

    @Test
    void givenBookingWithId_whenMakeReservation_returnEmpty() {
        assertThrows(PrimaryKeyExistInCreateRequest.class, () -> bookService.makeReservation(bookWithId));
    }

    @Test
    void givenExistingBook_whenGetReservationById_returnBooking() {
        when(bookRepository.findById(bookWithId.getBookingId())).thenReturn(Optional.of(bookWithId));

        Optional<Book> actual = bookService.getReservationById(bookWithId.getBookingId());

        assertTrue(actual.isPresent());
        assertEquals(bookWithId.getBookingId(), actual.get().getBookingId());
        assertEquals(bookWithId.getRoomId(), actual.get().getRoomId());
        assertEquals(bookWithId.getPeopleNum(), actual.get().getPeopleNum());
        assertEquals(bookWithId.getCheckOutDate(),actual.get().getCheckOutDate());
        assertEquals(bookWithId.getCheckInDate(), actual.get().getCheckInDate());
        assertEquals(bookWithId.getHotel().getId(), actual.get().getHotel().getId());
    }

    @Test
    void givenNonExistingBook_whenGetReservationById_returnEmpty() {
        when(bookRepository.findById(any())).thenReturn(Optional.empty());

        Optional<Book> actual = bookService.getReservationById(bookWithId.getBookingId());

        assertFalse(actual.isPresent());
    }
    
}
