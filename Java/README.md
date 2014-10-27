##Important info about the Java examples:

This sample code is a basic use-case example of :
* Getting a list of active customer integrations
* Uploading Classifications to a particular integration
* Downloading Segmented Data from a particular integration

**NOTE**:You need your Adobe Partner API Username and shared secret to run this code.
You also need to have a Adobe Analytics company with a valid report suite and atleast one Data Connector integration.
### Getting a list of active customer integrations

----------


1. This example uses two files i) `AdobeMarketingCloudPartnerAPI.java` ii) `GetIntegrations.java` 
2. Replace your Username and Shared Secret as commented in `GetIntegrations.java`. **This file has the `main` method.**
3. This example shows the use of `HTTP GET` and `HTTP POST` methods to use `GetIntegrations` call
4. The example covers the use case of using a filter to get integration details of a specific integration:
                    ```String postdata = "{ \"filter\":\"integrationCode='8a4b8b735cb2f696'\"}";```
5. Please do replace the above filter with the integrationCode applicable to yours.
6. For more details on Partner.GetIntegrations method, visit --> [Partner.GetIntegrations](https://marketing.adobe.com/developer/en_US/documentation/genesis/r-getintegrations)


### Uploading Classifications

----------

1. This example uses two files i) `AdobeMarketingCloudPartnerAPI.java` ii) `UploadClassifications.java`
2. Replace your Username and Shared Secret as commented in `UploadClassifications.java`. **This file has the `main` method.**
3. This example uses `HTTP POST` method to upload classification data to a specific integration. For more information on classifications in Adobe Analytics refer: [About classifications ](http://microsite.omniture.com/t2/help/en_US/reference/c_classifications.html)
4. The data to be uploaded must follow JSON. For details on the expected input and output data refer: [Import.UploadClassifications](https://marketing.adobe.com/developer/en_US/documentation/genesis/r-uploadclassifications)
5. The data used in this example is very specific to the integration being used here. Please replace the example data with your own application data.

### Downloading Segment Data

----------

1. This example uses two files i) `AdobeMarketingCloudPartnerAPI.java` ii) `RequestSegments.java`
2. Replace your Username and Shared Secret as commented in `RequestSegments.java`. **This file has the `main` method.**
3. This example show how one can i) make a request to get segment data ii) check if the request has been processed. The Partner API methods used for the same are i) `Export.RequestSegmentedData` (Using `HTTP POST`) ii) `Export.CheckDataRequest` (using `HTTP GET`) respectively.
4. This example uses `JSONObject` library to access data from JSON response. You may use your own way to achieve the same functionality. 
5. For details on the expected input and output data refer: [Export.RequestSegmentedData](https://marketing.adobe.com/developer/en_US/documentation/genesis/r-requestsegmenteddata), [Export.CheckDataRequest](https://marketing.adobe.com/developer/en_US/documentation/genesis/r-checkdatarequest) 
6. The request data related to requesting Segmented data is very specific to this example. It must be replaced with the integration of yours.
