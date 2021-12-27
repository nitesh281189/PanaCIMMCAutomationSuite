package com.PanaAutomation.Keywords;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import com.aventstack.extentreports.Status;

public class PreSetup extends GenericKeywords {

	public void changeSystemErrorMessageType() {
		try {
			String newType = data.get(dataKey);
			test.log(Status.INFO, "changeSystemErrorMessageType(): " + newType);

			String txtUserInterface = "User Interface";
			String txtWebErrorMessageType = "Web: Error Message Type";

			click_para("PanaMC.MCMCConfigButton_xpath");
			click_para("PanaMC.MCSystemConfigurationOption_xpath");
			click_para("PanaMC.MCApplicationSettingsCategoryList_xpath");
			typeAndEnter_para("PanaMC.MCApplicationSettingsCategoryTextBox_xpath", txtUserInterface);
			click_para("PanaMC.MCApplicationSettingsApplicationSettingList_xpath");
			typeAndEnter_para("PanaMC.MCApplicationSettingsApplicationSettingTextBox_xpath", txtWebErrorMessageType);

			// verify current value of error type is expected type or not
			String currentType = driver
					.findElement(
							By.xpath(prop.getProperty("PanaMC.MCApplicationSettingsApplicationSettingValueList_xpath")))
					.getText();
			if (!currentType.equalsIgnoreCase(newType)) {
				// current type is not expected type, change to expected type
				test.log(Status.INFO, "Error message type is NOT Popup, proceed to change");
				click_para("PanaMC.MCApplicationSettingsApplicationSettingValueList_xpath");
				typeAndEnter_para("PanaMC.MCApplicationSettingsApplicationSettingValueTextBox2_xpath", newType);
				click_para("PanaMC.MCApplicationSettingsButtonSave_xpath");
				// wait 3 sec
				sleepThread3Sec();
			} else {
				// current type is already expected type, no job here
				test.log(Status.INFO, "Error message type is already: " + newType);
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void changeSystemErrorMessageTypeToPopup() {
		try {
			test.log(Status.INFO, "changeSystemErrorMessageTypeToPopup()");

			String txtUserInterface = "User Interface";
			String txtWebErrorMessageType = "Web: Error Message Type";
			String txtPopup = "Popup";

			click_para("PanaMC.MCMCConfigButton_xpath");
			click_para("PanaMC.MCSystemConfigurationOption_xpath");
			click_para("PanaMC.MCApplicationSettingsCategoryList_xpath");
			typeAndEnter_para("PanaMC.MCApplicationSettingsCategoryTextBox_xpath", txtUserInterface);
			click_para("PanaMC.MCApplicationSettingsApplicationSettingList_xpath");
			typeAndEnter_para("PanaMC.MCApplicationSettingsApplicationSettingTextBox_xpath", txtWebErrorMessageType);

			// verify current value of error type is Popup or not
			String currentType = driver
					.findElement(
							By.xpath(prop.getProperty("PanaMC.MCApplicationSettingsApplicationSettingValueList_xpath")))
					.getText();
			if (!currentType.equalsIgnoreCase(txtPopup)) {
				// current type is not Popup, change to Popup
				test.log(Status.INFO, "Error message type is NOT Popup, proceed to change");
				click_para("PanaMC.MCApplicationSettingsApplicationSettingValueList_xpath");
				typeAndEnter_para("PanaMC.MCApplicationSettingsApplicationSettingValueTextBox2_xpath", txtPopup);
				click_para("PanaMC.MCApplicationSettingsButtonSave_xpath");
				// wait 3 sec
				sleepThread3Sec();
			} else {
				// current type is already Popup, no job here
				test.log(Status.INFO, "Error message type is already Popup");
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void createNewDefaultLocations() {
		try {
			test.log(Status.INFO, "createNewDefaultLocations()");

			String locationAOIQ = "Automated Optical Inspection in Quality";
			String locationAI = "Axial Insertion";

			createNewLocationFromDashBoard(locationAOIQ, false);
			DymamicWait();
			createNewLocationFromDashBoard(locationAI, false);
			DymamicWait();
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void preSetup_MCID1092() {
		try {
			test.log(Status.INFO, "preSetup_MCID1092");
			String currentTime = new SimpleDateFormat("yyyymmddhhmmss").format(Calendar.getInstance().getTime());

			// create new part number
			String partNumber = "MCID1092PartNumber " + currentTime;
			String partDesc = "Part Number for MCID1092";
			String partQuantity1 = "50";

			createNewMCPartNumberFromDashBoard(partNumber, partDesc, 1);
			DynamicWait();
			receiveMCPartNumberFromDashBoard(partNumber, null, partQuantity1, 0, false);
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void preSetup_MCID1096() {
		try {
			test.log(Status.INFO, "preSetup_MCID1096");

			// create new part number
			String currentTime = new SimpleDateFormat("yyyymmddhhmmss").format(Calendar.getInstance().getTime());
			String partNumber = "MCID1096PartNumber " + currentTime;
			String partDesc = "Part Number for MCID1096";
			String partQuantity1 = "50";
			String partQuantity2 = "60";

			createNewMCPartNumberFromDashBoard(partNumber, partDesc, 1);
			DynamicWait();
			receiveMCPartNumberFromDashBoard(partNumber, null, partQuantity1, 0, false);

			DymamicWait();
			receiveMCPartNumberFromDashBoard(partNumber, null, partQuantity2, 0, false);

			DymamicWait();
			spliceMCPartNumber1WithPartNumber2FromDashboard();

			DymamicWait();
			receiveMCPartNumberFromDashBoard(partNumber, null, partQuantity2, 2, false);
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void preSetup_MCID1099() {
		try {
			test.log(Status.INFO, "preSetup_MCID1099");

			// create new part number
			String currentTime = new SimpleDateFormat("yyyymmddhhmmss").format(Calendar.getInstance().getTime());
			String partNumber = "MCID1099PartNumber " + currentTime;
			String partDesc = "Part Number for MCID1099";
			String partQuantity1 = "50";
			String partQuantity2 = "60";

			createNewMCPartNumberFromDashBoard(partNumber, partDesc, 1);
			DynamicWait();
			receiveMCPartNumberFromDashBoard(partNumber, null, partQuantity1, 0, false);

			DymamicWait();
			receiveMCPartNumberFromDashBoard(partNumber, null, partQuantity2, 0, false);

			DymamicWait();
			spliceMCPartNumber1WithPartNumber2FromDashboard();
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void preSetup_MCID1100() {
		try {
			test.log(Status.INFO, "preSetup_MCID1100");

			// create new part number
			String currentTime = new SimpleDateFormat("yyyymmddhhmmss").format(Calendar.getInstance().getTime());
			String partNumber = "MCID1100PartNumber " + currentTime;
			String partDesc = "Part Number for MCID1100";
			String partQuantity1 = "50";
			String partQuantity2 = "60";

			createNewMCPartNumberFromDashBoard(partNumber, partDesc, 1);
			DynamicWait();
			receiveMCPartNumberFromDashBoard(partNumber, null, partQuantity1, 0, false);

			DymamicWait();
			receiveMCPartNumberFromDashBoard(partNumber, null, partQuantity2, 0, false);

			DymamicWait();
			spliceMCPartNumber1WithPartNumber2FromDashboard();
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void preSetup_MCID1191() {
		try {
			test.log(Status.INFO, "preSetup_MCID1191");

			// create new part number
			String currentTime = new SimpleDateFormat("yyyymmddhhmmss").format(Calendar.getInstance().getTime());
			String partNumber = "MCID1191PartNumber " + currentTime;
			String partDesc = "Part Number for MCID1191";
			String partQuantity1 = "50";
			String partQuantity2 = "60";

			createNewMCPartNumberFromDashBoard(partNumber, partDesc, 1);
			DynamicWait();
			receiveMCPartNumberFromDashBoard(partNumber, null, partQuantity1, 0, false);

			DymamicWait();
			receiveMCPartNumberFromDashBoard(partNumber, null, partQuantity2, 0, false);

			DymamicWait();
			spliceMCPartNumber1WithPartNumber2FromDashboard();
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void preSetup_MCID1192() {
		try {
			test.log(Status.INFO, "preSetup_MCID1192");
			String currentTime = new SimpleDateFormat("yyyymmddhhmmss").format(Calendar.getInstance().getTime());

			// create new part number 1
			String partNumber1 = "MCID1192PartNumber1 " + currentTime;
			String partDesc1 = "Part Number 1 for MCID1192";
			String partQuantity1 = "50";
			createNewMCPartNumberFromDashBoard(partNumber1, partDesc1, 1);
			DynamicWait();
			receiveMCPartNumberFromDashBoard(partNumber1, null, partQuantity1, 0, false);

			// create new part number 2
			String partNumber2 = "MCID1192PartNumber2 " + currentTime;
			String partDesc2 = "Part Number 2 for MCID1192";
			String partQuantity2 = "60";
			createNewMCPartNumber2FromDashBoard(partNumber2, partDesc2, 1);
			DymamicWait();
			receiveMCPartNumberFromDashBoard(partNumber2, null, partQuantity2, 0, false);

			DymamicWait();
			splice2DiffrentMCPartNumbersFromDashboard();
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void createNewMCPartNumber2FromDashBoard(String partNumber, String partDesc, int partLocation) {
		try {
			test.log(Status.INFO,
					"createNewMCPartNumberFromDashBoard(String partNumber, String partDesc, int partLocation)");

			click_para("PanaMC.MCMCConfigButton_xpath");
			click_para("PanaMC.MCPartConfigOption_xpath");
			click_para("PanaMC.MCCreateNewPartButton_xpath");
			click_para("PanaMC.MCNewPartNumberTextBox_xpath");
			type_para("PanaMC.MCNewPartNumberTextBox_xpath", partNumber);
			click_para("PanaMC.MCNewPartDescTextBox_xpath");
			type_para("PanaMC.MCNewPartDescTextBox_xpath", partDesc);
			scrollingToElementofAPage("PanaMC.MCPartMCCheckBox_xpath");
			click_para("PanaMC.MCPartMCCheckBox_xpath");
			if (partLocation == 1) {
				// Automated Optical Inspection in Quality
				click_para("PanaMC.MCDefaultReceivingLocationList_xpath");
				sleepThread1Sec();
				click_para("PanaMC.MCReceivingLocationAOIQOption1_xpath");
			} else if (partLocation == 2) {
				// Axial Insertion
				click_para("PanaMC.MCDefaultReceivingLocationList_xpath");
				sleepThread1Sec();
				click_para("PanaMC.MCReceivingLocationAIOption_xpath");
			}
			scrollingToBottomofAPage();
			click_para("PanaMC.MCNewPartSaveButton_xpath");
			DymamicWait();
			click_para("PanaMC.MCDisplayMessageCloseIcon_xpath");
			sleepThread1Sec();
			click_para("PanaMC.MCMCHomeButton_xpath");
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}
	
	public void preSetup_MCID1193() {
		try {
			test.log(Status.INFO, "preSetup_MCID1193");

			// create new part number
			String currentTime = new SimpleDateFormat("yyyymmddhhmmss").format(Calendar.getInstance().getTime());
			String partNumber = "MCID1193PartNumber " + currentTime;
			String partDesc = "Part Number for MCID1193";
			String partQuantity1 = "50";
			String partQuantity2 = "60";

			createNewMCPartNumberFromDashBoard(partNumber, partDesc, 1);
			DynamicWait();
			receiveMCPartNumberFromDashBoard(partNumber, null, partQuantity1, 0, false);

			DymamicWait();
			receiveMCPartNumberFromDashBoard(partNumber, null, partQuantity2, 0, false);

			DymamicWait();
			spliceMCPartNumber1WithPartNumber2FromDashboard();
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void preSetup_MCID1195() {
		try {
			test.log(Status.INFO, "preSetup_MCID1195");

			// create new part number
			String currentTime = new SimpleDateFormat("yyyymmddhhmmss").format(Calendar.getInstance().getTime());
			String partNumber = "MCID1195PartNumber " + currentTime;
			String partDesc = "Part Number for MCID1195";
			String partQuantity1 = "50";
			String partQuantity2 = "60";

			createNewMCPartNumberFromDashBoard(partNumber, partDesc, 1);
			DynamicWait();
			receiveMCPartNumberFromDashBoard(partNumber, null, partQuantity1, 0, false);

			DymamicWait();
			receiveMCPartNumberFromDashBoard(partNumber, null, partQuantity2, 0, false);

			DymamicWait();
			spliceMCPartNumber1WithPartNumber2FromDashboard();
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void preSetup_MCID1196() {
		try {
			test.log(Status.INFO, "preSetup_MCID1196");

			// create new part number
			String currentTime = new SimpleDateFormat("yyyymmddhhmmss").format(Calendar.getInstance().getTime());
			String partNumber = "MCID1196PartNumber " + currentTime;
			String partDesc = "Part Number for MCID1196";
			String partQuantity1 = "50";
			String partQuantity2 = "60";

			createNewMCPartNumberFromDashBoard(partNumber, partDesc, 1);
			DynamicWait();
			receiveMCPartNumberFromDashBoard(partNumber, null, partQuantity1, 0, false);

			DymamicWait();
			receiveMCPartNumberFromDashBoard(partNumber, null, partQuantity2, 0, false);

			DymamicWait();
			spliceMCPartNumber1WithPartNumber2FromDashboard();
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void preSetup_MCID1198() {
		try {
			test.log(Status.INFO, "preSetup_MCID1198");

			// create new part number
			String currentTime = new SimpleDateFormat("yyyymmddhhmmss").format(Calendar.getInstance().getTime());
			String partNumber = "MCID1198PartNumber " + currentTime;
			String partDesc = "Part Number for MCID1198";
			String partQuantity1 = "50";
			String partQuantity2 = "60";

			createNewMCPartNumberFromDashBoard(partNumber, partDesc, 1);
			DynamicWait();
			receiveMCPartNumberFromDashBoard(partNumber, null, partQuantity1, 0, false);

			DymamicWait();
			receiveMCPartNumberFromDashBoard(partNumber, null, partQuantity2, 0, false);

			DymamicWait();
			spliceMCPartNumber1WithPartNumber2FromDashboard();
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void preSetup_MCID1199() {
		try {
			test.log(Status.INFO, "preSetup_MCID1199");
			String partState = data.get(dataKey);
			String currentTime = new SimpleDateFormat("yyyymmddhhmmss").format(Calendar.getInstance().getTime());

			if (partState.equalsIgnoreCase("Hold")) {
				// create new part then set state to Hold
				String partNumber = "MCID1199PartNumberHold " + currentTime;
				String partDesc = "Part Number Hold for MCID1199";
				String partQuantity1 = "50";
				String partQuantity2 = "60";

				createNewMCPartNumberFromDashBoard(partNumber, partDesc, 1);
				DynamicWait();
				receiveMCPartNumberFromDashBoard(partNumber, null, partQuantity1, 0, false);

				DymamicWait();
				receiveMCPartNumberFromDashBoard(partNumber, null, partQuantity2, 0, false);

				DymamicWait();
				changeStateMCPartNumber2FromDashboard("Hold");
			} else if (partState.equalsIgnoreCase("Scrap")) {
				// create new part then set state to Scrap
				String partNumber = "MCID1199PartNumberScrap " + currentTime;
				String partDesc = "Part Number Scrap for MCID1199";
				String partQuantity1 = "50";
				String partQuantity2 = "60";

				createNewMCPartNumberFromDashBoard(partNumber, partDesc, 1);
				DynamicWait();
				receiveMCPartNumberFromDashBoard(partNumber, null, partQuantity1, 0, false);

				DymamicWait();
				receiveMCPartNumberFromDashBoard(partNumber, null, partQuantity2, 0, false);

				DymamicWait();
				changeStateMCPartNumber2FromDashboard("Scrap");
			} else {
				reportFailure("Invalid state input: " + partState);
			}

			DymamicWait();
			spliceMCPartNumber1WithPartNumber2FromDashboard();
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void preSetup_MCID1200() {
		try {
			test.log(Status.INFO, "preSetup_MCID1200");

			// create new part number
			String currentTime = new SimpleDateFormat("yyyymmddhhmmss").format(Calendar.getInstance().getTime());
			String partNumber = "MCID1200PartNumber " + currentTime;
			String partDesc = "Part Number for MCID1200";
			String partQuantity1 = "50";
			String partQuantity2 = "60";

			createNewMCPartNumberFromDashBoard(partNumber, partDesc, 1);
			DynamicWait();
			receiveMCPartNumberFromDashBoard(partNumber, null, partQuantity1, 0, false);

			DymamicWait();
			receiveMCPartNumberFromDashBoard(partNumber, null, partQuantity2, 0, false);

			DymamicWait();
			spliceMCPartNumber1WithPartNumber2FromDashboard();
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void preSetup_MCID1201() {
		try {
			test.log(Status.INFO, "preSetup_MCID1201");

			// create new part number
			String currentTime = new SimpleDateFormat("yyyymmddhhmmss").format(Calendar.getInstance().getTime());
			String partNumber = "MCID1201PartNumber " + currentTime;
			String partDesc = "Part Number for MCID1201";
			String partQuantity1 = "50";
			String partQuantity2 = "60";

			createNewMCPartNumberFromDashBoard(partNumber, partDesc, 1);
			DynamicWait();
			receiveMCPartNumberFromDashBoard(partNumber, null, partQuantity1, 0, false);

			DymamicWait();
			receiveMCPartNumberFromDashBoard(partNumber, null, partQuantity2, 0, false);

			DymamicWait();
			spliceMCPartNumber1WithPartNumber2FromDashboard();

			DymamicWait();
			changeStateMCPartNumber2FromDashboard("Hold");
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void preSetup_MCID1202() {
		try {
			test.log(Status.INFO, "preSetup_MCID1202");

			// create new part number
			String currentTime = new SimpleDateFormat("yyyymmddhhmmss").format(Calendar.getInstance().getTime());
			String partNumber = "MCID1202PartNumber " + currentTime;
			String partDesc = "Part Number for MCID1202";
			String partQuantity1 = "50";
			String partQuantity2 = "60";

			createNewMCPartNumberFromDashBoard(partNumber, partDesc, 1);
			DynamicWait();
			receiveMCPartNumberFromDashBoard(partNumber, null, partQuantity1, 0, false);

			DymamicWait();
			receiveMCPartNumberFromDashBoard(partNumber, null, partQuantity2, 0, false);

			DymamicWait();
			spliceMCPartNumber1WithPartNumber2FromDashboard();

			DymamicWait();
			changeStateMCPartNumber2FromDashboard("Scrap");
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void preSetup_MCID1203() {
		try {
			test.log(Status.INFO, "preSetup_MCID1203");

			// create new part number
			String currentTime = new SimpleDateFormat("yyyymmddhhmmss").format(Calendar.getInstance().getTime());
			String partLocation = "MCID1203PartLocationRestrict " + currentTime;
			String partNumber = "MCID1203PartNumber " + currentTime;
			String partDesc = "Part Number for MCID1203";
			String partQuantity1 = "50";
			String partQuantity2 = "60";

			createNewLocationFromDashBoard(partLocation, true);
			DymamicWait();
			createNewMCPartNumberFromDashBoard(partNumber, partDesc, 2);

			DynamicWait();
			receiveMCPartNumberFromDashBoard(partNumber, null, partQuantity1, 0, false);
			DymamicWait();
			receiveMCPartNumberFromDashBoard(partNumber, null, partQuantity2, 0, false);

			DymamicWait();
			moveMCPartNumber1ToNewLocationFromDashBoard(partLocation);

			DymamicWait();
			spliceMCPartNumber1WithPartNumber2FromDashboard();
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void preSetup_MCID1434() {
		try {
			test.log(Status.INFO, "preSetup_MCID1434");

			// create new part number
			String currentTime = new SimpleDateFormat("yyyymmddhhmmss").format(Calendar.getInstance().getTime());
			String partNumber = "MCID1434PartNumber " + currentTime;
			String partDesc = "Part Number for MCID1434";
			String partQuantity = "50";

			createNewMCPartNumberFromDashBoard(partNumber, partDesc, 1);
			DynamicWait();
			receiveMCPartNumberFromDashBoard(partNumber, null, partQuantity, 0, false);

			DymamicWait();
			String partNumber1PartNumber = driver.findElement(By.xpath(prop.getProperty("PanaMC.MCPartNumber1_xpath")))
					.getText();
			if (!partNumber1PartNumber.equalsIgnoreCase(partNumber)) {
				reportFailure("Received not correct Part Number. Expect: " + partNumber + " Actual: "
						+ partNumber1PartNumber);
			} else {
				test.log(Status.INFO, "Received correct Part Number: " + partNumber);
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void preSetup_MCID1518() {
		try {
			test.log(Status.INFO, "preSetup_MCID1518");

			// create new part number
			String currentTime = new SimpleDateFormat("yyyymmddhhmmss").format(Calendar.getInstance().getTime());
			String partNumber = "MCID1518PartNumber " + currentTime;
			String partDesc = "Part Number for MCID1518";
			String partQuantity = "70";

			createNewMCPartNumberFromDashBoard(partNumber, partDesc, 1);
			DynamicWait();
			receiveMCPartNumberFromDashBoard(partNumber, null, partQuantity, 0, false);
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void preSetup_MCID1519() {
		try {
			test.log(Status.INFO, "preSetup_MCID1519");

			String currentTime = new SimpleDateFormat("yyyymmddhhmmss").format(Calendar.getInstance().getTime());

			// create new part number
			String partNumber = "MCID1519PartNumber " + currentTime;
			String partDesc = "Part Number for MCID1519";
			String partQuantity1 = "50";
			String partQuantity2 = "60";
			String partLabelConfig = "LC " + currentTime;

			createNewLabelConfigFromDashBoard(partLabelConfig);
			DymamicWait();

			createNewMCPartNumberWithLabelConfigFromDashBoard(partNumber, partDesc, 1, partLabelConfig);
			DymamicWait();
			receiveMCPartNumberFromDashBoard(partNumber, null, partQuantity1, 0, false);
			DymamicWait();
			receiveMCPartNumberFromDashBoard(partNumber, null, partQuantity2, 0, false);
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void preSetup_MCID1524() {
		try {
			test.log(Status.INFO, "preSetup_MCID1524");

			// create new part number
			String currentTime = new SimpleDateFormat("yyyymmddhhmmss").format(Calendar.getInstance().getTime());
			String partNumber = "MCID1524PartNumber " + currentTime;
			String partDesc = "Part Number for MCID1524";
			String partQuantity = "70";

			createNewMCPartNumberFromDashBoard(partNumber, partDesc, 1);
			DynamicWait();
			receiveMCPartNumberFromDashBoard(partNumber, null, partQuantity, 0, false);

			DymamicWait();
			String partNumber1PartNumber = driver.findElement(By.xpath(prop.getProperty("PanaMC.MCPartNumber1_xpath")))
					.getText();
			if (!partNumber1PartNumber.equalsIgnoreCase(partNumber)) {
				reportFailure("Received not correct Part Number. Expect: " + partNumber + " Actual: "
						+ partNumber1PartNumber);
			} else {
				test.log(Status.INFO, "Received correct Part Number: " + partNumber);
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void preSetup_MCID1444() {
		try {
			test.log(Status.INFO, "preSetup_MCID1444");

			// create new part number
			String currentTime = new SimpleDateFormat("yyyymmddhhmmss").format(Calendar.getInstance().getTime());
			String partNumber = "MCID1444PartNumber " + currentTime;
			String partDesc = "Part Number for MCID1444";
			String partQuantity = "70";

			createNewMCPartNumberFromDashBoard(partNumber, partDesc, 1);
			DynamicWait();
			receiveMCPartNumberFromDashBoard(partNumber, null, partQuantity, 0, false);

			DymamicWait();
			String partNumber1PartNumber = driver.findElement(By.xpath(prop.getProperty("PanaMC.MCPartNumber1_xpath")))
					.getText();
			if (!partNumber1PartNumber.equalsIgnoreCase(partNumber)) {
				reportFailure("Received not correct Part Number. Expect: " + partNumber + " Actual: "
						+ partNumber1PartNumber);
			} else {
				test.log(Status.INFO, "Received correct Part Number: " + partNumber);
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void preSetup_MCID1445() {
		try {
			test.log(Status.INFO, "preSetup_MCID1445");

			// create new part number
			String currentTime = new SimpleDateFormat("yyyymmddhhmmss").format(Calendar.getInstance().getTime());
			String partNumber = "MCID1445PartNumber " + currentTime;
			String partDesc = "Part Number for MCID1445";
			String partQuantity = "70";

			createNewMCPartNumberFromDashBoard(partNumber, partDesc, 1);
			DynamicWait();
			receiveMCPartNumberFromDashBoard(partNumber, null, partQuantity, 0, false);

			DymamicWait();
			String partNumber1PartNumber = driver.findElement(By.xpath(prop.getProperty("PanaMC.MCPartNumber1_xpath")))
					.getText();
			if (!partNumber1PartNumber.equalsIgnoreCase(partNumber)) {
				reportFailure("Received not correct Part Number. Expect: " + partNumber + " Actual: "
						+ partNumber1PartNumber);
			} else {
				test.log(Status.INFO, "Received correct Part Number: " + partNumber);
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void preSetup_MCID1447() {
		try {
			test.log(Status.INFO, "preSetup_MCID1447");

			// create new part number
			String currentTime = new SimpleDateFormat("yyyymmddhhmmss").format(Calendar.getInstance().getTime());
			String partNumber = "MCID1447PartNumber " + currentTime;
			String partDesc = "Part Number for MCID1447";
			String partQuantity = "70";

			createNewNonMCPartNumberFromDashBoard(partNumber, partDesc, 1);
			DynamicWait();
			receiveMCPartNumberFromDashBoard(partNumber, null, partQuantity, 0, false);

			DymamicWait();
			String partNumber1PartNumber = driver.findElement(By.xpath(prop.getProperty("PanaMC.MCPartNumber1_xpath")))
					.getText();
			if (!partNumber1PartNumber.equalsIgnoreCase(partNumber)) {
				reportFailure("Received not correct Part Number. Expect: " + partNumber + " Actual: "
						+ partNumber1PartNumber);
			} else {
				test.log(Status.INFO, "Received correct Part Number: " + partNumber);
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void preSetup_MCID1448() {
		try {
			test.log(Status.INFO, "preSetup_MCID1448");

			// create new part number
			String currentTime = new SimpleDateFormat("yyyymmddhhmmss").format(Calendar.getInstance().getTime());
			String partNumber = "MCID1448PartNumber " + currentTime;
			String partDesc = "Part Number for MCID1448";
			String partQuantity = "70";

			createNewMCPartNumberFromDashBoard(partNumber, partDesc, 1);
			DynamicWait();
			receiveMCPartNumberFromDashBoard(partNumber, null, partQuantity, 0, false);

			DymamicWait();
			String partNumber1PartNumber = driver.findElement(By.xpath(prop.getProperty("PanaMC.MCPartNumber1_xpath")))
					.getText();
			if (!partNumber1PartNumber.equalsIgnoreCase(partNumber)) {
				reportFailure("Received not correct Part Number. Expect: " + partNumber + " Actual: "
						+ partNumber1PartNumber);
			} else {
				test.log(Status.INFO, "Received correct Part Number: " + partNumber);
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void preSetup_MCID1449() {
		try {
			test.log(Status.INFO, "preSetup_MCID1449");

			// create new part number
			String currentTime = new SimpleDateFormat("yyyymmddhhmmss").format(Calendar.getInstance().getTime());
			String partNumber = "MCID1449PartNumber " + currentTime;
			String partDesc = "Part Number for MCID1449";
			String partQuantity = "70";

			createNewMCPartNumberFromDashBoard(partNumber, partDesc, 1);
			DynamicWait();
			receiveMCPartNumberFromDashBoard(partNumber, null, partQuantity, 0, false);

			DymamicWait();
			String partNumber1PartNumber = driver.findElement(By.xpath(prop.getProperty("PanaMC.MCPartNumber1_xpath")))
					.getText();
			if (!partNumber1PartNumber.equalsIgnoreCase(partNumber)) {
				reportFailure("Received not correct Part Number. Expect: " + partNumber + " Actual: "
						+ partNumber1PartNumber);
			} else {
				test.log(Status.INFO, "Received correct Part Number: " + partNumber);
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void preSetup_MCID1450() {
		try {
			test.log(Status.INFO, "preSetup_MCID1450");

			// create new part number
			String currentTime = new SimpleDateFormat("yyyymmddhhmmss").format(Calendar.getInstance().getTime());
			String partNumber = "MCID1450PartNumber " + currentTime;
			String partDesc = "Part Number for MCID1450";
			String partQuantity = "70";

			createNewMCPartNumberFromDashBoard(partNumber, partDesc, 1);
			DynamicWait();
			receiveMCPartNumberFromDashBoard(partNumber, null, partQuantity, 0, false);

			DymamicWait();
			String partNumber1PartNumber = driver.findElement(By.xpath(prop.getProperty("PanaMC.MCPartNumber1_xpath")))
					.getText();
			if (!partNumber1PartNumber.equalsIgnoreCase(partNumber)) {
				reportFailure("Received not correct Part Number. Expect: " + partNumber + " Actual: "
						+ partNumber1PartNumber);
			} else {
				test.log(Status.INFO, "Received correct Part Number: " + partNumber);
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void preSetup_MCID1451() {
		try {
			test.log(Status.INFO, "preSetup_MCID1451");

			// create new part number
			String currentTime = new SimpleDateFormat("yyyymmddhhmmss").format(Calendar.getInstance().getTime());
			String partNumber = "MCID1451PartNumber " + currentTime;
			String partDesc = "Part Number for MCID1451";
			String partQuantity = "70";

			createNewNonMCPartNumberFromDashBoard(partNumber, partDesc, 1);
			DynamicWait();
			receiveMCPartNumberFromDashBoard(partNumber, null, partQuantity, 0, false);

			DymamicWait();
			String partNumber1PartNumber = driver.findElement(By.xpath(prop.getProperty("PanaMC.MCPartNumber1_xpath")))
					.getText();
			if (!partNumber1PartNumber.equalsIgnoreCase(partNumber)) {
				reportFailure("Received not correct Part Number. Expect: " + partNumber + " Actual: "
						+ partNumber1PartNumber);
			} else {
				test.log(Status.INFO, "Received correct Part Number: " + partNumber);
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void preSetup_MCID1452() {
		try {
			test.log(Status.INFO, "preSetup_MCID1452");

			// create new part number
			String currentTime = new SimpleDateFormat("yyyymmddhhmmss").format(Calendar.getInstance().getTime());
			String partNumber = "MCID1452PartNumber " + currentTime;
			String partDesc = "Part Number for MCID1452";
			String partQuantity = "70";

			createNewMCPartNumberFromDashBoard(partNumber, partDesc, 1);
			DynamicWait();
			receiveMCPartNumberFromDashBoard(partNumber, null, partQuantity, 0, false);

			DymamicWait();
			String partNumber1PartNumber = driver.findElement(By.xpath(prop.getProperty("PanaMC.MCPartNumber1_xpath")))
					.getText();
			if (!partNumber1PartNumber.equalsIgnoreCase(partNumber)) {
				reportFailure("Received not correct Part Number. Expect: " + partNumber + " Actual: "
						+ partNumber1PartNumber);
			} else {
				test.log(Status.INFO, "Received correct Part Number: " + partNumber);
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void preSetup_MCID1453() {
		try {
			test.log(Status.INFO, "preSetup_MCID1453");

			// create new part number
			String currentTime = new SimpleDateFormat("yyyymmddhhmmss").format(Calendar.getInstance().getTime());
			String partNumber = "MCID1453PartNumber " + currentTime;
			String partDesc = "Part Number for MCID1453";
			String partQuantity1 = "50";
			String partQuantity2 = "60";

			createNewMCPartNumberFromDashBoard(partNumber, partDesc, 1);
			DynamicWait();
			receiveMCPartNumberFromDashBoard(partNumber, null, partQuantity1, 0, false);

			DymamicWait();
			receiveMCPartNumberFromDashBoard(partNumber, null, partQuantity2, 0, false);

			DymamicWait();
			spliceMCPartNumber1WithPartNumber2FromDashboard();
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void preSetup_MCID1454() {
		try {
			test.log(Status.INFO, "preSetup_MCID1454");

			// create new part number
			String currentTime = new SimpleDateFormat("yyyymmddhhmmss").format(Calendar.getInstance().getTime());
			String partNumber = "MCID1454PartNumber " + currentTime;
			String partDesc = "Part Number for MCID1454";
			String partQuantity1 = "50";
			String partQuantity2 = "60";

			createNewMCPartNumberFromDashBoard(partNumber, partDesc, 1);
			DynamicWait();
			receiveMCPartNumberFromDashBoard(partNumber, null, partQuantity1, 1, false);

			DymamicWait();
			receiveMCPartNumberFromDashBoard(partNumber, null, partQuantity2, 2, false);

			DymamicWait();
			spliceMCPartNumber1WithPartNumber2FromDashboard();
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void preSetup_MCID1455() {
		try {
			test.log(Status.INFO, "preSetup_MCID1455");

			// create new part number
			String currentTime = new SimpleDateFormat("yyyymmddhhmmss").format(Calendar.getInstance().getTime());
			String partNumber = "MCID1455PartNumber " + currentTime;
			String partDesc = "Part Number for MCID1455";
			String partQuantity1 = "50";
			String partQuantity2 = "60";
			String partPSDData1 = "PSD Data 1";
			String partPSDData2 = "PSD Data 2";

			String partPSD1 = "PSD1";
			String partPSD2 = "PSD2";

			createNewPSDDataFromDashBoard(partPSD1);
			DymamicWait();
			createNewPSDDataFromDashBoard(partPSD2);
			DymamicWait();

			createNewMCPartNumberWithPSDFromDashBoard(partNumber, partDesc, 1, "1");
			DynamicWait();
			receiveMCPartNumberWithPSDFromDashBoard(partNumber, null, partQuantity1, 1, false, partPSDData1);

			DymamicWait();
			receiveMCPartNumberWithPSDFromDashBoard(partNumber, null, partQuantity2, 1, false, partPSDData2);

			DymamicWait();
			spliceMCPartNumber1WithPartNumber2FromDashboard();
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void preSetup_MCID1456() {
		try {
			test.log(Status.INFO, "preSetup_MCID1456");

			// create new part number
			String currentTime = new SimpleDateFormat("yyyymmddhhmmss").format(Calendar.getInstance().getTime());
			String partNumber1 = "MCID1456PartNumber1 " + currentTime;
			String partNumber2 = "MCID1456PartNumber2 " + currentTime;
			String partDesc = "Part Number for MCID1456";
			String partQuantity1 = "50";
			String partQuantity2 = "60";

			createNewMCPartNumberFromDashBoard(partNumber1, partDesc, 1);
			DynamicWait();
			receiveMCPartNumberFromDashBoard(partNumber1, null, partQuantity1, 1, false);

			DymamicWait();
			createNewMCPartNumber2FromDashBoard(partNumber2, partDesc, 1);
			DymamicWait();
			receiveMCPartNumberFromDashBoard(partNumber2, null, partQuantity2, 1, false);

			DymamicWait();
			spliceMCPartNumber1WithPartNumber2FromDashboard();
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void preSetup_MCID1458() {
		try {
			test.log(Status.INFO, "preSetup_MCID1458");

			// create new part number
			String currentTime = new SimpleDateFormat("yyyymmddhhmmss").format(Calendar.getInstance().getTime());
			String partNumber = "MCID1458PartNumber " + currentTime;
			String partDesc = "Part Number for MCID1458";
			String partQuantity1 = "50";
			String partQuantity2 = "60";

			createNewMCPartNumberFromDashBoard(partNumber, partDesc, 1);
			DynamicWait();
			receiveMCPartNumberFromDashBoard(partNumber, null, partQuantity1, 1, false);
			DymamicWait();
			receiveMCPartNumberFromDashBoard(partNumber, null, partQuantity2, 1, false);

			DymamicWait();
			spliceMCPartNumber1WithPartNumber2FromDashboard();
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void preSetup_MCID1460() {
		try {
			test.log(Status.INFO, "preSetup_MCID1460");

			// create new part number
			String currentTime = new SimpleDateFormat("yyyymmddhhmmss").format(Calendar.getInstance().getTime());
			String partNumber = "MCID1460PartNumber " + currentTime;
			String partDesc = "Part Number for MCID1460";
			String partQuantity1 = "50";
			String partQuantity2 = "60";

			createNewMCPartNumberFromDashBoard(partNumber, partDesc, 1);
			DynamicWait();
			receiveMCPartNumberFromDashBoard(partNumber, null, partQuantity1, 1, false);
			DymamicWait();
			receiveMCPartNumberFromDashBoard(partNumber, null, partQuantity2, 1, false);

			DymamicWait();
			spliceMCPartNumber1WithPartNumber2FromDashboard();
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void preSetup_MCID1461() {
		try {
			test.log(Status.INFO, "preSetup_MCID1461");

			// create new part number
			String currentTime = new SimpleDateFormat("yyyymmddhhmmss").format(Calendar.getInstance().getTime());
			String partNumber = "MCID1461PartNumber " + currentTime;
			String partDesc = "Part Number for MCID1461";
			String partQuantity1 = "50";
			String partQuantity2 = "60";

			createNewMCPartNumberFromDashBoard(partNumber, partDesc, 1);
			DynamicWait();
			receiveMCPartNumberFromDashBoard(partNumber, null, partQuantity1, 1, false);
			DymamicWait();
			receiveMCPartNumberFromDashBoard(partNumber, null, partQuantity2, 1, false);

			DymamicWait();
			spliceMCPartNumber1WithPartNumber2FromDashboard();
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void preSetup_MCID1463() {
		try {
			test.log(Status.INFO, "preSetup_MCID1463");

			// create new part number
			String currentTime = new SimpleDateFormat("yyyymmddhhmmss").format(Calendar.getInstance().getTime());
			String partNumber = "MCID1463PartNumber " + currentTime;
			String partDesc = "Part Number for MCID1463";
			String partQuantity1 = "50";
			String partQuantity2 = "60";

			createNewMCPartNumberFromDashBoard(partNumber, partDesc, 1);
			DynamicWait();
			receiveMCPartNumberFromDashBoard(partNumber, null, partQuantity1, 1, false);
			DymamicWait();
			receiveMCPartNumberFromDashBoard(partNumber, null, partQuantity2, 1, false);

			DymamicWait();
			spliceMCPartNumber1WithPartNumber2FromDashboard();
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void preSetup_MCID1466() {
		try {
			test.log(Status.INFO, "preSetup_MCID1466");

			// create new part number
			String currentTime = new SimpleDateFormat("yyyymmddhhmmss").format(Calendar.getInstance().getTime());
			String partNumber = "MCID1466PartNumber " + currentTime;
			String partDesc = "Part Number for MCID1466";
			String partQuantity1 = "50";
			String partQuantity2 = "60";

			createNewMCPartNumberFromDashBoard(partNumber, partDesc, 1);
			DynamicWait();
			receiveMCPartNumberFromDashBoard(partNumber, null, partQuantity1, 1, false);
			DymamicWait();
			receiveMCPartNumberFromDashBoard(partNumber, null, partQuantity2, 1, false);

			DymamicWait();
			spliceMCPartNumber1WithPartNumber2FromDashboard();
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void preSetup_MCID1467() {
		try {
			test.log(Status.INFO, "preSetup_MCID1466");

			// create new part number
			String currentTime = new SimpleDateFormat("yyyymmddhhmmss").format(Calendar.getInstance().getTime());
			String partNumber = "MCID1467PartNumber " + currentTime;
			String partDesc = "Part Number for MCID1467";
			String partQuantity1 = "50";
			String partQuantity2 = "60";
			String partQuantity3 = "70";

			createNewMCPartNumberFromDashBoard(partNumber, partDesc, 1);
			DynamicWait();
			receiveMCPartNumberFromDashBoard(partNumber, null, partQuantity1, 1, false);
			DymamicWait();
			receiveMCPartNumberFromDashBoard(partNumber, null, partQuantity2, 1, false);
			DymamicWait();
			receiveMCPartNumberFromDashBoard(partNumber, null, partQuantity3, 1, false);
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void preSetup_MCID1469() {
		try {
			test.log(Status.INFO, "preSetup_MCID1469");

			// create new part number
			String currentTime = new SimpleDateFormat("yyyymmddhhmmss").format(Calendar.getInstance().getTime());
			String partNumber = "MCID1469PartNumber " + currentTime;
			String partDesc = "Part Number for MCID1469";
			String partQuantity1 = "50";
			String partQuantity2 = "60";

			createNewMCPartNumberFromDashBoard(partNumber, partDesc, 1);
			DynamicWait();
			receiveMCPartNumberFromDashBoard(partNumber, null, partQuantity1, 0, false);

			DymamicWait();
			receiveMCPartNumberFromDashBoard(partNumber, null, partQuantity2, 0, false);

			DymamicWait();
			spliceMCPartNumber1WithPartNumber2FromDashboard();
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void preSetup_MCID1470() {
		try {
			test.log(Status.INFO, "preSetup_MCID1470");

			// create new part number
			String currentTime = new SimpleDateFormat("yyyymmddhhmmss").format(Calendar.getInstance().getTime());
			String partNumber = "MCID1470PartNumber " + currentTime;
			String partDesc = "Part Number for MCID1470";
			String partQuantity1 = "50";
			String partQuantity2 = "60";

			createNewMCPartNumberFromDashBoard(partNumber, partDesc, 1);
			DynamicWait();
			receiveMCPartNumberFromDashBoard(partNumber, null, partQuantity1, 0, false);

			DymamicWait();
			receiveMCPartNumberFromDashBoard(partNumber, null, partQuantity2, 0, false);

			DymamicWait();
			spliceMCPartNumber1WithPartNumber2FromDashboard();

			DymamicWait();
			changeStateMCPartNumber2FromDashboard("Hold");
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void preSetup_MCID1471() {
		try {
			test.log(Status.INFO, "preSetup_MCID1471");

			// create new part number
			String currentTime = new SimpleDateFormat("yyyymmddhhmmss").format(Calendar.getInstance().getTime());
			String partNumber = "MCID1471PartNumber " + currentTime;
			String partDesc = "Part Number for MCID1471";
			String partQuantity1 = "50";
			String partQuantity2 = "60";

			createNewMCPartNumberFromDashBoard(partNumber, partDesc, 1);
			DynamicWait();
			receiveMCPartNumberFromDashBoard(partNumber, null, partQuantity1, 0, false);

			DymamicWait();
			receiveMCPartNumberFromDashBoard(partNumber, null, partQuantity2, 0, false);

			DymamicWait();
			spliceMCPartNumber1WithPartNumber2FromDashboard();

			DymamicWait();
			changeStateMCPartNumber2FromDashboard("Scrap");
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void preSetup_MCID1472() {
		try {
			test.log(Status.INFO, "preSetup_MCID1472");

			// create new part number
			String currentTime = new SimpleDateFormat("yyyymmddhhmmss").format(Calendar.getInstance().getTime());
			String partLocation = "MCID1472PartLocationRestrict " + currentTime;
			String partNumber = "MCID1472PartNumber " + currentTime;
			String partDesc = "Part Number for MCID1472";
			String partQuantity1 = "50";
			String partQuantity2 = "60";

			createNewLocationFromDashBoard(partLocation, true);
			DymamicWait();
			createNewMCPartNumberFromDashBoard(partNumber, partDesc, 2);

			DynamicWait();
			receiveMCPartNumberFromDashBoard(partNumber, null, partQuantity1, 0, false);
			DymamicWait();
			receiveMCPartNumberFromDashBoard(partNumber, null, partQuantity2, 0, false);

			DymamicWait();
			moveMCPartNumber1ToNewLocationFromDashBoard(partLocation);

			DymamicWait();
			spliceMCPartNumber2WithPartNumber1FromDashboard();
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void preSetup_MCID1473() {
		try {
			test.log(Status.INFO, "preSetup_MCID1473");

			// create new part number
			String currentTime = new SimpleDateFormat("yyyymmddhhmmss").format(Calendar.getInstance().getTime());
			String partLocation = "MCID1473PartLocationRestrict " + currentTime;
			String partNumber = "MCID1473PartNumber " + currentTime;
			String partDesc = "Part Number for MCID1473";
			String partQuantity1 = "50";
			String partQuantity2 = "60";

			createNewLocationFromDashBoard(partLocation, true);
			DymamicWait();
			createNewMCPartNumberFromDashBoard(partNumber, partDesc, 2);

			DynamicWait();
			receiveMCPartNumberFromDashBoard(partNumber, null, partQuantity1, 0, false);
			DymamicWait();
			receiveMCPartNumberFromDashBoard(partNumber, null, partQuantity2, 0, false);

			DymamicWait();
			spliceMCPartNumber2WithPartNumber1FromDashboard();

			DymamicWait();
			moveMCPartNumber1ToNewLocationFromDashBoard(partLocation);
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void preSetup_MCID1474() {
		try {
			test.log(Status.INFO, "preSetup_MCID1474");

			// create new part number
			String currentTime = new SimpleDateFormat("yyyymmddhhmmss").format(Calendar.getInstance().getTime());
			String partLocation = "MCID1474PartLocationRestrict " + currentTime;
			String partNumber = "MCID1474PartNumber " + currentTime;
			String partDesc = "Part Number for MCID1474";
			String partQuantity1 = "50";
			String partQuantity2 = "60";

			createNewLocationFromDashBoard(partLocation, true);
			DymamicWait();
			createNewMCPartNumberFromDashBoard(partNumber, partDesc, 2);

			DynamicWait();
			receiveMCPartNumberFromDashBoard(partNumber, null, partQuantity1, 0, false);
			DymamicWait();
			receiveMCPartNumberFromDashBoard(partNumber, null, partQuantity2, 0, false);

			DymamicWait();
			spliceMCPartNumber2WithPartNumber1FromDashboard();

			DymamicWait();
			changeStateMCPartNumber2WithRestrictLocationFromDashboard("Scrap", partLocation);
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void preSetup_MCID1740() {
		try {
			test.log(Status.INFO, "preSetup_MCID1740");

			// create new part number
			String currentTime = new SimpleDateFormat("yyyymmddhhmmss").format(Calendar.getInstance().getTime());
			String partNumber = "MCID1740PartNumber " + currentTime;
			String partDesc = "Part Number for MCID1740";
			String partQuantity = "50";

			//createNewMCPartNumberFromDashBoard(partNumber, partDesc, 1);
			createNewMCPartNumberFromDashBoardNew(partNumber, partDesc, 1);
			DynamicWait();
			receiveMCPartNumberFromDashBoardnew(partNumber, null, partQuantity, 0, false);
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}
	
	public void createNewMCPartNumberFromDashBoardNew(String partNumber, String partDesc, int partLocation) {
		try {
			
			click_paranew("PanaMC.MCMCConfigButton_xpath","MC Config Option");
			click_paranew("PanaMC.MCPartConfigOption_xpath","Part Config Option Under MC Config");
			click_paranew("PanaMC.MCCreateNewPartButton_xpath","New Button");
			click_paranew("PanaMC.MCNewPartNumberTextBox_xpath","Part Number Text Field");
			type_paranew("PanaMC.MCNewPartNumberTextBox_xpath", partNumber,"Part Number Text Field");
			click_paranew("PanaMC.MCNewPartDescTextBox_xpath","Description Text Field");
			type_paranew("PanaMC.MCNewPartDescTextBox_xpath", partDesc,"Description Text Field");
			scrollingToElementofAPage("PanaMC.MCPartMCCheckBox_xpath");
			click_paranew("PanaMC.MCPartMCCheckBox_xpath","MC Check Box");
			if (partLocation == 1) {
				// Automated Optical Inspection in Quality
				click_paranew("PanaMC.MCDefaultReceivingLocationList_xpath","Default Receiving Location List Box ");
				click_paranew("PanaMC.MCReceivingLocationAOIQOption_xpath","Default Receiving Location List Item");
			} else if (partLocation == 2) {
				// Axial Insertion
				click_paranew("PanaMC.MCDefaultReceivingLocationList_xpath","Default Receiving Location List Box");
				click_paranew("PanaMC.MCReceivingLocationAIOption_xpath","Default Receiving Location List Item");
			}
			scrollingToBottomofAPage();
			click_paranew("PanaMC.MCNewPartSaveButton_xpath","Save Button");
			DymamicWait();
			click_paranew("PanaMC.MCDisplayMessageCloseIcon_xpath","Close Icon");
			sleepThread1Sec();
			click_paranew("PanaMC.MCMCHomeButton_xpath","Home Button");
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}


public void receiveMCPartNumberFromDashBoardnew(String partNumber, String partMID, String partQuantity,
			int partLocation, boolean isPrintCheck) {
		try {
			
			click_paranew("PanaMC.MCReceiveButton_xpath","Receive Button");
			if (null != partMID) {
				type_paranew("PanaMC.MCReceiveMaterialID_xpath", partMID,"MaterialID Text Field");
			}
			type_paranew("PanaMC.MCReceivePartNumber_xpath", partNumber.substring(0, partNumber.length() - 1),"Part Number Text Field");
			send2KeyEventsToElement("PanaMC.MCReceivePartNumber_xpath", Keys.ARROW_DOWN, Keys.ENTER);
			DymamicWait();
			type_paranew("PanaCM.ReceiveQuntity_xpath", partQuantity,"Quantity Text Field");
			if (partLocation == 1) {
				// Automated Optical Inspection in Quality
				click_paranew("PanaMC.MCReceivePartReceivedLocationList_xpath","Received Location List Box");
				click_paranew("PanaMC.MCReceivePartReceivedLocationListItemAOIQ_xpath","Received Location List Item");
			} else if (partLocation == 2) {
				// Axial Insertion
				click_paranew("PanaMC.MCReceivePartReceivedLocationList_xpath","Received Location List Box");
				click_paranew("PanaMC.MCReceivePartReceivedLocationListItemAI_xpath","Received Location List Item");
			}
			if (!isPrintCheck) {
				click_paranew("PanaMC.MCReceivePrintCheckbox_xpath","Print Check Box");
			}
			click_paranew("PanaCM.ReceiveSaveBttn_xpath","Save Button");
			DymamicWait();
			click_paranew("PanaMC.ActionClose_xpath","Close Icon");
			sleepThread1Sec();
			click_paranew("PanaMC.MCReceiveBackToListButton_xpath","Back To List Button");
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}
	
	
	

	public void createNewLabelConfigFromDashBoard(String labelConfigName) {
		try {
			test.log(Status.INFO, "createNewLabelConfigFromDashBoard(String labelConfigName)");

			String templateFile = "box_tote_template6.btw";

			click_para("PanaMC.MCMCConfigButton_xpath");
			click_para("PanaMC.MCPartConfigOption_xpath");
			click_para("PanaMC.MCPartConfigLabelConfigTab_xpath");
			click_para("PanaMC.MCPartConfigLabelConfigTabCreateNewButton_xpath");
			click_para("PanaMC.MCPartConfigLabelConfigTabNameTextBox_xpath");
			type_para("PanaMC.MCPartConfigLabelConfigTabNameTextBox_xpath", labelConfigName);
			fileUploadWithXpath("PanaMC.MCPartConfigLabelConfigTabFileUploadButton_xpath", templateFile);
			sleepThread5Sec();
			click_para("PanaMC.MCPartConfigLabelConfigTabSaveButton_xpath");
			DymamicWait();
			click_para("PanaMC.MCDisplayMessageCloseIcon_xpath");
			sleepThread1Sec();
			click_para("PanaMC.MCMCHomeButton_xpath");
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void createNewPSDDataFromDashBoard(String partPSD) {
		try {
			test.log(Status.INFO, "createNewPSDDataFromDashBoard(String partPSD)");

			click_para("PanaMC.MCMCConfigButton_xpath");
			click_para("PanaMC.MCPartConfigOption_xpath");
			click_para("PanaMC.MCPartConfigPSDTab_xpath");
			click_para("PanaMC.MCPartConfigPSDTabCreateNewButton_xpath");
			click_para("PanaMC.MCPartConfigPSDTabNameTextBox_xpath");
			type_para("PanaMC.MCPartConfigPSDTabNameTextBox_xpath", partPSD);
			click_para("PanaMC.MCPartConfigPSDTabDataTypeList_xpath");
			click_para("PanaMC.MCPartConfigPSDTabDataTypeListItem1_xpath"); // fix data type is 1
			click_para("PanaMC.MCPartConfigPSDTabSaveButton_xpath");
			DymamicWait();
			click_para("PanaMC.MCDisplayMessageCloseIcon_xpath");
			sleepThread1Sec();
			click_para("PanaMC.MCMCHomeButton_xpath");
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void createNewMCPartNumberFromDashBoard(String partNumber, String partDesc, int partLocation) {
		try {
			test.log(Status.INFO,
					"createNewMCPartNumberFromDashBoard(String partNumber, String partDesc, int partLocation)");

			click_para("PanaMC.MCMCConfigButton_xpath");
			click_para("PanaMC.MCPartConfigOption_xpath");
			click_para("PanaMC.MCCreateNewPartButton_xpath");
			click_para("PanaMC.MCNewPartNumberTextBox_xpath");
			type_para("PanaMC.MCNewPartNumberTextBox_xpath", partNumber);
			click_para("PanaMC.MCNewPartDescTextBox_xpath");
			type_para("PanaMC.MCNewPartDescTextBox_xpath", partDesc);
			scrollingToElementofAPage("PanaMC.MCPartMCCheckBox_xpath");
			click_para("PanaMC.MCPartMCCheckBox_xpath");
			if (partLocation == 1) {
				// Automated Optical Inspection in Quality
				click_para("PanaMC.MCDefaultReceivingLocationList_xpath");
				click_para("PanaMC.MCReceivingLocationAOIQOption_xpath");
			} else if (partLocation == 2) {
				// Axial Insertion
				click_para("PanaMC.MCDefaultReceivingLocationList_xpath");
				click_para("PanaMC.MCReceivingLocationAIOption_xpath");
			}
			scrollingToBottomofAPage();
			click_para("PanaMC.MCNewPartSaveButton_xpath");
			DymamicWait();
			click_para("PanaMC.MCDisplayMessageCloseIcon_xpath");
			sleepThread1Sec();
			click_para("PanaMC.MCMCHomeButton_xpath");
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void createNewMCPartNumberWithPSDFromDashBoard(String partNumber, String partDesc, int partLocation,
			String partPSD) {
		try {
			test.log(Status.INFO,
					"createNewMCPartNumberWithPSDFromDashBoard(String partNumber, String partDesc, int partLocation, String partPSD)");

			click_para("PanaMC.MCMCConfigButton_xpath");
			click_para("PanaMC.MCPartConfigOption_xpath");
			click_para("PanaMC.MCCreateNewPartButton_xpath");
			click_para("PanaMC.MCNewPartNumberTextBox_xpath");
			type_para("PanaMC.MCNewPartNumberTextBox_xpath", partNumber);
			click_para("PanaMC.MCNewPartDescTextBox_xpath");
			type_para("PanaMC.MCNewPartDescTextBox_xpath", partDesc);
			scrollingToElementofAPage("PanaMC.MCPartMCCheckBox_xpath");
			click_para("PanaMC.MCPartMCCheckBox_xpath");
			if (partLocation == 1) {
				// Automated Optical Inspection in Quality
				click_para("PanaMC.MCDefaultReceivingLocationList_xpath");
				click_para("PanaMC.MCReceivingLocationAOIQOption_xpath");
			} else if (partLocation == 2) {
				// Axial Insertion
				click_para("PanaMC.MCDefaultReceivingLocationList_xpath");
				click_para("PanaMC.MCReceivingLocationAIOption_xpath");
			}
			scrollingToElementofAPage("PanaMC.MCPartPSDTraceData_xpath");
			if (partPSD.contains("1")) {
				click_para("PanaMC.MCPartPSDTraceDataCheckBoxPSDData1_xpath");
			}
			if (partPSD.contains("2")) {
				click_para("PanaMC.MCPartPSDTraceDataCheckBoxPSDData2_xpath");
			}
			scrollingToBottomofAPage();
			click_para("PanaMC.MCNewPartSaveButton_xpath");
			DymamicWait();
			click_para("PanaMC.MCDisplayMessageCloseIcon_xpath");
			sleepThread1Sec();
			click_para("PanaMC.MCMCHomeButton_xpath");
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void createNewMCPartNumberWithLabelConfigFromDashBoard(String partNumber, String partDesc, int partLocation,
			String partLabelConfig) {
		try {
			test.log(Status.INFO,
					"createNewMCPartNumberWithLabelConfigFromDashBoard(String partNumber, String partDesc, int partLocation, String partLabelConfig)");

			click_para("PanaMC.MCMCConfigButton_xpath");
			click_para("PanaMC.MCPartConfigOption_xpath");
			click_para("PanaMC.MCCreateNewPartButton_xpath");
			click_para("PanaMC.MCNewPartNumberTextBox_xpath");
			type_para("PanaMC.MCNewPartNumberTextBox_xpath", partNumber);
			click_para("PanaMC.MCNewPartDescTextBox_xpath");
			type_para("PanaMC.MCNewPartDescTextBox_xpath", partDesc);
			scrollingToElementofAPage("PanaMC.MCPartMCCheckBox_xpath");
			click_para("PanaMC.MCPartMCCheckBox_xpath");
			sleepThread1Sec();
			click_para("PanaMC.MCPartConfigLabelConfigList_xpath");
			typeAndEnter_para("PanaMC.MCPartConfigLabelConfigListTextBox_xpath", partLabelConfig);			
			if (partLocation == 1) {
				// Automated Optical Inspection in Quality
				click_para("PanaMC.MCDefaultReceivingLocationList_xpath");
				click_para("PanaMC.MCReceivingLocationAOIQOption_xpath");
			} else if (partLocation == 2) {
				// Axial Insertion
				click_para("PanaMC.MCDefaultReceivingLocationList_xpath");
				click_para("PanaMC.MCReceivingLocationAIOption_xpath");
			}
			scrollingToBottomofAPage();
			click_para("PanaMC.MCNewPartSaveButton_xpath");
			DymamicWait();
			click_para("PanaMC.MCDisplayMessageCloseIcon_xpath");
			sleepThread1Sec();
			click_para("PanaMC.MCMCHomeButton_xpath");
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void createNewNonMCPartNumberFromDashBoard(String partNumber, String partDesc, int partLocation) {
		try {
			test.log(Status.INFO,
					"createNewNonMCPartNumberFromDashBoard(String partNumber, String partDesc, int partLocation)");

			click_para("PanaMC.MCMCConfigButton_xpath");
			click_para("PanaMC.MCPartConfigOption_xpath");
			click_para("PanaMC.MCCreateNewPartButton_xpath");
			click_para("PanaMC.MCNewPartNumberTextBox_xpath");
			type_para("PanaMC.MCNewPartNumberTextBox_xpath", partNumber);
			click_para("PanaMC.MCNewPartDescTextBox_xpath");
			type_para("PanaMC.MCNewPartDescTextBox_xpath", partDesc);
			scrollingToElementofAPage("PanaMC.MCPartMCCheckBox_xpath");
			if (partLocation == 1) {
				// Automated Optical Inspection in Quality
				click_para("PanaMC.MCDefaultReceivingLocationList_xpath");
				click_para("PanaMC.MCReceivingLocationAOIQOption_xpath");
			} else if (partLocation == 2) {
				// Axial Insertion
				click_para("PanaMC.MCDefaultReceivingLocationList_xpath");
				click_para("PanaMC.MCReceivingLocationAIOption_xpath");
			}
			scrollingToBottomofAPage();
			click_para("PanaMC.MCNewPartSaveButton_xpath");
			DymamicWait();
			click_para("PanaMC.MCDisplayMessageCloseIcon_xpath");
			sleepThread1Sec();
			click_para("PanaMC.MCMCHomeButton_xpath");
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void createNewLocationFromDashBoard(String locationName, boolean isRestrict) {
		try {
			test.log(Status.INFO, "createNewLocationFromDashBoard(String locationName, boolean isRestrict)");

			click_para("PanaMC.MCMCConfigButton_xpath");
			click_para("PanaMC.MCPartConfigOption_xpath");
			click_para("PanaMC.MCPartConfigLocationTab_xpath");
			click_para("PanaMC.MCPartConfigLocationTabCreateNewButton_xpath");
			type_para("PanaMC.MCLocationTabLocationNameTextBox_xpath", locationName);
			click_para("PanaMC.MCLocationTabLocationTypeList_xpath");
			click_para("PanaMC.MCLocationTabLocationTypeItem5_xpath"); // fix location type no 5
			if (isRestrict) {
				click_para("PanaMC.MCLocationTabRestrictLocationCheckBox_xpath");
			}
			click_para("PanaMC.MCLocationTabSaveButton_xpath");
			DymamicWait();
			click_para("PanaMC.MCDisplayMessageCloseIcon_xpath");
			sleepThread1Sec();
			click_para("PanaMC.MCMCHomeButton_xpath");
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}

	}

	public void receiveMCPartNumberFromDashBoard(String partNumber, String partMID, String partQuantity,
			int partLocation, boolean isPrintCheck) {
		try {
			test.log(Status.INFO,
					"receiveMCPartNumberFromDashBoard(String partNumber, String partMID, String partQuantity,\r\n"
							+ "			int partLocation, boolean isPrintCheck)");
			click_para("PanaMC.MCReceiveButton_xpath");
			if (null != partMID) {
				type_para("PanaMC.MCReceiveMaterialID_xpath", partMID);
			}
			type_para("PanaMC.MCReceivePartNumber_xpath", partNumber.substring(0, partNumber.length() - 1));
			send2KeyEventsToElement("PanaMC.MCReceivePartNumber_xpath", Keys.ARROW_DOWN, Keys.ENTER);
			DymamicWait();
			type_para("PanaCM.ReceiveQuntity_xpath", partQuantity);
			if (partLocation == 1) {
				// Automated Optical Inspection in Quality
				click_para("PanaMC.MCReceivePartReceivedLocationList_xpath");
				sleepThread1Sec();
				click_para("PanaMC.MCReceivePartReceivedLocationListItemAOIQ_xpath");
			} else if (partLocation == 2) {
				// Axial Insertion
				click_para("PanaMC.MCReceivePartReceivedLocationList_xpath");
				sleepThread1Sec();
				click_para("PanaMC.MCReceivePartReceivedLocationListItemAI_xpath");
			}
			if (!isPrintCheck) {
				click_para("PanaMC.MCReceivePrintCheckbox_xpath");
			}
			click_para("PanaCM.ReceiveSaveBttn_xpath");
			DymamicWait();
			click_para("PanaMC.ActionClose_xpath");
			sleepThread1Sec();
			click_para("PanaMC.MCReceiveBackToListButton_xpath");
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void receiveMCPartNumberWithPSDFromDashBoard(String partNumber, String partMID, String partQuantity,
			int partLocation, boolean isPrintCheck, String partPSDData) {
		try {
			test.log(Status.INFO,
					"receiveMCPartNumberFromDashBoard(String partNumber, String partMID, String partQuantity,\r\n"
							+ "			int partLocation, boolean isPrintCheck)");
			click_para("PanaMC.MCReceiveButton_xpath");
			if (null != partMID) {
				type_para("PanaMC.MCReceiveMaterialID_xpath", partMID);
			}
			type_para("PanaMC.MCReceivePartNumber_xpath", partNumber.substring(0, partNumber.length() - 1));
			send2KeyEventsToElement("PanaMC.MCReceivePartNumber_xpath", Keys.ARROW_DOWN, Keys.ENTER);
			DymamicWait();
			type_para("PanaCM.ReceiveQuntity_xpath", partQuantity);
			if (partLocation == 1) {
				// Automated Optical Inspection in Quality
				click_para("PanaMC.MCReceivePartReceivedLocationList_xpath");
				click_para("PanaMC.MCReceivePartReceivedLocationListItemAOIQ_xpath");
			} else if (partLocation == 2) {
				// Axial Insertion
				click_para("PanaMC.MCReceivePartReceivedLocationList_xpath");
				click_para("PanaMC.MCReceivePartReceivedLocationListItemAI_xpath");
			}
			if (!isPrintCheck) {
				click_para("PanaMC.MCReceivePrintCheckbox_xpath");
			}
			type_para("PanaMC.MCReceiveTraceData1_xpath", partPSDData);
			click_para("PanaCM.ReceiveSaveBttn_xpath");
			DymamicWait();
			click_para("PanaMC.ActionClose_xpath");
			sleepThread1Sec();
			click_para("PanaMC.MCReceiveBackToListButton_xpath");
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void moveMCPartNumber1ToNewLocationFromDashBoard(String newLocation) {
		try {
			test.log(Status.INFO, "moveMCPartNumber1ToNewLocationFromDashBoard(String newLocation)");

			click_para("PanaMC.MCPartNumber1AddCartButton_xpath");
			click_para("PanaMC.MCViewCardButton_xpath");
			click_para("PanaMC.MCViewCardPartNumber1_xpath");
			click_para("PanaMC.MCViewCardMoveButton_xpath");
			click_para("PanaMC.MCViewCardMoveWindowLocationTextBox_xpath");
			type_para("PanaMC.MCViewCardMoveWindowLocationTextBox_xpath",
					newLocation.substring(0, newLocation.length() - 1));
			send2KeyEventsToElement("PanaMC.MCViewCardMoveWindowLocationTextBox_xpath", Keys.ARROW_DOWN, Keys.ENTER);
			sleepThread1Sec();
			//click_para("PanaMC.MCViewCardMoveWindowMoveButton_xpath");
			DymamicWait();
			click_para("PanaMC.MCDisplayMessageCloseIcon_xpath");
			sleepThread1Sec();
			click_para("PanaMC.MCMCHomeButton_xpath");
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void spliceMCPartNumber1WithPartNumber2FromDashboard() {
		try {
			test.log(Status.INFO, "spliceMCPartNumber1WithPartNumber2FromDashboard()");
			String partNumber2MID = driver.findElement(By.xpath(prop.getProperty("PanaMC.MCPartNumber2MID_xpath")))
					.getText();

			click_para("PanaMC.MCPartNumber1_xpath");
			click_para("PanaMC.MCSpliceButton_xpath");
			click_para("PanaMC.MCSpliceWindowTextBox_xpath");
			type_para("PanaMC.MCSpliceWindowTextBox_xpath", partNumber2MID);
			click_para("PanaMC.MCSpliceWindowSpliceButton_xpath");
			DymamicWait();
			click_para("PanaMC.MCSpliceDialogCloseButton_xpath");
			sleepThread1Sec();
			click_para("PanaMC.MCMCHomeButton_xpath");
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void spliceMCPartNumber2WithPartNumber1FromDashboard() {
		try {
			test.log(Status.INFO, "spliceMCPartNumber2WithPartNumber1FromDashboard()");
			String partNumber1MID = driver.findElement(By.xpath(prop.getProperty("PanaMC.MCPartNumber1MID_xpath")))
					.getText();

			click_para("PanaMC.MCPartNumber2_xpath");
			click_para("PanaMC.MCSpliceButton_xpath");
			click_para("PanaMC.MCSpliceWindowTextBox_xpath");
			type_para("PanaMC.MCSpliceWindowTextBox_xpath", partNumber1MID);
			click_para("PanaMC.MCSpliceWindowSpliceButton_xpath");
			DymamicWait();
			click_para("PanaMC.MCSpliceDialogCloseButton_xpath");
			sleepThread1Sec();
			click_para("PanaMC.MCMCHomeButton_xpath");
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void spliceMCPartNumber3WithPartNumber2FromDashboard() {
		try {
			test.log(Status.INFO, "spliceMCPartNumber3WithPartNumber2FromDashboard()");
			String partNumber2MID = driver.findElement(By.xpath(prop.getProperty("PanaMC.MCPartNumber2MID_xpath")))
					.getText();

			click_para("PanaMC.MCPartNumber3_xpath");
			click_para("PanaMC.MCSpliceButton_xpath");
			click_para("PanaMC.MCSpliceWindowTextBox_xpath");
			type_para("PanaMC.MCSpliceWindowTextBox_xpath", partNumber2MID);
			click_para("PanaMC.MCSpliceWindowSpliceButton_xpath");
			DymamicWait();
			click_para("PanaMC.MCSpliceDialogCloseButton_xpath");
			sleepThread1Sec();
			click_para("PanaMC.MCMCHomeButton_xpath");
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void splice2DiffrentMCPartNumbersFromDashboard() {
		try {
			test.log(Status.INFO, "splice2DiffrentMCPartNumbersFromDashboard()");
			String partNumber2MID = driver.findElement(By.xpath(prop.getProperty("PanaMC.MCPartNumber2MID_xpath")))
					.getText();

			click_para("PanaMC.MCPartNumber1_xpath");
			click_para("PanaMC.MCSpliceButton_xpath");
			click_para("PanaMC.MCSpliceWindowTextBox_xpath");
			type_para("PanaMC.MCSpliceWindowTextBox_xpath", partNumber2MID);
			click_para("PanaMC.MCSpliceWindowSpliceButton_xpath");
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void changeStateMCPartNumber2FromDashboard(String newState) {
		try {
			test.log(Status.INFO, "changeStateMCPartNumber2FromDashboard(): " + newState);
			String newLocation = "Axial Insertion";

			click_para("PanaMC.MCPartNumber2AddCartButton_xpath");
			click_para("PanaMC.MCViewCardButton_xpath");
			click_para("PanaMC.MCViewCardPartNumber1_xpath");
			if (newState.equalsIgnoreCase("Hold")) {
				// change to Hold state
				click_para("PanaMC.MCViewCardHoldButton_xpath");
				click_para("PanaMC.MCViewCardHoldWindowReasonCodeList_xpath");
				click_para("PanaMC.MCViewCardHoldWindowReasonCodeItem1_xpath");
				click_para("PanaMC.MCViewCardHoldWindowHoldButton_xpath");
			} else if (newState.equalsIgnoreCase("Scrap")) {
				// change to Scrap state
				click_para("PanaMC.MCViewCardScrapButton_xpath");
				click_para("PanaMC.MCViewCardScrapWindowReasonCodeList_xpath");
				click_para("PanaMC.MCViewCardScrapWindowReasonCodeItem1_xpath");
				click_para("PanaMC.MCViewCardScrapWindowLocationTextBox_xpath");
				type_para("PanaMC.MCViewCardScrapWindowLocationTextBox_xpath",
						newLocation.substring(0, newLocation.length() - 1));
				send2KeyEventsToElement("PanaMC.MCViewCardScrapWindowLocationTextBox_xpath", Keys.ARROW_DOWN,
						Keys.ENTER);
				DymamicWait();
				click_para("PanaMC.MCViewCardScrapWindowScrapButton_xpath");
			} else {
				reportFailure("Invalid state input: " + newState);
			}
			DymamicWait();
			click_para("PanaMC.MCDisplayMessageCloseIcon_xpath");
			sleepThread1Sec();
			click_para("PanaMC.MCMCHomeButton_xpath");
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

	public void changeStateMCPartNumber2WithRestrictLocationFromDashboard(String newState, String restrictLocation) {
		try {
			test.log(Status.INFO, "changeStateMCPartNumber2FromDashboard(): " + newState);

			click_para("PanaMC.MCPartNumber2AddCartButton_xpath");
			click_para("PanaMC.MCViewCardButton_xpath");
			click_para("PanaMC.MCViewCardPartNumber1_xpath");
			if (newState.equalsIgnoreCase("Hold")) {
				// change to Hold state
				click_para("PanaMC.MCViewCardHoldButton_xpath");
				click_para("PanaMC.MCViewCardHoldWindowReasonCodeList_xpath");
				click_para("PanaMC.MCViewCardHoldWindowReasonCodeItem1_xpath");
				click_para("PanaMC.MCViewCardHoldWindowHoldButton_xpath");
			} else if (newState.equalsIgnoreCase("Scrap")) {
				// change to Scrap state
				click_para("PanaMC.MCViewCardScrapButton_xpath");
				click_para("PanaMC.MCViewCardScrapWindowReasonCodeList_xpath");
				click_para("PanaMC.MCViewCardScrapWindowReasonCodeItem1_xpath");
				click_para("PanaMC.MCViewCardScrapWindowLocationTextBox_xpath");
				type_para("PanaMC.MCViewCardScrapWindowLocationTextBox_xpath",
						restrictLocation.substring(0, restrictLocation.length() - 1));
				send2KeyEventsToElement("PanaMC.MCViewCardScrapWindowLocationTextBox_xpath", Keys.ARROW_DOWN,
						Keys.ENTER);
				DymamicWait();
				click_para("PanaMC.MCViewCardScrapWindowScrapButton_xpath");
			} else {
				reportFailure("Invalid state input: " + newState);
			}
			DymamicWait();
			click_para("PanaMC.MCDisplayMessageCloseIcon_xpath");
			sleepThread1Sec();
			click_para("PanaMC.MCMCHomeButton_xpath");
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}
	
	
	/*Created New Method specific to MC Config*/
	
	public void changeSystemErrorMessageTypeConfig() {
		try {
			String newType = data.get(dataKey);
			test.log(Status.INFO, "changeSystemErrorMessageType(): " + newType);

			String txtUserInterface = "User Interface";
			String txtWebErrorMessageType = "Web: Error Message Type";

			click_paranew("PanaMC.MCMCConfigButton_xpath","MC Config Option");
			click_paranew("PanaMC.MCSystemConfigurationOption_xpath","System Configuration Option Under MC Config");
			click_paranew("PanaMC.MCApplicationSettingsCategoryList_xpath","Application Settings Category Drop Down List");
			typeAndEnter_paranew("PanaMC.MCApplicationSettingsCategoryTextBox_xpath", txtUserInterface,"Category Text Field");
			click_paranew("PanaMC.MCApplicationSettingsApplicationSettingList_xpath","Application Setting Drop Down List");
			typeAndEnter_paranew("PanaMC.MCApplicationSettingsApplicationSettingTextBox_xpath" ,txtWebErrorMessageType,"Application Setiing Text Field");

			// verify current value of error type is expected type or not
			String currentType = driver
					.findElement(
							By.xpath(prop.getProperty("PanaMC.MCApplicationSettingsApplicationSettingValueList_xpath")))
					.getText();
			if (!currentType.equalsIgnoreCase(newType)) {
				// current type is not expected type, change to expected type
				test.log(Status.INFO, "Error message type is NOT Popup, proceed to change");
				click_paranew("PanaMC.MCApplicationSettingsApplicationSettingValueList_xpath","Application Setting Value Drop Down List");
				typeAndEnter_paranew("PanaMC.MCApplicationSettingsApplicationSettingValueTextBox2_xpath", newType,"Application Setting Value Text Field");
				click_paranew("PanaMC.MCApplicationSettingsButtonSave_xpath","Save Button");

				// wait 3 sec
				sleepThread3Sec();
			} else {
				// current type is already expected type, no job here
				test.log(Status.INFO, "Error message type is already: " + newType);
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}
	
	public void preSetup_SpliceButton() {
		try {
			test.log(Status.INFO, "Adding new part for splicing");
			String currentTime = new SimpleDateFormat("yyyymmddhhmmss").format(Calendar.getInstance().getTime());

			// create new part number
			String partNumber = "MCID0001PartNumber " + currentTime;
			String partDesc = "New part for splicing";
			String partQuantity1 = "50";

			createNewMCPartNumberFromDashBoard(partNumber, partDesc, 1);
			DynamicWait();
			receiveMCPartNumberFromDashBoard(partNumber, null, partQuantity1, 0, false);
		} catch (Exception e) {
			e.printStackTrace();
			reportFailure("Unexpected exception is thrown: " + e);
		}
	}

}
