data-connectors-api-examples
============================

A set of code examples for interfacing with the Data Connections (Partner) API

<h3>Important things to note</h3>

* Disclaimer: This code is not intended to be a robust SDK, these samples are only provided to give a starting point for development and to aid with doing proof of concept work.
* This code uses a REST-ful interface for the Data Connectors API -- however SOAP can also be used in most cases
* This code is currently provided in 3 languages -- PHP, C# , Java
* This API uses a WSSE authentication header on every call, each example class has a method called getWSSEHeader for this purpose
  * The code examples can not be run without Partner API credentials (a Username and Secret). These must be obtain through an Adobe Partner Integration Manager after appropriate agreements are in place.
* Each example passes JSON encoded data as a String and received JSON encoded data as a String
  * parsing the JSON data is left as an exercise for the developer so you can choose your favorite JSON library

<h3>What the example code does...</h3>

* Query for the full list of active customer integrations. Also, shows how to filter the query.
* Import classification data into the Adobe Marketing Cloud for a specific customer integration instance.
* Export analytics data from the Adobe Marketing Cloud for a specific customer integration instance.

Note: many parameters being passed in example API calls will need to be modified for your own Partner account.



<h3>What is the Data Connectors API?</h3>

It is an interface for Adobe partners that have pre-configured a data sharing integration with the Adobe Marketing Cloud, the AMC (specifically Adobe Analytics in most cases). As joint customers subscribe to this integration the Adobe partner can use the Data Connectors API to do operations like... 1) get a list of all joint customers that have subscribed to the integration, 2) import data into the AMC on behalf of the customer, 3) export data from the AMC on behalf of the customer. To learn more about Data Connectors see the following links.

* https://marketing.adobe.com/developer/en_US/get-started/partner-api/c-tutorials
* https://marketing.adobe.com/developer/en_US/documentation/genesis/c-genapi-overview


