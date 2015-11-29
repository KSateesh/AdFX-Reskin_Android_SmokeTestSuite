package com.twc.General;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;

import com.twc.driver.Driver;

public class wfxtg extends Driver {

	@SuppressWarnings("unused")
	public static List<String> getValues(String req, String values) {

		String[] arrays;
		String[] key;
		String zcs = null;
		String hzcs = null;
		String nzcs = null;
		List<String> pubad_ZCSvalues = new ArrayList<String>();
		List<String> pubad_HZCSvalues = new ArrayList<String>();
		List<String> pubad_NZCSvalues = new ArrayList<String>();

		if (values.equals("zcs")) {

			// System.out.println("Verifing the "+req);
			arrays = req.split(" ");
			for (String keys : arrays) {
				// System.out.println("Keys ::"+keys);
				if (keys.contains("=")) {
					key = keys.split("=");
					// System.out.println("keys are :: "+key[0] + "---"+key[1]);
					if (key[0].contains(values)) {
						values = key[1].toString();
						values = values.substring(0, values.lastIndexOf(","));
						pubad_ZCSvalues.add(values);
						// System.out.println("values: "+ values);

						break;
					}

				}
			}
//			System.out.println("pubad_ZCSvalues values are :: "+ pubad_ZCSvalues.toString());			
			return pubad_ZCSvalues;
		}

		if (values.equals("hzcs")) {

			// System.out.println("Verifing the "+req);
			arrays = req.split(" ");

			for (String keys : arrays) {
				// System.out.println("Keys ::"+keys);
				if (keys.contains("=")) {
					key = keys.split("=");
					// System.out.println("keys are :: "+key[0] + "---"+key[1]);
					if (key[0].contains(values)) {
						values = key[1].toString();
						values = values.substring(0, values.lastIndexOf(","));
						// System.out.println("values: "+ values);
						pubad_HZCSvalues.add(values);

						break;
					}

				}
			}
//			System.out.println("pubad_HZCSvalues values are :: "+ pubad_HZCSvalues.toString());
			return 	pubad_HZCSvalues;
		}

		if (values.equals("nzcs")) {			
			arrays = req.split(" ");
			for (String keys : arrays) {
				// System.out.println("Keys ::"+keys);
				if (keys.contains("=")) {
					key = keys.split("=");
					// System.out.println("keys are :: "+key[0] + "---"+key[1]);
					if (key[0].contains(values)) {
						values = key[1].toString();
						values = values.substring(0, values.lastIndexOf(","));
						// System.out.println("values: "+ values);
						pubad_NZCSvalues.add(values);
						break;
					}

				}
			}
//			System.out.println("pubad_NZCSvalues values are :: "+ pubad_NZCSvalues.toString());
			return 	pubad_NZCSvalues;
		}
		
		if (values.equals("zip")) {            
			arrays = req.split(" ");
			for (String keys : arrays) {			
				if (keys.contains("=")) {
					key = keys.split("=");
					// System.out.println("keys are :: "+key[0] + "---"+key[1]);
					if (key[0].contains(values)) {
						values = key[1].toString();
						values = values.substring(0, values.lastIndexOf(","));
						break;
					}

				}
			}
			System.out.println("Zip value is :: "+ values);							
		}
		return null;

	}
}
