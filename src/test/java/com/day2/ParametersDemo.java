package com.day2;

import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

public class ParametersDemo {
	
	@Test(enabled = false)
	void pathParams() {
		given()
			.pathParam("country", "india")
		.when()
			.get("https://restcountries.com/v2/name/{country}")
		.then()
			.statusCode(200)
			.log().body();
	}
	
	@Test
	void quaryParams() {
		given()
			.header("x-api-key", "reqres_98af37a4c34149f6a379b5ccc9f5ab1a")
			.queryParams("page","2")
			.queryParams("id","5")
		.when()
			.get("https://reqres.in/api/users")
		.then()
			.statusCode(200)
			.log().body();
	}
}
