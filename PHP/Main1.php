<?
include_once './AdobeMarketingCloudPartnerAPI.class.php';

date_default_timezone_set('America/Denver');


// ***  The username and secret can be obtained through your Adobe Partner Integrations contact   ***
$API_USERNAME =  "[API_USERNAME]";
$API_SECRET =  "[API_SECRET]";

// ***  Create an instance of the AdobeMarketingCloudPartnerAPI class, to be use to make api calls   ***
$client = new AdobeMarketingCloudPartnerAPI( $API_USERNAME, $API_SECRET );

// ***  If you pass true then all api calls will be routed through Charles proxy, for debugging   ***				
$client->setProxyEnabled(false);


// *** Call Partner.GetIntegrations using a GET call  ***
$url = "https://api.omniture.com/genesis/rest/3.1/index.html?method=Partner.GetIntegrations";
$result = $client->callGET( $url );

// ***  print out the response from the api call   ***
var_dump($result);

echo "=========================\n";


// ***  Call Partner.GetIntegrations using a GET call, with the "filter" parameter on the query string  ***
$url_modified = $url . "&filter=" . urlencode("integrationCode='8a4b8b735cb2f696'");
$result = $client->callGET( $url_modified );

// ***  print out the response from the api call   ***
var_dump($result);

echo "=========================\n";


// ***  Call Partner.GetIntegrations using a GET call, with the "filter" parameter on the query string  ***
$jsonParams = "{ \"filter\":\"integrationCode='8a4b8b735cb2f696'\" }";
$result = $client->callPOST( $url, $jsonParams );

// ***  print out the response from the api call   ***
var_dump($result);

?> 
