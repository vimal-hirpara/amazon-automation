package com.utility;

import org.openqa.selenium.By;

import com.parent.BaseInit;

public class MyMethods extends BaseInit {
	
	public String search(String str) {
		driver.findElement(By.id("")).sendKeys(str);
		return str;
		
	}

}
