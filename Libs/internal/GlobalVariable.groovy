package internal

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.main.TestCaseMain


/**
 * This class is generated automatically by Katalon Studio and should not be modified or deleted.
 */
public class GlobalVariable {
     
    /**
     * <p></p>
     */
    public static Object signature_hex
     
    /**
     * <p></p>
     */
    public static Object client_id
     
    /**
     * <p></p>
     */
    public static Object timestamp
     
    /**
     * <p></p>
     */
    public static Object signature
     
    /**
     * <p></p>
     */
    public static Object Token
     
    /**
     * <p></p>
     */
    public static Object clientId
     
    /**
     * <p></p>
     */
    public static Object signatureHex
     
    /**
     * <p></p>
     */
    public static Object timeStamp
     
    /**
     * <p></p>
     */
    public static Object clientSecret
     

    static {
        try {
            def selectedVariables = TestCaseMain.getGlobalVariables("default")
			selectedVariables += TestCaseMain.getGlobalVariables(RunConfiguration.getExecutionProfile())
            selectedVariables += TestCaseMain.getParsedValues(RunConfiguration.getOverridingParameters(), selectedVariables)
    
            signature_hex = selectedVariables['signature_hex']
            client_id = selectedVariables['client_id']
            timestamp = selectedVariables['timestamp']
            signature = selectedVariables['signature']
            Token = selectedVariables['Token']
            clientId = selectedVariables['clientId']
            signatureHex = selectedVariables['signatureHex']
            timeStamp = selectedVariables['timeStamp']
            clientSecret = selectedVariables['clientSecret']
            
        } catch (Exception e) {
            TestCaseMain.logGlobalVariableError(e)
        }
    }
}
