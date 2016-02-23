package NativeApps;


import io.appium.java_client.android.AndroidDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MakeaCallTest {


	@SuppressWarnings("rawtypes")
	public static AndroidDriver driver;

	public static void startAppiumServer() throws Exception{

		CommandLine command = new CommandLine("/Applications/Appium.app/Contents/Resources/node/bin/node");

		command.addArgument("/Applications/Appium.app/Contents/Resources/node_modules/appium/bin/appium.js", false);

		command.addArgument("--address", false);

		command.addArgument("127.0.0.1");

		command.addArgument("--port", false);

		command.addArgument("4723");

		command.addArgument("--no-reset", false);

		DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();

		DefaultExecutor executor = new DefaultExecutor();

		executor.setExitValue(1);

		executor.execute(command, resultHandler);

		Thread.sleep(7000);

	}

		
	//Stopping the Appium Server
	public static void stopAppiumServer() throws Exception{

		Runtime.getRuntime().exec("killall node");

	}
	
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) throws Exception {

		stopAppiumServer();

		startAppiumServer();
		
		DesiredCapabilities capabilites = new DesiredCapabilities();
		
		capabilites.setCapability("device", "Android");
		capabilites.setCapability("deviceName", "Android");
		capabilites.setCapability("platformVersion", "4.3");
		capabilites.setCapability("platformName", "Android");
		
		//capabilites.setCapability("appPackage", "com.android.contacts");
		//capabilites.setCapability("appActivity", "com.android.contacts.activities.PeopleActivity");
		capabilites.setCapability("appPackage", "com.android.dialer");
		capabilites.setCapability("appActivity", "com.android.dialer.DialtactsActivity");
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilites);
		driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);

		
		WebElement tableLayout = driver.findElement(By.id("com.android.dialer:id/dialpad"));
		
		//01264889469 - Home
		tableLayout.findElement(By.id("com.android.dialer:id/zero")).click();
		tableLayout.findElement(By.id("com.android.dialer:id/one")).click();
		tableLayout.findElement(By.id("com.android.dialer:id/two")).click();
		tableLayout.findElement(By.id("com.android.dialer:id/six")).click();
		tableLayout.findElement(By.id("com.android.dialer:id/four")).click();
		tableLayout.findElement(By.id("com.android.dialer:id/eight")).click();
		tableLayout.findElement(By.id("com.android.dialer:id/eight")).click();
		tableLayout.findElement(By.id("com.android.dialer:id/nine")).click();
		tableLayout.findElement(By.id("com.android.dialer:id/four")).click();
		tableLayout.findElement(By.id("com.android.dialer:id/six")).click();
		tableLayout.findElement(By.id("com.android.dialer:id/nine")).click();
		driver.findElement(By.id("com.android.dialer:id/dialButton")).click();
		
		Thread.sleep(3000);	
		
		// should start ringing the number
		String callstate = driver.findElement(By.id("com.android.phone:id/callStateLabel")).getText();
		// should be DIALLING
		System.out.println("Call State is : " + callstate);
		
		WebDriverWait wait = new WebDriverWait(driver,5);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id("com.android.phone:id/endButton")));
		element.click();
		
		//driver.findElement(By.id("com.android.phone:id/endButton")).click();
		Thread.sleep(3000);
		driver.quit();

		stopAppiumServer();


	}
}