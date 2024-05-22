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

public class Generate {
	@Keyword
	public static int randomize() {
		System.out.println('ini external id ');
		int number = 10000 + new Random().nextInt(99999-10000)
		System.out.println(number);
		return number;
	}

	@Keyword
	public static String signSHA256RSA() throws Exception {
		// Remove markers and new line characters in private key

		String input = GlobalVariable.clientId+'|'+GlobalVariable.timeStamp;

		java.security.Security.addProvider(
				new org.bouncycastle.jce.provider.BouncyCastleProvider()
				);


//		String strPk = "MIIEowIBAAKCAQEAl63x+1l5kiitYOEAK5zxby6SUkAWtElLTMuAfP3PKCZnnw1tpIlwpR3lZAs0H7NZkWmSjXygnUSMGW/e7cUXp18xDLQfQ+TVb5iZlBWJqQgoNoMKO0OyEqPFqUyUYZGWi2BxzRzVtzB3iOhVNVN8pLMqydmn2zh3wbB9qfkUSQfG7GBzKf07PPr9ag4XQNofBsldTp6k6K8O/emWy6lLVAv6BbdVxGLj8YoDDBh4ZkpOQBIx9Oos9l/2OCNGeOiONo/29QZoBaEyVy92S5nAMI3d24xe5Ra45JqRedsRR0H94Dl1Ec8hnNSBoZ8720ikPlAgq/3R1Oe9dqCWmJmk3wIDAQABAoIBAD8TsUvrm6jyjZd6Ok3oyGaKIYNe75It4Mig4GAaVKnHvtPgC7afCV9BHYILPrPoK9lS5AU+zLRaK0Dwyu5qRjnuVkGILnBXm4L7HPL+Oe83WzHAMOMQdd9Wh/6twA8LMmXnx6ua0C2MVEl1BOpWO8FqWOL0Rt/djb/Ym4mx8YOkuitb63iTkzN4zRJHADAsnIz+inFwkR1Awj523w7rp0O0a3uOzJlvO04L46hN6kL3ehJERHN3woOM9WdqDU25jRTDSx3nqC2XKKGT7yxGNi5gg9/la/oAzXL0DT2TZ98C55sZmOj4Khnhwx29e/kyalwrf424I0MdlJdE5LZxdAECgYEA0BxzdVQoZQDV5CRRy/1RB5kGetPV/mv/U8z4HY0X25HZ/Om6rtb/6XYbY4pr3EBc/Ay5uRR8kJsexHl8CBxYiivEFqhVVaJZlJoBtt1+fYjwKzpA2V7Q2TeqlRY3X8Z/pxmBgRUY4ZMCa/kiFfsEKjmXQ0rlvRFxZNWx08S5Ld8CgYEAupUuLAaloQ9glvEyYUBfNc9O1CCLRUBPBj/eKnT3Y0DC1pQ3VroG0zT497mRO6wtCMSA7lSoF6z/nxO9wvT3nUJ+el99S9MLl5H3DdK2aV4eIwsDAga509euSeLMIGTpIHJILMXKJ0vkotKVSnvoZAOnE6j5HLeU25ko9gHwaQECgYAp+GLsmaFf5VA9p3l7m0XZM2pUIR66JcujeeXcB9V06bA9ymFk22VXDwOtCoCANdbK2kImalRI0Y/d7eYuuJC+pN7x9tbm40wPpRONbpQQ+yn8fc4QSIf0Fr7nPEjEnwKlj4ZHoxqWe5B0eBUAu9P2NBEmeBdnDvqAbHxlifoSnwKBgQCFcOH3vL1CRjQnX9NPOHGfk2dmUOwqGAcsigsDRGoVwyBVxOUbEqqE7VLOIRpcmhQPPDKVwVgCrsiW5dCGmRpqjz7TmG2NCCA3Q750VWn+S8C0AVEiZtsKRiuxThH9bQ5KFVHHK6IbnqyjQoZhIeMffHlGl0eqoSVGNPDVmKdvAQKBgCfNW26zm5N+lM616Ia7ReSIicfzjErzDHpYR75Fr2tqxzsRMBS6cnOYlYfBuz2+FFn4oQN7TjDKheXml4Ykft6tyfn/baUF26qmfkN/CVbu/Cs5/QMcXKfX5OJYhQMNjE2/P/pfITE9tDXFF6WSWdCzglxdJ1xpn0Zt/35LWzb+"
		String strPk = "MIIEowIBAAKCAQEAxsh0Vwwxa9TChv+uDN6ZYDrUWyzrjeGZDjAAPZUhAPZjGeiajV9zN14u36HwaHvCcInJVDFMh2pUgfR8wDW7wfk5uKBnecpKamyDbwHGGC3+xtWpTVj6IsEbmVbaLzBAxuI4A1Txq6IvP6wWOhILqpS4ursbTBO6I+TLhSCdYyHmcg/yoDaXwDWLFhwIk3fcBOumq2bPiOMeE2TLl5//UkTTHfofTSiocMvIcSFFY7hy1ssnWufm6PdBf0Xuzk3ipSPT9iB5orvLUKuAbGKtNhnPABBIa0nvow/Q4fJFjtbdYtRmcHgSYCL1p1saooY5Y/Q6aoWREJ7BPfLFT9M2fwIDAQABAoIBACG12NTyQmdQ+3Q9Y9zW2xfQojacFY5IFdasGT5YTfKF1iSMp7kV2Moo9LYEFmsBoeXH2BapDZthUtd/CLDn6+FnTHyc5y9mONx3boqiuF9dXXmGvmFZl4HRUcbdTb725eVUc79M5nb+2BhqLJLIgKX69qDkHir2pXh00nbPIyGuTyz4UaH8hoh8i1EHraopVu9kzmVI1ml4pbrF3XObvyxqw5aNXFMaqyJUM4MKYX1EK8zDoaE9+hxJyviShJrlQAywt14MJvyEEdp5U7/svERwvhjcq6w8NHvEBpGicSYWgqgjsW8WBpSgKTH5/UjsNcht7Z8caPahcnqotXl28HECgYEA+RP825GEVJPlQx7bUAkpekBkb/UazKRO+NhwByxAiFKqENLkQVBAyY11+xKU0nmrxd+gr0w4uDW/9s7V35BI6ohjpqxzKZw643Qs++dUgojTyZxeRlFj1Z8+Mkt7KvERg0eggNfcP908BAj5RmfYCIdIDT+hGDx6VR1vnKn4pn0CgYEAzE6nRupAvSNONmx5F5UQHmY7nvZByotppJ8p1RuxlgtmPcV3X3lzZ7uvl6vmOScwib97le91kPUA2z4EO7YfQJQkMHLrQBIK0yoLqjh6pwiOnKPN6cGipDg7haw7bYbKcsikCUfo8qbdOkPI/gm4xS+xnH1yFVgGi3NoTEzu1asCgYAeEuy/8iUBNU5EOXCPjFumuCmoXvbaaKhkYXW9V/l+vERufZgzzqXT8DGjnA2+EBTOcn7rS4+5pjvEaBrowHodX1Rw5neNOaDen8Nisv7AOBciS7ASW3drbqvK1NRNTggoSri1ddrRFzxM3GyEtLsf5H2to9Ymn/7BUimMx/UO3QKBgBYsnzkyKY6dq1p60XW+xv4VqHjrT161XOTyJW8sz0xytkkUCo3BN8ZVd4xlqtofJ8R7lOVEFSWDgfFJvNniPWgb7AWaI3BTxPsAQD3cXWuNoTTdDndFfeqG8+bhN53+mDB3GF6tgFn9xFsTilgQJA66y3Mppl0sraErCHHnk7B7AoGBAIxUbtABfhSlsNEb5407QvH9/CD8hsOzMwXsDDqAfojEvXcFMDG0WoP0V8NgnQnWUmlsMgWA3MZLzYeYwYIks1sDUEL00gkTbNODWmdDh0IaXjgtRKJtkd/0t3a/d5/l7CvkcpG/U04WuWKrwAr6miOwrvRB+r/1pUN1iE5zB8y6"
		System.out.println(GlobalVariable.timeStamp)

		// Remove markers and new line characters in private key
		String realPK = strPk.replaceAll("-----END PRIVATE KEY-----", "")
				.replaceAll("-----BEGIN PRIVATE KEY-----", "")
				.replaceAll("\n", "");



		byte[] b1 = Base64.getDecoder().decode(realPK);
		PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(b1);
		KeyFactory kf = KeyFactory.getInstance("RSA");

		Signature privateSignature = Signature.getInstance("SHA256withRSA");

		privateSignature.initSign(kf.generatePrivate(spec));
		privateSignature.update(input.getBytes(StandardCharsets.UTF_8));
		byte[] s = privateSignature.sign();
		return Base64.getEncoder().encodeToString(s);
	}


	public static String minifyJson(String jsonString) {
		StringBuilder minified = new StringBuilder();
		boolean insideString = false;

		for (char c : jsonString.toCharArray()) {
			if (c == '"') {
				insideString = !insideString;
			}

			if (!Character.isWhitespace(c) || insideString) {
				minified.append(c);
			}
		}

		return minified.toString();
	}

	public static String signature(String bodyString,String endpoint_path, String timeStamp) {

		//		String request_body = "{\"accountNo\":\"" + accountNo + "\"}";
		String bodyMinify = minifyJson(bodyString)
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] hash = digest.digest(bodyMinify.getBytes());
		StringBuilder hexString = new StringBuilder();

		for (byte b : hash) {
			String hex = Integer.toHexString(b & 0xFF);
			if (hex.length() == 1) {
				hexString.append('0');
			}
			hexString.append(hex);
		}

		String requestBodyEncrypt = hexString.toString();
		System.out.println('ini request body before encrypt' + bodyMinify)
		System.out.println('ini request body encrypt' + requestBodyEncrypt)


		String payload = "POST" + ":" + endpoint_path + ":" + GlobalVariable.Token + ":" + requestBodyEncrypt + ":" + timeStamp;
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

