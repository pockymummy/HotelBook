package com.thanawat.hotelx.HotelBook.repository;

import com.thanawat.hotelx.HotelBook.data.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel,Long> {
}
