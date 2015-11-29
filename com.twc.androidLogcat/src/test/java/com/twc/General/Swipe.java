package com.twc.General;

import org.openqa.selenium.Dimension;

import com.twc.driver.Driver;

public class Swipe extends Driver {

	public static void swipe(){
	
		Dimension dimensions = Ad.manage().window().getSize();
		// System.out.println("dimensions :: "+dimensions);
//		int scrollStart = 2300;
//		int scrollEnd = 50;
//		Ad.swipe(0, scrollStart, 0, scrollEnd, 2000);
		Ad.swipe(0, 2300, 0, 60, 2000);
	}
}
