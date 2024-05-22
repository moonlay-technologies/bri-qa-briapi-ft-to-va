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

import com.kms.katalon.core.util.internal.Base64
import com.kms.katalon.core.testobject.ResponseObject
import java.security.Security
import org.bouncycastle.jce.provider.BouncyCastleProvider
import org.bouncycastle.util.encoders.Hex
import java.security.KeyFactory
import java.security.spec.PKCS8EncodedKeySpec
import java.security.Signature
import java.security.MessageDigest
import java.security.PrivateKey
import java.security.PublicKey
import java.security.spec.X509EncodedKeySpec
import javax.xml.bind.DatatypeConverter


public class utilsBri {

	@Keyword
	String base64UrlEncode(String input) {
		return Base64.encodeBase64URLSafeString(input.getBytes())
	}

	@Keyword
	String hexToBase64(String hexstring) {
		byte[] bytes = Hex.decode(hexstring)
		return DatatypeConverter.printBase64Binary(bytes)
	}

	@Keyword
	public static convertToKeyword() {
		Security.addProvider(new BouncyCastleProvider())

		//	String privateKeyPEM = "-----BEGIN RSA PRIVATE KEY----- MIIEpAIBAAKCAQEAruxfhvFDbQ+zbiH3DMDIewIne0SRvvn5nY5cZ3VGy60W37BZ X2bvllVSoihxIKs7J2AUVRJbG/0fyhGJZj9HhwsVx/SK9TT1Jobfu6Bb8iUbGOKg C1ZlmD/O7Ion7090Pl9zVLKvnE2VhFxTyYsrId2iMXFlAtY8kbUiYBr1aQaIS6eC Wq9LPWdgZZJRblsnaWxZEXPsdmd5s4CHaMAOycgOjqy4IaEKJBcSWtE9aVc2rLNF Fb6O83YxY2Myjgt9yTc8ENhkSoS33oCGt0VmoVGT8gpYX8sC3uuKlVgDcwbyAn/3 scXDnChdydHws2IYVCergN3Wu5wutaKNdoB/2wIDAQABAoIBAHbHf16MebKXFMpW OgHo7rnIjrK/rxA1gnCgeNKv6jDoCj4+65FCvrIgogoG2DrMy1BDh8r0ufVZfrqi CeDqW1GyD0pGHg2/tjBBKa+ohob491uAmBjaxAYMXiB0+AUiuNaZziDXbTP/4uWM JEEZRWKrszVLwMGaZGq0yvDjnX2AmGlmIv59oe7B+7UEMQTMl5cAmvNJIok77fIV LBJIH5fG012qJjGDobIQcpmJyYJQZlKZfkwz+9fCXs4M22VXE5eFgdgidRMbO4pY ZS1xMTbdA4he9mxyGVIkbkK52b23B8sd8k6BsSjVrNwAys4hs7EQm2nBDcxkHeqq ZonGbPECgYEA3NWFw2XLR9e55hgD3RE10L9/OhM4Z/mxZDHYqzslgN7EjEmKcrn6 lhD4H7RLXOQ+H3vlTNnq9ovgFGrx7mLQ4kzFKWchcJBMr9cHf8zYY/2ftzwRUkbu 2oW9tDs2jB3yXpbn+xVaUyrW7WsXFRtXrmuGeONWY64wjYoeQL0aWFcCgYEAysdD PCm64U4pZYHqsNQ24a6nWrprwmq/xql0+N0OLm0fWyxDO+pExKWLpOwOvFX2Tpft BEr0Qg9xz7MmL+szSOYo3p2tt8+1aQUFxYtwBVfCgfwycPLJXwH4yPQXYo5/dgkQ xUfG03+gK4xiWCpzoP2f8N+FCTJ6lZ1HBECosh0CgYEA1j486KWwQsHSs50m2mkg +aVfLBuLhKwk4lzNpMncB5uXubZ7H/S4XLCX2EJVPwicEHVlr1pXbtujCIv8rNan anUqbpGESq1kCNAnkWBNqyxasC0RbO4uOki4E8vCjsta4VOsOdasa7VLEMjteues +CWPL2b23/wVVKvjz9DmZz8CgYAXFcpUvhd1cPWLhdfXG8HHvZ9118VO0+JTVpVn g5sKtAee1JIu7wO3M3WLaAhhbpPk7KziETpvjtyRHb8aM3eaNAGxHlhZlb78XsMC c1ZkdVJChBil/S3psArHreo/oSNnp66OFxbOmzf73TaQ/Pw+9sDPplK58lI8WDRu ioHqDQKBgQCwcxOPA7omn7xEn5psyaex3kQWEekHp26Iq0HzuLCISt3CZ+YxAw1u AM8cBKBCea7FUqi0xlcleDPpgZjEmlVeLRcmIxnmMArbOn/PXxWD2MHdqczGYVQj zfHlF2S0T6HcbzapMkgZXRQr/BiWAqHryz93lXk2j2/An4zwSZBolA== -----END RSA PRIVATE KEY-----"
		String privateKeyPEM = "123456abcb"
		String clientId = "vy4eHvijLy6qpXkrc9eFnFl7aGHIR3YN"
		String timestamp = "2023-05-23T10:12:24.738+07:00"

		byte[] privateKeyBytes = privateKeyPEM.getBytes()
		privateKeyBytes = Base64.decodeBase64(privateKeyBytes)

		PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes)
		KeyFactory keyFactory = KeyFactory.getInstance("RSA")
		PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec)

		Signature signature = Signature.getInstance("SHA256withRSA")
		signature.initSign(privateKey)
		signature.update((clientId + "|" + timestamp).getBytes())
		byte[] signatureValue = signature.sign()

		String base64Signature = hexToBase64(Hex.toHexString(signatureValue))

		return base64Signature;

		//	ResponseObject response = WSResponseManager.getInstance().getCurrentResponse()
		//	response.addHeader("signature", base64Signature)
		//	response.addHeader("signature_hex", Hex.toHexString(signatureValue))
	}

	//convertToKeyword()
}

