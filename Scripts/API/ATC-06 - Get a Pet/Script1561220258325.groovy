import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.RequestObject as RequestObject
import com.kms.katalon.core.testobject.ResponseObject as ResponseObject
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import groovy.json.JsonSlurper as JsonSlurper

def jsonSlurper = new JsonSlurper()

ResponseObject ro = WS.sendRequest(findTestObject('API/ATC-06', [('petId') : petID]))

String s = ro.getStatusCode()

def json_issues_by_jql = jsonSlurper.parseText(ro.getResponseText())

WS.verifyResponseStatusCode(ro, 200)

println(json_issues_by_jql.id)

if (json_issues_by_jql.id != '2') {
    println('The ID is not match with expected')
}

println(json_issues_by_jql.name)

if (json_issues_by_jql.name != 'name2') {
    println('The name is not match with expected')
}

