package com.PanaAutomation.Base;

import java.io.Console;
import java.util.List;

import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.collections.Lists;

public class runMC {

	public static void main(String args[]) {
		TestListenerAdapter tla = new TestListenerAdapter();
		TestNG testng = new TestNG();
		List<String> suites = Lists.newArrayList();
		if(args.length<=0)
		{
			System.out.println("runMC Runnning..");
			suites.add(".\\testng.xml");//path to xml..
		}			
		else
		{
			System.out.println("runMC Runnning.. "+args[0]);
			suites.add(".\\testng"+args[0]+".xml");//path to xml..
		}
		
		testng.setTestSuites(suites);
		testng.run();	
		
	}
}


