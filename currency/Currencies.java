/*

*/
package currency;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.MalformedURLException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
//import org.json.simple.JSONObject;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class Currencies	{

	public static String apiKey = "c26e32e388c84aac4872";

	public static void main(String args[])	{
		System.out.println("Currencies");
		
		String apiURL = "https://free.currconv.com/api/v7/currencies?apiKey=" + apiKey;
		long startTime = System.currentTimeMillis();

		try	{
			URL url = new URL(apiURL);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");
			if (conn.getResponseCode() != 200)  {
				throw new RuntimeException("Connection failed! HTTP error code: " + conn.getResponseCode());
			}
			BufferedReader reader = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			String output = reader.readLine();
			conn.disconnect();
			long endTime = System.currentTimeMillis();
			System.out.println("Request status: 200 (OK)\tResponse time: " + (endTime - startTime) + " ms");
			//System.out.println(output);
			
			JSONParser parser = new JSONParser();
			JSONObject obj = (JSONObject) parser.parse(output);
			//System.out.println(obj);
			JSONArray results = (JSONObject) obj.get("results");
			System.out.println(results);
			//for (JSONObject cur : obj)	{
			//	System.out.println(cur);
			//}
			
  		}	catch (MalformedURLException e) {
			e.printStackTrace();
			System.out.println("MalformedURLException");
        } catch (IOException e) {
            e.printStackTrace();
			//System.out.println("IOException " + conn.getResponseCode());
        } catch (ParseException e)  {
            e.printStackTrace();
			System.out.println("ParseException");
        }
	}
}
