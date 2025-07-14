package com.thanawat.hotelx.HotelBook.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "books")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id @GeneratedValue
    private Long bookingId;
    private Long customerId;
    private Long hotelId;
    private Long roomId;
    private int peopleNum;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate checkInDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate checkOutDate;
}
