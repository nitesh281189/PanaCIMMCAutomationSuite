package com.PanaMCAutomation.MC_MCConfig;

import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.PanaAutomation.Util.Constants;
import com.PanaAutomation.Util.DataUtil;
import com.PanaAutomation.Base.BaseTest;
import com.aventstack.extentreports.Status;

public class PMReasonGroupCodeAssignMandtory003 extends BaseTest {
	@Test(dataProvider="getData")
	public void testB(Hashtable<String,String> data) throws Exception{
		test.log(Status.INFO, "To check Assign Reason Code Mandatory Field");
		test.log(Status.INFO, "Starting Test Case " + testName);
		
	if(DataUtil.isSkip(testName, xls)|| data.get(Constants.RUNMODE_COL).equals(Constants.RUNMODE_NO)){
			 test.log(Status.SKIP,"Runmode is set to NO");
	throw new SkipException("Runmode is set to No");
	     	}
	    System.out.println("Running LoginTest");
	   ds.executeKeywords(testName, xls,data);
	  
	}
}
