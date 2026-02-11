package com.day2;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

public class PostRequestBodyExamples {
	String studentId;
	
	@Test(enabled = false)
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
	
	@Test(enabled = false)
	void createStudentUsingJSONLibrary() {
		
		JSONObject requestBody = new JSONObject();
		requestBody.put("name", "Scott");
		requestBody.put("location", "France");
		requestBody.put("phone", "123456");
		
		String courses[]= {"C", "C++"};
		requestBody.put("courses", courses);
		
		studentId=given()
					.contentType("application/json")
					.body(requestBody.toString())
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
	
	@Test(enabled = false)
	void createStudentUsingPOJOClass() {
		
		StudentPojo requestBody = new StudentPojo();
		requestBody.setName("Scott");
		requestBody.setLocation("France");
		requestBody.setPhone("123456");
		
		String courses[]= {"C","C++"};
		requestBody.setCourses(courses);
		
		studentId=given()
					.contentType("application/json")
					.body(requestBody)
				.when()
					.post("http://localhost:3000/students")
				.then()
					.statusCode (201)
					.body ("name", equalTo(requestBody.getName()))
					.body("location", equalTo(requestBody.getLocation()))
					.body("phone", equalTo(requestBody.getPhone()))
					.body("courses[0]", equalTo(requestBody.getCourses()[0]))
					.body("courses [1]", equalTo(requestBody.getCourses()[1]))
					.header("Content-Type", "application/json")
					.log().body()
					.extract().jsonPath().getString("id");
	}
	
	@Test
	void createStudentUsingExternalFile() throws FileNotFoundException {
		
		File myFile = new File(".\\src\\test\\java\\com\\day2\\body.json");
		FileReader fileReader = new FileReader(myFile);
		JSONTokener jsonTokener = new JSONTokener(fileReader);
		
		JSONObject requestBody = new JSONObject(jsonTokener);
		
		
		studentId=given()
					.contentType("application/json")
					.body(requestBody.toString())
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
