package com.twc.AD_TestSuite;


import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import atu.testng.reports.ATUReports;
import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;

import com.twc.AppiumAutoStart.Capabilities_android;
import com.twc.AppiumAutoStart.movingFiles;
import com.twc.General.DeleteFile;
import com.twc.General.File_Exist;
import com.twc.General.app_Kill_Relaunch;
import com.twc.General.setAddress_Location;
import com.twc.General.toKnowBuildVersion;
import com.twc.SmokeTestCases.SmokeTest_AD_C333175_HourlyScroll;
import com.twc.SmokeTestCases.SmokeTest_AD_C333180_Daily;
import com.twc.SmokeTestCases.SmokeTest_AD_C333172_CleanLaunch;
import com.twc.SmokeTestCases.SmokeTest_AD_C333174_FactualCall;
import com.twc.SmokeTestCases.SmokeTest_AD_C333173_Verify_WeatherFX_ApiCall_RE;
import com.twc.SmokeTestCases.SmokeTest_AD_C333174_FactualCall_RE;
import com.twc.SmokeTestCases.SmokeTest_AD_C333175_Hourly;
import com.twc.SmokeTestCases.SmokeTest_AD_C333175_Hurricane;
import com.twc.SmokeTestCases.SmokeTest_AD_C333176_Map;
import com.twc.SmokeTestCases.SmokeTest_AD_C333177_News;
import com.twc.SmokeTestCases.SmokeTest_AD_C333179_Verify_PullToRefresh;
import com.twc.SmokeTestCases.SmokeTest_AD_C333180_10Day_2;
import com.twc.SmokeTestCases.SmokeTest_AD_C333180_10Day;
import com.twc.SmokeTestCases.SmokeTest_AD_C333182_Verify_Lotame_onApp_Launch;
import com.twc.SmokeTestCases.SmokeTest_c334143_CleanLaunch_RE;
import com.twc.SmokeTestCases.SmokeTest_AD_C333173_Verify_WeatherFX_ApiCall;
import com.twc.driver.Driver;
import com.twc.driver.PropertyFile;

import org.testng.ITestResult;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import io.appium.java_client.AppiumDriver;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;


@Listeners({ATUReportsListener.class, MethodListener.class })

@SuppressWarnings("unused")
public class Smoke_Test extends Driver{
	
	{
		System.setProperty("atu.reporter.config", "./atu.properties"); 
	}
	
		//Pull to Refresh
	@Test (priority=0, threadPoolSize = 1,invocationCount = 1)
	public void AD_C333179_Verify_AdCall_On_PulltoRefresh() throws ParseException, IOException, InterruptedException
	{
		SmokeTest_AD_C333179_Verify_PullToRefresh pulltorefresh = new SmokeTest_AD_C333179_Verify_PullToRefresh();
		pulltorefresh.Verify_PulltoRefresh();
	}
	
	  //Factual FX API Call == RE
	@Test(priority=1, threadPoolSize = 1,invocationCount = 1)
	public void AD_C333174_FactualCall_On_FreshLaunch_RE() throws Exception {

		SmokeTest_AD_C333174_FactualCall_RE FactualTest_RE = new SmokeTest_AD_C333174_FactualCall_RE();
		FactualTest_RE.verify_facualcal_onfresh_launch();

	}
	//Weather FX API Call == RE
	@Test(priority=2, threadPoolSize = 1,invocationCount = 1)
	public void AD_C333173_Verify_WeatherFX_ApiCall_On_FreshLaunch_RE() throws Exception {

		SmokeTest_AD_C333173_Verify_WeatherFX_ApiCall_RE wfxtg = new SmokeTest_AD_C333173_Verify_WeatherFX_ApiCall_RE();
		wfxtg.verify_WeatherFX_Apicall_On_FreshLaunch();
    }
		
	//LotameAdTargeting_On_AppLaunch 
	@Test(priority=3, threadPoolSize = 1,invocationCount = 1)
	public void AD_C333182_LotameAdTargeting_On_AppLaunch() throws Exception
	{
		SmokeTest_AD_C333182_Verify_Lotame_onApp_Launch LotameAdTarget = new SmokeTest_AD_C333182_Verify_Lotame_onApp_Launch();
		LotameAdTarget.Verify_LotameCall_onapp_launch_test();
	}
	
			
	//Hourly Ad
	@Test(priority=4, threadPoolSize = 1,invocationCount = 1 )
	public void AD_C333175_Verify_Ad_Present_On_HourlyExtended_page() throws Exception {
		
		SmokeTest_AD_C333175_Hourly hourlyExtend = new SmokeTest_AD_C333175_Hourly();
		hourlyExtend.verify_adpresent_onextendedHourly_page();

	}
	
