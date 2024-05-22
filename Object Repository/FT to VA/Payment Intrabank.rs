<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>Payment Intrabank</name>
   <tag></tag>
   <elementGuidId>c27fc79a-b2a9-4280-891f-451a93cdd482</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <autoUpdateContent>false</autoUpdateContent>
   <connectionTimeout>-1</connectionTimeout>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n    \&quot;partnerServiceId\&quot;: \&quot;${partnerServiceId}\&quot;,\n    \&quot;customerNo\&quot;: \&quot;${customerNo}\&quot;,\n    \&quot;virtualAccountNo\&quot;: \&quot;${virtualAccountNo}\&quot;,\n    \&quot;virtualAccountName\&quot;: \&quot;${virtualAccountName}\&quot;,\n    \&quot;sourceAccountNo\&quot;: \&quot;${sourceAccountNo}\&quot;,\n    \&quot;partnerReferenceNo\&quot;: \&quot;${partnerReferenceNo}\&quot;,\n    \&quot;trxDateTime\&quot;: \&quot;${trxDateTime}\&quot;,\n    \&quot;paidAmount\&quot;: {\n        \&quot;value\&quot;: \&quot;${value}\&quot;,\n        \&quot;currency\&quot;: \&quot;${currency}\&quot;\n    }\n}&quot;,
  &quot;contentType&quot;: &quot;application/json&quot;,
  &quot;charset&quot;: &quot;UTF-8&quot;
}</httpBodyContent>
   <httpBodyType>text</httpBodyType>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Content-Type</name>
      <type>Main</type>
      <value>application/json</value>
      <webElementGuid>f54f7d86-cc1d-4d94-b52c-7173a81a0c28</webElementGuid>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>X-PARTNER-ID</name>
      <type>Main</type>
      <value>BRIAPItest</value>
      <webElementGuid>e220750c-baac-48cc-8a3c-c8304903bbcf</webElementGuid>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>CHANNEL-ID</name>
      <type>Main</type>
      <value>888</value>
      <webElementGuid>e06cb046-ae6d-4449-9056-2589cf91cce1</webElementGuid>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>X-SIGNATURE</name>
      <type>Main</type>
      <value>${signature}</value>
      <webElementGuid>4a8f6fad-e225-41b6-8fcc-16baf52bf7c7</webElementGuid>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>X-TIMESTAMP</name>
      <type>Main</type>
      <value>${timeStamp}</value>
      <webElementGuid>dee105af-2485-4fb5-9243-3b3bde23b963</webElementGuid>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Authorization</name>
      <type>Main</type>
      <value>Bearer ${token}</value>
      <webElementGuid>dc3e8cc4-aed1-423c-b07a-48992b7cf5fc</webElementGuid>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>X-EXTERNAL-ID</name>
      <type>Main</type>
      <value>${externalId}</value>
      <webElementGuid>ab588e97-8840-425f-b776-f1f16f5340f5</webElementGuid>
   </httpHeaderProperties>
   <katalonVersion>8.6.0</katalonVersion>
   <maxResponseSize>-1</maxResponseSize>
   <migratedVersion>5.4.1</migratedVersion>
   <restRequestMethod>POST</restRequestMethod>
   <restUrl>https://sandbox.partner.api.bri.co.id/payment/snap/prod/v1.1/transfer-va/payment-intrabank</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceEndpoint></soapServiceEndpoint>
   <soapServiceFunction></soapServiceFunction>
   <socketTimeout>-1</socketTimeout>
   <useServiceInfoFromWsdl>true</useServiceInfoFromWsdl>
   <verificationScript>import static org.assertj.core.api.Assertions.*

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webservice.verification.WSResponseManager

import groovy.json.JsonSlurper
import internal.GlobalVariable as GlobalVariable

RequestObject request = WSResponseManager.getInstance().getCurrentRequest()

ResponseObject response = WSResponseManager.getInstance().getCurrentResponse()</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
