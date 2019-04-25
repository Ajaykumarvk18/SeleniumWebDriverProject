package com.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class MainTest 
{
	public void test()
	{
		WebDriver driver=DriverUtility.getDriver("chrome");
		driver.manage().window().maximize();
		  driver.get("http://www.google.co.in/");
		  String title=driver.getTitle();
		  Assert.assertEquals(title,"Google");
	}

}
