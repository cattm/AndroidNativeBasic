package NativeApps;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class AppiumServerAndroid {

	@SuppressWarnings("rawtypes")
	public static AndroidDriver driver;
	
	//Starting the Appium Server

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


	
	public static void main(String[] args) throws Exception{

		stopAppiumServer();

		startAppiumServer();

		DesiredCapabilities capabilities=new DesiredCapabilities();

		capabilities.setCapability("device", "Android");
		capabilities.setCapability("deviceName", "Android");
		capabilities.setCapability("platformVersion", "4.3");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("automationName", "Appium");


	

		capabilities.setCapability("appPackage","io.selendroid.testapp");
		capabilities.setCapability("appActivity","io.selendroid.testapp.HomeScreenActivity");

		@SuppressWarnings("rawtypes")
		AndroidDriver driver=new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);

		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);

		driver.findElement(By.id("io.selendroid.testapp:id/my_text_field")).sendKeys("Hello Appium");

		Thread.sleep(3000);

		driver.quit();

		stopAppiumServer();

	}
}
