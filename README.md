##Guidlines to use this Java example:

This sample code is a basic use-case example of :
* Getting a list of active customer integrations
* Uploading Classifications to a particular integration
* Downloading Segmented Data from a particular integration


### Getting a list of active customer integrations





----------
**NOTE**:You need your Adobe Partner API Username and shared secret to run this code.
You also need to have a Adobe Analytics company with a valid report suite and atleast one Data Connector integration.

1. This example uses two files i) `AdobeMarketingCloudPartnerAPI.java` ii) `GetIntegrations.java` 
2. Replace your Username and Shared Secret as commented in GetIntegrations.java. This file has the main method.
3. This example shows the use of `HTTP GET` and `HTTP POST` methods to use `GetIntegrations` call
4. The example covers the use case of using a filter to get integration details of a specific integration:
                    ```String postdata = "{ \"filter\":\"integrationCode='8a4b8b735cb2f696'\"}";```
5. Please do replace the above filter with the integrationCode applicable to yours.
6. For more details on Partner.GetIntegrations method, visit --> [https://marketing.adobe.com/developer/en_US/documentation/genesis/r-getintegrations](https://marketing.adobe.com/developer/en_US/documentation/genesis/r-getintegrations)