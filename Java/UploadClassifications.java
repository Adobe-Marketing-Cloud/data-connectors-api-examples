
public class UploadClassifications {

	public static void main(String[] args) {

		// *** Initialize with the URL
		String urlname = "https://api.omniture.com/genesis/rest/3.1/index.html?method=Import.UploadClassifications";
		
		// *** The username and secret can be obtained through your Adobe Partner Integrations contact ***
		AdobeMarketingPartnerAPI client = new AdobeMarketingPartnerAPI("USERNAME", "PASSWORD");

		/* Format the upload data in the format as give here - https://marketing.adobe.com/developer/en_US/documentation/genesis/r-uploadclassifications
		 * The original formating of the data below is:
		 *
		 * {
			"integrationCode":"8a4b8b735cb2f696",
			"metricName":"Tracking ID",
			"columnNames":[
			"Key", "Name", "Color"],
			"rows":[
						["1234", "Name 1", "Red"],
						["1235", "Name 2", "White"]
					],
			"endOfBlock":"1"
			}
		 * 
		 * */
		
		String postData = "{"
				+ "\"integrationCode\":\"8a4b8b735cb2f696\","
				+ "\"metricName\":\"Tracking ID\","
				+ "\"columnNames\":["
						+ "\"Key\", \"Name\", \"Color\"],"
						+ "\"rows\":["
							+ "[\"1234\", \"Name 1\", \"Red\"],"
							+ "[\"1235\", \"Name 2\", \"White\"]"
							+ "],"
				+ "\"endOfBlock\":\"1\"}";
		
		// *** Use method callPOST with URL and post data as arguments ***
		String jsonResponse = client.callPOST(urlname, postData);
		
		// *** Capture the fileID for future use e.g. for Import.CheckClassificationUpload method ***
		System.out.println("Response: "+jsonResponse);
		
	}
	
}
