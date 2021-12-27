package com.PanaAutomation.Base;

import java.util.Properties;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


import com.PanaAutomation.reports.EmailReport;

public class TestListener implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		//System.out.println("Anjana here - Test Finished");
		EmailReport EM = new EmailReport();
		Properties envProp;
		envProp = EM.getEnvProp();
		try
		{
			if(envProp.getProperty("SendMail").equalsIgnoreCase("true"))
			{
				EM.SendEmail();
			}
			else
			{
				System.out.println("Email not sent");
			}
		}
		catch(Exception e)
		{
			System.out.println("Email not sent");
		}
	}
	
}