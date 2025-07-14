package com.thanawat.hotelx.HotelBook;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.thanawat.hotelx.HotelBook.data.Availability;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class HotelBookApplication {

	public static void main(String[] args) {
        SpringApplication.run(HotelBookApplication.class, args);
	}

}
