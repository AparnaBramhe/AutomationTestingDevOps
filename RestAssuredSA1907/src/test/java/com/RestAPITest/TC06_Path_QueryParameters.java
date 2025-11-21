package com.RestAPITest;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.response.Response;

/* given(): pre-requisite
 * header, path parameter, query parameter, 
 * request payload, authorization
 * -------------------------------------------
 * when(): Action(type of request) 
 * GET, POST, PUT, PATCH, DELETE
 * -------------------------------------------
 * then(): Validate Response
 * Status code, status message, response time, 
 * response payload, header, cookies
 * -------------------------------------------
 * */

public class TC06_Path_QueryParameters {
  @Test
  public void f() 
  {
	RestAssured.baseURI = "https://api.restful-api.dev/";
	Response res = given()
				.pathParam("path", "objects")
				.queryParam("id", 3)
				.queryParam("id", 5)
				.queryParam("id", 10)
				
				.when().get("/{path}");
				//.when().get("https://api.restful-api.dev/{path}");
				//.when().get("https://api.restful-api.dev/objects?id=3&id=5&id=10");
	
	//log the response
	res.then().log().body();
  }
}
