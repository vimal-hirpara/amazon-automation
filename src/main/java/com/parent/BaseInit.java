package com.parent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;


import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;


public class BaseInit {
	public static AndroidDriver<MobileElement> driver = null;
	AppiumDriverLocalService service;
	static Properties data = new Properties();
	
	public void startUp() throws IOException, InterruptedException{
		startServer(); // To start appium server
		if(driver == null){
			FileInputStream fi = new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/properties/objects.properties");
			data.load(fi);
			
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability("deviceName", "My Phone");
			caps.setCapability("udid", data.getProperty("udid")); // Give Device ID of // your mobile phone
			caps.setCapability("platformName", "Android");
			caps.setCapability("platformVersion", data.getProperty("platformVersion"));
			caps.setCapability("appPackage", data.getProperty("appPackage")); //Give app package name
			caps.setCapability("appActivity", data.getProperty("appActivity")); // Give splash screen/home screen activity to start app
			caps.setCapability("noReset", "true");
			caps.setCapability("unicodeKeyboard", "true");                                     
			caps.setCapability("resetKeyboard", "true");
			
			try {
				driver = new AndroidDriver<MobileElement>(new URL(data.getProperty("url")), caps);
			} catch (MalformedURLException e) {
				System.out.println(e.getMessage());
			}
		}
		
	}
	
	public void startServer() {
		AppiumServiceBuilder builder = new AppiumServiceBuilder();
		// Tell serviceBuilder where Appium is installed. Or set this path in an environment variable named APPIUM_PATH
		builder.withAppiumJS(new File("/usr/local/lib/node_modules/appium"));
		//service.stop();
		if (service != null && service.isRunning()) {
			service.stop();
		}
		service = AppiumDriverLocalService.buildService(builder);
		service.start();
	}

	@AfterTest
	public void stopServer() {
		service.stop();
	}
	//verify by id method
	public static MobileElement verifyIdPropVal(String propIdKey) throws IOException {

		try {
			return (MobileElement) driver.findElement(By.id(data.getProperty(propIdKey)));

		} catch (Throwable t) {
			t.printStackTrace();
			System.out.println("ID Key not found in the properties file" + "--" + propIdKey);
			return null;
		}
	}
}
