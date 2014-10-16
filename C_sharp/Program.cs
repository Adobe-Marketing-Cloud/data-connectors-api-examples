using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Net;
using System.Security.Cryptography;

namespace DataConnectorsREST
{
    class Program
    {
        // ***  The username and secret can be obtained through your Adobe Partner Integrations contact   ***
        private const string API_USERNAME = "[API_USERNAME]";
        private const string API_SECRET = "[API_SECRET]";


        /**
         * This Main method shows how the Partner.GetIntegrations method is called a few different ways.
         * 
         * See documentation -- https://marketing.adobe.com/developer/en_US/documentation/genesis/r-getintegrations
         */
        static void Main(string[] args)
        {
            
            // ***  Create an instance of the AdobeMarketingCloudPartnerAPI class, to be use to make api calls   ***
            AdobeMarketingCloudPartnerAPI client = new AdobeMarketingCloudPartnerAPI(API_USERNAME, API_SECRET);

            // *** Call Partner.GetIntegrations using a GET call  ***
            string url = "https://api.omniture.com/genesis/rest/3.1/index.html?method=Partner.GetIntegrations";
            string jsonResult = client.callGET(url);
            
            // ***  print out the response from the api call   ***
            Console.WriteLine(jsonResult);
            Console.WriteLine("=========================");

            // ***  Call Partner.GetIntegrations using a GET call, with the "filter" parameter on the query string  ***
            string url_modified = url + "&filter=" + System.Web.HttpUtility.UrlEncode("integrationCode='8a4b8b735cb2f696'");
            string jsonResult2 = client.callGET(url_modified);
            
            // ***  print out the response from the api call   ***
            Console.WriteLine(jsonResult2);
            Console.WriteLine("=========================");

            // ***  Call Partner.GetIntegrations using a POST call, with a "filter" parameter in the POST body   *** 
            string jsonParams = "{ \"filter\":\"integrationCode='8a4b8b735cb2f696'\" }";
            string jsonResult3 = client.callPOST(url, jsonParams);
            
            // ***  print out the response from the api call   ***
            Console.WriteLine(jsonResult3);

            Console.ReadLine();
        }




        /**
         * This Main method shows how to Import classification (meta) data for an integrated report suite. 
         * 
         * See documentation -- https://marketing.adobe.com/developer/en_US/documentation/genesis/r-uploadclassifications
         */
        /*
        static void Main(string[] args)
        {

            // ***  Create an instance of the AdobeMarketingCloudPartnerAPI class, to be use to make api calls   ***
            AdobeMarketingCloudPartnerAPI client = new AdobeMarketingCloudPartnerAPI(API_USERNAME, API_SECRET);

            // ***  Set the URL and the parameters needed for the Import.UploadClassifications call   ***
            string url = "https://api.omniture.com/genesis/rest/3.1/index.html?method=Import.UploadClassifications";
            string jsonParams = @"{ 
""integrationCode"":""8a4b8b735cb2f696"",
""metricName"":""Tracking ID"",
""columnNames"":[""Key"", ""Name"", ""Color""],
""rows"":[
    [""1234"", ""Name 1"", ""Red""],
    [""1235"", ""Name 2"", ""White""]
],
""endOfBlock"":""1""
            }";

            // ***  Call Import.UploadClassifications using a POST call   ***
            string jsonResult = client.callPOST(url, jsonParams);

            // ***  print out the response from the api call   ***
            Console.WriteLine(jsonResult);

            Console.ReadLine();
        }
        */
        


        /**
         * This Main method shows how to Export analytics report data for an integrated report suite. 
         * 
         * See documentation -- https://marketing.adobe.com/developer/en_US/documentation/genesis/r-requestsegmenteddata
         */
        /*
        static void Main(string[] args)
        {

            // ***  Create an instance of the AdobeMarketingCloudPartnerAPI class, to be use to make api calls   ***
            AdobeMarketingCloudPartnerAPI client = new AdobeMarketingCloudPartnerAPI(API_USERNAME, API_SECRET);

            // ***  Set the URL and the parameters needed for the Export.RequestSegmentedData call   ***
            string url = "https://api.omniture.com/genesis/rest/3.1/index.html?method=Export.RequestSegmentedData";
            string jsonParams = @"{ 
""integrationCode"":""8a4b8b735cb2f696"",
""breakdownNames"":[""Tracking ID""],
""metricNames"":[""visits""],
""segmentName"":""Visits with Report ID"",
""dateGranularity"":""none"",
""dateType"":""range"",
""dateFrom"":""10/01/14"",
""dateTo"":""10/01/14""
}";

            // ***  Call Export.RequestSegmentedData using a POST call   ***
            string jsonResult = ""; // client.callPOST(url, jsonParams);

            // ***  print out the response from the api call   ***
            Console.WriteLine(jsonResult);

            // ***  Set the URL and the parameters needed for the Export.CheckDataRequest call, note the requestId is returned in the previous jsonResult   ***
            url = "https://api.omniture.com/genesis/rest/3.1/index.html?method=Export.CheckDataRequest";
            jsonParams = @"{
""integrationCode"":""8a4b8b735cb2f696"",
""requestId"":""2331347""
}";

            // ***  Call Export.CheckDataRequest using a POST call   ***
            jsonResult = client.callPOST(url, jsonParams);

            // ***  print out the response from the api call   ***
            Console.WriteLine(jsonResult);

            // *** Finally, if the report data is ready there will be a data_url in the response -- this can then be referenced to download the data with callGET ***


            Console.ReadLine();
        }
        */

    }
}


    
