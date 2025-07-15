package com.thanawat.hotelx.HotelBook.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "hotels")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {
    @Id 
    private Long id;
    private String name;
}
