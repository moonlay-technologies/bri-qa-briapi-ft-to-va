package utils

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

public class helper {

	public static String minifyJson(String jsonString) {
		StringBuilder minified = new StringBuilder();

		for (char c : jsonString.toCharArray()) {
			if (!Character.isWhitespace(c)) {
				minified.append(c);
			}
		}
		return minified.toString();
	}


	@Keyword
	public static String transferSKNBI2(String timeStamp, String partnerReferenceNo, String value, String currency, String beneficiaryAccountName, String beneficiaryAccountNo, String beneficiaryAddress, String beneficiaryBankCode, String beneficiaryCustomerResidence, String beneficiaryCustomerType, String customerReference, String feeType, String receiverPhone, String remark, String senderCustomerResidence, String senderCustomerType, String senderPhone, String sourceAccountNo, String transactionDate,String deviceId, String channel, String senderName, String senderIdentity, String senderAddress, String corporateType) {

		//		String request_body = '{\n    "partnerReferenceNo": "'+partnerReferenceNo+'",\n    "amount": {\n    "value": "'+value+'",\n    "currency": "'+currency+'"\n    },\n    "beneficiaryAccountName": "'+beneficiaryAccountName+'",\n    "beneficiaryAccountNo": "'+beneficiaryAccountNo+'",\n    "beneficiaryAddress":"'+beneficiaryAddress+'",\n    "beneficiaryBankCode":"'+beneficiaryBankCode+'",\n    "beneficiaryCustomerResidence":"'+beneficiaryCustomerResidence+'",\n    "beneficiaryCustomerType":"'+beneficiaryCustomerType+'",\n    "customerReference":"'+customerReference+'",\n    "feeType":"'+feeType+'",\n    "receiverPhone":"'+receiverPhone+'",\n    "remark":"'+remark+'",\n    "senderCustomerResidence":"'+senderCustomerResidence+'",\n    "senderCustomerType":"'+senderCustomerType+'",\n    "senderPhone":"'+senderPhone+'",\n    "sourceAccountNo":"'+sourceAccountNo+'",\n    "transactionDate":"'+transactionDate+'",\n    "additionalInfo": {\n    "deviceId": "'+deviceId+'",\n    "channel": "'+channel+'",\n    "senderName": "'+senderName+'",\n    "senderIdentity": "'+senderIdentity+'",\n    "senderAddress": "'+senderAddress+'",\n    "corporateType": "'+corporateType+'"\n}\n}'
		String request_body = '{"partnerReferenceNo":"'+partnerReferenceNo+'","amount":{"value":"'+value+'","currency":"'+currency+'"},"beneficiaryAccountName":"'+beneficiaryAccountName+'","beneficiaryAccountNo":"'+beneficiaryAccountNo+'","beneficiaryAddress":"'+beneficiaryAddress+'","beneficiaryBankCode":"'+beneficiaryBankCode+'","beneficiaryCustomerResidence":"'+beneficiaryCustomerResidence+'","beneficiaryCustomerType":"'+beneficiaryCustomerType+'","customerReference":"'+customerReference+'","feeType":"'+feeType+'","receiverPhone":"08572202751","remark":"MD-SKN_202110270001","senderCustomerResidence":"1","senderCustomerType":"1","senderPhone":"085733047341","sourceAccountNo":"020601009446306","transactionDate":"2022-04-22T14:37:56-07:00","additionalInfo":{"deviceId":"12345679237","channel":"mobilephone","senderName":"Latifah Hanum","senderIdentity":"315205100190093","senderAddress":"Jakarta Selatan","corporateType":"1"}}'
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
		System.out.println('ini request body encrypt' + request_body)


		String payload = "POST" + ":" + "/snap/v1.0/transfer-sknbi" + ":" + GlobalVariable.Token + ":" + requestBodyEncrypt + ":" + timeStamp;
		//			String payload = "POST:/snap/v1.0/transfer-sknbi:Bup3VA6T6hTAou3nHJ47oQU3bA4A:7d1edba40a6f50bdbf351fe4835c288958c383382d01e0ea8ba7a8a5091bc780:2023-11-21T22:09:57.149+07:00"
		System.out.println('ini payload encrypt ' + payload)


		String client_secret = GlobalVariable.clientSecret as String
		System.out.println('ini client secret' + client_secret);
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
		System.out.println('ini signature encrypt ' + signature);
		return signature;
	}



	public static String InterbankAccount(String timeStamp, String beneficiaryBankCode, String beneficiaryAccountNo, String deviceId, String channel) {

		//		String request_body = '{\n    "beneficiaryBankCode": "'+beneficiaryBankCode+'",\n     "beneficiaryAccountNo":"'+beneficiaryAccountNo+'",\n     "additionalInfo": {\n    "deviceId": "'+deviceId+'",\n     "channel":"'+channel+'"\n}\n}'
		String request_body = '{"beneficiaryBankCode":"'+beneficiaryBankCode+'","beneficiaryAccountNo":"'+beneficiaryAccountNo+'","additionalInfo":{"deviceId":"'+deviceId+'","channel":"'+channel+'"}}'
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] hash = digest.digest(request_body.getBytes());
		StringBuilder hexString = new StringBuilder()

		for (byte b : hash) {
			String hex = Integer.toHexString(b & 0xFF);
			if (hex.length() == 1) {
				hexString.append('0');
			}
			hexString.append(hex);
		}
		String requestBodyEncrypt = hexString.toString();
		System.out.println('ini request body sebelum encrypt' + request_body)
		System.out.println('ini request body encrypt' + requestBodyEncrypt)


		String payload = "POST" + ":" + "/interbank/realcase/snap/v1.0/account-inquiry-external" + ":" + GlobalVariable.Token + ":" + requestBodyEncrypt + ":" + timeStamp;
		System.out.println('ini payload encrypt ' + payload)


		String client_secret = GlobalVariable.clientSecret as String
		System.out.println('ini client Secret encrypt' + client_secret)
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
		System.out.println('ini signature encrypt' + signature);
		return signature;
	}

}
