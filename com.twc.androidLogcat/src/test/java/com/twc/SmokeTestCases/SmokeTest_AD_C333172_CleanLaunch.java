package com.twc.SmokeTestCases;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import atu.testng.reports.ATUReports;

import com.twc.General.Swipe;
import com.twc.driver.Driver;
import com.twc.driver.PropertyFile;


@SuppressWarnings("unused")
public class SmokeTest_AD_C333172_CleanLaunch extends Driver{


	@SuppressWarnings({ "unused", "resource", "unchecked", "deprecation" })
	public void CleanLaunch_launch() throws Exception
//	public static void main(String[] args) throws IOException
	{
	
		Driver.property();
		PropertyFile.property();
		
		//Scroll the app
		String originalContext = Ad.getContext();
		Ad.context("NATIVE_APP");	

		String[] str ={"/bin/bash", "-c", "/Users/monocept/Documents/adt-bundle-mac-x86_64-20130522/sdk/platform-tools/adb shell setprop log.tag.TwcAd DEBUG"};
		Process p = Runtime.getRuntime().exec(str);	
		System.out.println("Debug command is done");
	
		String[] str1 ={"/bin/bash", "-c", "/Users/monocept/Documents/adt-bundle-mac-x86_64-20130522/sdk/platform-tools/adb -d logcat -v time >> "+properties.getProperty("LogFilePath")};
		Process p1 = Runtime.getRuntime().exec(str1);
        System.out.println("Writing App logs to LogFile");
        
        ATUReports.add("Launch the app",false);
		Thread.sleep(1000);
		
		Dimension dimensions = Ad.manage().window().getSize();
//		 System.out.println("dimensions :: "+dimensions);
		
//		try{
//		//Wait for 20 sec for element presence
//		WebDriverWait wait = new WebDriverWait(Ad, 10);
//		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.weather.Weather:id/temperature")));
//		}catch(Exception e){
			//System.out.println("Error message :: "+e);
//			}
		 
		Thread.sleep(1000);
		ATUReports.add("Scroll the app to verify ad calls(BB,feed_0,feed_1 to feed_n)",false);
		System.out.println("Searching for SkiSlopes module to stop the scrolling");	
		
		for(int FeedValue=1;FeedValue<=12;FeedValue++)
		{
			
			WebElement skiSlopes = null;
//			MobileElement skiSlopes = null;

			try {
				skiSlopes = (MobileElement) Ad.findElementById("com.weather.Weather:id/ski_title");	
				
			    } catch (Exception e) {
				    // System.out.println(e);	
			      }

			if (skiSlopes!= null && skiSlopes.isDisplayed()) {
				
				System.out.println("Ski Slopes module is present");				
			     Swipe.swipe();
					break;

			} else {
				System.out.println("Ski Slopes module is NOT present,scrolling down");				
				Swipe.swipe();
			}
		}
		
		BufferedReader r = new BufferedReader(new FileReader(properties.getProperty("LogFilePath")));
		
					String line = "";
					String allLine = "";
		
					while((line=r.readLine()) != null)
					{
						System.out.println("Sys data is ::"+line);
					}
		
					String FilePath = properties.getProperty("LogFilePath");
		
					Map<String, String> mapkeys = new HashMap<String, String>();
		
					try {
						FileInputStream fstream = new FileInputStream(FilePath);
						BufferedReader br = new BufferedReader(new InputStreamReader(
								fstream));
						String strLine;
						// / read log line by line ------ strLine = br.readLines(6, 10); /
						StringBuffer sb = new StringBuffer("");
						while ((strLine = br.readLine()) != null) {
		
							sb.append(strLine);
		
						}
						
						ATUReports.add("Get the Feed calls data from Log File",false);
						for(int FeedValue=0;FeedValue<=6;FeedValue++)
							{
						//verify Feed_0 Vlaues
						if(FeedValue==0)
						{
							String req1 = sb.toString().substring(sb.toString().lastIndexOf("URL=https://pubads.g.doubleclick.net/gampad/adx?iu=%2F7646%2Fapp_android_us%2Fdisplay%2Fbb"));
							String	req = req1.substring(req1.indexOf("URL"),req1.indexOf("lang")+4);
							System.out.println("BB call value :: " + req);
							
							if(req1.contains("URL=https://pubads.g.doubleclick.net/gampad/adx?iu=%2F7646%2Fapp_android_us%2Fdisplay%2Fbb"))
							{
								System.out.println("Branded Background call is present");
								ATUReports.add("Branded Background call is present",false);
								
							}else
							{
								System.out.println("Branded Background call is NOT present");
								ATUReports.add("Branded Background call is NOT present",false);
								
							}
		
						}else
							//	Getting and taking feed cals from last				
							if(sb.toString().contains("slotName=weather.feed"+FeedValue)){
								String req1 = sb.toString().substring( sb.toString().lastIndexOf("slotName=weather.feed"+FeedValue));
								String	req = req1.substring(req1.indexOf(",")+1,req1.indexOf("}"));
//								System.out.println("Request is ::"+req1);
								if(req1.contains("slotName=weather.feed"+FeedValue))
								{
									System.out.println("Verified Feed_"+FeedValue+" call is present");
									ATUReports.add("Verified Feed_"+FeedValue+" call is present",false);
									
								}
							}
		
					}
						System.out.println("Verify AdCalls_on_Clean launch Case Ended");
		
						br.close();
		
					} catch (Exception e) {
						e.printStackTrace();
		
					}
		
		
				
	}


	}









