package com.globalsearch;

import java.io.IOException;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.parent.BaseInit;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class searchProduct extends BaseInit {
	public static String text = "apple 12";

	@BeforeTest
	public void search() throws IOException, InterruptedException {
		startUp();
		Thread.sleep(3000);
		verifyIdPropVal("id_search").clear();
		Thread.sleep(2000);
		verifyIdPropVal("id_after_search").sendKeys(text);
		driver.pressKey(new KeyEvent(AndroidKey.ENTER));
	}

	//This methid is for apply filters
	public void applyFilters() throws InterruptedException {
		driver.findElement(By.id("s-all-filters")).click();
		Thread.sleep(5000);
		try { 
			 driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Smartphones\"))"); 
			 driver.findElement(By.id("n/1805560031")).click(); 
			}catch(Exception e) {
				 System.out.println("We got an error scrolling!"); 
		 }
		 
		Thread.sleep(3000);
		driver.findElement(By.id("s-all-filters")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//android.view.View[@content-desc=\"Price: High to Low\"]/android.view.View")).click();
	}
	@Test
	public void getSearchList() throws InterruptedException {
		
		try { 
			driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Smartphones\"))");
			driver.findElement(By.id("n/1805560031")).click(); 
		}catch(Exception e) {
			System.out.println("We got an error scrolling!"); 
		 }
		 
		Thread.sleep(3000);
		//driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[2]/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ViewAnimator/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[1]/android.view.View/android.view.View[3]")).click();
		driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[2]/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ViewAnimator/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[1]/android.view.View/android.view.View[3]/android.widget.TextView[1]")).click();
		String s1 = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[2]/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ViewAnimator/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[1]/android.view.View/android.view.View[3]/android.widget.TextView[1]")).getText();
		System.out.println("First item from list >>>>>>" + s1);
	}
}
