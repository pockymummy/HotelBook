package com.thanawat.hotelx.HotelBook.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.thanawat.hotelx.HotelBook.data.Availability;
import com.thanawat.hotelx.HotelBook.service.AvailabilityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AvailabilityController.class)
public class AvailabilityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AvailabilityService availabilityService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private Availability defaultAvailability;
    private List<Availability> defaultAvailabilityList;

    @BeforeEach
    public void setup() {
        objectMapper.registerModule(new JavaTimeModule());
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
        defaultAvailabilityList = availabilityList;
    }

    @Test
    void givenAvailabilityExist_whenGetAvailability_ReturnAvailabilityList() throws Exception {
        when(availabilityService.getAvailabilityByLocationDate(defaultAvailability)).thenReturn(defaultAvailabilityList);

        mockMvc.perform(get("/availabilities")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(defaultAvailability)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

}
