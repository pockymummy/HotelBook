package com.thanawat.hotelx.HotelBook.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.thanawat.hotelx.HotelBook.data.Book;
import com.thanawat.hotelx.HotelBook.exception.PrimaryKeyExistInCreateRequest;
import com.thanawat.hotelx.HotelBook.service.BookService;
import com.thanawat.hotelx.HotelBook.util.DateConfiguredObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @MockitoBean
    private BookService bookService;

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = DateConfiguredObjectMapper.getDateConfiguredObjectMapper();

    private Book defaultReservation;

    private Book savedReservation;

    @BeforeEach
    void setup() {
        defaultReservation = Book.builder()
                .hotelId(1L)
                .checkInDate(LocalDate.of(2024,10,05))
                .checkOutDate(LocalDate.of(2024,10,07))
                .customerId(1L)
                .peopleNum(2)
                .roomId(1L)
                .build();

        savedReservation =                        Book.builder()
                .hotelId(1L)
                .checkInDate(LocalDate.of(2024,10,05))
                .checkOutDate(LocalDate.of(2024,10,07))
                .customerId(1L)
                .peopleNum(2)
                .roomId(1L)
                .bookingId(1L)
                .build();
    }

    @Test
    void givenAvailability_whenMakeReservation_returnReservation() throws Exception {
        when(bookService.makeReservation(defaultReservation)).thenReturn(savedReservation);

        mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(defaultReservation)))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    Book actual = objectMapper.readValue(result.getResponse().getContentAsString(), Book.class);
                    assertEquals(savedReservation, actual);
                });
    }

    @Test
    void givenPrimaryKeyInAvailability_whenMakeReservation_returnBadRequest() throws Exception {
        doThrow(new PrimaryKeyExistInCreateRequest())
                .when(bookService)
                .makeReservation(defaultReservation);

        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(defaultReservation)))
                .andExpect(status().isBadRequest())
                .andExpect(result -> {
                    assertEquals(PrimaryKeyExistInCreateRequest.class.getName(), result.getResponse().getContentAsString());
                });
    }

    @Test
    void givenExistingBooking_whenGetReservationById_returnBooking() throws Exception {
        when(bookService.getReservationById(savedReservation.getBookingId())).thenReturn(Optional.of(savedReservation));

        mockMvc.perform(get("/books/" + savedReservation.getBookingId()))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    Book actual = objectMapper.readValue(result.getResponse().getContentAsString(), Book.class);
                    assertEquals(savedReservation.getBookingId(), actual.getBookingId());
                    assertEquals(savedReservation.getHotelId(), actual.getHotelId());
                });
    }

    @Test
    void givenNonExistingBooking_whenGetReservationById_returnNotFound() throws Exception {
        when(bookService.getReservationById(savedReservation.getBookingId())).thenReturn(Optional.empty());

        mockMvc.perform(get("/books/" + savedReservation.getBookingId()))
                .andExpect(status().isNotFound());
        }
}

