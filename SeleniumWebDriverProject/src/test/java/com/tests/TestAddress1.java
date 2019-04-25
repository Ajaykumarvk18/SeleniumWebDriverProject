package com.tests;


import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestAddress1 
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
		  
		  String home=driver.getWindowHandle();
		  Set<String> set=driver.getWindowHandles();
		  Assert.assertEquals(set.size(), 2);
		  System.out.println(set);
		  Object[] windows=set.toArray();
		  
		  driver.switchTo().window(windows[1].toString());
		  System.out.println("The title :"+driver.getTitle());
		  System.out.println("The title :"+driver.getCurrentUrl());
		  Assert.assertEquals(driver.getTitle(), "Contact Us");
		  driver.switchTo().frame(driver.findElement(By.name("main_page")));
		  WebElement address=driver.findElement(By.id("demo3"));
		  System.out.println(address.getText());
		  Assert.assertTrue(address.getText().contains("Chennai"));
		  List<WebElement> list = address.findElements(By.tagName("p"));
		 Assert.assertEquals(list.size(),6);
		 
		 driver.switchTo().window(home);
		 driver.findElement(By.cssSelector("input[value='FIND DETAILS']")).click();
		 if(ExpectedConditions.alertIsPresent()!=null)
		 {
			 Alert al=driver.switchTo().alert();
			 System.out.println("Alert message"+al.getText());
			 al.accept();
		 }
		 
		  //driver.switchTo().window(home);
		  //driver.quit();
		  
		  
		  
	  }
}
