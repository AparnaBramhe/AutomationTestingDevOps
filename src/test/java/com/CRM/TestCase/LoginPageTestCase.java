package com.CRM.TestCase;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;

public class LoginPageTestCase extends BaseClass
{
	@BeforeClass
	public void pageSetup()
	{
		hp.getStatusOfLink();
	}
  @Test
  public void validateLogin() 
  {
	String url = lp.doLogin("test@gmail.com", "test123");
	Assert.assertTrue(url.contains("customers"), "Test fail:Login fail!");
	System.out.println("Test pass:Login completed!");
  }
}
