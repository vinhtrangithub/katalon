import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.Alert
import org.openqa.selenium.WebDriver
import org.testng.Assert as Assert
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.annotation.SetUp as SetUp
import com.kms.katalon.core.annotation.TearDown as TearDown

WebUI.callTestCase(findTestCase('WebUI/common/WTC_NavigateToPage'), [('lnk_MenuLink') : lnk_MenuLink, ('lbl_Title') : lbl_Title], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('WebUI/JavaScriptPage/btn_JSAlert'))

Thread.sleep(500)

WebUI.acceptAlert()

Thread.sleep(500)

WebUI.verifyElementText(findTestObject('WebUI/JavaScriptPage/lbl_Result'), 'You successfuly clicked an alert')

WebUI.click(findTestObject('WebUI/JavaScriptPage/btn_JSConfirm'))

Thread.sleep(500)

WebUI.dismissAlert()
Thread.sleep(500)

WebUI.verifyElementText(findTestObject('WebUI/JavaScriptPage/lbl_Result'), 'You clicked: Cancel')

WebUI.click(findTestObject('WebUI/JavaScriptPage/btn_JSPrompt'))

Thread.sleep(500)

Alert alert1 = DriverFactory.getWebDriver().switchTo().alert()

println alert1.getText()

alert1.sendKeys('Hello')

Thread.sleep(1000)

WebUI.acceptAlert()

WebUI.verifyElementText(findTestObject('WebUI/JavaScriptPage/lbl_Result'), 'You entered: Hello')

@SetUp
def setup() {
    '1. Navigate to the URL.'
    WebUI.openBrowser(GlobalVariable.url)

    WebUI.maximizeWindow()
}

@TearDown
def tearDown() {
    'Close browser'
    WebUI.closeBrowser()
}

