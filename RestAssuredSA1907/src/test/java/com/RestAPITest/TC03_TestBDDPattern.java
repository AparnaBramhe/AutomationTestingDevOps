package com.RestAPITest;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

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

public class TC03_TestBDDPattern {
  @Test
  public void testSingleUser() {
	  
	  Response res = given()
			  		.header("x-api-key", "reqres-free-v1")
			  		.when().get("https://reqres.in/api/users/2");
	  
	  //.then().statusCode(200)
	  //.log().body();
	  System.out.println(res.getStatusCode());
	  
	  //log
	  //for all headers
	  res.then().log().headers();
	  
	  //body
	  res.then().log().body();
	  
	  //all
	  res.then().log().all();
  }
}
