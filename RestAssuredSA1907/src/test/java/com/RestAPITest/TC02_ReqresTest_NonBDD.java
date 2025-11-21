package com.RestAPITest;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TC02_ReqresTest_NonBDD {
  @Test(priority=1)
  public void testSingleUser() {
	  
	  Response res = RestAssured.given().header("x-api-key", "reqres-free-v1").get("https://reqres.in/api/users/2");
	  
	  //status code should be 200
	  
	  int actcode = res.getStatusCode();
	  
	  Assert.assertEquals(actcode, 200, "Status code not matched");
	  System.out.println("Status code matched : "+actcode);
	  
	  // get the response payload
	  
	  //System.out.println(res.asPrettyString());  
	  
	  res.then().log().body();
	  
	  //validation for Json payload
	  
	  /* Manual: let res = pm.response.json();
	   * 
	   *  Automation: res.jsonPath().getInt("json path");
	   *  			  res.jsonPath().getString("json path");
	   * */
	  
	  //validate id should be 2
	  int id = res.jsonPath().getInt("data.id");
	  Assert.assertEquals(id, 2);
	  System.out.println("ID matched");
	  
	  //"janet.weaver@reqres.in"
	  String actEmail = res.jsonPath().getString("data.email");
	  String exp = "@reqres.in";
	  Assert.assertTrue(actEmail.contains(exp));
	  System.out.println("Email matched!"+actEmail);
  }
  
  @Test(priority=2)
  public void testListOfUsers()
  {
	  Response res = RestAssured.given().header("x-api-key", "reqres-free-v1").get("https://reqres.in/api/users?page=2");
	  
	  //log the body
	  res.then().log().body();
	  
	  //per_page = 6
	  int noOfRecords = res.jsonPath().getInt("per_page");
	  Assert.assertEquals(noOfRecords, 6);
	  System.out.println("No of records per page : "+noOfRecords);
	  
	  //first_name = "Tobias"
	  String firstName = res.jsonPath().getString("data[2].first_name");
	  Assert.assertEquals(firstName, "Tobias");
	  System.out.println("First name matched");
	  
	  //how to get all email ids from current response
	  List<String> allEmails = res.jsonPath().getList("data.email");
	  
	  System.out.println("Emails count : "+allEmails.size());
	  
	  for(String i:allEmails)
	  {
		  System.out.println(i);
	  }
	  
	  
  }
}
