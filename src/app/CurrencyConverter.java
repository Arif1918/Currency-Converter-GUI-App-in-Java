package app;

import java.util.Set;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import okhttp3.*;

public class CurrencyConverter {

    private static final String API_URL = "https://api.apilayer.com/exchangerates_data/convert";
    private static final String API_SYMBOLS_URL = "https://api.apilayer.com/exchangerates_data/symbols";
    private static final String API_KEY = "vGWb226IGMvKtF14oRJbqm7RjnNB4Es4";
    
	public CurrencyConverter() {
		
		
		
	}
	
	public static double getConvertedAmount(String fromCurrency,String toCurrency,double amount) {
	
			
		OkHttpClient client = new OkHttpClient();
			
		String url = String.format("%s?to=%s&from=%s&amount=%f", API_URL,toCurrency,fromCurrency,amount);
			
		Request request = new Request.Builder()
				.url(url)
				.addHeader("apikey", API_KEY)
				.get()
				.build();
			
			
		try(Response response = client.newCall(request).execute()){
				
			if(response.isSuccessful() && response.body() != null) {
					
				return parseResponse(response.body().string());
					
			}else {
					
				System.err.println("Request failed: "+ response.message());
			}
				
		}catch(Exception e) {
				
			e.printStackTrace();
		}
			
		return 0;
		
	 }
	
	private static double parseResponse(String responseBody) {
		
		JsonElement jsonElement = JsonParser.parseString(responseBody);
		JsonObject jsonObject = jsonElement.getAsJsonObject();
		
		return jsonObject.get("result").getAsDouble();
		
	}

	public static String[]  getCurrencySymbols() {
			
		OkHttpClient client = new OkHttpClient();
		
		Request request = new Request.Builder()
			.url(API_SYMBOLS_URL)
		    .addHeader("apikey", API_KEY)
		    .get()
		    .build();
		    
		try(Response response = client.newCall(request).execute()){
		    
			if(response.isSuccessful() && response.body() != null) {
		    	
		    	return parseSymbolsResponse(response.body().string());
		    		
		    }else {
		    	
		    	System.err.println("Request failed: "+ response.message());
		    }

		}catch(Exception e) {
		    	
		    e.printStackTrace();
		}
		  
		return new String[0]; // Return an empty array instead of null
		
	}
	
	private static String[] parseSymbolsResponse(String responseBody) {
		
		JsonElement jsonElement = JsonParser.parseString(responseBody);
		JsonObject jsonObject = jsonElement.getAsJsonObject();
		JsonObject symbolsObject = jsonObject.getAsJsonObject("symbols");
		
		Set<String> keySet = symbolsObject.keySet();
		
		return keySet.toArray(new String[0]);
	}
	
	
}
