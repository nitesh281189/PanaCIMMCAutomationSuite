package com.PanaAutomation.Driver;

import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Properties;

import org.apache.james.mime4j.field.datetime.DateTime;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.PanaAutomation.Keywords.AppKeywords;
import com.PanaAutomation.Util.Constants;
import com.PanaAutomation.Util.Xls_Reader;
import com.aventstack.extentreports.ExtentTest;

import freemarker.core.Environment;

public class DriverScript {
	public Properties envProp;
	public Properties prop;
	public ExtentTest test;
	AppKeywords app;

	public void executeKeywords(String testName, Xls_Reader xls, Hashtable<String, String> testdata) throws Exception {
		int rows = xls.getRowCount(Constants.KEYWORDS_SHEET);
		System.out.println("Rows are" + rows);
		app = new AppKeywords();
		// Send prop to keywords class
		app.setEnvProp(envProp);
		app.setProp(prop);
		// send data
		app.setData(testdata);
		app.setExtentTest(test);
		for (int rNum = 2; rNum <= rows; rNum++) {
			String tcid = xls.getCellData(Constants.KEYWORDS_SHEET, Constants.TCID_COL, rNum);

			if (tcid.equals(testName)) {
				String keyword = xls.getCellData(Constants.KEYWORDS_SHEET, Constants.KEYWORD_COL, rNum);
				String objectKey = xls.getCellData(Constants.KEYWORDS_SHEET, Constants.OBJECT_COL, rNum);
				String dataKey = xls.getCellData(Constants.KEYWORDS_SHEET, Constants.DATA_COL, rNum);
				String proceedOnFail = xls.getCellData(Constants.KEYWORDS_SHEET, Constants.PROCEED_COL, rNum);
				String data = testdata.get(dataKey);
				String Description = xls.getCellData(Constants.KEYWORDS_SHEET, Constants.DESCRIPTION_COL, rNum);
				// System.out.println(tcid+"-----"+keyword+"-------"+objectKey+"--------"+data);
				// System.out.println(prop.getProperty(objectKey));
				// System.out.println(prop.getProperty("env"));
				// System.out.println(prop.getProperty("signinLink"));
				// System.out.println(tcid +" --- "+ keyword+" --- "+
				// prop.getProperty(objectKey)+" --- "+ data);
				// test.log(Status.INFO,tcid +" --- "+ keyword+" --- "+
				// prop.getProperty(objectKey)+" --- "+ data);

				app.setDataKey(dataKey);
				app.setObjectKey(objectKey);
				app.setProceedOnFail(proceedOnFail);
				app.setDescriptionKey(Description);

				/*
				 * if(keyword.equals("openBrowser")) app.openBrowser(); else
				 * if(keyword.equals("navigate")) app.navigate(); else
				 * if(keyword.equals("click")) app.click(); else if(keyword.equals("type"))
				 * app.type(); else if(keyword.equals("validateLogin")) app.validateLogin(); }
				 */

				// Reflections Api
				int iScript = keyword.lastIndexOf('*');
				// String addScript = "";
				if (iScript > 0) {
					String parameter = keyword.substring(iScript+1);
					String nameMethod = keyword.substring(0, iScript);

					Method method;
					method = app.getClass().getMethod(nameMethod, String.class);
					method.invoke(app, parameter);
				} else {
					Method method;
					method = app.getClass().getMethod(keyword);
					method.invoke(app);
				}

			}

		}

		app.assertAll();
	}

	// method.invoke(app);
	public void executeKeywords(String keyword, String obj, String data2) throws Exception {
		String key1 = System.currentTimeMillis() + "";
		// int rows =xls.getRowCount(Constants.KEYWORDS_SHEET);
		// System.out.println("Rows are"+ rows);
		if (this.app == null)
			this.app = new AppKeywords();

		String objectKey = obj;// "obj link to env";
		String dataKey = key1;// "data link to Datasheet xls";
		String proceedOnFail = "Y";// "Y=yes/N=no resume";

		/// Simulate create a list data to fill
		if (envProp.containsKey(obj) == false && prop.containsKey(obj) == false) {
			envProp.setProperty(key1, obj);
			prop.setProperty(key1, obj);
			objectKey = key1;
		}

		Hashtable<String, String> testdata = new Hashtable<String, String>();
		testdata.put(key1, data2);

		/// Default data
		app.setEnvProp(envProp);// Env file
		app.setProp(prop);// Prop file

		/// send data
		app.setData(testdata);// Data sheet from XLS with key/value for [dataKey]
		app.setExtentTest(test);// ???

		app.setDataKey(dataKey);
		app.setObjectKey(objectKey);
		app.setProceedOnFail(proceedOnFail);

		// Reflections Api
		int iScript = keyword.lastIndexOf('*');
		if (iScript > 0) {
			String parameter = keyword.substring(iScript+1);
			String nameMethod = keyword.substring(0, iScript);

			Method method;
			method = app.getClass().getMethod(nameMethod, String.class);
			method.invoke(app, parameter);
		} else {
			Method method;
			method = app.getClass().getMethod(keyword);
			method.invoke(app);
		}
		// app.assertAll();
	}

	public Properties getEnvProp() {
		return envProp;
	}

	public void setEnvProp(Properties envProp) {
		this.envProp = envProp;
	}

	public Properties getProp() {
		return prop;
	}

	public void setProp(Properties prop) {
		this.prop = prop;
	}

	public void setExtentTest(ExtentTest test) {
		this.test = test;
	}

	public void quit() {
		if (app != null)
			app.quit();
	}

}