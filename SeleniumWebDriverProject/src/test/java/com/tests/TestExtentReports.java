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

public class TestExtentReports
{
  WebDriver driver;
  ExtentHtmlReporter htmlReporter;
  ExtentReports reports;
  ExtentTest logger;
  @BeforeClass
  public void beforeclass() 
  {
	  driver=DriverUtility.getDriver("chrome");
	  driver.get("http://demowebshop.tricentis.com/login");
	  driver.manage().window().maximize();
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss-ms");
	String path=System.getProperty("user.dir")+"/extent-reports/"+sdf.format(new Date())+".html";
	htmlReporter=new ExtentHtmlReporter(path);
	reports=new ExtentReports();
	reports.attachReporter(htmlReporter);
	reports.setSystemInfo("username", "Ajay");
	reports.setSystemInfo("host", "localhost");
	reports.setSystemInfo("Environment", "Test Environment");
	
	htmlReporter.config().setReportName("Test Me App Report");
	htmlReporter.config().setTheme(Theme.DARK);
  }
  @AfterClass
  public void afterclass()
  {
	reports.flush();
  }
  @Test
  public void passtest() 
  {
	logger=reports.createTest("passTest");
	logger.log(Status.INFO, "pass test started");
	Assert.assertTrue(true);
}
  @Test
  public void failtest()
  {
	  logger=reports.createTest("failTest");
		logger.log(Status.INFO, "fail test started");
		//Assert.assertTrue(false);
		driver.findElement(By.id("Email")).sendKeys("ask@email.com");
		driver.findElement(By.id("Pass")).sendKeys("ask@email.com");
}
  @Test
  public void skiptest() 
  {
	  logger=reports.createTest("skipTest");
		logger.log(Status.INFO, "skip test started");
		throw new SkipException("SKIP"); 
}
  @AfterMethod
  public void aftermethod(ITestResult result)
  {
	  if (result.getStatus()==ITestResult.FAILURE) 
	  {
		logger.log(Status.FAIL, "The test is failed");
		TakesScreenshot ts=(TakesScreenshot) driver;
		File srcFile=ts.getScreenshotAs(OutputType.FILE);
		String imagepath=System.getProperty("user.dir")+"/extent-reports/images/"+result.getMethod()+".png";
		
		
			try {
				FileUtils.copyFile(srcFile, new File(imagepath));
				logger.log(Status.INFO, result.getThrowable());
				logger.addScreenCaptureFromPath(imagepath,"fail test image");
			}
			catch (IOException e) 
			{
				
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
	}
	  else if(result.getStatus()==ITestResult.SUCCESS)
	  {
		  logger.log(Status.PASS, "The test is success");
		  logger.log(Status.INFO, MarkupHelper.createLabel("The test is success",ExtentColor.GREEN));
	  }
	  else if(result.getStatus()==ITestResult.SKIP) 
	  {
		logger.log(Status.SKIP, "The test is skip");  
	  }
	
}
}
