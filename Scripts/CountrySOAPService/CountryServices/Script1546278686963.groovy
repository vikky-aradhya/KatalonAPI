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
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

CountryList = WS.sendRequest(findTestObject('CountrySoapServices/CountryListByNames'))

WS.verifyElementText(CountryList, 'ListOfCountryNamesByNameResponse.ListOfCountryNamesByNameResult.tCountryCodeAndName[98].sISOCode', 
    'IN')

String xml1 = CountryList.responseBodyContent

def datavalue = new XmlSlurper().parseText(xml1)

def countryCode = datavalue.ListOfCountryNamesByNameResult.tCountryCodeAndName[98].sISOCode.text()

GlobalVariable.CountryISOCode = countryCode

println('Country ISO code is: ' + GlobalVariable.CountryISOCode)

CountryCurrency = WS.sendRequest(findTestObject('CountrySoapServices/CountryCurrency'))

WS.verifyElementText(CountryCurrency, 'CountryCurrencyResponse.CountryCurrencyResult.sISOCode', 'INR')

WS.verifyElementText(CountryCurrency, 'CountryCurrencyResponse.CountryCurrencyResult.sName', 'Rupees')

