import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
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

//Get text before drap and drop
String lblA_before = WebUI.getText(findTestObject('WebUI/DrapDropPage/lbl_ColumnA'))

String lblB_before = WebUI.getText(findTestObject('WebUI/DrapDropPage/lbl_ColumnB'))

//Cus.dragAndDropToObject(findTestObject('WebUI/DrapDropPage/btn_ColumnA'), findTestObject('WebUI/DrapDropPage/btn_ColumnB'))
CustomKeywords.'training.webui.util.drapAndDrop'(findTestObject('WebUI/DrapDropPage/btn_ColumnA'), findTestObject('WebUI/DrapDropPage/btn_ColumnB'))

//Get text after drop and drop
String lblA_after = WebUI.getText(findTestObject('WebUI/DrapDropPage/lbl_ColumnA'))

String lblB_after = WebUI.getText(findTestObject('WebUI/DrapDropPage/lbl_ColumnB'))

if (lblA_before == lblA_after) {
	Assert.assertTrue(false, 'The column A is not drap and drop to column B.')
}

if (lblB_before == lblB_after) {
	Assert.assertTrue(false, 'The column B is not drap and drop to column A.')
}

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

