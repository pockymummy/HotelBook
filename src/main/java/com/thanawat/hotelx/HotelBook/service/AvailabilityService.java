package com.thanawat.hotelx.HotelBook.service;

import com.thanawat.hotelx.HotelBook.data.Availability;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AvailabilityService {
    public List<Availability> getAvailabilityByLocationDate(Availability availability) {
        Availability firstAvailability = Availability.builder()
                .locationId(availability.getLocationId())
                .end(availability.getEnd())
                .start(availability.getStart())
                .price(100.0)
                .hotelId(1L)
                .numPeople(availability.getNumPeople())
                .roomId(1L)
                .build();

        Availability secondAvailability = Availability.builder()
                .start(availability.getStart())
                .end(availability.getEnd())
                .locationId(availability.getLocationId())
                .hotelId(availability.getHotelId())
                .numPeople(availability.getNumPeople())
                .price(200.0)
                .hotelId(2L)
                .roomId(1L)
                .build();

        List<Availability> availabilityList = new ArrayList<Availability>();
        availabilityList.add(firstAvailability);
        availabilityList.add(secondAvailability);

        return availabilityList;
    }
}
