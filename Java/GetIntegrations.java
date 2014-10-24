import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;




public class Main {

	public static void main(String[] args) {
	// TODO Auto-generated method stub

		// Initialize with Username and Shared secret
		AdobeMarketingPartnerAPI client = new AdobeMarketingPartnerAPI("USERNAME", "PASSWORD");
		
		// Call the callGet function with argument as non URL encoded URL string
		String getresponse = client.callGet("https://api.omniture.com/genesis/rest/3.1/index.html?method=Partner.GetIntegrations&filter=integrationCode=\"aebd07c1b76e505e\"");

		// Call the call callPost function with argument as non url encoded URL String and JSON formated postdata string
		Map<String, String> input = new HashMap<String, String>();
		input.put("filter", "integrationCode='aebd07c1b76e505e'");
		System.out.println(JSONObject.fromObject(input).toString());
		// change it to string.. 
		
		
		String postdata = JSONObject.fromObject(input).toString();
		String postresponse = client.callPost("https://api.omniture.com/genesis/rest/3.1/index.html?method=Partner.GetIntegrations", postdata);
	
		//Testing
		System.out.println("GET RESPONSE: "+getresponse);
		System.out.println("POST RESPONSE: "+postresponse);
		
		System.out.println("Parsed Response from GET Call: ");
		client.responseParser(getresponse);
		
		System.out.println("Parsed Response from POST Call: ");
		client.responseParser(postresponse);
		

	}

}



