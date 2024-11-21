package com.javinsnc.microservices.inventory_service;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MySQLContainer;

import static org.junit.jupiter.api.Assertions.*;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InventoryServiceApplicationTests {

	@ServiceConnection
	static MySQLContainer mySQLContainer = new MySQLContainer("mysql:8.3.0");

	@LocalServerPort
	private Integer port;

	static {
		mySQLContainer.start();
	}

	@BeforeEach
	void setup() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	@Test
	void shouldBeInStock() {
		final var result = RestAssured.given()
				.queryParam("skuCode", "iphone_15")
				.queryParam("quantity", 100)
				.when()
				.get("/api/inventory")
				.then()
				.statusCode(200)
				.extract().body().as(Boolean.class);

		assertTrue(result);
	}

	@Test
	void shouldNotBeInStock() {
		final var result = RestAssured.given()
				.queryParam("skuCode", "iphone_15")
				.queryParam("quantity", 101)
				.when()
				.get("/api/inventory")
				.then()
				.statusCode(200)
				.extract().body().as(Boolean.class);

		assertFalse(result);
	}

}
