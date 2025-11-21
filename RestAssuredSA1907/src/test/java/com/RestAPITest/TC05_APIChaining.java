package com.RestAPITest;

import static io.restassured.RestAssured.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.PojoClasses.AuthPojo;
import com.PojoClasses.Booking;
import com.PojoClasses.BookingDates;
import com.PojoClasses.PatchPojo;

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

public class TC05_APIChaining {
	int bookingid;
	String tokenValue;
	
  @Test(priority=1)
  public void createNewBooking() {
	  System.out.println("-----------Create New Booking-----------");
	  
	  //generate payload
	  BookingDates date = new BookingDates();
	  date.setCheckin("2025-11-19");
	  date.setCheckout("2025-11-20");
	  
	  Booking booking = new Booking();
	  booking.setFirstname("Nikita");
	  booking.setLastname("Rathod");
	  booking.setTotalprice(10000);
	  booking.setDepositpaid(true);
	  booking.setBookingdates(date);	  
	  booking.setAdditionalneeds("Dinner");
	  
	  Response res = given()
			  		.header("Content-Type", "application/json")
			  		.body(booking)
			  		.when().post("https://restful-booker.herokuapp.com/booking");
	  
	  //status code
	  System.out.println("Status code is : "+res.statusCode());
	  
	  //log the response
	  res.then().log().body();
	  
	  //Booking id to be stored
	  bookingid = res.jsonPath().getInt("bookingid");
	  System.out.println("Booking created with id : "+bookingid);
  }
  
  @Test(priority=2)
  public void getBookingDetails()
  {
	  System.out.println("--------------Get Booking Details-------------");
	  Response res = given()
			  		.when().get("https://restful-booker.herokuapp.com/booking/"+bookingid);
	  
	//log the response
	  res.then().log().body();
	  
	  System.out.println("Booking details for id :"+bookingid);
  }
  
  @Test(priority=3)
  public void createToken()
  {
	  System.out.println("------------Create Auth Token-----------");
	  
	  //generate payload
	  AuthPojo auth = new AuthPojo();
	  auth.setUsername("admin");
	  auth.setPassword("password123");
	  
	  Response res = given()
			  .header("Content-Type", "application/json")
			  .body(auth)
			  .when().post("https://restful-booker.herokuapp.com/auth");
	  
	  //log the response
	  res.then().log().body();
	  
	  tokenValue = res.jsonPath().getString("token");
	  System.out.println("Token generated : "+tokenValue);
  }
  
  @Test(priority=4)
  public void fullupdateBookingDetails()
  {
	  System.out.println("--------------- Full Update Booking Details------------");
	  
	  BookingDates date = new BookingDates();
	  date.setCheckin("2025-11-21");
	  date.setCheckout("2025-11-22");
	  
	  Booking booking = new Booking();
	  booking.setFirstname("Nikhil");
	  booking.setLastname("Rathi");
	  booking.setTotalprice(12000);
	  booking.setDepositpaid(true);
	  booking.setBookingdates(date);	  
	  booking.setAdditionalneeds("Lunch");
	  
	  Response res = given()
			  		.header("Content-Type", "application/json")
			  		.header("Accept", "application/json")
			  		.header("Cookie", "token="+tokenValue)
			  		.body(booking)
			  		.when().put("https://restful-booker.herokuapp.com/booking/"+bookingid);
	  
	  
	  //log the response
	  res.then().log().body();
	  
	  System.out.println("Booking Updated with id : "+bookingid);
  }
  
  @Test(priority=5)
  public void partialUpdateBooking()
  {
	  System.out.println("-------------Partial Update Booking--------------");
	  
	  PatchPojo patch1 = new PatchPojo();
	  patch1.setFirstname("Kashish");
	  patch1.setLastname("Jain");
	  
	  Response res = given()
			  		.header("Content-Type","application/json")
			  		.header("Accept","application/json")
			  		.header("Cookie","token="+tokenValue)
			  		.body(patch1)
			  		.when().patch("https://restful-booker.herokuapp.com/booking/"+bookingid);
	  
	  //log the response
	  res.then().log().body();
	  System.out.println("Partial update for id :"+bookingid);
  }
  
  @Test(priority=6)
  public void deleteBooking()
  {
	  System.out.println("----------------Delete Booking---------------");
	  
	  Response res = given()
			  		.header("Content-Type","application/json")
			  		.header("Cookie","token="+tokenValue)
			  		.when().delete("https://restful-booker.herokuapp.com/booking/"+bookingid);
	  
	  Assert.assertEquals(res.getStatusCode(),201);
	  System.out.println("Status code matched");
	  
	  res.then().log().all();
  }
}
