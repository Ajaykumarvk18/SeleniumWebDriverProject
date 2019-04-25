package com.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestDreadAndDrop 
{
	@Test
  public void testDragAndDrop()
  {
	  WebDriver driver=DriverUtility.getDriver("chrome");
	  driver.get("https://demos.telerik.com/aspnet-ajax/treeview/examples/overview/defaultcs.aspx");
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  WebElement from=driver.findElement(By.xpath("//div[@id=\"ctl00_ContentPlaceholder1_RadTreeView1\"]/ul/li/ul/li[3]/ul/li[1]/div/div/span"));
	  WebElement to=driver.findElement(By.id("ctl00_ContentPlaceholder1_priceChecker"));
	  Actions act1=new Actions(driver);
	  act1.dragAndDrop(from, to).perform();
	  WebDriverWait wait=new WebDriverWait(driver, 20);
	  wait.until(ExpectedConditions.invisibilityOfElementWithText(By.id("ctl00_ContentPlaceholder1_Label1"), "Drop a package here to check price"));
	  String result=driver.findElement(By.id("ctl00_ContentPlaceholder1_Label1")).getText();
	  System.out.println("the result"+result);
	  Assert.assertTrue(result.contains("$3999"));
	  driver.close();
  }
}
