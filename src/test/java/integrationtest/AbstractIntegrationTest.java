package integrationtest;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@AutoConfigureMockMvc
@ActiveProfiles("integration-test")
public class AbstractIntegrationTest {
    private static final String POSTGRES_IMAGE = "postgres:latest";
    private static final String DATABASE_NAME = "hotelbookapp";
    private static final String DATABASE_USER = "postgres";
    private static final String DATABASE_PASSWORD = "yourpassword";

    @Container
    static final PostgreSQLContainer<?> postgresqlContainer = initPostgresqlContainer();

    private static PostgreSQLContainer<?> initPostgresqlContainer() {
        PostgreSQLContainer<?> container = new PostgreSQLContainer<>(POSTGRES_IMAGE)
                .withDatabaseName(DATABASE_NAME)
                .withUsername(DATABASE_USER)
                .withPassword(DATABASE_PASSWORD);
        container.start();
        return container;
    }

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgresqlContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgresqlContainer::getUsername);
        registry.add("spring.datasource.password", postgresqlContainer::getPassword);
    }
}
