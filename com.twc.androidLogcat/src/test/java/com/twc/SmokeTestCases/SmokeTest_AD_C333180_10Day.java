package com.twc.SmokeTestCases;

import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSElement;

import java.util.HashMap;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import atu.testng.reports.ATUReports;

import com.twc.General.Swipe;
import com.twc.General.app_Kill_Relaunch;
import com.twc.driver.Driver;

public class SmokeTest_AD_C333180_10Day extends Driver {

	@SuppressWarnings({ "unused", "deprecation" })
	public void verify_adpresent_onextendedTenday_page() throws Exception {

		// app kill and relaunch the app
		app_Kill_Relaunch.Kill_realaunch();

		String originalContext = Ad.getContext();
		Ad.context("NATIVE_APP");

		ATUReports.add("Launch the app", false);
		// To get the dimensions of the screen
		Dimension dimensions = Ad.manage().window().getSize();
		// System.out.println("dimensions :: "+dimensions); //2

		System.out.println("Searching for Daily module");
		ATUReports.add("Scroll to Daily module", false);
		
		for (int i = 0; i < dimensions.getHeight(); i++) {

			WebElement tenday = null;

			try {
				tenday = Ad.findElementById("com.weather.Weather:id/tenday_title");
				// tenday = Ad.findElementByName("Daily");

			} catch (Exception e) {
				// System.out.println(e);
			}

			if (tenday != null && tenday.isDisplayed()) {
				System.out.println("Daily module is present and tap on 15Days button");
				ATUReports.add("Daily module is present and tap on 15Days button",false);

				try {
					Ad.findElementById("com.weather.Weather:id/daily_more")
							.click();
					// Ad.findElementByName("15 DAYS").click();

				} catch (NoSuchElementException e) {
					Ad.findElementByName("WEEKEND FORECAST").click();
				}

				MobileElement AdEle = (MobileElement) Ad.findElementByXPath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.view.View[1]");
				WebDriverWait wait1 = new WebDriverWait(Ad, 6);
				wait1.until(ExpectedConditions.visibilityOf(AdEle));

				if (AdEle.isDisplayed()) {
					System.out.println("Ad is present on Extended_Daily_page");
					ATUReports.add("Ad is present on Extended_Daily_page",false);
					Thread.sleep(2000);

					// Clicking back button
					Ad.findElementByAccessibilityId("Navigate up").click();
					break;
				} else {
					System.out.println("Ad is NOT present on Extended_Daily_page");
				}

			} else {
				System.out.println("Daily module is not present and scrolling down");

				Swipe.swipe();

			}
		}

	}

}
