package com.tests;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class TestExtendReports1 
{
      WebDriver driver;
      ExtentHtmlReporter htmlreporter;
      ExtentReports reports;
      ExtentTest logger;
      
      @BeforeClass
      public void beforeClass()
      {
    	  driver=DriverUtility.getDriver("chrome");
    	  driver.get("http://demowebshop.tricentis.com/login");
    	  driver.manage().window().maximize();
    	  
    	 
    	  SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss-ms");
    	  String path=System.getProperty("user.dir")+"/extent-reports/"+sdf.format(new Date())+".html";
    	  htmlreporter=new ExtentHtmlReporter(path);
    	  reports=new ExtentReports();
    	  reports.attachReporter(htmlreporter);
    	  reports.setSystemInfo("username", "indumathi");
    	  reports.setSystemInfo("host","localhost");
    	  reports.setSystemInfo("Environment", "Test Environment");
    	  
    	  htmlreporter.config().setReportName("Test Me App Report");
    	  htmlreporter.config().setTheme(Theme.DARK);
    	  
      }
      
      @AfterClass
      public void afterClass()
      {
    	  reports.flush();
      }
      
      @Test
      public void passTest()
      {
    	  logger=reports.createTest("passTest");
    	  logger.log(Status.INFO, "pass test started");
    	  Assert.assertTrue(true);
      }
      
      @Test
      public void failTest()
      {
    	  logger=reports.createTest("failTest");
    	  logger.log(Status.INFO, "fail test started");
    	  driver.findElement(By.id("Email")).sendKeys("askmail@email.com");
    	  driver.findElement(By.id("Password")).sendKeys("askmail@email.com");
         driver.findElement(By.partialLinkText("Log in")).click();
    	  
    	  
    	   
      }
      
      @Test
      public void skipTest()
      {
    	  logger=reports.createTest("skipTest");
    	  logger.log(Status.INFO, "skip test started");
    	  throw new SkipException("SKIP");
      }
      
      @AfterMethod
      public void afterMethod(ITestResult result)  //*to track the status 
      {
    	
		if(result.getStatus()==ITestResult.FAILURE)
    	  {
    		  logger.log(Status.FAIL, "THE TEST IS FAILED");
    		  TakesScreenshot ts=(TakesScreenshot) driver;
    		  File srcFile=ts.getScreenshotAs(OutputType.FILE);
    		  String imgPath=System.getProperty("user.dir")+"/extent-reports/"+result.getMethod().getMethodName()+".png"; 
    		  try 
    		  {
				FileUtils.copyFile(srcFile, new File(imgPath));
				logger.log(Status.INFO, result.getThrowable());
				logger.addScreenCaptureFromPath(imgPath,"Fail Test image");
			}
    		  catch (IOException e) 
    		  {
			
				e.printStackTrace();
			  }
    	  }
    	  
    	  else if(result.getStatus()==ITestResult.SUCCESS)
    	  {
    		  logger.log(Status.PASS, "THE TEST IS SUCCESS"); 
    		  logger.log(Status.INFO,MarkupHelper.createLabel( "THE TEST IS SUCCESS", ExtentColor.GREEN));
    	  }
    	  
    	  else if(result.getStatus()==ITestResult.SKIP)
    	  {
    		  logger.log(Status.SKIP, "THE TEST IS SKIP");
    	  }
    	  
    	  
      }
      
      
      
}