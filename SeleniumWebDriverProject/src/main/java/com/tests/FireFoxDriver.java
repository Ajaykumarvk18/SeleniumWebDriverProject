package com.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FireFoxDriver
{
	@Test
	  public void testGoogleHomePage()
	  {
		  System.setProperty("webdriver.gecko.driver", "C:\\Selenium\\Drivers\\geckodriver.exe");
		  WebDriver driver=new FirefoxDriver(); 
		  driver.manage().window().maximize();
		  driver.get("http://www.google.co.in/");
		  String title=driver.getTitle();
		  Assert.assertEquals(title,"Google");
	  }
}
