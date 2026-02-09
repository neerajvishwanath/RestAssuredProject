package com.day1;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class HTTPMethodsDemo {
	@Test(enabled = false)
	void getDevice() {
		given()
		.when()
			.get("https://api.restful-api.dev/objects/6")
		.then()
			.statusCode(200)
			.header("Content-Type",equalTo("application/json"))
			.time(lessThan(2000L))
			.log().all();
	}
	
	@Test
	void postDevice() {
		given()
			.contentType("application/json")
			.body("{\r\n"
					+ "   \"name\": \"HP Probook 04\",\r\n"
					+ "   \"data\": {\r\n"
					+ "      \"year\": 2020,\r\n"
					+ "      \"price\": 1249.99,\r\n"
					+ "      \"CPU model\": \"Intel Core i7\",\r\n"
					+ "      \"Hard disk size\": \"500 GB\"\r\n"
					+ "   }\r\n"
					+ "}")
		.when()
			.post("https://api.restful-api.dev/objects")
		.then()
			.statusCode(200)
			.header("Content-Type",equalTo("application/json"))
			.time(lessThan(2000L))
			.body("name", equalTo("HP Probook 04"))
			.body(containsString("id"))
			.log().all();
			
	}
	
	
}
