package com.thanawat.hotelx.HotelBook.controller;

import com.thanawat.hotelx.HotelBook.data.Availability;
import com.thanawat.hotelx.HotelBook.service.AvailabilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/availabilities")
public class AvailabilityController {
    private final AvailabilityService availabilityService;

    @GetMapping
    public ResponseEntity<List<Availability>> getAvailabilityByLocationDate(@RequestBody Availability availability) {
        return ResponseEntity.ok(availabilityService.getAvailabilityByLocationDate(availability));
    }
}
