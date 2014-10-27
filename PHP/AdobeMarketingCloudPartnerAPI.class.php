<?php

/**
 *  Client class shows an example of using the Adobe Marketing Cloud Partner API 
 *
 *  Author: Sean Gubler <sgubler@adobe.com>
 *
 */

class AdobeMarketingCloudPartnerAPI {

	private $username = '';
	private $secret='';
	
	private $isProxyEnabled=false;
	private $proxy='127.0.0.1:8888'; // used for making calls through Charles proxy
	private $noncePart='';
	
	/**
	 * Constructor
	 */
	public function AdobeMarketingCloudPartnerAPI($username, $secret){
		$this->username = $username;
		$this->secret = $secret;
		list($usec, $sec) = explode(' ', microtime());
		$this->noncePart = md5(getmypid() . "-" . $sec . '-' . $usec);
	}	
	
	public function callGET($url){
		//open connection
		$ch = $this->getCurlObject('wsse');
		
		//set the url
		curl_setopt($ch,CURLOPT_URL, $url);
		
		//execute GET
		$result = curl_exec($ch);

		//close connection
		curl_close($ch);
		
		return $result;	
	}
	
	public function callPOST($url, $jsonFormattedParameters){ 
		
		$headers = array(                                                                          
		    'Content-Type: application/json',                                                                                
    		'Content-Length: ' . strlen($jsonFormattedParameters)                                                                   
		);
		
		//open connection
		$ch = $this->getCurlObject('Wsse');
		
		//set the url
		curl_setopt($ch,CURLOPT_URL, $url);
		
		curl_setopt($ch, CURLOPT_CUSTOMREQUEST, "POST");                                                                    
		curl_setopt($ch, CURLOPT_POSTFIELDS, $jsonFormattedParameters);                                                                  
		curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);                                                                      
		
		//execute GET
		$result = curl_exec($ch);

		//close connection
		curl_close($ch);
		
		return $result;	
	}
	
	public function setProxyEnabled($isEnabled){
		$this->isProxyEnabled=$isEnabled;
	}
	
		
	private function getWSSEHeader(){
		$created = date('c');
		list($usec, $sec) = explode(' ', microtime());
		$nonce =  $this->noncePart . '-' . $sec . '-' . $usec ;
		$combined_str = $nonce.$created.$this->secret;
		$sha1_str = sha1($combined_str);
		$password_digest = base64_encode($sha1_str);
		return "X-WSSE: UsernameToken Username=\"$this->username\", PasswordDigest=\"$password_digest\", Nonce=\"$nonce\", Created=\"$created\"";
	}
	
	
	private function getCurlObject($authHeaderType){
		//open connection
		$ch = curl_init();
		
		$authHeaderType = strtoupper($authHeaderType);
		
		//set auth header
		if ($authHeaderType=='WSSE'){
			curl_setopt($ch, CURLOPT_HTTPHEADER, array(
				$this->getWSSEHeader()
			));		
		}
		
		if($this->isProxyEnabled){
			curl_setopt($ch, CURLOPT_PROXY, $this->proxy);
			//curl_setopt($ch, CURLOPT_PROXYUSERPWD, $proxyauth);
		}
		curl_setopt($ch, CURLOPT_FOLLOWLOCATION, 1);
		curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
		return $ch;
	}

}


?>
 
