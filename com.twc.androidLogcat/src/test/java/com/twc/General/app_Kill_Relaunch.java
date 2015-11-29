package com.twc.General;

import com.twc.driver.Driver;

public class app_Kill_Relaunch extends Driver {

	public static void Kill_realaunch() throws Exception {
		// Thread.sleep(2000);
		
		// Close the app
		Ad.closeApp();
		Thread.sleep(1000);
		System.out.println("App closed successfully");

		// Relaunch the app
		Ad.launchApp();
		System.out.println("App launched successfully");
		Thread.sleep(1000);

	}

}
