package com.thanawat.hotelx.HotelBook.mapper;

import com.thanawat.hotelx.HotelBook.data.Book;
import com.thanawat.hotelx.HotelBook.data.Hotel;
import com.thanawat.hotelx.HotelBook.dto.BookDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookMapper {
    public BookDTO mapBookToDTO(Book book)  {
        return new BookDTO(book.getBookingId()
                , book.getCustomerId()
                , book.getRoomId()
                , book.getHotel().getId()
                , book.getPeopleNum()
                , book.getCheckInDate()
                , book.getCheckOutDate());
    }

    public Book mapDTOToBook(BookDTO dto) {
        return new Book(dto.getBookingId()
                , dto.getCustomerId()
                , Hotel.builder().id(dto.getBookingId()).build()
                , dto.getRoomId()
                , dto.getPeopleNum()
                , dto.getCheckInDate()
                , dto.getCheckOutDate());
    }
}
