package NativeApps;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import java.net.URL;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;


public class AppiumServerIOS {
	
	
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

	public static IOSDriver driver;
	

	public static void main(String[] args) throws Exception {
		
		stopAppiumServer();
		startAppiumServer();
		
		 DesiredCapabilities capabilities = new DesiredCapabilities();
		    capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "");
		    capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9.2");
		    capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
		    capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone Simulator");
		   // capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		    
		    capabilities.setCapability("app", "com.example.apple-samplecode.UICatalog");
			 
		    capabilities.setCapability("automationName", "Appium");
		    capabilities.setCapability("udid","c84c5fd8fb6cd5e842473a1888c511b7cf8d9bef");

		    driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		    
		    driver.scrollTo("Date Picker").click();
		  Thread.sleep(3000);

		stopAppiumServer();
		
	}


}
