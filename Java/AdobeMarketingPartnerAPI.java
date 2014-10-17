import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.codec.binary.Base64;


public class AdobeMarketingPartnerAPI {

	String USERNAME = null;
	String SECRET = null;
	public AdobeMarketingPartnerAPI(String username, String secret) {
		
		this.USERNAME = username;
		this.SECRET = secret;
		
	}

	public String getWSSEHeader() throws NoSuchAlgorithmException{
		String WSSEHeader = null;
		// produce a time stamp
		SimpleDateFormat createdat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		createdat.setTimeZone(TimeZone.getTimeZone("GMT"));
		String timestamp = createdat.format(Calendar.getInstance().getTime()).trim();
		
		// create a random number for nonce
		String rand = Long.toString(new Date().getTime());
		// Base64 the random number to create a nonce
		String nonce = Base64.encodeBase64String(rand.getBytes()).trim();
		
		// Create a password digest from Timestamp, random number and Secret
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		md.reset();
		md.update(rand.getBytes());
		md.update(timestamp.getBytes());
		md.update(SECRET.getBytes());
		String passwordDigest = Base64.encodeBase64String(md.digest()).trim();
		
		// Create WSSE Header
		WSSEHeader = "UsernameToken Username=\""+USERNAME.trim()+"\", "+"PasswordDigest=\""+passwordDigest+"\", "+"Nonce=\""+nonce+"\", "+"Created=\""+timestamp+"\"";
		WSSEHeader = WSSEHeader.replace("\n", "");
	//		System.out.println("Header: "+WSSEHeader);
		
		return WSSEHeader;
	}
	
	public String callGet(String urlname){ //upper case GET
		
		String jsonResponse = null;

		try {
			URL url = new URL(urlname);
			URLConnection connection = url.openConnection();
			connection.setDoInput(true);
			connection.setDoOutput(false);
			connection.setUseCaches(false);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.addRequestProperty("X-WSSE", getWSSEHeader());
			connection.connect();
			InputStream in = connection.getInputStream();
		    BufferedReader res = new BufferedReader(new InputStreamReader(in, "UTF-8"));
		    StringBuffer sBuffer = new StringBuffer();
		    String temp = null;
		    while ((temp = res.readLine()) != null)
		    	sBuffer.append(temp);
		    	res.close();
			jsonResponse = sBuffer.toString();
		
			} catch (IOException e) {e.printStackTrace();} catch (Exception e) {e.printStackTrace();}
	
		return jsonResponse;
	}
	
	public void responseParser(String jsonResponse){
		
		String response = jsonResponse;
		JSONArray json = JSONArray.fromObject(response);
		for (int i=0; i<json.size(); i++){
			System.out.println("Integration "+i+" Details:");
			JSONObject jsonobj = JSONObject.fromObject(json.get(i));
			System.out.println("Integration Code: "+"\t"+jsonobj.get("integrationCode"));
			System.out.println("Integration Name: "+"\t"+jsonobj.get("integrationName"));
			System.out.println("Report Suite ID: "+"\t"+jsonobj.get("reportSuiteId"));
			System.out.println("Creation Date: "+"\t"+jsonobj.get("creationDate")+"\n");
			}
		
		}
		
	
	public String callPost(String urlname,String postData){  // Upper case to POST
		
		String jsonResponse=null;
		
		try {
			URL url = new URL(urlname);
			URLConnection connection = url.openConnection();
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setUseCaches(false);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.addRequestProperty("X-WSSE", getWSSEHeader());
			OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
			wr.write(postData);
			wr.flush();
			connection.connect();
			InputStream in = connection.getInputStream();
		    BufferedReader res = new BufferedReader(new InputStreamReader(in, "UTF-8"));
		    StringBuffer sBuffer = new StringBuffer();
		    String temp = null;
		    while ((temp = res.readLine()) != null)
		    	sBuffer.append(temp);
		    	res.close();
			jsonResponse = sBuffer.toString();
		
			} catch (IOException e) {e.printStackTrace();} catch (Exception e) {e.printStackTrace();}
	
		
		return jsonResponse;
		
	}
}
