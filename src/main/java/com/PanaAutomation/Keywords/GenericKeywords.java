package com.PanaAutomation.Keywords;

import java.awt.AWTException;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.regex.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.PanaAutomation.Util.Constants;
import com.PanaAutomation.reports.ExtentManager;

public class GenericKeywords {
	public Properties envProp;
	public Properties prop;
	public String ObjectKey;
	public String dataKey;
	public String proceedOnFail;
	public String Description;
	public Hashtable<String, String> data;
	public WebDriver driver;
	public ExtentTest test;
	public String mode;
	public SoftAssert softAssert = new SoftAssert();
	static List<String> filesListInDir = new ArrayList<String>();

	private enum Mode {
		xpath, id, linkText, value, name, cssSel;
	}

	/********************* Setter functions ************************/
	public String getProceedOnFail() {
		return proceedOnFail;
	}

	public void setProceedOnFail(String proceedOnFail) {
		this.proceedOnFail = proceedOnFail;
	}

	public String closeBrowser() {
		test.log(Status.INFO, "Closing the browser");
		try {
			driver.close();
			driver.quit();// quit Webdriver
			driver = null; // close all
		} catch (Exception e) {
			test.log(Status.INFO, "Unable to closing the browser");
			return "FAIL <br>" + " Unable to close browser" + e.getMessage().substring(0, 40) + "</br>";
		}
		return "PASS <br> Browser closed successfully" + "</br>";
	}

	public void setExtentTest(ExtentTest test) {
		this.test = test;
	}

	public void setEnvProp(Properties envProp) {
		this.envProp = envProp;
	}

	public void setProp(Properties prop) {
		this.prop = prop;
	}

	public void setObjectKey(String objectKey) {
		ObjectKey = objectKey;
	}

	public void setDataKey(String dataKey) {
		this.dataKey = dataKey;
	}
	
	public void setDescriptionKey(String Description) {
		this.Description = Description;
	}

	public void setData(Hashtable<String, String> data) {
		this.data = data;
	}

	/****************************
	 * Keywords
	 ********************************************/

	public void openBrowser() {
		try {
			if (driver == null) {
				System.out.println("Opening Browser" + data.get(dataKey));
				String browser = data.get(dataKey);
				test.log(Status.INFO, "Opening Browser " + browser + " successfully");
				if (browser.equals("Mozilla")) {

					System.setProperty("webdriver.gecko.driver",
							System.getProperty("user.dir") + prop.getProperty("fireFoxDriver"));
					driver = new FirefoxDriver();
				} else if (browser.equals("Chrome")) {

					System.setProperty("webdriver.chrome.driver",
							System.getProperty("user.dir") + prop.getProperty("chromeDriver"));

					driver = new ChromeDriver();
					
					/*ChromeOptions options = new ChromeOptions();
					options.addArguments("disable-infobars");
					
					options.addArguments("")
					*/
					
				} else if (browser.equals("IE")) {
					driver = new InternetExplorerDriver();
				}
			} else {

				navigate();
			}
			// max and set implicit wait
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			reportFailure("Unable to Launch Browser");
			e.printStackTrace();
		}

	}

	public void getTableHeaders() {
		try {
			test.debug("Retrieves table headers and compares the given data with table headers");

			if (waitUntilExists(ObjectKey, "xpath")) {
				List<WebElement> totalHeaders = driver.findElements(By.xpath(prop.getProperty(ObjectKey)));
				String temp = data.get(dataKey);
				String allElements[] = temp.split(";");
				int counter = 0;
				int NoOfElements = allElements.length;
				for (int i = 0; i < allElements.length; i++) {
					for (int j = 0; j <= totalHeaders.size(); j++) {
						String name = totalHeaders.get(j).getText();
						if (allElements[i].equalsIgnoreCase(name)) {
							counter = counter + 1;
							break;
						}
					}
				}

				if (counter == NoOfElements)
					test.log(Status.INFO, "Cart Type List Column Headers  are Matches With Expetced Values... ");
				else

					test.log(Status.INFO, "Cart Type List Column Headers  are Matches With Expetced Values... ");
			}
		} catch (Exception e) {
			reportFailure("Unexpected exception is thrown: " + e);
			e.printStackTrace();
		}
	}

	public void click() {
		try {
			// System.out.println("Clicking" +prop.getProperty(ObjectKey));
			//test.log(Status.INFO, "Clicking " + prop.getProperty(ObjectKey));
			test.log(Status.INFO, "Clicking on " + Description);
			
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(prop.getProperty(ObjectKey))));
			getObject(ObjectKey).click();
			// driver.findElement(By.xpath(prop.getProperty(ObjectKey))).click();
			DymamicWait();
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unable to Click " + prop.getProperty(ObjectKey));
		}
	}

	public void smartClick() {
		DymamicWait();
		try {
			// System.out.println("Clicking" +prop.getProperty(ObjectKey));
			test.log(Status.INFO, "Clicking " + prop.getProperty(ObjectKey));
			WebDriverWait wait = new WebDriverWait(driver, 20);
			// By WebElement = By.xpath(prop.getProperty(ObjectKey));
			// wait.until(ExpectedConditions.visibilityOf((org.openqa.selenium.WebElement)
			// WebElement));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(prop.getProperty(ObjectKey))));

			test.log(Status.INFO, "Found Element for clicking");

			getObject(ObjectKey).click();
			// driver.findElement(By.xpath(prop.getProperty(ObjectKey))).click();
			DymamicWait();
		} catch (Exception e) {
			reportFailure("Click element not clickable" + e);
		}
	}

	public void click1() {
		try {
			test.log(Status.INFO, "Clicking " + prop.getProperty(ObjectKey));
			Point target = driver.findElement(By.xpath(prop.getProperty(ObjectKey))).getLocation();
			System.out.println("Coordinates" + target);
			Actions builder = new Actions(driver);
			builder.moveToElement((WebElement) target, -573, -321).click().build().perform();
		} catch (Exception e) {
			reportFailure("Unexpected exception is thrown: " + e);
			e.printStackTrace();
		}

	}

	public void mouseMove() throws AWTException {
//	
//	  Point coordinates = driver.findElement(By.id("GojsFlowDiagram")).getLocation();
//	  Robot robot = new Robot();
//	  robot.mouseMove(coordinates.getX(),coordinates.getY()+120);
//	  
//	  Actions builder = new Actions(driver);
//
//	  builder.keyDown(Keys.CONTROL)
//	     .click((WebElement) coordinates)
//	     .moveByOffset( 10, 25 );
//	     .click();
//	     .keyUp(Keys.CONTROL).build().perform();
//	  
	}

	public void VerifyReset() {
		try {
			test.log(Status.INFO, "Verifying Text Field Reset Or not ");
			String s = driver.findElement(By.xpath(prop.getProperty(ObjectKey))).getAttribute("value");
			System.out.println(s);
			//test.log(Status.INFO, "Test Feild Vlaue-->" + s);
			if (s.equals("")) {
				test.log(Status.INFO,  ""+ Description + " got Reset ");
			} else {
				//reportFailure("Tesr Feild not got Reset-->" + s);
				reportFailure(""+ Description +" not got Reset ");
			}
		} catch (Exception e) {
			reportFailure("Unexpected exception is thrown: " + e);
			e.printStackTrace();
		}
	}

	public void type() {
		try {
			// System.out.println("Typing"+prop.getProperty(ObjectKey)+".Data-
			// "+data.get(dataKey));
			test.log(Status.INFO, "Typing in " + Description + " Test Data- " + data.get(dataKey));
			// driver.findElement(By.xpath(prop.getProperty(ObjectKey))).sendKeys(data.get(dataKey));
			// driver.findElement(By.xpath(prop.getProperty(ObjectKey))).clear();
			//test.log(Status.INFO, Description);
			getObject(ObjectKey).clear();
			DymamicWait();
			getObject(ObjectKey).sendKeys(data.get(dataKey));

		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unable to type " + prop.getProperty(ObjectKey) + ". Data- " + data.get(dataKey));
		}
	}

	public void typeAndEnter() {
		try {
			// System.out.println("Typing"+prop.getProperty(ObjectKey)+".Data-
			// "+data.get(dataKey));
			test.log(Status.INFO, "Typing in " + Description + ": Data - " + data.get(dataKey));
			// driver.findElement(By.xpath(prop.getProperty(ObjectKey))).sendKeys(data.get(dataKey));
			// driver.findElement(By.xpath(prop.getProperty(ObjectKey))).clear();
			getObject(ObjectKey).clear();
			DymamicWait();
			getObject(ObjectKey).sendKeys(data.get(dataKey));
			getObject(ObjectKey).sendKeys(Keys.ENTER);
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unable to type " + prop.getProperty(ObjectKey) + ". Data- " + data.get(dataKey));
		}
	}

	public void typeMatEnter() {
		try {

			test.log(Status.INFO, "Typing in " + prop.getProperty(ObjectKey) + ". Data- " + data.get(dataKey));
			getObject(ObjectKey).clear();
			DymamicWait();
			String value = data.get(dataKey);
			value = value.replace(".", "");
			getObject(ObjectKey).sendKeys(value);
			getObject(ObjectKey).sendKeys(Keys.ENTER);
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unable to type " + prop.getProperty(ObjectKey) + ". Data- " + data.get(dataKey));
		}
	}

	public void typeMatVerify() {
		try {
			test.log(Status.INFO, "Typing in " + prop.getProperty(ObjectKey) + ". Data- " + data.get(dataKey));
			getObject(ObjectKey).clear();
			DymamicWait();
			String value = data.get(dataKey);
			value = "SN" + value.replace(".", "");
			getObject(ObjectKey).sendKeys(value);
			getObject(ObjectKey).sendKeys(Keys.ENTER);
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unable to type " + prop.getProperty(ObjectKey) + ". Data- " + data.get(dataKey));
		}
	}

	public void type1() throws InterruptedException {
		try {

			// System.out.println("Typing"+prop.getProperty(ObjectKey)+".Data-
			// "+data.get(dataKey));
			// test.log(Status.INFO, "Typing in "+prop.getProperty(ObjectKey)+".Data-
			// "+data.get(dataKey));
			// driver.findElement(By.xpath(prop.getProperty(ObjectKey))).sendKeys(data.get(dataKey));
//		driver.manage().window().setSize(null);
			// driver = ((Object) driver).PhantomJS();
//		driver = Selenium::WebDriver.for :phantomjs
//		driver.manage.window.resize_to 1920, 1080
			// browser.set_window_size(1120, 550);

			// driver.findElement(By.xpath(prop.getProperty(ObjectKey))).clear();
			// getObject(ObjectKey).clear();

			// WebDriverWait wait = new WebDriverWait(driver, 10);
			// wait.until(ExpectedConditions.visibilityOf((WebElement)
			// By.xpath(prop.getProperty(ObjectKey))));
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath(prop.getProperty(ObjectKey)))).clear();
			// DymamicWait();
			// getObject(ObjectKey).sendKeys(data.get(dataKey));

