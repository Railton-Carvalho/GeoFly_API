package com.rocket.rain.apigateaway.integrationtest.swagger;

import static  io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

import com.rocket.rain.apigateaway.configs.TestConfigs;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SwaggerIntegrationTest {

	@Value("${local.server.port}")
	private int serverPort;

	@Test
	public void shouldDisplaySwaggerUiPage() {
		var content =
				given()
						.basePath("/swagger-ui/index.html")
						.port(TestConfigs.SERVER_PORT) // Use a porta fornecida pelo Spring Boot
						.when()
						.get()
						.then()
						.statusCode(200)
						.extract()
						.body()
						.asString();
		assertTrue(content.contains("Swagger UI"));
		System.out.println(serverPort);
	}
}
