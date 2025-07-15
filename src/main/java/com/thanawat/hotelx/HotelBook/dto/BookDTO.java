package com.thanawat.hotelx.HotelBook.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDTO {
    private Long bookingId;
    private Long customerId;
    private Long roomId;
    private Long hotelId;
    private int peopleNum;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate checkInDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate checkOutDate;
}
