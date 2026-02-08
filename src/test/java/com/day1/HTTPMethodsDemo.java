package com.day1;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class HTTPMethodsDemo {
	@Test
	void getUsers() {
		given()
		.when()
			.get("https://api.restful-api.dev/objects/6")
		.then()
			.statusCode(200)
			.header("Content-Type",equalTo("application/json"))
			.time(lessThan(2000L))
			.log().all();
	}
}
