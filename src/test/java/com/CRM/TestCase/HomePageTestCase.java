package com.CRM.TestCase;

import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTestCase extends BaseClass
{
  @Test(priority = 1)
  public void verifyUrl() 
  {
	  String actUrl = hp.getAppUrl();
	  Assert.assertTrue(actUrl.contains("crm"),"Test fail:Url not matched!");
	  System.out.println("Test pass:Url matched!");
  }
  
  @Test(priority = 2)
  public void verifyTitle() 
  {
	  String actTitle = hp.getAppTitle();
	  Assert.assertTrue(actTitle.contains("Customer"),"Test fail:Title not matched!");
	  System.out.println("Test pass:Title matched!");
  }
  
  @Test(priority = 3)
  public void validateSignIn() 
  {
	  String nextPageUrl = hp.getStatusOfLink();
	  Assert.assertTrue(nextPageUrl.contains("login"),"Test fail:Login page not opened!");
	  System.out.println("Test pass:Application navigating to Login page!");  
  }
}
