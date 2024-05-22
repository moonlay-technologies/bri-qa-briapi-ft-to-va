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
import java.security.MessageDigest
import java.nio.charset.StandardCharsets
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;


'Intrabank Account'

'Generate External Id'
def externalId = 10000 + new Random().nextInt(99999-10000)
WS.comment("Random 5-digit integer: " + externalId)

'Generate TimeStamp'
def timeStamp = new Date().format("yyyy-MM-dd'T'HH:mm:ss.SSSZ").replace('0700','07:00')

def trxDateTime = new Date().format("yyyy-MM-dd'T'HH:mm:ssZ").replace('0700','07:00')
WS.comment('ini transactionDate ' + trxDateTime )


"Proses Encrypt Request Body"
PaymentIntrabank2 = findTestObject('Object Repository/FT to VA/Payment Intrabank', [
	('externalId') : externalId,
	('signature') : '',
	('timeStamp') : timeStamp,
	('token') : GlobalVariable.Token,
	('partnerServiceId') : partnerServiceId,
	('customerNo') : customerNo,
	('virtualAccountNo') : virtualAccountNo,
	('virtualAccountName') : virtualAccountName,
	('sourceAccountNo') : sourceAccountNo,
	('partnerReferenceNo') : partnerReferenceNo,
	('trxDateTime') : trxDateTime,
	('value') : value,
	('currency') : currency
	])

request_body2 = (PaymentIntrabank2.getHttpBody() as String)
endpoint_path = '/payment/snap/prod/v1.1/transfer-va/payment-intrabank'


'Generate Signature'
signature = CustomKeywords.'utils.Generate.signature'(request_body2,endpoint_path,timeStamp)

PaymentIntrabank = findTestObject('Object Repository/FT to VA/Payment Intrabank',[
	('externalId') : externalId,
	('signature') : signature,
	('timeStamp') : timeStamp,
	('token') : GlobalVariable.Token,
	('partnerServiceId') : partnerServiceId,
	('customerNo') : customerNo,
	('virtualAccountNo') : virtualAccountNo,
	('virtualAccountName') : virtualAccountName,
	('sourceAccountNo') : sourceAccountNo,
	('partnerReferenceNo') : partnerReferenceNo,
	('trxDateTime') : trxDateTime,
	('value') : value,
	('currency') : currency
	])

'Get Body request'

request_body = (PaymentIntrabank.getHttpBody() as String)
WS.comment('request body ' + request_body)

'Get Respon Body'
response = WS.sendRequest(PaymentIntrabank)
WS.comment('response body ' + response.getResponseText())

JsonSlurper slurper = new JsonSlurper()
Map parsedJson = slurper.parseText(response.getResponseText())


//	statusCode = response.getStatusCode()
//	responseMessage = parsedJson.responseMessage
//	accountNo = parsedJson.accountNo
//	responseCode = parsedJson.responseCode
//	
//	if (statusCode == 200) {
//		WebUI.verifyMatch(accountNo, accountNumber, false, FailureHandling.CONTINUE_ON_FAILURE)
//		}
//	WebUI.verifyMatch(statusCode.toString(), statusCodeList, false, FailureHandling.CONTINUE_ON_FAILURE)
//	WebUI.verifyMatch(responseMessage ,responseMessageList, false, FailureHandling.CONTINUE_ON_FAILURE)
//	WebUI.verifyMatch(responseCode ,responseCodeList, false, FailureHandling.CONTINUE_ON_FAILURE)
	
/* statusCodeList = di ambil dari excell sebagai data files sebagai parameter verifikasi status code pada api
   accountNumber = di ambil dari excell sebagai data files sebagai parameter verifikasi status code pada api
 */
	

	/* statusCodeList = di ambil dari excell sebagai data files sebagai parameter verifikasi status code pada api
	 responseMessageList = di ambil dari excell sebagai data files sebagai parameter verifikasi status response Message pada api
     responseCodeList = di ambil dari excell sebagai data files sebagai parameter verifikasi status response Code pada api
   */


