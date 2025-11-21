package com.RestAPITest;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import com.PojoClasses.AuthPojo;

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

public class TC04_JsonPayloadWays {
  @Test(priority=1)
  public void copypastePayload() {
	  //payload
	  System.out.println("-----------Test request payload using copy paste----------");
	  Response res = given()
			  		.header("Content-Type", "application/json")
			  		.body("{\n"
			  				+ "    \"username\" : \"admin\",\n"
			  				+ "    \"password\" : \"password123\"\n"
			  				+ "}")
			  		.when().post("https://restful-booker.herokuapp.com/auth");
	  
	  System.out.println("Status code : "+res.getStatusCode());
	  
	  //log the body
	  
	  res.then().log().body();
	  
	  String actToken = res.jsonPath().getString("token");
	  System.out.println(actToken);
  }
  
  @Test(priority=2)
  public void payloadUsingHashmap()
  {
	  System.out.println("--------Test request payload using Hashmap------------");
	  
	  //generate payload
	  HashMap<String, Object> data = new HashMap<String, Object>();
	  data.put("username","admin");
	  data.put("password", "password123");
	  
	  Response res = given()
			  		.header("Content-Type","application/json")
			  		.body(data)
			  		.when().post("https://restful-booker.herokuapp.com/auth");
			
	  //response log
	  res.then().log().body();
	  
	  String actToken = res.jsonPath().getString("token");
	  System.out.println(actToken);
  }
  
  @Test(priority=3)
  public void payloadUsingPOJO()
  {
	  System.out.println("--------Test request payload using POJO class------------");
	  
	  //generate payload
	  
	  AuthPojo auth = new AuthPojo();
	  auth.setUsername("admin");
	  auth.setPassword("password123");
	  
	  Response res = given()
			  		.header("Content-Type","application/json")
			  		.body(auth)
			  		
			  		.when().post("https://restful-booker.herokuapp.com/auth");
	  
	  //response log
	  res.then().log().body();
	  
	  String actToken = res.jsonPath().getString("token");
	  System.out.println(actToken);
  }
  
  @Test(priority=4)
  public void payloadUsingJSONObjectClass()
  {
	  System.out.println("--------Test request payload using JSONObject class------------");
	  
	  JSONObject obj = new JSONObject();
	  obj.put("username","admin");
	  obj.put("password","password123");
	  
	  //why toString method : To send payload in formatted json
	  Response res = given()
			  		.header("Content-Type","application/json")
			  		.body(obj.toString())
			  		
			  		.when().post("https://restful-booker.herokuapp.com/auth");
	  
	  //response log
	  res.then().log().body(); 
	  
	  String actToken = res.jsonPath().getString("token");
	  System.out.println(actToken);
  } 
  
  @Test(priority=5)
  public void payloadUsingJsonFile() throws FileNotFoundException
  {
	  System.out.println("--------Test request payload using JsonFile------------");
	  
	  // file path
	  	File f1 = new File(System.getProperty("user.dir")+"//AuthData.json");
	  	
	  // file reader to read file
	  	FileReader fr = new FileReader(f1);
	  	
	  // JSONTokener class
	  	JSONTokener token = new JSONTokener(fr);
	  	
	  // JSONObject class
	  	JSONObject obj = new JSONObject(token);
	  	
	  Response res = given()
			  		.header("Content-Type","application/json")
			  		.body(obj.toString())
			  
			  		.when().post("https://restful-booker.herokuapp.com/auth");
	  
	  //response log 
	  res.then().log().body();
	  
	  String actToken = res.jsonPath().getString("token");
	  System.out.println(actToken);  
  }
}
