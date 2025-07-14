package com.thanawat.hotelx.HotelBook.repository;

import com.thanawat.hotelx.HotelBook.data.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {
}
