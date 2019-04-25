package com.tests;

import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestKeyboardActions 
{
	@Test
	
  public void testKeyboard()
  {
	  WebDriver driver= DriverUtility.getDriver("chrome");
	  driver.get("http://10.232.237.143:443/TestMeApp");
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	  WebElement search=driver.findElement(By.id("myInput"));
	  Actions act1=new Actions(driver);
	 // act1.keyDown(search,Keys.SHIFT).perform();
	  act1.keyDown(search,Keys.SHIFT).sendKeys("b").pause(3000).keyUp(search,Keys.SHIFT).sendKeys("a").pause(3000).sendKeys("g").perform();
	  act1.moveToElement(driver.findElement(By.xpath("//div[contains(text(),'Hand bag')]"))).click().perform();
	  driver.findElement(By.cssSelector("input[value='FIND DETAILS']")).click();
	 String res=driver.findElement(By.cssSelector("input[value='FIND DETAILS']")).getText();
	  //Assert.assertTrue(res.contains("Hand"));
  }
}



