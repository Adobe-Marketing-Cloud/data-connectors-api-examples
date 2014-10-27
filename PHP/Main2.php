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


// ***  Set the URL and the parameters needed for the Import.UploadClassifications call   ***
$url = "https://api.omniture.com/genesis/rest/3.1/index.html?method=Import.UploadClassifications";

$jsonParams = <<<EOD
{ 
"integrationCode":"8a4b8b735cb2f696",
"metricName":"Tracking ID",
"columnNames":["Key", "Name", "Color"],
"rows":[
    ["1234", "Name 1", "Red"],
    ["1235", "Name 2", "White"]
],
"endOfBlock":"1"
}
EOD;
                          
// ***  Call Import.UploadClassifications using a POST call   ***
$jsonResult = $client->callPOST($url, $jsonParams);

// ***  print out the response from the api call   ***
echo $jsonResult;


?> 
