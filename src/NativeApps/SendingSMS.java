package NativeApps;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

public class SendingSMS {

	@SuppressWarnings("rawtypes")
	public static AndroidDriver driver;
	
	
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) throws MalformedURLException {


		
		DesiredCapabilities capabilites = new DesiredCapabilities();
		
		capabilites.setCapability("device", "Android");
		capabilites.setCapability("deviceName", "Android");
		capabilites.setCapability("platformVersion", "4.3");
		capabilites.setCapability("platformName", "Android");
		
		capabilites.setCapability("appPackage", "com.android.mms");
		capabilites.setCapability("appActivity", "com.android.mms.ui.ConversationList");
		
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilites);
		driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
	
		driver.findElement(By.id("com.android.mms:id/action_compose_new")).click();
		
		driver.findElement(By.id("com.android.mms:id/recipients_editor")).sendKeys("07801510569");
		
		driver.findElement(By.id("com.android.mms:id/embedded_text_editor")).sendKeys("Sending message through Appium");
		
		driver.findElement(By.id("com.android.mms:id/send_button_sms")).click();

	}
}