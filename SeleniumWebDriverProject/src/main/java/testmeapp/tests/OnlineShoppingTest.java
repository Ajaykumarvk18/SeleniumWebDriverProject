package testmeapp.tests;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.tests.DriverUtility;

public class OnlineShoppingTest 
{
  WebDriver driver;
  ExtentHtmlReporter htmlreporter;
  ExtentReports reports;
  ExtentTest logger;
  String aj="Ajaykumar30";
	@Test(priority = 1)
	public void TestRegistration() 
	{
		driver=DriverUtility.getDriver("chrome");
		driver.get("http://10.232.237.143:443/TestMeApp");
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.findElement(By.partialLinkText("SignUp")).click();
		
		
		
		 driver.findElement(By.id("userName")).sendKeys(aj);
		
		  driver.findElement(By.id("firstName")).sendKeys("Ajay");
		 String avl= driver.findElement(By.id("err")).getText();
			 Assert.assertEquals(avl, "Available");
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
		  //String errmsg=driver.findElement(By.partialLinkText(" User Registered Succesfully!!! Please login")).getText();
		  String errmsg=driver.findElement(By.xpath("//div[@id=\"errormsg\"][4]")).getText();
		  Assert.assertEquals(errmsg,"User Registered Succesfully!!! Please login");
		  logger=reports.createTest("Registration");
			logger.log(Status.INFO, "Registration success");
		  
	}
	@Test(priority = 2)
	public void TestLogin() 
	{
		driver.findElement(By.id("userName")).sendKeys(aj);
		driver.findElement(By.id("password")).sendKeys("Ajaykumar@1");
		driver.findElement(By.cssSelector("input[value='Login']")).click();
		//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		Assert.assertTrue(driver.findElement(By.xpath("//header[@id=\"header\"]/div[1]/div/div/div[2]/div/ul")).getText().contains(aj));
		logger=reports.createTest("Login");
		logger.log(Status.INFO, "Login success");
	}
	@Test(priority = 3)
	public void TestCart() 
	{
		//Actions act1=new Actions(driver);
		//act1.moveToElement(driver.findElement(By.xpath("//div[@id=\"menu3\"]/li[2]/a/span"))).perform();
		//driver.findElement(By.id("myInput")).click();
		driver.findElement(By.id("myInput")).sendKeys("Bag");
		driver.findElement(By.cssSelector("input[value='FIND DETAILS']")).click();
		driver.findElement(By.partialLinkText("Add to cart")).click();
		driver.findElement(By.partialLinkText("Cart")).click();
		driver.findElement(By.partialLinkText("Checkout")).click();
		driver.findElement(By.cssSelector("input[value='Proceed to Pay']")).click();
		logger=reports.createTest("Cart");
		logger.log(Status.INFO, "Adding to Cart");
	}
	@Test(priority = 4)
	public void TestPayment() 
	{
		driver.findElement(By.xpath("//div[@id=\"swit\"]/div[1]/div/label/i")).click();
		driver.findElement(By.id("btn")).click();
		driver.findElement(By.xpath("//div[@id=\"horizontalTab\"]/div[2]/div/div/div/div/form/input[1]")).sendKeys("123456");
		driver.findElement(By.xpath("//div[@id=\"horizontalTab\"]/div[2]/div/div/div/div/form/input[2]")).sendKeys("Pass@456");
		driver.findElement(By.cssSelector("input[value='LOGIN']")).click();
		driver.findElement(By.xpath("//div[@id=\"horizontalTab\"]/div[2]/div/div/div/div/form/input")).sendKeys("Trans@456");
		driver.findElement(By.cssSelector("input[value='PayNow']")).click();
		
		logger=reports.createTest("Payment");
		logger.log(Status.INFO, "payment success");
		
	} 
	
	@BeforeTest
	public void startReportBeforeTest() 
	{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss-ms");
		String path=System.getProperty("user.dir")+"/extent-reports/"+sdf.format(new Date())+".html";
		htmlreporter=new ExtentHtmlReporter(path);
		reports=new ExtentReports();
		reports.attachReporter(htmlreporter);
		reports.setSystemInfo("username", "Ajay");
		reports.setSystemInfo("host", "localhost");
		reports.setSystemInfo("Environment", "Test Environment");
		
		htmlreporter.config().setReportName("Test Me App Report");
		htmlreporter.config().setTheme(Theme.DARK);
	}
	   @AfterTest
	      public void aftertest()
	      {
		   reports.flush();
	      }
	   @AfterMethod
	   public void getResultAfterMethod(ITestResult result)   
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
