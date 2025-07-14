package com.thanawat.hotelx.HotelBook.service;

import com.thanawat.hotelx.HotelBook.data.Availability;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class AvailabilityServiceTest {
    @InjectMocks
    private AvailabilityService availabilityService;

    private Availability defaultAvailability;
    private List<Availability> defaultAvailabilityList;

    @BeforeEach
    public void setup() {
        Availability availabilityRequest = Availability
                .builder()
                .start(LocalDate.parse("2025-12-20", DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .end(LocalDate.parse("2025-12-25", DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .locationId(1L)
                .numPeople(2)
                .build();

        Availability availabilityResponse1 = Availability
                .builder()
                .start(LocalDate.parse("2025-12-20", DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .end(LocalDate.parse("2025-12-25", DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .locationId(1L)
                .numPeople(2)
                .hotelId(1L)
                .price(100.0)
                .build();

        Availability availabilityResponse2 = Availability
                .builder()
                .start(LocalDate.parse("2025-12-20", DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .end(LocalDate.parse("2025-12-25", DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .locationId(1L)
                .numPeople(2)
                .hotelId(2L)
                .price(100.0)
                .build();

        defaultAvailability = availabilityRequest;
        List<Availability> availabilityList = new ArrayList<Availability>();
        availabilityList.add(availabilityResponse1);
        availabilityList.add(availabilityResponse2);
    }


    @Test
    void givenAvailabilityExisted_whenGetAvailability_ReturnAvailabilityList() {
        List<Availability> actual = availabilityService.getAvailabilityByLocationDate(defaultAvailability);

        assertEquals(2, actual.size());
    }
}
