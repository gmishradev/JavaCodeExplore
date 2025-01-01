package main.java.com;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;
import sun.net.www.http.HttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class RestCall {

    public static void main(String[] args) {
        getClusterDetails();
    }

   public static void getClusterDetails()
   {
       try {
           // create HTTP Client
           CloseableHttpClient httpClient = HttpClientBuilder.create().build();
          // HttpPost postRequest = new HttpPost("http://localhost:4020/api/v1/mc/clusterIds");

           HttpGet postRequest = new HttpGet("http://localhost:4020/api/v1/mc/clusterIds");
           // Add additional header to getRequest which accepts application/xml data
           postRequest.addHeader("accept", "application/json");

           // Execute your request and catch response
           HttpResponse response = httpClient.execute(postRequest);

           // Check for HTTP response code: 200 = success
           if (response.getStatusLine().getStatusCode() != 200) {
               throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
           }

           // Get-Capture Complete application/xml body response
           BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
           String output;
           System.out.println("============Output:============");

           // Simply iterate through XML response and show on console.
           while ((output = br.readLine()) != null) {
               System.out.println(output);
               parseJsonString(output);
           }

       } catch (ClientProtocolException e) {
           e.printStackTrace();

       } catch (IOException e) {
           e.printStackTrace();
       }
   }

    private static void parseJsonString(String output) {
        Map<String,String> map = new HashMap<>();
        JSONObject jsonObject = new JSONObject(output);
        JSONArray jsonArray = (JSONArray) jsonObject.get("clusters");
        for(int i =0;i<jsonArray.length();i++)
        {
            JSONObject object =  jsonArray.optJSONObject(i);
            map.put(object.getString("unravelId"),object.getString("displayName"));
        }
        System.out.println(map);
    }
}
