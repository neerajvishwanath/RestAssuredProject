package com.day2;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class PostRequestBodyExamples {
	String studentId;
	
	@Test
	void createStudentUsingHashMap() {
		
		HashMap <String, Object> requestBody=new HashMap<>();
		requestBody.put("name", "Scott");
		requestBody.put("location", "France");
		requestBody.put("phone", "123456");
		
		String courses[]= {"C", "C++"};
		requestBody.put("courses", courses);
		
		studentId=given()
					.contentType("application/json")
					.body(requestBody)
				.when()
					.post("http://localhost:3000/students")
				.then()
					.statusCode (201)
					.body ("name", equalTo("Scott"))
					.body("location", equalTo("France"))
					.body("phone", equalTo("123456"))
					.body("courses[0]", equalTo("C"))
					.body("courses [1]", equalTo("C++"))
					.header("Content-Type", "application/json")
					.log().body()
					.extract().jsonPath().getString("id");
	}
	
	@AfterMethod
	void deleteStudentRecord() {
		given()
		.when()
			.delete("http://localhost:3000/students/" + studentId)
		.then()
			.statusCode(200);
	}
}
