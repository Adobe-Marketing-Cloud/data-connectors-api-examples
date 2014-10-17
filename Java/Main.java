import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class Main {

	public static void main(String[] args) {
	// TODO Auto-generated method stub

		String urlname = "https://api.omniture.com/genesis/rest/3.1/index.html?method=Export.RequestSegmentedData";
		
		// Initialize with Username and Shared secret
		AdobeMarketingPartnerAPI client = new AdobeMarketingPartnerAPI("omtrgublercorp_sgubler", "6*FImbcdSBdH");
		
		// Format the upload data in the format as give here - https://marketing.adobe.com/developer/en_US/documentation/genesis/r-uploadclassifications
				//The original formating of the data below is:
				/*
				 * { 
					"integrationCode":"8a4b8b735cb2f696",
					"breakdownNames":["Tracking ID"],
					"metricNames":["visits"],
					"segmentName":"Visits with Report ID",
					"dateGranularity":"none",
					"dateType":"range",
					"dateFrom":"10/01/14",
					"dateTo":"10/01/14"
					}
				 * 
				 * */
				String postData = "{ "
					+	"\"integrationCode\":\"8a4b8b735cb2f696\","
					+	"\"breakdownNames\":[\"Tracking ID\"],"
					+	"\"metricNames\":[\"visits\"],"
					+	"\"segmentName\":\"Visits with Report ID\","
					+	"\"dateGranularity\":\"none\","
					+	"\"dateType\":\"range\","
					+	"\"dateFrom\":\"10/01/14\","
					+	"\"dateTo\":\"10/01/14\""
					+	"}";
				
				// Use method callPost for using Export.RequestSegementedData
				String jsonResponse = client.callPost(urlname, postData);
				
				// Capture the requestId for use in Export.CheckDataRequest method
				//System.out.println("Response: "+jsonResponse);
				JSONObject json = JSONObject.fromObject(jsonResponse);
				String requestId = json.get("requestId").toString();
				
				// loop the call to CheckDataRequest method until the status is 2
				
				do {
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String checkStatusURL = "https://api.omniture.com/genesis/rest/3.1/index.html?method=Export.CheckDataRequest&integrationCode=8a4b8b735cb2f696&requestId="+requestId;
					String checkStatusURLresponse = client.callGet(checkStatusURL);
					json = JSONObject.fromObject(checkStatusURLresponse);
					System.out.println(json.get("message").toString());
				} while (!json.get("status").toString().equals("2"));
		
				if (json.get("data_url").toString() != null){
					String segmentedDataURL = json.get("data_url").toString();
					System.out.println(segmentedDataURL);
					} else {
						
						System.out.println("URL not available");
					}
				
				
				
	}
	
}
