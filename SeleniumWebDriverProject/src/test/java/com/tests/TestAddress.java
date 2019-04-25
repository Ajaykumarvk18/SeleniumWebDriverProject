package com.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class TestAddress 
{
	@Test
  public void TestAdd() throws InterruptedException
  {
	  WebDriver driver=DriverUtility.getDriver("chrome");
	  driver.get("http://10.232.237.143:443/TestMeApp");
	  driver.manage().window().maximize();
	  Thread.sleep(5000);
	  Actions act1=new Actions(driver);
	  act1.moveToElement(driver.findElement(By.partialLinkText("AboutUs"))).perform();
	  act1.moveToElement(driver.findElement(By.partialLinkText("Our Offices"))).perform();
	  act1.moveToElement(driver.findElement(By.linkText("Chennai"))).click().perform();
	  
	  
	  
  }
}
