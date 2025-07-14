package com.thanawat.hotelx.HotelBook.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Availability {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate start;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate end;
    private int numPeople;
    private Long locationId;
    private Long hotelId;
    private Long roomId;
    private Double price;
}
