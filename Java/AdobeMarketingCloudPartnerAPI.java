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
import org.apache.commons.codec.binary.Base64;


public class AdobeMarketingCloudPartnerAPI {

	String USERNAME = null;
	String SECRET = null;
	
	// *** Constructor with username and password initialized ***
	public AdobeMarketingCloudPartnerAPI(String username, String secret) {
		
		this.USERNAME = username;
		this.SECRET = secret;
		
	}
	
	// *** The method getWSSEHeader generates WSSEHeader information for use in callPOST and callGET methods ***
	public String getWSSEHeader() throws NoSuchAlgorithmException{
		String WSSEHeader = null;
		
		// *** produce a time stamp
		SimpleDateFormat createdat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		createdat.setTimeZone(TimeZone.getTimeZone("GMT"));
		String timestamp = createdat.format(Calendar.getInstance().getTime()).trim();
		
		// *** create a random number for nonce
		String rand = Long.toString(new Date().getTime());
		
		// *** Base64 the random number to create a nonce
		String nonce = Base64.encodeBase64String(rand.getBytes()).trim();
		
		// *** Create a password digest from Timestamp, random number and Secret
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		md.reset();
		md.update(rand.getBytes());
		md.update(timestamp.getBytes());
		md.update(SECRET.getBytes());
		String passwordDigest = Base64.encodeBase64String(md.digest()).trim();
		
		// *** Create WSSE Header
		WSSEHeader = "UsernameToken Username=\""+USERNAME.trim()+"\", "+"PasswordDigest=\""+passwordDigest+"\", "+"Nonce=\""+nonce+"\", "+"Created=\""+timestamp+"\"";
		WSSEHeader = WSSEHeader.replace("\n", "");
		
		return WSSEHeader;
	}
	
	// *** Method to call a HTTP GET method. Accepts String URL as an argument ***
	public String callGET(String urlname){ 
		
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
	
	// *** Method to call a HTTP POST method. Accepts String URL and JSON formatted post data as an argument ***
	public String callPOST(String urlname,String postData){  
		
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
