package com.RestAPITest;

import org.testng.annotations.Test;

import com.PojoClasses.AuthPojo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/* POJO Object ---------> JSON = Serialization
 * JSON Object ---------> POJO = Deserialization
 */

public class TC08_Serialization_deserialization {
  @Test
  public void testSerialization() throws JsonProcessingException {
	  
	  AuthPojo auth= new AuthPojo();
	  auth.setUsername("Jane");
	  auth.setPassword("test123");
	  
	  ObjectMapper obj = new ObjectMapper();
	  
	  String jsonData = obj.writerWithDefaultPrettyPrinter().writeValueAsString(auth);
	  System.out.println(jsonData);
  }
  
  @Test
  public void testDeserialization() throws JsonMappingException, JsonProcessingException {
	 
	  String jsonData = "{\n"
	  		+ "  \"username\" : \"Jane\",\n"
	  		+ "  \"password\" : \"test123\"\n"
	  		+ "}";
	  
	  ObjectMapper obj = new ObjectMapper();
	  AuthPojo auth = obj.readValue(jsonData, AuthPojo.class);
	  
	  System.out.println(auth.getUsername());
	  System.out.println(auth.getPassword());
  }
}
