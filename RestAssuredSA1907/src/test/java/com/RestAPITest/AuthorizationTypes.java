package com.RestAPITest;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;

public class AuthorizationTypes {
  @Test
  public void basicAuth() 
  {
	  //Base64 Algorithm
	  Response res = given()
			  		.auth().basic("postman", "password")
			  		.when().get("https://postman-echo.com/basic-auth");
	  
	  res.then().log().body();
  }
 
  @Test
  public void digestAuth() 
  {
	  // MD5 and SHA Algorithm
	  Response res = given()
			  		.auth().digest("postman", "password")
			  		.when().get("https://postman-echo.com/digest-auth");
	  
	  res.then().log().body();
  }
  
  @Test
  public void testBearerToken() 
  {
	  //String token = "";
	  Response res = given()
			  		//.header("Authorization", "Bearer"+token)
			  		.when().get("https://api.github.com/user/repos");
	  
	  res.then().log().body();
  }
  
  @Test
  public void testOAuth2() 
  {
	  //String githubToken = "";
	  Response res = given()
			  		//.auth().oauth2(githubToken)
			  		.when().get("https://api.github.com/user/repos");
	  
	  res.then().log().body();
  }
  
}
