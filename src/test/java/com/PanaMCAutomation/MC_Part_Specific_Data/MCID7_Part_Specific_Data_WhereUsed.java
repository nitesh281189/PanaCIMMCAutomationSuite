package com.PanaMCAutomation.MC_Part_Specific_Data;

import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.PanaAutomation.Base.BaseTest;
import com.PanaAutomation.Util.Constants;
import com.PanaAutomation.Util.DataUtil;
import com.aventstack.extentreports.Status;

public class MCID7_Part_Specific_Data_WhereUsed extends BaseTest {

	@Test(dataProvider = "getData")
	public void testB(Hashtable<String, String> data) throws Exception {
		test.log(Status.INFO, "To Test the 'Where Used' feature for Part Specific Data");
		test.log(Status.INFO, "Starting" + testName);

		if (DataUtil.isSkip(testName, xls) || data.get(Constants.RUNMODE_COL).equals(Constants.RUNMODE_NO)) {
			test.log(Status.SKIP, "Runmode is set to NO");
			throw new SkipException("Runmode is set to No");
		}
		System.out.println("Running TSMType New");
		ds.executeKeywords(testName, xls, data);
	}

}