//		Actions action = new Actions(driver);
//		int lenText = driver.findElement(By.xpath("//*[@id='row00dvsearchgrid']")).getText().length();
//		for(int i = 0; i < lenText; i++){
//			  action.sendKeys(Keys.ARROW_LEFT);
//			}
//			action.build().perform();
//			for(int i = 0; i < lenText; i++){
//				  action.sendKeys(Keys.DELETE);
//				}	
//			Thread.sleep(1000);
//			action.build().perform();	

			//test.log(Status.INFO, "Typing in type1");
			test.log(Status.INFO, "Typing in " + Description);
//		driver.findElement(By.xpath("//*[@id='row00dvsearchgrid']")).sendKeys("SuBox");
			WebElement ele = driver.findElement(By.xpath(prop.getProperty(ObjectKey)));
			Actions act = new Actions(driver);
			act.moveToElement(ele).doubleClick(ele).sendKeys(data.get(dataKey)).build().perform();
			// ele.getText();
			// System.out.println(ele);
			// JavascriptExecutor jse = (JavascriptExecutor) driver;
			// jse.executeScript("document.getElementById('row00dvsearchgrid').value =
			// 'SuBox';");

			// WebElement element =
			// WebUiCommonHelper.findWebElement(findTestObject("//*[@id='filterrow.dvsearchgrid']"),30)
			// WebUI.executeJavaScript("arguments[0].value='Your Value';",
			// Arrays.asList(element))
		} catch (Exception e) {
			reportFailure("Unexpected exception is thrown: " + e);
			e.printStackTrace();
		}
	}

	public void mouseHover() {
		try {
			WebElement element = getObject(ObjectKey);

			Thread.sleep(3000);

			Actions builder = new Actions(driver);
			builder.moveToElement(element).build().perform();
		} catch (Exception e) {
			reportFailure("Unexpected exception is thrown: " + e);
			e.printStackTrace();
		}
	}

	public void mouseHover(String xpath) {
		try {
			WebElement element = driver.findElement(By.xpath(prop.getProperty(xpath)));

			Thread.sleep(3000);

			Actions builder = new Actions(driver);
			builder.moveToElement(element).build().perform();
		} catch (Exception e) {
			reportFailure("Unexpected exception is thrown: " + e);
			e.printStackTrace();
		}
	}

	public void mouseDragAndDrop() {
		try {
			// Thread.sleep(3000);test.log(Status.INFO, "Mouse DragAndDrop " +
			// prop.getProperty(ObjectKey));
			WebElement source = getObject(ObjectKey);
			WebElement target = driver.findElement(By.xpath(data.get(dataKey)));

			Actions builder = new Actions(driver);
			Action action = builder.dragAndDrop(source, target).build();
			action.perform();
		} catch (Exception e) {
			reportFailure("Unexpected exception is thrown: " + e);
			e.printStackTrace();
		}
	}

	public void DragAndDrop() {
		try {
			test.log(Status.INFO, "Drag And Drop for " + Description);
			WebElement source = getObject(ObjectKey);
			WebElement target = driver.findElement(By.xpath(data.get(dataKey)));

			// Thread.sleep(3000);

			Actions builder = new Actions(driver);
			Action action = builder.clickAndHold(source).moveToElement(target).release(target).build();
			action.perform();
		} catch (Exception e) {
			reportFailure("Unexpected exception is thrown: " + e);
			e.printStackTrace();
		}
	}

	public void scrollingToElementofAPage() {

		try {
			test.log(Status.INFO, "Scrolling to Element of the Page");
			WebElement element = driver.findElement(By.xpath(prop.getProperty(ObjectKey)));
			// WebElement element =
			// driver.findElement(By.xpath("//*[contains(text(),'Closed')]"));
			if (element != null) {
				// ((JavascriptExecutor)
				// driver).executeScript("arguments[0].scrollIntoView(true);", element);
				// ((JavascriptExecutor)
				// driver).executeScript("arguments[0].scrollIntoViewIfNeeded();", element);//
				// new to Center
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({ block: 'center' });",
						element);// new to Center

			}
//		return "PASS <br>" + "Scrolling to element of a page successfully " + data + " in " + ObjectKey
//				+ "</br>";
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unable to scrolling to element of a page in " + prop.getProperty(ObjectKey));
		}

	}

	public void scrollingToElementofAPage(String xpath) {

		try {
			WebElement element = driver.findElement(By.xpath(prop.getProperty(xpath)));
			// WebElement element =
			// driver.findElement(By.xpath("//*[contains(text(),'Closed')]"));
			if (element != null) {
				// ((JavascriptExecutor)
				// driver).executeScript("arguments[0].scrollIntoView(true);", element);
				// ((JavascriptExecutor)
				// driver).executeScript("arguments[0].scrollIntoViewIfNeeded();", element);//
				// new to Center
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({ block: 'center' });",
						element);// new to Center

			}
//		return "PASS <br>" + "Scrolling to element of a page successfully " + data + " in " + ObjectKey
//				+ "</br>";
		} catch (Exception e) {

			e.printStackTrace();
			reportFailure("Unable to scrolling to element of a page in " + prop.getProperty(xpath));
		}

	}

	public void moveToElementofAPage() {

		try {
			WebElement element = driver.findElement(By.xpath(prop.getProperty(ObjectKey)));
			// WebElement element =
			// driver.findElement(By.xpath("//*[contains(text(),'Closed')]"));
//		if(element!=null){
//			((JavascriptExecutor) driver).executeScript(
//	                "arguments[0].scrollIntoView(true);", element);
//			Actions actions = new Actions(driver);
//			actions.moveToElement(element).build().perform();
//			//element..click();
//			
//			
//		}

			for (int j = 0; j <= 5; j++) {
				System.out.println("In For ...." + j);
				if (isClickable(driver.findElement(By.xpath(prop.getProperty("PanaMA.MAImportWorkordersDrpdwn_xpath"))),
						driver)) {
					driver.findElement(By.xpath(prop.getProperty("PanaMA.MAImportWorkordersDrpdwn_xpath"))).click();
//					if (isClickable(driver.findElement(By.xpath(prop.getProperty(ObjectKey))),driver)) {
//						System.out.println("In Display .... "  + ObjectKey  );
//						
//							//((JavascriptExecutor) driver).executeScript(
//					          //      "arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(prop.getProperty(ObjectKey))));
//							//driver.findElement(By.xpath(prop.getProperty(ObjectKey))).click();
//							System.out.println("Clickable ............");
//						//break;
//						
//					}
				} else {
					System.out.println("Not Clickable ......");
				}
			}
			System.out.println("After For ......");
			driver.findElement(By.xpath(prop.getProperty(ObjectKey))).click();

		} catch (Exception e) {
			reportFailure("Unexpected exception is thrown: " + e);
			e.printStackTrace();
		}

	}

	public void scrollingToBottomofAPage() {
		try {
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
//		return "PASS <br>" + "Scrolling to bottom of a page successfully " + data + " in " + object
//				+ "</br>";
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unable to scrolling to bottom of a page");
//		return "FAIL <br>" + "Unable to scrolling to bottom of a page" + data + " in "
//		+ object + "</br>";
		}

	}

	public void navigate() {
		try {
			test.log(Status.INFO, "Navigating to Website " + envProp.getProperty(ObjectKey));
			//test.log(Status.INFO, Description);
			driver.get(envProp.getProperty(ObjectKey));
		} catch (Exception e) {
			reportFailure("Unable to navigate to Website " + envProp.getProperty(ObjectKey));
		}

	}

	public void validateLogin() {
		System.out.println("Validating " + prop.getProperty(ObjectKey));

	}

	public void validateTitle() {
		try {
			String expectedTitle = data.get(dataKey);
			String ActualTitle = driver.getTitle();
			if (expectedTitle.equals(ActualTitle)) {
				test.log(Status.INFO, " ' " + ActualTitle + " ' Application launched successfully. ");
			} else {// Report Failure

				reportFailure(
						"Unable to launch website and Titles did not match. Got title as  ' " + ActualTitle + " '. ");
			}
		} catch (Exception e) {
			reportFailure("Unexpected exception is thrown: " + e);
			e.printStackTrace();
		}
	}

	public void validateElementPresent() {
		try {
			test.log(Status.INFO, "Validating the Element Exists or Not");
			// String expectedValue=prop.getProperty(ObjectKey);
//		try {
//	    	boolean isExists = driver.findElement(By.xpath(prop.getProperty(ObjectKey))).isDisplayed();
//	    	 System.out.println(isExists);
//			    if (isExists==true) { // Check for existence of Object
//			    test.log(Status.INFO,"Element Exists");
//			    }
////			    else if (isExists == false) {	// Check for non-existence of Object
////			    test.log(Status.INFO,"Element Doesnt Exists");}
//		}catch (NoSuchElementException e) {
//			test.log(Status.INFO,"Element Doesnt Exists");
//	    	return;
//		}

			if (isElementPresent(ObjectKey)) {

				//test.log(Status.INFO, "The Element Exists on the webpage..");
				test.log(Status.INFO, Description + "Element Exists on the page");
			} else if (!isElementPresent(ObjectKey)) {
				// report failure
				//reportFailure("Element not found on the WebPage ");
				reportFailure( Description + " Element not found on the Webpage ");
			}
		} catch (Exception e) {
			reportFailure("Unexpected exception is thrown: " + e);
			e.printStackTrace();
		}
	}

	public void validatePopUpMessage() {
		try {
			String expectedResult = data.get(dataKey);
			String popUpText = driver.findElement(By.xpath("//*[@id=\"displayMessageText\"]")).getText();
			if (popUpText.equals(expectedResult)) {
				test.log(Status.INFO, "Element Exists.." + popUpText);
			} else {
				//reportFailure("Element not found " + ObjectKey);
				reportFailure("Element not found " + popUpText);
			}
		} catch (Exception e) {
			reportFailure("Unexpected exception is thrown: " + e);
			e.printStackTrace();
		}
	}
	
	public void validatePopUpMessageCarrier() {
		try {
			String expectedResult = data.get(dataKey);
			String popUpText = driver.findElement(By.xpath("//*[@class=\"textmsgMobile\"]")).getText();
			if (popUpText.equals(expectedResult)) {
				test.log(Status.INFO, "Element Exists.." + popUpText);
			} else {
				//reportFailure("Element not found " + ObjectKey);
				reportFailure("Element not found " + popUpText);
			}
		} catch (Exception e) {
			reportFailure("Unexpected exception is thrown: " + e);
			e.printStackTrace();
		}
	}

	public void mesInfoVerify() {
		try {
			String expectedResult = data.get(dataKey);
			String popUpText = driver.findElement(By.xpath(prop.getProperty(ObjectKey))).getText();
			if (popUpText.equals(expectedResult)) {
				test.log(Status.INFO, "Element Exists.." + popUpText);
			} else {
				reportFailure("Element not found " + ObjectKey);
			}
		} catch (Exception e) {
			reportFailure("Unexpected exception is thrown: " + e);
			e.printStackTrace();
		}
	}

	public void validateURL() {
		try {
			String CurrURL = driver.getCurrentUrl();

			if (CurrURL.equals(ObjectKey)) {
				test.log(Status.INFO, "URL Validated ");
				System.out.println("URL Validated");
			}

			else {
				System.out.println("URL not Validated");
			}
		} catch (Exception e) {
			reportFailure("Unexpected exception is thrown: " + e);
			e.printStackTrace();
		}
	}

	public void DymamicWait() {
		try {
			
			String getWaitTime;
			getWaitTime = envProp.getProperty("DynamicWaitTime");
			int maxWaitTime = Integer.parseInt(getWaitTime);
			for (int j = 0; j < maxWaitTime; j++) {
				String ajaxComplete = ((org.openqa.selenium.JavascriptExecutor) driver)
						.executeScript("return jQuery.active == 0").toString();
				if (ajaxComplete.equalsIgnoreCase("true")) {
					break;
				}

				Thread.sleep(1000);
			}
			for (int j = 0; j < maxWaitTime; j++) {
				String pageLoad = ((org.openqa.selenium.JavascriptExecutor) driver)
						.executeScript("return document.readyState").toString();
				if (pageLoad.equalsIgnoreCase("complete")) {
					break;
				}
				Thread.sleep(1000);
			}

			for (int j = 0; j < maxWaitTime; j++) {
				String isLoaderHidden = ((org.openqa.selenium.JavascriptExecutor) driver)
						.executeScript("return $('.spinner').is(':visible') == false").toString();
				if (isLoaderHidden.equalsIgnoreCase("true")) {
					break;
				}
				Thread.sleep(1000);
			}

			// System.out.println("DymamicWait() value=" + data.get(dataKey));

		} catch (Exception e) {
			System.out.println("Error DymamicWait() " + e.getMessage());
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void DynamicWait() {
		try {
			String getWaitTime;
			getWaitTime = envProp.getProperty("DynamicWaitTime");
			int maxWaitTime = Integer.parseInt(getWaitTime);
			for (int j = 0; j < maxWaitTime; j++) {
				String ajaxComplete = ((org.openqa.selenium.JavascriptExecutor) driver)
						.executeScript("return jQuery.active == 0").toString();
				if (ajaxComplete.equalsIgnoreCase("true")) {
					break;
				}

				Thread.sleep(1000);
			}
			for (int j = 0; j < maxWaitTime; j++) {
				String pageLoad = ((org.openqa.selenium.JavascriptExecutor) driver)
						.executeScript("return document.readyState").toString();
				if (pageLoad.equalsIgnoreCase("complete")) {
					break;
				}
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void Delay() {
		try {
			String sleepValue = data.get(dataKey);
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.invisibilityOfElementWithText(By.xpath(prop.getProperty(ObjectKey)),
					sleepValue));

			System.out.println("Delay.. " + sleepValue);
			// Thread.sleep(Integer.parseInt(sleepValue));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void VerifyButtonText() {
		try {
			DymamicWait();
			test.log(Status.INFO, "Verifying Button Text");
			String actual = driver.findElement(By.xpath(prop.getProperty(ObjectKey))).getText();
			String actualResult = data.get(dataKey);
			if (actual.equals(actualResult))
				test.log(Status.INFO, "Button Text As Expected ..." + actualResult);
			else {
				reportFailure("Button Text is not as Expected " + actualResult);

			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void VerifyTextFieldValue() {
		try {
			DymamicWait();
			test.log(Status.INFO, "Verifying Button Text");
			String actual = driver.findElement(By.xpath(prop.getProperty(ObjectKey))).getText();
			String actualResult = data.get(dataKey);
			if (actual.equals(actualResult))
				test.log(Status.INFO, "Button Text As Expected ..." + actualResult);
			else {
				reportFailure("Button Text is not as Expected " + actualResult);
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void VerifyButtonState() {
		try {
			DymamicWait();
			test.log(Status.INFO, "To verify whether button is Enabled or Disabled");
			String actualResult = data.get(dataKey);
			String actual = driver.findElement(By.xpath(prop.getProperty(ObjectKey))).getAttribute("href");
			System.out.println(actual);
			if (actual != null && actualResult.equalsIgnoreCase("true")) {

				test.log(Status.INFO, "object is Enabled--->" + actual);
			} else if (actual == null && actualResult.equalsIgnoreCase("false")) {
				test.log(Status.INFO, "object is Disabled----->" + actual);
			} else {
				reportFailure("Object not found or invalid data " + actualResult);
				test.log(Status.INFO, "Object not found or invalid data ..." + actualResult);
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void VerifyTextBoxState() {
		try {
			test.log(Status.INFO, "Validating the Textbox is Enabled or Disable");
			String expectedResult = data.get(dataKey);
			boolean actual = driver.findElement(By.xpath(prop.getProperty(ObjectKey))).isEnabled();
			System.out.println(actual);
			System.out.println(expectedResult);
			if (actual == true) {
				test.log(Status.INFO, "TextBox is Enabled And Status is----->" + actual);
			} else if (actual == false) {
				test.log(Status.INFO, "TextBox is Disabled And Status is----->" + actual);
			} else {
				reportFailure("Object not found or invalid data " + actual);
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void VerifyDropDownState() {
		try {
			test.log(Status.INFO, "Validating the DropDown is Clickable or Not");
			WebElement s = driver.findElement(By.xpath(prop.getProperty(ObjectKey)));
			// test.log(Status.INFO,"Is Clickable? " + s.isEnabled());
			if (isClickable(s, driver)) {
				test.log(Status.INFO, "Is Clickable? " + s.isEnabled());
				// s.click();
			} else {
				reportFailure("Object is Not Clickable ");
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}

	}

	public static boolean isClickable(WebElement el, WebDriver driver) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 6);
			wait.until(ExpectedConditions.elementToBeClickable(el));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void VerifyButton() {
		try {
			DymamicWait();
			test.log(Status.INFO, "To verify whether button is Enabled or Disabled");
			String expectedResult = data.get(dataKey);
			boolean actual = driver.findElement(By.xpath(prop.getProperty(ObjectKey))).isEnabled();
			System.out.println(actual);
			System.out.println(expectedResult);
			if (actual == true) {
				test.log(Status.INFO, "object is Enabled--->" + actual);
			} else if (actual == false) {
				test.log(Status.INFO, "object is Disabled---->" + actual);
			} else {
				reportFailure("Object not found or invalid data " + actual);
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void checkCheckBox() {
		// String objDesc = ObjectKey;
		// String[] textboxname = object.split("_");
		// int objsize = textboxname.length;
		// objDesc = textboxname[objsize-1];
		try {
			Thread.sleep(3000);
			String checked = driver.findElement(By.xpath(prop.getProperty(ObjectKey))).getAttribute("checked");
			if (checked == null) // checkbox is unchecked
				driver.findElement(By.xpath((prop.getProperty(ObjectKey)))).click();
			DymamicWait();
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);

			// else
			// return "FAIL <br> <B> Unable to Check the "+"<b>"+objDesc+"</b>"+" checkbox"+
			// "</br>" ;

			// return "FAIL <br> <B> The "+"<b>"+objDesc+"</b>"+" checkbox is not
			// exist"+"</br>"+ e.getMessage().substring(0, 20);
		}
	}

	public void ValidateElementEnabled() {
		try {
			test.log(Status.INFO, "Validating Element Disabled or Not...");
			boolean result = driver.findElement(By.xpath(prop.getProperty(ObjectKey))).isEnabled();
			System.out.println(result);
			if (result == true) {
				test.log(Status.INFO, "Yes Element is Enabled And Status is-------->" + result);
			} else {
				reportFailure("No,Element is Not Enabled And Staus Is-------->" + result);
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void dbClick() {
		try {
			// getObject(ObjectKey).click();
			test.log(Status.INFO, "Double click on " + Description);
			WebElement element = driver.findElement(By.xpath(prop.getProperty(ObjectKey)));
			Actions builder = new Actions(driver);
			builder.doubleClick(element).perform();
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void mouseHoverNClick(String ObjectKey, String dataKey) {
		test.debug("Mousehover and click the specified object");

		// String objDesc = "";
		try {
			// String[] textboxname = ObjectKey.split("_");
			// int objsize = textboxname.length;
			// objDesc = textboxname[objsize-1];
			if (waitUntilExists(ObjectKey, "xpath")) {
				Actions act = new Actions(driver);
				WebElement imgButton = driver.findElement(By.xpath(prop.getProperty(ObjectKey)));
				act.moveToElement(imgButton).build().perform();
				WebElement quickView = driver.findElement(By.xpath(prop.getProperty(ObjectKey)));
				act.moveToElement(quickView).build().perform();
				act.click().build().perform();
				// return Constants.KEYWORD_PASS + "<br>" + "MouseHovered and Successfully
				// clicked on the object" + "</br>";

			}
			// else
			// return Constants.KEYWORD_FAIL + "<br>" + objDesc + "object is not found" +
			// "</br>";
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
			// return Constants.KEYWORD_FAIL + "<br>" + "Unable to MouseHover and click on
			// the " + objDesc + " object as " + e.getMessage().substring(0, 40) + "</br>";
		}
	}

//private boolean waitUntilExists(String ObjectKey) throws InterruptedException {
//	test.debug("Wait until the object is visible");
//	boolean result = false;
//    Mode accessBy = Mode.valueOf(mode);
//    int count = 1;
//    while (count < 2000) {
//    	Thread.sleep(1000);
//    	switch(accessBy) {
//	    	case xpath:
//	    		if (driver.findElement(By.xpath(prop.getProperty(ObjectKey))) != null) {
//	    			result = true;
//	    			count = 2000;
//	    		}
//	    		break;
//	    	case id:
//	    		if (driver.findElement(By.id(prop.getProperty(ObjectKey))) != null) {
//	    			result = true;
//	    			count = 2000;
//	    		}
//	    		break;
//	    	case linkText:
//	    		if (driver.findElement(By.linkText(prop.getProperty(ObjectKey))) != null) {
//	    			result = true;
//	    			count = 2000;
//	    		}
//	    		break;
//	    	case name:
//	    		if (driver.findElement(By.name(prop.getProperty(ObjectKey))) != null) {
//	    			result = true;
//	    			count = 2000;
//	    		}
//	    		break;
//	    	case cssSel:
//	    		if (driver.findElement(By.cssSelector(prop.getProperty(ObjectKey))) != null) {
//	    			result = true;
//	    			count = 2000;
//	    		}
//	    		break;
//		default:
//			break;
//    	}
//       count++;
//    }
//    return result;
//}

	public boolean waitUntilExists(String object, String mode) {
		// APP_LOGS.debug("Wait until the object is visible");

		boolean result = false;
		try {
			Mode accessBy = Mode.valueOf(mode);
			int count = 1;
			while (count < 2000) {
				Thread.sleep(1000);
				switch (accessBy) {
				case xpath:
					if (driver.findElement(By.xpath(prop.getProperty(ObjectKey))) != null) {
						result = true;
						count = 2000;
					}
					break;
				case id:
					if (driver.findElement(By.xpath(prop.getProperty(ObjectKey))) != null) {
						result = true;
						count = 2000;
					}
					break;
				case linkText:
					if (driver.findElement(By.linkText(prop.getProperty(ObjectKey))) != null) {
						result = true;
						count = 2000;
					}
					break;
				case name:
					if (driver.findElement(By.name(prop.getProperty(ObjectKey))) != null) {
						result = true;
						count = 2000;
					}
					break;
				case cssSel:
					if (driver.findElement(By.cssSelector(prop.getProperty(ObjectKey))) != null) {
						result = true;
						count = 2000;
					}
					break;
				default:
					break;
				}
				count++;
			}

		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
		return result;
	}

	public void quit() {
		try {
			if (driver != null)
				driver.quit();
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}

	}

	/******************** Utilty Funtion ******************/
//Centralized function to extract the objects
	public WebElement getObject(String objectKey) {

		WebElement e = null;
		try {
//	  if(objectKey.endsWith("_xpath"))
//	  e=driver.findElement(By.xpath(prop.getProperty(ObjectKey)));
//	  else 
			if (objectKey.endsWith("_id"))
				e = driver.findElement(By.id(prop.getProperty(ObjectKey)));
			else if (objectKey.endsWith("_css"))
				e = driver.findElement(By.cssSelector(prop.getProperty(ObjectKey)));
			else if (objectKey.endsWith("_name"))
				e = driver.findElement(By.name(prop.getProperty(ObjectKey)));
			else
				e = driver.findElement(By.xpath(prop.getProperty(ObjectKey)));

			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOf(e));
			wait.until(ExpectedConditions.elementToBeClickable(e));
		} catch (Exception ex) {
			// Failure-Report that failure
			reportFailure("Object Not Found " + ObjectKey);
		}
		return e;
	}

// Returns true or False
//True- Present
//False-Not Present
	public boolean isElementPresent(String ObjectKey) {
		List<WebElement> list = null;
		try {

			// if(ObjectKey.endsWith("_xpath"))
			// list=driver.findElements(By.xpath(prop.getProperty(ObjectKey)));
			// else
			if (ObjectKey.endsWith("_id"))
				list = driver.findElements(By.id(prop.getProperty(ObjectKey)));
			else if (ObjectKey.endsWith("_css"))
				list = driver.findElements(By.cssSelector(prop.getProperty(ObjectKey)));
			else if (ObjectKey.endsWith("_name"))
				list = driver.findElements(By.name(prop.getProperty(ObjectKey)));
			else
				list = driver.findElements(By.xpath(prop.getProperty(ObjectKey)));// df by Xpath HienHD_060519

		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}

		if (list.size() == 0)
			return false;
		else
			return true;
	}

	public void SelectItem() {
		test.log(Status.INFO, "SelectItem in " + prop.getProperty(ObjectKey) + ". Data- " + data.get(dataKey));
		System.out.println("SelectItem in " + prop.getProperty(ObjectKey) + ". Data- " + data.get(dataKey));

		try {
			// System.out.println("Typing"+prop.getProperty(ObjectKey)+".Data-
			// "+data.get(dataKey));
			// test.log(Status.INFO, "Typing in " + prop.getProperty(ObjectKey) + ". Data- "
			// + data.get(dataKey));
			// driver.findElement(By.xpath(prop.getProperty(ObjectKey))).sendKeys(data.get(dataKey));
			// driver.findElement(By.xpath(prop.getProperty(ObjectKey))).clear();

			Select selectObject = new Select(driver.findElement(By.id("select2-drop")));
			// selectObject.selectByValue("Item1");
			selectObject.selectByIndex(1);

			// getObject(ObjectKey).clear();
			// getObject(ObjectKey).click();
			// DymamicWait();
//		List<WebElement> menuItem = driver.findElements(By.cssSelector("select2-result-label"));
//		 for (int i=0; i<menuItem.size(); i++)
//		 {
//			 System.out.println("options: "+menuItem.get(i).getText());
//              //options.get(i).click();
//         } 
			// menuItem.get(1).click();

		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unable to type " + prop.getProperty(ObjectKey) + ". Data- " + data.get(dataKey));
		}

	}

	public void Enter() {
		try {
			driver.findElement(By.xpath(prop.getProperty(ObjectKey))).sendKeys(Keys.ENTER);
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}

	}

	public void Enter1() {
		try {
			getObject(ObjectKey).sendKeys(Keys.ENTER);
			getObject(ObjectKey).clear();
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}

	}

	public String CheckBoxSelected(String ObjectKey, String dataKey) {
//		try {
		test.debug("Verifying whether the checkbox is selected");
		String objDesc = "";
		String expec = data.get(dataKey);
		String[] textboxname = ObjectKey.split("_");
		int objsize = textboxname.length;
		objDesc = textboxname[objsize - 1];
		try {
			if (waitUntilExists(ObjectKey, "xpath")) {
				String checked = driver.findElement(By.xpath(prop.getProperty(ObjectKey))).getAttribute("checked");
				if (checked != null && dataKey.equalsIgnoreCase("true"))
					return Constants.KEYWORD_PASS + "-- Specfied Checkbox is checked";
				else if (checked == null && dataKey.equalsIgnoreCase("false"))
					return Constants.KEYWORD_PASS + "<br>" + "<b>" + objDesc + "</b>" + " Checkbox is Selected"
							+ "</br>";
				else
					return Constants.KEYWORD_FAIL + "<br>" + "<b>" + objDesc + "</b>" + " Checkbox is not Selected"
							+ "</br>";
			} else
				return Constants.KEYWORD_FAIL + "<br>" + "<b>" + objDesc + "</b>" + " Checkbox is not Selected"
						+ "</br>";
		} catch (Exception e) {
			return Constants.KEYWORD_FAIL + "<br>" + "<b>" + objDesc + "</b>"
					+ " Unable to verify whether the specified checkbox is selected" + "</br>"
					+ e.getMessage().substring(0, 20);
		}
//		} catch (Exception e) {
//			e.printStackTrace();
//			reportFailure("Unexpected exception is thrown: " + e);
//		}		
	}

	public void verifyRadioSelected() {
		try {
			test.debug("Verifying whether the Radio is selected?");
			String expec = data.get(dataKey);
			String checked = driver.findElement(By.xpath(prop.getProperty(ObjectKey))).getAttribute("checked");
			System.out.println(checked);
			// assertEquals(checked.isSelected(),true);
			if (checked != null && expec.equalsIgnoreCase("true")) {
				test.debug("Radio Button  Checked--->" + checked);
			} else {
				test.debug("Radio Button Not Checked--->" + checked);
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void verifyTab() {
		try {
			test.debug("Verifying whether the Tab is Enable?");
			String expec = data.get(dataKey);
			String tab = driver.findElement(By.xpath(prop.getProperty(ObjectKey))).getAttribute("aria-selected");
			System.out.println(tab);
			if (tab.equals(expec)) {
				test.debug("Tab Disabled--->" + tab);
			} else {
				reportFailure("Tab is Enabled" + tab);
			}
//		if (tab!= null && expec.equalsIgnoreCase("true")){
//			test.debug("Tab is Enabled --->"+tab);
//			reportFailure("Tab is Disabled"+ tab);
//			}else{
//		    test.debug("Tab is Disabled--->"+tab);
//		      takeScreenShot();
//			 drawBorder(driver,tab);
//			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void verifyTab1() {
		try {
			test.debug("Verifying whether the Tab is Enable?");
			String expec = data.get(dataKey);
			boolean tab = driver.findElement(By.xpath(prop.getProperty(ObjectKey))).isDisplayed();
			String s1 = Boolean.toString(tab);
			System.out.println(s1);
			if (s1.equals(expec)) {
				test.debug("Tab Enabled--->" + s1);
			} else {
				reportFailure("Tab is Disabled" + s1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void VerifyCheckBox() {
		try {
			test.debug("Verifying whether the checkbox is selected?");
			String expec = data.get(dataKey);
			System.out.println("Expected=" + expec);
			WebElement element_node = driver.findElement(By.xpath(prop.getProperty(ObjectKey)));
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			String checked = jse.executeScript("return arguments[0].checked", element_node).toString();
			System.out.println("Actual= " + checked);
			// String ajaxComplete =
			// ((org.openqa.selenium.JavascriptExecutor)driver).executeScript("return
			// jQuery.active == 0").toString();
			// String checked =
			// driver.findElement(By.xpath(prop.getProperty(ObjectKey))).getAttribute("checked");//fix
			// System.out.println(checked);
			if (expec.equals(checked)) {
				test.debug("Check Box Status is  " + checked);
			} else {
				test.debug("Check Box Status is " + checked);
				reportFailure("Check Box Status is " + checked);// New HienHD_020519
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void VerifyUserConfigCheckBox() {
		try {
			test.debug("Verifying whether the checkbox is selected?");
			String expec = data.get(dataKey);
			// System.out.println(expec);
			String checked = driver.findElement(By.xpath(prop.getProperty(ObjectKey))).getAttribute("src");
			System.out.println(checked);
			if (expec.equals(checked)) {
				test.debug("Check Box Already Checked" + checked);
			} else {
				test.debug("Check Box Not Checked" + checked);
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void VerifyComboBox() {
		try {
			test.debug("Verifying whether the combobox is Disabled or Not?");
			String expec = data.get(dataKey);
			System.out.println(expec);
			String actual = driver.findElement(By.xpath(prop.getProperty(ObjectKey))).getAttribute("href");
			System.out.println(actual);
			if (actual != null && expec.equalsIgnoreCase("true")) {
				test.log(Status.INFO, "object is Enabled--->" + actual);
			} else if (actual == null && expec.equalsIgnoreCase("false")) {
				test.log(Status.INFO, "object is Disabled---->" + actual);
			} else {
				reportFailure("Object not found or invalid data " + actual);
			}
//		if(actual==true)
//		{
//		test.log(Status.INFO,"object is Enabled--->"+actual);
//		}else if (actual==false){ 
//		test.log(Status.INFO,"object is Disabled---->"+actual);
//		}else{
//		reportFailure("Object not found or invalid data "+ actual);
//		}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public String selectList(String ObjectKey, String dataKey) {
		test.debug("Find a random value in list");
		String expec = data.get(dataKey);
		String objDesc = "";
		String[] textboxname = ObjectKey.split("_");
		int objsize = textboxname.length;
		objDesc = textboxname[objsize - 1];
		try {
			if (!data.equals(Constants.RANDOM_VALUE)) {
				driver.findElement(By.xpath(prop.getProperty(ObjectKey))).sendKeys(expec);
			} else {
				// logic to find a random value in list
				WebElement droplist = driver.findElement(By.xpath(prop.getProperty(ObjectKey)));
				List<WebElement> droplist_cotents = droplist.findElements(By.tagName("option"));
				Random num = new Random();
				int index = num.nextInt(droplist_cotents.size());
				String selectedVal = droplist_cotents.get(index).getText();
				driver.findElement(By.xpath(prop.getProperty(ObjectKey))).sendKeys(selectedVal);
				return Constants.KEYWORD_FAIL + "<br>" + "Unable to select the " + data + " from " + objDesc + " list"
						+ "</br>";
			}
		} catch (Exception e) {
			return Constants.KEYWORD_FAIL + "<br>" + "Unable to select the " + data + " from " + objDesc + " list"
					+ "</br>" + e.getMessage().substring(0, 40);
		}
		return Constants.KEYWORD_PASS + "<br>" + "Selected " + data + " from the " + objDesc + " dropdown list"
				+ "</br>";
	}

	public List<String> getColumnValues(int colIndex) {
		WebElement colElement;
		List<String> colValues = new ArrayList<String>();
		List<WebElement> rows = getRows();
		System.out.println("Rows count: " + rows.size());
		Iterator<WebElement> i = rows.iterator();
		while (i.hasNext()) {
			WebElement row = i.next();
			System.out.println("Row data: " + row.getText());
			// How to avoid this check for the header row for each row
			// iteration?
			if (row.findElements(By.tagName("th")).size() > 0) {
				colElement = row.findElement(By.xpath(".//th[" + colIndex + "]"));
			} else {
				colElement = row.findElement(By.xpath(".//td[" + colIndex + "]"));
			}
			colValues.add(colElement.getText().trim());
		}
		return colValues;
	}

	private List<WebElement> getRows() {
		// TODO Auto-generated method stub
		return null;
	}

	/**************************
	 * Reporting Function
	 **********************************/
	public void reportFailure(String failureMsg) {
		// Fail the Test
		test.log(Status.FAIL, failureMsg);
		// Take the screenshot,embedded screenshot in reports
		try {
			String expected = prop.getProperty(ObjectKey);
			takeScreenShot();
			drawBorder(driver, expected);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		takeScreenShot();

//		try {
//			copyMALogs();
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}

		try {
			copyMCLogs();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		/*
		 * try { zipReportsFolder(); } catch(Exception e) {
		 * System.out.println(e.getMessage()); }
		 */
		// fail in Testng
		// Assert.fail(failureMsg);
		if (proceedOnFail.equals("Y")) {// Soft Assertion
			softAssert.fail(failureMsg);
//	  driver.quit();
		} else {
			softAssert.fail(failureMsg);
			driver.quit();
			softAssert.assertAll();

		}

	}

	public void HighLighted() {
		try {
			String expected = prop.getProperty(ObjectKey);
			drawBorder(driver, expected);
			takeScreenShot();
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void assertAll() {
		softAssert.assertAll();
	}

	public void takeScreenShot() {
		// screenshot file Name and path
		// screenshotPath
		// fileName of the screenshot
		Date d = new Date();
		String screenshotFile = d.toString().replace(":", "_").replace(" ", "_") + ".png";
		// take screenshot
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		try {
			// get the dynamic folder name
			FileUtils.copyFile(srcFile, new File(ExtentManager.screenshotFolderPath + screenshotFile));
			// put screenshot file in reports
			test.log(Status.INFO, "Screenshot-> "
					+ test.addScreenCaptureFromPath(ExtentManager.screenshotFolderPath + screenshotFile));
			// test.log(Status.INFO,"Screenshot-> "+
			// test.addScreenCaptureFromPath(prop.getProperty("screenshotPath")+
			// screenshotFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void copyMALogs() {
		Date d = new Date();
		String MALogFile = d.toString().replace(":", "_").replace(" ", "_") + "_Server_Log.txt";
		File srcFile = new File(envProp.getProperty("MALogsPath"));
		try {
			FileUtils.copyFile(srcFile, new File(ExtentManager.logsOfMAFolderPath + MALogFile));
			System.out.println("<a href=" + ExtentManager.logsOfMAFolderPath + MALogFile + "></a>");
			test.log(Status.INFO, "MA Logs-> " + "<a href=./MALogs/" + MALogFile + ">MA Logs</a>");
		} catch (IOException e) {
			e.printStackTrace();
		}
		String MAArchiveFilePath = envProp.getProperty("MAArchiveLogsPath");
		File MAArchiveFile = getTheNewestFile(MAArchiveFilePath, "txt");
		String MAArchiveFile1 = d.toString().replace(":", "_").replace(" ", "_") + "_Archive_Server_Log.txt";

		try {
			FileUtils.copyFile(MAArchiveFile, new File(ExtentManager.logsOfMAFolderPath + MAArchiveFile1));
			System.out.println("<a href=" + ExtentManager.logsOfMAFolderPath + MAArchiveFile1 + "></a>");
			test.log(Status.INFO, "MA Archive Logs-> " + "<a href=./MALogs/" + MAArchiveFile1 + ">MA Archive Logs</a>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void copyMCLogs() {
		Date d = new Date();
		String MCLogFile = d.toString().replace(":", "_").replace(" ", "_") + "_log.txt";
		File srcFile = new File(envProp.getProperty("MCLogsPath"));
		try {
			FileUtils.copyFile(srcFile, new File(ExtentManager.logsOfMCFolderPath + MCLogFile));
			test.log(Status.INFO, "MC Logs-> " + "<a href=./MCLogs/" + MCLogFile + ">MC Logs</a>");
		} catch (IOException e) {
			e.printStackTrace();
		}

		String MCArchiveFilePath = envProp.getProperty("MCArchiveLogsPath");
		File MCArchiveFile = getTheNewestFile(MCArchiveFilePath, "txt");
		String MCArchiveFile1 = d.toString().replace(":", "_").replace(" ", "_") + "_Archive_Log.txt";

		try {
			FileUtils.copyFile(MCArchiveFile, new File(ExtentManager.logsOfMCFolderPath + MCArchiveFile1));
			System.out.println("<a href=" + ExtentManager.logsOfMCFolderPath + MCArchiveFile1 + "></a>");
			test.log(Status.INFO, "MC Archive Logs-> " + "<a href=./MCLogs/" + MCArchiveFile1 + ">MC Archive Logs</a>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public File getTheNewestFile(String filePath, String ext) {
		File theNewestFile = null;
		File dir = new File(filePath);
		FileFilter fileFilter = new WildcardFileFilter("*." + ext);
		File[] files = dir.listFiles(fileFilter);

		if (files.length > 0) {
			/** The newest file comes first **/
			Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
			theNewestFile = files[0];
		}

		return theNewestFile;
	}

	public void zipReportsFolder() {
		File dir = new File(ExtentManager.ReportFolder);
		zipDirectory(dir, ExtentManager.ReportsZipName);
	}

	private static void zipDirectory(File dir, String zipDirName) {
		try {
			populateFilesList(dir);
			// now zip files one by one
			// create ZipOutputStream to write to the zip file
			FileOutputStream fos = new FileOutputStream(zipDirName);
			ZipOutputStream zos = new ZipOutputStream(fos);
			for (String filePath : filesListInDir) {
				System.out.println("Zipping " + filePath);
				// for ZipEntry we need to keep only relative file path, so we used substring on
				// absolute path
				ZipEntry ze = new ZipEntry(filePath.substring(dir.getAbsolutePath().length() + 1, filePath.length()));
				zos.putNextEntry(ze);
				// read the file and write to ZipOutputStream
				FileInputStream fis = new FileInputStream(filePath);
				byte[] buffer = new byte[1024];
				int len;
				while ((len = fis.read(buffer)) > 0) {
					zos.write(buffer, 0, len);
				}
				zos.closeEntry();
				fis.close();
			}
			zos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method populates all the files in a directory to a List
	 * 
	 * @param dir
	 * @throws IOException
	 */
	private static void populateFilesList(File dir) throws IOException {
		File[] files = dir.listFiles();
		for (File file : files) {
			if (file.isFile())
				filesListInDir.add(file.getAbsolutePath());
			else
				populateFilesList(file);
		}
	}

	public static WebDriver HighlightElement(WebDriver driver, WebElement element) {
		if (driver instanceof JavascriptExecutor) {
			((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid red'", element);
		}
		return driver;
	}

	public static void drawBorder(WebDriver driver, String xpath) {
		try {
			WebElement element_node = driver.findElement(By.xpath(xpath));
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].style.border='3px solid red'", element_node);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void clickEscape() {
		try {
			Actions act = new Actions(driver);
			act.sendKeys(Keys.ESCAPE).build().perform();
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void clickTab() {
		try {
			Actions act = new Actions(driver);
			act.sendKeys(Keys.TAB).build().perform();
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public static void highlighElement(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {

			System.out.println(e.getMessage());
		}

		js.executeScript("arguments[0].setAttribute('style','border: solid 2px white');", element);
	}

	public void KeySpace() {
		try {
			driver.findElement(By.xpath(prop.getProperty(ObjectKey))).sendKeys(Keys.SPACE);
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}

	}

	public void sleepThread1Sec() {
		try {
			test.log(Status.INFO, "Sleep for 1Sec()");
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void sleepThread3Sec() {
		try {
			test.log(Status.INFO, "Sleep for 3Sec()");
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void sleepThread5Sec() {
		try {
			test.log(Status.INFO, "Sleep for 5Sec()");
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void sleepThread(long value) {
		try {
			Thread.sleep(value);
		} catch (InterruptedException e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void Check_Checkbox() {
		try {
			String isChecked = driver.findElement(By.xpath(prop.getProperty(ObjectKey))).getAttribute("checked");
			test.log(Status.INFO, "Checkbox: " + Description + " is checked !");
			if (isChecked == null) {
				try {
					driver.findElement(By.xpath(prop.getProperty(ObjectKey))).sendKeys(Keys.SPACE);
					// driver.findElement(By.xpath(prop.getProperty(ObjectKey))).click();
				} catch (Exception e) {
				}
			}

			sleepThread(1000);
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void Uncheck_Checkbox() {
		try {
			String isChecked = driver.findElement(By.xpath(prop.getProperty(ObjectKey))).getAttribute("checked");
			test.log(Status.INFO, "Checkbox: " + Description + " is unchecked !");
			if (isChecked != null) {
				try {
					driver.findElement(By.xpath(prop.getProperty(ObjectKey))).sendKeys(Keys.SPACE);
					// driver.findElement(By.xpath(prop.getProperty(ObjectKey))).click();
				} catch (Exception e) {
				}
			}

			sleepThread(1000);
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void Check_Checkbox1() {
		try {
			String isChecked = driver.findElement(By.xpath(prop.getProperty(ObjectKey))).getAttribute("checked");
			test.log(Status.INFO, "Checkbox: " + ObjectKey + " is checked !");
			if (isChecked == null) {
				try {
					// driver.findElement(By.xpath(prop.getProperty(ObjectKey))).sendKeys(Keys.SPACE);
					driver.findElement(By.xpath(prop.getProperty(ObjectKey))).click();
				} catch (Exception e) {
				}
			}

			sleepThread(1000);
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void Uncheck_Checkbox1() {
		try {
			String isChecked = driver.findElement(By.xpath(prop.getProperty(ObjectKey))).getAttribute("checked");
			test.log(Status.INFO, "Checkbox: " + ObjectKey + " is unchecked !");
			if (isChecked != null) {
				try {
					// driver.findElement(By.xpath(prop.getProperty(ObjectKey))).sendKeys(Keys.SPACE);
					driver.findElement(By.xpath(prop.getProperty(ObjectKey))).click();
				} catch (Exception e) {
				}
			}

			sleepThread(1000);
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void VerifyValueEquals() {
		try {
			DymamicWait();
			test.log(Status.INFO, "Verifying Text Content");
			String actual = driver.findElement(By.xpath(prop.getProperty(ObjectKey))).getAttribute("value");
			String actualResult = data.get(dataKey);
			if (actual.equals(actualResult))
				test.log(Status.INFO, "Text As Expected ..." + actualResult + actual);
			else {
				reportFailure("Text is not as Expected " + actualResult + actual);
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * Function to verify that an element is not present
	 */
	public void validateElementIsPresent() {
		try {
			DymamicWait();
			test.log(Status.INFO, "To Validate Element Is Present");
			if (isElementPresent(ObjectKey) == true) {
				test.log(Status.INFO, "Element is present" + Description);
			} else // if (!isElementPresent(ObjectKey)) fix hienhd_060519
			{
				// report failure
				reportFailure("Element is NOT present " + Description);
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * Function to verify that an element is not present
	 */
	public void validateElementNotPresent() {
		try {
			DymamicWait();
			test.log(Status.INFO, "Validate Element is not Present");
			if (isElementPresent(ObjectKey) == false) {
				test.log(Status.INFO, "Element does NOT exist" + Description);
			} else // if (!isElementPresent(ObjectKey)) fix HienHD_060519
			{
				// report failure
				reportFailure("Element still exist " + ObjectKey);
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * Function to verify that a button can not be click
	 */
	public void verifyButtonCanNotClick() {
		try {
			test.log(Status.INFO, "verifyButtonCanNotClick " + Description);
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(prop.getProperty(ObjectKey))));
			getObject(ObjectKey).click();
			DymamicWait();
			// report failure
			reportFailure("Still able to click " + prop.getProperty(ObjectKey));
		} catch (Exception e) {
			// e.printStackTrace(); #modify by Long 5132019
			test.log(Status.INFO, "Button can not click " + prop.getProperty(ObjectKey));
		}
	}

	/**
	 * Function to verify that an element contain a text string
	 */
	public void verifyTextContain() {
		try {
			test.log(Status.INFO, "verifyTextContain");
			DymamicWait();
			String expect = data.get(dataKey);
			String actual = driver.findElement(By.xpath(prop.getProperty(ObjectKey))).getText();
			if (actual.contains(expect))
				test.log(Status.INFO, "Text is as expected. Expect: " + expect + " Actual: " + actual);
			else {
				// report failure
				reportFailure("Text is NOT as expected. Expect: " + expect + " Actual: " + actual);
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * Function to verify that an element NOT contain a text string
	 */
	public void verifyTextNotContain() {
		try {
			test.log(Status.INFO, "verifyTextNotContain");
			DymamicWait();
			String expect = data.get(dataKey);
			String actual = driver.findElement(By.xpath(prop.getProperty(ObjectKey))).getText();
			if (!actual.contains(expect))
				test.log(Status.INFO, "Text is as expect. Expect: " + expect + " Actual: " + actual);
			else {
				// report failure
				reportFailure("Text is NOT as expect. Expect: " + expect + " Actual: " + actual);
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * Function to verify that an element equal a text string
	 */
	public void verifyTextEqual() {
		try {
			test.log(Status.INFO, "verifyTextEqual");
			DymamicWait();
			String expect = data.get(dataKey);
			String actual = driver.findElement(By.xpath(prop.getProperty(ObjectKey))).getText();
			if (actual.equalsIgnoreCase(expect)) {
				test.log(Status.INFO, "Text is as expect. Expect: " + expect + " Actual: " + actual);
			} else {
				// report failure
				reportFailure("Text is NOT as expect. Expect: " + expect + " Actual: " + actual);
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}
	
	public void verifyTextEqualCarrier() {
		try {
			test.log(Status.INFO, "verifyTextEqual");
			DymamicWait();
			String expect = data.get(dataKey);
			String actual = driver.findElement(By.xpath(prop.getProperty(ObjectKey))).getAttribute("aria-label");
		
			if (expect.equalsIgnoreCase("Master Location")) {
				actual = driver.findElement(By.xpath("(//*[contains(text(),'Master Location')])[3]")).getAttribute("aria-label");
			}	
			
			if (actual.contains(expect)) {
				test.log(Status.INFO, "Text is as expect. Expect: " + expect + " Actual: " + actual);
			} else {
				// report failure
				reportFailure("Text is NOT as expect. Expect: " + expect + " Actual: " + actual);
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void verifyTextNotEqual() {
		try {
			test.log(Status.INFO, "Verifying Text Content");
			DymamicWait();
			String actual = driver.findElement(By.xpath(prop.getProperty(ObjectKey))).getText();
			String actualResult = data.get(dataKey);
			if (actual.equalsIgnoreCase(actualResult)) {
				// report failure
				reportFailure("Text is not equal Expected " + actualResult);
			} else {
				test.log(Status.INFO, "Text Equal Expected ..." + actualResult);
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * Function to verify inner text of an element equals to a string
	 */
	public void verifyInnerTextEqual() {
		try {
			test.log(Status.INFO, "verifyInnerTextEqual");
			DymamicWait();
			String expect = data.get(dataKey);
			WebElement button = driver.findElement(By.xpath(prop.getProperty(ObjectKey)));
			String actual = button.getText();

//			String actual = driver.findElement(By.xpath(prop.getProperty(ObjectKey))).getAttribute("value");
//			WebElement button = driver.findElement(By.xpath(prop.getProperty(ObjectKey)));
//			JavascriptExecutor jse = (JavascriptExecutor) driver;
//			actual = jse.executeScript("return arguments[0].innerText;", button).toString();
			if (actual.equalsIgnoreCase(expect)) {
				test.log(Status.INFO, "Text is as expect. Expect: " + expect + " Actual: " + actual);
			} else {
				// report failure
				reportFailure("Text is NOT as expect. Expect: " + expect + " Actual: " + actual);
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * Function to verify string values are equal
	 */
	public void verifyStringValueEquals() {
		try {
			test.log(Status.INFO, "verifyStringValueEquals");
			DymamicWait();
			String expect = data.get(dataKey);
			String actual = driver.findElement(By.xpath(prop.getProperty(ObjectKey))).getAttribute("value");
			if (expect.contains(actual)) {
				test.log(Status.INFO, "Text is as expected. Expected: " + expect + " Actual: " + actual);
			} else {
				reportFailure("Text is NOT as expected. Expected: " + expect + " Actual: " + actual);
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * Function to verify string value contains a string
	 */
	public void verifyStringValueContain() {
		try {
			test.log(Status.INFO, "verifyStringValueContain()");
			DymamicWait();
			String expect = data.get(dataKey);
			String actual = driver.findElement(By.xpath(prop.getProperty(ObjectKey))).getAttribute("value");
			if (actual.contains(expect)) {
				test.log(Status.INFO, "Text is as expect. Expect: " + expect + " Actual: " + actual);
			} else {
				reportFailure("Text is NOT as expect. Expect: " + expect + " Actual: " + actual);
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * Function to verify string values is empty
	 */
	public void verifyStringValueIsEmpty() {
		try {
			test.log(Status.INFO, "verifyStringValueIsNotEmpty()");
			DymamicWait();
			String actual = driver.findElement(By.xpath(prop.getProperty(ObjectKey))).getAttribute("value");
			if (actual.length() == 0) {
				test.log(Status.INFO, "String is empty");
			} else {
				reportFailure("String is NOT empty: " + actual);
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * Function to verify string values is NOT empty
	 */
	public void verifyStringValueNotEmpty() {
		try {
			test.log(Status.INFO, "verifyStringValueIsNotEmpty()");
			DymamicWait();
			String actual = driver.findElement(By.xpath(prop.getProperty(ObjectKey))).getAttribute("value");
			if (actual.length() > 0) {
				test.log(Status.INFO, "String is NOT empty: " + actual);
			} else {
				reportFailure("String is empty");
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * Function to verify number values with dot in string are equal
	 */
	public void verifyStringNumberWithDotValueEquals() {
		try {
			test.log(Status.INFO, "verifyStringValueEquals");
			DymamicWait();
			String expect = data.get(dataKey);
			String actual = driver.findElement(By.xpath(prop.getProperty(ObjectKey))).getText();
			if (expect.contains(actual)) {
				test.log(Status.INFO, "Text is as expect. Expect: " + expect + " Actual: " + actual);
			} else {
				reportFailure("Text is NOT as expect. Expect: " + expect + " Actual: " + actual);
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * Function to verify text value of element is NOT empty
	 */
	public void verifyTextNotEmpty() {
		try {
			test.log(Status.INFO, "verifyTextNotEmpty()");
			DymamicWait();
			String actual = driver.findElement(By.xpath(prop.getProperty(ObjectKey))).getText();
			if (actual.length() > 0) {
				test.log(Status.INFO, "Text is NOT empty: " + actual);
			} else {
				reportFailure("Text is empty");
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * Function to verify text value of element is empty
	 */
	public void verifyTextIsEmpty() {
		try {
			test.log(Status.INFO, "verifyTextIsEmpty()");
			DymamicWait();
			String actual = driver.findElement(By.xpath(prop.getProperty(ObjectKey))).getText();
			if (actual.length() == 0) {
				test.log(Status.INFO, "Text is empty");
			} else {
				reportFailure("Text is NOT empty: " + actual);
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * Verify text by Regex HienHD_130519
	 */
	public void verifyTextRegEx() {
		try {
			test.log(Status.INFO, "verifyTextRegEx");
			DymamicWait();
			String expect = data.get(dataKey);
			String actual = driver.findElement(By.xpath(prop.getProperty(ObjectKey))).getText();

			// Create a Pattern object
			Pattern r = Pattern.compile(expect, Pattern.MULTILINE);
			// Now create matcher object.
			Matcher m = r.matcher(actual);

			if (m.find())
				test.log(Status.INFO, "Text is as expect. Expect: " + expect + " Actual: " + actual);
			else {
				// report failure
				reportFailure("Text is NOT as expect. Expect: " + expect + " Actual: " + actual);
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * Verify text by Regex HienHD_130519 Example: verifyTextRegEx*innerText ;
	 * verifyTextRegEx*innerHTML
	 */
	public void verifyTextRegEx(String cmd) {
		try {
			/// System.out.print("@@@ " + cmd.toString());
			test.log(Status.INFO, "verifyTextRegEx*" + cmd);
			DymamicWait();
			String expect = data.get(dataKey);

			WebElement element_node = driver.findElement(By.xpath(prop.getProperty(ObjectKey)));
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			String actual = jse.executeScript("return arguments[0]." + cmd, element_node).toString();

			// Create a Pattern object
			Pattern r = Pattern.compile(expect, Pattern.MULTILINE);
			// Now create matcher object.
			Matcher m = r.matcher(actual);

			if (m.find())
				test.log(Status.INFO, "Text is as expect. Expect: " + expect + " Actual: " + actual);
			else {
				// report failure
				reportFailure("Text is NOT as expect. Expect: " + expect + " Actual: " + actual);
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * Function to verify that an element is visible
	 */
	public void validateElementIsVisible() {
		try {
			DymamicWait();
			test.log(Status.INFO, "validateElementIsVisible");
			WebElement element = driver.findElement(By.xpath(prop.getProperty(ObjectKey)));
			if (element.isDisplayed()) {
				//test.log(Status.INFO, "Element is visible" + ObjectKey);
				test.log(Status.INFO, "Element is visible " + Description);
			} else {
				// report failure
				reportFailure("Element is NOT visible " + ObjectKey);
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * Function to verify that an element is Invisible
	 */
	public void validateElementIsInvisible() {
		try {
			DymamicWait();
			test.log(Status.INFO, "validateElementIsVisible");
			WebElement element = driver.findElement(By.xpath(prop.getProperty(ObjectKey)));
			if (!element.isDisplayed()) {
				//test.log(Status.INFO, "Element is invisible" + ObjectKey);
				test.log(Status.INFO, "Element is Invisible " + Description);
			} else {
				// report failure
				reportFailure("Element is NOT invisible " + ObjectKey);
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * Function to verify that an element contains by script
	 */
	public void validateElementContainsBy(String script) {
		try {
			DymamicWait();
			test.log(Status.INFO, "validateElementContainsBy*" + script);

			String expect = data.get(dataKey);

			WebElement element_node = driver.findElement(By.xpath(prop.getProperty(ObjectKey)));
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			String actual = jse.executeScript("return arguments[0]." + script, element_node).toString();

			if (actual.contains(expect)) {
				test.log(Status.INFO, "Element is contains " + ObjectKey);
			} else {
				// report failure
				reportFailure("Element is NOT contains " + ObjectKey);
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * Code New Long
	 *
	 * Function to send keys to an element
	 **/

	public void send2KeyEventsToElement(String xpath, Keys key1, Keys key2) {
		try {
			DymamicWait();
			//test.log(Status.INFO, "send2KeyEventsToElement(String xpath, String key1, String key2)");
			WebElement element = driver.findElement(By.xpath(prop.getProperty(xpath)));

			Thread.sleep(1000);
			element.sendKeys(key1, key2);
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * Function to send keys ARROW_DOWN & ENTER to an element
	 */
	public void sendKeyEventsDOWNENTERToElement() {
		try {
			DymamicWait();
			test.log(Status.INFO, "sendKeyEventsDOWNENTERToElement()");
			WebElement element = driver.findElement(By.xpath(prop.getProperty(ObjectKey)));

			Thread.sleep(1000);
			element.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * Function to verify no item with Completed print status in Print screen TC ID:
	 * MCID1520
	 */
	public void validateNoSpecificPrintStatusItem() {
		try {
			DymamicWait();
			test.log(Status.INFO, "To validate - Not Completed Print Status Item");
			String inputStatus = data.get(dataKey);

			WebElement element = driver.findElement(By.xpath(prop.getProperty(ObjectKey)));
			if (null != element) {
				String statusPrint = element.getText();

				if (statusPrint.equalsIgnoreCase(inputStatus)) {
					reportFailure(
							"Found print item with state [" + inputStatus + "] is: " + prop.getProperty(ObjectKey));
				}
			} else {
				test.log(Status.INFO, "Element not found, skip");
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * Code New Long
	 */

	public void click_para(String xpath) {
		try {
			test.log(Status.INFO, "Clicking " + prop.getProperty(xpath));
			DymamicWait();
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(prop.getProperty(xpath))));
			driver.findElement(By.xpath(prop.getProperty(xpath))).click();
			DymamicWait();
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unable to Click " + prop.getProperty(xpath));
		}
	}

	public void typeAndEnter_para(String xpath, String value) {
		try {
			test.log(Status.INFO, "Typing in " + prop.getProperty(xpath) + ". Data- " + value);
			driver.findElement(By.xpath(prop.getProperty(xpath))).clear();
			DymamicWait();
			driver.findElement(By.xpath(prop.getProperty(xpath))).sendKeys(value);
			Thread.sleep(1000);
			driver.findElement(By.xpath(prop.getProperty(xpath))).sendKeys(Keys.ENTER);
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unable to type " + prop.getProperty(xpath) + ". Data- " + value);
		}
	}

	public void type_para(String xpath, String value) {
		try {
			test.log(Status.INFO, "Typing in " + prop.getProperty(xpath) + ". Data- " + value);
			driver.findElement(By.xpath(prop.getProperty(xpath))).clear();
			DymamicWait();
			driver.findElement(By.xpath(prop.getProperty(xpath))).sendKeys(value);
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unable to type " + prop.getProperty(xpath) + ". Data- " + value);
		}
	}

	public void type_paranew(String xpath, String value,String msg) {
		try {
			test.log(Status.INFO, "Typing in " + msg + " : Data- " + value);
			driver.findElement(By.xpath(prop.getProperty(xpath))).clear();
			DymamicWait();
			driver.findElement(By.xpath(prop.getProperty(xpath))).sendKeys(value);
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unable to type " + msg + " : Data- " + value);
		}
	}
	
	public String OffsetLocationX(String element) {// processFlowDiagram
		String location = null;
		try {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			location = jse.executeScript("return document.getElementById('" + element
					+ "').getBoundingClientRect().left + window.pageXOffset || document.documentElement.scrollLeft; ")
					.toString();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return location;

	}

	public String OffsetLocationY(String element) {// processFlowDiagram
		String location = null;
		try {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			location = jse.executeScript("return document.getElementById('" + element
					+ "').getBoundingClientRect().top + window.pageYOffset || document.documentElement.scrollTop; ")
					.toString();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return location;

	}

	public void rowsCount() {
		try {
			String countitem = driver.findElement(By.xpath(prop.getProperty(ObjectKey))).getText();
			int count = Integer.valueOf(countitem.substring(countitem.length() - 1));
			test.log(Status.INFO, "There are " + count + " elements in the list");
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * Upload file by HienHD_100519
	 */
	public void fileUpload() {
		try {
			//test.log(Status.INFO, "Upload filename " + prop.getProperty(ObjectKey) + ". Data- " + data.get(dataKey));
			  test.log(Status.INFO, "Upload filename "  + data.get(dataKey));
			getObject(ObjectKey).clear();
			DymamicWait();
			String urlPath = System.getProperty("user.dir") + "//src//test/resources//" + data.get(dataKey);
			getObject(ObjectKey).sendKeys(urlPath);
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unable to type " + prop.getProperty(ObjectKey) + ". Data- " + data.get(dataKey));
		}
	}

	/**
	 * Upload file with element
	 */
	public void fileUploadWithXpath(String xpath, String fileName) {
		try {
			test.log(Status.INFO, "Upload filename " + prop.getProperty(xpath) + ". Data- " + fileName);
			driver.findElement(By.xpath(prop.getProperty(xpath))).clear();
			DymamicWait();
			String urlPath = System.getProperty("user.dir") + "//src//test/resources//" + fileName;
			driver.findElement(By.xpath(prop.getProperty(xpath))).sendKeys(urlPath);
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unable to type " + prop.getProperty(xpath) + ". Data- " + fileName);
		}
	}
	
	public void click_paranew(String xpath, String Msg) {
		try {
			//test.log(Status.INFO, "Clicking " + prop.getProperty(xpath));
			test.log(Status.INFO, "Clicking " + Msg);
			DymamicWait();
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(prop.getProperty(xpath))));
			driver.findElement(By.xpath(prop.getProperty(xpath))).click();
			DymamicWait();
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unable to Click " + prop.getProperty(xpath));
		}
	}
	
	public void typeAndEnter_paranew(String xpath, String value,String Msg) {
		try {
			//test.log(Status.INFO, "Typing in " + prop.getProperty(xpath) + ". Data- " + value);
			test.log(Status.INFO, "Typing in " + Msg + " : Data- " + value);
			driver.findElement(By.xpath(prop.getProperty(xpath))).clear();
			DymamicWait();
			driver.findElement(By.xpath(prop.getProperty(xpath))).sendKeys(value);
			Thread.sleep(1000);
			driver.findElement(By.xpath(prop.getProperty(xpath))).sendKeys(Keys.ENTER);
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unable to type " + prop.getProperty(xpath) + ". Data- " + value);
		}
	}

	
}
