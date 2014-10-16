using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Net;
using System.Security.Cryptography;

namespace DataConnectorsREST
{

	/**
	 *  Client class shows an example of using the Adobe Marketing Cloud Partner API 
	 *
	 *  Author: Sean Gubler <sgubler@adobe.com>
	 *
	 */
    class AdobeMarketingCloudPartnerAPI
    {
        private string username = "";
        private string secret = "";

        public AdobeMarketingCloudPartnerAPI(string username, string secret)
        {
            this.username = username;
            this.secret = secret;
        }

        private string getWSSEHeader()
        {
            string nonce = Guid.NewGuid().ToString();
            string base64Nonce = Convert.ToBase64String(Encoding.UTF8.GetBytes(nonce));
            string timecreated = DateTime.UtcNow.ToString("yyyy-MM-ddTHH:mm:ssZ");
            SHA1 sha1 = new SHA1CryptoServiceProvider();
            string combinedString = nonce + timecreated + this.secret;
            byte[] data = System.Text.Encoding.UTF8.GetBytes(combinedString);
            byte[] digest = sha1.ComputeHash(data);
            string delimitedHexHash = BitConverter.ToString(digest).ToLower();
            string tmpresult = delimitedHexHash.Replace("-", "");
            string digestB64 = Convert.ToBase64String(Encoding.UTF8.GetBytes(tmpresult));

            return "UsernameToken Username=\"" + this.username + "\", PasswordDigest=\"" + digestB64 + "\", Nonce=\"" + nonce + "\", Created=\"" + timecreated + "\"";
        }

        public string callGET(string url)
        {
            WebClient client = new WebClient();
            client.Headers.Add("X-WSSE", this.getWSSEHeader());
            client.Headers.Add("Content-Type", "application/json");
            string jsonResult = client.DownloadString(url);
            client.Dispose();
            return jsonResult;
        }

        public string callPOST(string url, string jsonFormattedParameters)
        {
            WebClient client = new WebClient();
            client.Headers.Add("X-WSSE", this.getWSSEHeader());
            client.Headers.Add("Content-Type", "application/json");
            string jsonResult = client.UploadString(url, jsonFormattedParameters);
            client.Dispose();
            return jsonResult;
        }


    }
}
