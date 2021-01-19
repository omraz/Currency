/*



*/

package currency;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.MalformedURLException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Convert	{

	public static String apiKey = "c26e32e388c84aac4872";

	public static void main(String args[])	{
		
		System.out.println("Convert\tversion 0.0.0\t\u00a9 2020 OM");
		
		String apiURL = "https://free.currconv.com/api/v7/convert?q=EUR_CZK,USD_CZK&compact=ultra&apiKey=" + apiKey;
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
			
            JSONParser parser = new JSONParser();
			JSONObject obj = (JSONObject)parser.parse(output);

			Double eur = (Double)obj.get("EUR_CZK");
			System.out.printf("1 EUR = %.4f CZK\n", eur);
			Double usd = (Double)obj.get("USD_CZK");
			System.out.printf("1 USD = %.4f CZK\n", usd);
			
  		}	catch (MalformedURLException e) {
			e.printStackTrace();
			System.out.println("MalformedURLException");
        } catch (IOException e) {
            e.printStackTrace();
			//System.out.println("IOException " + conn.getResponseCode());
        } catch (ParseException e)  {
            e.printStackTrace();
			//System.out.println("ParseException");
        }
	}
}
