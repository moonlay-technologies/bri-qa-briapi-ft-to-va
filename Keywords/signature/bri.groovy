package signature

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

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
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

import java.security.KeyFactory;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64 as B64;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class bri {
	
	@Keyword
	public static int randomize() {
		return Math.abs( new Random().nextInt() % (99999 - 10000) ) + 10000;
	}
	
	@Keyword
	public static String transferSKNBI2(String timeStamp, String partnerReferenceNo, String value, String currency, String beneficiaryAccountName, String beneficiaryAccountNo, String beneficiaryAddress, String beneficiaryBankCode, String beneficiaryCustomerResidence, String beneficiaryCustomerType, String customerReference, String feeType, String receiverPhone, String remark, String senderCustomerResidence, String senderCustomerType, String senderPhone, String sourceAccountNo, String transactionDate,String deviceId, String channel, String senderName, String senderIdentity, String senderAddress, String corporateType) {
		
		String request_body = '{\n    "partnerReferenceNo": "'+partnerReferenceNo+'",\n    "amount": {\n    "value": "'+value+'",\n    "currency": "'+currency+'"\n    },\n    "beneficiaryAccountName": "'+beneficiaryAccountName+'",\n    "beneficiaryAccountNo": "'+beneficiaryAccountNo+'",\n    "beneficiaryAddress":"'+beneficiaryAddress+'",\n    "beneficiaryBankCode":"'+beneficiaryBankCode+'",\n    "beneficiaryCustomerResidence":"'+beneficiaryCustomerResidence+'",\n    "beneficiaryCustomerType":"'+beneficiaryCustomerType+'",\n    "customerReference":"'+customerReference+'",\n    "feeType":"'+feeType+'",\n    "receiverPhone":"'+receiverPhone+'",\n    "remark":"'+remark+'",\n    "senderCustomerResidence":"'+senderCustomerResidence+'",\n    "senderCustomerType":"'+senderCustomerType+'",\n    "senderPhone":"'+senderPhone+'",\n    "sourceAccountNo":"'+sourceAccountNo+'",\n    "transactionDate":"'+transactionDate+'",\n    "additionalInfo": {\n    "deviceId": "'+deviceId+'",\n    "channel": "'+channel+'",\n    "senderName": "'+senderName+'",\n    "senderIdentity": "'+senderIdentity+'",\n    "senderAddress": "'+senderAddress+'",\n    "corporateType": "'+corporateType+'"\n}\n}'
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] hash = digest.digest(request_body.getBytes(StandardCharsets.UTF_8));
		StringBuilder hexString = new StringBuilder();

		for (byte b : hash) {
			String hex = Integer.toHexString(0xff & b);
			if (hex.length() == 1) {
				hexString.append('0');
			}
			hexString.append(hex);
		}
		String requestBodyEncrypt = hexString.toString();
		System.out.println(requestBodyEncrypt);


		String payload = "POST" + ":" + "/snap/v1.0/transfer-sknbi" + ":" + GlobalVariable.Token + ":" + requestBodyEncrypt + ":" + timeStamp;

		String client_secret = GlobalVariable.clientSecret as String
		Mac hmacSHA512 = Mac.getInstance("HmacSHA512");
		SecretKeySpec secretKeySpec = new SecretKeySpec(client_secret.getBytes(StandardCharsets.UTF_8), "HmacSHA512");
		hmacSHA512.init(secretKeySpec);
		byte[] hmacBytes = hmacSHA512.doFinal(payload.getBytes(StandardCharsets.UTF_8));

		StringBuilder hexString1 = new StringBuilder();
		for (byte b : hmacBytes) {
			String hex = Integer.toHexString(0xff & b);
			if (hex.length() == 1) hexString1.append('0');
			hexString1.append(hex);
		}
		String signature = hexString1.toString();
		System.out.println(signature);
		return signature;

	}
}
