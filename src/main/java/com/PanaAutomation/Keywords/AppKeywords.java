package com.PanaAutomation.Keywords;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
//import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.xpath.XPath;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.james.mime4j.field.datetime.DateTime;
import org.apache.xmlbeans.impl.xb.xsdschema.FieldDocument.Field.Xpath;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.util.Strings;

import com.aventstack.extentreports.Status;
import com.google.common.collect.Ordering;

import DBTransaction.DBTransaction;

public class AppKeywords extends PreSetup {

	public static Double D_Hours = 0.0;
	public static Double D_Minutes = 5.0;
	public static String globalmID = "";
	storeValue sV = new storeValue();

	/**
	 * validateNewWOPage This Method to Check the New WO page is launched.
	 */
	public void validateNewWOPage() {
		try {
			test.log(Status.INFO, "Verifying New Workorder Page is launched");
			String expResult = "Workorder Name";
			String actResult = driver.findElement(By.xpath("//*[contains(text(),'Workorder Name')]")).getText();

			if (actResult.equals(expResult)) {

				test.log(Status.INFO, "New Workorder page opened Successfully.....");
			} else {
				reportFailure("New Workorder page was not opened Successfully.....");
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void EnterPartName() {
		try {
			// System.out.println("Typing"+prop.getProperty(ObjectKey)+".Data-
			// "+data.get(dataKey));
			test.log(Status.INFO, "Typing in " + prop.getProperty(ObjectKey) + ". Data- " + data.get(dataKey));
			// driver.findElement(By.xpath(prop.getProperty(ObjectKey))).sendKeys(data.get(dataKey));
			// driver.findElement(By.xpath(prop.getProperty(ObjectKey))).clear();
			getObject(ObjectKey).clear();
			DymamicWait();
			getObject(ObjectKey).sendKeys(data.get(dataKey));
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unable to type " + prop.getProperty(ObjectKey) + ". Data- " + data.get(dataKey));
		}
	}

	public void VerifyAdjustQuantity() {
		try {
			test.log(Status.INFO, "Verifying whether Adjust Quantity updating or Not?");
			// String Quantity = data.get(dataKey);
			// String
			// s=driver.findElement(By.xpath("//*[@id='grdAllLot']/tbody/tr[1]/td[3]")).getText();W
			// System.out.println(s);
			driver.findElement(By.xpath("//*[@id='btnAdjust']")).click();
			DymamicWait();
			driver.findElement(By.xpath("//*[@id='txtpopupAdjustQuantity']")).sendKeys("");
			driver.findElement(By.xpath("//*[@id='txtpopupAdjustQuantity']")).sendKeys(Keys.BACK_SPACE);
			WebElement s = driver.findElement(By.xpath("//*[@id='txtpopupAdjustQuantity']"));
			s.sendKeys("5");

			driver.findElement(By.xpath("//*[@id='s2id_ddlAdjustReasonCode']/a/span[1]")).click();
			driver.findElement(By.xpath("//*[@id='select2-drop']/ul/li[2]")).click();
			String val = s.getAttribute("value");
			System.out.println(val);
			driver.findElement(By.xpath("//*[@id='btnAdjustQuantity']")).click();
			DymamicWait();
			String s1 = driver.findElement(By.xpath("//*[@id='grdAllLot']/tbody/tr[1]/td[7]")).getText();
			if (val.contains(s1)) {
				test.log(Status.INFO, "The Quantity was Adjusted successfully --->" + s1);
			} else {
				reportFailure("Quantity was Not Adjusted ---->" + s1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void typeAppendTime() {
		try {

			String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

			// System.out.println("Typing"+prop.getProperty(ObjectKey)+".Data-
			// "+data.get(dataKey));
			//test.log(Status.INFO, "Typing in " + prop.getProperty(ObjectKey) + ". Data- " + data.get(dataKey));
			// driver.findElement(By.xpath(prop.getProperty(ObjectKey))).sendKeys(data.get(dataKey));
			// driver.findElement(By.xpath(prop.getProperty(ObjectKey))).clear();
			test.log(Status.INFO, "Typing in " + Description + ". Data- " + data.get(dataKey));
			getObject(ObjectKey).clear();

			String value = data.get(dataKey);
			getObject(ObjectKey).sendKeys(value);
			getObject(ObjectKey).sendKeys(timeStamp);
			sleepThread(1000);
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unable to type " + prop.getProperty(ObjectKey) + ". Data- " + data.get(dataKey));
		}
	}

	public void typePartname() {
		try {
			String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

			// System.out.println("Typing"+prop.getProperty(ObjectKey)+".Data-
			// "+data.get(dataKey));
			test.log(Status.INFO, "Typing in " + Description  + ": Data- " + data.get(dataKey));
			// driver.findElement(By.xpath(prop.getProperty(ObjectKey))).sendKeys(data.get(dataKey));
			// driver.findElement(By.xpath(prop.getProperty(ObjectKey))).clear();
			getObject(ObjectKey).clear();
			DymamicWait();

			String parttempname = data.get(dataKey) + timeStamp;
			getObject(ObjectKey).sendKeys(parttempname);
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unable to type " + prop.getProperty(ObjectKey) + ". Data- " + data.get(dataKey));
		}
	}

	public void typePartnumber() {
		try {
			String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

			// System.out.println("Typing"+prop.getProperty(ObjectKey)+".Data-
			// "+data.get(dataKey));
			test.log(Status.INFO, "Typing in " + prop.getProperty(ObjectKey) + ". Data- " + data.get(dataKey));
			// driver.findElement(By.xpath(prop.getProperty(ObjectKey))).sendKeys(data.get(dataKey));
			// driver.findElement(By.xpath(prop.getProperty(ObjectKey))).clear();
			getObject(ObjectKey).clear();
			DymamicWait();

			String partnumber = data.get(dataKey) + timeStamp;
			getObject(ObjectKey).sendKeys(partnumber);
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unable to type " + prop.getProperty(ObjectKey) + ". Data- " + data.get(dataKey));
		}
	}

	public void VerifyPart() {
		try {
			String timeStamp = new SimpleDateFormat("yyyy.MM.dd").format(new Date());

			// System.out.println("Typing"+prop.getProperty(ObjectKey)+".Data-
			// "+data.get(dataKey));
			test.log(Status.INFO, "Typing in " + Description + ": Data- " + data.get(dataKey));
			// driver.findElement(By.xpath(prop.getProperty(ObjectKey))).sendKeys(data.get(dataKey));
			// driver.findElement(By.xpath(prop.getProperty(ObjectKey))).clear();
			getObject(ObjectKey).clear();
			DymamicWait();

			String partnumber = data.get(dataKey) + timeStamp;
			getObject(ObjectKey).sendKeys(partnumber);
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unable to type " + prop.getProperty(ObjectKey) + ". Data- " + data.get(dataKey));
		}
	}

	/**
	 * validateProductField This Method is to Validate the product field selection.
	 */
	public void validateProductField() {
		try {
			test.log(Status.INFO, "Verifying Product Field in New Workorder");
			String expectedResult = data.get(dataKey);
			String actResult = driver.findElement(By.xpath("//input[@placeholder='Enter Product']"))
					.getAttribute("value");

			if (actResult.equals(expectedResult)) {

				test.log(Status.INFO, "Selected Value in product field:  " + actResult);
				boolean actValue = driver.findElement(By.xpath("(//span[contains(text(),'Add Subwork')])[1]"))
						.isDisplayed();

				if (actValue) {
					test.log(Status.INFO, "Add Subwokorder.. is Displayed");
				} else {
					test.log(Status.INFO, "No Add Subwokorder field.. is Displayed");
				}
			} else {
				reportFailure("New Workorder page was not opened Successfully.....");
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * VerifyProductFieldisDropDown This function verifies whether Product field
	 * gives a list of values when any matching product name is entered on it
	 */

	public void verifyProductFieldisDropDown() {
		try {
			test.log(Status.INFO, "Verifying Product Field in New Workorder");
			String productSearchName = data.get(dataKey);
			driver.findElement(By.xpath(prop.getProperty("Panama.MANewWOPdname_xpath"))).sendKeys(productSearchName);

			List<WebElement> ProductElements = driver
					.findElements(By.xpath(prop.getProperty("Panama.MAProdSearchResults_xpath")));

			if (ProductElements != null) {
				// System.out.println("Product Search Field gives dropdown values");
				test.log(Status.INFO, "Product Search Field gives dropdown values");
			} else {
				// System.out.println("Product Search Field is NOT Dropdown");

				reportFailure("Product Search Field is NOT Dropdown");
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * VerifyFormHeading This Method is to verify Title of the Form.
	 * 
	 */
	public void verifyFormHeading() {
		try {
			String formHeading = data.get(dataKey);
			test.log(Status.INFO, "Verifying " + formHeading + " form Heading");

			String object_xpath = prop.getProperty(ObjectKey);
			String menu = driver.findElement(By.xpath(object_xpath)).getText();

			if (menu.contains(formHeading)) {
				test.log(Status.INFO, formHeading + ".... Heading is present on the Screen");
			} else {
				reportFailure(formHeading + ".... Heading is not present on the Screen");
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * VerifyWorkOrderEditField This function verifies whether WO Name Field on New
	 * WO Page is editable or not.
	 */
	public void verifyWorkOrderEditField() {
		try {
			test.log(Status.INFO, "Verifying Work Order Name Edit Field is editable or not.");
			String ExpectedResult = "input";
			String ActualResult = driver.findElement(By.xpath(prop.getProperty("Panama.MANewWOName_xpath")))
					.getTagName();
			if (ActualResult.equals(ExpectedResult)) {
				// System.out.println("Workorder Name Test Field is Editable");
				test.log(Status.INFO, "Workorder Name Test Field is Editable.....");
			} else {
				// System.out.println("Workorder Name Test Field is NOT Editable");

				reportFailure("New Workorder page was not opened Successfully.....");
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * VerifyTargetQuantityEditField This function verifies whether target quantity
	 * field on New Work Order Page is editable or not
	 */
	public void verifyTargetQuantityEditField() {
		try {
			test.log(Status.INFO, "Verifying Target Qunaity Edit Field is editable or not.");
			String ExpectedResult = data.get(dataKey).substring(0, data.get(dataKey).length() - 2);
			driver.findElement(By.xpath(prop.getProperty("Panama.MANewWOTgQty_xpath"))).sendKeys(ExpectedResult);
			String ActualResult = driver.findElement(By.xpath(prop.getProperty("Panama.MANewWOTgQty_xpath")))
					.getAttribute("value");
			if (ActualResult.equals(ExpectedResult)) {
				// System.out.println("Target Qunaity Field is Editable");
				test.log(Status.INFO, "Target Qunaity  Field is Editable.....");
			} else {
				// System.out.println("Target Qunaity Field is NOT Editable");

				reportFailure("New Workorder page was not opened Successfully.....");
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * VerifyStartDateReadOnlyField This function verifies whether start date field
	 * in new work order page is readonly or not.
	 */
	public void verifyStartDateReadOnlyField() {
		try {
			test.log(Status.INFO, "Verifying Start Date is readonly or not.");
			String ExpectedResult = "true";
			String ActualResult = driver
					.findElement(By.xpath(prop.getProperty("PanaMA.MANewWOStartDateReadOnly_xpath")))
					.getAttribute("readonly");
			if (ActualResult.equals(ExpectedResult)) {
				// System.out.println("Start Date is not Editable");
				test.log(Status.INFO, "Start Date is not Editable");
			} else {
				// System.out.println("Start Date Editable");

				reportFailure("New Workorder page was not opened Successfully.....");
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * VerifyPatternPanelReadOnlyField This function verifies whether Pattern Panel
	 * field in new work order page is readonly or not.
	 */
	public void verifyPatternPanelReadOnlyField() {
		try {
			test.log(Status.INFO, "Verifying Pattern Panel is readonly or not.");
			String ExpectedResult = "true";
			String ActualResult = driver.findElement(By.xpath(prop.getProperty("Panama.MANewWOPatternPanel_xpath")))
					.getAttribute("readonly");
			if (ActualResult.equals(ExpectedResult)) {
				// System.out.println("Pattern Panel is not Editable");
				test.log(Status.INFO, "Pattern Panel is not Editable");
			} else {
				// System.out.println("Pattern Panel Editable");

				reportFailure("New Workorder page was not opened Successfully.....");
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * VerifyPatternPanelReadOnlyFieldValueEqualToOne This function verifies whether
	 * Pattern Panel field default value is 1.
	 */
	public void verifyPatternPanelReadOnlyFieldValueEqualToOne() {
		try {
			test.log(Status.INFO, "Verifying Pattern Panel Field default value is 1");
			String ExpectedResult = "1";
			String ActualResult = driver.findElement(By.xpath(prop.getProperty("Panama.MANewWOPatternPanel_xpath")))
					.getAttribute("value");
			if (ActualResult.equals(ExpectedResult)) {
				// System.out.println("Pattern Panel Read Only Value Default Value is 1");
				test.log(Status.INFO, "Pattern Panel Read Only Value Default Value is 1");
			} else {
				// System.out.println("Pattern Panel Field's Default Value is NOT equal to 1");

				reportFailure("New Workorder page was not opened Successfully.....");
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * VerifyEndDateReadOnlyField This function verifies whether end date field in
	 * new work order page is readonly or not.
	 */
	public void verifyEndDateReadOnlyField() {
		try {
			test.log(Status.INFO, "Verifying Start Date is readonly or not.");
			String ExpectedResult = "true";
			String ActualResult = driver.findElement(By.xpath(prop.getProperty("PanaMA.MANewWOEndDateReadOnly_xpath")))
					.getAttribute("readonly");
			if (ActualResult.equals(ExpectedResult)) {
				// System.out.println("End Date is not Editable");
				test.log(Status.INFO, "End Date is not Editable");
			} else {
				// System.out.println("End Date Editable");

				reportFailure("New Workorder page was not opened Successfully.....");
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * VerifySideFieldIsDropdown This function verifies whether side field is a drop
	 * down or not
	 */

	public void verifySideFieldIsDropdown() {
		try {
			test.log(Status.INFO, "Verifying Side field is a drop down or not");
			String ExpectedResult = "Top";
			driver.findElement(By.xpath(prop.getProperty("Panama.MANewWOSide_xpath"))).click();
			String ActualResult = driver.findElement(By.xpath(prop.getProperty("Panama.MANewWOSideFirstOption_xpath")))
					.getText();

			if (ActualResult.equals(ExpectedResult)) {
				// System.out.println("Set Field is a dropDown");
				test.log(Status.INFO, "Set Field is a dropDown");
			} else {
				// System.out.println("Set Field is NOT a dropDown");

				reportFailure("New Workorder page was not opened Successfully.....");
			}
			List<WebElement> SideElements = driver
					.findElements(By.xpath(prop.getProperty("Panama.MANewWOOptions_Xpath")));
			if (SideElements.size() == 3) {
				// System.out.println("Set Field is a dropDown");
				test.log(Status.INFO, "Set Field is a dropDown");
			} else {
				// System.out.println("Set Field is NOT a dropDown");

				reportFailure("New Workorder page was not opened Successfully.....");
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * VerifySaveButtonIsDisabled This function verifies whether save button is
	 * disabled initially when the new work order page opens
	 */
	public void verifySaveButtonIsDisabled() {
		try {
			test.log(Status.INFO, "Verifying whether save button is initially disabled when new WO page is opened");
			String ExpectedResult = "true";
			String ActualResult = driver.findElement(By.xpath(prop.getProperty("PanaMA.MANewWOSavebtn_xpath")))
					.getAttribute("disabled");
			if (ActualResult.equals(ExpectedResult)) {
				// System.out.println("Save Button is in disabled state by default");
				test.log(Status.INFO, "Save Button is in disabled state by default");
			} else {
				// System.out.println("Save Button is NOT in disabled state by default");

				reportFailure("New Workorder page was not opened Successfully.....");
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * VerifyCancelButtonIsEnabled This function verifies whether cancel button is
	 * enabled initially when the new work order page opens.
	 */
	public void verifyCancelButtonIsEnabled() {
		try {
			test.log(Status.INFO, "Verifying whether Cancel button is initially enabled when new WO page is opened");
			Boolean ExpectedResult = Boolean.TRUE;
			Boolean ActualResult = driver.findElement(By.xpath(prop.getProperty("PanaMA.MANewWOCancel_xpath")))
					.isEnabled();
			if (ActualResult.equals(ExpectedResult)) {
				// System.out.println("Cancel Button is in Enabled State by Default");
				test.log(Status.INFO, "Cancel Button is in Enabled State by Default");
			} else {
				// System.out.println("Cancel Button is NOT in Enabled State by Default");

				reportFailure("New Workorder page was not opened Successfully.....");
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * validateSideFieldDropDownValues This function verifies whether side field has
	 * three values Top, Bottom and All.
	 */
	public void validateSideFieldDropDownValues() throws InterruptedException {
		try {
			test.log(Status.INFO, "Verifying whether Side Field contains three values top, bottom and all");
			driver.findElement(By.xpath(prop.getProperty("Panama.MANewWOSide_xpath"))).click();
			// System.out.println("Successfully Clicked on Side drop down");
			Thread.sleep(Integer.parseInt(envProp.getProperty("WaitTimeThreeSec")));
			List<WebElement> SideElements = driver
					.findElements(By.xpath(prop.getProperty("Panama.MANewWOOptions_Xpath")));
			// System.out.println("Successfully got Side Drop Down Values");
			Thread.sleep(Integer.parseInt(envProp.getProperty("WaitTimeThreeSec")));
			ArrayList<String> SideNames = new ArrayList<String>(Arrays.asList("Top", "Bottom", "All"));

			int size = SideElements.size() - 1;

			for (int i = 0; i <= size; i++) {
				if ((SideElements.get(i).getText()).equals(SideNames.get(i))) {
					// System.out.println(SideElements.get(i).getText() + " matches " +
					// SideNames.get(i));
					test.log(Status.INFO, SideElements.get(i).getText() + " matches " + SideNames.get(i));
				} else {
					// System.out.println(SideElements.get(i).getText() + " did not match " +
					// SideNames.get(i));

					reportFailure("New Workorder page was not opened Successfully.....");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * VerifySubWorkOrderColumnHeadings This function verifies the column headings
	 * of all the subworkorder table fields.
	 */

	public void verifySubWorkOrderColumnHeadings() throws InterruptedException {
		try {
			test.log(Status.INFO, "Verifying subwork order table headings");
			Thread.sleep(Integer.parseInt(envProp.getProperty("WaitTimeTwoSec")));
			ArrayList<String> ColumnNames = new ArrayList<String>(Arrays.asList("", "", "Line", "Project", "Lot",
					"Side", "Lane", "Target Qty.", "Status", "Schedule Start", "Schedule End"));
			List<WebElement> ColumnElements = driver
					.findElements(By.xpath(prop.getProperty("PanaMA.MASubWOColumnHeadings_xpath")));

			for (int i = 0; i <= ColumnElements.size() - 1; i++) {
				if ((ColumnElements.get(i).getText()).equals(ColumnNames.get(i))) {
					// System.out.println(ColumnElements.get(i).getText() + " matches " +
					// ColumnNames.get(i));
					test.log(Status.INFO, ColumnElements.get(i).getText() + " matches " + ColumnNames.get(i));
				} else {
					// System.out.println(ColumnElements.get(i).getText() + " did not match " +
					// ColumnNames.get(i));

					reportFailure("New Workorder page was not opened Successfully.....");
				}
			}

			// Check if two subworkorder buttons are present
			List<WebElement> NewWorkOrderButtons = driver
					.findElements(By.xpath(prop.getProperty("PanaMA.MASubWOAddWOButton_xpath")));
			if (NewWorkOrderButtons.size() == 2) {
				test.log(Status.INFO, "Two new work order buttons are present");
			} else {
				reportFailure("Two new work order buttons are NOT present" + NewWorkOrderButtons.size());
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * verifySubworkOrderUIComponents This function verifies all the sub workorder
	 * UI components
	 */

	public void verifySubworkOrderUIComponents() throws InterruptedException {
		try {
			test.log(Status.INFO, "Verifying all the sub work order UI components");
			// Click on Line Item in sub work order
			driver.findElement(By.xpath(prop.getProperty("PanaMA.MASubWOLine_xpath"))).click();
			// Check if Line option is dropdown
			if (driver.findElement(By.xpath(prop.getProperty("PanaMA.MASubWOLineDrpDwn_xpath"))) != null) {
				// System.out.println("Line Item is a dropdown and it contains option containing
				// text \"LCS\"");
				test.log(Status.INFO, "Line Item is a dropdown and it contains option containing text \"LCS\"");
				driver.findElement(By.xpath(prop.getProperty("PanaMA.MASubWOLineDrpDwn_xpath"))).click();
			} else {
				// System.out.println("Line Item is NOT a dropdown and it DOES NOT contain
				// option containing text \"LCS\"");
			}

			// Click on Project Item in sub work order
			driver.findElement(By.xpath(prop.getProperty("PanaMA.MASubWOProject_xpath"))).click();
			// Check if Project option is dropdown
			if (driver.findElement(By.xpath(prop.getProperty("PanaMA.MASubWOMinhProject_xpath"))) != null) {
				// System.out.println("Project Item is a dropdown and it contains option
				// containing text \"Project\"");
				test.log(Status.INFO, "Project Item is a dropdown and it contains option containing text \"Project\"");
				driver.findElement(By.xpath(prop.getProperty("PanaMA.MASubWOMinhProject_xpath"))).click();
			} else {
				// System.out.println("Project Item is NOT a dropdown and it DOES NOT contain
				// option containing text \"Project\"");
			}

			// Click on Product Item in sub work order
			driver.findElement(By.xpath(prop.getProperty("PanaMA.MASubWOLot_xpath"))).click();
			// Check if Product option is dropdown
			if (driver.findElement(By.xpath(prop.getProperty("PanaMA.MASubWOMinhProduct_xpath"))) != null) {
				// System.out.println("Product Item is a dropdown and it contains option
				// containing text \"Product\"");
				test.log(Status.INFO, "Product Item is a dropdown and it contains option containing text \"Minh\"");
				driver.findElement(By.xpath(prop.getProperty("PanaMA.MASubWOMinhProduct_xpath"))).click();
			} else {
				// System.out.println("Product Item is NOT a dropdown and it DOES NOT contain
				// option containing text \"Product\"");
			}

			AppKeywords AppKeywordsObject = new AppKeywords();

			// Check if Side is ReadOnly
			WebElement SideElement = driver.findElement(By.xpath(prop.getProperty("PanaMA.MASubWOSide_xpath")));
			if (AppKeywordsObject.verifyIFieldisReadOnly(SideElement) == Boolean.TRUE) {
				test.log(Status.INFO, SideElement.getAttribute("value") + " is editable");
			} else {
				reportFailure("SideElement is NOT editable in sub WO page");
			}

			// Click on Lane Item in sub work order
			driver.findElement(By.xpath(prop.getProperty("PanaMA.MASubWOLane_xpath"))).click();
			// Check if Lane option is dropdown
			if (driver.findElements(By.xpath(prop.getProperty("Panama.MANewWOOptions_Xpath"))) != null) {
				// System.out.println("Lane Item is a dropdown and it contains option containing
				// text \"Lane\"");
				test.log(Status.INFO, "Lane Item is a dropdown and it contains option containing text \"Lane\"");
				Thread.sleep(Integer.parseInt(envProp.getProperty("WaitTimeOneSec")));
				driver.findElements(By.xpath(prop.getProperty("Panama.MANewWOOptions_Xpath"))).get(0).click();
			} else {
				// System.out.println("Lane Item is NOT a dropdown and it DOES NOT contain
				// option containing text \"Lane\"");
			}

			// Check Target Quantity is editable
			WebElement TQElement = driver.findElement(By.xpath(prop.getProperty("PanaMA.MASubWOTgtQty")));
			String TQElementCurrentVal = TQElement.getAttribute("value");
			if (TQElementCurrentVal.equals("1")) {
				test.log(Status.INFO, "Target Quantity default value is 1");
			} else {
				reportFailure("Target Quantity default value is NOT equal to 1");
			}
			TQElement.clear();
			TQElement.sendKeys("2");
			TQElementCurrentVal = TQElement.getAttribute("value");
			if (TQElementCurrentVal.equals("2")) {
				test.log(Status.INFO, "Target Quantity is editable, its value changed to 2");
			} else {
				reportFailure("Target Quantity is NOT editable, its value did not change to 2");
			}

			// Check if Status is ReadOnly
			WebElement StatusElement = driver.findElement(By.xpath(prop.getProperty("PanaMA.MASubWOStatus_xpath")));
			if (AppKeywordsObject.verifyIFieldisReadOnly(StatusElement) == Boolean.TRUE) {
				test.log(Status.INFO, "Status Field in sub WO is NOT editable. Hence Expected Result Met");
			} else {
				reportFailure("Status Field in sub WO is editable. Hence Expected Result is NOT Met");
			}

			// Check if Scheduled Start Date is ReadOnly
			String expectedResult1 = driver.findElement(By.xpath(prop.getProperty("PanaMA.MASubWOStrDt_xpath")))
					.getText();
			// System.out.println(expectedResult1);
			expectedResult1 = expectedResult1.replaceAll("D", "d").replaceAll("Y", "y");
			// System.out.println(expectedResult1);
			Date todaysDate = new Date();
			DateFormat df4 = new SimpleDateFormat(expectedResult1);
			String str4 = df4.format(todaysDate);
			WebElement ScheduledStartElement = driver
					.findElement(By.xpath(prop.getProperty("PanaMA.MASubWOStrDt_xpath")));
			ScheduledStartElement.sendKeys(str4);
			String expectedResult = driver.findElement(By.xpath(prop.getProperty("PanaMA.MASubWOStrDt_xpath")))
					.getText();
			if (expectedResult.equals(str4)) {
				test.log(Status.INFO, "Scheduled Start Date is editable inside sub WO. Hence Expected Result is met.");
			} else {
				reportFailure("Scheduled Start Date is NOT editable inside sub WO. Hence Expected Result is NOT met");
			}

			expectedResult1 = driver.findElement(By.xpath(prop.getProperty("PanaMA.MASubWOEndDt_xpath"))).getText();
			// System.out.println(expectedResult1);
			expectedResult1 = expectedResult1.replaceAll("D", "d").replaceAll("Y", "y");
			// System.out.println(expectedResult1);
			WebElement ScheduledEndElement = driver
					.findElement(By.xpath(prop.getProperty("PanaMA.MASubWOEndDt_xpath")));
			ScheduledEndElement.sendKeys(str4);
			expectedResult = driver.findElement(By.xpath(prop.getProperty("PanaMA.MASubWOEndDt_xpath"))).getText();
			if (expectedResult.equals(str4)) {
				test.log(Status.INFO, "Scheduled End Date is editable inside sub WO. Hence Expected Result is met");
			} else {
				reportFailure("Scheduled Start End is NOT editable, inside sub WO. Hence Expected Result is NOT met");
			}

			// Check if two subworkorder buttons are present
			List<WebElement> NewWorkOrderButtons = driver
					.findElements(By.xpath(prop.getProperty("PanaMA.MASubWOAddWOButton_xpath")));
			if (NewWorkOrderButtons.size() == 2) {
				test.log(Status.INFO, "Two \"New Work Order\" buttons are present. Hence Expected result met.");
			} else {
				reportFailure("Two \"New Work Order\" buttons are NOT present. Hence Expected result is NOT met."
						+ NewWorkOrderButtons.size());
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * verifyIFieldisReadOnly This function verifies if the field is readonly or not
	 */
	public Boolean verifyIFieldisReadOnly(WebElement Element) {
		String ExpectedResult = "true";
		String ActualResult = Element.getAttribute("readonly");
		if (ActualResult.equals(ExpectedResult)) {
			// System.out.println(Element.getAttribute("value") + " is not Editable");
			return Boolean.TRUE;
		} else {
			// System.out.println(Element.getAttribute("value") + " is Editable");
			return Boolean.FALSE;
		}

	}

	/*
	 * public void validateWOCreated() { test.log(Status.INFO,
	 * "Verifying New Workorder Page is launched"); String ExpectedResult =
	 * data.get(dataKey);
	 * 
	 * String actResult =
	 * driver.findElement(By.xpath("//input[@placeholder='Enter Product']")).
	 * getAttribute("value"); //System.out.println(actResult);
	 * 
	 * //System.out.println(ExpectedResult); if(actResult.equals(ExpectedResult)){
	 * //System.out.println(actResult+ " Value is selected"); test.log(Status.INFO,
	 * "Selected Value in product field:  " +actResult);
	 * 
	 * boolean actValue=driver.findElement(By.
	 * xpath("(//span[contains(text(),'Add Subwork')])[1]")).isDisplayed();
	 * //System.out.println(actValue); if(actValue) { test.log(Status.INFO,
	 * "Add Subwokorder.. is Displayed"); }else { test.log(Status.INFO,
	 * "No Add Subwokorder field.. is Displayed"); } }else{
	 * //System.out.println("Unable to Select Value in product field");
	 * 
	 * reportFailure("New Workorder page was not opened Successfully....." ); } }
	 * 
	 * public void validatingSampleFieldWithDateField(){
	 * 
	 * test.log(Status.INFO, "Validating Sample Field With DateField and Counter");
	 * //String expectedResult = data.get(dataKey); String
	 * expectedResult=driver.findElement(By.xpath("//*[@id='txtPrefix']")).
	 * getAttribute("value"); String expectedResult1=driver.findElement(By.xpath(
	 * "//*[@id='s2id_ddlDateFormat']/a/span[1]")).getText();
	 * //System.out.println(expectedResult1); expectedResult1 =
	 * expectedResult1.replaceAll("D", "d").replaceAll("Y", "y");
	 * //System.out.println(expectedResult1); Date todaysDate = new Date();
	 * DateFormat df4 = new SimpleDateFormat(expectedResult1); String str4 =
	 * df4.format(todaysDate);
	 * test.log(Status.INFO,"String in MM dd, yyyy format is: " + str4);
	 * //test.log(Status.INFO,expectedResult1); String
	 * expectedResult2=driver.findElement(By.xpath("//*[@id='txtSeed']")).
	 * getAttribute("value"); String actualResult=""; String except = expectedResult
	 * + expectedResult2 + str4 ; String
	 * result=driver.findElement(By.xpath(prop.getProperty(ObjectKey))).getAttribute
	 * ("value"); test.log(Status.INFO,result);
	 * test.log(Status.INFO,"Sample feild Value is ..." + except);
	 * if(result.equals(except)) actualResult="Is Equals"; else actualResult=
	 * "Is Not Equals"; if(!result.equals(except))
	 * reportFailure("Got Sample Feild result as "+ actualResult);
	 * 
	 * }
	 * 
	 * public void validateTopIteminSideandSave() {
	 * 
	 * //Click on Line Item in sub work order driver.findElement(By.
	 * xpath("//span[@class=\"ng-tns-c8-17 ng-star-inserted\"]")).click(); Actions
	 * action=new Actions(driver); action.sendKeys(driver.findElement(By.
	 * xpath("//span[@class=\"ng-tns-c8-17 ng-star-inserted\"]")),
	 * Keys.TAB).build().perform(); //Click on Project Item in sub work order
	 * driver.findElement(By.
	 * xpath("//span[@class=\"ng-tns-c8-19 ng-star-inserted\"]")).click(); //Side
	 * WebElement SideElement =
	 * driver.findElement(By.xpath("//input[@id=\"mat-input-9\"]"));
	 * action.sendKeys(SideElement, Keys.TAB).build().perform(); //Click on Lane
	 * Item in sub work order driver.findElement(By.
	 * xpath("//span[@class=\"ng-tns-c8-21 ng-star-inserted\"]")).click();
	 * 
	 * /*if(driver.findElements(By.xpath("//span[@class=\"mat-option-text\"]")) !=
	 * null) { //System.out.
	 * println("Lane Item is a dropdown and it contains option containing text \"Lane\""
	 * ); test.log(Status.INFO,
	 * "Lane Item is a dropdown and it contains option containing text \"Lane\"");
	 * try { Thread.sleep(1000); } catch (InterruptedException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); }
	 * driver.findElements(By.xpath("//span[@class=\"mat-option-text\"]")).get(2).
	 * click(); } else { //System.out.
	 * println("Lane Item is NOT a dropdown and it DOES NOT contain option containing text \"Lane\""
	 * ); } //Status //WebElement StatusElement =
	 * driver.findElement(By.xpath("//input[@id=\"mat-input-11\"]"));
	 * //action.sendKeys(StatusElement, Keys.TAB).build().perform();
	 * 
	 * }
	 */

	/**
	 * VerifyNewWorkOrderIsEnabled This function verifies whether after creation of
	 * successful WO, the home page is displayed or not.
	 */
	public void verifyNewWorkOrderIsEnabled() {
		try {
			Boolean ExpectedResult = Boolean.TRUE;

			Boolean ActualResult = driver.findElement(By.xpath(prop.getProperty("Panama.MANewWOBtn_xpath")))
					.isEnabled();
			if (ActualResult.equals(ExpectedResult)) {
				test.log(Status.INFO, "NewWorkOrderElementISEnabled State by Default");
			} else {
				reportFailure("New Workorder  was not saved Successfully.....");
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * Add_Deletebtn This function is to Verify Add and Delete Sub WO.
	 */
	public void Add_Deletebtn() {
		try {
			driver.findElement(By.xpath(prop.getProperty("PanaMA.MASubWOAddWOButton1_xpath"))).click();

			if (driver.findElement(By.xpath(prop.getProperty("PanaMA.MASubWODeleteWOButton_xpath"))).isDisplayed()) {
				// System.out.println("Row is added Successfully");
				test.log(Status.INFO, "Row is added Sucessfully");
			} else {
				// System.out.println("Row is not added successfully");

				reportFailure("Row is not added Sucessfully.....");
			}
			try {
				Thread.sleep(Integer.parseInt(envProp.getProperty("DynamicWaitTime")));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			driver.findElement(By.xpath(prop.getProperty("PanaMA.MASubWODeleteWOButton_xpath"))).click();
			try {
				Thread.sleep(Integer.parseInt(envProp.getProperty("DynamicWaitTime")));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			List<WebElement> rows = driver.findElements(By.xpath(prop.getProperty("PanaMA.MASubWORows_xpath")));
			int size = rows.size();
			// System.out.println("ActualResult is"+ size);
			if (size == 1) {
				// System.out.println("Row is deleted Successfully");
				test.log(Status.INFO, "Row is deleted Sucessfully");
			} else {
				// System.out.println("Row is not deleted successfully");

				reportFailure("Row is not deleted Sucessfully.....");
			}

			/*
			 * driver.findElement(By.xpath("//(//*[contains(text(),'Add Subworkorder')])[1]"
			 * )).click(); //DIV[@id="Content"]/DIV[2]/APP-SUB[1]/DIV[1]/DIV[1]/SPAN[2]
			 * if(driver.findElement(By.xpath(
			 * "//*[@id=\"no-more-tables\"]/mat-table/mat-row[2]/mat-cell[2]/div")).
			 * isDisplayed()){ //System.out.println("Row is added Sucessfully");
			 * test.log(Status.INFO, "Row is added Sucessfully" ); }else{
			 * //System.out.println("Row is not addedd succesfully");
			 * 
			 * reportFailure("Row is not added Sucessfully....." );
			 * //(//*[contains(text(),'Add Subworkorder')])[1] }
			 * 
			 * 
			 * driver.findElement(By.xpath(
			 * "//*[@id=\"no-more-tables\"]/mat-table/mat-row[2]/mat-cell[2]/div")).click();
			 * 
			 * Boolean ExpectedResult = Boolean.FALSE;
			 * 
			 * Boolean ActualResult = driver.findElement(By.xpath(
			 * "//*[@id=\\\"no-more-tables\\\"]/mat-table/mat-row[2]/mat-cell[2]/div")).
			 * isEnabled(); if(ActualResult.equals(ExpectedResult)) {
			 * //System.out.println("Delete button is not present"); test.log(Status.INFO,
			 * "Delete button is not present" ); }else{
			 * //System.out.println("Delete button is present");
			 * 
			 * reportFailure("Delete button is not present" ); //(//*[contains(text(),'Add
			 * Subworkorder')])[1]
			 * 
			 * //*[@id="dvFooter"]/div[2]/button[contains(text(),'Cancel')] }
			 */
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/*
	 * public void Add_Deletebtn() {
	 * driver.findElement(By.xpath("//(//*[contains(text(),'Add Subworkorder')])[1]"
	 * )).click(); //DIV[@id="Content"]/DIV[2]/APP-SUB[1]/DIV[1]/DIV[1]/SPAN[2]
	 * if(driver.findElement(By.xpath(
	 * "//*[@id=\"no-more-tables\"]/mat-table/mat-row[2]/mat-cell[2]/div")).
	 * isDisplayed()){ //System.out.println("Row is added Sucessfully");
	 * test.log(Status.INFO, "Row is added Sucessfully" ); }else{
	 * //System.out.println("Row is not addedd succesfully");
	 * 
	 * reportFailure("Row is not added Sucessfully....." );
	 * //(//*[contains(text(),'Add Subworkorder')])[1] }
	 * 
	 * 
	 * driver.findElement(By.xpath(
	 * "//*[@id=\"no-more-tables\"]/mat-table/mat-row[2]/mat-cell[2]/div")).click();
	 * 
	 * Boolean ExpectedResult = Boolean.FALSE;
	 * 
	 * Boolean ActualResult = driver.findElement(By.xpath(
	 * "//*[@id=\\\"no-more-tables\\\"]/mat-table/mat-row[2]/mat-cell[2]/div")).
	 * isEnabled(); if(ActualResult.equals(ExpectedResult)) {
	 * //System.out.println("Delete button is not present"); test.log(Status.INFO,
	 * "Delete button is not present" ); }else{
	 * //System.out.println("Delete button is present");
	 * 
	 * reportFailure("Delete button is not present" ); //(//*[contains(text(),'Add
	 * Subworkorder')])[1]
	 * 
	 * //*[@id="dvFooter"]/div[2]/button[contains(text(),'Cancel')] } }
	 */

	/*
	 * VerifyInvalidSideEntryIsNotAllowed This function verifies that both main work
	 * order and sub work order sides should be same. It looks for error message
	 * "Side should be same.", when sides are different and saved.
	 */
	public void verifyInvalidSideEntryIsNotAllowed() {
		try {
			test.log(Status.INFO, "Verifying the error message \"Side should be same.\" is displayed or not");
			List<WebElement> starElements = driver
					.findElements(By.xpath(prop.getProperty("PanaMA.NotifyErrorMessage_xpath")));
			String ExpectedValue = "Side should be same.";
			Boolean Found = Boolean.FALSE;
			for (WebElement Element : starElements) {
				if (Element.getText().equals(ExpectedValue)) {
					Found = Boolean.TRUE;
					break;
				}
			}
			if (Found == Boolean.TRUE) {
				test.log(Status.INFO, "Found the Error Message \"Side should be same.\". Hence Expected Result met.");

			} else {
				reportFailure(
						"Did not find the Error Message \"Side should be same.\". Hence Expected Result NOT met.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/*
	 * VerifyInvalidDateErrorMessager This function verifies whether error message
	 * is displayed when end date is less than start date or start date is less than
	 * current system time. The expected error messages
	 */
	public void verifyInvalidDateErrorMessager() {
		try {
			test.log(Status.INFO, "Verifying whether date error messages are displayed or not");
			List<WebElement> starElements = driver
					.findElements(By.xpath(prop.getProperty("PanaMA.NotifyErrorMessage_xpath")));
			// String ExpectedValue ="Schedule end time should be greater than the start
			// time.";
			String ExpectedValue = data.get(dataKey);
			Boolean Found = Boolean.FALSE;
			for (WebElement Element : starElements) {
				// System.out.println(Element.getText());
				if (Element.getText().equals(ExpectedValue)) {
					Found = Boolean.TRUE;
					break;
				}
			}
			if (Found == Boolean.TRUE) {
				test.log(Status.INFO, "Found the Error Message \"" + ExpectedValue + "\". Hence Expected Result met.");
			} else {
				reportFailure(
						"Did not find the Error Message \"" + ExpectedValue + "\". Hence Expected Result NOT met.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/*
	 * typeDate This function accepts Date Data from XLS field. If it is empty it
	 * will fill the now() time plus 5 minutes. Otherwise it will type the date
	 * provided by the XLS Data field
	 */
	public void typeDate() {
		try {
			String Date = data.get(dataKey);
			String object_xpath = prop.getProperty(ObjectKey);
			if (Date.isEmpty()) {
				Double D_plusMinutes = 5.0;
				AppKeywords.D_Minutes = AppKeywords.D_Minutes + D_plusMinutes;
				Date OldDate = new Date();
				Date NewDate = DateUtils.addMinutes(OldDate, (int) Math.round(AppKeywords.D_Minutes));
				DateFormat df4 = new SimpleDateFormat();
				String date1 = df4.format(NewDate);
				driver.findElement(By.xpath(object_xpath)).clear();
				driver.findElement(By.xpath(object_xpath)).sendKeys(date1);
			} else {
				driver.findElement(By.xpath(object_xpath)).clear();
				driver.findElement(By.xpath(object_xpath)).sendKeys(Date);
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * VerifyWOCreatedSuccessMessage This function looks for WO Created success
	 * message on creation of successful WO
	 */
	public void verifyWOCreatedSuccessMessage() {
		try {
			test.log(Status.INFO, "Verifying whether WO Success message is displayed or not");
			Boolean Found = Boolean.FALSE;
			List<WebElement> starElements = null;
			String notifyInfoXpath = prop.getProperty("PanaMA.NotifyErrorMessage_xpath");
			try {
				starElements = driver.findElements(By.xpath(notifyInfoXpath));
			} catch (Exception e) {
				Found = Boolean.FALSE;
			}
			String ExpectedValue = data.get(dataKey);
			for (WebElement Element : starElements) {
				// System.out.println(Element.getText());
				if (Element.getText().equals(ExpectedValue)) {
					Found = Boolean.TRUE;
					break;
				}
			}
			if (Found == Boolean.TRUE) {
				test.log(Status.INFO,
						"Found the Success Message \"" + ExpectedValue + "\". Hence Expected Result met.");
				// driver.close();
			} else {
				reportFailure(
						"Did not find the Success Message \"" + ExpectedValue + "\". Hence Expected Result NOT met.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * enterRandomWOName This function enters random WO name
	 */

	public void enterRandomWOName() {
		try {
			String uuid = UUID.randomUUID().toString().toUpperCase();
			uuid = uuid.substring(0, Math.min(uuid.length(), 3));
			String wo = "WO" + uuid;
			// System.out.println(wo);
			String object_xpath = prop.getProperty(ObjectKey);
			driver.findElement(By.xpath(object_xpath)).sendKeys(wo);
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * enterRandomWOName This function looks for generic error messages. The exact
	 * error message to look for is passed through dataKey
	 */
	public void verifyErrorMessage() {
		try {
			List<WebElement> starElements = driver
					.findElements(By.xpath(prop.getProperty("PanaMA.NotifyErrorMessage_xpath")));
			String ExpectedValue = data.get(dataKey);
			Boolean Found = Boolean.FALSE;
			for (WebElement Element : starElements) {
				// System.out.println(Element.getText());
				if (Element.getText().equals(ExpectedValue)) {
					Found = Boolean.TRUE;
					break;
				}
			}
			if (Found == Boolean.TRUE) {
				test.log(Status.INFO, "Found the Error Message \"" + ExpectedValue + "\". Hence Expected Result met.");
				// driver.close();
			} else {
				reportFailure(
						"Did not find the Error Message \"" + ExpectedValue + "\". Hence Expected Result NOT met.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * verifyWONameErrorMessage This function enters wrong WO NAMEs and looks for
	 * Error message Minimum length is 4 character is required
	 */
	public void verifyWONameErrorMessage() {
		try {
			String woName = data.get(dataKey);
			test.log(Status.INFO, "Verifying WorkOrder Name is valid or not for value : " + woName);

			boolean errorMsg = driver.findElement(By.xpath(prop.getProperty("PanaMA.MANewWONameErrorMsg")))
					.isDisplayed();
			// System.out.println("Error Msg is: " +errorMsg);

			if (errorMsg) {
				test.log(Status.INFO, "Found the WOName Error Message for the value. Hence Expected Result is met.");
			} else {
				reportFailure(woName
						+ "Did not find the Error Message for the value entered. Hence Expected Result is NOT met.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/*
	 * public void verifyWONameFieldSplChar() { test.log(Status.INFO,
	 * "Validating Workorder Name field with Special Characters "); String errorVal
	 * = driver.findElement(By.xpath("//input[@formcontrolname='workOrderName']")).
	 * getAttribute("aria-invalid");
	 * 
	 * if(errorVal.equals("true")) { WebElement WONameError = driver.findElement(By.
	 * xpath("//mat-error[contains(text(),\"Minimum length is 4 character\")]"));
	 * test.log(Status.INFO, "Found the WOName Error Message for the value: " +
	 * WONameError.getText()+ " Hence Expected Result met.");
	 * 
	 * } else{ reportFailure(" Provide date is valid for the WorkOrderName field");
	 * 
	 * 
	 * } }
	 */

	/**
	 * verifyWONameFieldDoesNotDisplayErrorMessage This function enters valid WO
	 * Names given in XLS data in WO Name field and makes sure that error message is
	 * not displayed
	 */
	public void verifyWONameFieldDoesNotDisplayErrorMessage() {
		try {
			test.log(Status.INFO, "Validating Workorder Name field with valid values");
			String errorVal = driver.findElement(By.xpath(prop.getProperty("Panama.MANewWOName_xpath")))
					.getAttribute("aria-invalid");

			if (errorVal.equals("false")) {
				test.log(Status.INFO, "Successfully verified that WO Name Error Message is NOT displayed. "
						+ "Hence expected result is met");
			} else {
				reportFailure("WO Name Error Message is displayed. " + "Hence expected result is NOT met");

			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * typeNumber removes The floating point .0 from the end. By default XLS returns
	 * data as 1234.0, we need only 1234. Hence this function is required.
	 */
	public void typeNumber() {
		try {
			String Data = data.get(dataKey);
			String ObjKey_Xpath = prop.getProperty(ObjectKey);
			driver.findElement(By.xpath(ObjKey_Xpath)).clear();
			driver.findElement(By.xpath(ObjKey_Xpath)).sendKeys(Data.substring(0, Data.length() - 2));
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * verifyTargetQuantityDoesNotAcceptSpacesInBetweenValue This function enters "1
	 * 2" in the target quantity field and makes sure that "1 2" is interpreted as
	 * "12". So spaces are ignored in target quantity field.
	 */
	public void verifyTargetQuantityDoesNotAcceptSpacesInBetweenValue() {
		try {
			test.log(Status.INFO, "Validating Target Qunaity Field with invalid values");
			String ExpectedResult = data.get(dataKey);
			String ExpectedResult1 = ExpectedResult.replaceAll("\\s", "");
			String ObservedResult = driver.findElement(By.xpath(prop.getProperty("Panama.MANewWOTgQty_xpath")))
					.getAttribute("value");
			test.log(Status.INFO, "Target Qunaity Value:" + ObservedResult);
			if (ObservedResult.equals(ExpectedResult1)) {
				test.log(Status.INFO, "Target Qunaity is not accepting spaces in between");
			} else {
				reportFailure("Target Qunaity is accepting spaces in between. Hence it is failed");

			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * verifyTargetQuantityDoesNotAcceptInvalidValue This function verifies Target
	 * Qunaity does not accept invalid values.
	 */
	public void verifyTargetQuantityDoesNotAcceptInvalidValue() {
		try {
			test.log(Status.INFO, "Validating Target Qunaity Field with invalid values");
			String errorVal = driver.findElement(By.xpath(prop.getProperty("Panama.MANewWOTgQty_xpath")))
					.getAttribute("aria-invalid");

			if (errorVal.equals("true")) {
				test.log(Status.INFO, "Successfully verified target quantity does not accept invalid values");
			} else {
				reportFailure("Target Quantity is accepting invalid values. Hence it is fail.");

			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * verifyTargetQuantityAcceptsValidValue Method to Check Target Quantity
	 * Editable and Accepts Valid Values.
	 */
	public void verifyTargetQuantityAcceptsValidValue() {
		try {
			test.log(Status.INFO, "Verifying Target Qunaity Edit Field is editable or not.");
			String ExpectedResult = data.get(dataKey);
			String ExpectedResult1 = ExpectedResult.substring(0, ExpectedResult.length() - 2);
			String ActualResult = driver.findElement(By.xpath(prop.getProperty("Panama.MANewWOTgQty_xpath")))
					.getAttribute("value");
			if (ActualResult.equals(ExpectedResult1)) {
				// System.out.println("Target Qunaity Field is Editable");
				test.log(Status.INFO, "Target Qunaity  Field is Editable.....");
				String errorVal = driver.findElement(By.xpath("//input[@formcontrolname='quantity']"))
						.getAttribute("aria-invalid");

				if (errorVal.equals("false")) {
					test.log(Status.INFO, "Successfully verified target quantity accepts valid value");
				} else {
					reportFailure("Successfully verified target quantity accepts valid value");

				}
			} else {
				// System.out.println("Target Qunaity Field is NOT Editable");

				reportFailure("New Workorder page was not opened Successfully.....");
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/*
	 * cleanup This function cleans up the created WO using the DB transaction
	 * command. Please note this function does not delete reserved WO.
	 */
	public void cleanup() throws SQLException {
		try {
			DBTransaction db1 = new DBTransaction(envProp);
			// db1.DeleteWO("WOB_1");
			db1.DeleteWO(data.get(dataKey));
			System.out.println("wait");
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/*
	 * cleanup This function cleans up the Passed WO using the DB transaction
	 * command. String WO Name
	 */
	public void cleanup(String WOName) throws SQLException {
		try {
			DBTransaction db1 = new DBTransaction(envProp);
			// db1.DeleteWO("WOB_1");
			db1.DeleteWO(WOName);
			System.out.println("wait");
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/*
	 * CreateWorkOrder This function will create WO using DB Transaction commands.
	 */
	public void createWorkOrder() throws SQLException, InterruptedException {
		try {
			String oldWOName = data.get(dataKey);
			try {
				cleanup();
			} catch (Exception e) {
				deleteWOFromWOList();
			}
			// Create Existing Work Order with Status!=Hold/Closed, Lane:Rear,
			// TimeOverlap:Yes
			String side = "A";
			String productName = "Product1";
			String prductInFold = "16"; // -----?
			String patternPerPanem = "1";
			String productSetupId = "26";
			String mjsid = "Project-0703_1";
			String lineInFold = "3"; // -----?
			String workorderstatus = "Created";
			String statusId = "1";
			String lane = "R";

			// For Existing Work Order
			// String scheduledStartString =
			// scheduledStart.ToUniversalTime().ToString("yyyy-MM-dd") + "T" +
			// scheduledStart.ToUniversalTime().ToString("hh:mm:ss") + "Z";
			// String scheduledEndString =
			// scheduledEnd.ToUniversalTime().ToString("yyyy-MM-dd") + "T" +
			// scheduledEnd.ToUniversalTime().ToString("hh:mm:ss") + "Z";
			// String scheduledStartString = "2019-01-21T09:04:20.961Z";
			// String scheduledEndString = "2019-01-21T10:24:20.961Z";

			Double D_plusMinutes = 5.0;
			AppKeywords.D_Minutes = AppKeywords.D_Minutes + D_plusMinutes;
			Date OldDate = new Date();
			Date NewStartDate = DateUtils.addMinutes(OldDate, (int) Math.round(AppKeywords.D_Minutes));
			Date NewStartDate1 = DateUtils.addHours(NewStartDate, 6);
			DateFormat df4 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
			String scheduledStartString = df4.format(NewStartDate1);

			D_plusMinutes = 60.0;
			AppKeywords.D_Minutes = AppKeywords.D_Minutes + D_plusMinutes;
			Date NewEndDate = DateUtils.addMinutes(OldDate, (int) Math.round(AppKeywords.D_Minutes));
			Date NewEndDate1 = DateUtils.addHours(NewEndDate, 6);
			DateFormat df4_1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
			String scheduledEndString = df4_1.format(NewEndDate1);

			String lotName = "Product1";
			String subWOSide = "T";

			String woJson = "{\"WorkOrderName\":\"" + oldWOName + "\",\"WorkOrderId\":0,\"ProductName\":\""
					+ productName + "\",\"ProductInfoId\":\"" + prductInFold + "\",\"PatternPerPanel\":"
					+ patternPerPanem + ",\"Side\":\"" + side + "\",\"PatternQuantity\":101,\"WorkOrderProductId\":1,"
					+ "\"SubWorkOrders\":[{\"SubWorkOrderId\":0,\"WoProductRelationId\":0,\"ProductSetupId\":"
					+ productSetupId + ",\"Lane\":\"" + lane
					+ "\",\"TargetQuantity\":1,\"CompletedQuantity\":0,\"ScheduleStartTime\":\"" + scheduledStartString
					+ "\",\"ScheduleEndTime\":\"" + scheduledEndString
					+ "\",\"ActualStartTime\":\"2019-04-28T09:04:20.961Z\",\"ActualEndTime\":\"2019-04-28T09:04:20.961Z\",\"ReworkedQuantity\":0,\"re_subworkorder_id\":0,\"WorkOrderStatusId\":"
					+ statusId + ",\"WorkOrderStatus\":\"" + workorderstatus
					+ "\",\"CreatedDate\":\"0001-01-01T00:00:00\",\"CreatedBy\":0,\"UpdatedDate\":\"0001-01-01T00:00:00\",\"UpdatedBy\":0,\"Mjsid\":\""
					+ mjsid + "\",\"LineName\":\"LCSIMLINE1TRAY\",\"LineInfoId\":" + lineInFold + ",\"LotName\":\""
					+ lotName + "\",\"Side\":\"" + subWOSide + "\"}]}";
			// '{"WorkOrderName":"WO29_10", "WorkOrderId":0, "ProductName":"Product1",
			// "ProductInfoId":"16","PatternPerPanel":1,"Side":"A","PatternQuantity":101,"WorkOrderProductId":1,"SubWorkOrders":[{"SubWorkOrderId":0,"WoProductRelationId":0,"ProductSetupId":26,"Lane":"R","TargetQuantity":1,"CompletedQuantity":0,"ScheduleStartTime":"2019-01-20T09:04:20.961Z","ScheduleEndTime":"2019-01-20T09:24:20.961Z","ActualStartTime":"2019-04-28T09:04:20.961Z","ActualEndTime":"2019-04-28T09:04:20.961Z","ReworkedQuantity":0,"re_subworkorder_id":0,"WorkOrderStatusId":1,"WorkOrderStatus":"Created","CreatedDate":"0001-01-01T00:00:00","CreatedBy":0,"UpdatedDate":"0001-01-01T00:00:00","UpdatedBy":0,"Mjsid":"Project-0703_1","LineName":"LCSIMLINE1TRAY","LineInfoId":3,"LotName":"Product1","Side":"T"}]}',
			// '')
			DBTransaction db1 = new DBTransaction(envProp);
			db1.RunPgSqlTransaction(woJson);

			D_Hours = 0.0;
			D_Minutes = 5.0;
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/*
	 * create7WOsFor7Days This WO creates 7 WOs for 7 Days
	 */
	public void create7WOsFor7Days() throws SQLException, InterruptedException {
		try {
			List<String> WOJson_Str = new ArrayList<String>(Arrays.asList(
					"{\"WorkOrderName\":\"WO033_Sunday_A\",\"WorkOrderId\":0,\"ProductName\":\"Product1\",\"ProductInfoId\":\"16\",\"PatternPerPanel\":1,\"Side\":\"A\",\"PatternQuantity\":101,\"WorkOrderProductId\":1,\"SubWorkOrders\":[{\"SubWorkOrderId\":0,\"WoProductRelationId\":0,\"ProductSetupId\":26,\"Lane\":\"R\",\"TargetQuantity\":1,\"CompletedQuantity\":0,\"ScheduleStartTime\":\"2018-01-21T14:33:40.153Z\",\"ScheduleEndTime\":\"2018-01-21T15:33:40.153Z\",\"ActualStartTime\":\"2019-04-28T09:04:20.961Z\",\"ActualEndTime\":\"2019-04-28T09:04:20.961Z\",\"ReworkedQuantity\":0,\"re_subworkorder_id\":0,\"WorkOrderStatusId\":1,\"WorkOrderStatus\":\"Created\",\"CreatedDate\":\"0001-01-01T00:00:00\",\"CreatedBy\":0,\"UpdatedDate\":\"0001-01-01T00:00:00\",\"UpdatedBy\":0,\"Mjsid\":\"Project-0703_1\",\"LineName\":\"LCSIMLINE1TRAY\",\"LineInfoId\":3,\"LotName\":\"Product1\",\"Side\":\"T\"}]}",
					"{\"WorkOrderName\":\"WO033_Monday_A\",\"WorkOrderId\":0,\"ProductName\":\"Product1\",\"ProductInfoId\":\"16\",\"PatternPerPanel\":1,\"Side\":\"A\",\"PatternQuantity\":101,\"WorkOrderProductId\":1,\"SubWorkOrders\":[{\"SubWorkOrderId\":0,\"WoProductRelationId\":0,\"ProductSetupId\":26,\"Lane\":\"R\",\"TargetQuantity\":1,\"CompletedQuantity\":0,\"ScheduleStartTime\":\"2018-01-22T14:33:40.153Z\",\"ScheduleEndTime\":\"2018-01-22T15:33:40.153Z\",\"ActualStartTime\":\"2019-04-28T09:04:20.961Z\",\"ActualEndTime\":\"2019-04-28T09:04:20.961Z\",\"ReworkedQuantity\":0,\"re_subworkorder_id\":0,\"WorkOrderStatusId\":1,\"WorkOrderStatus\":\"Created\",\"CreatedDate\":\"0001-01-01T00:00:00\",\"CreatedBy\":0,\"UpdatedDate\":\"0001-01-01T00:00:00\",\"UpdatedBy\":0,\"Mjsid\":\"Project-0703_1\",\"LineName\":\"LCSIMLINE1TRAY\",\"LineInfoId\":3,\"LotName\":\"Product1\",\"Side\":\"T\"}]}",
					"{\"WorkOrderName\":\"WO033_Tuesday_A\",\"WorkOrderId\":0,\"ProductName\":\"Product1\",\"ProductInfoId\":\"16\",\"PatternPerPanel\":1,\"Side\":\"A\",\"PatternQuantity\":101,\"WorkOrderProductId\":1,\"SubWorkOrders\":[{\"SubWorkOrderId\":0,\"WoProductRelationId\":0,\"ProductSetupId\":26,\"Lane\":\"R\",\"TargetQuantity\":1,\"CompletedQuantity\":0,\"ScheduleStartTime\":\"2018-01-23T14:33:40.153Z\",\"ScheduleEndTime\":\"2018-01-23T15:33:40.153Z\",\"ActualStartTime\":\"2019-04-28T09:04:20.961Z\",\"ActualEndTime\":\"2019-04-28T09:04:20.961Z\",\"ReworkedQuantity\":0,\"re_subworkorder_id\":0,\"WorkOrderStatusId\":1,\"WorkOrderStatus\":\"Created\",\"CreatedDate\":\"0001-01-01T00:00:00\",\"CreatedBy\":0,\"UpdatedDate\":\"0001-01-01T00:00:00\",\"UpdatedBy\":0,\"Mjsid\":\"Project-0703_1\",\"LineName\":\"LCSIMLINE1TRAY\",\"LineInfoId\":3,\"LotName\":\"Product1\",\"Side\":\"T\"}]}",
					"{\"WorkOrderName\":\"WO033_Wednesday_A\",\"WorkOrderId\":0,\"ProductName\":\"Product1\",\"ProductInfoId\":\"16\",\"PatternPerPanel\":1,\"Side\":\"A\",\"PatternQuantity\":101,\"WorkOrderProductId\":1,\"SubWorkOrders\":[{\"SubWorkOrderId\":0,\"WoProductRelationId\":0,\"ProductSetupId\":26,\"Lane\":\"R\",\"TargetQuantity\":1,\"CompletedQuantity\":0,\"ScheduleStartTime\":\"2018-01-24T14:33:40.153Z\",\"ScheduleEndTime\":\"2018-01-24T15:33:40.153Z\",\"ActualStartTime\":\"2019-04-28T09:04:20.961Z\",\"ActualEndTime\":\"2019-04-28T09:04:20.961Z\",\"ReworkedQuantity\":0,\"re_subworkorder_id\":0,\"WorkOrderStatusId\":1,\"WorkOrderStatus\":\"Created\",\"CreatedDate\":\"0001-01-01T00:00:00\",\"CreatedBy\":0,\"UpdatedDate\":\"0001-01-01T00:00:00\",\"UpdatedBy\":0,\"Mjsid\":\"Project-0703_1\",\"LineName\":\"LCSIMLINE1TRAY\",\"LineInfoId\":3,\"LotName\":\"Product1\",\"Side\":\"T\"}]}",
					"{\"WorkOrderName\":\"WO033_Thursday_A\",\"WorkOrderId\":0,\"ProductName\":\"Product1\",\"ProductInfoId\":\"16\",\"PatternPerPanel\":1,\"Side\":\"A\",\"PatternQuantity\":101,\"WorkOrderProductId\":1,\"SubWorkOrders\":[{\"SubWorkOrderId\":0,\"WoProductRelationId\":0,\"ProductSetupId\":26,\"Lane\":\"R\",\"TargetQuantity\":1,\"CompletedQuantity\":0,\"ScheduleStartTime\":\"2018-01-25T14:33:40.153Z\",\"ScheduleEndTime\":\"2018-01-25T15:33:40.153Z\",\"ActualStartTime\":\"2019-04-28T09:04:20.961Z\",\"ActualEndTime\":\"2019-04-28T09:04:20.961Z\",\"ReworkedQuantity\":0,\"re_subworkorder_id\":0,\"WorkOrderStatusId\":1,\"WorkOrderStatus\":\"Created\",\"CreatedDate\":\"0001-01-01T00:00:00\",\"CreatedBy\":0,\"UpdatedDate\":\"0001-01-01T00:00:00\",\"UpdatedBy\":0,\"Mjsid\":\"Project-0703_1\",\"LineName\":\"LCSIMLINE1TRAY\",\"LineInfoId\":3,\"LotName\":\"Product1\",\"Side\":\"T\"}]}",
					"{\"WorkOrderName\":\"WO033_Friday_A\",\"WorkOrderId\":0,\"ProductName\":\"Product1\",\"ProductInfoId\":\"16\",\"PatternPerPanel\":1,\"Side\":\"A\",\"PatternQuantity\":101,\"WorkOrderProductId\":1,\"SubWorkOrders\":[{\"SubWorkOrderId\":0,\"WoProductRelationId\":0,\"ProductSetupId\":26,\"Lane\":\"R\",\"TargetQuantity\":1,\"CompletedQuantity\":0,\"ScheduleStartTime\":\"2018-01-26T14:33:40.153Z\",\"ScheduleEndTime\":\"2018-01-26T15:33:40.153Z\",\"ActualStartTime\":\"2019-04-28T09:04:20.961Z\",\"ActualEndTime\":\"2019-04-28T09:04:20.961Z\",\"ReworkedQuantity\":0,\"re_subworkorder_id\":0,\"WorkOrderStatusId\":1,\"WorkOrderStatus\":\"Created\",\"CreatedDate\":\"0001-01-01T00:00:00\",\"CreatedBy\":0,\"UpdatedDate\":\"0001-01-01T00:00:00\",\"UpdatedBy\":0,\"Mjsid\":\"Project-0703_1\",\"LineName\":\"LCSIMLINE1TRAY\",\"LineInfoId\":3,\"LotName\":\"Product1\",\"Side\":\"T\"}]}",
					"{\"WorkOrderName\":\"WO033_Saturday_A\",\"WorkOrderId\":0,\"ProductName\":\"Product1\",\"ProductInfoId\":\"16\",\"PatternPerPanel\":1,\"Side\":\"A\",\"PatternQuantity\":101,\"WorkOrderProductId\":1,\"SubWorkOrders\":[{\"SubWorkOrderId\":0,\"WoProductRelationId\":0,\"ProductSetupId\":26,\"Lane\":\"R\",\"TargetQuantity\":1,\"CompletedQuantity\":0,\"ScheduleStartTime\":\"2018-01-27T14:33:40.153Z\",\"ScheduleEndTime\":\"2018-01-27T15:33:40.153Z\",\"ActualStartTime\":\"2019-04-28T09:04:20.961Z\",\"ActualEndTime\":\"2019-04-28T09:04:20.961Z\",\"ReworkedQuantity\":0,\"re_subworkorder_id\":0,\"WorkOrderStatusId\":1,\"WorkOrderStatus\":\"Created\",\"CreatedDate\":\"0001-01-01T00:00:00\",\"CreatedBy\":0,\"UpdatedDate\":\"0001-01-01T00:00:00\",\"UpdatedBy\":0,\"Mjsid\":\"Project-0703_1\",\"LineName\":\"LCSIMLINE1TRAY\",\"LineInfoId\":3,\"LotName\":\"Product1\",\"Side\":\"T\"}]}"));
			DBTransaction db1 = new DBTransaction(envProp);
			for (String woJson : WOJson_Str) {
				db1.RunPgSqlTransaction(woJson);
				System.out.println("wait");
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/*
	 * VerifyWOInWeekView_method This function verifies the WO in a week.
	 */
	public void VerifyWOInWeekView_method() throws Throwable {
		try {
			driver.navigate().to(prop.getProperty("ConfigurationUrl"));
			driver.navigate().to(prop.getProperty("SchedulerUrl"));

			// String WOName = data.get(dataKey);
			String WOName_xpath = prop.getProperty("PanaMA.Scheduler_WONameIn_SchedulerAppointment_xpath");

			WebElement element = null;

			String NewDate = "1/21/2018";
			driver.findElement(By.xpath(prop.getProperty("PanaMA.Scheduler_SelectData_xpath"))).clear();
			driver.findElement(By.xpath(prop.getProperty("PanaMA.Scheduler_SelectData_xpath"))).sendKeys(NewDate);
			driver.findElement(By.xpath(prop.getProperty("PanaMA.ApplyBtn_xpath"))).click();
			driver.findElement(By.xpath(prop.getProperty("PanaMA.Scheduler_WeekButton_xpath"))).click();

			List<String> DayStrings = new ArrayList<String>(
					Arrays.asList("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"));

			for (String Day : DayStrings) {
				String WOName_xpath1 = WOName_xpath.replace("$", "WO033_" + Day + "_A");
				element = driver.findElement(By.xpath(WOName_xpath1));
				if (element != null) {
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
					test.log(Status.INFO, "Successfully verified WO Name with " + Day + " in it");
				} else {
					reportFailure("Could not verify WO Name with " + Day + " in it");

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/*
	 * verifyBackgroundColorOfClosedWO This function verifies whether background
	 * color of closed WO matches to that of Legend Closed Color
	 */
	public void verifyBackgroundColorOfClosedWO() throws Throwable {
		try {
			boolean Result;
			driver.navigate().to(prop.getProperty("ConfigurationUrl"));
			driver.navigate().to(prop.getProperty("SchedulerUrl"));
			Result = false;
			String WOName = data.get(dataKey);
			String WOName_xpath = prop.getProperty("PanaMA.Scheduler_WONameIn_SchedulerAppointment_xpath");
			String WOName_xpath1 = WOName_xpath.replace("$", WOName);
			WebElement element = null;

			try {
				element = driver.findElement(By.xpath(WOName_xpath1));
			} catch (Exception e) {
				Date OldDate = Calendar.getInstance().getTime();
				DateFormat formatter = new SimpleDateFormat("M/dd/yyyy");
				Date addDays = DateUtils.addDays(OldDate, 1);
				String NewDate = formatter.format(addDays);
				driver.findElement(By.xpath(prop.getProperty("PanaMA.Scheduler_SelectData_xpath"))).clear();
				driver.findElement(By.xpath(prop.getProperty("PanaMA.Scheduler_SelectData_xpath"))).sendKeys(NewDate);
				driver.findElement(By.xpath(prop.getProperty("PanaMA.ApplyBtn_xpath"))).click();
				try {
					element = driver.findElement(By.xpath(WOName_xpath1));
				} catch (Exception e1) {
					reportFailure("Could not find WO Name on the Scheduler Screen" + WOName);
				}

			}
			if (element != null) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			}
			driver.findElement(By.xpath(WOName_xpath1)).click();
			driver.findElement(By.xpath(prop.getProperty("PanaMA.SchedulerPopupForceClosedBtn_xpath"))).click();
			WebDriverWait wait = new WebDriverWait(driver, 120);
			wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(prop.getProperty("PanaMA.SchedulerPopupForceClosedOkBtn_xpath"))));

			// Thread.sleep(120000);
			// driver.findElement(By.xpath("//button[contains(text(),\"Save\")]")).click();
			driver.findElement(By.xpath(prop.getProperty("PanaMA.SchedulerPopupForceClosedOkBtn_xpath"))).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath(prop.getProperty("PanaMA.SchedulerPopupForceClosedOkBtn_xpath"))).click();

			// WebDriverWait wait = new WebDriverWait(driver,120);
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath(WOName_xpath1)));
			Thread.sleep(Integer.parseInt(envProp.getProperty("DynamicWaitTime")));
			element = driver.findElement(By.xpath(WOName_xpath1));
			if (element != null) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			}
			String WOBalloonColor = driver.findElement(By.xpath(WOName_xpath1)).getCssValue("background");
			Pattern c = Pattern.compile("(rgb\\(.*\\)).*");
			Matcher m = c.matcher(WOBalloonColor);
			String WOBalloonColor1 = null;
			if (m.matches()) {
				WOBalloonColor1 = m.group(1);
			}

			// String WOBalloonColor1 = WOBalloonColor.substring(0,16);
			String LegendColorXpath = prop.getProperty("PanaMA.Scheduler_LegendColor_xpath");
			String LegendColorXpath1 = LegendColorXpath.replace("$", WOBalloonColor1);
			Result = VerifyPresent(LegendColorXpath1, 5, 2);
			if (Result == true) {
				test.log(Status.INFO, "Successfully verified the color of WO balloon background" + WOName);
			} else {
				reportFailure("Could not verify the color of WO balloon background" + WOName);
			}

			String Legend_Closed_xpath = prop.getProperty("PanaMA.SchedulerLegendClosed_xpath");

			String Closed_style = driver.findElement(By.xpath(Legend_Closed_xpath)).getAttribute("style");

			if (Closed_style.contains(WOBalloonColor1)) {
				test.log(Status.INFO, "Successfully verified the color of WO balloon background" + WOName);
			} else {
				reportFailure("Could not verify the color of WO balloon background" + WOName);
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/*
	 * verifyWODeletionFromWOList This function deletes the WO from the WO List, and
	 * verifies whether the WO got deleted successfully or not.
	 */
	public void verifyWODeletionFromWOList() throws Throwable {
		try {
			deleteWOFromWOList();
			Boolean Result;
			String WOName = data.get("WO Name");
			String WONameLink_xpath = prop.getProperty("PanaMA.WOListWOLink_xpath");
			String WONameLink_xpath1 = WONameLink_xpath.replace("$", WOName);
			Result = VerifyPresent(WONameLink_xpath1, 1, 1);
			if (Result == false) {
				test.log(Status.INFO, "Successfully verified WO deleted from WO List");
			} else {
				reportFailure("WO is not deleted from WO List");
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/*
	 * deleteWOFromWOList This function deletes the WO from the WO List.
	 */
	public void deleteWOFromWOList() throws InterruptedException {
		try {
			String WOName = data.get("WO Name");
			driver.navigate().to(prop.getProperty("WorkorderListUrl"));
			Date OldDate = Calendar.getInstance().getTime();
			DateFormat formatter = new SimpleDateFormat("M/dd/yyyy");
			Date subDays = DateUtils.addMonths(OldDate, -1);
			String SelStartDate = formatter.format(subDays);
			Date addDays = DateUtils.addDays(OldDate, 2);
			String SelEndDate = formatter.format(addDays);

			driver.findElement(By.xpath(prop.getProperty("PanaMA.WorkOrderListSelStartDate_xpath"))).clear();
			driver.findElement(By.xpath(prop.getProperty("PanaMA.WorkOrderListSelStartDate_xpath")))
					.sendKeys(SelStartDate);
			driver.findElement(By.xpath(prop.getProperty("PanaMA.WorkOrderListSelEndDate_xpath"))).clear();
			driver.findElement(By.xpath(prop.getProperty("PanaMA.WorkOrderListSelEndDate_xpath"))).sendKeys(SelEndDate);
			driver.findElement(By.xpath(prop.getProperty("PanaMA.ApplyBtn_xpath"))).click();
			WebDriverWait wait = new WebDriverWait(driver, 60);
			String deleteBtnXpath = prop.getProperty("PanaMA.WorkOrderListDeleteBtn_xpath");
			String deleteBtnXpath1 = deleteBtnXpath.replace("$", WOName);
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(deleteBtnXpath1)));
			} catch (Exception e) {
				test.log(Status.INFO, "WO Name does not exist, hence it is already deleted.");
				return;
			}
			driver.findElement(By.xpath(deleteBtnXpath1)).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(prop.getProperty("PanaMA.OKBtn_xpath"))));
			// Thread.sleep(3);
			driver.findElement(By.xpath(prop.getProperty("PanaMA.OKBtn_xpath"))).click();
			// Thread.sleep(5);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(prop.getProperty("PanaMA.OKBtn_xpath"))));
			driver.findElement(By.xpath(prop.getProperty("PanaMA.OKBtn_xpath"))).click();
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * verifyFilterSuccessful This function verifies whether filter on WO List is
	 * successful to not.
	 */
	public void verifyFilterSuccessful() throws Throwable {
		try {
			String WOName = data.get("WO Name");
			String WOListLink_xpath = prop.getProperty("PanaMA.WOListWOLink_xpath");
			String WOListLink_xpath1 = WOListLink_xpath.replace("$", WOName);
			driver.navigate().to(prop.getProperty("WorkorderListUrl"));
			String SelectWOCheckbox_xpath = prop.getProperty("PanaMA.WorkOrderListSelectWOStatusCheckbox_xpath");
			String SelectWOCheckbox_xpath1 = SelectWOCheckbox_xpath.replace("$", "Created");
			Date OldDate = Calendar.getInstance().getTime();
			DateFormat formatter = new SimpleDateFormat("M/dd/yyyy");
			Date subDays = DateUtils.addDays(OldDate, -1);
			String SelStartDate = formatter.format(subDays);
			Date addDays = DateUtils.addDays(OldDate, 2);
			String SelEndDate = formatter.format(addDays);

			driver.findElement(By.xpath(prop.getProperty("PanaMA.WorkOrderListSelStartDate_xpath"))).clear();
			driver.findElement(By.xpath(prop.getProperty("PanaMA.WorkOrderListSelStartDate_xpath")))
					.sendKeys(SelStartDate);
			driver.findElement(By.xpath(prop.getProperty("PanaMA.WorkOrderListSelEndDate_xpath"))).clear();
			driver.findElement(By.xpath(prop.getProperty("PanaMA.WorkOrderListSelEndDate_xpath"))).sendKeys(SelEndDate);
			driver.findElement(By.xpath(prop.getProperty("PanaMA.WorkOrderListSelectWOStatus_xpath"))).click();
			driver.findElement(By.xpath(SelectWOCheckbox_xpath1)).click();
			Actions action = new Actions(driver);
			action.sendKeys(Keys.ESCAPE).perform();
			driver.findElement(By.xpath(prop.getProperty("PanaMA.ApplyBtn_xpath"))).click();
			Boolean Result = VerifyPresent(WOListLink_xpath1, 5, 2);
			if (Result == true) {
				test.log(Status.INFO, "Able to successfully filter the WO name");
			} else {
				reportFailure("Could not filter WO name based on the filter state created");
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/*
	 * verifySchedulerIsDefaultHomeScreen This method verifies whether Scheduler is
	 * default home screen or not.
	 */
	public void verifySchedulerIsDefaultHomeScreen() throws Throwable {
		try {
			String titleScheduler_Xpath = prop.getProperty("PanaMA.Scheduler_titleScheduler_xpath");
			boolean Result;
			Result = VerifyPresent(titleScheduler_Xpath, 5, 2);
			if (Result == true) {
				test.log(Status.INFO, "Successfully verified Scheduler Screen is displayed");
			} else {
				reportFailure("Scheduler Screen is not displayed ");

			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/*
	 * NavigateToSchedulerScreenFromConfigurationScreen This method verifies whether
	 * navigation to scheduler screen happens correctly from configuration screen.
	 */
	public void NavigateToSchedulerScreenFromConfigurationScreen() throws Throwable {
		try {
			boolean Result = false;
			test.log(Status.INFO, "Navigate to Scheduler Screen");
			String titleScheduler_Xpath = prop.getProperty("PanaMA.Scheduler_titleScheduler_xpath");

			driver.navigate().to(prop.getProperty("ConfigurationUrl"));
			driver.navigate().to(prop.getProperty("SchedulerUrl"));
			Result = VerifyPresent(titleScheduler_Xpath, 5, 2);
			if (Result == true) {
				test.log(Status.INFO, "Successfully verified Scheduler Screen is displayed");
			} else {
				reportFailure("Scheduler Screen is not displayed ");
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/*
	 * verifySchedulerTitle This method verifies the title of Scheduler Screen.
	 */
	public void verifySchedulerTitle() throws Throwable {
		try {
			String titleScheduler_Xpath = prop.getProperty("PanaMA.Scheduler_titleScheduler_xpath");
			boolean Result;
			Result = VerifyPresent(titleScheduler_Xpath, 5, 2);
			if (Result == true) {
				test.log(Status.INFO, "Successfully verified Scheduler Screen is displayed");
			} else {
				reportFailure("Scheduler Screen is not displayed ");

			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/*
	 * verifyScedhulerScreenDescription This method verifies the Scheduler screen
	 * description.
	 */
	public void verifyScedhulerScreenDescription() throws Throwable {
		try {
			String descriptionScheduler_Xpath = prop.getProperty("PanaMA.Scheduler_descriptionScheduler");
			boolean Result;
			Result = VerifyPresent(descriptionScheduler_Xpath, 5, 2);
			if (Result == true) {
				test.log(Status.INFO, "Successfully verified the description of scheduler screen");
			} else {
				reportFailure("Description of Scheduler Screen is not successfully displayed.");

			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/*
	 * verifyScedhulerScreenCurrentDate This method verifies the Scheduler screen
	 * current date.
	 */
	public void verifyScedhulerScreenCurrentDate() throws Throwable {
		try {
			String inputDate = prop.getProperty("PanaMA.Scheduler_currentDateScheduler");
			DateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");
			Date date = new Date();
			// System.out.println(dateFormat.format(date)); //2016/11/16 12:08:43
			String inputDate_Xpath = inputDate.replace("$", dateFormat.format(date));
			boolean Result;
			Result = VerifyPresent(inputDate_Xpath, 5, 2);
			if (Result == true) {
				test.log(Status.INFO, "Successfully verified that current date is present on Scheduler Screen");
			} else {
				reportFailure("Could not verify that current date is present on Scheduler Screen");

			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/*
	 * verifyNewWorkorderPageLaunchesFromScedhulerScreen This method verifies that
	 * new work order screen can be launched from Scheduler Screen.
	 */
	public void verifyNewWorkorderPageLaunchesFromScedhulerScreen() throws Throwable {
		try {
			String newWorkorderButton_xpath = prop.getProperty("Panama.MANewWOBtn_xpath");
			driver.findElement(By.xpath(newWorkorderButton_xpath)).click();
			String edtWorkOrderName_xpath = prop.getProperty("PanaMA.Scheduler_newWorkorderFormControlName");
			if (driver.findElement(By.xpath(edtWorkOrderName_xpath)) != null) {
				test.log(Status.INFO, "Successfully verified that new workorder screen launches from Scheduler Screen");
			} else {
				reportFailure("New workorder screen did not launch from Scheduler Screen");
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/*
	 * verifyZoomInButton This function verifies whether the size of the column
	 * width increases if zoom in button is pressed.
	 */
	public void verifyZoomInButton() throws InterruptedException {
		try {
			List<WebElement> columnHeaders = driver.findElements(By.xpath("//div[@role=\"columnheader\"]"));
			String size1 = columnHeaders.get(0).getCssValue("width");
			System.out.println(size1);
			String size3 = (size1.replaceAll("[^0-9]", ""));
			Integer size1Number = Integer.parseInt(size3);

			driver.findElement(By.xpath(prop.getProperty("PanaMA.MASchedZoomIn_xpath"))).click();
			Thread.sleep(Integer.parseInt(envProp.getProperty("WaitTimeOneSec")));
			driver.findElement(By.xpath(prop.getProperty("PanaMA.MASchedZoomIn_xpath"))).click();

			columnHeaders = driver.findElements(By.xpath("//div[@role=\"columnheader\"]"));
			String size2 = columnHeaders.get(0).getCssValue("width");
			Integer size2Number = Integer.parseInt(size2.replaceAll("[^0-9]", ""));
			System.out.println(size2);

			if (size2Number > size1Number) {
				test.log(Status.INFO,
						"Successfully verified that on zoom-in the column header width of Scheduler table increases");
			} else {
				reportFailure("Failed to verify that on zoom-in the column header width of Scheduler table increases");
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/*
	 * verifyZoomType This function verifies the different zoom in text available
	 * passed as dataKey from XLS.
	 */
	public void verifyZoomType() throws Throwable {
		try {
			String Time = data.get(dataKey);
			String ZoomType = prop.getProperty("PanaMA.MASchedZoomText_xpath");
			String ZoomType1 = "";
			try {
				ZoomType1 = ZoomType.replace("$", Time);
			} catch (Exception e) {
				System.out.println(e);
			}

			if (VerifyPresent(ZoomType1, 5, 2)) {
				WebElement element = driver.findElement(By.xpath(ZoomType1));
				if (element != null) {
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
				}
				test.log(Status.INFO, "Successfully verified Zoom Time");
			} else {
				reportFailure("Could not Zoom Time");
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/*
	 * VerifyLegendColors This function verifies whether the color of legend screen
	 * matches that of color codes xpath
	 */
	public void VerifyLegendColors() {
		try {
			List<String> ColorCodeStrings = new ArrayList<String>();
			List<String> LegendColorCodeStrings = new ArrayList<String>();
			driver.navigate().to(prop.getProperty("ColorCodesUrl"));
			for (int i = 1; i <= 9; i++) {
				String ColorCodeXpath = prop.getProperty("PanaMA.ColorCodes_Xpath");
				String ColorCodeXpath1 = ColorCodeXpath.replace("$", String.valueOf(i));
				String colorCode = driver.findElement(By.xpath(ColorCodeXpath1)).getAttribute("Style");
				ColorCodeStrings.add(colorCode);
			}
			driver.navigate().to(prop.getProperty("SchedulerUrl"));
			for (int i = 1; i <= 9; i++) {
				String LegendColorCodeXpath = prop.getProperty("PanaMA.SchedLegend_ColorCodes_Xpath");
				String LegendColorCodeXpath1 = LegendColorCodeXpath.replace("$", String.valueOf(i));
				String legendColorCode = driver.findElement(By.xpath(LegendColorCodeXpath1)).getAttribute("Style");
				LegendColorCodeStrings.add(legendColorCode);
			}

			if (ColorCodeStrings.equals(LegendColorCodeStrings)) {
				test.log(Status.INFO, "Verified color codes successfully");
			} else {
				reportFailure("Could not verify legend colors successfully");
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/*
	 * VerifyLeftPanelIsVisible This function verifies by default left panel is
	 * visible.
	 */
	public void VerifyLeftPanelIsVisible() throws Throwable {
		try {
			boolean Result;
			Result = VerifyPresent(prop.getProperty("PanaMA.MASchedLeftPanelVisible_xpath"), 5, 2);
			if (Result == true) {
				test.log(Status.INFO, "Successfully Left Panel is visible.");
			} else {
				reportFailure("Could not verify Left Panel is visible");

			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/*
	 * VerifyLeftPanelIsNotVisible This function verifies left panel is not visible.
	 */
	public void VerifyLeftPanelIsNotVisible() throws Throwable {
		try {
			boolean Result;
			Result = VerifyPresent(prop.getProperty("PanaMA.MASchedLeftPanelHidden_xpath"), 5, 2);
			if (Result == true) {
				test.log(Status.INFO, "Successfully Left Panel is NOT visible.");
			} else {
				reportFailure("Could not verify Left Panel is NOT visible");

			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/*
	 * verifyZoomOutButton This method verifies that on clicking zoom out button,
	 * the column width decreases.
	 */
	public void verifyZoomOutButton() throws InterruptedException {
		try {
			WebElement text = driver.findElement(By.xpath("//div[@role=\"columnheader\"]"));
			String size1 = text.getCssValue("width");
			System.out.println(size1);
			String size2 = (size1.replaceAll("[^0-9]", ""));
			Integer size1Number = Integer.parseInt(size2);

			driver.findElement(By.xpath(prop.getProperty("PanaMA.MASchedZoomOut_xpath"))).click();
			Thread.sleep(Integer.parseInt(envProp.getProperty("WaitTimeOneSec")));
			driver.findElement(By.xpath(prop.getProperty("PanaMA.MASchedZoomOut_xpath"))).click();

			WebElement text1 = driver.findElement(By.xpath("//div[@role=\"columnheader\"]"));
			String size3 = text1.getCssValue("width");
			Integer size2Number = Integer.parseInt(size3.replaceAll("[^0-9]", ""));
			System.out.println(size2);

			if (size2Number < size1Number) {
				test.log(Status.INFO,
						"Successfully verified that on zoom-out the column header width of Scheduler table decreases");
			} else {
				reportFailure("Failed to verify that on zoom-out the column header width of Scheduler table decreases");
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/*
	 * verifyHourlyColumnHeadings This method verifies the hourly column headings.
	 */
	public void verifyHourlyColumnHeadings() throws Throwable {
		try {
			boolean Result;
			List<String> HourStrings = new ArrayList<String>(Arrays.asList("00:00", "01:00", "02:00", "03:00", "04:00",
					"05:00", "06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00",
					"16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00"));
			String Hourly_xpath = prop.getProperty("PanaMA.Scheduler_HourlyColumnHeadings");
			driver.findElement(By.xpath(prop.getProperty("PanaMA.SchedulerWOHorizontalScrollBar_xpath")));
			int scrollbarWidth = driver
					.findElement(By.xpath(prop.getProperty("PanaMA.SchedulerWOHorizontalScrollBar_xpath"))).getSize()
					.getWidth();
			int columnHeaderWidth = driver
					.findElement(By.xpath(prop.getProperty("PanaMA.SchedulerWOColumnHeading_xpath"))).getSize()
					.getWidth();
			int moveByOffset = scrollbarWidth - columnHeaderWidth;

			for (int i = 0; i <= HourStrings.size() - 1; i++) {
				String Hourly_xpath1 = Hourly_xpath.replace("$", HourStrings.get(i));
				Result = VerifyPresent(Hourly_xpath1, 5, 2);
				if (Result == true) {
					WebElement myElement = driver
							.findElement(By.xpath(prop.getProperty("PanaMA.SchedulerWOHorizontalScrollBar_xpath")));
					myElement.click();
					Actions move = new Actions(driver);
					move.moveToElement(myElement).clickAndHold();
					move.moveByOffset(moveByOffset, 0);
					move.release();
					move.perform();
					test.log(Status.INFO, Hourly_xpath1 + "Successfully verified all the hourly column headings");
				} else {
					reportFailure(Hourly_xpath1 + "Could not verify Hourly column headings");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/*
	 * verifyDayColumnHeadings This function verifies the day column headings.
	 */
	public void verifyDayColumnHeadings() throws Throwable {
		try {
			boolean Result;
			List<String> DayStrings = new ArrayList<String>(
					Arrays.asList("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"));
			String Day_xpath = prop.getProperty("PanaMA.Scheduler_HourlyColumnHeadings");
			int scrollableareaWidth = driver
					.findElement(By.xpath(prop.getProperty("PanaMA.SchedulerWOScrollAreaHorizontal_xpath"))).getSize()
					.getWidth();
			int contentWidgetWidth = driver
					.findElement(By.xpath(prop.getProperty("PanaMA.SchedulerWOContentArea_xpath"))).getSize()
					.getWidth();

			int moveByoffsetValue = (((contentWidgetWidth) - scrollableareaWidth) * 2) + 13;

			for (int i = 0; i <= DayStrings.size() - 1; i++) {
				String Day_xpath1 = Day_xpath.replace("$", DayStrings.get(i));
				Result = VerifyPresent(Day_xpath1, 5, 2);
				if (Result == true) {
					WebElement myElement = driver
							.findElement(By.xpath("(//div[contains(@id,\"ScrollThumbhorizontalScrollBar\")])[1]"));
					myElement.click();
					Actions move = new Actions(driver);
					move.moveToElement(myElement).clickAndHold();
					move.moveByOffset(moveByoffsetValue, 0);
					move.release();
					move.perform();
					/*
					 * for(int j = 0; j<=121; j++) { driver.findElement(By.xpath(prop.getProperty(
					 * "PanaMA.Scheduler_ScrollRightButton_xpath"))).click(); }
					 */
					test.log(Status.INFO, "Successfully verified all the day column headings");
				} else {
					reportFailure("Could not verify day column headings");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/*
	 * verifyBackgroundColorOfCreatedWO This method verifies the background color of
	 * created WO matches that of Legend color.
	 */
	public void verifyBackgroundColorOfCreatedWO() throws Throwable {
		try {
			boolean Result;
			driver.navigate().to(prop.getProperty("ConfigurationUrl"));
			driver.navigate().to(prop.getProperty("SchedulerUrl"));
			Result = false;
			String WOName = data.get(dataKey);
			String WOName_xpath = prop.getProperty("PanaMA.Scheduler_WONameIn_SchedulerAppointment_xpath");
			String WOName_xpath1 = WOName_xpath.replace("$", WOName);
			try {
				driver.findElement(By.xpath(WOName_xpath1));
			} catch (Exception e) {
				Date OldDate = Calendar.getInstance().getTime();
				DateFormat formatter = new SimpleDateFormat("M/dd/yyyy");
				Date addDays = DateUtils.addDays(OldDate, 1);
				String NewDate = formatter.format(addDays);
				driver.findElement(By.xpath(prop.getProperty("PanaMA.Scheduler_SelectData_xpath"))).clear();
				driver.findElement(By.xpath(prop.getProperty("PanaMA.Scheduler_SelectData_xpath"))).sendKeys(NewDate);
				driver.findElement(By.xpath(prop.getProperty("PanaMA.ApplyBtn_xpath"))).click();
				try {
					driver.findElement(By.xpath(WOName_xpath1));
				} catch (Exception e1) {
					reportFailure("Could not find WO Name on the Scheduler Screen" + WOName);
				}

			}

			String WOBalloonColor = driver.findElement(By.xpath(WOName_xpath1)).getCssValue("background");
			Pattern c = Pattern.compile("(rgb\\(.*\\)).*");
			Matcher m = c.matcher(WOBalloonColor);
			String WOBalloonColor1 = null;
			if (m.matches()) {
				WOBalloonColor1 = m.group(1);
			}
			String LegendColorXpath = prop.getProperty("PanaMA.Scheduler_LegendColor_xpath");
			String LegendColorXpath1 = LegendColorXpath.replace("$", WOBalloonColor1);
			Result = VerifyPresent(LegendColorXpath1, 5, 2);
			if (Result == true) {
				test.log(Status.INFO, "Successfully verified the color of WO balloon background" + WOName);
			} else {
				reportFailure("Could not verify the color of WO balloon background" + WOName);
			}
			System.out.println("wait");
			String Legend_created_xpath = prop.getProperty("PanaMA.SchedulerLegendCreated_xpath");

			String Created_style = driver.findElement(By.xpath(Legend_created_xpath)).getAttribute("style");

			if (Created_style.contains(WOBalloonColor1)) {
				test.log(Status.INFO, "Successfully verified the color of WO balloon background" + WOName);
			} else {
				reportFailure("Could not verify the color of WO balloon background" + WOName);
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/*
	 * verifyBackgroundColorOfReservedWO This method verifies the background color
	 * of Reserved WO matches that of Reserved Legend Color
	 */
	public void verifyBackgroundColorOfReservedWO() throws Throwable {
		try {
			boolean Result;
			driver.navigate().to(prop.getProperty("ConfigurationUrl"));
			driver.navigate().to(prop.getProperty("SchedulerUrl"));
			Result = false;
			String WOName = data.get(dataKey);
			String WOName_xpath = prop.getProperty("PanaMA.Scheduler_WONameIn_SchedulerAppointment_xpath");
			String WOName_xpath1 = WOName_xpath.replace("$", WOName);
			WebElement element = null;

			try {
				element = driver.findElement(By.xpath(WOName_xpath1));
			} catch (Exception e) {
				Date OldDate = Calendar.getInstance().getTime();
				DateFormat formatter = new SimpleDateFormat("M/dd/yyyy");
				Date addDays = DateUtils.addDays(OldDate, 1);
				String NewDate = formatter.format(addDays);
				driver.findElement(By.xpath(prop.getProperty("PanaMA.Scheduler_SelectData_xpath"))).clear();
				driver.findElement(By.xpath(prop.getProperty("PanaMA.Scheduler_SelectData_xpath"))).sendKeys(NewDate);
				driver.findElement(By.xpath(prop.getProperty("PanaMA.ApplyBtn_xpath"))).click();
				try {
					element = driver.findElement(By.xpath(WOName_xpath1));
				} catch (Exception e1) {
					reportFailure("Could not find WO Name on the Scheduler Screen" + WOName);
				}

			}
			if (element != null) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			}
			driver.findElement(By.xpath(WOName_xpath1)).click();
			driver.findElement(By.xpath(prop.getProperty("PanaMA.SchedulerPopupReservationBtn_xpath"))).click();
			WebDriverWait wait = new WebDriverWait(driver, 120);
			wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(prop.getProperty("PanaMA.SchedulerPopupReservationSaveBtn_xpath"))));

			// Thread.sleep(120000);
			driver.findElement(By.xpath(prop.getProperty("PanaMA.SchedulerPopupReservationSaveBtn_xpath"))).click();
			driver.findElement(By.xpath(prop.getProperty("PanaMA.SchedulerPopupReservationOkBtn_xpath"))).click();

			Thread.sleep(Integer.parseInt(envProp.getProperty("DynamicWaitTime")));
			driver.navigate().to(prop.getProperty("SchedulerUrl"));
			Thread.sleep(Integer.parseInt(envProp.getProperty("DynamicWaitTime")));
			element = driver.findElement(By.xpath(WOName_xpath1));
			if (element != null) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			}
			String WOBalloonColor = driver.findElement(By.xpath(WOName_xpath1)).getCssValue("background");
			Pattern c = Pattern.compile("(rgb\\(.*\\)).*");
			Matcher m = c.matcher(WOBalloonColor);
			String WOBalloonColor1 = null;
			if (m.matches()) {
				WOBalloonColor1 = m.group(1);
			}

			String LegendColorXpath = prop.getProperty("PanaMA.Scheduler_LegendColor_xpath");
			String LegendColorXpath1 = LegendColorXpath.replace("$", WOBalloonColor1);
			Result = VerifyPresent(LegendColorXpath1, 5, 2);
			if (Result == true) {
				test.log(Status.INFO, "Successfully verified the color of WO balloon background" + WOName);
			} else {
				reportFailure("Could not verify the color of WO balloon background" + WOName);
			}

			String Legend_reserved_xpath = prop.getProperty("PanaMA.SchedulerLegendReserved_xpath");

			String Reserved_style = driver.findElement(By.xpath(Legend_reserved_xpath)).getAttribute("style");

			String Legend_PartialReserved_xpath = prop.getProperty("PanaMA.SchedulerLegendPartialReserved_xpath");

			String PartialReserved_style = driver.findElement(By.xpath(Legend_PartialReserved_xpath))
					.getAttribute("style");

			if (Reserved_style.contains(WOBalloonColor1) || PartialReserved_style.contains(WOBalloonColor1)) {
				test.log(Status.INFO, "Successfully verified the color of WO balloon background" + WOName);
			} else {
				reportFailure("Could not verify the color of WO balloon background" + WOName);
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/*
	 * VerifyPresent This method look for an webobject based on XPATH.If it finds
	 * the object, it returns TRUE else FALSE.
	 */
	public Boolean VerifyPresent(String Xpath, int loopcounter, int retry) throws Throwable {
		boolean isResult = false;

		try {
			List<WebElement> elem = new ArrayList<WebElement>();
			try {
				int start = 0;
				int loopCounter = 5;
				while (elem.size() == 0 && start <= loopCounter) {
					elem = driver.findElements(By.xpath(Xpath));
					Thread.sleep(Integer.parseInt(envProp.getProperty("WaitTimeOneSec")));
					start++;
				}
			} catch (WebDriverException ex) {
				if (ex.getMessage().contains("The connection was closed unexpectedly.") && retry < 2)
					VerifyPresent(Xpath, loopcounter, 2);
				else
					throw (ex);
			} catch (Exception ex) {
				throw ex;
			}
			isResult = elem.size() > 0 ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}

		return isResult;

	}

	/**
	 * verifyPageTitle This Method to Verify Title of the page in the application.
	 */
	public void verifyPageTitle() {
		try {
			String title = data.get(dataKey);
			test.log(Status.INFO, "Verifying " + title + " Title");
			String object_xpath = prop.getProperty(ObjectKey);
			String actResult = driver.findElement(By.xpath(object_xpath)).getText();

			if (actResult.equals(title)) {
				test.log(Status.INFO, actResult + ".......Title is present on the " + title + " Page");
			} else {

				reportFailure(actResult + "....... does not match with the Tilte on the " + title + " Page");
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

//Sprint 1 Code

	/**
	 * verifySchedWO This Method is to Check Workorder present in Workorder Schedule
	 * Page.
	 */
	public void verifySchedWO() throws Throwable {
		try {
			String WOName = data.get(dataKey);
			String WOName_xpath = prop.getProperty(ObjectKey);
			String WOName_xpath1 = WOName_xpath.replace("$", WOName);
			System.out.println("Work Order Name " + WOName);
			try {
				boolean Result = false;

				for (int i = 0; i <= 5; i++) {
					System.out.println("Searching For ..." + WOName_xpath1);
					Result = VerifyPresent(WOName_xpath1, 5, 2);

					if (Result == true) {
						test.log(Status.INFO, "Successfully Found the Work Order " + WOName);
						break;
					}
				}
				if (Result == false) {
					reportFailure("Could not Found Work Order " + WOName);
				}

			} catch (Exception e) {
				reportFailure("Could not Found Work Order " + WOName);
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * cleanupMultiWO This Method is to Delete the Workorders .
	 */
	public void cleanupMultiWO() throws SQLException {
		try {
			DBTransaction db1 = new DBTransaction(envProp);
			String WOName = data.get(dataKey);
			String curWOName;
			for (int i = 0; i < 7; i++) {
				curWOName = WOName + i;
				db1.DeleteWO(curWOName);
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * verifySchedWorkOrders This Method is to Verify WorkOrder is present in
	 * current Week and Next Week .
	 */
	public void verifySchedWorkOrders() throws Throwable {
		try {
			String WOName = data.get(dataKey);
			String WOName_xpath = prop.getProperty("PanaMA.Scheduler_WONameIn_SchedulerAppointment_xpath");
			String curWOName, currWOName_xpath, NewDtStr;
			boolean Result = true;
			Date currDt = new Date();
			DateFormat df4 = new SimpleDateFormat("MM/dd/yyyy");
			Date NewDt;// = DateUtils.addHours(currDt, 48);
			String currDtStr = df4.format(currDt);

			for (int i = 0; i < 7; i++) {
				curWOName = WOName + i;
				System.out.println(curWOName);

				applyDate(currDtStr);

				currWOName_xpath = WOName_xpath.replace("$", curWOName);
				// Result = VerifyPresent(currWOName_xpath, 5, 2);
				// WebElement foundElement = driver.findElement(By.xpath(currWOName_xpath));
				List<WebElement> foundElement = driver.findElements(By.xpath(currWOName_xpath));
				if (foundElement.size() <= 0) {

					NewDt = DateUtils.addHours(currDt, 168);
					NewDtStr = df4.format(NewDt);
					applyDate(NewDtStr);
					Result = VerifyPresent(currWOName_xpath, 5, 2);
					if (Result == true) {
						test.log(Status.INFO, "Successfully Found Work Orders " + curWOName);
					} else {
						break;
					}
				} else {
					test.log(Status.INFO, "Successfully Found Work Orders " + curWOName);
				}

			}
			if (Result == true) {
				test.log(Status.INFO, "Successfully Found All Work Orders ");
			} else {
				reportFailure("Could not Found All Work Orders ");
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * applyDate This Method is to Open WorkOrder Schedule Page to given Week.
	 * currDtStr : Date String
	 */
	public void applyDate(String currDtStr) throws Throwable {
		try {
			driver.findElement(By.xpath(prop.getProperty("PanaMA.Scheduler_WeekButton_xpath"))).click();
			driver.findElement(By.xpath(prop.getProperty("PanaMA.MASchedCalSt_xpath"))).clear();
			driver.findElement(By.xpath(prop.getProperty("PanaMA.MASchedCalSt_xpath"))).sendKeys(currDtStr);
			driver.findElement(By.xpath(prop.getProperty("PanaMA.MASchedApplyButton_xpath"))).click();
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * createMulWorkOrders This Method is to Create Work Multiple WorkOrders in a
	 * Week.
	 */
	public void createMulWorkOrders() throws SQLException, InterruptedException {
		try {
			String WOName = data.get(dataKey);
			String curWOName;
			for (int i = 0; i < 7; i++) {
				curWOName = WOName + i;
				try {
					cleanup(curWOName);
				} catch (Exception e) {
					deleteWOFromWOList();
				}
				// Create Existing Work Order with Status!=Hold/Closed, Lane:Rear,
				// TimeOverlap:Yes
				String side = "A";
				String productName = "Product1";
				String prductInFold = "16"; // -----?
				String patternPerPanem = "1";
				String productSetupId = "26";
				String mjsid = "Project-0703_1";
				String lineInFold = "3"; // -----?
				String workorderstatus = "Created";
				String statusId = "9";
				String lane = "R";

				// For Existing Work Order
				// String scheduledStartString =
				// scheduledStart.ToUniversalTime().ToString("yyyy-MM-dd") + "T" +
				// scheduledStart.ToUniversalTime().ToString("hh:mm:ss") + "Z";
				// String scheduledEndString =
				// scheduledEnd.ToUniversalTime().ToString("yyyy-MM-dd") + "T" +
				// scheduledEnd.ToUniversalTime().ToString("hh:mm:ss") + "Z";
				// String scheduledStartString = "2019-01-21T09:04:20.961Z";
				// String scheduledEndString = "2019-01-21T10:24:20.961Z";

				Double D_plusMinutes = 5.0;
				AppKeywords.D_Minutes = AppKeywords.D_Minutes + D_plusMinutes;
				System.out.println("Minute ..." + AppKeywords.D_Minutes);
				Date OldDate = new Date();
				Date NewStartDate = DateUtils.addMinutes(OldDate, (int) Math.round(AppKeywords.D_Minutes));
				Date NewStartDate1 = DateUtils.addHours(NewStartDate, +6 + (i * 24));
				DateFormat df4 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
				String scheduledStartString = df4.format(NewStartDate1);

				int plusMinutes = 60;
				// AppKeywords.D_Minutes = AppKeywords.D_Minutes + D_plusMinutes;
				Date NewEndDate = DateUtils.addMinutes(NewStartDate1, plusMinutes);
				// Date NewEndDate1 = DateUtils.addHours(NewEndDate, 6);
				DateFormat df4_1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
				String scheduledEndString = df4_1.format(NewEndDate);

				String lotName = "Product1";
				String subWOSide = "T";

				String woJson = "{\"WorkOrderName\":\"" + curWOName + "\",\"WorkOrderId\":0,\"ProductName\":\""
						+ productName + "\",\"ProductInfoId\":\"" + prductInFold + "\",\"PatternPerPanel\":"
						+ patternPerPanem + ",\"Side\":\"" + side
						+ "\",\"PatternQuantity\":101,\"WorkOrderProductId\":1,"
						+ "\"SubWorkOrders\":[{\"SubWorkOrderId\":0,\"WoProductRelationId\":0,\"ProductSetupId\":"
						+ productSetupId + ",\"Lane\":\"" + lane
						+ "\",\"TargetQuantity\":1,\"CompletedQuantity\":0,\"ScheduleStartTime\":\""
						+ scheduledStartString + "\",\"ScheduleEndTime\":\"" + scheduledEndString
						+ "\",\"ActualStartTime\":\"2019-04-28T09:04:20.961Z\",\"ActualEndTime\":\"2019-04-28T09:04:20.961Z\",\"ReworkedQuantity\":0,\"re_subworkorder_id\":0,\"WorkOrderStatusId\":"
						+ statusId + ",\"WorkOrderStatus\":\"" + workorderstatus
						+ "\",\"CreatedDate\":\"0001-01-01T00:00:00\",\"CreatedBy\":0,\"UpdatedDate\":\"0001-01-01T00:00:00\",\"UpdatedBy\":0,\"Mjsid\":\""
						+ mjsid + "\",\"LineName\":\"LCSIMLINE1TRAY\",\"LineInfoId\":" + lineInFold + ",\"LotName\":\""
						+ lotName + "\",\"Side\":\"" + subWOSide + "\"}]}";
				// '{"WorkOrderName":"WO29_10", "WorkOrderId":0, "ProductName":"Product1",
				// "ProductInfoId":"16","PatternPerPanel":1,"Side":"A","PatternQuantity":101,"WorkOrderProductId":1,"SubWorkOrders":[{"SubWorkOrderId":0,"WoProductRelationId":0,"ProductSetupId":26,"Lane":"R","TargetQuantity":1,"CompletedQuantity":0,"ScheduleStartTime":"2019-01-20T09:04:20.961Z","ScheduleEndTime":"2019-01-20T09:24:20.961Z","ActualStartTime":"2019-04-28T09:04:20.961Z","ActualEndTime":"2019-04-28T09:04:20.961Z","ReworkedQuantity":0,"re_subworkorder_id":0,"WorkOrderStatusId":1,"WorkOrderStatus":"Created","CreatedDate":"0001-01-01T00:00:00","CreatedBy":0,"UpdatedDate":"0001-01-01T00:00:00","UpdatedBy":0,"Mjsid":"Project-0703_1","LineName":"LCSIMLINE1TRAY","LineInfoId":3,"LotName":"Product1","Side":"T"}]}',
				// '')
				DBTransaction db1 = new DBTransaction(envProp);
				db1.RunPgSqlTransaction(woJson);
				test.log(Status.INFO, "Created Work Order " + curWOName);
			}

			D_Hours = 0.0;
			D_Minutes = 5.0;
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * createMinhWorkOrder This Method is to Create Work Orders for Product
	 * MinhTest1.
	 */
	public void createMinhWorkOrder() throws SQLException, InterruptedException {
		try {
			String WOName = data.get(dataKey);
			try {
				cleanup();
			} catch (Exception e) {
				deleteWOFromWOList();
			}

			// Create Existing Work Order with Status!=Hold/Closed, Lane:Rear,
			// TimeOverlap:Yes
			String side = "A";
			String productName = "MinhTest1";
			String prductInFold = "2"; // -----?
			String patternPerPanem = "1";
			String productSetupId = "2";
			String mjsid = "MinhPrj_1";
			String lineInFold = "3"; // -----?
			String workorderstatus = "Created";
			String statusId = "1";
			String lane = "R";

			Double D_plusMinutes = 5.0;
			AppKeywords.D_Minutes = AppKeywords.D_Minutes + D_plusMinutes;
			Date OldDate = new Date();
			Date NewStartDate = DateUtils.addMinutes(OldDate, (int) Math.round(AppKeywords.D_Minutes));
			System.out.println("NewStartDate " + NewStartDate);
			Date NewStartDate1 = DateUtils.addHours(NewStartDate, 6);
			System.out.println("NewStartDate1 " + NewStartDate1);
			DateFormat df4 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
			String scheduledStartString = df4.format(NewStartDate1);
			System.out.println("scheduledStartString " + scheduledStartString);

			D_plusMinutes = 60.0;
			AppKeywords.D_Minutes = AppKeywords.D_Minutes + D_plusMinutes;
			Date NewEndDate = DateUtils.addMinutes(OldDate, (int) Math.round(AppKeywords.D_Minutes));
			Date NewEndDate1 = DateUtils.addHours(NewEndDate, 6);
			DateFormat df4_1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
			String scheduledEndString = df4_1.format(NewEndDate1);

			String lotName = "Product1";
			String subWOSide = "T";

			String woJson = "{\"WorkOrderName\":\"" + WOName + "\",\"WorkOrderId\":0,\"ProductName\":\"" + productName
					+ "\",\"ProductInfoId\":\"" + prductInFold + "\",\"PatternPerPanel\":" + patternPerPanem
					+ ",\"Side\":\"" + side + "\",\"PatternQuantity\":101,\"WorkOrderProductId\":1,"
					+ "\"SubWorkOrders\":[{\"SubWorkOrderId\":0,\"WoProductRelationId\":0,\"ProductSetupId\":"
					+ productSetupId + ",\"Lane\":\"" + lane
					+ "\",\"TargetQuantity\":1,\"CompletedQuantity\":0,\"ScheduleStartTime\":\"" + scheduledStartString
					+ "\",\"ScheduleEndTime\":\"" + scheduledEndString
					+ "\",\"ActualStartTime\":\"2019-04-28T09:04:20.961Z\",\"ActualEndTime\":\"2019-04-28T09:04:20.961Z\",\"ReworkedQuantity\":0,\"re_subworkorder_id\":0,\"WorkOrderStatusId\":"
					+ statusId + ",\"WorkOrderStatus\":\"" + workorderstatus
					+ "\",\"CreatedDate\":\"0001-01-01T00:00:00\",\"CreatedBy\":0,\"UpdatedDate\":\"0001-01-01T00:00:00\",\"UpdatedBy\":0,\"Mjsid\":\""
					+ mjsid + "\",\"LineName\":\"LCSIMLINE1TRAY\",\"LineInfoId\":" + lineInFold + ",\"LotName\":\""
					+ lotName + "\",\"Side\":\"" + subWOSide + "\"}]}";
			// '{"WorkOrderName":"WO29_10", "WorkOrderId":0, "ProductName":"Product1",
			// "ProductInfoId":"16","PatternPerPanel":1,"Side":"A","PatternQuantity":101,"WorkOrderProductId":1,"SubWorkOrders":[{"SubWorkOrderId":0,"WoProductRelationId":0,"ProductSetupId":26,"Lane":"R","TargetQuantity":1,"CompletedQuantity":0,"ScheduleStartTime":"2019-01-20T09:04:20.961Z","ScheduleEndTime":"2019-01-20T09:24:20.961Z","ActualStartTime":"2019-04-28T09:04:20.961Z","ActualEndTime":"2019-04-28T09:04:20.961Z","ReworkedQuantity":0,"re_subworkorder_id":0,"WorkOrderStatusId":1,"WorkOrderStatus":"Created","CreatedDate":"0001-01-01T00:00:00","CreatedBy":0,"UpdatedDate":"0001-01-01T00:00:00","UpdatedBy":0,"Mjsid":"Project-0703_1","LineName":"LCSIMLINE1TRAY","LineInfoId":3,"LotName":"Product1","Side":"T"}]}',
			// '')
			DBTransaction db1 = new DBTransaction(envProp);
			db1.RunPgSqlTransaction(woJson);
			test.log(Status.INFO, "Created Work Order " + WOName);

			D_Hours = 0.0;
			D_Minutes = 5.0;
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * createZBotWorkOrder This Method is to Create Work Orders for Product
	 * ZBotProd10.
	 */
	public void createZBotWorkOrder() throws SQLException, InterruptedException {
		try {
			String oldWOName = data.get(dataKey);
			try {
				cleanup();
			} catch (Exception e) {
				deleteWOFromWOList();
			}
			// Create Existing Work Order with Status!=Hold/Closed, Lane:Rear,
			// TimeOverlap:Yes
			String side = "A";
			String productName = "ZBotProd10";
			String prductInFold = "5"; // -----?
			String patternPerPanem = "3";
			String productSetupId = "17";
			String mjsid = "MAZPROD3_1";
			String lineInFold = "3"; // -----?
			String workorderstatus = "Created";
			String statusId = "1";
			String lane = "R";

			Double D_plusMinutes = 5.0;
			AppKeywords.D_Minutes = AppKeywords.D_Minutes + D_plusMinutes;
			Date OldDate = new Date();
			Date NewStartDate = DateUtils.addMinutes(OldDate, (int) Math.round(AppKeywords.D_Minutes));
			Date NewStartDate1 = DateUtils.addHours(NewStartDate, 6);
			DateFormat df4 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
			String scheduledStartString = df4.format(NewStartDate1);

			D_plusMinutes = 60.0;
			AppKeywords.D_Minutes = AppKeywords.D_Minutes + D_plusMinutes;
			Date NewEndDate = DateUtils.addMinutes(OldDate, (int) Math.round(AppKeywords.D_Minutes));
			Date NewEndDate1 = DateUtils.addHours(NewEndDate, 6);
			DateFormat df4_1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
			String scheduledEndString = df4_1.format(NewEndDate1);

			String lotName = "ZBotProd10";
			String subWOSide = "B";

			String woJson = "{\"WorkOrderName\":\"" + oldWOName + "\",\"WorkOrderId\":0,\"ProductName\":\""
					+ productName + "\",\"ProductInfoId\":\"" + prductInFold + "\",\"PatternPerPanel\":"
					+ patternPerPanem + ",\"Side\":\"" + side + "\",\"PatternQuantity\":101,\"WorkOrderProductId\":1,"
					+ "\"SubWorkOrders\":[{\"SubWorkOrderId\":0,\"WoProductRelationId\":0,\"ProductSetupId\":"
					+ productSetupId + ",\"Lane\":\"" + lane
					+ "\",\"TargetQuantity\":1,\"CompletedQuantity\":0,\"ScheduleStartTime\":\"" + scheduledStartString
					+ "\",\"ScheduleEndTime\":\"" + scheduledEndString
					+ "\",\"ActualStartTime\":\"2019-04-28T09:04:20.961Z\",\"ActualEndTime\":\"2019-04-28T09:04:20.961Z\",\"ReworkedQuantity\":0,\"re_subworkorder_id\":0,\"WorkOrderStatusId\":"
					+ statusId + ",\"WorkOrderStatus\":\"" + workorderstatus
					+ "\",\"CreatedDate\":\"0001-01-01T00:00:00\",\"CreatedBy\":0,\"UpdatedDate\":\"0001-01-01T00:00:00\",\"UpdatedBy\":0,\"Mjsid\":\""
					+ mjsid + "\",\"LineName\":\"LCSIMLINE1TRAY\",\"LineInfoId\":" + lineInFold + ",\"LotName\":\""
					+ lotName + "\",\"Side\":\"" + subWOSide + "\"}]}";
			// '{"WorkOrderName":"WO29_10", "WorkOrderId":0, "ProductName":"Product1",
			// "ProductInfoId":"16","PatternPerPanel":1,"Side":"A","PatternQuantity":101,"WorkOrderProductId":1,"SubWorkOrders":[{"SubWorkOrderId":0,"WoProductRelationId":0,"ProductSetupId":26,"Lane":"R","TargetQuantity":1,"CompletedQuantity":0,"ScheduleStartTime":"2019-01-20T09:04:20.961Z","ScheduleEndTime":"2019-01-20T09:24:20.961Z","ActualStartTime":"2019-04-28T09:04:20.961Z","ActualEndTime":"2019-04-28T09:04:20.961Z","ReworkedQuantity":0,"re_subworkorder_id":0,"WorkOrderStatusId":1,"WorkOrderStatus":"Created","CreatedDate":"0001-01-01T00:00:00","CreatedBy":0,"UpdatedDate":"0001-01-01T00:00:00","UpdatedBy":0,"Mjsid":"Project-0703_1","LineName":"LCSIMLINE1TRAY","LineInfoId":3,"LotName":"Product1","Side":"T"}]}',
			// '')
			DBTransaction db1 = new DBTransaction(envProp);
			db1.RunPgSqlTransaction(woJson);

			D_Hours = 0.0;
			D_Minutes = 5.0;
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * clickSchedWO This Method is to Click on Give Work Order , in Work Order
	 * Scheduler Page.
	 */
	public void clickSchedWO() throws Throwable {
		try {
			boolean Result = false;
			String WOName = data.get(dataKey);
			String WOName_xpath = prop.getProperty(ObjectKey);
			String WOName_xpath1 = WOName_xpath.replace("$", WOName);
			System.out.println("Work order Name " + WOName);
			for (int i = 0; i <= 5; i++) {
				System.out.println("In For ..." + i);
				Result = VerifyPresent(WOName_xpath1, 5, 2);
				System.out.println("In For After ..." + Result);
				if (Result == true) {
					System.out.println("In If  " + i);
					test.log(Status.INFO, "Successfully Found the Element " + WOName);
					for (int j = 0; j <= 300; j++) {
						driver.findElement(By.xpath(prop.getProperty("PanaMA.Scheduler_ScrollRightButton_xpath")))
								.click();
						if (driver.findElement(By.xpath(WOName_xpath1)).isDisplayed()) {
							System.out.println("In Display .... ");
							break;
						}
					}
					break;
				}
			}
			if (Result == false) {
				reportFailure("Could not found Element " + WOName);
			}
			test.log(Status.INFO, "Clicking on Element " + WOName);
			driver.findElement(By.xpath(WOName_xpath1)).click();
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * verifyWOPopUpElements This Method is to Find Elements on POP, in Work Order
	 * Scheduler Page.
	 */
	public void verifyWOPopUpElements() {
		try {
			String object_xpath = prop.getProperty(ObjectKey);

			test.log(Status.INFO, "Verifying Workorder List Popup Elements ");

			System.out.println("XPath " + object_xpath);
			List<WebElement> popUpElements = driver.findElements(By.xpath(object_xpath));
			int elementsCount = popUpElements.size();
			List<String> actualElements = new ArrayList<String>();
			actualElements.add("Product");
			actualElements.add("Project");
			actualElements.add("Scheduled");
			actualElements.add("Actual");
			actualElements.add("Edit");
			actualElements.add("Delete");
			actualElements.add("Reserve");
			actualElements.add("Force Close");

			String nameOfWO;

			System.out.println(elementsCount);
			for (int i = 0; i < elementsCount; i++) {
				nameOfWO = popUpElements.get(i).getText().split(":", 5)[0].trim();
				System.out.println(nameOfWO);
				// actualElements.
				if (!actualElements.contains(nameOfWO)) {
					reportFailure("Element is Not available on Popup " + nameOfWO);
				}
			}
			test.log(Status.INFO, "All Elements Found on WO Popup");
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * VerifyStatusFieldEmpty This Method is to Verify Status Field Empty, in Work
	 * Order Scheduler Page.
	 */
	public void VerifyStatusFieldEmpty() {
		try {
			// String data = "null";
			String data = driver.findElement(By.xpath(prop.getProperty("PanaMA.MASchedStat_xpath")))
					.getAttribute("value");

			if (Strings.isNullOrEmpty(data)) {
				test.log(Status.INFO, "By Default Status Field is Empty");
			} else {
				reportFailure("Status Field is not Empty");
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * VerifyLinesFieldEmpty This Method is to Verify Lines Field Empty, in Work
	 * Order Scheduler Page.
	 */
	public void VerifyLinesFieldEmpty() {
		try {
			// String data = "null";
			String data = driver.findElement(By.xpath(prop.getProperty("PanaMA.MASchedLines_xpath")))
					.getAttribute("value");
			System.out.println("Lines Field " + data);
			if (Strings.isNullOrEmpty(data)) {
				test.log(Status.INFO, "By Default Lines Field is Empty");
			} else {
				reportFailure("Lines Field is not Empty");
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * VerifyStartDate This Method is to Validate Start Date as current Date in Work
	 * Order Scheduler Page.
	 */
	public void VerifyStartDate() {
		try {
			Date curDt = new Date();
			DateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");

			String sysDt = dateFormat.format(curDt);
			String startDt = driver.findElement(By.xpath(prop.getProperty("PanaMA.MASchedCalSt_xpath")))
					.getAttribute("value");
			System.out.println("Start Date " + startDt + "  " + sysDt);

			if (sysDt.equals(startDt)) {
				test.log(Status.INFO, "Schedule Start Date is same as Current Date");
			} else {
				reportFailure("Schedule Start Date is not matching with Current Date");
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * verifySuccessfullCreationDeletion This Method to Verify Successful Creation
	 * and Deletion of Buffer.
	 */
	public void verifySuccessfulCreationDeletion() {
		try {
			String ExpectedValue = data.get(dataKey);
			test.log(Status.INFO, "Verifying whether " + ExpectedValue);
			Boolean Found = Boolean.FALSE;
			List<WebElement> starElements = null;

			String notifyInfoXpath = prop.getProperty("PanaMA.NotifyErrorMessage_xpath");

			try {
				starElements = driver.findElements(By.xpath(notifyInfoXpath));
			} catch (Exception e) {
				Found = Boolean.FALSE;
			}

			for (WebElement Element : starElements) {

				if (Element.getText().equals(ExpectedValue)) {
					Found = Boolean.TRUE;
					break;
				}
			}

			if (Found == Boolean.TRUE) {
				test.log(Status.INFO, "Found the Message " + ExpectedValue + " Result as Expected");
				// driver.close();
			} else {
				reportFailure("Did not find the Message " + ExpectedValue + "Result is not as Expected");
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}

	}

	/**
	 * verifyGlbBuffNav This Method to Check Global Buffer page is launched.
	 */
	public void verifyNavigation() {
		try {
			String expResult = data.get(dataKey);
			test.log(Status.INFO, "Verifying " + expResult + " Page is launched");
			String object_xpath = prop.getProperty(ObjectKey);

			String actResult = driver.findElement(By.xpath(object_xpath)).getText();

			if (actResult.equals(expResult)) {

				test.log(Status.INFO, expResult + " .....page opened Successfully");
			} else {
				reportFailure(expResult + "......page was not opened Successfully.....");
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * verifyGlbBuffTitle This Method to Check Global Buffer Title is present on the
	 * Global Buffer Screen.
	 *//*
		 * public void verifyGlbBuffTitle() { try { test.log(Status.INFO,
		 * "Verifying Global Buffer Title"); String object_xpath =
		 * prop.getProperty(ObjectKey); String expResult = "GLOBAL BUFFER"; String
		 * actResult = driver.findElement(By.xpath(object_xpath)).getText();
		 * 
		 * if(actResult.equals(expResult)) { test.log(Status.INFO, expResult+
		 * ".......Title is present on the Global Buffer Page" ); } else {
		 * 
		 * reportFailure(expResult+
		 * ".......Title is not present on the Global Buffer Page"); } } catch(Exception
		 * e) { e.printStackTrace(); } }
		 */

	/**
	 * verifyBuffPercentEdit This Method to Check Buffer Percentage Field is
	 * editable or not.
	 */
	public void verifyBuffPercentEdit() {
		try {
			test.log(Status.INFO, "Verifying Buffer Percentage Field is editable or non editable field.");
			String object_xpath = prop.getProperty(ObjectKey);
			// String value = data.get(dataKey);

			String ExpectedResult = data.get(dataKey).substring(0, data.get(dataKey).length() - 2);
			driver.findElement(By.xpath(object_xpath)).clear();
			driver.findElement(By.xpath(object_xpath)).sendKeys(ExpectedResult);
			String ActualResult = driver.findElement(By.xpath(object_xpath)).getAttribute("value");

			if (ActualResult.equals(ExpectedResult)) {
				test.log(Status.INFO, "Buffer Percentage Field is Editable");
			} else {
				reportFailure("Buffer Percentage Field is Non-Editable");
			}
		} catch (Exception e) {
			reportFailure("Buffer Percentage Field is Non-Editable");
			e.printStackTrace();
		}
	}

	/**
	 * verifyGlbBuffBtnStatus This Method to Check Global Buffer Save Button is
	 * enabled and clickable.
	 */
	public void verifyGlbBuffBtns() {
		try {
			String button = data.get(dataKey);
			String object_xpath = prop.getProperty(ObjectKey);

			test.log(Status.INFO, "Verifying Global Buffer " + button + " Button is enabled and clickable.");

			String result = driver.findElement(By.xpath(object_xpath)).getAttribute("data-display");
			if (result.equals("True")) {
				test.log(Status.INFO, button + " Button is Enabled and Clickable");
			} else {
				reportFailure(button + " Button is not Enabled and Non-Clickable");
			}
		} catch (Exception e) {
			reportFailure("Button is not Enabled and Non-Clickable");
			e.printStackTrace();
		}
	}

	/**
	 * verifyBuffPercentData This Method to Check Global Buffer Percentage Field
	 * accept input value "ZERO".
	 */
	public void verifyBuffPercentData() {
		try {
			String ExpectedResult = data.get(dataKey).substring(0, data.get(dataKey).length() - 2);
			test.log(Status.INFO, "Verifying Buffer Percentage Field accept valid Input Values 'Between 0 to 100'.");
			String object_xpath = prop.getProperty(ObjectKey);

			driver.findElement(By.xpath(object_xpath)).clear();
			driver.findElement(By.xpath(object_xpath)).sendKeys(ExpectedResult);
			driver.findElement(By.xpath(object_xpath)).sendKeys(Keys.TAB);
			String fieldVal = driver.findElement(By.xpath(object_xpath)).getAttribute("aria-invalid");

			if (fieldVal.equals("false")) {
				test.log(Status.INFO,
						" Value " + ExpectedResult + " is accepted in the Global Buffer Percentage Field");
			} else {
				reportFailure(" Value " + ExpectedResult + " is not accepted in the Global Buffer Percentage Field");
			}
		} catch (Exception e) {
			reportFailure(" Value is not accepted in the Global Buffer Percentage Field");
			e.printStackTrace();
		}
	}

	/**
	 * verifyBuffPercentInvalidData This Method to Check Global Buffer Percentage
	 * Field does not accept input value "Greater than 100 and negative values".
	 */
	public void verifyBuffPercentInvalidData() {
		try {
			String ExpectedResult = data.get(dataKey).substring(0, data.get(dataKey).length() - 2);
			test.log(Status.INFO,
					"Verifying Buffer Percentage Field does not accept Invalid Values 'Negative and Greater than 100 Values'");
			String object_xpath = prop.getProperty(ObjectKey);

			driver.findElement(By.xpath(object_xpath)).clear();
			driver.findElement(By.xpath(object_xpath)).sendKeys(ExpectedResult);
			driver.findElement(By.xpath(object_xpath)).sendKeys(Keys.TAB);
			String fieldVal = driver.findElement(By.xpath(object_xpath)).getAttribute("aria-invalid");

			if (fieldVal.equals("true")) {
				test.log(Status.INFO,
						" Value " + ExpectedResult + " is not accepted in the Global Buffer Percentage Field");
			} else {
				reportFailure(" Value " + ExpectedResult + " is accepted in the Global Buffer Percentage Field");
			}
		} catch (Exception e) {
			reportFailure(" Value is accepted in the Global Buffer Percentage Field");
			e.printStackTrace();
		}
	}

	/**
	 * verifyBuffPercentAlphaSplChar This Method to Check Global Buffer Percentage
	 * Field does not allow to provide Alphabets and Special Characters".
	 */
	public void verifyBuffPercentAlphaSplChar() {
		try {
			String value = data.get(dataKey);
			test.log(Status.INFO,
					"Verifying Buffer Percentage Field accept input value 'Alphabets and Special Characters' or not.");
			String object_xpath = prop.getProperty(ObjectKey);

			driver.findElement(By.xpath(object_xpath)).clear();
			driver.findElement(By.xpath(object_xpath)).sendKeys(value);

			String fieldVal = driver.findElement(By.xpath(object_xpath)).getText();

			if (fieldVal.isEmpty()) {
				test.log(Status.INFO, " Value " + value
						+ " is not entered in the Global Buffer Percentage Field as the field does not accepts Alphabets and Special Characters");
			} else {
				reportFailure(" Value " + value
						+ " is entered in the Global Buffer Percentage Field as the field accepts Alphabets and Special Characters");
			}
		} catch (Exception e) {
			reportFailure(
					" Value is entered in the Global Buffer Percentage Field as the field accepts Alphabets and Special Characters");
			e.printStackTrace();
		}
	}

	/**
	 * verifyBufferPercentageError This Method to Verify creation of Global Buffer
	 * with values greater than 100 or negative values.
	 */
	public void verifyBufferPercentageError() {
		try {
			String object_xpath = prop.getProperty(ObjectKey);
			String errorMsg = driver.findElement(By.xpath(object_xpath)).getText();
			String inputVal = data.get(dataKey).substring(0, data.get(dataKey).length() - 2);
			try {
				test.log(Status.INFO, "Verifying Creation of Global Buffer with Invalid value 'Greater than 100'");

				if (errorMsg.contains("between 0 and 100")) {
					test.log(Status.INFO, errorMsg + " is dispalyed as invalid data: " + inputVal + " is provided");
				} else {
					reportFailure(errorMsg + " as data: " + inputVal + "provided is valid");
				}
			} catch (Exception e) {
				reportFailure(errorMsg + " as data: " + inputVal + "provided is valid");
				e.printStackTrace();
			}
		} catch (Exception e) {
			reportFailure("Unexpected exception is thrown: " + e);
			e.printStackTrace();
		}
	}

	/**
	 * verifyUndoAction This Method to Verify whether entered input is cleared.
	 */
	public void verifyUndoAction() {
		try {
			test.log(Status.INFO, "Verifying Undo Button clears the data provided");

			String initiVal = driver.findElement(By.xpath(prop.getProperty("PanaMA.MAGBPercentField_xpath")))
					.getAttribute("value");
			driver.findElement(By.xpath(prop.getProperty("PanaMA.MAGBPercentField_xpath"))).clear();
			driver.findElement(By.xpath(prop.getProperty("PanaMA.MAGBPercentField_xpath"))).sendKeys("134");
			driver.findElement(By.xpath(prop.getProperty("PanaMA.MAGBPercentField_xpath"))).sendKeys(Keys.TAB);
			String finalVal = driver.findElement(By.xpath(prop.getProperty("PanaMA.MAGBPercentField_xpath")))
					.getAttribute("value");
			String object_xpath = prop.getProperty(ObjectKey);

			WebElement element = driver.findElement(By.xpath(prop.getProperty(ObjectKey)));

			if (element != null) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			}

			driver.findElement(By.xpath(object_xpath)).click();
			driver.findElement(By.xpath(prop.getProperty("PanaMA.MAGBPercentField_xpath"))).clear();

			driver.findElement(By.xpath(prop.getProperty("PanaMA.MAGBSaveChngOK_xpath"))).click();

			if (finalVal != initiVal) {
				test.log(Status.INFO, "Undo Button has resets the value to previous saved Global Buffer value");
			} else {
				reportFailure("Undo Button didnot reset the value to previous saved Global Buffer value");
			}
		} catch (Exception e) {
			reportFailure("Undo Button didnot reset the value to previous saved Global Buffer value");
			e.printStackTrace();
		}
	}

	/**
	 * verifyCancelAction This Method is to Verify clicking on CANCEL will not make
	 * any changes on the page
	 */
	public void verifyCancelAction() {
		try {
			test.log(Status.INFO, "Verifying clicking on Cancel button, should not make any changes on the page.");
			String object_xpath = prop.getProperty(ObjectKey);
			String value = data.get(dataKey).substring(0, data.get(dataKey).length() - 2);

			driver.findElement(By.xpath(object_xpath)).click();

			String title = driver.findElement(By.xpath(prop.getProperty("PanaMA.MAGBName_xpath"))).getText();
			String actValue = driver.findElement(By.xpath(prop.getProperty("PanaMA.MAGBPercentField_xpath")))
					.getAttribute("value");

			if ((title.equals("GLOBAL BUFFER")) && (value.equals(actValue))) {
				test.log(Status.INFO, "Cancel Action has not made any changes");
			} else {
				reportFailure("Cancel Action has made changes");
			}
		} catch (Exception e) {
			reportFailure("Cancel Action has made changes");
			e.printStackTrace();
		}
	}

	/**
	 * forceCloseWOCreateStatus This Method is to Verify Workorder to be forceclosed
	 * for the orders in Created Status.
	 */
	public void forceCloseWOCreateStatus() {
		try {
			test.log(Status.INFO, "Verifying closure of Workorder when ForceClosed.");
			List<WebElement> rows = driver.findElements(By.xpath(prop.getProperty("PanaMA.MAWOListTableRow_xpath")));
			int rowCount = rows.size();
			String woName = data.get(dataKey);

			for (int i = 1; i <= rowCount; i++) {

				String envXpathRow = prop.getProperty("PanaMA.MAWOListTableColumn_xpath");
				String XpathRow = envXpathRow.replaceAll("z", Integer.toString(i));

				List<WebElement> column = driver.findElements(By.xpath(XpathRow));

				int columnCount = column.size();

				for (int j = 1; j <= columnCount; j++) {
					String envXpathCol = prop.getProperty("PanaMA.MAWOListTableRowColumn_xpath");

					String XpathCol = envXpathCol.replaceAll("z", Integer.toString(i));
					String XpathCol1 = XpathCol.replace("q", Integer.toString(j));

					String object_xpathWO = prop.getProperty("PanaMA.MAWOListTableWOName_xpath");
					String XpathWO = object_xpathWO.replaceAll("z", Integer.toString(i));
					String actWOName = driver.findElement(By.xpath(XpathWO)).getText();

					String fieldVal = driver.findElement(By.xpath(XpathCol1)).getText();

					if ((fieldVal.equals("Created")) && (actWOName.equals(woName))) {

						String envXpathForceClose = prop.getProperty("PanaMA.MAWOListForceCloseIcon_xpath");
						String xpathForceClose = envXpathForceClose.replaceAll("z", Integer.toString(i));

						driver.findElement(By.xpath(xpathForceClose)).click();
						driver.findElement(By.xpath(prop.getProperty("PanaMA.MAWOListPopUpOK_xpath"))).click();

						test.log(Status.INFO, "Verifying whether Workorder is Force Closed");
						List<WebElement> starElements = driver
								.findElements(By.xpath(prop.getProperty("PanaMA.NotifyErrorMessage_xpath")));

						Boolean Found = Boolean.FALSE;
						for (WebElement Element : starElements) {
							if (Element.getText().equals("Workorder closed Successfully.")) {
								driver.findElement(By.xpath(prop.getProperty("PanaMA.MANewWOEmConfig_xpath"))).click();
								Found = Boolean.TRUE;
								break;
							}
						}
						if (Found == Boolean.TRUE) {
							test.log(Status.INFO, "Workorder Force Closed successfully");
							break;
						} else {
							reportFailure("Unable to ForceClose workorder");
						}
					}

				}

			}

		} catch (Exception e) {
			reportFailure("Unable to ForceClose workorder");
			e.printStackTrace();
		}
	}

	/**
	 * sortByDefault This Method is to Verify Workorder names are sorted in
	 * ascending order or not in WorkorderList page by default.
	 */
	public void sortByDefault() {
		try {
			String object_xpath = prop.getProperty(ObjectKey);
			String columnName = data.get(dataKey);
			test.log(Status.INFO,
					"Verifying Workorder Names are sorted in Ascending order or not in WorkorderList based on "
							+ columnName);

			List<WebElement> woNamesList = driver.findElements(By.xpath(object_xpath));
			int woNamesCount = woNamesList.size();
			List<String> woNames = new ArrayList<String>();
			String nameOfWO;

			for (int i = 0; i < woNamesCount; i++) {
				nameOfWO = woNamesList.get(i).getText();
				woNames.add(nameOfWO);
			}

			boolean sortStatus = Ordering.natural().isStrictlyOrdered(woNames);
			if (sortStatus == false) {
				test.log(Status.INFO,
						columnName + " in the Workorder List are not sorted based on  Workorder Names by default");

			} else {
				reportFailure(columnName + " in the Workorder List are sorted based on names of Workorder by default");
			}
		} catch (Exception e) {
			reportFailure("Column in the Workorder List are sorted based on names of Workorder by default");
			e.printStackTrace();
		}
	}

	/**
	 * sortByAscending This Method is to Verify Workorder List table rows to be
	 * sorted in Ascending order in WorkorderList page.
	 */
	public void sortByAscending() {
		try {
			String columnName = data.get(dataKey);
			String object_xpath = prop.getProperty(ObjectKey);
			test.log(Status.INFO,
					"Verifying Workorder List table rows to be sorted in Ascending order based on " + columnName);

			List<WebElement> namesList = driver.findElements(By.xpath(object_xpath));
			int namesCount = namesList.size();
			List<String> names = new ArrayList<String>();
			List<String> sortedNames = new ArrayList<String>();
			String nameOfWO;

			for (int i = 0; i < namesCount; i++) {
				nameOfWO = namesList.get(i).getText();
				names.add(nameOfWO);
				sortedNames.add(nameOfWO);
			}

			Collections.sort(sortedNames, Ordering.natural());

			Assert.assertEquals(names, sortedNames);
			test.log(Status.INFO, columnName + " in the Workorder List table are sorted in Ascending order.");
		} catch (AssertionError e) {
			reportFailure("Column in the Workorder List table are sorted in Ascending order.");
		}
	}

	/**
	 * sortByDescending This Method is to Verify Workorder List table rows to be
	 * sorted in Descending order in WorkorderList page.
	 */
	public void sortByDescending() {
		try {
			String columnName = data.get(dataKey);
			String object_xpath = prop.getProperty(ObjectKey);
			test.log(Status.INFO,
					"Verifying Workorder List table rows to be sorted in Descending order based on " + columnName);

			List<WebElement> namesList = driver.findElements(By.xpath(object_xpath));
			int namesCount = namesList.size();
			List<String> names = new ArrayList<String>();
			List<String> sortedNames = new ArrayList<String>();

			String nameOfWO;

			for (int i = 0; i < namesCount; i++) {
				nameOfWO = namesList.get(i).getText();
				names.add(nameOfWO);
				sortedNames.add(nameOfWO);
			}

			Collections.sort(sortedNames, Ordering.natural().reverse());

			Assert.assertEquals(names, sortedNames);
			test.log(Status.INFO, columnName + " in the Workorder List table are sorted in Descending order.");

		} catch (AssertionError e) {
			reportFailure("Column in the Workorder List table are sorted in Ascending order.");
		}
	}

	/**
	 * sortByDescending This Method is to Verify Workorder List table is Filtered
	 * based on the Schedule Start date and End date.
	 */
	public void verifyWODisplayedBasedonDate() {
		try {
			String object_xpath = prop.getProperty(ObjectKey);
			test.log(Status.INFO,
					"Verifying Workorder List table is Filtered based on the Schedule Start date and End date");

			String schedStDt, schedEndDt;

			List<WebElement> namesListStDt = driver.findElements(By.xpath(object_xpath));
			int namesCountStDt = namesListStDt.size();

			List<WebElement> namesListEndDt = driver
					.findElements(By.xpath(prop.getProperty("PanaMA.MAWOListSchedEndDt_xpath")));

			int i, count = 0;
			for (i = 0; i < namesCountStDt; i++) {
				schedStDt = namesListStDt.get(i).getText();
				schedEndDt = namesListEndDt.get(i).getText();
				String[] schedStDt1, schedStDt2;

				schedStDt1 = schedStDt.split(" ");
				schedStDt2 = schedEndDt.split(" ");

				if ((schedStDt1[0].compareTo("1/1/2018") >= 0) && (schedStDt2[0].compareTo("3/30/2025") <= 0)) {
					count = count + 1;
				}
			}
			if (count == namesCountStDt) {
				test.log(Status.INFO,
						"All Workorders filtered during the Start date and End date period are dispalyed successfully.");

			} else {
				reportFailure("All Workorders filtered during the Start date and End date period are not displayed.");
			}
		} catch (Exception e) {
			reportFailure("All Workorders filtered during the Start date and End date period are not displayed.");
			e.printStackTrace();
		}
	}

	/**
	 * clickDynamicXpath This Method is to Click on the Element based on Xpath.
	 */
	public void clickDynamicXpath() {
		try {
			String dataValue = data.get(dataKey);
			String object_xpath = prop.getProperty(ObjectKey);
			String obj_xpath = object_xpath.replace("$", dataValue);
			test.log(Status.INFO, "Clicking " + obj_xpath);
			driver.findElement(By.xpath(obj_xpath)).click();
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * verifyWODisplayedBasedonLines This Method is to Verify Workorder List table
	 * is Workorder List table is Filtered based on the Lines.
	 */
	public void verifyWODisplayedBasedonLines() {
		String expLineName = data.get(dataKey);
		String object_xpath = prop.getProperty(ObjectKey);

		try {
			int count = 0;
			test.log(Status.INFO, "Verifying Workorder List table is Filtered based on the Lines");

			String lineName;
			List<WebElement> namesListLines = driver.findElements(By.xpath(object_xpath));
			int namesCountList = namesListLines.size();

			for (int i = 0; i < namesCountList; i++) {
				lineName = namesListLines.get(i).getText();
				if (lineName.equals(expLineName)) {
					count = count + 1;
				}
			}
			if (count == namesCountList) {
				test.log(Status.INFO,
						"All Workorders filtered for the Line " + expLineName + " are dispalyed successfully.");
			} else {
				reportFailure("All Workorders filtered for the Line " + expLineName + " are not dispalyed.");
			}
		} catch (Exception e) {
			reportFailure("All Workorders filtered for the Line " + expLineName + " are not dispalyed.");
			e.printStackTrace();
		}
	}

	/**
	 * verifyEnterProductEdit This Method to Check Enter Product Field is editable
	 * or not.
	 */
	public void verifyEnterProductEdit() {
		try {
			test.log(Status.INFO, "Verifying Buffer Percentage Field is editable or non editable field.");
			String object_xpath = prop.getProperty(ObjectKey);
			String ExpectedResult = data.get(dataKey);

			driver.findElement(By.xpath(object_xpath)).clear();
			driver.findElement(By.xpath(object_xpath)).sendKeys(ExpectedResult);
			String ActualResult = driver.findElement(By.xpath(object_xpath)).getAttribute("value");

			if (ActualResult.equals(ExpectedResult)) {
				test.log(Status.INFO, "Enter Product Field is Editable");
			} else {
				reportFailure("Enter Product Field is Non-Editable");
			}
		} catch (Exception e) {
			reportFailure("Enter Product Field is Non-Editable");
		}
	}

	/**
	 * verifyEnterProductEdit This Method to Check Enter Product Field is editable
	 * or not.
	 */
	public void verifyEnterPartEdit() {
		try {
			test.log(Status.INFO, "Verifying Part Field is editable or non editable field.");
			String object_xpath = prop.getProperty(ObjectKey);
			String ExpectedResult = data.get(dataKey);

			driver.findElement(By.xpath(object_xpath)).clear();
			driver.findElement(By.xpath(object_xpath)).sendKeys(ExpectedResult);
			String ActualResult = driver.findElement(By.xpath(object_xpath)).getAttribute("value");

			if (ActualResult.equals(ExpectedResult)) {
				test.log(Status.INFO, "Enter Part Field is Editable");
			} else {
				reportFailure("Enter Part Field is Non-Editable");
			}
		} catch (Exception e) {
			reportFailure("Enter Part Field is Non-Editable");
		}
	}

	/**
	 * VerifyButtonIsDisabled This function verifies whether buttons are disabled
	 * initially.
	 */
	public void verifyButtonIsDisabled() {
		try {
			String button = data.get(dataKey);
			test.log(Status.INFO, "Verifying whether " + button + " button is initially disabled.");
			String ExpectedResult = "true";
			String object_xpath = prop.getProperty(ObjectKey);

			String ActualResult = driver.findElement(By.xpath(object_xpath)).getAttribute("disabled");
			if (ActualResult.equals(ExpectedResult)) {
				test.log(Status.INFO, button + " Button is in disabled state by default");
			} else {

				reportFailure(button + "Button is not in disabled state by default");
			}
		} catch (Exception e) {
			reportFailure("Button is not in disabled state by default");
		}
	}

	/**
	 * VerifyButtonIsEnabled This function verifies whether buttons are enabled
	 * initially.
	 */
	public void verifyButtonIsEnabled() {
		try {
			String button = data.get(dataKey);
			String object_xpath = prop.getProperty(ObjectKey);

			test.log(Status.INFO, "Verifying whether " + button + " button is initially enabled.");
			Boolean ExpectedResult = Boolean.TRUE;
			Boolean ActualResult = driver.findElement(By.xpath(object_xpath)).isEnabled();
			if (ActualResult.equals(ExpectedResult)) {

				test.log(Status.INFO, button + " Button is in Enabled State by Default");
			} else {

				reportFailure(button + "Button is not in Enabled State by Default");
			}
		} catch (Exception e) {
			reportFailure("Button is not in Enabled State by Default");
		}
	}

	/**
	 * errorMessage "Verifying Product Buffer Percentage Field accept Float value as
	 * input.
	 */
	public void errorMessage() {
		try {
			String inputVal = data.get(dataKey).substring(0, data.get(dataKey).length() - 2);
			String message = driver.findElement(By.xpath("//div[contains(@class,'notify')]")).getText();
			try {
				test.log(Status.INFO, "Verifying Product Buffer Percentage Field accept Float value as input.");

				if (message.contains("between 0 and 100")) {
					test.log(Status.INFO, message + " is dispalyed as invalid data: " + inputVal + " is provided");
				} else if (message.contains("No Product Buffer")) {
					test.log(Status.INFO,
							message + " is dispalyed as the product: " + inputVal + " does not have data");
				} else {
					reportFailure(message + " as data: " + inputVal + "provided is valid");
				}
			} catch (Exception e) {
				reportFailure(message + " as data: " + inputVal + "provided is valid");
				e.printStackTrace();
			}
		} catch (Exception e) {
			reportFailure("Unexpected exception is thrown: " + e);
		}

	}

	/**
	 * validateProductBufferUIComponents This function verifies whether Copy Button
	 * or CheckBox is Clickable.
	 */
	public void validateProductBufferUIComponents() {
		try {
			test.log(Status.INFO, "Verifying Product Buffer whether Copy Button or CheckBox is Clickable.");

			String object_xpath = prop.getProperty(ObjectKey);
			Boolean Found = Boolean.FALSE;
			Found = driver.findElement(By.xpath(object_xpath)).isDisplayed();
			Boolean Clickable = Boolean.FALSE;
			Clickable = driver.findElement(By.xpath(object_xpath)).isEnabled();
			if (Clickable == Boolean.TRUE) {
				test.log(Status.INFO, "Copy Button or CheckBox is Clickable");
				// driver.close();
			} else if (Found == Boolean.TRUE) {

				test.log(Status.INFO, "Page is navigated with Valid Input");
			} else {
				reportFailure("Invalid Input");
			}
		} catch (Exception e) {
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * verifyProductBuffUndoAction This Method to Verify whether entered input is
	 * cleared.
	 */
	public void verifyProductBuffUndoAction() {
		try {
			String fieldName = data.get(dataKey);

			test.log(Status.INFO, "Verifying Undo Button clears the data provided in  " + fieldName);
			String object_xpath = prop.getProperty(ObjectKey);

			String valInBuffField = driver.findElement(By.xpath(object_xpath)).getText();

			if (valInBuffField.isEmpty()) {
				test.log(Status.INFO, "Undo Button has cleared the data in the " + fieldName);
			} else {
				reportFailure("Undo Button has not cleared the data in the Product Buffer Percentage Field");
			}
		} catch (Exception e) {
			reportFailure("Undo Button has not cleared the data in the Product Buffer Percentage Field");
			e.printStackTrace();
		}
	}

	/**
	 * validateCheckBoxIsClicked This Method to Verify whether Product Buffer UI
	 * component Checkbox is Clickable.
	 */
	public void validateCheckBoxIsClicked() {
		try {
			test.log(Status.INFO, "Verifying Product Buffer UI component Checkbox is Clickable.");
			String object_xpath = prop.getProperty(ObjectKey);

			String chkBxVal = driver.findElement(By.xpath(object_xpath)).getAttribute("aria-checked");
			if (chkBxVal.equals("true")) {
				test.log(Status.INFO, "Successfully verified CheckBox is clicked");
			} else {
				reportFailure("Unable to click on the CheckBox");

			}
		} catch (Exception e) {
			reportFailure("Unable to click on the CheckBox");
			e.printStackTrace();
		}
	}

	/**
	 * verifyProductBuffEditField This Method to Verify whether Product Buffer Field
	 * accepts Special Characters.
	 */
	public void verifyProductBuffEditField() {
		try {
			String fieldName = data.get(dataKey);
			test.log(Status.INFO, "Verifying Product Buffer Field accepts Special Characters");
			String object_xpath = prop.getProperty(ObjectKey);

			String fieldVal = driver.findElement(By.xpath(object_xpath)).getAttribute("aria-invalid");

			if (fieldVal.equals("false")) {
				test.log(Status.INFO, " Value " + fieldName + " is accepted in the Product Buffer Edit Field");
			} else {
				reportFailure(" Value " + fieldName + " is not accepted in the Product Buffer Edit Field");
			}
		} catch (Exception e) {
			reportFailure(" Value is not accepted in the Product Buffer Edit Field");
			e.printStackTrace();
		}
	}

	/**
	 * verifyProductBuffFieldValidData This Method to Verify whether Product Buffer
	 * Field accepts Valid Input.
	 */
	public void verifyProductBufferValidData() {
		try {
			// String fieldName = data.get(dataKey);
			String prodName = data.get(dataKey);
			test.log(Status.INFO, "Verifying Product Buffer Field accepts Valid Input");
			String object_xpath = prop.getProperty(ObjectKey);

			List<WebElement> pdFieldList = driver.findElements(By.xpath(object_xpath));
			int countList = pdFieldList.size();
			if (countList > 0) {
				test.log(Status.INFO,
						" Value " + prodName + " is accepted in the Product Buffer Edit Field and List is dispalyed");
			} else if (countList == 0) {
				test.log(Status.INFO, " Value " + prodName
						+ " is accepted in the Product Buffer Edit Field, but no List is dispalyed");
			} else {
				reportFailure(" Value " + prodName
						+ " is not accepted in the Product Buffer Edit Field and no List is dispalyed");
			}
		} catch (Exception e) {
			reportFailure(" Value is not accepted in the Product Buffer Edit Field and no List is dispalyed");
			e.printStackTrace();
		}
	}

	/**
	 * verifyFilterSuccessful This function verifies whether filter on WO List is
	 * successful to not.
	 */
	public void verifyWOListFilter() throws Throwable {
		try {
			Date OldDate = Calendar.getInstance().getTime();
			DateFormat formatter = new SimpleDateFormat("M/dd/yyyy");
			Date subDays = DateUtils.addDays(OldDate, -2);
			String SelStartDate = formatter.format(subDays);
			Date addDays = DateUtils.addDays(OldDate, 10);
			String SelEndDate = formatter.format(addDays);

			driver.findElement(By.xpath(prop.getProperty("PanaMA.WorkOrderListSelStartDate_xpath"))).clear();
			driver.findElement(By.xpath(prop.getProperty("PanaMA.WorkOrderListSelStartDate_xpath")))
					.sendKeys(SelStartDate);
			driver.findElement(By.xpath(prop.getProperty("PanaMA.WorkOrderListSelEndDate_xpath"))).clear();
			driver.findElement(By.xpath(prop.getProperty("PanaMA.WorkOrderListSelEndDate_xpath"))).sendKeys(SelEndDate);

			Actions action = new Actions(driver);
			action.sendKeys(Keys.ESCAPE).perform();
			driver.findElement(By.xpath(prop.getProperty("PanaMA.ApplyBtn_xpath"))).click();
		} catch (Exception e) {
			reportFailure("Unexpected exception is thrown: " + e);
			e.printStackTrace();
		}
	}

	/**
	 * NavaigateToUrl This function To Navigate to URL.
	 */
	public void NavaigateToUrl() throws Throwable {
		try {
			test.log(Status.INFO, "Navigating to Page ..." + prop.getProperty(ObjectKey));
			driver.navigate().to(prop.getProperty(ObjectKey));
		} catch (Exception e) {
			reportFailure("Unable to Navigate URL" + prop.getProperty(ObjectKey));
		}
	}

	/**
	 * clickDropdown This function To click on DropDown.
	 */
	public void clickDropdown() throws Throwable {
		try {
			String drpDwnxpath = (prop.getProperty(ObjectKey).replace("$", data.get(dataKey)));
			System.out.println("Drop Dwn Path " + drpDwnxpath);

			test.log(Status.INFO, "Clicking on Drop Down" + drpDwnxpath);
			driver.findElement(By.xpath(drpDwnxpath)).click();
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}

	}

	/**
	 * importWO This function To Click Import Work Order.
	 */
	public void selectImportWOField() {
		try {
			int idx = Integer.parseInt(prop.getProperty(data.get(dataKey)));
			int drpDwmIdx = idx + 1;
			String xPth = prop.getProperty(ObjectKey).replace("$", Integer.toString(drpDwmIdx));
			WebElement element = driver.findElement(By.xpath(xPth));

			test.log(Status.INFO, "Clicking on, \"" + data.get(dataKey) + "\" Drop Down");

			// WebElement element =
			// driver.findElement(By.xpath("//*[contains(text(),'Closed')]"));
			if (element != null) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			}
			element.click();

			Actions action = new Actions(driver);

			for (int i = 1; i <= idx; i++) {
				action.sendKeys(Keys.ARROW_DOWN).build().perform();
			}
			Thread.sleep(Integer.parseInt(envProp.getProperty("WaitTimeThreeSec")));
			action.sendKeys(Keys.RETURN).click().build().perform();
			System.out.println("Clicked on Workorder Name");

//			element = driver.findElement(By.xpath("(//div[@class='jqx-icon-arrow-down jqx-icon'])[1]"));
//
//			if (element != null) {
//				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
//			}

			driver.findElement(By.xpath(prop.getProperty("PanaMA.MAImportWorkordersTmpName_xpath"))).click();

		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unable to Select " + data.get(dataKey) + " from Dropdown.");
		}

	}

	/**
	 * importWO This function To Click Import Work Order.
	 */
	public void selectDiffImportWOField() {
		try {
			System.out.println("In Diff Method .......");
			int idx1 = Integer.parseInt(prop.getProperty(data.get(dataKey)));

			int drpDwmIdx1 = idx1 + 1;
			System.out.println("Index ......." + drpDwmIdx1);
			String xPth = prop.getProperty(ObjectKey).replace("$", Integer.toString(drpDwmIdx1));
			System.out.println("XPATH .............." + xPth);
			test.log(Status.INFO, "Clicking on, \"" + data.get(dataKey) + "\" Drop Down");
			// if (waitUntilExists(xPth, "xpath")) {
			Thread.sleep(Integer.parseInt(envProp.getProperty("WaitTimeThreeSec")));
			WebElement Element = driver.findElement(By.xpath(xPth));

			if (Element != null) {
				System.out.println("Element is Not Null .....");
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", Element);
			}

			WebDriverWait wait = new WebDriverWait(driver, 5);
			Actions act = new Actions(driver);
			act.moveToElement(Element).build().perform();
			Thread.sleep(Integer.parseInt(envProp.getProperty("WaitTimeTwoSec")));
			wait.until(ExpectedConditions.elementToBeClickable(Element));

			Element.click();

			Actions action = new Actions(driver);

			for (int i = 1; i < idx1; i++) {
				action.sendKeys(Keys.ARROW_DOWN).build().perform();
			}
			verifyWOToastMessage();
			Thread.sleep(Integer.parseInt(envProp.getProperty("DynamicWaitTime")));
			action.sendKeys(Keys.RETURN).build().perform();
			System.out.println("Import WO ..... wait");
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}

	}

	/**
	 * importWO This function To Select Schedule End in Schedule Start Dropdown.
	 */
	public void selectSchedEndImportWOField() {
		try {
			System.out.println("In Diff Method .......");
			int idx1 = Integer.parseInt(prop.getProperty(data.get(dataKey)));

			int drpDwmIdx1 = idx1 + 1;
			System.out.println("Index ......." + drpDwmIdx1);
			String xPth = prop.getProperty(ObjectKey).replace("$", Integer.toString(drpDwmIdx1));
			System.out.println("XPATH .............." + xPth);
			test.log(Status.INFO, "Clicking on, \"" + data.get(dataKey) + "\" Drop Down");
			// if (waitUntilExists(xPth, "xpath")) {
			Thread.sleep(Integer.parseInt(envProp.getProperty("WaitTimeThreeSec")));
			WebElement Element = driver.findElement(By.xpath(xPth));

			if (Element != null) {
				System.out.println("Element is Not Null .....");
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", Element);
			}

			WebDriverWait wait = new WebDriverWait(driver, 5);
			Actions act = new Actions(driver);
			act.moveToElement(Element).build().perform();
			Thread.sleep(Integer.parseInt(envProp.getProperty("WaitTimeTwoSec")));
			wait.until(ExpectedConditions.elementToBeClickable(Element));

			Element.click();

			Actions action = new Actions(driver);

			for (int i = 1; i <= (idx1 + 1); i++) {
				action.sendKeys(Keys.ARROW_DOWN).build().perform();
			}
			verifyWOToastMessage();
			Thread.sleep(Integer.parseInt(envProp.getProperty("DynamicWaitTime")));
			action.sendKeys(Keys.RETURN).build().perform();
			System.out.println("Import WO ..... wait");
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}

	}

	/**
	 * verifyWOToastMessage This function To Verify the Toast Messaged when two Drop
	 * downs selected same elements.
	 */
	public void verifyWOToastMessage() {
		try {
			String ExpectedValue = "already selected in another field";
			String AcctToastMsg = "";
			System.out.println("In Verification .... " + ExpectedValue);
			test.log(Status.INFO, "Verifying the toast message, \"" + ExpectedValue + "\"");
			Boolean Found = Boolean.FALSE;
			List<WebElement> starElements = null;
			String notifyInfoXpath = prop.getProperty("PanaMA.NotifyMessage_xpath");
			try {
				starElements = driver.findElements(By.xpath(notifyInfoXpath));
			} catch (Exception e) {
				Found = Boolean.FALSE;
			}
			for (WebElement Element : starElements) {
				System.out.println("In For .....");
				if (Element.getText().contains(ExpectedValue)) {
					AcctToastMsg = Element.getText();
					Found = Boolean.TRUE;
					break;
				}
			}
			if (Found == Boolean.TRUE) {
				System.out.println("Found the toast message \"" + AcctToastMsg + "\". Hence Expected Result met.");
				test.log(Status.INFO, "Found the toast message \"" + AcctToastMsg + "\". Hence Expected Result met.");
			} else {
				reportFailure(
						"Did not find the toast Message \"" + ExpectedValue + "\". Hence Expected Result NOT met.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unable to get Toast Message ");
		}

	}

	/**
	 * verifyButtonDisable verifyButtonDisable This function To Verify the Button
	 * Enabled or Not.
	 */
	public void verifyButtonDisable() {
		try {
			String dataK = data.get(dataKey);
			if (dataK == null)
				dataK = "";
			test.log(Status.INFO, "Verifying  " + dataK + "is Disabled");
			String object_xpath = prop.getProperty(ObjectKey).replace("$", dataK);
			Boolean res = driver.findElement(By.xpath(object_xpath)).isEnabled();
			if (res) {
				reportFailure(dataK + " is Enabled. Failing Test case Since not met Expected Behaviour");
			} else {
				test.log(Status.INFO, dataK + " Button is Disabled");
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unable to Find Button");
		}

	}

	/**
	 * delete Template Deletes the Template in Import Work order.
	 */
	public void deleteTemplate() {
		try {
			String templateName = data.get(dataKey);
			DBTransaction db1 = new DBTransaction(envProp);
			db1.deleteWOImportTemplate(templateName);
			test.log(Status.INFO, "Deleted Template" + templateName);
		} catch (Exception e) {
			test.log(Status.INFO, "Template Not Found with Name");
		}
	}

	/**
	 * createTemplate Creates the Template in Import Work order.
	 */
	public void createTemplate() {
		try {
			String templateName = data.get(dataKey);
			driver.findElement(By.xpath(prop.getProperty("PanaMA.MAImportWorkordersTmpName_xpath")))
					.sendKeys(templateName);

			for (int drpDwnIdx = 2; drpDwnIdx < 11; drpDwnIdx++) {
				String xPth = prop.getProperty(ObjectKey).replace("$", Integer.toString(drpDwnIdx));
				WebElement element = driver.findElement(By.xpath(xPth));

				test.log(Status.INFO, "Clicking on, \"" + data.get(dataKey) + "\" Drop Down");

				// WebElement element =
				// driver.findElement(By.xpath("//*[contains(text(),'Closed')]"));
				if (element != null) {
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
				}
				element.click();

				Actions action = new Actions(driver);

				for (int i = 1; i < drpDwnIdx; i++) {
					action.sendKeys(Keys.ARROW_DOWN).build().perform();
				}
				Thread.sleep(Integer.parseInt(envProp.getProperty("WaitTimeOneSec")));
				action.sendKeys(Keys.RETURN).click().build().perform();
				driver.findElement(By.xpath(prop.getProperty("PanaMA.MAImportWorkordersTmpName_xpath"))).click();
				Thread.sleep(Integer.parseInt(envProp.getProperty("WaitTimeOneSec")));

			}

			String saveBtnXpath = prop.getProperty("PanaMA.MAImportWorkordersImportBtn_xpath").replace("$", "Save");
			System.out.println("SAve Btn ......." + saveBtnXpath);
			WebElement saveBtn = driver.findElement(By.xpath(saveBtnXpath));
			if (saveBtn != null) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", saveBtn);
			}
			saveBtn.click();

			driver.findElement(By.xpath(prop.getProperty("PanaMA.MAWOListPopUpOK_xpath"))).click();
			// captureToastMessage("Successfully.");

			// verifyTempCreation();

		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Not able to Create Import Template");
		}
	}

	public void verifyTempCreation() {
		try {
			String templateName = data.get(dataKey);
			String xPth = prop.getProperty(ObjectKey).replace("$", Integer.toString(1));
			WebElement element = driver.findElement(By.xpath(xPth));

			test.log(Status.INFO, "Clicking on, \"" + data.get(dataKey) + "\" Drop Down");

			if (element != null) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			}
			element.click();
			Thread.sleep(Integer.parseInt(envProp.getProperty("WaitTimeTwoSec")));
			String xPthTemplate = prop.getProperty("PanaMA.MAImportWorkordersFileDropDwn_xpath").replace("$",
					templateName);

			// System.out.println("XPath ...." + xPthTemplate);

			driver.findElement(By.xpath(xPthTemplate)).click();

			// System.out.println("After XPath ...." + xPthTemplate);
		} catch (Exception e) {
			reportFailure("Template is not created .....");
		}
	}

	public void verifyColumnMappingErrorMsg() {
		try {
			String templateName = data.get(dataKey);
			String textAreaXpath = prop.getProperty("PanaMA.MAImportWorkordersTmpName_xpath");
			test.log(Status.INFO, "Enter Text" + templateName + " in " + textAreaXpath);
			driver.findElement(By.xpath(textAreaXpath)).sendKeys(templateName);

			String saveBtnXpath = prop.getProperty("PanaMA.MAImportWorkordersImportBtn_xpath").replace("$", "Save");
			System.out.println("SAve Btn ......." + saveBtnXpath);
			WebElement saveBtn = driver.findElement(By.xpath(saveBtnXpath));
			if (saveBtn != null) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", saveBtn);
			}
			test.log(Status.INFO, "Clicking Save Button " + saveBtnXpath);
			saveBtn.click();

			captureToastMessage("No selection made for column mapping.");

		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Not able to Create Import Template");
		}

	}

	public void createTemplateDB() {
		try {
			String templateName = data.get(dataKey);
			DBTransaction db1 = new DBTransaction(envProp);
			db1.createWOImportTemplate(templateName);
			test.log(Status.INFO, "Creating Template" + templateName);
		} catch (Exception e) {
			test.log(Status.INFO, "Not able to Create template");
		}
	}

	/**
	 * selectTemplate Method to Select the WO Template from Dropdown.
	 */
	public void selectTemplate() {
		try {
			String templateName = data.get(dataKey);
			String xPth = prop.getProperty(ObjectKey).replace("$", Integer.toString(1));
			WebElement element = driver.findElement(By.xpath(xPth));

			test.log(Status.INFO, "Clicking on, \"" + data.get(dataKey) + "\" Drop Down");

			if (element != null) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			}
			element.click();
			Thread.sleep(Integer.parseInt(envProp.getProperty("WaitTimeTwoSec")));
			String xPthTemplate = prop.getProperty("PanaMA.MAImportWorkordersFileDropDwn_xpath").replace("$",
					templateName);

			System.out.println("XPath ...." + xPthTemplate);

			driver.findElement(By.xpath(xPthTemplate)).click();
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unable to Select Template ...");
		}
	}

	/**
	 * verifySuccessImportMessage Method to validate Successful import.
	 */
	public void verifySuccessImportMessage() {
		try {

			String message = driver.findElement(By.xpath(prop.getProperty(ObjectKey))).getText();

			System.out.println("Path ...." + prop.getProperty(ObjectKey));

			System.out.println("Import Msg ..." + message);

			if (message.contains("successfully")) {
				test.log(Status.INFO, "Imported Successfully");
			} else {
				String errorMsg = driver
						.findElement(By.xpath(prop.getProperty("PanaMA.MAImportWorkordersImportErrorMsg_xpath")))
						.getText();
				reportFailure("Import Un Successful \" " + errorMsg + " \"");
			}

		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unable to Select Template ...");
		}
	}

	/**
	 * verifyDeleteTemp Method to validate deletion of WO Import Template.
	 */
	public void verifyDeleteTemp() {
		try {
			String templateName = data.get(dataKey);
			String xPth = prop.getProperty(ObjectKey).replace("$", Integer.toString(1));
			WebElement element = driver.findElement(By.xpath(xPth));

			test.log(Status.INFO, "Clicking on, \"" + data.get(dataKey) + "\" Drop Down");

			if (element != null) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			}
			element.click();
			Thread.sleep(Integer.parseInt(envProp.getProperty("WaitTimeTwoSec")));
			String xPthTemplate = prop.getProperty("PanaMA.MAImportWorkordersFileDropDwn_xpath").replace("$",
					templateName);

			System.out.println("XPath ...." + xPthTemplate);

			driver.findElement(By.xpath(xPthTemplate)).click();

			String delBtnXpath = prop.getProperty("PanaMA.MAImportWorkordersImportBtn_xpath").replace("$", "Delete");
			System.out.println("Deletee Btn ......." + delBtnXpath);
			WebElement delBtn = driver.findElement(By.xpath(delBtnXpath));
			if (delBtn != null) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", delBtn);
			}
			test.log(Status.INFO, "Clicking Delete Button, " + delBtnXpath);
			delBtn.click();

			driver.findElement(By.xpath(prop.getProperty("PanaMA.MAWOListPopUpOK_xpath"))).click();

			captureToastMessage("Successfully.");
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * verifyDeleteTemp Method to validate deletion of WO Import Template.
	 */
	public void verifyUndoTemp() {
		try {
			String templateName = data.get(dataKey);
			String xPth = prop.getProperty(ObjectKey).replace("$", Integer.toString(1));
			WebElement element = driver.findElement(By.xpath(xPth));

			test.log(Status.INFO, "Clicking on, \"" + data.get(dataKey) + "\" Drop Down");

			if (element != null) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			}
			element.click();
			Thread.sleep(Integer.parseInt(envProp.getProperty("WaitTimeTwoSec")));
			String xPthTemplate = prop.getProperty("PanaMA.MAImportWorkordersFileDropDwn_xpath").replace("$",
					templateName);

			System.out.println("XPath ...." + xPthTemplate);

			driver.findElement(By.xpath(xPthTemplate)).click();

			String delBtnXpath = prop.getProperty("PanaMA.MAImportWorkordersImportBtn_xpath").replace("$", "Undo");
			System.out.println("Undo Btn ......." + delBtnXpath);
			WebElement delBtn = driver.findElement(By.xpath(delBtnXpath));
			if (delBtn != null) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", delBtn);
			}
			test.log(Status.INFO, "Clicking Undo Button, " + delBtnXpath);
			delBtn.click();

			driver.findElement(By.xpath(prop.getProperty("PanaMA.MAWOListPopUpOK_xpath"))).click();

			if (element != null) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			}

			WebElement elementAfterUndo = driver
					.findElement(By.xpath(prop.getProperty("PanaMA.MAImportWorkordersSelectFile_xpath")));

			if (elementAfterUndo != null) {
				test.log(Status.INFO, "Undo Successfull.");
			} else {
				reportFailure("Not able to Perform Undo");
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Not able to Perform Undo");
		}
	}

	/**
	 * verifyUpdateTemp Method to validate the Create and Update the WO Import
	 * Template.
	 */
	public void verifyUpdateTemp() {
		try {
			String templateName = data.get(dataKey);
			driver.findElement(By.xpath(prop.getProperty("PanaMA.MAImportWorkordersTmpName_xpath")))
					.sendKeys(templateName);

			int drpDwnIdx = 2;

			String xPth = prop.getProperty(ObjectKey).replace("$", Integer.toString(drpDwnIdx));
			WebElement element = driver.findElement(By.xpath(xPth));

			test.log(Status.INFO, "Clicking on, \"" + data.get(dataKey) + "\" Drop Down");

			// WebElement element =
			// driver.findElement(By.xpath("//*[contains(text(),'Closed')]"));
			if (element != null) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			}
			element.click();

			Actions action = new Actions(driver);

			for (int i = 1; i < drpDwnIdx; i++) {
				action.sendKeys(Keys.ARROW_DOWN).build().perform();
			}
			Thread.sleep(Integer.parseInt(envProp.getProperty("WaitTimeOneSec")));
			action.sendKeys(Keys.RETURN).click().build().perform();
			driver.findElement(By.xpath(prop.getProperty("PanaMA.MAImportWorkordersTmpName_xpath"))).click();

			String saveBtnXpath = prop.getProperty("PanaMA.MAImportWorkordersImportBtn_xpath").replace("$", "Save");
			System.out.println("SAve Btn ......." + saveBtnXpath);
			WebElement saveBtn = driver.findElement(By.xpath(saveBtnXpath));
			if (saveBtn != null) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", saveBtn);
			}
			saveBtn.click();

			driver.findElement(By.xpath(prop.getProperty("PanaMA.MAWOListPopUpOK_xpath"))).click();

			captureToastMessage("Successfully.");

			xPth = prop.getProperty(ObjectKey).replace("$", Integer.toString(1));
			element = driver.findElement(By.xpath(xPth));

			test.log(Status.INFO, "Clicking on, \"" + data.get(dataKey) + "\" Drop Down");

			if (element != null) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			}
			element.click();

			Thread.sleep(Integer.parseInt(envProp.getProperty("WaitTimeTwoSec")));
			String xPthTemplate = prop.getProperty("PanaMA.MAImportWorkordersFileDropDwn_xpath").replace("$",
					templateName);

			System.out.println("XPath ...." + xPthTemplate);

			driver.findElement(By.xpath(xPthTemplate)).click();

			drpDwnIdx = 3;

			String xPth1 = prop.getProperty(ObjectKey).replace("$", Integer.toString(drpDwnIdx));
			WebElement element1 = driver.findElement(By.xpath(xPth1));

			test.log(Status.INFO, "Clicking on, \"" + data.get(dataKey) + "\" Drop Down");

			if (element1 != null) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
			}
			element1.click();

			Actions act = new Actions(driver);

			for (int i = 1; i < drpDwnIdx - 2; i++) {
				act.sendKeys(Keys.ARROW_DOWN).build().perform();
			}
			Thread.sleep(Integer.parseInt(envProp.getProperty("WaitTimeOneSec")));
			act.sendKeys(Keys.RETURN).click().build().perform();

			saveBtnXpath = prop.getProperty("PanaMA.MAImportWorkordersImportBtn_xpath").replace("$", "Save");
			System.out.println("SAve Btn 2......." + saveBtnXpath);
			saveBtn = driver.findElement(By.xpath(saveBtnXpath));
			if (saveBtn != null) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", saveBtn);
			}
			Thread.sleep(Integer.parseInt(envProp.getProperty("WaitTimeOneSec")));
			saveBtn.click();

			driver.findElement(By.xpath(prop.getProperty("PanaMA.MAWOListPopUpOK_xpath"))).click();

			captureToastMessage("Successfully.");
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void sendFile() {
		try {
			// System.out.println("Typing"+prop.getProperty(ObjectKey)+".Data-
			// "+data.get(dataKey));
			String csvFilePath = System.getProperty("user.dir") + envProp.getProperty("ImportCSVFilePath");
			String fileName = csvFilePath + "\\" + data.get(dataKey);

			test.log(Status.INFO, "Typing in " + prop.getProperty(ObjectKey) + ".Data- " + data.get(dataKey));
			// driver.findElement(By.xpath(prop.getProperty(ObjectKey))).sendKeys(data.get(dataKey));
			// driver.findElement(By.xpath(prop.getProperty(ObjectKey))).clear();

			System.out.println("File Name   " + fileName);

			DymamicWait();
			getObject(ObjectKey).sendKeys(fileName);
		} catch (Exception e) {
			reportFailure("File Not avaialble to Import");
		}
	}

	public void verifyTitle() {
		try {
			DymamicWait();
			// reportFailure("Title From XLS:");
			String TitleFromXls = data.get(dataKey);
			String object_xpath = prop.getProperty(ObjectKey);
			String TitleFromPage = driver.findElement(By.xpath(object_xpath)).getText();
			if (TitleFromXls.equals(TitleFromPage)) {
				test.log(Status.INFO, "Successfully verified the title");
				// reportFailure("Title From XLS: " + TitleFromXls +" does not match title from
				// page " + TitleFromPage);
			} else {
				reportFailure("Title From XLS: " + TitleFromXls + " does not match title from page " + TitleFromPage);
			}
		} catch (Exception e) {
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * verifyAllItemsChecked This Method is related to Reservation screen. It
	 * verifies whether all checkboxes in a box are in checked state.
	 */
	public void verifyAllItemsChecked() {
		try {
			List<WebElement> Elements = driver.findElements(By.xpath(prop.getProperty(ObjectKey)));
			for (WebElement Element1 : Elements) {
				System.out.println(Element1.getAttribute("class"));
				if (!Element1.getAttribute("class").equals("jqx-checkbox-check-checked")) {
					reportFailure(Element1 + " is not checked");
					break;
				}
			}
			test.log(Status.INFO, "All elements are checked.");
		} catch (Exception e) {
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * verifyAllItemsUnChecked This Method is related to Reservation screen. It
	 * verifies whether all checkboxes in a box are in unchecked state.
	 */
	public void verifyAllItemsUnChecked() {
		// reportFailure("Element is checked");
		try {
			List<WebElement> Elements = driver.findElements(By.xpath(prop.getProperty(ObjectKey)));
			for (WebElement Element1 : Elements) {
				if (Element1.getAttribute("class").equals("jqx-checkbox-check-checked")) {
					reportFailure(Element1 + " is checked");
					break;
				}
			}
			test.log(Status.INFO, "All elements are unchecked.");
		} catch (Exception e) {
			reportFailure("Element is checked");
		}
	}

	/**
	 * verifyDescription This Method verifies the description of Screen. Screen
	 * description is passed via data key.
	 */
	public void verifyDescription() {
		try {
			// reportFailure("Description From XLS:");
			DymamicWait();
			String DescriptionFromXls = data.get(dataKey);
			String object_xpath = prop.getProperty(ObjectKey);
			String DescriptionFromPage = driver.findElement(By.xpath(object_xpath)).getText();
			if (DescriptionFromXls.equals(DescriptionFromPage)) {
				test.log(Status.INFO, "Successfully verified the Description");
			} else {
				reportFailure("Description From XLS: " + DescriptionFromXls + " does not match Description from page "
						+ DescriptionFromPage);
			}
		} catch (Exception e) {
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * verifySelectedConfigurationPreference This method is related to Reservation
	 * Screen. It verifies the configuration preference equal to user/global.
	 * user/global is configured as datakey.
	 */
	public void verifySelectedConfigurationPreference() {
		try {
			DynamicWait();
			String ConfigurationPreferenceFromXls = data.get(dataKey);
			String object_xpath = prop.getProperty(ObjectKey);
			String ConfigurationPreferenceFromPage = driver.findElement(By.xpath(object_xpath)).getAttribute("value");
			if (ConfigurationPreferenceFromXls.equals(ConfigurationPreferenceFromPage)) {
				test.log(Status.INFO,
						"Successfully verified the Configuration Preference " + ConfigurationPreferenceFromXls);
			} else {
				reportFailure("Configuration Preference From XLS: " + ConfigurationPreferenceFromPage
						+ " does not match Configuration Preference from page " + ConfigurationPreferenceFromPage);
			}
		} catch (Exception e) {
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * verifyToastMessage This Method verifies any Toast message. Toast message to
	 * be verified, is configured in data key.
	 */
	public void verifyToastMessage() {
		try {
			String ExpectedValue = data.get(dataKey);
			test.log(Status.INFO, "Verifying the toast message " + ExpectedValue);
			Boolean Found = Boolean.FALSE;
			List<WebElement> starElements = null;
			String notifyInfoXpath = prop.getProperty("PanaMA.NotifyMessage_xpath");
			try {
				starElements = driver.findElements(By.xpath(notifyInfoXpath));
			} catch (Exception e) {
				Found = Boolean.FALSE;
			}

			for (WebElement Element : starElements) {
				if (Element.getText().contains(ExpectedValue)) {
					Found = Boolean.TRUE;
					break;
				}
			}
			if (Found == Boolean.TRUE) {
				System.out.println("Found the toast message \"" + ExpectedValue + "\". Hence Expected Result met.");
				test.log(Status.INFO, "Found the toast message \"" + ExpectedValue + "\". Hence Expected Result met.");
			} else {
				reportFailure(
						"Did not find the toast Message \"" + ExpectedValue + "\". Hence Expected Result NOT met.");
			}
		} catch (Exception e) {
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void captureToastMessage(String msg) {
		try {
			String ExpectedValue = msg;
			String actMsg = null;
			test.log(Status.INFO, "Verifying the toast message " + ExpectedValue);
			Boolean Found = Boolean.FALSE;
			List<WebElement> starElements = null;
			String notifyInfoXpath = prop.getProperty("PanaMA.NotifyMessage_xpath");
			try {
				starElements = driver.findElements(By.xpath(notifyInfoXpath));
			} catch (Exception e) {
				Found = Boolean.FALSE;
			}
			for (WebElement Element : starElements) {
				if (Element.getText().contains(ExpectedValue)) {
					actMsg = Element.getText();
					Found = Boolean.TRUE;
					break;
				}
			}
			if (Found == Boolean.TRUE) {
				System.out.println("Found the toast message \"" + actMsg + "\". Hence Expected Result met.");
				test.log(Status.INFO, "Found the toast message \"" + actMsg + "\". Hence Expected Result met.");
			} else {
				reportFailure(
						"Did not find the toast Message \"" + ExpectedValue + "\". Hence Expected Result NOT met.");
			}
		} catch (Exception e) {
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * verifyAlertErrorMessage This Method verifies Alert message visible in red
	 * color. Alert message to be verified, is configured in data key.
	 */
	public void verifyAlertErrorMessage() {
		try {
			String object_xpath = prop.getProperty(ObjectKey);
			if (driver.findElement(By.xpath(object_xpath)) != null) {
				test.log(Status.INFO, "Found the Alert Error Message");
			} else {
				reportFailure("Did not find the Alert Error Message");
			}
		} catch (Exception e) {
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * VerifySOMovedToUnselected This Method verifies Search order can successfully
	 * moved to UnSelected window.
	 */
	public void VerifySOMovedToUnselected() throws InterruptedException {
		try {
			// Thread.sleep(20000);
			String object_xpath = prop.getProperty("PanaMA.MAReservationScreenUnSelectedSearchordersItems_xpath");
			String soValue = data.get(dataKey);
			String object_xpath1 = object_xpath.replace("$", soValue);
			if (driver.findElement(By.xpath(object_xpath1)) != null) {
				test.log(Status.INFO, "Successfully verfieid search order moved to unselected.");
			} else {
				reportFailure("Could not verify search order moved to unselected.");
			}
		} catch (Exception e) {
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * VerifySOMovedToSelected This Method verifies Search order can successfully
	 * moved to Selected window.
	 */
	public void VerifySOMovedToSelected() {
		try {
			String object_xpath = prop.getProperty("PanaMA.MAReservationScreenSelectedSearchordersItems_xpath");
			String soValue = data.get(dataKey);
			String object_xpath1 = object_xpath.replace("$", soValue);
			if (driver.findElement(By.xpath(object_xpath1)) != null) {
				test.log(Status.INFO, "Successfully verfieid search order moved to Selected.");
			} else {
				reportFailure("Could not verify search order moved to selected.");
			}
		} catch (Exception e) {
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * Sleep This Method halts the execution by specified value in datakey.
	 */
	public void Sleep() throws Exception, InterruptedException {
		try {
			String sleepValue = data.get(dataKey);
			System.out.println("Sleep.. " + sleepValue);
			Thread.sleep(Integer.parseInt(sleepValue));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * verifyFieldDropDwnList This function verifies whether Notification Name field
	 * is clickable and Drop Down List is displayed.
	 */
	public void verifyFieldDropDwnList() {
		try {
			String object_xpath = prop.getProperty(ObjectKey);
			int count = 0;
			test.log(Status.INFO, "Verifying Notification Name field is clickable and Drop Down List is displayed");

			List<WebElement> notifList = driver.findElements(By.xpath(object_xpath));
			int notifListCount = notifList.size();

			for (int i = 0; i < notifListCount; i++) {
				driver.findElements(By.xpath((object_xpath)));
				count = count + 1;
			}

			if (count == notifListCount) {
				test.log(Status.INFO, "Notification Name field is clickable and Drop Down List is displayed");
			} else {
				reportFailure("Unable to click on Notification Name field and Drop Down List is not displayed");
			}

		} catch (Exception e) {
			reportFailure("Unable to click on Notification Name field and Drop Down List is not displayed");
			e.printStackTrace();
		}
	}

	/**
	 * verifyFieldDropDwnChkBx This function verifies whether Notification Name
	 * field is clickable and checkbox are selected.
	 */
	public void verifyFieldDropDwnChkBx() {
		try {
			String object_xpath = prop.getProperty(ObjectKey);
			int count = 0;
			test.log(Status.INFO, "Verifying Notification Name field is clickable and checkbox are selected");

			List<WebElement> notifList = driver
					.findElements(By.xpath(prop.getProperty("PanaMA.MAEmailConfigNotifNameList_xpath")));
			int notifListCount = notifList.size();

			for (int i = 1; i <= notifListCount; i++) {
				String listXpath = object_xpath.replace("$", Integer.toString(i));
				WebElement chkBx = driver.findElement(By.xpath(listXpath));
				chkBx.click();
				String chkBxStatus = chkBx.getAttribute("aria-selected");
				if (chkBxStatus.equals("true")) {
					count = count + 1;
				}
			}
			if (count == notifListCount) {
				test.log(Status.INFO,
						"Notification Name field is clickable and All the values in the Drop Down List are selected");
			} else {
				reportFailure("All values in the Drop Down List are not selected");
			}

		} catch (Exception e) {
			reportFailure("All values in the Drop Down List are not selected");
			e.printStackTrace();
		}

	}

	/**
	 * verifyFieldEditable This Method is to Verify if the data input Field is
	 * editable or not.
	 */
	public void verifyFieldEditable() {
		try {
			String object_xpath = prop.getProperty(ObjectKey);
			String fieldValue = data.get(dataKey);

			test.log(Status.INFO, "Verifying Email Addresses is editable or non editable field.");

			String ActualResult = driver.findElement(By.xpath(object_xpath)).getAttribute("value");

			if (ActualResult.equals(fieldValue)) {
				test.log(Status.INFO, "Enter Email Addresses field is Editable");
			} else {
				reportFailure("Enter Email Addresses field  is Non-Editable");
			}
		} catch (Exception e) {
			reportFailure("Enter Email Addresses field  is Non-Editable");
		}
	}

	/**
	 * verifyFieldEnabled This Method to Verify if Notification Email Addresses
	 * field is disabled by default
	 */
	public void verifyFieldEnabled() {
		try {
			String dataValue = data.get(dataKey);
			String object_xpath = prop.getProperty(ObjectKey);
			String obj_xpath = object_xpath.replace("$", dataValue);
			test.log(Status.INFO, "Verifying Notification Email Addresses field is disabled by default.");

			String fieldStatus = driver.findElement(By.xpath(obj_xpath)).getAttribute("aria-disabled");

			if (fieldStatus.equals("true")) {
				test.log(Status.INFO, "Notification Email Addresses field is in Disabled state by default");
			} else {
				reportFailure("Notification Email Addresses field is not in Disabled state by default");
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Notification Email Addresses field is not in Disabled state by default");
		}
	}

	/**
	 * verifyFieldValidData This Method to Verify if Field accepts Valid data.
	 */
	public void verifyFieldValidData() {
		try {
			String object_xpath = prop.getProperty(ObjectKey);
			String fieldName = data.get(dataKey);
			String fieldStatus = driver.findElement(By.xpath(object_xpath)).getAttribute("aria-invalid");

			if (fieldStatus.equals("false")) {
				test.log(Status.INFO, "The data provided is accepted and valid for the field " + fieldName);

			} else {
				reportFailure("The data provided is not accepted and invalid for the field " + fieldName);
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("The data provided is not accepted and invalid for the field");
		}
	}

	/**
	 * verifyFieldInValidData This Method to Verify if Field accepts Valid data.
	 */
	public void verifyFieldInValidData() {
		try {
			String object_xpath = prop.getProperty(ObjectKey);
			String fieldName = data.get(dataKey);
			String fieldStatus = driver.findElement(By.xpath(object_xpath)).getAttribute("aria-invalid");

			if (fieldStatus.equals("true")) {
				test.log(Status.INFO, "The data provided is not accepted and invalid for the field " + fieldName);

				try {
					if (driver.findElement(By.xpath(prop.getProperty("PanaMA.MAEmailConfigEmAddError_xpath")))
							.isDisplayed()) {
						String errorMsg1 = driver
								.findElement(By.xpath(prop.getProperty("PanaMA.MAEmailConfigEmAddError_xpath")))
								.getText();
						test.log(Status.INFO,
								"The data provided is not accepted and error.... " + errorMsg1 + " is displayed");
					}
				} catch (Exception e) {
					// System.out.println("No error Message to display");
				}
			} else {
				reportFailure("The data provided is accepted and valid for the field " + fieldName);
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("The data provided is accepted and valid for the field ");
		}
	}

	/**
	 * verifyEmailConfigMessage This Method to Verify if the Email Address
	 * Configured is valid or invalid.
	 */
	public void verifyEmailConfigMessage() {
		try {
			String ExpectedValue = data.get(dataKey);
			test.log(Status.INFO, "Verifying if the Email Address Configured is valid or invalid");
			Boolean Found = Boolean.FALSE;
			List<WebElement> starElements = null;
			String notifyInfoXpath = prop.getProperty("PanaMA.NotifyErrorMessage_xpath");
			try {
				starElements = driver.findElements(By.xpath(notifyInfoXpath));
			} catch (Exception e) {
				Found = Boolean.FALSE;
			}

			for (WebElement Element : starElements) {
				if (Element.getText().equals(ExpectedValue)) {
					Found = Boolean.TRUE;
					break;
				}
			}

			if (Found == Boolean.TRUE) {
				test.log(Status.INFO, "Found the Message " + ExpectedValue + " Email configured is InValid.");
			} else {
				reportFailure("Did not find the Message " + ExpectedValue + "Result is not as Expected");
			}
		} catch (Exception e) {
			reportFailure("Unexpected exception is thrown: " + e);
			e.printStackTrace();
		}

	}

	/**
	 * verifyNotificationNameSelection This Method to Verify if the Notification
	 * Name field is clickable and checkbox are selected.
	 */
	public void verifyNotificationNameSelection() {
		try {
			String object_xpath = prop.getProperty(ObjectKey);
			int count = 0;
			int notifiNameCount = Integer.parseInt(data.get(dataKey).substring(0, data.get(dataKey).length() - 2));
			test.log(Status.INFO, "Verifying Notification Name field is clickable and checkbox are selected");

			List<WebElement> notifList = driver
					.findElements(By.xpath(prop.getProperty("PanaMA.MAEmailConfigNotifNameList_xpath")));
			int notifListCount = notifList.size();

			for (int i = 1; i <= notifListCount; i++) {
				String listXpath = object_xpath.replace("$", Integer.toString(i));
				WebElement chkBx = driver.findElement(By.xpath(listXpath));
				// chkBx.click();
				String chkBxStatus = chkBx.getAttribute("aria-selected");
				if (chkBxStatus.equals("true")) {
					count = count + 1;
				}
			}
			if (count == notifiNameCount) {
				test.log(Status.INFO,
						"Notification Name field is clickable and value in the Drop Down List are selected");
			} else {
				reportFailure("Values in the Drop Down List are not selected");
			}

		} catch (Exception e) {
			reportFailure("All values in the Drop Down List are not selected");
			e.printStackTrace();
		}
	}

	/**
	 * verifyRadioSelectedInReservationScreen This method verifies whether radio is
	 * selected in Reservation Screen.
	 */
	public void verifyRadioSelectedInReservationScreen() {
		try {
			test.debug("Verifying whether the Radio is selected in Reservation Screen");
			String complete_xpath = prop.getProperty(ObjectKey) + "/parent::*/parent::*";
			String checked = driver.findElement(By.xpath(complete_xpath)).getAttribute("class");
			System.out.println(checked);
			// assertEquals(checked.isSelected(),true);
			if (checked.equalsIgnoreCase("mat-radio-checked")) {
				test.debug("Radio Button  Checked--->" + checked);
			} else {
				test.debug("Radio Button Not Checked--->" + checked);
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * verifyRadioSelectedInReservationScreen This method verifies the value in text
	 * field of Reservation Screen.
	 */
	public void VerifyTextFieldValueInReservationScr() {
		try {
			DymamicWait();
			test.log(Status.INFO, "Verifying Text Field Value");
			String actual = driver.findElement(By.xpath(prop.getProperty(ObjectKey))).getAttribute("value");
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

	/**
	 * verifyItemIsChecked This Method is related to Reservation screen. It verifies
	 * whether a checkboxes in a box are in checked state.
	 */
	public void verifyItemIsChecked() {
		try {
			List<WebElement> Elements = driver.findElements(By.xpath(prop.getProperty(ObjectKey)));
			for (WebElement Element1 : Elements) {
				System.out.println(Element1.getAttribute("class"));
				if (!Element1.getAttribute("class").equals("jqx-checkbox-check-checked")) {
					reportFailure(Element1 + " is not checked");
					break;
				}
			}
			test.log(Status.INFO, "All elements are checked.");
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void importWO1() {
		try {
			WebElement Element = driver.findElement(By.xpath("(//div[@class=\"jqx-icon-arrow-down jqx-icon\"])[9]"));

			// WebElement element =
			// driver.findElement(By.xpath("//*[contains(text(),'Closed')]"));
			if (Element != null) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", Element);
			}
			Element.click();
			Actions action = new Actions(driver);

			HashMap<String, Integer> WOColumns = new HashMap<String, Integer>();
			WOColumns.put("WorkOrder Name", 1);
			WOColumns.put("Product Name", 2);
			WOColumns.put("Line Name", 3);
			WOColumns.put("Project Name", 4);
			WOColumns.put("Lot Name", 5);
			WOColumns.put("Lane", 6);
			WOColumns.put("Pattern Quantity", 7);
			WOColumns.put("Schedule Start", 8);
			WOColumns.put("Schedule End", 9);

			for (int i = 1; i <= WOColumns.get("Schedule End"); i++) {
				action.sendKeys(Keys.ARROW_DOWN).build().perform();
			}
			action.sendKeys(Keys.RETURN).build().perform();
			System.out.println("wait");
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * validatePartBufferUIComponents This function verifies whether Copy Button or
	 * CheckBox is Clickable.
	 */
	public void validatePartBufferUIComponents() {
		try {
			test.log(Status.INFO, "Verifying Part Buffer whether Copy Button or CheckBox is Clickable.");

			String object_xpath = prop.getProperty(ObjectKey);
			Boolean Found = Boolean.FALSE;
			Found = driver.findElement(By.xpath(object_xpath)).isDisplayed();
			Boolean Clickable = Boolean.FALSE;
			Clickable = driver.findElement(By.xpath(object_xpath)).isEnabled();
			if (Clickable == Boolean.TRUE) {
				test.log(Status.INFO, "Copy Button or CheckBox is Clickable");
				// driver.close();
			} else if (Found == Boolean.TRUE) {

				test.log(Status.INFO, "Page is navigated with Valid Input");
			} else {
				reportFailure("Invalid Input");
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * validatePartNumberColumns This function verifies whether Copy Button or
	 * CheckBox is Clickable.
	 */
	public void validatePartNumberColumns() {
		try {
			test.log(Status.INFO, "Verifying Part Number Column names");
			List<WebElement> Elements = driver
					.findElements(By.xpath(prop.getProperty("PanaMA.MAPartNumberColumn_xpath")));
			int Fail = 0;

			for (WebElement Element : Elements) {
				System.out.println(Element.getText());
				System.out.println(data.get(dataKey));
				if (Element.getText().contains(data.get(dataKey))) {

				} else {
					Fail = 1;
				}

			}
			if (Fail == 0) {
				test.log(Status.INFO, "All the part number columns are successfully verified.");
				// driver.close();
			} else if (Fail == 1) {

				reportFailure("Part Number columns does not contain Part Number letter");
			} else {
				reportFailure("Part Number columns does not contain Part Number letter");
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * verifyUndoActionEmailAddress This Method to Verify whether entered input is
	 * cleared.
	 */
	public void verifyUndoActionEmailAddress() {
		try {
			// String object_xpath = prop.getProperty(ObjectKey);
			test.log(Status.INFO, "Verifying Undo Button clears the data provided");

			String finalVal = getObject("PanaMA.MAEmailConfigEmAddField_xpath").getAttribute("value");

			if (finalVal.isEmpty()) {
				test.log(Status.INFO, "Undo Button has cleared the data enetered in the Email Addresses Field");
			} else {
				reportFailure("Undo Button has not cleared the data enetered in the Email Addresses Field");
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Undo Button has not cleared the data enetered in the Email Addresses Field");
		}
	}

	/**
	 * verifyCancelActionEmailConfig This Method to Verify whether Cancel Action has
	 * not made any changes to the data provided
	 */
	public void verifyCancelActionEmailConfig() {
		try {
			String object_xpath = prop.getProperty(ObjectKey);
			test.log(Status.INFO, "Verifying Cancel Action should not make any changes for data provided");
			int count = 0;
			String emailAdd = driver.findElement(By.xpath(prop.getProperty("PanaMA.MAEmailConfigEmAddField_xpath")))
					.getAttribute("value");

			List<WebElement> notifList = driver
					.findElements(By.xpath(prop.getProperty("PanaMA.MAEmailConfigNotifNameList_xpath")));
			int notifListCount = notifList.size();

			for (int i = 1; i <= notifListCount; i++) {
				String listXpath = object_xpath.replace("$", Integer.toString(i));
				WebElement chkBx = driver.findElement(By.xpath(listXpath));
				String chkBxStatus = chkBx.getAttribute("aria-selected");
				if (chkBxStatus.equals("true")) {
					count = count + 1;
				}
			}

			if (count > 0 && emailAdd.length() > 0) {
				test.log(Status.INFO, "Cancel Action has not made any changes to the data provided");
			} else {
				reportFailure("Cancel Action has cleared all the data provided");
			}

		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Cancel Action has cleared all the data provided");
		}
	}

	/**
	 * verifyEmailConfiguredSuccessfully This Method to Verify Successful Creation
	 * of Email for the Notification Name.
	 */
	public void verifyEmailConfiguredSuccessfully() {
		try {
			String ExpectedValue = data.get(dataKey);
			test.log(Status.INFO, "Verifying Successful Creation or Deletion of Email for the Notification Name.");
			Boolean Found = Boolean.FALSE;
			List<WebElement> starElements = null;
			String notifyInfoXpath = prop.getProperty("PanaMA.NotifyErrorMessage_xpath");

			try {
				starElements = driver.findElements(By.xpath(notifyInfoXpath));
			} catch (Exception e) {
				Found = Boolean.FALSE;
			}

			for (WebElement Element : starElements) {
				if (Element.getText().equals(ExpectedValue)) {
					Found = Boolean.TRUE;
					break;
				}
			}

			if (Found == Boolean.TRUE) {
				test.log(Status.INFO,
						"Found the Message.... ' " + ExpectedValue + " '..... Email Configuration successfull");

			} else {
				reportFailure(
						"Did not find the Message.... ' " + ExpectedValue + " '.....Email Configuration successfull");
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * verifyEmailDeletion This Method to Verify Successful Deletion of Email for
	 * the Notification Name.
	 */
	public void verifyEmailDeletion() {
		try {
			String ExpectedValue = data.get(dataKey);
			test.log(Status.INFO, "Verifying Successful Deletion of Email for the Notification Name.");
			Boolean Found = Boolean.FALSE;
			List<WebElement> starElements = null;
			String notifyInfoXpath = prop.getProperty("PanaMA.NotifyErrorMessage_xpath");

			try {
				starElements = driver.findElements(By.xpath(notifyInfoXpath));
			} catch (Exception e) {
				Found = Boolean.FALSE;
			}

			for (WebElement Element : starElements) {
				if (Element.getText().equals(ExpectedValue)) {
					Found = Boolean.TRUE;
					break;
				}
			}

			if (Found == Boolean.TRUE) {
				test.log(Status.INFO,
						"Found the Message.... ' " + ExpectedValue + " '..... Email Configuration successfull");

			} else {
				reportFailure(
						"Did not find the Message.... ' " + ExpectedValue + " '.....Email Configuration successfull");
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * createEmail This Method to Configure email address to the Notification
	 * through DB.
	 */
	public void createEmail() throws SQLException {
		try {
			test.log(Status.INFO, "Configuration of Email to Notification Name through DB");
			DBTransaction db1 = new DBTransaction(envProp);
			db1.deleteEmailforNotification(data.get(dataKey));
			db1.RunPgSqlEmailTransaction(data.get(dataKey));
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Configuration of Email to Notification Name failed");
		}
	}

	/**
	 * createMultipleEmail This Method to Configure email address to the multiple
	 * Notifications through DB.
	 */
	public void createMultipleEmail() throws SQLException {
		try {
			test.log(Status.INFO, "Configuration of Email to Notification Name through DB");
			DBTransaction db1 = new DBTransaction(envProp);
			db1.deleteEmailforNotification(data.get(dataKey));
			db1.RunPgSqlEmailMultipleTransaction(data.get(dataKey));
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Configuration of Email to Notification Name failed");
		}
	}

	/**
	 * deleteEmail This Method to Delete email address to the Notification.
	 */
	public void deleteEmail() throws SQLException {
		try {
			test.log(Status.INFO, "Deletion of Email Address configured to Notification Name through DB");
			DBTransaction db1 = new DBTransaction(envProp);
			db1.deleteEmailforNotification(data.get(dataKey));
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Deletion of Email Address configured to Notification Name failed");
		}
	}

	/**
	 * verifyUndoActionMutipleFieldsEmConfig This Method to Verify whether entered
	 * input data in mutiple fields of Email Configuration page is cleared.
	 */
	public void verifyUndoActionMutipleFieldsEmConfig() {
		try {
			String object_xpath = prop.getProperty(ObjectKey);
			int count = 0;
			test.log(Status.INFO, "Verifying Undo Button clears the data provided in all the fields");

			String emFieldVal = driver.findElement(By.xpath(prop.getProperty("PanaMA.MAEmailConfigEmAddField_xpath")))
					.getAttribute("value");
			String chkBxVal = driver.findElement(By.xpath(prop.getProperty("PanaMA.MAEmailConfigAllChkBxStatus_xpath")))
					.getAttribute("aria-checked");

			driver.findElement(By.xpath(prop.getProperty("PanaMA.MAEmailConfigNotifNameField_xpath"))).click();

			List<WebElement> notifList = driver
					.findElements(By.xpath(prop.getProperty("PanaMA.MAEmailConfigNotifNameList_xpath")));
			int notifListCount = notifList.size();

			for (int i = 1; i <= notifListCount; i++) {
				String listXpath = object_xpath.replace("$", Integer.toString(i));
				WebElement chkBx = driver.findElement(By.xpath(listXpath));
				String chkBxStatus = chkBx.getAttribute("aria-selected");
				if (chkBxStatus.equals("true")) {
					count = count + 1;
				}
			}

			if (emFieldVal.isEmpty() && count == 0 && chkBxVal.equals("false")) {
				test.log(Status.INFO, "Undo Button has cleared the data provided in all fields");
			} else {
				reportFailure("Undo Button has not cleared the data provided in all fields");
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Undo Button has not cleared the data provided in all fields");
		}
	}

	/* Kalyan Chakravarthy - This method checks the enabled status of a text box */
	public void checkEditmodeofTextBox() {
		try {
			test.log(Status.INFO, "Verifying the enabled status of a text box");
			String object_xpath = prop.getProperty(ObjectKey);
			boolean enabledValue = driver.findElement(By.xpath(object_xpath)).isEnabled();
			if (enabledValue) {
				test.log(Status.INFO, "Text Box field is enabled");
			} else {
				reportFailure("Text Box field is in disabled state");
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Text Box field is in disabled state");
		}
	}

	/*
	 * Kalyan Chakravarthy - This method verifies the part buffer copy button
	 * feature
	 */
	public void verifyPartBufferCopyButton() {
		try {
			test.log(Status.INFO, "Verifying updated record of the Part buffer using copy button");
			String object_xpath = prop.getProperty(ObjectKey);
			String bufferValuebeforecopying = data.get(dataKey);
			// System.out.println("bufferValuebeforecopying"+bufferValuebeforecopying);
			String bufferValueAftercopying = driver.findElement(By.xpath(object_xpath)).getAttribute("value");
			// System.out.println("bufferValueAftercopying"+bufferValueAftercopying);
			// Notes : Need to update the framework - as the value from data sheet is read
			// as 15.0 when we give 15, we can go with below statement
			// or Type casting -convert the data types and compare
			bufferValueAftercopying = bufferValueAftercopying + ".0";
			if (bufferValuebeforecopying.equals(bufferValueAftercopying)) {
				test.log(Status.INFO, "Part Buffer value is updated and is :  " + bufferValueAftercopying);
			} else {
				reportFailure("Part Buffer values are not updated using Copy button");
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Part Buffer values are not updated using Copy button");
		}
	}

	/*
	 * Kalyan Chakravarthy - This method verifies the part number in the records in
	 * part number table under part number column when searched for a specific
	 * partnumber
	 */
	public void verifyPartNumberInSearchPartNumberTable() {
		try {
			test.log(Status.INFO, "Verifying updated record of the Part buffer using copy button");
			String object_xpath = prop.getProperty(ObjectKey);
			String productName = data.get(dataKey);
			System.out.println("productName " + productName);
			String ValueFromTheTableFirstRow = driver.findElement(By.xpath(object_xpath)).getText();
			System.out.println("ValueFromTheTableFirstRow" + ValueFromTheTableFirstRow);

			if (ValueFromTheTableFirstRow.contains(productName)) {
				test.log(Status.INFO, "Part Number is present in the table records  :  " + ValueFromTheTableFirstRow);
			} else {
				reportFailure("Part Number is not present in the table records");
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Error message is not displayed as expected");
		}
	}

	/*
	 * Kalyan Chakravarthy - This method verifies the display of error message on
	 * Invalid part number search Can be used to validate page level error messages
	 */

	public void partBufferMessages() {
		String errorMessagefromthedatasheet = data.get(dataKey);
		System.out.println(errorMessagefromthedatasheet);
		String messagefromSystem = driver.findElement(By.xpath(prop.getProperty("PanaMA.NotifyMessage_xpath")))
				.getText();
		System.out.println(messagefromSystem);
		try {
			test.log(Status.INFO, "Verifying the display of error msg on giving Invalid Inputs");

			if (errorMessagefromthedatasheet.equals(messagefromSystem)) {
				test.log(Status.INFO, "Exception messages Validation Success");
			} else {
				reportFailure("Error message is not displayed as expected");
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Error message is not displayed as expected");
		}
	}

	/**
	 * Accept Delete Item Function on a list
	 *
	 */

	public void deleteAccept() throws InterruptedException {
		try {
		Thread.sleep(1000);
		driver.findElement(By.xpath(prop.getProperty(ObjectKey))).click();
		}
		catch (Exception e) 
		{
				e.printStackTrace();
				reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * Accept Delete Item Function on a list
	 *
	 */

	public void clickAcceptbyname() throws InterruptedException {
		String PN_xpath = data.get(dataKey);
		Thread.sleep(800);
		driver.findElement(By.xpath(prop.getProperty(ObjectKey))).click();
	}

	/**
	 * Verify Saving Status Successful or Unsuccessful
	 */

	public void savingStatusVerify() {
		try {
			String success_stt = "SuccessMessage_Icon";
			String warning_stt = "WarningMessage_Icon";

			String saving_stt = driver.findElement(By.xpath(prop.getProperty(ObjectKey))).getAttribute("class");
			if (saving_stt.equals(success_stt) || saving_stt.equals(warning_stt)) {

				test.log(Status.INFO, "Saving item is sucessful");

			} else {
				reportFailure("Saving item is unsuccessful !!!");
				closeBrowser();
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}

	}

	public void StatusVerify() {
		try {
			String var = data.get(dataKey);
			if (var == "") {
				test.log(Status.INFO, "Saving item is succesfully but there is no pop-up message");
			} else {
				String saving_stt = driver.findElement(By.xpath(prop.getProperty(ObjectKey))).getAttribute("class");
				if (saving_stt.equals(var)) {

					test.log(Status.INFO, "Saving item is same as expect");

				} else {
					reportFailure("Saving item is different with expect !!!");
					closeBrowser();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void failingStatusVerify() {
		try {
			String warning_stt = "WarningMessage_Icon";
			String error_stt = "ErrorMessage_Icon";

			String saving_stt = driver.findElement(By.xpath(prop.getProperty(ObjectKey))).getAttribute("class");
			if (saving_stt.equals(warning_stt) || saving_stt.equals(error_stt)) {

				test.log(Status.INFO, "PopUp Message is corrected");

			} else {
				reportFailure("PopUp Message is not corrected !!!");
				// closeBrowser();
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}
	
	
	public void failingStatusVerify1() {

		try {
			String warning_stt = "warning";
			String error_stt = "error";
			String saving_stt = driver.findElement(By.xpath(prop.getProperty(ObjectKey))).getText();

			if (saving_stt.equals(warning_stt) || saving_stt.equals(error_stt)) {
				test.log(Status.INFO, "PopUp Message is corrected");
			} else {
				reportFailure("PopUp Message is not corrected !!!");
			}
		} catch (Exception e) {
		e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}

	}

	/**
	 * Verify Deleting Status Successful or Unsuccessful
	 */

	public void deletingStatusVerify() {
		try {
			String success_stt = "SuccessMessage_Icon";
			String deleting_stt = driver.findElement(By.xpath(prop.getProperty(ObjectKey))).getAttribute("class");
			if (deleting_stt.equals(success_stt)) {

				test.log(Status.INFO, "Deleting item is sucessful");

			} else {
				reportFailure("Deleting item is unsuccessful !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * Verify Deleting Status Successful or Unsuccessful
	 */
	public void deletingStatusCheck() {
		
		try {
			String success_stt = "Deleted successfull"
					+ "y";
			String deleting_stt = driver.findElement(By.id("displayMessageText")).getText();
			
			if (deleting_stt.equals(success_stt)) {

				test.log(Status.INFO, "Deleting item is sucessful");

			} else {
				reportFailure("Deleting item is unsuccessful !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
		
	}
	
	public void deleteBottomElement() {
		try {
			String countitem = driver.findElement(By.xpath("//*[@id=\"grdValueslabel\"]")).getText();
			int count = Integer.valueOf(countitem.substring(countitem.length() - 2));
			driver.findElement(By.xpath("//*[@id=\"row0grdValues\"]/div[2]")).click();
			Actions action = new Actions(driver);

			for (int i = 1; i < count; i++) {
				action.sendKeys(Keys.ARROW_DOWN).build().perform();
			}
			int step;
			driver.findElement(By.xpath("//*[@id=\"row0grdValues\"]/div[2]")).click();
			try {
				for (step = 1; step <= count; step++) {
					action.sendKeys(Keys.ARROW_DOWN).build().perform();
					String text = driver.findElement(By.xpath("//*[@id=\"row" + (step - 1) + "grdValues\"]/div[2]"))
							.getText();
					if (text.equals("")) {
						break;
					}
				}
				driver.findElement(By.xpath("//*[@id=\"row" + (step - 2) + "grdValues\"]/div[4]")).click();
				test.log(Status.INFO, "Choose the last element and click to delete");
			} catch (Exception e) {
				e.printStackTrace();
				reportFailure("Cannot click to delete the last element");
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * Verify Saving Data Successful or Unsuccessful
	 */

	public void addDataVerify() {
		try {
			test.log(Status.INFO, "Verifying Adding method is successful or not");
			String expectedResult = data.get(dataKey).replace(".", "");
			String actResult = driver.findElement(By.xpath(prop.getProperty(ObjectKey))).getText();

			if (actResult.contains(expectedResult)) {
				test.log(Status.INFO, "Adding item: " + actResult + " is displayed");
			} else {
				reportFailure("Adding item is not successful !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * Verify Deleting Data Successful or Unsuccessful
	 */

	public void deleteDataVerify() {
		try {
			test.log(Status.INFO, "Verifying Deleting method is successful or not");
			String expectedResult = data.get(dataKey);
			String actResult = driver.findElement(By.xpath(prop.getProperty(ObjectKey))).getText();

			if (actResult.contains(expectedResult)) {
				reportFailure("Deleting item is not successful !!!");
			} else {
				test.log(Status.INFO, "Item: " + expectedResult + " is removed");
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * verifyScarpData This Method to verify whether entered input data is scrapped
	 * or not .
	 */
	public void scrapDataVerify() {
		try {
			// WebElement element =
			// driver.findElement(By.xpath(prop.getProperty(ObjectKey)));
			// System.out.println("---text:"+element.getText() + " ---size: " +
			// element.getSize() + "---tag name: " + element.getTagName()+ "---class name: "
			// + element.getAttribute("class"));
			test.log(Status.INFO, "Verifying Scrapped material is successful or not");
			String expectedResult = data.get(dataKey);
			String actResult = driver.findElement(By.xpath(prop.getProperty(ObjectKey))).getText();

			if (actResult.contains(expectedResult)) {
				test.log(Status.INFO, "Scrap material: " + actResult + " successful");
			} else {
				reportFailure("Scrap material is not successful !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * verifyReceiveData
	 */
	public void receiveDataVerify() {
		try {
			// WebElement element =
			// driver.findElement(By.xpath(prop.getProperty(ObjectKey)));
			// System.out.println("---text:"+element.getText() + " ---size: " +
			// element.getSize() + "---tag name: " + element.getTagName()+ "---class name: "
			// + element.getAttribute("class"));
			test.log(Status.INFO, "Verifying Receive material is successful or not");
			String expectedResult = data.get(dataKey);
			String actResult = driver.findElement(By.xpath(prop.getProperty(ObjectKey))).getText();

			if (actResult.contains(expectedResult)) {
				test.log(Status.INFO, "Receive material: " + actResult + " successful");
			} else {
				reportFailure("Receive material is not successful !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * verifyNewPart
	 */
	public void NewPartVerify() {
		try {
			test.log(Status.INFO, "Verifying New Part is successful or not");
			String expectedResult = data.get(dataKey).replace(".", "");
			String actResult = driver.findElement(By.xpath(prop.getProperty(ObjectKey))).getText();

			if (actResult.contains(expectedResult)) {
				test.log(Status.INFO, "Make New Part: " + actResult + " successful");
			} else {
				reportFailure("Make New Part is not successful !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	/**
	 * verifyDataAfterAutoZero This Method to verify whether entered auto Zero
	 * method is successful or not.
	 */
	public void AutoZeroDataVerify() {
		try {
			// WebElement element =
			// driver.findElement(By.xpath(prop.getProperty(ObjectKey)));
			// System.out.println("---text:"+element.getText() + " ---size: " +
			// element.getSize() + "---tag name: " + element.getTagName()+ "---class name: "
			// + element.getAttribute("class"));
			test.log(Status.INFO, "Verifying AutoZero material is successful or not");
			String expectedResult = data.get(dataKey);
			DymamicWait();
			String actResult = driver.findElement(By.xpath(prop.getProperty(ObjectKey))).getText();

			if (actResult.contains(expectedResult)) {
				test.log(Status.INFO, "AutoZero material: " + actResult + " is successful");

			} else {
				reportFailure("AutoZero material is not successful !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void AutoZero() {
		try {
			test.log(Status.INFO, "Doing AutoZero Action");
			WebElement materialID = driver
					.findElement(By.xpath(prop.getProperty("PanaMC.MaterialIDRow1AutoZero_xpath")));
			String MaterialID = "SN" + materialID.getText();

			try {
				test.log(Status.INFO, "Click in " + Description);
				WebDriverWait wait = new WebDriverWait(driver, 10);
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(prop.getProperty(ObjectKey))));
				getObject(ObjectKey).click();
				DymamicWait();
			} catch (Exception e) {
				e.printStackTrace();
				reportFailure("Unable to Click " + Description);
			}

			/*WebElement txtinput = driver.findElement(By.xpath(prop.getProperty("PanaMC.AZInput_xpath")));
			try {
				test.log(Status.INFO,
						"Typing in " + prop.getProperty("PanaMC.AZInput_xpath") + ". Data- " + MaterialID);
				txtinput.clear();
				DymamicWait();
				Thread.sleep(2000);
				txtinput.sendKeys(MaterialID);
				txtinput.sendKeys(Keys.ENTER);
			} catch (Exception e) {
				e.printStackTrace();
				reportFailure("Unable to Type " + prop.getProperty("PanaMC.AZInput_xpath") + ". Data- " + MaterialID);
			}*/
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void DeletePrint() {
		try {
			test.log(Status.INFO, "Doing DeletePrint Action");
			String materialID_old = driver.findElement(By.xpath("//*[@id=\"grdprintmaterial\"]/tbody/tr[1]/td[2]"))
					.getText();

			try {
				test.log(Status.INFO, "Click in " + prop.getProperty(ObjectKey));
				WebDriverWait wait = new WebDriverWait(driver, 10);
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(prop.getProperty(ObjectKey))));
				getObject(ObjectKey).click();
				DymamicWait();
			} catch (Exception e) {
				e.printStackTrace();
				reportFailure("Unable to Click " + prop.getProperty(ObjectKey));
			}
			String materialID_new = driver.findElement(By.xpath("//*[@id=\"grdprintmaterial\"]/tbody/tr[1]/td[2]"))
					.getText();

			if (materialID_new.equals(materialID_old)) {
				reportFailure("Delete Print is not successful !!!");
			} else {
				test.log(Status.INFO, "Delete Print is successful");
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void SelectCanvas() {
		try {
			test.log(Status.INFO, "Select Canvas first element");
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			Actions builder = new Actions(driver);
			int offsetx = Math
					.round(Float.parseFloat(jse.executeScript("return boxingLeftSidebar.XD.width").toString()) / 2);
			int offsety = 15;
			Action drawAction = builder
					.moveToElement(driver.findElement(By.xpath(prop.getProperty(ObjectKey))), offsetx, offsety).click()
					.build();
			drawAction.perform();
			sleepThread(1000);
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void EmptyFieldVerify() {
		try {
			test.log(Status.INFO, "Verifying A Field is empty or not");
			String actResult = driver.findElement(By.xpath(prop.getProperty(ObjectKey))).getText();
			if (actResult.equals("")) {
				test.log(Status.INFO, "The field of: " + ObjectKey + " is empty");
			} else {
				reportFailure(" The field of " + ObjectKey + " not empty");
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void typeAppend() {
		try {
			test.log(Status.INFO, "Typing in " + prop.getProperty(ObjectKey) + ". Data- " + data.get(dataKey));
			String value = data.get(dataKey);
			getObject(ObjectKey).sendKeys(value);
			getObject(ObjectKey).sendKeys(Keys.ENTER);
			sleepThread(1000);
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unable to type " + prop.getProperty(ObjectKey) + ". Data- " + data.get(dataKey));
		}
	}

	public void typeStoredValue() {
		try {
			test.log(Status.INFO, "Typing in " + prop.getProperty(ObjectKey) + ". Data- " + data.get(dataKey));
			String value = sV.getValue();
			getObject(ObjectKey).sendKeys(value);
			sleepThread(1000);
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unable to type " + prop.getProperty(ObjectKey) + ". Data- " + data.get(dataKey));
		}
	}

	public void typeAppendAndEnter() {
		try {
			String value = sV.getValue();
			test.log(Status.INFO, "Typing in " + Description + ": Data- " + value);
			getObject(ObjectKey).sendKeys(value);
			sleepThread(1000);
			getObject(ObjectKey).sendKeys(Keys.ENTER);
			sleepThread(1000);
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void typeAppendValue() {
		try {
			String var = data.get(dataKey);
			int var1 = Integer.valueOf(var);
			String countitem = sV.getValue();
			String[] count = countitem.split("#");
			String[] actual = count[0].split("T");
			int current = Integer.valueOf(actual[1]);
			String expect = "MT" + String.valueOf(current + var1) + "#E";
			try {
				test.log(Status.INFO, "Typing in " + prop.getProperty(ObjectKey) + ". Data- " + expect);
				getObject(ObjectKey).sendKeys(expect);
				getObject(ObjectKey).sendKeys(Keys.ENTER);
				sleepThread(1000);
			} catch (Exception e) {
				e.printStackTrace();
				reportFailure("Unable to type " + prop.getProperty(ObjectKey) + ". Data- " + expect);
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}
	
	
	public void typeAppendValueCarrier() {
		try {
			String var = data.get(dataKey);
			int var1 = Integer.valueOf(var);
			String countitem = sV.getValue();
			String[] count = countitem.split("#");
			String[] actual = count[0].split("R");
			int current = Integer.valueOf(actual[1]);
			String expect = "CR" + String.valueOf(current + var1) + "#R";
			try {
				test.log(Status.INFO, "Typing in " + prop.getProperty(ObjectKey) + ". Data- " + expect);
				getObject(ObjectKey).sendKeys(expect);
				getObject(ObjectKey).sendKeys(Keys.ENTER);
				sleepThread(1500);
			} catch (Exception e) {
				e.printStackTrace();
				reportFailure("Unable to type " + prop.getProperty(ObjectKey) + ". Data- " + expect);
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}
	

	public void selectCarrierTypeCanvas() {
		try {
			test.log(Status.INFO, "Select Carrier Type in Canvas");
			int num = Math.round(Float.parseFloat(data.get(dataKey)));
			Float offsetx, offsetx1, offsetx2, offsetx3, offsetx4;
			Float offsety, offsety1, offsety2, offsety3, offsety4;

			int delta = 5;
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			offsetx1 = Float.parseFloat(jse.executeScript("return boxingFlowDiagram.XD.width").toString()) / 2;
			offsetx2 = Float.parseFloat(jse.executeScript("return boxingLeftSidebar.XD.width").toString()) / 2;
			// offsetx3=Float.parseFloat(jse.executeScript("return
			// boxingLeftSidebar.findNodeForKey(boxingLeftSidebar.model.nodeDataArray[0].key).Tc.width").toString())/2;

			offsetx = offsetx2;
			offsety1 = Float.parseFloat(jse.executeScript("return boxingFlowDiagram.XD.height").toString()) / 2;
			offsety2 = Float.parseFloat(jse.executeScript("return boxingLeftSidebar.XD.height").toString()) / 2;
			offsety3 = Float.parseFloat(jse.executeScript(
					"return boxingLeftSidebar.findNodeForKey(boxingLeftSidebar.model.nodeDataArray[0].key).Tc.height")
					.toString()) / 2;

			offsety = delta + offsety3 + offsety3 * 2 * (num - 1) + 2 * (offsety1 - offsety2);
			int x = Math.round(offsetx);
			int y = Math.round(offsety);

			sleepThread(2000);
			Actions builder = new Actions(driver);
			Action drawAction = builder.moveToElement(driver.findElement(By.xpath(prop.getProperty(ObjectKey))), -x, y)
					.clickAndHold().moveByOffset(20, 20).build();
			drawAction.perform();
			sleepThread(2000);
			drawAction = builder.moveToElement(driver.findElement(By.xpath(prop.getProperty(ObjectKey))),
					Math.round(offsetx1), Math.round(offsety1)).release().build();
			drawAction.perform();
			sleepThread(2000);
			offsetx4 = Float.parseFloat(jse.executeScript(
					"return boxingFlowDiagram.findNodeForKey(boxingFlowDiagram.model.nodeDataArray[0].key).Tc.width")
					.toString()) / 2;
			offsety4 = Float.parseFloat(jse.executeScript(
					"return boxingFlowDiagram.findNodeForKey(boxingFlowDiagram.model.nodeDataArray[0].key).Tc.height")
					.toString()) / 2;
			sleepThread(2000);
			drawAction = builder.moveToElement(driver.findElement(By.xpath(prop.getProperty(ObjectKey))),
					Math.round(offsetx1 + offsetx4), Math.round(offsety1 + offsety4)).click().build();
			drawAction.perform();
			sleepThread(1000);
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void moveMaterialIntoCarrier() {
		try {
			test.log(Status.INFO, "Select Carrier Type in Canvas");
			int num = Math.round(Float.parseFloat(data.get(dataKey)));
			Float offsetx, offsetx1, offsetx2, offsetx3, offsetx4;
			Float offsety, offsety1, offsety2, offsety3, offsety4;

			int delta = 5;
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			offsetx1 = Float.parseFloat(jse.executeScript("return boxingFlowDiagram.XD.width").toString()) / 2;
			offsetx2 = Float.parseFloat(jse.executeScript("return boxingLeftSidebar.XD.width").toString()) / 2;
			// offsetx3=Float.parseFloat(jse.executeScript("return
			// boxingLeftSidebar.findNodeForKey(boxingLeftSidebar.model.nodeDataArray[0].key).Tc.width").toString())/2;
			offsetx4 = Float.parseFloat(jse.executeScript(
					"return boxingFlowDiagram.findNodeForKey(boxingFlowDiagram.model.nodeDataArray[0].key).Tc.width")
					.toString()) / 2;
			offsetx = offsetx2;
			offsety1 = Float.parseFloat(jse.executeScript("return boxingFlowDiagram.XD.height").toString()) / 2;
			offsety2 = Float.parseFloat(jse.executeScript("return boxingLeftSidebar.XD.height").toString()) / 2;
			offsety3 = Float.parseFloat(jse.executeScript(
					"return boxingLeftSidebar.findNodeForKey(boxingLeftSidebar.model.nodeDataArray[0].key).Tc.height")
					.toString()) / 2;
			offsety4 = Float.parseFloat(jse.executeScript(
					"return boxingFlowDiagram.findNodeForKey(boxingFlowDiagram.model.nodeDataArray[0].key).Tc.height")
					.toString()) / 2;
			offsety = delta + offsety3 + offsety3 * 2 * (num - 1) + 2 * (offsety1 - offsety2);
			int x = Math.round(offsetx);
			int y = Math.round(offsety);

			sleepThread(2000);
			Actions builder = new Actions(driver);
			Action drawAction = builder.moveToElement(driver.findElement(By.xpath(prop.getProperty(ObjectKey))), -x, y)
					.clickAndHold().moveByOffset(20, 20).build();
			drawAction.perform();
			drawAction = builder.moveToElement(driver.findElement(By.xpath(prop.getProperty(ObjectKey))),
					Math.round(offsetx1 + offsetx4), Math.round(offsety1 + offsety4)).release().build();
			drawAction.perform();
			sleepThread(2000);
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void validateAvailabiltyCarrierTypeCanvas() {
		try {
			test.log(Status.INFO, "Validate the availability Carrier Type in Canvas");
			Float num;
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			// offsetx3=Float.parseFloat(jse.executeScript("return
			// boxingLeftSidebar.findNodeForKey(boxingLeftSidebar.model.nodeDataArray[0].key).Tc.width").toString())/2;
			sleepThread(2000);
			num = Float.parseFloat(jse.executeScript("return boxingLeftSidebar.model.nodeDataArray.length").toString());
			if (num > 0) {
				test.log(Status.INFO, "The Carrier Type is available");
			} else {
				reportFailure("The Carrier Type is not available");
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unable to find " + prop.getProperty(ObjectKey));
		}
	}

	public void validateAvailabiltyHierarchyCanvas() {
		try {
			test.log(Status.INFO, "Validate the availability Hierarchy in Canvas");
			Float num;
			String value;
			String var = data.get(dataKey);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			// offsetx3=Float.parseFloat(jse.executeScript("return
			// boxingLeftSidebar.findNodeForKey(boxingLeftSidebar.model.nodeDataArray[0].key).Tc.width").toString())/2;
			sleepThread(2000);
			num = Float.parseFloat(
					jse.executeScript("return carrierHierarchyFlowDiagram.model.nodeDataArray.length").toString());
			if (num > 0) {
				test.log(Status.INFO, "The Hierarchy is available");
				value = jse.executeScript("return carrierHierarchyFlowDiagram.model.nodeDataArray[0].name").toString();
				if (value.contains(var)) {
					test.log(Status.INFO, "The Hierarchy contains Carrier Number");
				}
			} else {
				reportFailure("The Hierarchy is not available");
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unable to find " + prop.getProperty(ObjectKey));
		}
	}

	public void validateSearchCarrierTypeCanvas() {
		try {
			test.log(Status.INFO, "Validate the availability Carrier Type in Canvas");
			Float num;
			String name;
			String expectedvalue = data.get(dataKey);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			// offsetx3=Float.parseFloat(jse.executeScript("return
			// boxingLeftSidebar.findNodeForKey(boxingLeftSidebar.model.nodeDataArray[0].key).Tc.width").toString())/2;
			sleepThread(2000);
			num = Float.parseFloat(jse.executeScript("return boxingLeftSidebar.model.nodeDataArray.length").toString());
			if (num > 0) {
				test.log(Status.INFO, "The Carrier Type is available");
				for (int i = 0; i < num; i++) {
					name = jse.executeScript("return boxingLeftSidebar.model.nodeDataArray[" + i + "].name").toString();
					if (name.contains(expectedvalue)) {
						test.log(Status.INFO, "The Carrier Type in the list is correct");
					} else {
						reportFailure("The Carrier Type in the list is wrong");
					}
				}
			} else {
				reportFailure("The Carrier Type is not available");
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unable to find " + prop.getProperty(ObjectKey));
		}
	}

	public void countCarrierTypeCanvas() {
		try {
			test.log(Status.INFO, "Validate the availability Carrier Type in Canvas");
			Float num;
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			// offsetx3=Float.parseFloat(jse.executeScript("return
			// boxingLeftSidebar.findNodeForKey(boxingLeftSidebar.model.nodeDataArray[0].key).Tc.width").toString())/2;
			sleepThread(2000);
			num = Float.parseFloat(jse.executeScript("return boxingLeftSidebar.model.nodeDataArray.length").toString());
			if (num > 0) {
				test.log(Status.INFO, "The Carrier Type is available");
				test.log(Status.INFO, "There are: " + num + " carrier types in the list.");
			} else {
				reportFailure("The Carrier Type is not available");
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unable to find " + prop.getProperty(ObjectKey));
		}
	}

	public void countBoxingRuntimeCanvas() {
		try {
			test.log(Status.INFO, "Validate the availability of Carrier in Canvas");
			String expect = data.get(dataKey);
			String num;
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			// offsetx3=Float.parseFloat(jse.executeScript("return
			// boxingLeftSidebar.findNodeForKey(boxingLeftSidebar.model.nodeDataArray[0].key).Tc.width").toString())/2;
			sleepThread(2000);
			num = jse.executeScript("return boxingRuntimeFlowDiagram.model.nodeDataArray.length").toString();
			if (expect.equals(num)) {
				test.log(Status.INFO, "The Carrier is available!");
				test.log(Status.INFO, "There are: " + num + " carrier in the canvas.");
			} else {
				reportFailure("The Carrier is not available!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unable to find " + prop.getProperty(ObjectKey));
		}
	}

	public void countBoxingRuntimeCapacity() {
		try {
			test.log(Status.INFO, "Validate the capacity of Carrier in Canvas");
			String expect = data.get(dataKey);
			String num;
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			// offsetx3=Float.parseFloat(jse.executeScript("return
			// boxingLeftSidebar.findNodeForKey(boxingLeftSidebar.model.nodeDataArray[0].key).Tc.width").toString())/2;
			sleepThread(2000);
			num = jse.executeScript("return boxingRuntimeFlowDiagram.model.nodeDataArray[0].capacity").toString();
			if (expect.equals(num)) {
				test.log(Status.INFO, "The Carrier capacity is expected same with data input!");
				test.log(Status.INFO, "The capacity is: " + num + " !");
			} else {
				reportFailure("The Carrier capacity is not same!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unable to find " + prop.getProperty(ObjectKey));
		}
	}

	public void validateCanvasBoxingNotNull() {
		try {
			test.log(Status.INFO, "Validate the availability of Carrier in Canvas");
			int num;
			String[] capacity = new String[3];
			String[] name = new String[3];
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			// offsetx3=Float.parseFloat(jse.executeScript("return
			// boxingLeftSidebar.findNodeForKey(boxingLeftSidebar.model.nodeDataArray[0].key).Tc.width").toString())/2;
			sleepThread(2000);
			num = Math.round(Float.parseFloat(
					jse.executeScript("return boxingRuntimeFlowDiagram.model.nodeDataArray.length").toString()));
			for (int i = 0; i < num; i++) {
				capacity[i] = jse.executeScript("return boxingRuntimeFlowDiagram.model.nodeDataArray[" + i + "].name")
						.toString();
				name[i] = jse.executeScript("return boxingRuntimeFlowDiagram.model.nodeDataArray[" + i + "].capacity")
						.toString();
				if (capacity[i] != null && name[i] != null) {
				} else {
					reportFailure("Display of name or capacity is null !!! Element :" + (i + 1));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unable to find " + prop.getProperty(ObjectKey));
		}
	}

	public void successStatusVerify() {
		try {
			String success_stt = "SuccessMessage_Icon";

			String saving_stt = driver.findElement(By.xpath(prop.getProperty(ObjectKey))).getAttribute("class");
			if (saving_stt.equals(success_stt)) {

				test.log(Status.INFO, "Saving item is sucessful");

			} else {
				reportFailure("Saving item is unsuccessful !!!");
				closeBrowser();
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}

	}

	
	public void successStatusMove() {
		try {
			String success_stt = "Carrier moved successfully.";

			//String saving_stt = driver.findElement(By.xpath(prop.getProperty(ObjectKey))).getAttribute("class");
			
			String saving_stt = driver.findElement(By.xpath(prop.getProperty(ObjectKey))).getText();
			
			
			if (saving_stt.equals(success_stt)) {

				test.log(Status.INFO, "Saving item is sucessful");

			} else {
				reportFailure("Saving item is unsuccessful !!!");
				closeBrowser();
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}

	}
	public void verifyRowCount() {
		try {
			String expect = data.get(dataKey);
			WebElement element = driver.findElement(By.xpath(prop.getProperty(ObjectKey)));
			test.log(Status.INFO, "Verify the number of row count on screen ");
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			String actual = jse.executeScript("return arguments[0].rows.length", element).toString();
			DymamicWait();
			if (expect.contains(actual)) {
				test.log(Status.INFO, "The number of row count is same as option choose ! " + expect);
			} else {
				reportFailure("Display wrong the number of row counts. ");
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}
	
	public void verifyRowCountCarrier() {
		try {
			WebElement element = driver.findElement(By.xpath(prop.getProperty(ObjectKey)));
			test.log(Status.INFO, "Verify the number of row count on screen: " + element);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			String actual = jse.executeScript("return arguments[0].rows.length", element).toString();
			DymamicWait();
			if (actual != null) {
				test.log(Status.INFO, "The number of row count is same as option choose ! ");
			} else {
				reportFailure("Display wrong the number of row counts. ");
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void verifyCountLabellats2digits() {
		try {
			String countitem = driver.findElement(By.xpath(prop.getProperty(ObjectKey))).getText();
			String count = countitem.substring(countitem.length() - 2);
			String expect = data.get(dataKey);
			test.log(Status.INFO, "There are " + count + " elements in the list");
			DymamicWait();

			if (expect.contains(count)) {
				test.log(Status.INFO, "The number of row count is same as display ! " + count);
			} else {
				reportFailure("Display wrong the number of row counts." + count + "." + expect);
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void verifyCountLabellats1digit() {
		try {
			String countitem = driver.findElement(By.xpath(prop.getProperty(ObjectKey))).getText();
			String count = countitem.substring(countitem.length() - 1);
			String expect = data.get(dataKey);
			test.log(Status.INFO, "There are " + count + " elements in the list");
			DymamicWait();

			if (expect.contains(count)) {
				test.log(Status.INFO, "The number of row count is same as display ! " + count);
			} else {
				reportFailure("Display wrong the number of row counts." + count + "." + expect);
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void verifyCountLabel() {
		try {
			String countitem = driver.findElement(By.xpath(prop.getProperty(ObjectKey))).getText();
			String[] count = countitem.split("of");
			String[] actual = count[0].split("-");
			test.log(Status.INFO, "There are " + actual[1] + " elements in the list");
			String expect = data.get(dataKey);
			String check = actual[1].replace(" ", "");
			DymamicWait();

			if (expect.contains(check)) {
				test.log(Status.INFO, "The number of row count is same as display ! " + check);
			} else {
				reportFailure("Display wrong the number of row counts." + check + "." + expect);
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void moveToBelowElement() {
		try {
			String countitem = driver.findElement(By.xpath(prop.getProperty(ObjectKey))).getText();
			String[] count = countitem.split("#");
			String[] actual = count[0].split("T");
			int current = Integer.valueOf(actual[1]);
			String expect = "mt" + String.valueOf(current - 1);
			DymamicWait();
			Thread.sleep(1000);
			type_para("PanaMC.CarrierFunctionCarrierCartMoveInput_xpath", expect);
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void moveToAboveElement() {
		try {
			String countitem = driver.findElement(By.xpath(prop.getProperty(ObjectKey))).getText();
			String[] count = countitem.split("#");
			String[] actual = count[0].split("T");
			int current = Integer.valueOf(actual[1]);
			String expect = "mt" + String.valueOf(current + 1);
			DymamicWait();
			Thread.sleep(1000);
			type_para("PanaMC.CarrierFunctionCarrierCartMoveInput_xpath", expect);
			Thread.sleep(1000);
			sV.setValue(expect);
		} catch (Exception e) {
			e.printStackTrace();

			reportFailure("Unexpected exception is thrown: " + e);
		}
	}
	
	
public void moveToAboveElementCarrier() {
		
		try {
			String countitem = driver.findElement(By.xpath(prop.getProperty(ObjectKey))).getText();
			String[] count = countitem.split("#");
			String[] actual = count[0].split("R");
			int current = Integer.valueOf(actual[1]);
			String expect = "CR" + String.valueOf(current + 1);
			DymamicWait();
			Thread.sleep(1000);
			type_para("PanaMC.CarrierFunctionCarrierCartMoveInput_xpath", expect);
			Thread.sleep(1000);
			sV.setValue(expect);
		} catch (Exception e) {
			e.printStackTrace();

			reportFailure("Unexpected exception is thrown: " + e);
		}
	}
	
	
	

	public void moveToCarrier() {
		try {
			String value = sV.getValue();
			DymamicWait();
			Thread.sleep(1000);
			driver.findElement(By.xpath(prop.getProperty(ObjectKey))).sendKeys(value);
			Thread.sleep(1000);
			driver.findElement(By.xpath(prop.getProperty(ObjectKey))).sendKeys(Keys.SPACE);
			Thread.sleep(1000);
			driver.findElement(By.xpath(prop.getProperty(ObjectKey))).sendKeys(Keys.BACK_SPACE);
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void scanCarrierNumber() {
		try {
			String prefix = data.get(dataKey);
			String value = driver.findElement(By.xpath(prop.getProperty(ObjectKey))).getText();
			// System.out.println("Typing"+prop.getProperty(ObjectKey)+".Data-
			// "+data.get(dataKey));
			test.log(Status.INFO, "Typing in " + prop.getProperty("Pana.Home_xpath") + ". Data- " + data.get(dataKey));
			// driver.findElement(By.xpath(prop.getProperty(ObjectKey))).sendKeys(data.get(dataKey));
			// driver.findElement(By.xpath(prop.getProperty(ObjectKey))).clear();
			driver.findElement(By.xpath(prop.getProperty("Pana.Home_xpath"))).clear();
			driver.findElement(By.xpath(prop.getProperty("Pana.Home_xpath"))).sendKeys(prefix);
			sleepThread(1000);
			driver.findElement(By.xpath(prop.getProperty("Pana.Home_xpath"))).sendKeys(value);
			sleepThread(1000);
			driver.findElement(By.xpath(prop.getProperty("Pana.Home_xpath"))).sendKeys(Keys.ENTER);
			sleepThread(1000);
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void storeValuebyValue() {
		try {
			String value = driver.findElement(By.xpath(prop.getProperty(ObjectKey))).getAttribute("value");
			sV.setValue(value);
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void storeValuebyText() {
		try {
			String value = driver.findElement(By.xpath(prop.getProperty(ObjectKey))).getText();
			sV.setValue(value);
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}
	

	public void clickCanvasBoxingRuntime() {
		try {
			test.log(Status.INFO, "Select Boxing Carrier in Canvas");

			Float offsetx, offsetx1, offsetx2, offsetx3, offsetx4;
			Float offsety, offsety1, offsety2, offsety3, offsety4;

			JavascriptExecutor jse = (JavascriptExecutor) driver;
			offsetx1 = Float.parseFloat(jse.executeScript("return boxingRuntimeFlowDiagram.XD.width").toString()) / 2;
			// offsetx2=Float.parseFloat(jse.executeScript("return
			// boxingLeftSidebar.XD.width").toString())/2;
			// offsetx3=Float.parseFloat(jse.executeScript("return
			// boxingLeftSidebar.findNodeForKey(boxingLeftSidebar.model.nodeDataArray[0].key).Tc.width").toString())/2;

			offsetx = offsetx1;
			offsety1 = Float.parseFloat(jse.executeScript("return boxingRuntimeFlowDiagram.XD.height").toString()) / 2;
			// offsety2=Float.parseFloat(jse.executeScript("return
			// boxingLeftSidebar.XD.height").toString())/2;
			// offsety3=Float.parseFloat(jse.executeScript("return
			// boxingLeftSidebar.findNodeForKey(boxingLeftSidebar.model.nodeDataArray[0].key).Tc.height").toString())/2;

			offsety = offsety1;
			int x = Math.round(offsetx);
			int y = Math.round(offsety);

			sleepThread(2000);
			Actions builder = new Actions(driver);
			Action drawAction = builder.moveToElement(driver.findElement(By.xpath(prop.getProperty(ObjectKey))), x, y)
					.click().build();
			drawAction.perform();
			sleepThread(1000);
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}

	}
//public void SelectCanvasbyName()
//		
//		{		
//			test.log(Status.INFO, "Select Canvas by Name");
//			String Objname =  data.get(dataKey);	
//			JavascriptExecutor jse = (JavascriptExecutor) driver;
//			int length =Math.round(Float.parseFloat(jse.executeScript("return boxingLeftSidebar.model.nodeDataArray.length").toString())); 
//			int k=0;
//			try {				
//				for(int i=1;i<=length;i++)
//				{
//					String name = jse.executeScript("return boxingLeftSidebar.model.nodeDataArray["+(i-1)+"].name").toString();
//					if (Objname.equals(name))
//					{
//						k=i;
//						break;
//					}
//					
//				}
//				if (k==0) {reportFailure("There is no object with name is: "+Objname);}
//			} catch (Exception e) {
//				e.printStackTrace();				
//				reportFailure("There is no object in canvas");
//			} finally {
//				jse.executeScript("boxingLeftSidebar.select(boxingLeftSidebar.findNodeForKey(boxingLeftSidebar.model.nodeDataArray["+(k-1)+"].key))");	
//			}			
//			
//		
//	}

	public void KeyBSpace() {
		try {
			driver.findElement(By.xpath(prop.getProperty(ObjectKey))).sendKeys(Keys.BACK_SPACE);
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void typeWithCSS() throws InterruptedException {
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

		test.log(Status.INFO, "Typing in type1");
//		driver.findElement(By.xpath("//*[@id='row00dvsearchgrid']")).sendKeys("SuBox");
		WebElement ele = driver.findElement(By.xpath(prop.getProperty(ObjectKey)));
		Actions act = new Actions(driver);
		act.moveToElement(ele).doubleClick(ele).sendKeys(data.get(dataKey)).build().perform();

		Thread.sleep(5000);
		act.moveToElement(ele).doubleClick(ele).sendKeys(Keys.DOWN).build().perform();
		act.moveToElement(ele).doubleClick(ele).sendKeys(Keys.ENTER).build().perform();

	}

	public void deletePrint1() {
		try {
			driver.findElement(By.xpath(prop.getProperty(ObjectKey))).click();
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void verifyTextContain1() {
		try {
			test.log(Status.INFO, "verifyTextContain");
			DymamicWait();
			String expect = data.get(dataKey);
			String actual = driver.findElement(By.xpath(prop.getProperty(ObjectKey))).getText();
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

	public int ValidatetableRowCount() {
		int rowCount = 0;

		try {
			List<WebElement> rows = driver.findElements(By.xpath(prop.getProperty(ObjectKey)));
			rowCount = rows.size();
			test.log(Status.INFO, "No. of rows  " + rowCount);
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}

		return rowCount;
	}

	public void ColumnCheckbox() {
		try {
			test.debug("Verifying whether the checkbox is selected?");
			String expec = data.get(dataKey);
			System.out.println(expec);
			String checked = driver.findElement(By.xpath("//*[@id=\"select2-Hide-Show\"]/span/div/ul/li[2]/a/label"))
					.getAttribute("value");
			System.out.println(checked);
			if (expec.equals(checked)) {
				test.debug("Check Box Already Checked" + checked);
				driver.findElement(By.xpath("//*[@id=\"select2-Hide-Show\"]/span/div/ul/li[2]/a/label"))
						.sendKeys(Keys.SPACE);
				driver.findElement(By.xpath("//*[@id=\"select2-Hide-Show\"]/span/div/ul/li[2]/a/label"))
						.sendKeys(Keys.SPACE);
				driver.findElement(By.xpath("//*[@id=\"btnslctColumnSelector\"]")).click();

			} else {
				test.debug("Check Box Not Checked" + checked);
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void VerifyTextFieldValueStarts() {
		try {
			DymamicWait();
			test.log(Status.INFO, "Verifying Button Text");
			String actual = driver.findElement(By.xpath(prop.getProperty(ObjectKey))).getText();
			String actualResult = data.get(dataKey);
			if (actual.startsWith(actualResult))
				test.log(Status.INFO, "Button Text As Expected ..." + actualResult);
			else {
				reportFailure("Button Text is not as Expected " + actualResult);
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void verifyQuantity() {
		try {
			DymamicWait();
			test.log(Status.INFO, "Verifying Button Text");
			String actual = driver.findElement(By.xpath(prop.getProperty(ObjectKey))).getText();
			String actualResult = data.get(dataKey);
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void ScanMaterial() {
		try {
			String MaterialId = driver.findElement(By.xpath(prop.getProperty(ObjectKey))).getText();
			driver.findElement(By.xpath("//*[@id='txtHomeScan']")).sendKeys("SN" + MaterialId);
			driver.findElement(By.xpath("//*[@id=\'txtScanIdInventory_MaterialIdentifier\']")).sendKeys(MaterialId);
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void ScanPart() {
		try {
			String PartNo = driver.findElement(By.xpath(prop.getProperty(ObjectKey))).getText();
			driver.findElement(By.xpath("//*[@id='txtHomeScan']")).sendKeys("P" + PartNo);
			driver.findElement(By.xpath("//*[@id=\'txtScanIdInventory_PartNumber\']")).sendKeys(PartNo);
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void VerifyingAttributeDelete() {
		try {
			test.log(Status.INFO, "Verifying whether Attribute Item Deleted Or Not?");
			String DelItem = data.get(dataKey);
			//test.log(Status.INFO, "Choosed Item To Delete--->" + DelItem);
			ArrayList<String> simpleTable = new ArrayList<>();
			// WebElement table_element =
			// driver.findElement(By.id("jqxScrollThumbverticalScrollBardvsearchgrid"));
			List<WebElement> rows = driver.findElements(By.xpath("//DIV[@class='jqx-grid-cell-left-align']"));
			System.out.println(rows.size());
			for (int row = 0; row < rows.size(); row++) {
				String we = driver.findElement(By.xpath("//*[@id='row" + row + "dvsearchgrid']/div[2]/div")).getText();
				try {
					simpleTable.add(we);
				} catch (Exception e) {
				}
			}
			DymamicWait();
			System.out.println(simpleTable);

			if (simpleTable.contains(DelItem)) {
				reportFailure("Not Deleted ---->" + simpleTable);
			} else {
				test.log(Status.INFO, "Choosen Item Got Deleted--->" + simpleTable);
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void validatingSampleFieldWithSuffixField() {
		try {
			test.log(Status.INFO, "Validating Sample Field With Suffix and Counter");

			// String expectedResult = data.get(dataKey);
			String expectedResult = driver.findElement(By.xpath("//*[@id='txtCounter']")).getAttribute("value");
			String expectedResult1 = driver.findElement(By.xpath("//*[@id='txtSuffix']")).getAttribute("value");
			if (expectedResult.equals(expectedResult1)) {
				test.log(Status.INFO, "Verified");

			} else {
				test.log(Status.INFO, "Not Verified");

			}

			takeScreenShot();
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void validatingCounterConfig() {
		try {
			test.log(Status.INFO, "Validating Counter value by default its start value or not ");
			String s = driver.findElement(By.xpath("//*[@id='txtSuffix']")).getAttribute("value");
			String s2 = driver.findElement(By.xpath("//*[@id='txtSeed']")).getAttribute("value");

			String except = driver.findElement(By.xpath("//*[@id='txtCounter']")).getAttribute("value");
			String result = except;
			test.log(Status.INFO, result);
			System.out.println(result);
			test.log(Status.INFO, "String result starts with:" + s2 + ":--" + result.startsWith(s2));
			test.log(Status.INFO, "String result starts with : " + s + ":--" + result.startsWith(s));
			System.out.println("String result starts with:" + s2 + result.startsWith(s2));
			System.out.println("String result starts with : " + s + result.startsWith(s));
			String actualResult = "";
			if (result.equals(except))
				actualResult = "True";
			else
				actualResult = "False";
			if (!result.equals(except))
				reportFailure("Got Sample Feild result as " + actualResult);
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void ValidateCounterTxtboxCountSize() {
		try {
			test.log(Status.INFO, "Validating counter textbox Input Length");
			String actualResult = "";
			String result = driver.findElement(By.xpath(prop.getProperty(ObjectKey))).getAttribute("value");
			int len = result.length();
			// getAttribute("maxlength")
			test.log(Status.INFO, "Input lenght in Counter TxtBox is.." + len);

			if (len <= 17)
				actualResult = "Not Exceed";
			else
				actualResult = "Exceed the Max Length";
			if (len > 17) {
				System.out.println(actualResult);
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	//Seema New Methods - Verifying the Delete Message Popup
		public void deletingStatus() {

			WebElement deleteMessage = driver.findElement(By.xpath(prop.getProperty(ObjectKey)));
			if (deleteMessage != null) {
				test.log(Status.INFO, "Deleting item is sucessful");

			} else {
				reportFailure("Deleting item is unsuccessful !!!");
			}
		}

		
		public void savingStatusVerify1() {
			try {
				String success_stt = "check_circle";
				String warning_stt = "warning";
				String saving_stt = driver.findElement(By.xpath(prop.getProperty(ObjectKey))).getText();
				if (saving_stt.equals(success_stt) || saving_stt.equals(warning_stt)) {
					test.log(Status.INFO, "Saving item is sucessful");
				} else {
					reportFailure("Saving item is unsuccessful !!!");
					closeBrowser();
				}
			} catch (Exception e) {
				e.printStackTrace();
				reportFailure("Unexpected exception is thrown: " + e);
			}
		}
		
		public void typeAppend2() {
			try {
				String value = sV.getValue();
				test.log(Status.INFO, "Typing in " + Description + ": Data- " + value);
				getObject(ObjectKey).clear();
				DymamicWait();
				getObject(ObjectKey).sendKeys(value);
				sleepThread(2000);
				getObject(ObjectKey).sendKeys(Keys.BACK_SPACE);
				sleepThread(2000);
				getObject(ObjectKey).sendKeys(Keys.BACK_SPACE);
				sleepThread(1000);
			} catch (Exception e) {
				e.printStackTrace();
				reportFailure("Unexpected exception is thrown: " + e);
			}
		}
		
	public void validateCounterReset() {
		try {
			test.log(Status.INFO, "Validating counter Reset the Value After Exceeding The Limit");
			String expectedResult = data.get(dataKey);
			String result = driver.findElement(By.xpath(prop.getProperty(ObjectKey))).getAttribute("value");
			String actualResult = "";
			test.log(Status.INFO, "Value of counter field is.. " + result);
			if (result.equals(expectedResult))
				actualResult = "";
			else
				actualResult = "Not Reset";
			if (!result.equals(expectedResult))
				reportFailure("Got Result Is Not Reset " + result);
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}
}

class storeValue {
	String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
	
	
}

////Get all options
////List<WebElement> dd = select.getOptions(); 
//List<WebElement> options = driver.findElements(By.xpath("//DIV[@id='listitem0innerListBoxddlAttributes']/span"));
////Get the length
////System.out.println(options.size());
//// Loop to print one by one
//for (int j = 0; j < options.size(); j++) {
//	test.log(Status.INFO,options.get(j).getAttribute("value"));
//	//System.out.println(options.get(j).getText());
//}	
