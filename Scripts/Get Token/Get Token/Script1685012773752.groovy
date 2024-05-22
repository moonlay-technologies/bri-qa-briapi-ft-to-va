import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import groovy.json.JsonSlurper as JsonSlurper

'Get Token Process'

'Generate Time Stamp'

def timeStamp = new Date().format("yyyy-MM-dd'T'HH:mm:ss.SSSZ").replace('0700','07:00')
WS.comment ('ini tanggal' + timeStamp)

GlobalVariable.timeStamp = timeStamp

'Generate SignatureHex'

def signatureHex = CustomKeywords.'utils.Generate.signSHA256RSA'()
WS.comment('signatureHEX' + signatureHex)

getToken = findTestObject('Object Repository/Get Token/getToken',[
	('signatureHex') : signatureHex,
	('clientID') : GlobalVariable.clientId,
	('timeStamp') : timeStamp
	])


'Get Body request'
request_body = (getToken.getHttpBody() as String)
WS.comment('request body ' + request_body)

'Get Respon Body'
response = WS.sendRequest(getToken)
WS.comment('response body ' + response.getResponseText())

JsonSlurper slurper = new JsonSlurper()
Map parsedJson = slurper.parseText(response.getResponseText())

GlobalVariable.Token = parsedJson.accessToken

