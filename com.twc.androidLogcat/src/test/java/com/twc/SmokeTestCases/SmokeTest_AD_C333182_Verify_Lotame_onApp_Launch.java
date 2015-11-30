package com.twc.SmokeTestCases;


import io.appium.java_client.MobileElement;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import atu.testng.reports.ATUReports;

import com.twc.General.Swipe;
import com.twc.driver.Driver;
import com.twc.driver.PropertyFile;

public class SmokeTest_AD_C333182_Verify_Lotame_onApp_Launch extends Driver {
	
	public static String ids = null;
	
	@SuppressWarnings({ "unused", "unchecked", "deprecation" })
//	public static void main(String[] args) throws IOException, InterruptedException {
	public void Verify_LotameCall_onapp_launch_test() throws InterruptedException, ParseException, IOException
	{
		//reading file from Property file
		 Driver.property();
			PropertyFile.property();

	System.out.println("Verification of Lotame Call Test_Case Started");
		
		String adbPath = properties.getProperty("adbPath");
		String[] str ={"/bin/bash", "-c", adbPath+" shell setprop log.tag.TwcAd DEBUG"};
		Process p = Runtime.getRuntime().exec(str);
		
		System.out.println("Debug command is done");
	
		String[] str1 ={"/bin/bash", "-c", adbPath+" -d logcat -v time >> "+properties.getProperty("LogFilePath")};
		Process p1 = Runtime.getRuntime().exec(str1);
		System.out.println("Writing App logs to LogFile");
		
		ATUReports.add("Launch the app",false);
		try {
		//Wait for 20 sec for element presence
		WebDriverWait wait = new WebDriverWait(Ad, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.weather.Weather:id/temperature")));
		
		//Temperature  Element
		MobileElement el = (MobileElement) Ad.findElementById("com.weather.Weather:id/temperature");
		System.out.println("Temp : "+el.getText());

		Swipe.swipe();
		Swipe.swipe();
		} catch(Exception e){
//			System.out.println("Exception message : "+e);
		}
		
		Thread.sleep(2000);

		
		MobileElement AdEle =(MobileElement) Ad.findElementById("com.weather.Weather:id/ad_view_holder");

		WebDriverWait wait1 = new WebDriverWait(Ad, 4);
		
		wait1.until(ExpectedConditions.visibilityOf(AdEle));
	
		if(AdEle.isDisplayed())
		{
			System.out.println("Feed_1 Ad is present");
			ATUReports.add("Feed_1 Ad is present",false);

		}

		Thread.sleep(2000);
				
		String pubsg=null;

		//Reading the log file for feed_1 to verify SG value
			
				BufferedReader r = new BufferedReader(new FileReader(properties.getProperty("LogFilePath")));

				String line = "";
				String allLine = "";

				while((line=r.readLine()) != null)
				{
					System.out.println("Sys data is ::"+line);
				}

				String FilePath = properties.getProperty("LogFilePath");


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
					
					String[] arrays;
					String[] key;
					List<String> pubad_sgvalues = new ArrayList<String>();
					
					if(sb.toString().contains("slotName=weather.feed1")){
						String req = sb.toString().substring( sb.toString().lastIndexOf("slotName=weather.feed1"));
						req = req.substring(req.indexOf(",")+1,req.indexOf("}"));
						System.out.println("Verifing the "+req);
						arrays = req.split(" ");
						
						for(String keys : arrays){

							if(keys.contains("=")){
								 key = keys.split("=");
//								System.out.println("keys are :: "+key[0] + "---"+key[1]);
								if(key[0].contains("sg"))
								{															
									pubsg = key[1].toString();																		
									pubsg= pubsg.substring(0,pubsg.lastIndexOf(",")) ;
									pubsg=pubsg.replaceAll(",", ", ");
					    			pubad_sgvalues.add(pubsg);					    			
									break;
								} 
							}
						}
						System.out.println("pubad_sg values are :: " + pubad_sgvalues.toString());
						
					}
					
		               Thread.sleep(2000);
						// Capturing the Lotame Call Data (bcp.crwdcntrl.net)
						if(sb.toString().contains("response from server is {"+'"'+"Profile")){
						String lotame= null;
						if(sb.toString().contains("response from server is {"+"\"Profile\""))
								{
					 lotame = sb.toString().substring(sb.toString().lastIndexOf("response from server is {"+'"'+"Profile"));
	    			 lotame = lotame.substring(lotame.indexOf("Audiences")+12,lotame.indexOf("}}}")+1);
					 System.out.println("Verifing the lotame call "+lotame);
							
								}
		
						Thread.sleep(2000);
						//Reading the Lotame Call from LogFile [From Audiences tag]					
						JSONParser parser = new JSONParser();
						Object obj = parser.parse(lotame.toString());
						JSONObject jsonObject = (JSONObject) obj;
						JSONArray Audience = (JSONArray) jsonObject.get("Audience");
						 
						 List<String> idvalues = new ArrayList<String>();
						if (Audience!= null) {
				    			Iterator<JSONObject> AudienceIterator = Audience.iterator();
				    			while (AudienceIterator.hasNext()) {
				    			    JSONObject AudienceObject = (JSONObject) AudienceIterator.next();
				    			   String id = AudienceObject.get("id").toString();
				    			   System.out.println("id values : " + id);
				    			   idvalues.add(id);
				    			}
				    			ATUReports.add("Verify Audience values from Lotame API call",false);
				    			System.out.println("Lotame call Audience values are :: " + idvalues.toString());
				    			String actual = idvalues.toString().replace("[", "").replace("]", "");
				    			ATUReports.add("Lotame API Call Audience values are :: "+actual,false);
//				    			ATUReports.add("Audience values are present in Lotame Call",false);
				    			
				    			ATUReports.add("Verify PubAd_SG values from PubAd call",false);
				    			System.out.println("pubad_sg values are :: " + pubad_sgvalues.toString());
				    			String expected = pubad_sgvalues.toString().replace("[", "").replace("]", "");
				    			ATUReports.add("PubAd_SG values are :: "+expected,false);
//				    			ATUReports.add("PubAd_SG values are present ",false);
				    			
               //Asserting the PubAd_SG values with Lotame Call id values of Audiences object of JSON Object
					Assert.assertEquals(actual, expected);
					System.out.println("PubAd_SG values and Lotame call Audience values are matched");
					ATUReports.add("PubAd_SG and Lotame call values are matched",false);				    
						}
					}
					
					br.close();

				} catch (Exception e) {
					e.printStackTrace();
				}

				System.out.println("Lotame Call test case is done");
        }
    }
