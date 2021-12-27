package com.PanaMCAutomation.MC_Material_Function;

import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.PanaAutomation.Base.BaseTest;
import com.PanaAutomation.Util.Constants;
import com.PanaAutomation.Util.DataUtil;
import com.aventstack.extentreports.Status;

public class MCID1523_MaterialFunction_ReceiveRetryReprintAllPrinting extends BaseTest {

	@Test(dataProvider = "getData")
	public void testB(Hashtable<String, String> data) throws Exception {
		test.log(Status.INFO, "To verify functionlity of Retry Reprint All Printing");
		test.log(Status.INFO, "Starting Test Case " + testName);

		if (DataUtil.isSkip(testName, xls) || data.get(Constants.RUNMODE_COL).equals(Constants.RUNMODE_NO)) {
			test.log(Status.SKIP, "Runmode is set to NO");
			throw new SkipException("Runmode is set to No");
		}
		System.out.println("Running MCID1523: Receive Retry Reprint All Printing Test");
		ds.executeKeywords(testName, xls, data);
	}

}