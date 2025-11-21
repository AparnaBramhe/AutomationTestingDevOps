package com.RestAPITest;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TC01_FirstAPITest_NonBDD {
  @Test
  public void SingleObjectTest() {
	  /*
	   * RestAssured as Class
	   * Response as an Interface
	   * */
	  
	  Response res = RestAssured.get("https://api.restful-api.dev/objects/7");
	  System.out.println("Status code is : "+res.getStatusCode());
	  System.out.println("Status line is : "+res.statusLine());
	  System.out.println("Response time in(ms) is : "+res.getTimeIn(TimeUnit.MILLISECONDS));
	  System.out.println("Header : "+res.getHeader("Content-Type"));
	  System.out.println("-----------JSON Payload-------------");
	  System.out.println(res.asPrettyString());
  }
}
