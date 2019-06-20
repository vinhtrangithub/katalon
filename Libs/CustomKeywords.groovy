
/**
 * This class is generated automatically by Katalon Studio and should not be modified or deleted.
 */

import com.kms.katalon.core.testobject.TestObject

import java.lang.String


def static "training.webui.util.drapAndDrop"(
    	TestObject source	
     , 	TestObject destination	) {
    (new training.webui.util()).drapAndDrop(
        	source
         , 	destination)
}

def static "training.webui.util.moveSlider"(
    	TestObject source	) {
    (new training.webui.util()).moveSlider(
        	source)
}

def static "training.webui.util.isFileDownloaded"(
    	String downloadPath	
     , 	String fileName	) {
    (new training.webui.util()).isFileDownloaded(
        	downloadPath
         , 	fileName)
}

def static "training.webui.util.getValueWithRowCol"(
    	TestObject to	
     , 	String row	
     , 	String col	) {
    (new training.webui.util()).getValueWithRowCol(
        	to
         , 	row
         , 	col)
}

def static "training.webui.util.getHeaderColValue"(
    	TestObject to	
     , 	String col	) {
    (new training.webui.util()).getHeaderColValue(
        	to
         , 	col)
}

def static "training.webui.util.clickOnSpecHeaderCol"(
    	TestObject to	
     , 	String colIndex	) {
    (new training.webui.util()).clickOnSpecHeaderCol(
        	to
         , 	colIndex)
}

def static "training.webui.util.verifySpecificColSorted"(
    	TestObject to	
     , 	String colIndex	) {
    (new training.webui.util()).verifySpecificColSorted(
        	to
         , 	colIndex)
}
