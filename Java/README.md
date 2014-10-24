Guidlines to use this Java example:

This sample code is a basic use-case example of :
1. Getting a list of active customer integrations
2. Uploading Classifications to a particular integration 
3) Downloading Segmented Data from a particular integration

Use Case 1: Getting a list of integrations:
 -> This will require Two java files i) GetIntegrations.java ii) AdobeMarketingCloudPartnerAPI.java
1. Open the GetIntegrations.java. This has the main method. 
2. Fill in your Partner Username and Shared Secret in place of "USERNAME" and "PASSWORD" respectively
3. This example shows two way to get the list i) by HTTP GET method ii) by HTTP POST method
4. Both the GET and POST methods can be passed with filters as arguments
5. In the example, the integrationCode = aebd07c1b76e505e has been used. Replace this with integrationCode with the one valid 
1.	open Main2.php and alter lines 8-9 to use your own Partner API username/secret
2.	execute the Main2.php script by using “php Main2.php”
3.	notice, that you receive errors on the api call, this is because the integrationCode being passed in the filter parameter is not a valid integrationCode for your partner account. Also, the other data in the import (“metricName”, “columnNames”, “rows”) will likely not be structured correctly for your Data Connectors product
How to export analytics data from the Adobe Marketing Cloud for a customer integration
