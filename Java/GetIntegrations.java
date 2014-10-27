import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;




public class GetIntegrations {

	public static void main(String[] args) {
	// TODO Auto-generated method stub

		// *** The username and secret can be obtained through your Adobe Partner Integrations contact ***
		AdobeMarketingCloudPartnerAPI client = new AdobeMarketingCloudPartnerAPI("USERNAME", "PASSWORD");
		
		// *** Call the callGET method without Filter ***
		String getresponse1 = client.callGET("https://api.omniture.com/genesis/rest/3.1/index.html?method=Partner.GetIntegrations");
		
		// *** Call the callGET function with filter as URL query string parameter ***
		String getresponse2 = client.callGET("https://api.omniture.com/genesis/rest/3.1/index.html?method=Partner.GetIntegrations&filter=integrationCode=\"aebd07c1b76e505e\"");

		// *** Call the callPOST function with filter as JSON formatted postdata string ***
		String postdata = "{ \"filter\":\"integrationCode='8a4b8b735cb2f696'\"}";
		String postresponse = client.callPOST("https://api.omniture.com/genesis/rest/3.1/index.html?method=Partner.GetIntegrations", postdata);
	
		System.out.println("******* RESPONSE FROM GET CALL WIHOUT FILTER ********");
		System.out.println(getresponse1+"/n");
		System.out.println("******* RESPONSE FROM GET CALL WITH FILTER ********");
		System.out.println(getresponse2+"/n");
		System.out.println("******* RESPONSE FROM POST CALL WITH FILTER ********");
		System.out.println(postresponse+"/n");
		

		

	}

}



