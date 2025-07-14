package util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class DateConfiguredObjectMapper {
    public static ObjectMapper getDateConfiguredObjectMapper() {
        ObjectMapper objectMapper1 = new ObjectMapper();
        objectMapper1.registerModule(new JavaTimeModule());
        return  objectMapper1;
    }
}
