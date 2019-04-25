package com.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationExample 
{
	WebDriver driver;
	@BeforeMethod
	public void beforemethod() 
	{
		driver=DriverUtility.getDriver("chrome");
		driver.get("http://10.232.237.143:443/TestMeApp");
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.findElement(By.partialLinkText("SignUp")).click();
	}
	@AfterMethod
	public void aftermethod()
	{
		
		driver.close();
	}
	@Test
  public void TestRegister()
  {
	  driver.findElement(By.id("userName")).sendKeys("Ajaykumar2");
	 String avl= driver.findElement(By.id("err")).getText();
	 Assert.assertTrue(avl.contains("Available"));
	  driver.findElement(By.id("firstName")).sendKeys("Ajay");
	  driver.findElement(By.id("lastName")).sendKeys("Kumar");
	  driver.findElement(By.id("password")).sendKeys("Ajaykumar@1");
	  driver.findElement(By.id("pass_confirmation")).sendKeys("Ajaykumar@1");
	  driver.findElement(By.cssSelector("input[value='Male']")).click();
	  driver.findElement(By.id("emailAddress")).sendKeys("ask@gmail.com");
	  driver.findElement(By.id("mobileNumber")).sendKeys("8545884554");
	  driver.findElement(By.id("dob")).sendKeys("05/20/1996");
	  driver.findElement(By.id("address")).sendKeys("flaT NO 20,NAMMA Apartments,Namma Bengaluru");
	   WebElement li = driver.findElement(By.id("securityQuestion"));
	  Select sa=new Select(li);
	  sa.selectByValue("411011");
	  driver.findElement(By.id("answer")).sendKeys("Red");
	  driver.findElement(By.cssSelector("input[value='Register']")).click();
  }
}
