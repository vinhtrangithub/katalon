package training.webui

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.driver.DriverFactory

import java.util.regex.Matcher
import java.util.regex.Pattern

import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Action
import org.openqa.selenium.interactions.Actions

public class util {
	String getJsDndHelper() {
		return '''
		function simulateDragDrop(sourceNode, destinationNode) {
		    var EVENT_TYPES = {
		        DRAG_END: 'dragend',
		        DRAG_START: 'dragstart',
		        DROP: 'drop'
		    }
		
		    function createCustomEvent(type) {
		        var event = new CustomEvent("CustomEvent")
		        event.initCustomEvent(type, true, true, null)
		        event.dataTransfer = {
		            data: {
		            },
		            setData: function(type, val) {
		                this.data[type] = val
		            },
		            getData: function(type) {
		                return this.data[type]
		            }
		        }
		        return event
		    }
		
		    function dispatchEvent(node, type, event) {
		        if (node.dispatchEvent) {
		            return node.dispatchEvent(event)
		        }
		        if (node.fireEvent) {
		            return node.fireEvent("on" + type, event)
		        }
		    }
		
		    var event = createCustomEvent(EVENT_TYPES.DRAG_START)
		    dispatchEvent(sourceNode, EVENT_TYPES.DRAG_START, event)
		
		    var dropEvent = createCustomEvent(EVENT_TYPES.DROP)
		    dropEvent.dataTransfer = event.dataTransfer
		    dispatchEvent(destinationNode, EVENT_TYPES.DROP, dropEvent)
		
		    var dragEndEvent = createCustomEvent(EVENT_TYPES.DRAG_END)
		    dragEndEvent.dataTransfer = event.dataTransfer
		    dispatchEvent(sourceNode, EVENT_TYPES.DRAG_END, dragEndEvent)
		}
		''';
	}
	
	public static WebElement getElementByTestObject(TestObject testObject, int timeout = 5){
		WebElement webElement = WebUiCommonHelper.findWebElement(testObject, timeout)
		return webElement
	}

	public static List<WebElement> getListElementByTestObject(TestObject testObject, int timeout = 5){
		List<WebElement> listWebElements = WebUiCommonHelper.findWebElements(testObject, timeout)
		return listWebElements
	}

	@Keyword
	def drapAndDrop(TestObject source, TestObject destination){
		
		WebElement elementSource = getElementByTestObject(source)
		WebElement elementDestination = getElementByTestObject(destination)
		((JavascriptExecutor) DriverFactory.getWebDriver()).executeScript(getJsDndHelper() + "simulateDragDrop(arguments[0], arguments[1])", elementSource, elementDestination)
		//Actions act=new Actions(DriverFactory.getWebDriver());
		//act.dragAndDrop(elementSource, elementDestination).perform()
		//act.build()
		//act.clickAndHold(elementSource).perform();
		//Thread.sleep(1000);
		//act.moveToElement(elementDestination).perform();
		//Thread.sleep(1000);
		//act.release(elementDestination).perform();
		//Action drapDrop = act.clickAndHold(elementSource).moveToElement(elementDestination).release(elementDestination).build()
		//drapDrop.perform()
		//.moveToElement(elementDestination).release(elementDestination).build().perform()
	}

	@Keyword
	def moveSlider(TestObject source){
		WebElement element = getElementByTestObject(source)
		int width = element.getSize().getWidth()
		println width
	}

	@Keyword
	def isFileDownloaded(String downloadPath, String fileName) {
		File dir = new File(downloadPath);
		File[] dirContents = dir.listFiles();
		boolean iResult = false;

		for (int i = 0; i < dirContents.length; i++) {
			if (dirContents[i].getName().equals(fileName)) {
				// File has been found, it can now be deleted:
				dirContents[i].delete();
				iResult = true;
			}
		}
		if (iResult){
			println "File has been downloaded and deleted."
		}else {
			println "File is not downloaded successful."
		}
	}

	@Keyword
	def getValueWithRowCol(TestObject to, String row, String col) {
		WebElement table = getElementByTestObject(to)
		String value = table.findElement(By.xpath(String.format("./tbody/tr[%s]/td[%s]", row, col))).getText()
		return value
	}

	@Keyword
	def getHeaderColValue(TestObject to, String col) {
		WebElement table = getElementByTestObject(to)
		//String value = table.findElement(String.format("./thead/tr/th[%{s}]", col)).getText()
		String value = table.findElement(By.xpath(String.format("./thead/tr/th[%s]", col))).getText()
		return value
	}

	@Keyword
	def clickOnSpecHeaderCol(TestObject to, String colIndex) {
		WebElement table = getElementByTestObject(to)
		//String value = table.findElement(String.format("./thead/tr/th[%{s}]", col)).getText()
		table.findElement(By.xpath(String.format("./thead/tr/th[%s]", colIndex))).click()
	}

	@Keyword
	def verifySpecificColSorted(TestObject to, String colIndex) {
		WebElement table = getElementByTestObject(to)
		List<WebElement> rows = table.findElements(By.xpath("./tbody/tr"))
		ArrayList<String> rowsdata = new ArrayList<>();
		for(WebElement row : rows){
			rowsdata.add((row.findElement(By.xpath(String.format("./td[%s]", colIndex))).getText()).toString());
		}

		ArrayList<String> sortedList = new ArrayList<>();
		for(String s : rowsdata){
			sortedList.add(s);
		}

		Collections.sort(sortedList);
		if (!sortedList.equals(rowsdata)){
			println "Data doesn't sort as expected."
		} else {
			println "Data is sorted as expected."
		}
	}
}
