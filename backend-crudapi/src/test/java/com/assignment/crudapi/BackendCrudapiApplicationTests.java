package com.assignment.crudapi;

import io.restassured.http.ContentType;
import static io.restassured.RestAssured.*;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

@SuppressWarnings("unused")
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BackendCrudapiApplicationTests {

	@Test
	@Order(1)
	public void test_adduser() {
		String jsonbody = "{\"first_name\":\"Sample\",\"last_name\":\"1\",\"age\":22,\"email\":\"Sample@gmail.com\"}";
		String res = given()
				.header("Content-type","application/json").contentType(ContentType.JSON).accept(ContentType.JSON)
				.body(jsonbody)
				.when()
				.post("http://localhost:8031/api/users/add")
				.then()
				.assertThat().statusCode(201)
				.extract().response().asString();
	}
	
	@Test
	@Order(2)
	public void test_getallusers() {
		String res = given()
				.header("Content-type","application/json").contentType(ContentType.JSON).accept(ContentType.JSON)
				.when()
				.get("http://localhost:8031/api/users/allusers")
				.then()
				.assertThat().statusCode(200)
				.extract().response().asString();
	}
	
	@Test
	@Order(3)
	public void test_getuser() {
		String res = given()
				.header("Content-type","application/json").contentType(ContentType.JSON).accept(ContentType.JSON)
				.when()
				.get("http://localhost:8031/api/users/Sample@gmail.com")
				.then()
				.assertThat().statusCode(200)
				.extract().response().asString();
	}
	
	@Test
	@Order(4)
	public void test_updateuser() {
		String jsonbody = "{\"first_name\":\"Sample\",\"last_name\":\"2\",\"age\":21,\"email\":\"Sample@gmail.com\"}";
		String res = given()
				.header("Content-type","application/json").contentType(ContentType.JSON).accept(ContentType.JSON)
				.body(jsonbody)
				.when()
				.put("http://localhost:8031/api/users/Sample@gmail.com")
				.then()
				.assertThat().statusCode(200)
				.extract().response().asString();
	}
	
	@Test
	@Order(5)
	public void test_deleteuser() {
		String res = given()
				.header("Content-type","application/json").contentType(ContentType.JSON).accept(ContentType.JSON)
				.when()
				.delete("http://localhost:8031/api/users/Sample@gmail.com")
				.then()
				.assertThat().statusCode(200)
				.extract().response().asString();
	}
	
}
