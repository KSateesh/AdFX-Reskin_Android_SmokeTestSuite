package com.twc.SmokeTestCases;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSElement;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import atu.testng.reports.ATUReports;

import com.twc.General.Swipe;
import com.twc.General.app_Kill_Relaunch;
import com.twc.driver.Driver;

@SuppressWarnings("unused")
public class SmokeTest_AD_C333175_Hourly extends Driver {

	@SuppressWarnings({ "unused", "deprecation" })
	public void verify_adpresent_onextendedHourly_page() throws Exception {

		// app kill and relaunch the app
		// app_Kill_Relaunch.Kill_realaunch();

		String originalContext = Ad.getContext();
		Ad.context("NATIVE_APP");

		ATUReports.add("Launch the app", false);

		// To get the dimensions of the screen
		Dimension dimensions = Ad.manage().window().getSize();
		// System.out.println("dimensions :: "+dimensions);

		Thread.sleep(2000);

		System.out.println("Searching for Hourly module");
		ATUReports.add("Scroll to Hourly module", false);

		for (int i = 0; i < dimensions.getHeight(); i++) {

			WebElement hourly = null;

			try {
				hourly = Ad.findElementById("com.weather.Weather:id/hourly_title_textview");
				// hourly = Ad.findElementByName("HOURLY");

			} catch (Exception e) {
				// System.out.println(e);
			}

			if (hourly != null && hourly.isDisplayed()) {

				System.out.println("Hourly module is displayed and tap on the same");
				ATUReports.add("Hourly module is displayed and tap on the same",false);

				Ad.findElementById("com.weather.Weather:id/hourly_title_textview").click();
				
				// Ad.findElementByName("HOURLY").click();

				MobileElement extendeHourlyAd = (MobileElement) Ad.findElementByXPath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[3]/android.widget.FrameLayout[1]/android.view.View[1]");

				WebDriverWait wait1 = new WebDriverWait(Ad, 4);

				wait1.until(ExpectedConditions.visibilityOf(extendeHourlyAd));

				if (extendeHourlyAd.isDisplayed()) {
					
					System.out.println("Ad is present on Extended_Hourly page");
					ATUReports.add("Ad is present on Extended_Hourly page",false);

					Thread.sleep(2000);

					// Clicking back button
					Ad.findElementByAccessibilityId("Navigate up").click();
					break;
				}

			} else {

				System.out.println("Hourly module is not present and scrolling down");

				Swipe.swipe();

			}

		}
	}
}
