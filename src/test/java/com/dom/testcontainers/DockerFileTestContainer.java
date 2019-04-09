package com.dom.testcontainers;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import org.hamcrest.CoreMatchers;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.testcontainers.containers.GenericContainer;

import java.time.Duration;

public class DockerFileTestContainer {

	@Rule
	public GenericContainer container = new GenericContainer("diogomonte/rest-api-application")
			.withStartupTimeout(Duration.ofSeconds(30))
			.withExposedPorts(8080);

	@Test
	public void itShouldTestHelloWorldContainer() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = container.getMappedPort(8080);

		RestAssured.given().accept(ContentType.JSON)
				.when().get("/hello")
				.then().assertThat().statusCode(200)
				.and().body(CoreMatchers.equalTo("Hello world!"));
	}

}
