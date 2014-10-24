# PHP example code

### Important notes
* All examples receive and print out the API response as a JSON formatted string - it is left as an exercise to parse that string with whatever JSON parser you'd like, including the built in json_decode function.
* It is expected that you will get api errors if you plug in your credentials and run these examples as they are. This is because the parameters passed in the api calls are NOT relevant for your partner account. They were provided so that you could see what valid parameters might look like.

### How to run

#### How to query for a list of active integrations.

1. open Main1.php and alter lines 8-9 to use your own Partner API username/secret
2. execute the Main1.php script by using “php Main1.php”
3. notice, that you receive errors on the second and third api calls, this is because the integrationCode being passed in the filter parameter is not a valid integrationCode for your partner account

#### How to import classification data into the Adobe Marketing Cloud for a customer integration

1. open Main2.php and alter lines 8-9 to use your own Partner API username/secret
2. execute the Main2.php script by using “php Main2.php”
3. notice, that you receive errors on the api call, this is because the integrationCode being passed in the filter parameter is not a valid integrationCode for your partner account. Also, the other data in the import (“metricName”, “columnNames”, “rows”) will likely not be structured correctly for your Data Connectors product

#### How to export analytics data from the Adobe Marketing Cloud for a customer integration

1. ...


