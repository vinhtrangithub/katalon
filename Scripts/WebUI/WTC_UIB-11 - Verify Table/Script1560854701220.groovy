import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.Keys as Keys
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
import org.openqa.selenium.support.Color;

WebUI.callTestCase(findTestCase('WebUI/common/WTC_NavigateToPage'), [('lnk_MenuLink') : lnk_MenuLink, ('lbl_Title') : lbl_Title], 
    FailureHandling.STOP_ON_FAILURE)

String email = CustomKeywords.'training.webui.util.getHeaderColValue'(findTestObject('WebUI/TablePage/tbl_Table1'), '3')

if (email != 'Email'){
	println 'The third column value is not as expected'
}

String name = CustomKeywords.'training.webui.util.getValueWithRowCol'(findTestObject('WebUI/TablePage/tbl_Table1'), '3', '2')
if (name != 'Json'){
	println 'The value of cell [3,2] does not as expected'
}

String price = CustomKeywords.'training.webui.util.getValueWithRowCol'(findTestObject('WebUI/TablePage/tbl_Table1'), '2', '4')
if (price != '$51.00'){
	println 'The value of cell [2,4] does not as expected'
}

CustomKeywords.'training.webui.util.clickOnSpecHeaderCol'(findTestObject('WebUI/TablePage/tbl_Table2'), '3')

CustomKeywords.'training.webui.util.verifySpecificColSorted'(findTestObject('WebUI/TablePage/tbl_Table2'), '3')

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

