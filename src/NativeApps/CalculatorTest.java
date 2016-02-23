package NativeApps;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CalculatorTest {
	
	
	@SuppressWarnings("rawtypes")
	public static AndroidDriver driver;
	public WebElement frameLayout;
	
	

	
	@SuppressWarnings("rawtypes")
	@BeforeMethod
	public void setUp() throws MalformedURLException{
		
		DesiredCapabilities capabilites = new DesiredCapabilities();
		
		capabilites.setCapability("device", "Android");
		capabilites.setCapability("deviceName", "Android");
		capabilites.setCapability("platformVersion", "4.3");
		capabilites.setCapability("platformName", "Android");
		
		capabilites.setCapability("appPackage", "com.android.calculator2");
		capabilites.setCapability("appActivity", "com.android.calculator2.Calculator");
		
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilites);
		driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
		
		frameLayout = driver.findElement(By.className("android.widget.FrameLayout"));
		
		boolean found = frameLayout.findElement(By.id("com.android.calculator2:id/clear")).isDisplayed();
		if (found) {
			frameLayout.findElement(By.id("com.android.calculator2:id/clear")).click();
		} else {
			frameLayout.findElement(By.id("com.android.calculator2:id/del")).click();
		}
			
	}
	
	@Test(priority = 1)
	public void add(){
		
		frameLayout.findElement(By.id("com.android.calculator2:id/digit7")).click();
		frameLayout.findElement(By.id("com.android.calculator2:id/plus")).click();
		frameLayout.findElement(By.id("com.android.calculator2:id/digit5")).click();
		frameLayout.findElement(By.id("com.android.calculator2:id/equal")).click();
	
		String text = frameLayout.findElement(By.className("android.widget.EditText")).getText();
		Assert.assertEquals(text, "12");
		
		
	}
	
	
	@Test(priority = 2)
	public void sub(){
		
		frameLayout.findElement(By.id("com.android.calculator2:id/digit7")).click();
		frameLayout.findElement(By.id("com.android.calculator2:id/minus")).click();
		frameLayout.findElement(By.id("com.android.calculator2:id/digit5")).click();
		frameLayout.findElement(By.id("com.android.calculator2:id/equal")).click();
		String text = frameLayout.findElement(By.className("android.widget.EditText")).getText();
		Assert.assertEquals(text, "2");
	}
	
	@Test(priority = 3)
	public void mult(){
		
		frameLayout.findElement(By.id("com.android.calculator2:id/digit7")).click();
		frameLayout.findElement(By.id("com.android.calculator2:id/mul")).click();
		frameLayout.findElement(By.id("com.android.calculator2:id/digit5")).click();
		frameLayout.findElement(By.id("com.android.calculator2:id/equal")).click();
		
		String text = frameLayout.findElement(By.className("android.widget.EditText")).getText();
		
		Assert.assertEquals(text, "35");
		
	}
	
	@Test(priority = 4)
	public void div(){
		
		frameLayout.findElement(By.id("com.android.calculator2:id/digit7")).click();
		frameLayout.findElement(By.id("com.android.calculator2:id/div")).click();
		frameLayout.findElement(By.id("com.android.calculator2:id/digit7")).click();
		frameLayout.findElement(By.id("com.android.calculator2:id/equal")).click();
		
		String text = frameLayout.findElement(By.className("android.widget.EditText")).getText();
		
		//System.out.println("MC Text = " + text);
	
		Assert.assertEquals(text, "1");
	}
	
	@AfterMethod
	public void tearDown(){
		
		driver.quit();
	}

}
