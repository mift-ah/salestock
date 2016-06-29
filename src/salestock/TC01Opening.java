package salestock;
/*
Automation ini dibuat dengan bahasa JAVA menggunakan selenium webdriver dan plugin TestNG for Eclipse.
by Miftah Fauzi
*/

import java.io.File;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Augmenter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class TC01Opening {
	
	//menentukan baseURL
	protected static String baseUrl = "http://automationpractice.com/";	
	
	protected static WebDriver driver;
			
	public WebDriver getWebDriver() {
	return driver;
	}
	
	//mengambil screenshot dan menyimpannya dengan nama tertentu
	public void takeScreenshot(String fileName) throws Exception {
		WebDriver driverScreen = new Augmenter().augment(driver);
		File file  = ((TakesScreenshot)driverScreen).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file, new File("/Users/Miftah Fauzi/Documents/automate/" + fileName + ".jpg"));
		}
		    
	@BeforeSuite(alwaysRun = true)
	public void setupBeforeSuite() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		WebDriver.Timeouts timeoutOptions = driver.manage().timeouts();
		timeoutOptions.implicitlyWait(30, TimeUnit.SECONDS);
		timeoutOptions.pageLoadTimeout(30, TimeUnit.SECONDS);
		timeoutOptions.setScriptTimeout(30, TimeUnit.SECONDS);
		}

	@AfterSuite(alwaysRun = true)
		public void setupAfterSuite() {
		driver.quit();
		}	
	}	

