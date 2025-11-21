package com.PojoClasses;

public class PatchPojo {
	
	/* POJO - Plain Old Java Object
	 * 
	 * Encapsulation: Private data(keys), public methods
	 * 
	 * {
    		"username" : "admin",
    		"password" : "password123"
		}
	 * */
	
	private String firstname;
	private String lastname;
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	

}
