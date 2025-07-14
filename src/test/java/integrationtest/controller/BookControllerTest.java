package integrationtest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thanawat.hotelx.HotelBook.HotelBookApplication;
import com.thanawat.hotelx.HotelBook.data.Book;
import com.thanawat.hotelx.HotelBook.repository.BookRepository;
import integrationtest.AbstractIntegrationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import util.DateConfiguredObjectMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = HotelBookApplication.class)
public class BookControllerTest extends AbstractIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    private final ObjectMapper objectMapper = DateConfiguredObjectMapper.getDateConfiguredObjectMapper();

    @BeforeEach
    void clearData()  { bookRepository.deleteAll();}

    @Test
    void givenExistedBooking_whenGetBooking_returnBooking() throws Exception{
        Book existingBook = Book.builder().hotelId(1L).build();
        Book savedBook = bookRepository.save(existingBook);

        mockMvc.perform(get("/books/" + savedBook.getBookingId()))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    Book actual = objectMapper.readValue(result.getResponse().getContentAsString(), Book.class);
                    assertEquals(savedBook.getBookingId(), actual.getBookingId());
                    assertEquals(savedBook.getHotelId(), actual.getHotelId());
                });
    }

    @Test
    void givenNonExistedBooking_whenGetBooking_returnEmpty() throws Exception{
        mockMvc.perform(get("/books/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void givenBooking_whenCreateReservation_returnBooking() throws Exception{
        Book booking = Book.builder().hotelId(1L).build();

        mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(booking)))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    Book actual = objectMapper.readValue(result.getResponse().getContentAsString(), Book.class);
                    assertNotNull(actual.getBookingId());
                    assertEquals(booking.getHotelId(), actual.getHotelId());
                });
    }
}
