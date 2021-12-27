package com.PanaAutomation.Base;


import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.PanaAutomation.Base.BaseTest;
import com.PanaAutomation.Util.Constants;
import com.PanaAutomation.Util.DataUtil;
import com.aventstack.extentreports.Status;

public class GUITool extends BaseTest {

	@Test(dataProvider = "getData")
	public void testB(Hashtable<String, String> data) 
	{
		test.log(Status.INFO, "Starting" + testName);

		if (DataUtil.isSkip(testName, xls) || data.get(Constants.RUNMODE_COL).equals(Constants.RUNMODE_NO)) 
		{
			test.log(Status.SKIP, "Runmode is set to NO");
			throw new SkipException("Runmode is set to No");
		}
		System.out.println("Running GUITool");
				
		///ds.executeKeywords(testName, xls, data);
		
		System.out.println("Connected to GUI Tool...");
		Scanner input = new Scanner(System.in);
		while (input.hasNext()) 
		{
			String str = input.nextLine();
			if(str.equals("#echo"))
			{
				System.out.println(str);
			}
			else if(str.equals("quit"))
		    {
		    	System.out.println("GUITool exitting.. "+str);
		    	System.exit(0);
		    }
		    else
		    {
		    	try {
		    		ArrayList<String> params = new ArrayList<String>();
			    	String strKey = "[Hien@key]"; 
			    	while(true)
			    	{
			    		int idx = str.indexOf(strKey);
			    		if(idx >= 0)
			    		{
			    			params.add(str.substring(0,idx));
			    			str = str.substring(idx + strKey.length(), str.length());
			    		}
			    		else 
			    		{
			    			break;	
			    		}
			    	}            	
			    	//String[] val = str.split("\\[Hien@key]");
			    	//if(val.length)
			    	System.out.println("==>Command: "+params.get(0)+" - "+params.get(1)+" - "+params.get(2));
			    	ds.executeKeywords(params.get(0),params.get(1),params.get(2));
		    		
				} catch (Exception e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}		    			    
		    }
		}// end read-line
		
	}

}
