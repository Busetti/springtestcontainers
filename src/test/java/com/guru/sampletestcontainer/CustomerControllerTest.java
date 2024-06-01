package com.guru.sampletestcontainer;

import com.guru.sampletestcontainer.entities.Customer;
import com.guru.sampletestcontainer.repo.CustomerRepo;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
// Similar o Before Each Method (Sql Inserts)
@Sql(scripts = "src/test/resources/setup.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
// Similar to Afer Each Method (Sql Cleanup)
@Sql(scripts = "src/test/resources/cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class CustomerControllerTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(DockerImageName.parse("postgres:latest"));

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private CustomerRepo customerRepo;


    @Test
    public void testContainer() {
        Assertions.assertTrue(postgres.isCreated());
    }

    @Test
    public void shouldAllCustomers() {
        Customer[] customers = testRestTemplate.getForObject("/api/customers", Customer[].class);
        Assertions.assertEquals(2, customers.length);
    }

    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }


}