	//10 Day Ad [Daily Ad]
	@Test(priority=5, threadPoolSize = 1,invocationCount = 1)
	public void AD_C333180_Verify_Ad_Present_On_DailyExtended_page() throws Exception {

		SmokeTest_AD_C333180_10Day tendayExtended = new SmokeTest_AD_C333180_10Day();
		tendayExtended.verify_adpresent_onextendedTenday_page();

	}
	
		//Maps page Ad
	@Test(priority=6, threadPoolSize = 1,invocationCount = 1)
	public void AD_C333176_Verify_Ad_Present_On_MapsExtended_page() throws Exception {

		SmokeTest_AD_C333176_Map mapsExtended = new SmokeTest_AD_C333176_Map();
		mapsExtended.verify_adpresent_onextendedMap_page();

	} 
	
	//News Page Ad	
	@Test(priority=7, threadPoolSize = 1,invocationCount = 1)
	public void AD_C333177_Verify_Ad_Present_On_NewsExtended_page() throws Exception {

		SmokeTest_AD_C333177_News newsExtended = new SmokeTest_AD_C333177_News();
		newsExtended.verify_adpresent_onextendedNews_page();

	}
	
	//CleanLaunch_Scroll
	@Test(priority=8, threadPoolSize = 1,invocationCount = 1)
	public void AD_C333172_Verify_AdCalls_On_CleanLaunch() throws Exception {

		SmokeTest_AD_C333172_CleanLaunch cleanLaunch = new SmokeTest_AD_C333172_CleanLaunch();
		cleanLaunch.CleanLaunch_launch();

	}
		
//	===============
	
//	//Weather FX API Call-Full
//	@Test(priority=2, threadPoolSize = 1,invocationCount = 1)
//	public void AD_C333173_Verify_WeatherFX_ApiCall() throws Exception {
//
//		SmokeTest_AD_C333173_Verify_WeatherFX_ApiCall tendayExtended = new SmokeTest_AD_C333173_Verify_WeatherFX_ApiCall();
//		tendayExtended.SmokeTest_WFX();
//   }
//	
//	  //Factual FX API Call
//		@Test(priority=1, threadPoolSize = 1,invocationCount = 1)
//		public void AD_C333174_FactualCall_On_FreshLaunch() throws Exception {
//
//			SmokeTest_AD_C333174_FactualCall FactualTest = new SmokeTest_AD_C333174_FactualCall();
//			FactualTest.Factual_Test();
//
//		}


	@BeforeTest
	public void Capabilities_Launch() throws Exception {
		
		 //Calling the capabilities method
		Capabilities_android cap = new Capabilities_android();
	    cap.dcap();
		
		//Delete the log existed file
		DeleteFile DF = new DeleteFile();
		File_Exist FE = new File_Exist();
		
		if(FE.fileexist()) {
			DF.deleteFile();

		} else {
			System.out.println("File not exist");
		}
	}
	
	@SuppressWarnings("deprecation")
	@BeforeClass
	public void getBuildVersion() throws Exception {

		// Calling the method to know build version of the app class
//		toKnowBuildVersion getBuildVersion = new toKnowBuildVersion();
//		getBuildVersion.moreOptionsClick();

		// Calling the method to know build version of the app class
//	   setAddress_Location sa = new setAddress_Location();
//	  sa.setLocation();
		
		String	AndroidVersion = properties.getProperty("platformVersion");

		String deviceName = properties.getProperty("deviceName");
		
		String buildVersion = properties.getProperty("buildVersion");

		Driver.property();
		
		PropertyFile.property();

		ATUReports.indexPageDescription = "<h1> Android Smoke Test Report </h1> <br/> <h2> On "+ deviceName.toString() +" and version- " +AndroidVersion.toString()+" </h2> <br/> <h2>with build : "+buildVersion+"</h2>";

		ATUReports.add("Setting IndexPageDescription : ",false);

		ATUReports.setAuthorInfo("Appium", "Android_SmokeTest", "Report");

		ATUReports.currentRunDescription = "<h1>Android_Smoke Tests-Detailed Report with Pie Chart</h1>";

	}

	@SuppressWarnings("deprecation")
	@BeforeMethod
	public void App_Kill_ReLaunch() throws Exception {
		
//		ATUReports.add("Killing the app and relaunch the app",false);
//		System.out.println("Killing the app and relaunch the app");
//		app_Kill_Relaunch.Kill_realaunch();

	}
	
	@SuppressWarnings({ "deprecation", "static-access" })
	@AfterSuite
	public void movefiles() throws IOException {
		movingFiles mf = new movingFiles();
		mf.movefiles();
		ATUReports.add("Moving TWC Image and CSS Files.",false);


	}

	
}
