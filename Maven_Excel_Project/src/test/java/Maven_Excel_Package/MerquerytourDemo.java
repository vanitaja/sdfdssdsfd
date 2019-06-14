package Maven_Excel_Package;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class MerquerytourDemo 
{
	public WebDriver driver;
	
  @Test(dataProvider = "dp")
  public void loginWithValidDetails(String username, String password) 
  {
	driver.findElement(By.xpath("//input[@name='userName']")).sendKeys(username);
	driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
	driver.findElement(By.xpath("//input[@name='login']")).click();
	boolean act_flag=driver.findElement(By.xpath("//img[@src='/images/masts/mast_flightfinder.gif']")).isDisplayed();
	boolean exp_flag=true;
	Assert.assertEquals(act_flag, exp_flag);
	driver.findElement(By.xpath("//a[contains(text(),'SIGN-OFF')]")).click();
	System.out.println("valid user login successfully");
  }
  
  @BeforeMethod
  public void getAllCookies() 
  {
	  Set<Cookie> c=driver.manage().getCookies();
	  for(Cookie coo:c)
	  {
		  System.out.println("get the name of the cookies:-"+coo);
	  }
  }

  @AfterMethod
  public void captureScreenshot(ITestResult result) throws IOException 
  {
	  if(result.FAILURE==result.getStatus())
	  {
		  System.out.println("fail test case is:-"+result.getName());
		  File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		  FileUtils.copyFile(src,new File("E:\\Selenium_Programs\\Maven_Excel_Project\\src\\test\\resources\\screenexcel.jpg"));
		  System.out.println("capture the screenshot successfully");
	  }
  }


  @DataProvider
  public Object[][] dp() throws IOException 
  {
     ExcelData e= new ExcelData();
     e.excelData("E:\\Selenium_Programs\\Maven_Excel_Project\\MerquryExcel.xlsx.xlsx");
     int rows=e.getRowCount("sheet1");
     int columns=e.getColumnCount("sheet1");
     
     Object[][] userdataexcel=new Object[rows][columns];
     for(int i=0;i<rows;i++)
     {
    	 for(int j=0;j<columns;j++)
    	 {
    		 userdataexcel[i][j]=e.getData("sheet1", i, j);
    	 }
     }
     return userdataexcel;
     
  }
  @BeforeClass
  public void maximizeWindow() 
  {
	  driver.manage().window().maximize();
	  System.out.println("maximize the window successfully");
  }

  @AfterClass
  public void deleteAllCookies() 
  {
	  System.out.println("delete the all cookies successfully");
  }

  @BeforeTest
  public void enterApplicationURL() 
  {
	  driver.get("http://www.newtours.demoaut.com/");
	  System.out.println("enter the application successfully");
  }

  @AfterTest
  public void dbConnectionClose() 
  {
	  System.out.println("close the db connection successfully");
  }

  @BeforeSuite
  public void openBrowser() 
  {
	  System.setProperty("webdriver.chrome.driver", "E:\\Selenium_Java\\Selenium1\\chromedriver_win32(1)\\chromedriver.exe");
	  driver= new ChromeDriver();
	  System.out.println("chrome browser open successfully");
  }

  @AfterSuite
  public void close() 
  {
	  driver.close();
	  System.out.println("close the browser successfully");
  }

}
