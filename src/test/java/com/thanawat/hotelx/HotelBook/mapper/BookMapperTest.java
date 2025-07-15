package com.thanawat.hotelx.HotelBook.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thanawat.hotelx.HotelBook.data.Book;
import com.thanawat.hotelx.HotelBook.data.Hotel;
import com.thanawat.hotelx.HotelBook.dto.BookDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import util.DateConfiguredObjectMapper;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class BookMapperTest {

    @InjectMocks
    private BookMapper bookMapper;

    private Book defaultBook;
    private BookDTO defaultBookDto;

    private final ObjectMapper objectMapper = DateConfiguredObjectMapper.getDateConfiguredObjectMapper();

    @BeforeEach
    void setup() {
        defaultBook = Book.builder()
                .bookingId(1L)
                .roomId(1L)
                .peopleNum(1)
                .checkInDate(LocalDate.of(2000,01,01))
                .checkOutDate(LocalDate.of(2000,01,05))
                .hotel(Hotel.builder().id(1L).build())
                .customerId(1L)
                .build();

        defaultBookDto = BookDTO.builder()
                .bookingId(1L)
                .roomId(1L)
                .peopleNum(1)
                .checkInDate(LocalDate.of(2000,01,01))
                .checkOutDate(LocalDate.of(2000,01,05))
                .hotelId(1L)
                .customerId(1L)
                .build();
    }

    @Test
    void givenBook_whenMapToDto_returnDTO() throws Exception{
        BookDTO actual = bookMapper.mapBookToDTO(defaultBook);

        assertEquals(objectMapper.writeValueAsString(defaultBookDto), objectMapper.writeValueAsString(actual));
    }

    @Test
    void givenDTO_whenMapToBook_returnBook() throws Exception{
        Book actual = bookMapper.mapDTOToBook(defaultBookDto);

        assertEquals(objectMapper.writeValueAsString(defaultBook), objectMapper.writeValueAsString(actual));
    }



}
