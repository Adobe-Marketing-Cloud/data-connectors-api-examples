<?
include_once './AdobeMarketingCloudPartnerAPI.class.php';

date_default_timezone_set('America/Denver');


// ***  The username and secret can be obtained through your Adobe Partner Integrations contact   ***
$API_USERNAME = "[API_USERNAME]";
$API_SECRET = "[API_SECRET]";

// ***  Create an instance of the AdobeMarketingCloudPartnerAPI class, to be use to make api calls   ***
$client = new AdobeMarketingCloudPartnerAPI( $API_USERNAME, $API_SECRET );
					
// ***  If you pass true then all api calls will be routed through Charles proxy, for debugging   ***
$client->setProxyEnabled(false);

// ***  Set the URL and the parameters needed for the api call   ***
$url = "https://api.omniture.com/genesis/rest/3.1/index.html?method=Export.RequestSegmentedData";

$jsonParams = <<<EOD
{ 
"integrationCode":"8a4b8b735cb2f696",
"breakdownNames":["Tracking ID"],
"metricNames":["visits"],
"segmentName":"Visits with Report ID",
"dateGranularity":"none",
"dateType":"range",
"dateFrom":"10/01/14",
"dateTo":"10/01/14"
}
EOD;
                          
// ***  Call Export.RequestSegmentedData using a POST call   ***
$jsonResult = $client->callPOST($url, $jsonParams);

// ***  print out the response from the api call   ***
echo $jsonResult;


// ***  Set the URL and the parameters needed for the api call   ***
$url = "https://api.omniture.com/genesis/rest/3.1/index.html?method=Export.CheckDataRequest";

$jsonParams = <<<EOD
{ 
"integrationCode":"8a4b8b735cb2f696",
"requestId":"2329486"
}
EOD;
                          
// ***  Call Export.CheckDataRequest using a POST call, these exports can take several hours to process  ***
$jsonResult = $client->callPOST($url, $jsonParams);

// ***  print out the response from the api call   ***
echo $jsonResult;


// ***  Set the URL and the parameters needed for the api call   ***
$url = "https://api.omniture.com/genesis/i/3.1/export/index.html?integrationCode=8a4b8b735cb2f696&requestId=2329486";
                          
// ***  Get the data at the "data_url" ***
$jsonResult = $client->callGET($url);

// ***  print out the response from the api call   ***
echo $jsonResult;


?> 
